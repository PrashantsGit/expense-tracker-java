package com.expense.dao;

import java.sql.*;
import com.expense.model.User;
import com.expense.connection.DBConnection;

public class UserDAO {
    private Connection conn;

    public UserDAO() {
        this.conn = DBConnection.getConnection();
    }

    public boolean registerUser(User user) {
        boolean f = false;
        String query = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            if (ps.executeUpdate() == 1) f = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return f;
    }

    public User loginUser(String email, String password) {
        User user = null;
        String query = "SELECT user_id, username, email FROM users WHERE email=? AND password=?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, email);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = new User();
                    user.setId(rs.getInt("user_id"));
                    user.setUsername(rs.getString("username"));
                    user.setEmail(rs.getString("email"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }


}