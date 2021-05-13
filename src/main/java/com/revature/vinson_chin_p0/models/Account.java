package com.revature.vinson_chin_p0.models;

/**
 * Account class used for constructing an Account object
 * @author Vinson Chin
 *
 */
public class Account {

    private int id;
    private int userId;
    private double balance;
    private String accountType;
    private String name;

    public Account() {
        super();
    }

    public Account(int userId, double balance, String accountType, String name) {
        this.userId = userId;
        this.balance = balance;
        this.accountType = accountType;
        this.name = name;
    }

    public Account(int id, int userId, double balance, String accountType, String name) {
        this(userId, balance, accountType, name);
        this.id = id;
    }

    /**
     * Getters and Setters
     *
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userId;
    }

    public void setUserid(int userid) {
        this.userId = userid;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method to create a string containing the information of the Account object
     *
     * @return
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Account{");
        sb.append("id=").append(id);
        sb.append(", userId='").append(userId).append('\'');
        sb.append(", balance='").append(balance).append('\'');
        sb.append(", accountType='").append(accountType).append('\'');
        sb.append(", name='").append(name);
        sb.append('}');
        return sb.toString();
    }
}
