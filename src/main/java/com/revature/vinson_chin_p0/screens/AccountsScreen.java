package com.revature.vinson_chin_p0.screens;

import com.revature.vinson_chin_p0.daos.AccountDAO;
import com.revature.vinson_chin_p0.models.Account;
import com.revature.vinson_chin_p0.models.AppUser;
import com.revature.vinson_chin_p0.util.ScreenRouter;

import java.io.BufferedReader;

public class AccountsScreen extends Screen {

    private AccountDAO accountDao = new AccountDAO();
    private BufferedReader consoleReader;
    private ScreenRouter router;

    public AccountsScreen(BufferedReader consoleReader, ScreenRouter router) {
        super("AccountsScreen", "/accounts");
        this.consoleReader = consoleReader;
        this.router = router;
    }

    @Override
    public void render(AppUser currentUser) {

        int i = -1;

        try {

            Account[] accounts = accountDao.findAccounts(currentUser.getId());
            System.out.println("Accounts");
            if (accounts[0] != null) {
                i++;
                while (accounts[i] != null) {
                    System.out.println((i + 1) + ") " + accounts[i].getName());
                    i++;
                }
            }
            System.out.println((i + 1) + ") Create a new account");
            System.out.println((i + 2) + ") Return to Dashboard");
            System.out.println("Which account would you like to view?");
            System.out.print("> ");
            int userSelection = Integer.parseInt(consoleReader.readLine());

            if (userSelection == (i + 2)) {
                System.out.println("Returning to Dashboard\n");
                router.navigate("/dashboard", currentUser);
            } else if (userSelection == (i + 1)) {
                System.out.println("Navigating to new account recreation\n");
                router.navigate("/newAccount", currentUser);
            } else if (userSelection <= i) {
                System.out.println("Navigating to " + accounts[userSelection - 1].getName() + " account\n");
                Account currentAccount = accounts[userSelection - 1];
                router.navigate("/account", currentUser, currentAccount);
            } else {
                System.out.println("Invalid selection\n");
                this.render(currentUser);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render() {

    }

    @Override
    public void render(AppUser currentUser, Account currentAccount) {}
}
