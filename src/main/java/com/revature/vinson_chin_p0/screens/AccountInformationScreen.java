package com.revature.vinson_chin_p0.screens;

import com.revature.vinson_chin_p0.daos.AccountDAO;
import com.revature.vinson_chin_p0.exceptions.InvalidRequestException;
import com.revature.vinson_chin_p0.exceptions.ResourcePersistenceException;
import com.revature.vinson_chin_p0.models.Account;
import com.revature.vinson_chin_p0.models.AppUser;
import com.revature.vinson_chin_p0.services.AccountService;
import com.revature.vinson_chin_p0.util.ScreenRouter;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Account information screen used for changing account information
 * @author Vinson Chin
 *
 */
public class AccountInformationScreen extends Screen{

    BufferedReader consoleReader;
    ScreenRouter router;
    AccountService accountService;

    public AccountInformationScreen(BufferedReader consoleReader, ScreenRouter router, AccountService accountService) {
        super("AccountInformationScreen", "/accountInformation");
        this.consoleReader = consoleReader;
        this.router = router;
        this.accountService = accountService;
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
     * Allows users to view and change account information
     *
     * @param currentUser
     * @param currentAccount
     */
    @Override
    public void render(AppUser currentUser, Account currentAccount) {

        String name = currentAccount.getName();

        try {

            System.out.println("What would you like to change?");
            System.out.println("1) Account Type: " + currentAccount.getAccountType());
            System.out.println("2) Name: " + currentAccount.getName());
            System.out.println("3) Return to Account");
            String userSelection = consoleReader.readLine();

            switch (userSelection) {
                case "1":
                    System.out.println("What type do you want this account to be?");
                    System.out.println("1) Checking");
                    System.out.println("2) Saving");
                    System.out.println("3) Trust");
                    System.out.println("4) Retirement");
                    System.out.println("5) Brokerage");
                    System.out.print("> ");
                    String selection = consoleReader.readLine();;

                    switch (selection) {
                        case "1":
                            currentAccount.setAccountType("Checking");
                            break;
                        case "2":
                            currentAccount.setAccountType("Saving");
                            break;
                        case "3":
                            currentAccount.setAccountType("Trust");
                            break;
                        case "4":
                            currentAccount.setAccountType("Retirement");
                            break;
                        case "5":
                            currentAccount.setAccountType("Brokerage");
                            break;
                        default:
                            System.out.println("Invalid selection");
                            this.render(currentUser, currentAccount);
                    }
                    accountService.update(currentAccount);
                    System.out.println("Account Type changed");
                    this.render(currentUser, currentAccount);
                    break;
                case "2":
                    System.out.print("What would you like to change the name to? ");
                    String newName = consoleReader.readLine();
                    currentAccount.setName(newName);
                    accountService.update(currentAccount);
                    System.out.println("Name changed\n");
                    this.render(currentUser, currentAccount);
                    break;
                case "3":
                    System.out.println("Returning to account");
                    router.navigate("/account", currentUser, currentAccount);
                    break;
                default:
                    System.out.println("Invalid Selection\n");
                    this.render(currentUser, currentAccount);
            }
        } catch (InvalidRequestException e) {
            System.err.println(e + "\n");
            currentAccount.setName(name);
            this.render(currentUser, currentAccount);
        } catch (ResourcePersistenceException e) {
            System.err.println(e + "\n");
            currentAccount.setName(name);
            this.render(currentUser, currentAccount);
        } catch (IOException e) {
            System.err.println("Unable to read input or navigating to another screen...exiting application");
            System.exit(0);
        }

    }
}
