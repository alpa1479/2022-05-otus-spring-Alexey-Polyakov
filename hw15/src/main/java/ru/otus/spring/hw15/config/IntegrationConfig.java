package ru.otus.spring.hw15.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;
import ru.otus.spring.hw15.domain.ProductOrder;
import ru.otus.spring.hw15.messaging.selectors.OpenProductOrderSelector;
import ru.otus.spring.hw15.services.ProductOrderService;

import java.util.concurrent.Executors;

@Configuration
@RequiredArgsConstructor
public class IntegrationConfig {

    private final ProductOrderService productOrderService;

    @Bean
    public QueueChannel productOrderInputChannel() {
        return MessageChannels.queue(10).get();
    }

    @Bean
    public PublishSubscribeChannel productOrderOutputChannel() {
        return MessageChannels.publishSubscribe().get();
    }

    @Bean
    public QueueChannel productOrderProcessingChannel() {
        return MessageChannels.queue(10).get();
    }

    @Bean
    public QueueChannel productOrderRejectionChannel() {
        return MessageChannels.queue(10).get();
    }

    @Bean
    public QueueChannel productOrderCompletionChannel() {
        return MessageChannels.queue(10).get();
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedRate(100)
                .taskExecutor(Executors.newFixedThreadPool(5))
                .maxMessagesPerPoll(10)
                .get();
    }

    @Bean
    public IntegrationFlow productOrderFiltrationFlow(OpenProductOrderSelector openProductOrderSelector) {
        return IntegrationFlows.from(productOrderInputChannel())
                .log(message -> ">>>> filtering order = " + message.getPayload())
                .filter(openProductOrderSelector, spec -> spec.discardChannel(productOrderRejectionChannel()))
                .<ProductOrder, Boolean>route(
                        productOrderService::hasAllItemsInStock,
                        spec -> spec
                                .channelMapping(true, productOrderProcessingChannel())
                                .channelMapping(false, productOrderRejectionChannel())
                )
                .get();
    }

    @Bean
    public IntegrationFlow productOrderProcessingFlow() {
        return IntegrationFlows.from(productOrderProcessingChannel())
                .<ProductOrder>handle((payload, headers) -> productOrderService.process(payload))
                .log(message -> ">>>> order processed = " + message.getPayload())
                .channel(productOrderCompletionChannel())
                .get();
    }

    @Bean
    public IntegrationFlow productOrderRejectionFlow() {
        return IntegrationFlows.from(productOrderRejectionChannel())
                .<ProductOrder>handle((payload, headers) -> productOrderService.reject(payload))
                .<ProductOrder>log(message -> ">>>> order rejected = " + message.getPayload())
                .channel(productOrderOutputChannel())
                .get();
    }

    @Bean
    public IntegrationFlow productOrderCompletionFlow() {
        return IntegrationFlows.from(productOrderCompletionChannel())
                .<ProductOrder>handle((payload, headers) -> productOrderService.complete(payload))
                .<ProductOrder>log(message -> ">>>> order completed = " + message.getPayload())
                .channel(productOrderOutputChannel())
                .get();
    }
}
