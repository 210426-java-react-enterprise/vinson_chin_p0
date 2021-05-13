package com.revature.vinson_chin_p0.services;

import com.revature.vinson_chin_p0.daos.TransactionDAO;
import com.revature.vinson_chin_p0.exceptions.InvalidRequestException;
import com.revature.vinson_chin_p0.models.Transaction;

/**
 * TransactionService class for validations and checking for exceptions
 * @author Vinson Chin
 *
 */
public class TransactionService {

    private TransactionDAO transactionDao;

    public TransactionService(TransactionDAO transactionDao) { this.transactionDao = transactionDao; }

    /**
     * Validates new transaction then sends to DAO
     *
     * @param newTransaction
     * @return
     */
    public Transaction create(Transaction newTransaction) throws InvalidRequestException {

        if (!isTransactionValid(newTransaction)) {
            throw new InvalidRequestException("Invalid new transaction data provided!");
        }

        return transactionDao.save(newTransaction);

    }

    /**
     * Checks transaction for correct format
     *
     * @param transaction
     * @return
     */
    public boolean isTransactionValid(Transaction transaction) {
        if (transaction == null) return false;
        if (transaction.getAccountId() <= 0) return false;
        if (transaction.getAmount() <= 0) return false;
        if (transaction.getTransactionType() == null || transaction.getTransactionType().trim().isEmpty()) return false;
        return true;
    }
}
