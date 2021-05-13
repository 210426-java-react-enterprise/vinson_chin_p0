package com.revature.vinson_chin_p0.services;

import com.revature.vinson_chin_p0.daos.AccountDAO;
import com.revature.vinson_chin_p0.exceptions.InvalidRequestException;
import com.revature.vinson_chin_p0.exceptions.ResourcePersistenceException;
import com.revature.vinson_chin_p0.models.Account;
import com.revature.vinson_chin_p0.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * AccountService class for validations and checking for exceptions
 * @author Vinson Chin
 *
 */
public class AccountService {

    private AccountDAO accountDao;

    public AccountService(AccountDAO accountDao) { this.accountDao = accountDao; }

    /**
     * Validates new account and sends to DAO
     *
     * @param newAccount
     * @return
     */
    public Account create(Account newAccount) throws InvalidRequestException, ResourcePersistenceException {

        if (!isAccountValid(newAccount)) {
            throw new InvalidRequestException("Invalid new account data provided!");
        }

        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

            if (!accountDao.isNameAvailable(conn, newAccount.getName())) {
                throw new ResourcePersistenceException("The provided account name is already taken!");
            }

            return accountDao.save(conn, newAccount);

        } catch (SQLException throwables) {
            System.err.println("Connection or SQL statement problems...exiting application");
            System.exit(0);
        }

        return null;

    }

    /**
     * Validates updated account and sends to DAO
     *
     * @param changedAccount
     * @return
     */
    public Account update(Account changedAccount) throws InvalidRequestException, ResourcePersistenceException {

        if (!isAccountValid(changedAccount)) {
            throw new InvalidRequestException("Invalid account data provided!");
        }

        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

            if (!accountDao.isUpdatedNameAvailable(conn, changedAccount.getName(), changedAccount.getId())) {
                throw new ResourcePersistenceException("The provided name is already taken!");
            }

            return accountDao.update(conn, changedAccount);

        } catch (SQLException throwables) {
            System.err.println("Connection or SQL statement problems...exiting application");
            System.exit(0);
        }

        return null;

    }

    /**
     * Validates changes in balance and sends to DAO
     *
     * @param changedAccount
     * @return
     */
    public Account updateBalance(Account changedAccount) throws InvalidRequestException {

        if (!isAccountValid(changedAccount)) {
            throw new InvalidRequestException("Invalid account data provided!");
        }

        return accountDao.updateBalance(changedAccount);

    }

    /**
     * Checks account for correct format
     *
     * @param account
     * @return
     */
    public boolean isAccountValid(Account account) {
        if (account == null) return false;
        if (account.getUserid() <= 0) return false;
        if (account.getBalance() < 0) return false;
        if (account.getAccountType() == null || account.getAccountType().trim().isEmpty()) return false;
        if (account.getName() == null || account.getName().trim().isEmpty() || account.getName().length() > 255)
            return false;
        return true;
    }
}
