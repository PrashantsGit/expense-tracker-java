package com.expense.controller;

import java.io.IOException;
import java.sql.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.expense.dao.ExpenseDAO;
import com.expense.model.Expense;
import com.expense.model.User;

@WebServlet("/AddExpenseServlet")
public class AddExpenseServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get logged-in user from session
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loggedUser");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int userId = user.getId();

        // Get form data
        String title = request.getParameter("title");
        double amount = Double.parseDouble(request.getParameter("amount"));

        int categoryId = Integer.parseInt(request.getParameter("category_id"));

        String dateStr = request.getParameter("expense_date");
        Date expenseDate = Date.valueOf(dateStr);

        String description = request.getParameter("description");

        // Create Expense object
        Expense exp = new Expense();
        exp.setUserId(userId);
        exp.setCategoryId(categoryId);
        exp.setTitle(title);
        exp.setAmount(amount);
        exp.setExpenseDate(expenseDate);
        exp.setDescription(description);

        // Save using DAO
        ExpenseDAO dao = new ExpenseDAO();
        boolean success = dao.addExpense(exp);

        if (success) {
            response.sendRedirect("view-expense.jsp");
        } else {
            response.sendRedirect("add-expense.jsp?error=1");
        }
    }
}
