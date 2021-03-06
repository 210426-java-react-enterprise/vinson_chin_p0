package com.revature.vinson_chin_p0.util;

import com.revature.vinson_chin_p0.daos.AccountDAO;
import com.revature.vinson_chin_p0.daos.TransactionDAO;
import com.revature.vinson_chin_p0.daos.UserDAO;
import com.revature.vinson_chin_p0.screens.*;
import com.revature.vinson_chin_p0.services.AccountService;
import com.revature.vinson_chin_p0.services.TransactionService;
import com.revature.vinson_chin_p0.services.UserService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Class used to determine if application is running and where to navigate to.
 * @author Vinson Chin
 *
 */
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
        final AccountDAO accountDao = new AccountDAO();
        final AccountService accountService = new AccountService((accountDao));
        final TransactionDAO transactionDao = new TransactionDAO();
        final TransactionService transactionService = new TransactionService(transactionDao);

        router = new ScreenRouter();
        router.addScreen(new WelcomeScreen(consoleReader, router))
              .addScreen(new LoginScreen(consoleReader, router))
              .addScreen(new RegisterScreen(consoleReader, router, userService))
              .addScreen(new Dashboard(consoleReader,router))
              .addScreen(new UserInformationScreen(consoleReader, router, userService))
              .addScreen(new AccountsScreen(consoleReader, router))
              .addScreen(new NewAccountScreen(consoleReader, router, accountService))
              .addScreen(new CurrentAccountScreen(consoleReader, router, accountService, accountDao, transactionService))
              .addScreen(new AccountInformationScreen(consoleReader, router, accountService))
              .addScreen(new TransactionsScreen(consoleReader, router));

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
