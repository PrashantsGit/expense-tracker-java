<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="com.expense.dao.CategoryDAO" %>
<%@ page import="com.expense.model.Category" %>

<%@ include file="components/header.jsp" %>
<%@ include file="components/sidebar.jsp" %>

<div class="content-wrapper">

    <!-- Page Header -->
    <section class="content-header">
        <div class="container-fluid">
            <h1>Add New Expense</h1>
        </div>
    </section>

    <!-- Main Content -->
    <section class="content">
        <div class="container-fluid">

            <div class="card card-primary">
                <div class="card-header">
                    <h3 class="card-title">Expense Form</h3>
                </div>

                <!-- Expense Form -->
                <form action="AddExpenseServlet" method="post">

                    <div class="card-body">

                        <!-- Title -->
                        <div class="form-group">
                            <label>Expense Title</label>
                            <input type="text" name="title" class="form-control"
                                   placeholder="Enter expense title" required>
                        </div>

                        <!-- Amount -->
                        <div class="form-group">
                            <label>Amount (₹)</label>
                            <input type="number" step="0.01" name="amount"
                                   class="form-control"
                                   placeholder="Enter amount" required>
                        </div>

                        <!-- Date -->
                        <div class="form-group">
                            <label>Expense Date</label>
                            <input type="date" name="expense_date"
                                   class="form-control" required>
                        </div>

                        <!-- Category Dropdown -->
                        <div class="form-group">
                            <label>Select Category</label>
                            <select name="category_id" class="form-control" required>

                                <option value="">-- Choose Category --</option>

                                <%
                                    CategoryDAO dao = new CategoryDAO();
                                    List<Category> list = dao.getAllCategories();

                                    for(Category c : list){
                                %>
                                        <option value="<%= c.getCategoryId() %>">
                                            <%= c.getCategoryName() %>
                                        </option>
                                <%
                                    }
                                %>

                            </select>
                        </div>

                        <!-- Description -->
                        <div class="form-group">
                            <label>Description</label>
                            <textarea name="description" class="form-control"
                                      placeholder="Optional notes..."></textarea>
                        </div>

                    </div>

                    <!-- Submit -->
                    <div class="card-footer">
                        <button type="submit" class="btn btn-primary">
                            Add Expense
                        </button>
                    </div>

                </form>

            </div>

        </div>
    </section>
</div>

<%@ include file="components/footer.jsp" %>
