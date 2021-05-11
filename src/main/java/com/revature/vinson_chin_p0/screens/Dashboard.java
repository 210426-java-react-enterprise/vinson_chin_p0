package com.revature.vinson_chin_p0.screens;

import com.revature.vinson_chin_p0.models.Account;
import com.revature.vinson_chin_p0.models.AppUser;
import com.revature.vinson_chin_p0.util.ScreenRouter;

import java.io.BufferedReader;

public class Dashboard extends Screen {

    private BufferedReader consoleReader;
    private ScreenRouter router;

    public Dashboard(BufferedReader consoleReader, ScreenRouter router) {
        super("Dashboard", "/dashboard");
        this.consoleReader = consoleReader;
        this.router = router;
    }

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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render() {}

    @Override
    public void render(AppUser currentUser, Account currentAccount) {}
}
