package com.techelevator;

import org.junit.Test;

import java.math.BigDecimal;
import static org.junit.Assert.*;

public class MoneyTest {

    @Test
    public void getChangeTest(){

        Money money = new Money();



        BigDecimal amountInPennies = new BigDecimal("10.35");

        int expectedQuarters= 41;
        int expectedDime= 1;
        int expectedNickel= 0;

        assertEquals("Quarters: " + expectedQuarters + "\nDimes: " + expectedDime + "\nNickels: " + expectedNickel,money.getChange(amountInPennies));

    }

    @Test
    public void getCurrentTest(){

        Money money = new Money();
        money.feedCurrent(new BigDecimal("10.00"));
        BigDecimal expectedCurrent = new BigDecimal("10.00");

        assertEquals(expectedCurrent,  money.getCurrent());

    }




    }
