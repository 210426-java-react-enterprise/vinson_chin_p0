package com.revature.vinson_chin_p0.daos;

import com.revature.vinson_chin_p0.models.Account;
import com.revature.vinson_chin_p0.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Account data access object used for inserting and updating data in the database
 * @author Vinson Chin
 *
 */
public class AccountDAO {

    /**
     * Prepares a statement to insert account data into the database
     *
     * @param conn
     * @param newAccount
     * @return
     */
    public Account save(Connection conn, Account newAccount) {

        try {

            String sqlInsertUser =
                    "insert into project0.accounts (userid, balance, accounttype, name) values (?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sqlInsertUser, new String[] { "id" });
            pstmt.setInt(1,newAccount.getUserid());
            pstmt.setDouble(2,newAccount.getBalance());
            pstmt.setString(3, newAccount.getAccountType());
            pstmt.setString(4, newAccount.getName());
            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted != 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                while (rs.next()) {
                    newAccount.setId(rs.getInt("id"));
                }
            }

        } catch (SQLException throwables) {
            System.err.println("Connection or SQL statement problems...exiting application");
            System.exit(0);
        }

        return newAccount;
    }

    /**
     * Prepares a statement to update account data for current account
     *
     * @param conn
     * @param changedAccount
     * @return
     */
    public Account update(Connection conn, Account changedAccount) {

        try {

            String sqlUpdateUser = "update project0.accounts set accounttype = ? , name = ? where id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sqlUpdateUser);
            pstmt.setString(1, changedAccount.getAccountType());
            pstmt.setString(2,changedAccount.getName());
            pstmt.setInt(3, changedAccount.getId());
            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated != 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                while (rs.next()) {
                    changedAccount.setId(rs.getInt("id"));
                }
            }

        } catch (SQLException throwables) {
            System.err.println("Connection or SQL statement problems...exiting application");
            System.exit(0);
        }

        return changedAccount;
    }

    /**
     * Prepares a statement to update balance for current account
     *
     * @param changedAccount
     * @return
     */
    public Account updateBalance(Account changedAccount) {

        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sqlUpdateUser = "update project0.accounts set balance = ? where id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sqlUpdateUser);
            pstmt.setDouble(1, changedAccount.getBalance());
            pstmt.setInt(2, changedAccount.getId());
            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated != 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                while (rs.next()) {
                    changedAccount.setId(rs.getInt("id"));
                }
            }

        } catch (SQLException throwables) {
            System.err.println("Connection or SQL statement problems...exiting application");
            System.exit(0);
        }

        return changedAccount;
    }

    /**
     * Prepares a statement to search the database for a name for current user
     *
     * @param conn
     * @param name
     * @param userId
     * @return
     */
    public boolean isNameAvailable(Connection conn, String name, int userId) {

        try {

            String sql = "select * from project0.accounts where name = ? and userid = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setInt(2, userId);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return false;
            }

        } catch (SQLException e) {
            System.err.println("Connection or SQL statement problems...exiting application");
            System.exit(0);
        }

        return true;

    }

    /**
     * Prepares a statement to search the database for a name for the current user except current account
     *
     * @param conn
     * @param name
     * @param id
     * @param userId
     * @return
     */
    public boolean isUpdatedNameAvailable(Connection conn, String name, int id, int userId) {

        try {

            String sql = "select * from project0.accounts where name = ? and userid = ? " +
                    "except select * from project0.accounts where id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setInt(2, userId);
            pstmt.setInt(3, id);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return false;
            }

        } catch (SQLException e) {
            System.err.println("Connection or SQL statement problems...exiting application");
            System.exit(0);
        }

        return true;

    }

    /**
     * Prepares a statement to search for all other accounts for user except current account
     *
     * @param userId
     * @param id
     * @return
     */
    public Account[] findOtherAccounts(int userId, int id) {

        Account[] accounts = new Account[50];

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql =
                    "select * from project0.accounts where userid = ? except select * from project0.accounts where id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);
            pstmt.setInt(2, id);

            ResultSet rs = pstmt.executeQuery();
            int i = 0;
            while (rs.next()) {
                accounts[i] = new Account();
                accounts[i].setId(rs.getInt("id"));
                accounts[i].setUserid(rs.getInt("userid"));
                accounts[i].setBalance(rs.getDouble("balance"));
                accounts[i].setAccountType(rs.getString("accounttype"));
                accounts[i].setName(rs.getString("name"));
                i++;
            }

        } catch (SQLException e) {
            System.err.println("Connection or SQL statement problems...exiting application");
            System.exit(0);
        }

        return accounts;

    }

    /**
     * Prepares a statement to search for all accounts for user
     *
     * @param userId
     * @return
     */
    public Account[] findAccounts(int userId) {

        Account[] accounts = new Account[50];

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "select * from project0.accounts where userid = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);

            ResultSet rs = pstmt.executeQuery();
            int i = 0;
            while (rs.next()) {
                accounts[i] = new Account();
                accounts[i].setId(rs.getInt("id"));
                accounts[i].setUserid(rs.getInt("userid"));
                accounts[i].setBalance(rs.getDouble("balance"));
                accounts[i].setAccountType(rs.getString("accounttype"));
                accounts[i].setName(rs.getString("name"));
                i++;
            }

        } catch (SQLException e) {
            System.err.println("Connection or SQL statement problems...exiting application");
            System.exit(0);
        }

        return accounts;

    }
}
