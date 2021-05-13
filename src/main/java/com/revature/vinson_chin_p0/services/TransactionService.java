package com.revature.vinson_chin_p0.services;

import com.revature.vinson_chin_p0.daos.TransactionDAO;
import com.revature.vinson_chin_p0.exceptions.InvalidRequestException;
import com.revature.vinson_chin_p0.models.Transaction;

public class TransactionService {

    private TransactionDAO transactionDao;

    public TransactionService(TransactionDAO transactionDao) { this.transactionDao = transactionDao; }

    public Transaction create(Transaction newTransaction) throws InvalidRequestException {

        if (!isTransactionValid(newTransaction)) {
            throw new InvalidRequestException("Invalid new transaction data provided!");
        }

        return transactionDao.save(newTransaction);

    }

    public boolean isTransactionValid(Transaction transaction) {
        if (transaction == null) return false;
        if (transaction.getAccountId() <= 0) return false;
        if (transaction.getAmount() <= 0) return false;
        if (transaction.getTransactionType() == null || transaction.getTransactionType().trim().isEmpty()) return false;
        return true;
    }
}
