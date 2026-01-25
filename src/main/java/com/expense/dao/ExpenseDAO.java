package com.expense.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.expense.connection.DBConnection;
import com.expense.model.Expense;

public class ExpenseDAO {

    private Connection conn;

    public ExpenseDAO() {
        this.conn = DBConnection.getConnection();
    }

    // ================= ADD EXPENSE =================
    public boolean addExpense(Expense exp) {
        String query = "INSERT INTO expenses "
                     + "(user_id, category_id, title, amount, expense_date, description) "
                     + "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, exp.getUserId());
            ps.setInt(2, exp.getCategoryId());
            ps.setString(3, exp.getTitle());
            ps.setDouble(4, exp.getAmount());
            ps.setDate(5, exp.getExpenseDate()); // Proper DATE handling
            ps.setString(6, exp.getDescription());

            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // ================= VIEW EXPENSES BY USER =================
    public List<Expense> getExpensesByUserId(int userId) {

        List<Expense> list = new ArrayList<>();

        String query = "SELECT e.expense_id, e.title, e.amount, e.expense_date, "
                     + "e.description, c.category_name "
                     + "FROM expenses e "
                     + "JOIN categories c ON e.category_id = c.category_id "
                     + "WHERE e.user_id = ? "
                     + "ORDER BY e.expense_date DESC";

        try (PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Expense exp = new Expense();

                    exp.setExpenseId(rs.getInt("expense_id"));
                    exp.setTitle(rs.getString("title"));
                    exp.setAmount(rs.getDouble("amount"));
                    exp.setExpenseDate(rs.getDate("expense_date"));
                    exp.setDescription(rs.getString("description"));
                    exp.setCategoryName(rs.getString("category_name"));

                    list.add(exp);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // ================= DELETE EXPENSE =================
    public boolean deleteExpense(int expenseId) {

        String query = "DELETE FROM expenses WHERE expense_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, expenseId);
            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // ================= FILTER EXPENSES BY DATE RANGE =================
    public List<Expense> getExpensesByDateRange(int userId, Date fromDate, Date toDate) {

        List<Expense> list = new ArrayList<>();

        String query = "SELECT e.expense_id, e.title, e.amount, e.expense_date, "
                     + "e.description, c.category_name "
                     + "FROM expenses e "
                     + "JOIN categories c ON e.category_id = c.category_id "
                     + "WHERE e.user_id = ? AND e.expense_date BETWEEN ? AND ? "
                     + "ORDER BY e.expense_date DESC";

        try (PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, userId);
            ps.setDate(2, fromDate);
            ps.setDate(3, toDate);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Expense exp = new Expense();

                    exp.setExpenseId(rs.getInt("expense_id"));
                    exp.setTitle(rs.getString("title"));
                    exp.setAmount(rs.getDouble("amount"));
                    exp.setExpenseDate(rs.getDate("expense_date"));
                    exp.setDescription(rs.getString("description"));
                    exp.setCategoryName(rs.getString("category_name"));

                    list.add(exp);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
