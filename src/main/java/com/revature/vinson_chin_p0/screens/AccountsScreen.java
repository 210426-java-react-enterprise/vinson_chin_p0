package com.revature.vinson_chin_p0.screens;

import com.revature.vinson_chin_p0.daos.AccountDAO;
import com.revature.vinson_chin_p0.models.Account;
import com.revature.vinson_chin_p0.models.AppUser;
import com.revature.vinson_chin_p0.util.ScreenRouter;

import java.io.BufferedReader;
import java.io.IOException;

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

        try {

            Account[] accounts = accountDao.findAccounts(currentUser.getId());
            System.out.println("Accounts");
            int i = 0;
            if (accounts[0] != null) {
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
                System.out.println("Navigating to " + accounts[userSelection - 1].getName() + " account");
                Account currentAccount = accounts[userSelection - 1];
                router.navigate("/account", currentUser, currentAccount);
            } else {
                System.out.println("Invalid selection\n");
                this.render(currentUser);
            }
        } catch (NumberFormatException e) {
            System.err.println("Your selection should be a number");
            this.render(currentUser);
        } catch (IOException e) {
            System.err.println("Unable to read input or navigating to another screen...exiting application");
            System.exit(0);
        }
    }

    @Override
    public void render() {

    }

    @Override
    public void render(AppUser currentUser, Account currentAccount) {}
}
