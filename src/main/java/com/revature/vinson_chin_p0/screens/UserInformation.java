package com.revature.vinson_chin_p0.screens;

import com.revature.vinson_chin_p0.models.AppUser;
import com.revature.vinson_chin_p0.services.UserService;
import com.revature.vinson_chin_p0.util.ScreenRouter;

import java.io.BufferedReader;
import java.util.regex.Pattern;

import static com.revature.vinson_chin_p0.Driver.app;

public class UserInformation extends Screen {

    private UserService userService;
    private BufferedReader consoleReader;
    private ScreenRouter router;

    public UserInformation(BufferedReader consoleReader, ScreenRouter router, UserService userService) {
        super("UserInformation", "/information");
        this.consoleReader = consoleReader;
        this.router = router;
        this.userService = userService;
    }

    @Override
    public void render(AppUser currentUser) {

        System.out.println("\nUser Information");
        System.out.println("Username: " + currentUser.getUsername());
        System.out.println("Password: " + currentUser.getPassword());
        System.out.println("Email: %s" + currentUser.getEmail());
        System.out.println("First Name: " + currentUser.getFirstName());
        System.out.println("Last Name: " + currentUser.getLastName());
        System.out.println("Date of Birth: " + currentUser.getDob());
        System.out.println("Phone Number: " + currentUser.getPhone());
        System.out.println("\nWhat would you like to change?");
        System.out.println("1) Username");
        System.out.println("2) Password");
        System.out.println("3) Email");
        System.out.println("4) First Name");
        System.out.println("5) Last Name");
        System.out.println("6) Date of Birth");
        System.out.println("7) Phone Number");
        System.out.println("8) Save and Return to Dashboard");

        try {
            System.out.print("> ");
            String userSelection = consoleReader.readLine();

            switch (userSelection) {
                case "1":
                    System.out.print("New Username: ");
                    currentUser.setUsername(consoleReader.readLine());
                    this.render(currentUser);
                    break;
                case "2":
                    System.out.print("New Password: ");
                    currentUser.setPassword(consoleReader.readLine());
                    this.render(currentUser);
                    break;
                case "3":
                    System.out.print("New Email: ");
                    String email = consoleReader.readLine();
                    while (Pattern.matches("[a-zA-Z].*@[a-zA-Z]+\\.[a-zA-Z]+", email) == false) {
                        System.out.println("Not a valid email");
                        System.out.print("Re-enter email: ");
                        email = consoleReader.readLine();
                    };
                    currentUser.setEmail(email);
                    this.render(currentUser);
                    break;
                case "4":
                    System.out.print("New First Name: ");
                    currentUser.setFirstName(consoleReader.readLine());
                    this.render(currentUser);
                    break;
                case "5":
                    System.out.print("New Last Name: ");
                    currentUser.setLastName(consoleReader.readLine());
                    this.render(currentUser);
                    break;
                case "6":
                    System.out.print("New Date of Birth(YYYY-MM-DD): ");
                    String dob = consoleReader.readLine();
                    while (Pattern.matches("\\d{4}-\\d\\d-\\d\\d", dob) == false) {
                        System.out.println("Not a valid date");
                        System.out.print("Re-enter date: ");
                        dob = consoleReader.readLine();
                    }
                    currentUser.setDob(dob);
                    this.render(currentUser);
                    break;
                case "7":
                    System.out.print("New Phone Number(Just Numbers): ");
                    currentUser.setPhone(Long.parseLong(consoleReader.readLine()));
                    this.render(currentUser);
                    break;
                case "8":
                    System.out.println("Returning to Dashboard\n");
                    userService.update(currentUser);
                    router.navigate("/dashboard", currentUser);
                    break;
                default:
                    System.out.println("Invalid selection!\n");
                    this.render(currentUser);
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
