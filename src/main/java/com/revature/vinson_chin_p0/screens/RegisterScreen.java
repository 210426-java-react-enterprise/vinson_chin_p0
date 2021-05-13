package com.revature.vinson_chin_p0.screens;

import com.revature.vinson_chin_p0.exceptions.InvalidRequestException;
import com.revature.vinson_chin_p0.exceptions.ResourcePersistenceException;
import com.revature.vinson_chin_p0.models.Account;
import com.revature.vinson_chin_p0.models.AppUser;
import com.revature.vinson_chin_p0.services.UserService;
import com.revature.vinson_chin_p0.util.ScreenRouter;

import java.io.BufferedReader;
import java.io.IOException;

import static com.revature.vinson_chin_p0.Driver.app;

/**
 * Register screen used for registering a user
 * @author Vinson Chin
 *
 */
public class RegisterScreen extends Screen {

    private UserService userService;
    private BufferedReader consoleReader;
    private ScreenRouter router;

    public RegisterScreen(BufferedReader consoleReader, ScreenRouter router, UserService userService) {
        super("RegisterScreen", "/register");
        this.consoleReader = consoleReader;
        this.userService = userService;
        this.router = router;
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
     * Used on this screen
     * Allows the creation of a new user
     *
     */
    public void render() {

        String firstName;
        String lastName;
        String email;
        String username;
        String password;
        String dob;
        long phone;

        try {

            System.out.println("New User Registration");
            System.out.println("The following information is required");

            System.out.print("First name: ");
            firstName = consoleReader.readLine();

            System.out.print("Last name: ");
            lastName = consoleReader.readLine();

            System.out.print("Email: ");
            email = consoleReader.readLine();

            System.out.print("Username: ");
            username = consoleReader.readLine();

            System.out.print("Password: ");
            password = consoleReader.readLine();

            System.out.print("Date of Birth(YYYY-MM-DD): ");
            dob = consoleReader.readLine();

            System.out.print("Phone Number (Just Numbers): ");
            phone = Long.parseLong(consoleReader.readLine());

            AppUser newUser = new AppUser(username, password, email, firstName, lastName, dob, phone);
            userService.register(newUser);
            System.out.println("Registration Successful!\n");

        } catch (NumberFormatException nfe) {
            System.err.println("Not a Valid Phone Number! Please try again!\n");
        } catch (InvalidRequestException | ResourcePersistenceException e) {
            System.err.println(e + "\n");
        } catch (IOException e) {
            System.err.println("Unable to read inputs");
            app().setAppRunning(false);
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

}
