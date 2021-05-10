package com.revature.vinson_chin_p0.services;

import com.revature.vinson_chin_p0.daos.UserDAO;
import com.revature.vinson_chin_p0.exceptions.InvalidRequestException;
import com.revature.vinson_chin_p0.exceptions.ResourcePersistenceException;
import com.revature.vinson_chin_p0.models.AppUser;

public class UserService {

    private UserDAO userDao;

    public UserService(UserDAO userDao) {
        this.userDao = userDao;
    }

    public AppUser register(AppUser newUser) throws InvalidRequestException, ResourcePersistenceException {

        if (!isUserValid(newUser)) {
            throw new InvalidRequestException("Invalid new user data provided!");
        }

        if (!userDao.isUsernameAvailable(newUser.getUsername())) {
            throw new ResourcePersistenceException("The provided username is already taken!");
        }

        if (!userDao.isEmailAvailable(newUser.getEmail())) {
            throw new ResourcePersistenceException("The provided email is already taken!");
        }

        return userDao.save(newUser);

    }

    public AppUser update(AppUser changedUser) throws InvalidRequestException, ResourcePersistenceException {

        if (!isUserValid(changedUser)) {
            throw new InvalidRequestException("Invalid new user data provided!");
        }

        if (!userDao.isUpdatedUsernameAvailable(changedUser.getUsername(), changedUser.getId())) {
            throw new ResourcePersistenceException("The provided username is already taken!");
        }

        if (!userDao.isUpdatedEmailAvailable(changedUser.getEmail(), changedUser.getId())) {
            throw new ResourcePersistenceException("The provided email is already taken!");
        }

        return userDao.update(changedUser);

    }

    public boolean isUserValid(AppUser user) {
        if (user == null) return false;
        if (user.getUsername() == null || user.getUsername().trim().isEmpty() || user.getUsername().length() > 20) return false;
        if (user.getPassword() == null || user.getPassword().trim().isEmpty() || user.getPassword().length() > 255) return false;
        if (user.getEmail() == null || user.getEmail().trim().isEmpty() || user.getEmail().length() > 255) return false;
        if (user.getFirstName() == null || user.getFirstName().trim().isEmpty() || user.getFirstName().length() > 25) return false;
        if (user.getLastName() == null || user.getLastName().trim().isEmpty() || user.getLastName().length() > 25) return false;
        if (user.getDob() == null || user.getDob().trim().isEmpty() || user.getDob().length() > 10) return false;
        if (user.getPhone() <= 0) return false;
        return true;
    }
}
