package com.expense.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.expense.model.Category;
import com.expense.connection.DBConnection;

public class CategoryDAO {
    private Connection conn;

    public CategoryDAO() {
        this.conn = DBConnection.getConnection();
    }

    // Retrieve all categories for the dropdown menu
    public List<Category> getAllCategories() {
        List<Category> list = new ArrayList<>();
        String query = "SELECT category_id, category_name FROM categories ORDER BY category_name";

        try (PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Category c = new Category();
                c.setCategoryId(rs.getInt("category_id"));
                c.setCategoryName(rs.getString("category_name"));
                list.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}