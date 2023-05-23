package ru.otus.spring.hw15.utils;

import lombok.experimental.UtilityClass;
import ru.otus.spring.hw15.domain.Product;
import ru.otus.spring.hw15.domain.ProductOrder;
import ru.otus.spring.hw15.domain.ProductOrderStatus;

import java.util.List;

import static ru.otus.spring.hw15.utils.Randoms.DEFAULT_SHORT_RANGE;
import static ru.otus.spring.hw15.utils.Randoms.randomInt;

@UtilityClass
public class ProductOrderGenerator {

    // @formatter:off
    public static List<ProductOrder> generate() {
        return List.of(
                ProductOrder.from(1, ProductOrderStatus.OPEN, List.of(
                        Product.from(1, "Samsung Galaxy S21",        randomInt(DEFAULT_SHORT_RANGE)))
                ),
                ProductOrder.from(2, ProductOrderStatus.OPEN, List.of(
                        Product.from(2, "Apple iPhone 12 Pro",       randomInt(DEFAULT_SHORT_RANGE)))
                ),
                ProductOrder.from(3, ProductOrderStatus.OPEN, List.of(
                        Product.from(3, "Google Pixel 5",            randomInt(DEFAULT_SHORT_RANGE)))
                ),
                ProductOrder.from(4, ProductOrderStatus.OPEN, List.of(
                        Product.from(4, "Sony Xperia 1 III",         randomInt(DEFAULT_SHORT_RANGE)))
                ),
                ProductOrder.from(5, ProductOrderStatus.OPEN, List.of(
                        Product.from(5, "OnePlus 9 Pro",             randomInt(DEFAULT_SHORT_RANGE)))
                ),
                ProductOrder.from(6, ProductOrderStatus.PROCESSING, List.of(
                        Product.from(5, "OnePlus 9 Pro",             randomInt(DEFAULT_SHORT_RANGE)))
                ),
                ProductOrder.from(7, ProductOrderStatus.REJECTED, List.of(
                        Product.from(5, "OnePlus 9 Pro",             randomInt(DEFAULT_SHORT_RANGE)))
                ),
                ProductOrder.from(8, ProductOrderStatus.COMPLETED, List.of(
                        Product.from(5, "OnePlus 9 Pro",             randomInt(DEFAULT_SHORT_RANGE)))
                )
        );
    }
    // @formatter:on

}
