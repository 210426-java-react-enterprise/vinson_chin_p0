package com.revature.vinson_chin_p0.models;

import java.sql.Timestamp;

/**
 * Transaction class used for constructing a Transaction object
 * @author Vinson Chin
 *
 */
public class Transaction {

    private int id;
    private int accountId;
    private double amount;
    private String transactionType;
    private Timestamp dateTime;

    public Transaction() { super(); }

    public Transaction(int accountId, double amount, String transactionType) {
        this.accountId = accountId;
        this.amount = amount;
        this.transactionType = transactionType;
    }

    public Transaction(int id, int accountId, double amount, String transactionType) {
        this(accountId, amount, transactionType);
        this.id = id;
    }

    /**
     * Getter and Setter methods
     *
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Method to create a string containing the information of the Transaction object
     *
     * @return
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Transaction{");
        sb.append("id=").append(id);
        sb.append(", accountId='").append(accountId).append('\'');
        sb.append(", amount='").append(amount).append('\'');
        sb.append(", transactionType='").append(transactionType).append('\'');
        sb.append(", dateTime='").append(dateTime);
        sb.append('}');
        return sb.toString();
    }
}
