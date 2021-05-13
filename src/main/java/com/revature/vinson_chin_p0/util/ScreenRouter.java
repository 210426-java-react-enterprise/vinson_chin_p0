package com.revature.vinson_chin_p0.util;

import com.revature.vinson_chin_p0.models.Account;
import com.revature.vinson_chin_p0.models.AppUser;
import com.revature.vinson_chin_p0.screens.Screen;

import java.io.IOException;

/**
 * Class for navigating between screens.
 * @author Vinson Chin
 *
 */
public class ScreenRouter {

    private LinkedList<Screen> screens = new LinkedList<>();

    /**
     * Adds a new screen to navigate to.
     *
     * @param screen
     * @return
     */
    public ScreenRouter addScreen(Screen screen) {
        screens.add(screen);
        return this;
    }

    /**
     * Navigates to route and calls render method on screen.
     *
     * @param route
     */
    public void navigate(String route) {
        for (int i = 0; i < screens.size(); i++) {
            Screen screen = screens.get(i);
            if (screen.getRoute().equals(route)) {
                screen.render();
            }
        }
    }

    /**
     * Navigates to route and calls render method and passes a AppUser object.
     *
     * @param route
     * @param currentUser
     */
    public void navigate(String route, AppUser currentUser) {
        for (int i = 0; i < screens.size(); i++) {
            Screen screen = screens.get(i);
            if (screen.getRoute().equals(route)) {
                screen.render(currentUser);
            }
        }
    }

    /**
     * Navigates to route and calls render method and passes an AppUser object and an Account object.
     *
     * @param route
     * @param currentUser
     * @param currentAccount
     */
    public void navigate(String route, AppUser currentUser, Account currentAccount) throws IOException {
        for (int i = 0; i < screens.size(); i++) {
            Screen screen = screens.get(i);
            if (screen.getRoute().equals(route)) {
                screen.render(currentUser, currentAccount);
            }
        }
    }
}
