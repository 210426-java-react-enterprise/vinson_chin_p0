package com.revature.vinson_chin_p0.screens;

import com.revature.vinson_chin_p0.daos.AccountDAO;
import com.revature.vinson_chin_p0.models.Account;
import com.revature.vinson_chin_p0.models.AppUser;
import com.revature.vinson_chin_p0.services.AccountService;
import com.revature.vinson_chin_p0.util.ScreenRouter;

import java.io.BufferedReader;

public class NewAccountScreen extends Screen{

    BufferedReader consoleReader;
    ScreenRouter router;
    AccountService accountService;
    AccountDAO accountDAO = new AccountDAO();

    public NewAccountScreen(BufferedReader consoleReader, ScreenRouter router, AccountService accountService) {
        super("NewAccountScreen", "/newAccount");
        this.consoleReader = consoleReader;
        this.router = router;
        this.accountService = accountService;
    }

    @Override
    public void render(AppUser currentUser) {

        int userId = currentUser.getId();
        double balance = 0.0;
        String accountType = "";
        String name;

        try {
            System.out.println("New Account Creation");
            System.out.println("What type of account is this?");
            System.out.println("1) Checking");
            System.out.println("2) Saving");
            System.out.println("3) Trust");
            System.out.println("4) Retirement");
            System.out.println("5) Brokerage");
            System.out.print("> ");
            String userSelection = consoleReader.readLine();

            switch (userSelection) {
                case "1":
                    accountType = "Checking";
                    break;
                case "2":
                    accountType = "Saving";
                    break;
                case "3":
                    accountType = "Trust";
                    break;
                case "4":
                    accountType = "Retirement";
                    break;
                case "5":
                    accountType = "Brokerage";
                    break;
                default:
                    System.out.println("Invalid selection");
                    this.render(currentUser);
            }

            System.out.println("What would you like to name this account? ");
            name = consoleReader.readLine();

            Account newAccount = new Account(userId, balance, accountType, name);
            accountService.create(newAccount);
            System.out.println("New Account Created\n");
            router.navigate("/accounts", currentUser);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render() {}

    @Override
    public void render(AppUser currentUser, Account currentAccount) {}
}
