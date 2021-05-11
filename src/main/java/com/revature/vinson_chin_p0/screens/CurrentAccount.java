package com.revature.vinson_chin_p0.screens;

import com.revature.vinson_chin_p0.daos.AccountDAO;
import com.revature.vinson_chin_p0.models.Account;
import com.revature.vinson_chin_p0.models.AppUser;
import com.revature.vinson_chin_p0.services.AccountService;
import com.revature.vinson_chin_p0.util.ScreenRouter;

import java.io.BufferedReader;

public class CurrentAccount extends Screen {

    BufferedReader consoleReader;
    ScreenRouter router;
    AccountService accountService;
    AccountDAO accountDao = new AccountDAO();

    public CurrentAccount(BufferedReader consoleReader, ScreenRouter router, AccountService accountService) {
        super("CurrentAccount", "/account");
        this.consoleReader = consoleReader;
        this.router = router;
        this.accountService = accountService;
    }

    @Override
    public void render(AppUser currentUser) {}

    @Override
    public void render() {}

    @Override
    public void render(AppUser currentUser, Account currentAccount) {

//        int userId = currentAccount.getUserid();
//        double balance = currentAccount.getBalance();
//        String name = currentAccount.getName();
//        String accountType = currentAccount.getAccountType();
        double amount;
        Account[] accounts = accountDao.findAccounts(currentUser.getId());

        try {
            System.out.println("Account: " + currentAccount.getName());
            System.out.println("Type:" + currentAccount.getAccountType());
            System.out.print("Balance: $");
            System.out.printf("%.2f", currentAccount.getBalance());
            System.out.println("What would you like to do?");
            System.out.println("1) Deposit");
            System.out.println("2) Withdraw");
            System.out.println("3) Transfer");
            System.out.println("4) View Transactions");
            System.out.println("5) Change name or type");
            System.out.println("6) Return to accounts");
            String userSelection = consoleReader.readLine();

            switch (userSelection) {
                case "1":
                    System.out.print("How much would you like to deposit? ");
                    amount = Double.parseDouble(consoleReader.readLine());
                    currentAccount.setBalance(currentAccount.getBalance() + amount);
                    System.out.println("Money deposited\n");
                    this.render(currentUser, currentAccount);
                    break;
                case "2":
                    System.out.print("How much would you like to withdraw? ");
                    amount = Double.parseDouble(consoleReader.readLine());
                    if (amount > currentAccount.getBalance()) {
                        System.out.println("Not enough funds in account\n");
                    } else {
                        currentAccount.setBalance(currentAccount.getBalance() - amount);
                        System.out.println("Money withdrawn\n");
                    }
                    this.render(currentUser, currentAccount);
                    break;
                case "3":
                    System.out.println("Which of your accounts do you want to transfer?");

                    Account otherAccount;
                    int i = -1;

                    if (accounts[0] != null) {
                        i++;
                        while (accounts[i] != null) {
                            System.out.println((i + 1) + ") " + accounts[i].getName());
                            i++;
                        }
                        System.out.print("> ");
                        int selection = Integer.parseInt(consoleReader.readLine());

                        if (selection <= i) {
                            otherAccount = accounts[selection - 1];
                        } else {
                            System.out.println("Invalid selection\n");
                            this.render(currentUser, currentAccount);
                            break;
                        }

                        System.out.print("How much do you want to transfer? ");
                        amount = Double.parseDouble(consoleReader.readLine());
                        if (amount > currentAccount.getBalance()) {
                            System.out.println("Not enough funds in account\n");
                        } else {
                            currentAccount.setBalance(currentAccount.getBalance() - amount);
                            otherAccount.setBalance(otherAccount.getBalance() + amount);
                            System.out.println("Money transferred\n");
                        }
                    } else {
                        System.out.println("No other accounts to transfer\n");
                    }
                    this.render(currentUser, currentAccount);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
