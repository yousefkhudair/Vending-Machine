package com.techelevator;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ItemTest {

    @Test
    public void getSlotTest() {
        Item cola = new Drink("C1", "Cola", new BigDecimal("1.25"));
        String actualSlot = cola.getSlot();
        String expectedSlot = "C1";
        assertEquals(expectedSlot, actualSlot);
    }

    @Test
    public void getInventoryTest() {
        Item moonpie = new Candy("B1", "Moonpie", new BigDecimal("1.80"));
        int actualInventory = moonpie.getInventory();
        int expectedInventory = 5;
        assertEquals(expectedInventory, actualInventory);

    }

    @Test
    public void setInventoryTest() {
        Item triplemint = new Gum("D4", "Triplemint", new BigDecimal("0.75"));
        int beforeSettingInv = triplemint.getInventory();
        triplemint.setInventory(3);
        int afterSettingInv = triplemint.getInventory();
        int expectedAfterSetting = 3;
        assertEquals(expectedAfterSetting, afterSettingInv);
    }

    @Test
    public void getNameTest() {
        Item crunchie = new Candy("B4", "Crunchie", new BigDecimal("1.75"));
        String actualGetNameResult = crunchie.getName();
        String expectedGetNameResult = "Crunchie";
        assertEquals(expectedGetNameResult, actualGetNameResult);
    }

    @Test
    public void getPriceTest() {
        Item stackers = new Chip("A2", "Stackers", new BigDecimal("1.45"));
        BigDecimal actualPrice = stackers.getPrice();
        BigDecimal expectedPrice = new BigDecimal("1.45");
        assertEquals(expectedPrice, actualPrice);
    }

    @Test
    public void candyGetSoundTest() {
        Item candy = new Candy("B2", "Cowtales", new BigDecimal("1.50"));
        String actualResultGetSound = candy.getSound();
        String expectedResultGetSound = "Munch Munch, Yum!";
        assertEquals(expectedResultGetSound, actualResultGetSound);
    }

    @Test
    public void chipGetSoundTest() {
        Item chip = new Chip("A4", "Cloud Popcorn", new BigDecimal("3.65"));
        String actualGetSoundResult = chip.getSound();
        String expectedGetSoundResult = "Crunch Crunch, Yum!";
        assertEquals(expectedGetSoundResult, actualGetSoundResult);
    }

    @Test
    public void drinkGetSoundTest() {
        Item drink = new Drink("C2", "Dr. Salt", new BigDecimal("1.50"));
        String actualGetSoundResult = drink.getSound();
        String expectedGetSoundResult = "Glug Glug, Yum!";
        assertEquals(expectedGetSoundResult, actualGetSoundResult);
    }

    @Test
    public void gumGetSoundTest() {
        Item gum = new Gum("D3", "Chiclets", new BigDecimal("0.75"));
        String actualGetSoundResult = gum.getSound();
        String expectedGetSoundResult = "Chew Chew, Yum!";
        assertEquals(expectedGetSoundResult, actualGetSoundResult);
    }

}