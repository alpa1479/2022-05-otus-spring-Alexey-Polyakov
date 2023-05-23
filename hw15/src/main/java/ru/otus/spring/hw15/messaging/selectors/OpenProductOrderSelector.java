package ru.otus.spring.hw15.messaging.selectors;

import org.springframework.integration.core.MessageSelector;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw15.domain.ProductOrder;
import ru.otus.spring.hw15.domain.ProductOrderStatus;

@Service
public class OpenProductOrderSelector implements MessageSelector {

    @Override
    public boolean accept(Message<?> message) {
        var productOrder = (ProductOrder) message.getPayload();
        return productOrder.getStatus() == ProductOrderStatus.OPEN;
    }
}
