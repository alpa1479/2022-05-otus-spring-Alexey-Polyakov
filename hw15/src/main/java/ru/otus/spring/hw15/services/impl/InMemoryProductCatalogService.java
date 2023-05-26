package ru.otus.spring.hw15.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw15.domain.Product;
import ru.otus.spring.hw15.domain.ProductCatalog;
import ru.otus.spring.hw15.services.ProductCatalogService;

import java.util.List;

import static ru.otus.spring.hw15.utils.Randoms.DEFAULT_LONG_RANGE;
import static ru.otus.spring.hw15.utils.Randoms.randomInt;

@Slf4j
@Service
public class InMemoryProductCatalogService implements ProductCatalogService {

    // @formatter:off
    private final static ProductCatalog CATALOG = new ProductCatalog(
            Product.from(1,  "Samsung Galaxy S21",    randomInt(DEFAULT_LONG_RANGE)),
            Product.from(2,  "Apple iPhone 12 Pro",   randomInt(DEFAULT_LONG_RANGE)),
            Product.from(3,  "Google Pixel 5",        randomInt(DEFAULT_LONG_RANGE)),
            Product.from(4,  "Sony Xperia 1 III",     randomInt(DEFAULT_LONG_RANGE)),
            Product.from(5,  "OnePlus 9 Pro",         randomInt(DEFAULT_LONG_RANGE))
    );
    // @formatter:on

    @Override
    public boolean isNotEmpty() {
        return !CATALOG.isEmpty();
    }

    @Override
    public boolean hasAllItemsInStock(List<Product> products) {
        log.info(">>>> catalog items that still in stock = {}", CATALOG.getAllItemsInStock());
        return CATALOG.hasAllItemsInStock(products);
    }

    @Override
    public void reduceAmountOfProducts(List<Product> products) {
        CATALOG.reduceAmountOfProducts(products);
    }
}
