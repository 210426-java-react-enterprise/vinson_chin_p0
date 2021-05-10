package com.revature.vinson_chin_p0.daos;

import com.revature.vinson_chin_p0.models.Account;
import com.revature.vinson_chin_p0.models.AppUser;
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

}
