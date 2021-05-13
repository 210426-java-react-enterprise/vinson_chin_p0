package com.revature.vinson_chin_p0.screens;

import com.revature.vinson_chin_p0.daos.TransactionDAO;
import com.revature.vinson_chin_p0.models.Account;
import com.revature.vinson_chin_p0.models.AppUser;
import com.revature.vinson_chin_p0.models.Transaction;
import com.revature.vinson_chin_p0.util.ScreenRouter;

import java.io.BufferedReader;
import java.io.IOException;

public class TransactionsScreen extends Screen {

    BufferedReader consoleReader;
    ScreenRouter router;
    TransactionDAO transactionDao = new TransactionDAO();

    public TransactionsScreen(BufferedReader consoleReader, ScreenRouter router) {
        super("TransactionsScreen", "/transactions");
        this.consoleReader = consoleReader;
        this.router = router;
    }

    @Override
    public void render(AppUser currentUser) {
    }

    @Override
    public void render() {
    }

    @Override
    public void render(AppUser currentUser, Account currentAccount) {

        int i = -1;

        try {

            Transaction[] transactions = transactionDao.findTransactions(currentAccount.getId());
            System.out.println("\nTransactions");
            if (transactions[0] != null) {
                i++;
                while (transactions[i] != null) {
                    System.out.printf("%tF %tT\t\t%15s\t\t%.2f\n",transactions[i].getDateTime(),
                                                                  transactions[i].getDateTime(),
                                                                  transactions[i].getTransactionType(),
                                                                  transactions[i].getAmount());
                    i++;
                }
            } else {
                System.out.println("No transactions on this account");
            }
            System.out.print("Enter 'r' to refresh or any key to return: ");
            String accountReturn = consoleReader.readLine();

            if (accountReturn.equals("r")) {
                this.render(currentUser, currentAccount);
            } else {
                router.navigate("/account", currentUser, currentAccount);
            }

        } catch (IOException e) {
            System.err.println("Unable to read input or navigating to another screen...exiting application");
            System.exit(0);
        }
    }
}
