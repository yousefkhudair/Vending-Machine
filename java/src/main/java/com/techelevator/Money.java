package com.techelevator;

import java.math.BigDecimal;

public class Money {

    private BigDecimal current = new BigDecimal("0.00");

    public BigDecimal getCurrent() {
        return current;
    }

    public void feedCurrent(BigDecimal money){
        this.current =this.current.add(money);
    }

    public void makePurchase(BigDecimal cost) {
           this.current = this.current.subtract(cost);

    }

    public String getChange(BigDecimal amount) {

        double amountInPennies = amount.doubleValue()*100;

       int quarter;
       int dime;
       int nickel;

        quarter = (int) (amountInPennies / 25);
        amountInPennies %= 25;
        dime =  (int) (amountInPennies / 10);
        amountInPennies %= 10;
        nickel = (int) (amountInPennies / 5);


        this.current = new BigDecimal("0.00");
        LogWriter log = new LogWriter("log.txt");

        log.writeToFile("GIVE CHANGE: $" + amount + " $" + this.current);

        return "Quarters: " + quarter + "\nDimes: " + dime + "\nNickels: " + nickel;

    }


}
