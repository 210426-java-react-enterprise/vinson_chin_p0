package com.revature.project0.screens;

import com.revature.project0.daos.UserDAO;
import com.revature.project0.models.AppUser;

import java.io.BufferedReader;

public class RegisterScreen extends Screen {

    private static UserDAO userDao = new UserDAO(); // ok for now, but actually gross -- fix later
    private static BufferedReader consoleReader;

    public RegisterScreen(BufferedReader consoleReader) {
        this.consoleReader = consoleReader;
    }

    public static void render() {

        String firstName;
        String lastName;
        String email;
        String username;
        String password;
        int age;
        String reg;

        // ok but a little verbose
//        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
//        BufferedReader consoleReader = new BufferedReader(inputStreamReader);

        try {
            // risky code that might through an exception

            System.out.println("Register for a new account!");
            System.out.println("+-------------------------+");

            System.out.print("First name: ");
            firstName = consoleReader.readLine(); // throws Exception here

            System.out.print("Last name: ");
            lastName = consoleReader.readLine();

            System.out.print("Email: ");
            email = consoleReader.readLine();

            System.out.print("Username: ");
            username = consoleReader.readLine();

            System.out.print("Password: ");
            password = consoleReader.readLine();

            System.out.print("Age: ");
            age = Integer.parseInt(consoleReader.readLine());

            AppUser newUser = new AppUser(username, password, email, firstName, lastName, age);
            userDao.saveUserToFile(newUser);

            System.out.print("Would you like to log in now? (y to continue) ");
            reg = consoleReader.readLine();
            if (reg.equals("y")) {
                LoginScreen loginScreen = new LoginScreen(consoleReader);
                LoginScreen.render();
            }

        } catch (NumberFormatException nfe) {
            // do something about these!
            System.err.println("You provided an incorrect value for your age! Please try again!");
            render(); // this breaks some stuff! we will need to fix this
        } catch (Exception e) {
            e.printStackTrace(); // include this line while developing/debugging the app!
            // should be logged to a file in a production environment
        }



    }

}
