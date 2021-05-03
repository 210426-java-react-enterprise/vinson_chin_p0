package com.revature.project0.screens;

import com.revature.project0.daos.UserDAO;
import com.revature.project0.models.AppUser;

import java.io.BufferedReader;

public class LoginScreen extends Screen {

    private static UserDAO userDao = new UserDAO();
    private static BufferedReader consoleReader;

    public LoginScreen(BufferedReader consoleReader) {
        this.consoleReader = consoleReader;
    }
    public static void render() {

        try {
            String username;
            String password;

            System.out.println("Log into your account!");
            System.out.println("+---------------------+");

            System.out.print("Username: ");
            username = consoleReader.readLine();

            System.out.print("Password: ");
            password = consoleReader.readLine();

            if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
                AppUser authenticatedUser = userDao.findUserByUsernameAndPassword(username, password);
                while (authenticatedUser == null) {
                    System.out.println("Login Failed!");
                    System.out.print("Re-enter Username: ");
                    username = consoleReader.readLine();
                    System.out.print("Re-enter Password: ");
                    password = consoleReader.readLine();
                    authenticatedUser = userDao.findUserByUsernameAndPassword(username, password);
                }
                System.out.println("Login Successful!");
            } else {
                System.out.println("It looks like you didn't provide any credentials!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
