package com.revature.vinson_chin_p0.daos;

import com.revature.vinson_chin_p0.models.AppUser;
import com.revature.vinson_chin_p0.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public AppUser save(AppUser newUser) {

        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sqlInsertUser = "insert into project0.users (username , password , email , firstname , lastname , dob, phone) values (?,?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sqlInsertUser, new String[] { "id" });
            pstmt.setString(1,newUser.getUsername());
            pstmt.setString(2,newUser.getPassword());
            pstmt.setString(3,newUser.getEmail());
            pstmt.setString(4,newUser.getFirstName());
            pstmt.setString(5,newUser.getLastName());
            pstmt.setString(6,newUser.getDob());
            pstmt.setLong(7,newUser.getPhone());
            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted != 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                while (rs.next()) {
                    newUser.setId(rs.getInt("id"));
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return newUser;
    }

    public AppUser update(AppUser changedUser) {

        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sqlUpdateUser = "update project0.users set username = ? , password = ? , email = ? , firstname = ? , lastname = ? , dob = ?, phone = ? where id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sqlUpdateUser);
            pstmt.setString(1,changedUser.getUsername());
            pstmt.setString(2,changedUser.getPassword());
            pstmt.setString(3,changedUser.getEmail());
            pstmt.setString(4,changedUser.getFirstName());
            pstmt.setString(5,changedUser.getLastName());
            pstmt.setString(6,changedUser.getDob());
            pstmt.setLong(7,changedUser.getPhone());
            pstmt.setInt(8, changedUser.getId());
            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated != 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                while (rs.next()) {
                    changedUser.setId(rs.getInt("id"));
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return changedUser;
    }

    public boolean isUsernameAvailable(String username) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "select * from project0.users where username = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;

    }

    public boolean isUpdatedUsernameAvailable(String username, int id) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "select * from project0.users where username = ? except select * from project0.users where id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
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

    public boolean isEmailAvailable(String email) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "select * from project0.users where email = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    public boolean isUpdatedEmailAvailable(String email, int id) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "select * from project0.users where email = ? except select * from project0.users where id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setInt(2,id);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    public AppUser findUserByUsernameAndPassword(String username, String password) {

        AppUser user = null;

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "select * from project0.users where username = ? and password = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                user = new AppUser();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setFirstName(rs.getString("firstname"));
                user.setLastName(rs.getString("lastname"));
                user.setEmail(rs.getString("email"));
                user.setDob(rs.getString("dob"));
                user.setPhone(rs.getLong("phone"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;

    }

}
