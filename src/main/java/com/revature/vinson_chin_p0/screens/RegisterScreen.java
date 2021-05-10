package com.revature.vinson_chin_p0.screens;

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
        this.userService = userService;
    }

    public void render(AppUser currentUser) {

        String firstName;
        String lastName;
        String email;
        String username;
        String password;
        String dob;
        long phone;

        try {

            System.out.println("New Account Registration");
            System.out.println("The following information is required");

            System.out.print("First name: ");
            firstName = consoleReader.readLine();

            System.out.print("Last name: ");
            lastName = consoleReader.readLine();

            System.out.print("Email: ");
            email = consoleReader.readLine();
            while (Pattern.matches("[a-zA-Z].*@[a-zA-Z]+[.][a-zA-Z]+", email) == false) {
                System.out.println("Not a valid email");
                System.out.print("Re-enter email: ");
                email = consoleReader.readLine();
            };

            System.out.print("Username: ");
            username = consoleReader.readLine();

            System.out.print("Password: ");
            password = consoleReader.readLine();

            System.out.print("Date of Birth(YYYY-MM-DD): ");
            dob = consoleReader.readLine();
            while (Pattern.matches("\\d{4}-\\d\\d-\\d\\d", dob) == false) {
                System.out.println("Not a valid date");
                System.out.print("Re-enter date: ");
                dob = consoleReader.readLine();
            }

            System.out.print("Phone Number (Just Numbers): ");
            phone = Long.parseLong(consoleReader.readLine());

            AppUser newUser = new AppUser(username, password, email, firstName, lastName, dob, phone);
            userService.register(newUser);
            System.out.println("Registration Successful!\n");

        } catch (NumberFormatException nfe) {
            System.err.println("Not a Valid Phone Number! Please try again!\n");
            this.render(currentUser);
        } catch (InvalidRequestException | ResourcePersistenceException e) {
            System.err.println(e + "\n");
            router.navigate("/welcome");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
