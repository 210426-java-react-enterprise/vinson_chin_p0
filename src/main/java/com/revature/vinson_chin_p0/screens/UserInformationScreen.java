package com.revature.vinson_chin_p0.screens;

import com.revature.vinson_chin_p0.exceptions.InvalidRequestException;
import com.revature.vinson_chin_p0.exceptions.ResourcePersistenceException;
import com.revature.vinson_chin_p0.models.Account;
import com.revature.vinson_chin_p0.models.AppUser;
import com.revature.vinson_chin_p0.services.UserService;
import com.revature.vinson_chin_p0.util.ScreenRouter;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * User Information screen used for viewing and changing user information
 * @author Vinson Chin
 *
 */
public class UserInformationScreen extends Screen {

    private UserService userService;
    private BufferedReader consoleReader;
    private ScreenRouter router;

    public UserInformationScreen(BufferedReader consoleReader, ScreenRouter router, UserService userService) {
        super("UserInformationScreen", "/information");
        this.consoleReader = consoleReader;
        this.router = router;
        this.userService = userService;
    }

    /**
     * Render used with passing in current user
     * Used on this screen
     * Allows users to change information and navigation to other screens
     *
     * @param currentUser
     */
    @Override
    public void render(AppUser currentUser) {

        String username = currentUser.getUsername();
        String password = currentUser.getPassword();
        String email = currentUser.getEmail();
        String firstName = currentUser.getFirstName();
        String lastName = currentUser.getLastName();
        String dob = currentUser.getDob();
        long phone = currentUser.getPhone();

        try {

            System.out.println("\nUser Information");
            System.out.println("1) Username: " + currentUser.getUsername());
            System.out.println("2) Password: " + currentUser.getPassword());
            System.out.println("3) Email: " + currentUser.getEmail());
            System.out.println("4) First Name: " + currentUser.getFirstName());
            System.out.println("5) Last Name: " + currentUser.getLastName());
            System.out.println("6) Date of Birth: " + currentUser.getDob());
            System.out.println("7) Phone Number: " + currentUser.getPhone());
            System.out.println("8) Save and Return to Dashboard");
            System.out.println("\nWhat would you like to change?");
            System.out.print("> ");
            String userSelection = consoleReader.readLine();

            switch (userSelection) {
                case "1":
                    System.out.print("New Username: ");
                    currentUser.setUsername(consoleReader.readLine());
                    userService.update(currentUser);
                    this.render(currentUser);
                    break;
                case "2":
                    System.out.print("New Password: ");
                    currentUser.setPassword(consoleReader.readLine());
                    userService.update(currentUser);
                    this.render(currentUser);
                    break;
                case "3":
                    System.out.print("New Email: ");
                    currentUser.setEmail(consoleReader.readLine());
                    userService.update(currentUser);
                    this.render(currentUser);
                    break;
                case "4":
                    System.out.print("New First Name: ");
                    currentUser.setFirstName(consoleReader.readLine());
                    userService.update(currentUser);
                    this.render(currentUser);
                    break;
                case "5":
                    System.out.print("New Last Name: ");
                    currentUser.setLastName(consoleReader.readLine());
                    userService.update(currentUser);
                    this.render(currentUser);
                    break;
                case "6":
                    System.out.print("New Date of Birth(YYYY-MM-DD): ");
                    currentUser.setDob(consoleReader.readLine());
                    userService.update(currentUser);
                    this.render(currentUser);
                    break;
                case "7":
                    System.out.print("New Phone Number(Just Numbers): ");
                    currentUser.setPhone(Long.parseLong(consoleReader.readLine()));
                    userService.update(currentUser);
                    this.render(currentUser);
                    break;
                case "8":
                    System.out.println("Returning to Dashboard\n");
                    router.navigate("/dashboard", currentUser);
                    break;
                default:
                    System.out.println("Invalid selection");
                    this.render(currentUser);
            }
        } catch (InvalidRequestException e) {
            System.err.println(e);
            currentUser.setUsername(username);
            currentUser.setPassword(password);
            currentUser.setEmail(email);
            currentUser.setFirstName(firstName);
            currentUser.setLastName(lastName);
            currentUser.setDob(dob);
            currentUser.setPhone(phone);
            this.render(currentUser);
        } catch (ResourcePersistenceException e) {
            System.err.println(e);
            currentUser.setUsername(username);
            currentUser.setEmail(email);
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
