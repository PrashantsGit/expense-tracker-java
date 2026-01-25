<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="components/header.jsp" %>

<div class="content-wrapper">
    <section class="content p-4">

        <div class="card card-success" style="width:400px; margin:auto;">
            <div class="card-header">
                <h3 class="card-title">Login</h3>
            </div>

            <form action="LoginServlet" method="post">
                <div class="card-body">

                    <div class="form-group">
                        <label>Email</label>
                        <input type="email" name="email" class="form-control" required>
                    </div>

                    <div class="form-group">
                        <label>Password</label>
                        <input type="password" name="password" class="form-control" required>
                    </div>

                </div>

                <div class="card-footer">
                    <button type="submit" class="btn btn-success">Login</button>
                    <a href="register.jsp" class="btn btn-link">Create account</a>
                </div>
            </form>

        </div>

    </section>
</div>

<%@ include file="components/footer.jsp" %>
