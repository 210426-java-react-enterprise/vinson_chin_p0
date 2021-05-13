package com.revature.vinson_chin_p0.screens;

import com.revature.vinson_chin_p0.exceptions.InvalidRequestException;
import com.revature.vinson_chin_p0.exceptions.ResourcePersistenceException;
import com.revature.vinson_chin_p0.models.Account;
import com.revature.vinson_chin_p0.models.AppUser;
import com.revature.vinson_chin_p0.services.AccountService;
import com.revature.vinson_chin_p0.util.ScreenRouter;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * New account screen used for creating a new account for current user
 * @author Vinson Chin
 *
 */
public class NewAccountScreen extends Screen{

    BufferedReader consoleReader;
    ScreenRouter router;
    AccountService accountService;

    public NewAccountScreen(BufferedReader consoleReader, ScreenRouter router, AccountService accountService) {
        super("NewAccountScreen", "/newAccount");
        this.consoleReader = consoleReader;
        this.router = router;
        this.accountService = accountService;
    }

    /**
     * Render used with passing in current user
     * Used on this screen
     * Allows the creation of a new account and navigation to other screens
     *
     * @param currentUser
     */
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
        } catch (InvalidRequestException e) {
            System.err.println(e + "\n");
            this.render(currentUser);
        } catch (ResourcePersistenceException e) {
            System.err.println(e + "\n");
            this.render(currentUser);
        } catch (IOException e) {
            System.err.println("Unable to read input or navigating to another screen...exiting application");
            System.exit(0);
        }
    }

    /**
     * Render used without passing anything
     * Not used on this screen
     *
     */
    @Override
    public void render() {}

    /**
     * Render used with passing in current user and current account
     * Not used on this screen
     *
     * @param currentUser
     * @param currentAccount
     */
    @Override
    public void render(AppUser currentUser, Account currentAccount) {}
}
