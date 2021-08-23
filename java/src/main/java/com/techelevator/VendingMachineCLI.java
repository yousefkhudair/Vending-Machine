package com.techelevator;

import com.techelevator.view.Menu;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.FileSystemNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class VendingMachineCLI {

    private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
    private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
    private static final String MAIN_MENU_EXIT = "Exit";
    private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_EXIT};
    private static final String[] PURCHASE_OPTIONS = {"Feed Money", "Select Product", "Finish Transaction"};

    private Menu menu;
    private List<Item> itemList = new ArrayList<>();
    //Money money = new Money();

    public VendingMachineCLI(Menu menu) {
        this.menu = menu;
    }

    public void run(){

        loadData();

        while (true) {
            String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

            if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
                displayItems();
            } else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
                displayPurchaseMenu();
            } else if (choice.equals(MAIN_MENU_EXIT)) {
                System.out.println("Thank you for using the Vendo-Matic 800!");
                System.exit(1);
            }
        }
    }

    public void loadData() {
        File file = new File("vendingmachine.csv");

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] lineArr = line.split("\\|");
                String slot = lineArr[0];
                String name = lineArr[1];
                BigDecimal price = new BigDecimal(lineArr[2]);
                String type = lineArr[3];

                if (type.equals("Chip")) {
                    Item item = new Chip(slot, name, price);
                    itemList.add(item);
                } else if (type.equals("Drink")) {
                    Item item = new Drink(slot, name, price);
                    itemList.add(item);
                } else if (type.equals("Candy")) {
                    Item item = new Candy(slot, name, price);
                    itemList.add(item);
                } else if (type.equals("Gum")) {
                    Item item = new Gum(slot, name, price);
                    itemList.add(item);
                }

            }
        } catch (FileSystemNotFoundException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void displayItems() {
        System.out.println("===============================");
        System.out.println("     Vending Machine Items     ");
        System.out.println("===============================");

        for (Item item : itemList) {
            System.out.println(item.getSlot() + " | " + item.getName() + " | $" + item.getPrice() + " | " + item.getInventory() + " Remaining");
        }
    }

    public void displayPurchaseMenu() {
        System.out.println("===============================");
        System.out.println("         Purchase Menu         ");
        System.out.println("===============================");

        boolean stay = true;
        Money myMoney = new Money();


        while (stay) {
            System.out.println("\nCurrent Money Provided: $" + myMoney.getCurrent());
            String choice = (String) menu.getChoiceFromOptions(PURCHASE_OPTIONS);

            if (choice.equals("Feed Money")) {
                feedMoney(myMoney);
            } else if (choice.equals("Select Product")) {
                selectProduct(myMoney);
            } else if (choice.equals("Finish Transaction")) {
                System.out.println("Thank you for using the Vendo-Matic 800!");
                System.out.println("Here is your change. Have a nice day!");
                System.out.println(myMoney.getChange(myMoney.getCurrent()));
                //SalesLogger
                System.exit(1);

            }
        }
    }

    public void feedMoney(Money myMoney) {
        Scanner scanner = new Scanner(System.in);
        boolean bool = true;
        BigDecimal prevAmount = myMoney.getCurrent();

        while (bool) {
            try {
                System.out.print("\nPlease feed money (1.00, 2.00, 5.00, 10.00): ");
                String amount = scanner.nextLine();
                BigDecimal myAmount = new BigDecimal(amount);
                myAmount = myAmount.setScale(2, RoundingMode.HALF_UP);

                List<BigDecimal> checkAmount = new ArrayList<>();
                checkAmount.add(new BigDecimal("1.00"));
                checkAmount.add(new BigDecimal("2.00"));
                checkAmount.add(new BigDecimal("5.00"));
                checkAmount.add(new BigDecimal("10.00"));

                if (checkAmount.contains(myAmount)) {
                    myMoney.feedCurrent(myAmount);
                    LogWriter log = new LogWriter("log.txt");
                    log.writeToFile("FEED MONEY: $" + prevAmount + " $" + myMoney.getCurrent());
                } else {
                    System.out.println("\nInvalid amount, returning back to purchase menu.");
                    break;
                }

                System.out.print("\nWould you like to feed more money? (Y/N): ");
                String yesOrNo = scanner.nextLine().toLowerCase();
                if (yesOrNo.equals("n")) {
                    bool = false;
                } else if (!yesOrNo.equals("y")) {
                    System.out.println("\nInvalid choice, returning back to purchase menu.");
                    bool = false;
                }

            } catch (NumberFormatException e) {
                System.out.println("\nInvalid entry, returning back to purchase menu.");
                break;
            }
        }
    }

    public void selectProduct(Money myMoney) {
        displayItems();
        Scanner scanner = new Scanner(System.in);
        boolean con = true;

        while (con) {
            System.out.println("Which item would you like to select");
            String itemToPurchase = scanner.nextLine();

            boolean valid = false;

            for (Item item : itemList) {
                if (item.getSlot().equals(itemToPurchase.toUpperCase()) &&
                        myMoney.getCurrent().compareTo(item.getPrice()) >= 0 && item.getInventory() >0) { // myMoney.getCurrent() >= item.getPrice() &&
                        valid = true;
                }

                if (valid) {

                    BigDecimal beforeTrans = myMoney.getCurrent();
                    item.setInventory(item.getInventory()-1);
                    myMoney.makePurchase(item.getPrice());
                    System.out.println("======================================");
                    System.out.println("             " + item.getName());
                    System.out.println("                $" + item.getPrice());
                    System.out.println("======================================");
                    System.out.println("        " +item.getSound()+"       ");
                    System.out.println("======================================");

                    LogWriter log = new LogWriter("log.txt");

                    log.writeToFile(item.getName() + " " + item.getSlot() +
                            " $" + beforeTrans + " $" + myMoney.getCurrent());

                    con = false;
                    break;
                }
            }

            if (!valid){
                System.out.println("Transaction Declined. Returning to Purchase Menu");
                con= false;
            }
        }

    }

    public static void main(String[] args) {
        System.out.println("==============================================");
        System.out.println("        Welcome to the Vendo-Matic 800!       ");
        System.out.println("==============================================");
        Menu menu = new Menu(System.in, System.out);
        VendingMachineCLI cli = new VendingMachineCLI(menu);
        cli.run();
    }
}
