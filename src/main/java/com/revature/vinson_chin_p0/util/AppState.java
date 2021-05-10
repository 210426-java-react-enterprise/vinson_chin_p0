package com.revature.vinson_chin_p0.util;

import com.revature.vinson_chin_p0.daos.UserDAO;
import com.revature.vinson_chin_p0.screens.*;
import com.revature.vinson_chin_p0.services.UserService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AppState {

    private BufferedReader consoleReader;
    private ScreenRouter router;
    private boolean appRunning;

    public AppState() {
        System.out.println("Initializing application...");

        appRunning = true;
        consoleReader = new BufferedReader(new InputStreamReader(System.in));

        final UserDAO userDao = new UserDAO();
        final UserService userService = new UserService(userDao);

        router = new ScreenRouter();
        router.addScreen(new WelcomeScreen(consoleReader, router))
                .addScreen(new LoginScreen(consoleReader, router))
                .addScreen(new RegisterScreen(consoleReader, router, userService))
                .addScreen(new Dashboard(consoleReader,router))
                .addScreen(new UserInformation(consoleReader, router, userService));


        System.out.println("Application initialized!");
    }

    public ScreenRouter getRouter() {
        return router;
    }

    public boolean isAppRunning() {
        return appRunning;
    }

    public void setAppRunning(boolean appRunning) {
        this.appRunning = appRunning;
    }

}
