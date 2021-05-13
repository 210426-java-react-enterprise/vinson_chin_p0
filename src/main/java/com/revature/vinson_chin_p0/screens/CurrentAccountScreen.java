package com.revature.vinson_chin_p0.screens;

import com.revature.vinson_chin_p0.daos.AccountDAO;
import com.revature.vinson_chin_p0.exceptions.InvalidRequestException;
import com.revature.vinson_chin_p0.models.Account;
import com.revature.vinson_chin_p0.models.AppUser;
import com.revature.vinson_chin_p0.models.Transaction;
import com.revature.vinson_chin_p0.services.AccountService;
import com.revature.vinson_chin_p0.services.TransactionService;
import com.revature.vinson_chin_p0.util.ScreenRouter;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Current account screen used for viewing information, changing balance, and navigating to transactions
 * @author Vinson Chin
 *
 */
public class CurrentAccountScreen extends Screen {

    BufferedReader consoleReader;
    ScreenRouter router;
    AccountService accountService;
    AccountDAO accountDao;
    TransactionService transactionService;

    public CurrentAccountScreen(BufferedReader consoleReader,
                                ScreenRouter router,
                                AccountService accountService,
                                AccountDAO accountDao,
                                TransactionService transactionService) {
        super("CurrentAccountScreen", "/account");
        this.consoleReader = consoleReader;
        this.router = router;
        this.accountService = accountService;
        this.accountDao = accountDao;
        this.transactionService = transactionService;
    }

    /**
     * Render used with passing in current user
     * Not used on this screen
     *
     * @param currentUser
     */
    @Override
    public void render(AppUser currentUser) {}

    /**
     * Render used without passing anything
     * Not used on this screen
     *
     */
    @Override
    public void render() {}

    /**
     * Render used with passing in current user and current account
     * Used on this screen
     * Allows users to change balance in account and navigation to other screens
     *
     * @param currentUser
     * @param currentAccount
     */
    @Override
    public void render(AppUser currentUser, Account currentAccount) {

        double amount;
        double accountBalance = currentAccount.getBalance();
        Transaction newTransaction;
        Account[] accounts = accountDao.findOtherAccounts(currentUser.getId(), currentAccount.getId());

        try {

            System.out.println("\nAccount: " + currentAccount.getName());
            System.out.println("Type:" + currentAccount.getAccountType());
            System.out.print("Balance: $");
            System.out.printf("%.2f", currentAccount.getBalance());
            System.out.println("\nWhat would you like to do?");
            System.out.println("1) Deposit");
            System.out.println("2) Withdraw");
            System.out.println("3) Transfer");
            System.out.println("4) View Transactions");
            System.out.println("5) Change name or type");
            System.out.println("6) Return to accounts");
            System.out.print("> ");
            String userSelection = consoleReader.readLine();

            switch (userSelection) {
                case "1":
                    System.out.print("How much would you like to deposit? ");
                    amount = Double.parseDouble(consoleReader.readLine());
                    newTransaction = new Transaction(currentAccount.getId(), amount, "Deposit");
                    transactionService.create(newTransaction);
                    currentAccount.setBalance(currentAccount.getBalance() + amount);
                    accountService.updateBalance(currentAccount);
                    System.out.println("Money deposited");
                    this.render(currentUser, currentAccount);
                    break;
                case "2":
                    System.out.print("How much would you like to withdraw? ");
                    amount = Double.parseDouble(consoleReader.readLine());
                    if (amount > currentAccount.getBalance()) {
                        System.out.println("Not enough funds in account");
                    } else {
                        newTransaction = new Transaction(currentAccount.getId(), amount, "Withdrawal");
                        transactionService.create(newTransaction);
                        currentAccount.setBalance(currentAccount.getBalance() - amount);
                        accountService.updateBalance(currentAccount);
                        System.out.println("Money withdrawn");
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
                            System.out.println("Invalid selection");
                            this.render(currentUser, currentAccount);
                            break;
                        }

                        System.out.print("How much do you want to transfer? ");
                        amount = Double.parseDouble(consoleReader.readLine());
                        if (amount > currentAccount.getBalance()) {
                            System.out.println("Not enough funds in account");
                        } else {
                            newTransaction = new Transaction(currentAccount.getId(), amount, "Transferred out");
                            transactionService.create(newTransaction);
                            currentAccount.setBalance(currentAccount.getBalance() - amount);
                            accountService.updateBalance(currentAccount);
                            newTransaction = new Transaction(otherAccount.getId(), amount, "Transferred in");
                            transactionService.create(newTransaction);
                            otherAccount.setBalance(otherAccount.getBalance() + amount);
                            accountService.updateBalance(otherAccount);
                            System.out.println("Money transferred");
                        }
                    } else {
                        System.out.println("No other accounts to transfer");
                    }
                    this.render(currentUser, currentAccount);
                    break;
                case "4":
                    System.out.println("Displaying all transactions for this account");
                    router.navigate("/transactions", currentUser, currentAccount);
                    break;
                case "5":
                    System.out.println("Navigating to the account information\n");
                    router.navigate("/accountInformation", currentUser, currentAccount);
                    break;
                case "6":
                    System.out.println("Returning to accounts screen\n");
                    router.navigate("/accounts", currentUser);
                    break;
                default:
                    System.out.println("Invalid Selection");
                    this.render(currentUser, currentAccount);
            }

        } catch (NumberFormatException e) {
            System.err.println("Amount should be a number");
            this.render(currentUser, currentAccount);
        } catch (InvalidRequestException e) {
            System.err.println(e);
            currentAccount.setBalance(accountBalance);
            this.render(currentUser, currentAccount);
        } catch (IOException e) {
            System.err.println("Unable to read input or navigating to another screen...exiting application");
            System.exit(0);
        }

    }
}
