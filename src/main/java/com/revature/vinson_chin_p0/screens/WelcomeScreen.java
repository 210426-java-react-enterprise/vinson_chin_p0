package com.revature.vinson_chin_p0.screens;

import com.revature.vinson_chin_p0.models.AppUser;
import com.revature.vinson_chin_p0.util.ScreenRouter;
import static com.revature.vinson_chin_p0.Driver.app;

import java.io.BufferedReader;

public class WelcomeScreen extends Screen {

    private BufferedReader consoleReader;
    private ScreenRouter router;

    public WelcomeScreen(BufferedReader consoleReader, ScreenRouter router) {
        super("WelcomeScreen", "/welcome");
        this.consoleReader = consoleReader;
        this.router = router;
    }

    @Override
    public void render(AppUser currentUser) {

        System.out.println("\nWelcome to your Banking Application\n");
        System.out.println("What would you like to do?");
        System.out.println("1) Login");
        System.out.println("2) Register");
        System.out.println("3) Exit application");

        try {
            System.out.print("> ");
            String userSelection = consoleReader.readLine();

            switch (userSelection) {
                case "1":
                    System.out.println("Navigating to login screen\n");
                    router.navigate("/login");
                    break;
                case "2":
                    System.out.println("Navigating to register screen\n");
                    router.navigate("/register");
                    break;
                case "3":
                    System.out.println("Exiting application!");
                    app().setAppRunning(false);
                    break;
                default:
                    System.out.println("Invalid selection!\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
