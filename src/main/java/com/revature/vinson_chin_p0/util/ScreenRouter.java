package com.revature.vinson_chin_p0.util;

import com.revature.vinson_chin_p0.models.Account;
import com.revature.vinson_chin_p0.models.AppUser;
import com.revature.vinson_chin_p0.screens.Screen;

import java.io.IOException;

public class ScreenRouter {

    private LinkedList<Screen> screens = new LinkedList<>();

    public ScreenRouter addScreen(Screen screen) {
        screens.add(screen);
        return this;
    }

    public void navigate(String route) {
        for (int i = 0; i < screens.size(); i++) {
            Screen screen = screens.get(i);
            if (screen.getRoute().equals(route)) {
                screen.render();
            }
        }
    }

    public void navigate(String route, AppUser currentUser) {
        for (int i = 0; i < screens.size(); i++) {
            Screen screen = screens.get(i);
            if (screen.getRoute().equals(route)) {
                screen.render(currentUser);
            }
        }
    }

    public void navigate(String route, AppUser currentUser, Account currentAccount) throws IOException {
        for (int i = 0; i < screens.size(); i++) {
            Screen screen = screens.get(i);
            if (screen.getRoute().equals(route)) {
                screen.render(currentUser, currentAccount);
            }
        }
    }
}
