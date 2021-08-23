package com.techelevator;

import java.math.BigDecimal;

public class Gum extends Item {
    private String type = "Gum";

    public Gum(String slot, String name, BigDecimal price) {
        super(slot, name, price);
    }

    public String getSound() {
        return "Chew Chew, Yum!";
    }
}
