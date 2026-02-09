<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.expense.dao.ExpenseDAO" %>
<%@ page import="com.expense.model.Expense" %>
<%@ page import="com.expense.model.User" %>

<%
    // ---------- Session Protection ----------
    User u = (User) session.getAttribute("loggedUser");
    if (u == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<%@ include file="components/header.jsp" %>
<%@ include file="components/sidebar.jsp" %>

<div class="content-wrapper">

    <!-- Page Header -->
    <section class="content-header">
        <div class="container-fluid">
            <h1>View Expenses</h1>
        </div>
    </section>

    <!-- Main Content -->
    <section class="content">
        <div class="container-fluid">

            <div class="card">
                <div class="card-body">

                    <table class="table table-bordered table-striped">
                        <thead>
                            <tr>
                                <th>Title</th>
                                <th>Category</th>
                                <th>Amount (₹)</th>
                                <th>Date</th>
                                <th>Description</th>
                                <th>Action</th>
                            </tr>
                        </thead>

                        <tbody>
                            <%
                                ExpenseDAO dao = new ExpenseDAO();
                                List<Expense> list = dao.getExpensesByUserId(u.getId());

                                if (list.isEmpty()) {
                            %>
                                <tr>
                                    <td colspan="6" class="text-center text-muted">
                                        No expenses found. Start adding your expenses!
                                    </td>
                                </tr>
                            <%
                                } else {
                                    for (Expense e : list) {
                            %>
                                <tr>
                                    <td><%= e.getTitle() %></td>
                                    <td><%= e.getCategoryName() %></td>
                                    <td><%= e.getAmount() %></td>
                                    <td><%= e.getExpenseDate() %></td>
                                    <td><%= e.getDescription() %></td>
                                    <td>
                                        <a href="DeleteExpenseServlet?expenseId=<%= e.getExpenseId() %>"
                                           class="btn btn-danger btn-sm"
                                           onclick="return confirm('Are you sure you want to delete this expense?');">
                                            <i class="fas fa-trash"></i>
                                        </a>
                                    </td>
                                </tr>
                            <%
                                    }
                                }
                            %>
                        </tbody>
                    </table>

                </div>
            </div>

        </div>
    </section>
</div>

<%@ include file="components/footer.jsp" %>
