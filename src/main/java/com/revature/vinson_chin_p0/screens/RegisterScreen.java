package com.revature.vinson_chin_p0.screens;

import com.revature.vinson_chin_p0.daos.UserDAO;
import com.revature.vinson_chin_p0.exceptions.InvalidRequestException;
import com.revature.vinson_chin_p0.exceptions.ResourcePersistenceException;
import com.revature.vinson_chin_p0.models.AppUser;
import com.revature.vinson_chin_p0.services.UserService;
import com.revature.vinson_chin_p0.util.ScreenRouter;

import java.io.BufferedReader;
import java.util.regex.Pattern;

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

    public void render() {

        String firstName;
        String lastName;
        String email;
        String username;
        String password;
        String dob;
        int phone;
        String address;
        String city;
        String country;
        int zipcode;


        try {
            // risky code that might through an exception

            System.out.println("New Account Registration");
            System.out.println("The following information is required");

            System.out.print("First name: ");
            firstName = consoleReader.readLine();

            System.out.print("Last name: ");
            lastName = consoleReader.readLine();

            System.out.print("Email: ");
            email = consoleReader.readLine();
            while (Pattern.matches("[a-zA-Z]\\w*@[a-zA-Z].[a-zA-Z]*", email) == false) {
                System.out.println("Not a valid email");
                System.out.print("Re-enter email: ");
                email = consoleReader.readLine();
            };

            System.out.print("Username: ");
            username = consoleReader.readLine();

            System.out.print("Password: ");
            password = consoleReader.readLine();

            System.out.print("Birthday(YYYY-MM-DD): ");
            dob = consoleReader.readLine();
            while (Pattern.matches("\\d{4}-\\d\\d-\\d\\d", dob) == false) {
                System.out.println("Not a valid date");
                System.out.print("Re-enter birthday: ");
                dob = consoleReader.readLine();
            }

            AppUser newUser = new AppUser(username, password, email, firstName, lastName, dob);
            userService.register(newUser);

        } catch (NumberFormatException nfe) {
            // do something about these!
            System.err.println("You provided an incorrect value for your age! Please try again!");
            this.render();
        } catch (InvalidRequestException | ResourcePersistenceException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }



    }

}
