package ru.otus.spring.hw15.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "from")
public class Product {

    private long id;
    private String name;
    private int amount;

    public void reduceAmount(int amount) {
        this.amount = this.amount - amount;
    }
}
