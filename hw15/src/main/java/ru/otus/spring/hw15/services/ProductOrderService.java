package ru.otus.spring.hw15.services;

import ru.otus.spring.hw15.domain.ProductOrder;

public interface ProductOrderService {

    boolean hasAllItemsInStock(ProductOrder productOrder);

    ProductOrder process(ProductOrder productOrder);

    ProductOrder reject(ProductOrder productOrder);

    ProductOrder complete(ProductOrder productOrder);
}
