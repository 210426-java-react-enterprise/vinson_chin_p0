package com.revature.vinson_chin_p0.screens;

import com.revature.vinson_chin_p0.models.Account;
import com.revature.vinson_chin_p0.models.AppUser;
import com.revature.vinson_chin_p0.util.ScreenRouter;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Dashboard used for navigating to accounts or user information
 * @author Vinson Chin
 *
 */
public class Dashboard extends Screen {

    private BufferedReader consoleReader;
    private ScreenRouter router;

    public Dashboard(BufferedReader consoleReader, ScreenRouter router) {
        super("Dashboard", "/dashboard");
        this.consoleReader = consoleReader;
        this.router = router;
    }

    /**
     * Render used with passing in current user
     * Used on this screen
     * Allows navigation to other screens and logging out
     *
     * @param currentUser
     */
    @Override
    public void render(AppUser currentUser) {

        System.out.println("User Dashboard");
        System.out.println("What would you like to do?");
        System.out.println("1) View Accounts");
        System.out.println("2) User Information");
        System.out.println("3) Logout");

        try {
            System.out.print("> ");
            String userSelection = consoleReader.readLine();

            switch (userSelection) {
                case "1":
                    System.out.println("Navigating to accounts screen\n");
                    router.navigate("/accounts", currentUser);
                    break;
                case "2":
                    System.out.println("Navigating to user information screen");
                    router.navigate("/information", currentUser);
                    break;
                case "3":
                    System.out.println("Logging out\n");
                    router.navigate("/welcome");
                    break;
                default:
                    System.out.println("Invalid selection!\n");
                    this.render(currentUser);
            }

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
