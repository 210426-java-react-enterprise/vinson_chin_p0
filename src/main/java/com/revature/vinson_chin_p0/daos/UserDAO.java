package com.revature.vinson_chin_p0.daos;

import com.revature.vinson_chin_p0.models.AppUser;
import com.revature.vinson_chin_p0.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * User data access object to insert and update data into the database
 * @author Vinson Chin
 *
 */
public class UserDAO {

    /**
     * Prepares a statement to insert user data into the database
     *
     * @param conn
     * @param newUser
     * @return
     */
    public AppUser save(Connection conn, AppUser newUser) {

        try {

            String sqlInsertUser = "insert into project0.users " +
                    "(username , password , email , firstname , lastname , dob, phone) values (?,?,?,?,?,?,?)";
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
            System.err.println("Connection or SQL statement problems...exiting application");
            System.exit(0);
        }

        return newUser;
    }

    /**
     * Prepares a statement to update user data in the database
     *
     * @param conn
     * @param changedUser
     * @return
     */
    public AppUser update(Connection conn, AppUser changedUser) {

        try {

            String sqlUpdateUser = "update project0.users set " +
                    "username = ? , password = ? , email = ? , firstname = ? , lastname = ? , dob = ?, phone = ? " +
                    "where id = ?";
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
            System.err.println("Connection or SQL statement problems...exiting application");
            System.exit(0);
        }

        return changedUser;
    }

    /**
     * Prepares a statement to search the database for a username
     *
     * @param conn
     * @param username
     * @return
     */
    public boolean isUsernameAvailable(Connection conn, String username) {

        try {

            String sql = "select * from project0.users where username = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return false;
            }

        } catch (SQLException throwables) {
            System.err.println("Connection or SQL statement problems...exiting application");
            System.exit(0);
        }

        return true;

    }

    /**
     * Prepares a statement to search the database for a username except for the current user
     *
     * @param conn
     * @param username
     * @param id
     * @return
     */
    public boolean isUpdatedUsernameAvailable(Connection conn, String username, int id) {

        try {

            String sql =
                    "select * from project0.users where username = ? except select * from project0.users where id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setInt(2, id);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return false;
            }

        } catch (SQLException throwables) {
            System.err.println("Connection or SQL statement problems...exiting application");
            System.exit(0);
        }

        return true;

    }

    /**
     * Prepares a statement to search the database for an email
     *
     * @param conn
     * @param email
     * @return
     */
    public boolean isEmailAvailable(Connection conn, String email) {

        try {

            String sql = "select * from project0.users where email = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return false;
            }

        } catch (SQLException throwables) {
            System.err.println("Connection or SQL statement problems...exiting application");
            System.exit(0);
        }

        return true;
    }

    /**
     * Prepares a statement to search the database for an email except for the current user
     *
     * @param conn
     * @param email
     * @param id
     * @return
     */
    public boolean isUpdatedEmailAvailable(Connection conn, String email, int id) {

        try {

            String sql =
                    "select * from project0.users where email = ? except select * from project0.users where id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setInt(2,id);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return false;
            }

        } catch (SQLException throwables) {
            System.err.println("Connection or SQL statement problems...exiting application");
            System.exit(0);
        }

        return true;
    }

    /**
     * Prepares a statement to search the database for a username and password
     *
     * @param username
     * @param password
     * @return
     */
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

        } catch (SQLException throwables) {
            System.err.println("Connection or SQL statement problems...exiting application");
            System.exit(0);
        }

        return user;

    }

}
