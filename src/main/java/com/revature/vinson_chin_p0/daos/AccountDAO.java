package com.revature.vinson_chin_p0.daos;

import com.revature.vinson_chin_p0.models.Account;
import com.revature.vinson_chin_p0.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAO {

    public Account save(Account newAccount) {

        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sqlInsertUser = "insert into project0.accounts (userid, balance, accounttype, name) values (?,?,?,?)";
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
            throwables.printStackTrace();
        }

        return newAccount;
    }

    public Account update(Account changedAccount) {

        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

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
            throwables.printStackTrace();
        }

        return changedAccount;
    }

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
            throwables.printStackTrace();
        }

        return changedAccount;
    }

    public boolean isNameAvailable(String name) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "select * from project0.accounts where name = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;

    }

    public boolean isUpdatedNameAvailable(String name, int id) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "select * from project0.accounts where name = ? except select * from project0.accounts where id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setInt(2, id);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;

    }

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
            e.printStackTrace();
        }

        return accounts;

    }
}
