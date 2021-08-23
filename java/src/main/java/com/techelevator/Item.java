package com.techelevator;

import java.math.BigDecimal;

public abstract class Item {

    private String slot;
    private String name;
    private BigDecimal price;
    //private String type;
    private int inventory;

    public Item(String slot, String name, BigDecimal price) {
        this.inventory = 5;
        this.slot = slot;
        this.name = name;
        this.price = price;
    }
    public String getSlot() {
        return slot;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public String getName() {
        return name;
    }
    public BigDecimal getPrice() {
        return price;
    }

    public abstract String getSound();
}
