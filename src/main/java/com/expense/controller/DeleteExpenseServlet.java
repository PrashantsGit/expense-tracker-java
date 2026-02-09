package com.expense.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.expense.dao.ExpenseDAO;
import com.expense.model.User;

@WebServlet("/DeleteExpenseServlet")
public class DeleteExpenseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User user = (User) (session != null ? session.getAttribute("loggedUser") : null);

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int userId = user.getId();
        int expenseId = Integer.parseInt(request.getParameter("expenseId"));

        ExpenseDAO dao = new ExpenseDAO();
        dao.deleteExpense(expenseId, userId);

        response.sendRedirect("view-expense.jsp");
    }
}
