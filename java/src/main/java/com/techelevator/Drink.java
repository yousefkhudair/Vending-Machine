package com.techelevator;

import java.math.BigDecimal;

public class Drink extends Item {

    private String type = "Drink";

    public Drink(String slot, String name, BigDecimal price) {
        super(slot, name, price);
    }

    public String getSound() {
        return "Glug Glug, Yum!";
    }
}
