package ru.otus.spring.hw15.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw15.domain.ProductOrder;
import ru.otus.spring.hw15.domain.ProductOrderStatus;
import ru.otus.spring.hw15.services.ProductCatalogService;
import ru.otus.spring.hw15.services.ProductOrderService;

@Service
@RequiredArgsConstructor
public class InMemoryProductOrderService implements ProductOrderService {

    private final ProductCatalogService productCatalogService;

    @Override
    public boolean hasAllItemsInStock(ProductOrder productOrder) {
        return productCatalogService.hasAllItemsInStock(productOrder.getProducts());
    }

    @Override
    public ProductOrder process(ProductOrder productOrder) {
        productOrder.setStatus(ProductOrderStatus.PROCESSING);
        productCatalogService.reduceAmountOfProducts(productOrder.getProducts());
        return productOrder;
    }

    @Override
    public ProductOrder reject(ProductOrder productOrder) {
        productOrder.setStatus(ProductOrderStatus.REJECTED);
        return productOrder;
    }

    @Override
    public ProductOrder complete(ProductOrder productOrder) {
        productOrder.setStatus(ProductOrderStatus.COMPLETED);
        return productOrder;
    }
}
