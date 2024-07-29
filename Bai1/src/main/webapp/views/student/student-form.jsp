<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 7/29/2024
  Time: 4:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>${student==null ? "Create new student" : "Update student"}</h1>
<form action="LoadStudent" method="post">
    <input type="hidden" name="action" value="${student == null ? 'save' : 'update'}">
    <c:if test="${student != null}">
    <input type="hidden" name="id" value="${student.stuId}">
    </c:if>
    <label for="fullName">Class name: </label>
    <input type="text" name="fullName" id="fullName" value="${student != null ? student.fullName : ''}" required>
    <label for="gender">Gender: </label>
    <input type="checkbox" name="gender" id="gender" ${student != null && student.gender ? 'checked' : ''}>
    <label for="birthday">birthday: </label>
    <input type="text" name="birthday" id="birthday" value="${student != null ? student.birthday : ''}" required>
    <label for="address">address: </label>
    <input type="text" name="address" id="address" value="${student != null ? student.address : ''}" required>
    <label for="classId">Class Id: </label>

    <input type="text" name="classId" id="classId" value="${student != null ? student.classId : ''}" required>
    <button type="submit">Save</button>
</body>
</html>
