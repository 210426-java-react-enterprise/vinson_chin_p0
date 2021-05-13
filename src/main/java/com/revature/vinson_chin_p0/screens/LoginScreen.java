package com.revature.vinson_chin_p0.screens;

import com.revature.vinson_chin_p0.daos.UserDAO;
import com.revature.vinson_chin_p0.models.Account;
import com.revature.vinson_chin_p0.models.AppUser;
import com.revature.vinson_chin_p0.util.ScreenRouter;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Login screen used for logging into user
 * @author Vinson Chin
 *
 */
public class LoginScreen extends Screen {

    private UserDAO userDao = new UserDAO();
    private BufferedReader consoleReader;
    private ScreenRouter router;

    public LoginScreen(BufferedReader consoleReader, ScreenRouter router) {
        super("LoginScreen", "/login");
        this.consoleReader = consoleReader;
        this.router = router;
    }

    /**
     * Render used without passing anything
     * Used on this screen
     * Allows users to login and navigation to other screens
     *
     */
    public void render() {

        try {
            String username;
            String password;
            int attempts = 1;

            System.out.println("Login Screen");

            System.out.print("Username: ");
            username = consoleReader.readLine();

            System.out.print("Password: ");
            password = consoleReader.readLine();

            if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
                AppUser authenticatedUser = userDao.findUserByUsernameAndPassword(username, password);
                String retry;
                while (authenticatedUser == null) {
                    System.out.println("Login failed!");
                    if (attempts == 5) {
                        System.out.println("All attempts used. Returning to welcome screen.\n");
                        break;
                    }
                    System.out.print("Enter 'y' to try again: ");
                    retry = consoleReader.readLine();

                    if (retry == "y") {
                        System.out.print("Re-enter Username: ");
                        username = consoleReader.readLine();

                        System.out.print("Re-enter Password: ");
                        password = consoleReader.readLine();

                        authenticatedUser = userDao.findUserByUsernameAndPassword(username, password);
                    } else {
                        System.out.println("Returning to welcome screen\n");
                        break;
                    }

                }
                if (authenticatedUser != null) {
                    System.out.println("Login successful!\n");
                    router.navigate("/dashboard", authenticatedUser);
                }
            } else {
                System.out.println("No credentials provided");
                System.out.println("Returning to welcome screen\n");
            }

        } catch (IOException e) {
            System.err.println("Unable to read input or navigating to another screen...exiting application");
            System.exit(0);
        }
    }

    /**
     * Render used with passing in current user and current account
     * Not used on this screen
     *
     * @param currentUser
     * @param currentAccount
     */
    @Override
    public void render(AppUser currentUser, Account currentAccount) {}

    /**
     * Render used with passing in current user
     * Not used on this screen
     *
     * @param currentUser
     */
    @Override
    public void render(AppUser currentUser) {}
}
