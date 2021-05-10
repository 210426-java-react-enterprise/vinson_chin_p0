package com.revature.vinson_chin_p0.screens;

import com.revature.vinson_chin_p0.util.ScreenRouter;

import java.io.BufferedReader;

public class Dashboard extends Screen {

    private BufferedReader consoleReader;
    private ScreenRouter router;

    public Dashboard(BufferedReader consoleReader, ScreenRouter router) {
        super("LoginScreen", "/login");
        this.consoleReader = consoleReader;
        this.router = router;
    }

    @Override
    public void render() {
        System.out.println("DASHBOARD!");
    }
}
