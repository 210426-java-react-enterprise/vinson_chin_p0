package com.revature.vinson_chin_p0.screens;

import com.revature.vinson_chin_p0.models.Account;
import com.revature.vinson_chin_p0.models.AppUser;

import java.io.IOException;

/**
 * Abstract class screen for rest of screens to extend
 * @author Vinson Chin
 *
 */
public abstract class Screen {

    protected String name;
    protected String route;

    public Screen(String name, String route) {
        this.name = name;
        this.route = route;
    }

    public String getName() {
        return name;
    }

    public String getRoute() {
        return route;
    }

    /**
     * Render methods used for other screens
     *
     */
    public abstract void render(AppUser currentUser);

    public abstract void render();

    public abstract void render(AppUser currentUser, Account currentAccount) throws IOException;
}
