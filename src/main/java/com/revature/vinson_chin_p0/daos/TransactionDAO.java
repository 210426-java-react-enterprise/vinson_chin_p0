package com.revature.vinson_chin_p0.daos;

import com.revature.vinson_chin_p0.models.Transaction;
import com.revature.vinson_chin_p0.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionDAO {

    public Transaction save(Transaction newTransaction) {

        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sqlInsertUser = "insert into project0.transactions (accountid, amount, transactiontype, datetime) values (?,?,?,(select current_timestamp))";
            PreparedStatement pstmt = conn.prepareStatement(sqlInsertUser, new String[] { "id" });
            pstmt.setInt(1, newTransaction.getAccountId());
            pstmt.setDouble(2,newTransaction.getAmount());
            pstmt.setString(3, newTransaction.getTransactionType());
            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted != 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                while (rs.next()) {
                    newTransaction.setId(rs.getInt("id"));
                }
            }

        } catch (SQLException throwables) {
            System.err.println("No connection found...exiting program");
            System.exit(-1);
        }

        return newTransaction;
    }

    public Transaction[] findTransactions(int accountId) {

        Transaction[] transactions = new Transaction[50];

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "select * from project0.transactions where accountid = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, accountId);

            ResultSet rs = pstmt.executeQuery();
            int i = 0;
            while (rs.next()) {
                transactions[i] = new Transaction();
                transactions[i].setId(rs.getInt("id"));
                transactions[i].setAccountId(rs.getInt("accountid"));
                transactions[i].setAmount(rs.getDouble("amount"));
                transactions[i].setTransactionType(rs.getString("transactiontype"));
                transactions[i].setDateTime(rs.getTimestamp("datetime"));
                i++;
            }

        } catch (SQLException throwables) {
            System.err.println("No connection found...exiting program");
            System.exit(-1);
        }

        return transactions;

    }
}
