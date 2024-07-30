<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 7/29/2024
  Time: 4:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <title>Title</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<form action="LoadClasses" method="get">
    <input type="hidden" name="action" value="search">
    <input type="text" name="searchQuery" placeholder="Search Class">
    <button type="submit">Search</button>
</form>
<div class="container">
    <h1>LIST ACCOUNTS!</h1>
    <table class="table table-bordered">
        <tr>
            <th>Id</th>
            <th>Class Name</th>
            <th>Status</th>
            <th>Action</th>
        </tr>

        <c:forEach items="${classes}" var="u">
            <tr>
                <td>${u.classId}</td>
                <td>${u.className}</td>
                <td>${u.status ? "Active" : "Inactive"}</td>
                <td>
                    <a href="LoadClasses?action=edit&id=${u.classId}">Update</a>
                    <a href="LoadClasses?action=delete&id=${u.classId}">Delete</a>
                    <a href="LoadClasses?action=details&id=${u.classId}">Show Details</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <button class="btn btn-primary">
        <a href="LoadClasses?action=create">Add new Classes</a>
    </button>
    <button class="btn btn-warning">
        <a href="LoadStudent">Show List Student</a>
    </button>
    <button class="btn btn-danger">
        <a href="index.jsp">Back to Home</a>
    </button>
    <h1>${errorDelete}</h1>
<%--    <button class="btn btn-success">--%>
<%--        details--%>
<%--    </button>--%>
</div>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
</body>
</html>
