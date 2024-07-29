<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 7/29/2024
  Time: 4:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>${classD==null ? "Create new class" : "Update class"}</h1>
<form action="LoadClasses" method="post">
    <input type="hidden" name="action" value="${classD == null ? 'save' : 'update'}">
    <label for="id">Class name: </label>
    <input type="text" name="id" id="id" value="${classD != null ? classD.classId : ''}" required>
    <label for="className">Class name: </label>
    <input type="text" name="className" id="className" value="${classD != null ? classD.className : ''}" required>
    <label for="status">Status: </label>
    <input type="checkbox" name="status" id="status" ${classD != null && classD.status ? 'checked' : ''}>
    <button type="submit">Save</button>
</form>

</body>
</html>
