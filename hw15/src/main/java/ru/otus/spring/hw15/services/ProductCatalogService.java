package ru.otus.spring.hw15.services;

import ru.otus.spring.hw15.domain.Product;

import java.util.List;

public interface ProductCatalogService {

    boolean isNotEmpty();

    boolean hasAllItemsInStock(List<Product> products);

    void reduceAmountOfProducts(List<Product> products);
}
