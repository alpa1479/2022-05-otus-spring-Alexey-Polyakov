package ru.otus.spring.hw15.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "from")
public class ProductOrder {

    private long id;
    private ProductOrderStatus status;
    private List<Product> products;
}
