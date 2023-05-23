package ru.otus.spring.hw15.messaging.gateways;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.spring.hw15.domain.ProductOrder;

@MessagingGateway
public interface ProductOrderGateway {

    @Gateway(requestChannel = "productOrderInputChannel", replyChannel = "productOrderOutputChannel")
    ProductOrder process(ProductOrder productOrder);
}
