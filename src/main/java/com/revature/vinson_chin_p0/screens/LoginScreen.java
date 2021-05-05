package com.revature.vinson_chin_p0.screens;

import com.revature.vinson_chin_p0.daos.UserDAO;
import com.revature.vinson_chin_p0.models.AppUser;

import java.io.BufferedReader;

public class LoginScreen extends Screen {

    private UserDAO userDao = new UserDAO();
    private BufferedReader consoleReader;

    public LoginScreen(BufferedReader consoleReader) {
        super("LoginScreen", "/login");
        this.consoleReader = consoleReader;
    }

    public void render() {

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
                if (authenticatedUser != null) {
                    System.out.println("Login successful!");
                } else {
                    System.out.println("Login failed!");
                }
            } else {
                System.out.println("It looks like you didn't provide any credentials!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
