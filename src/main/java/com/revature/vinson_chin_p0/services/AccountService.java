package com.revature.vinson_chin_p0.services;

import com.revature.vinson_chin_p0.daos.AccountDAO;
import com.revature.vinson_chin_p0.exceptions.InvalidRequestException;
import com.revature.vinson_chin_p0.exceptions.ResourcePersistenceException;
import com.revature.vinson_chin_p0.models.Account;

public class AccountService {

    private AccountDAO accountDao;

    public AccountService(AccountDAO accountDao) { this.accountDao = accountDao; }

    public Account create(Account newAccount) throws InvalidRequestException, ResourcePersistenceException {

        if (!isAccountValid(newAccount)) {
            throw new InvalidRequestException("Invalid new account data provided!");
        }

        if (!accountDao.isNameAvailable(newAccount.getName())) {
            throw new ResourcePersistenceException("The provided account name is already taken!");
        }

        return accountDao.save(newAccount);

    }

    public Account update(Account changedAccount) throws InvalidRequestException, ResourcePersistenceException {

        if (!isAccountValid(changedAccount)) {
            throw new InvalidRequestException("Invalid account data provided!");
        }

        if (!accountDao.isUpdatedNameAvailable(changedAccount.getName(), changedAccount.getId())) {
            throw new ResourcePersistenceException("The provided name is already taken!");
        }

        return accountDao.update(changedAccount);

    }

    public Account updateBalance(Account changedAccount) throws InvalidRequestException {

        if (!isAccountValid(changedAccount)) {
            throw new InvalidRequestException("Invalid account data provided!");
        }

        return accountDao.updateBalance(changedAccount);

    }

    public boolean isAccountValid(Account account) {
        if (account == null) return false;
        if (account.getUserid() <= 0) return false;
        if (account.getBalance() < 0) return false;
        if (account.getAccountType() == null || account.getAccountType().trim().isEmpty() || account.getAccountType().length() > 1) return false;
        if (account.getName() == null || account.getName().trim().isEmpty() || account.getName().length() > 255) return false;
        return true;
    }
}
