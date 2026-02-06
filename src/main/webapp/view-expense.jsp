<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.expense.dao.ExpenseDAO" %>
<%@ page import="com.expense.model.Expense" %>

<%@ include file="components/header.jsp" %>
<%@ include file="components/sidebar.jsp" %>

<div class="content-wrapper">
    <section class="content-header">
        <h1>View Expenses</h1>
    </section>

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
                            </tr>
                        </thead>

                        <tbody>
                            <%
                                com.expense.model.User u =
                                    (com.expense.model.User) session.getAttribute("loggedUser");

                                ExpenseDAO dao = new ExpenseDAO();
                                List<Expense> list = dao.getExpensesByUserId(u.getId());

                                for (Expense e : list) {
                            %>
                            <tr>
                                <td><%= e.getTitle() %></td>
                                <td><%= e.getCategoryName() %></td>
                                <td><%= e.getAmount() %></td>
                                <td><%= e.getExpenseDate() %></td>
                                <td><%= e.getDescription() %></td>
                            </tr>
                            <%
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
