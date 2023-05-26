package ru.otus.spring.hw15.domain;

import lombok.Data;

import java.util.List;

@Data
public class ProductCatalog {

    private List<Product> products;

    public ProductCatalog(Product... products) {
        this.products = List.of(products);
    }

    public List<Product> getAllItemsInStock() {
        return products.stream()
                .filter(product -> product.getAmount() != 0)
                .toList();
    }

    public boolean isEmpty() {
        return products.stream()
                .allMatch(product -> product.getAmount() == 0);
    }

    public boolean hasAllItemsInStock(List<Product> products) {
        return products.stream()
                .allMatch(this::hasRequiredAmountInStock);
    }

    public void reduceAmountOfProducts(List<Product> products) {
        products.forEach(this::reduceProductAmount);
    }

    public void reduceProductAmount(Product product) {
        this.products.stream()
                .filter(p -> p.getId() == product.getId())
                .forEach(p -> p.reduceAmount(product.getAmount()));
    }

    public boolean hasRequiredAmountInStock(Product product) {
        return this.products.stream()
                .anyMatch(p -> p.getId() == product.getId() && p.getAmount() >= product.getAmount());
    }
}
