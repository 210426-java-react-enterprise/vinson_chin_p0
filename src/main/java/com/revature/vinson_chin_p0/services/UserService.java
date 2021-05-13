package com.revature.vinson_chin_p0.services;

import com.revature.vinson_chin_p0.daos.UserDAO;
import com.revature.vinson_chin_p0.exceptions.InvalidRequestException;
import com.revature.vinson_chin_p0.exceptions.ResourcePersistenceException;
import com.revature.vinson_chin_p0.models.AppUser;
import com.revature.vinson_chin_p0.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class UserService {

    private UserDAO userDao;

    public UserService(UserDAO userDao) {
        this.userDao = userDao;
    }

    public AppUser register(AppUser newUser) throws InvalidRequestException, ResourcePersistenceException{

        if (!isUserValid(newUser)) {
            throw new InvalidRequestException("Invalid new user data provided!");
        }

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            if (!userDao.isUsernameAvailable(conn, newUser.getUsername())) {
                throw new ResourcePersistenceException("The provided username is already taken!");
            }

            if (!userDao.isEmailAvailable(conn, newUser.getEmail())) {
                throw new ResourcePersistenceException("The provided email is already taken!");
            }

            return userDao.save(conn, newUser);

        } catch (SQLException throwables) {
            System.err.println("Connection or SQL statement problems...exiting application");
            System.exit(0);
        }

        return null;

    }

    public AppUser update(AppUser changedUser) throws InvalidRequestException, ResourcePersistenceException {

        if (!isUserValid(changedUser)) {
            throw new InvalidRequestException("Invalid new user data provided!");
        }

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            if (!userDao.isUpdatedUsernameAvailable(conn, changedUser.getUsername(), changedUser.getId())) {
                throw new ResourcePersistenceException("The provided username is already taken!");
            }

            if (!userDao.isUpdatedEmailAvailable(conn, changedUser.getEmail(), changedUser.getId())) {
                throw new ResourcePersistenceException("The provided email is already taken!");
            }

            return userDao.update(conn, changedUser);

        } catch (SQLException throwables) {
            System.err.println("Connection or SQL statement problems...exiting application");
            System.exit(0);
        }
        return null;

    }

    public boolean isUserValid(AppUser user) {
        if (user == null) return false;
        if (user.getUsername() == null || user.getUsername().trim().isEmpty() || user.getUsername().length() > 20) return false;
        if (user.getPassword() == null || user.getPassword().trim().isEmpty() || user.getPassword().length() > 255) return false;
        if (user.getEmail() == null || user.getEmail().trim().isEmpty() || user.getEmail().length() > 255 || !Pattern.matches("[a-zA-Z].*@[a-zA-Z]+[.][a-zA-Z]+", user.getEmail())) return false;
        if (user.getFirstName() == null || user.getFirstName().trim().isEmpty() || user.getFirstName().length() > 25) return false;
        if (user.getLastName() == null || user.getLastName().trim().isEmpty() || user.getLastName().length() > 25) return false;
        if (user.getDob() == null || user.getDob().trim().isEmpty() || user.getDob().length() > 10 || !Pattern.matches("\\d{4}-\\d\\d-\\d\\d", user.getDob())) return false;
        if (user.getPhone() <= 0) return false;
        return true;
    }
}
