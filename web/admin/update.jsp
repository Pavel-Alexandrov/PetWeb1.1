<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: DNS
  Date: 16.10.2019
  Time: 6:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Изменить пользователя</title>
</head>
<body>
    <form action="/admin/update" method="post">
        <p>Login</p>
        <p><c:out value="${login}"/></p>
        <p>Name</p>
        <p><input type="text" name="name"></p>
        <p>Password</p>
        <p><input type="text" name="password"></p>
        <input type="hidden" name="login" value="${login}">
        <input type="hidden" name="id" value="${id}">
        <input type="hidden" name="role" value="${role}">
        <input hidden="hidden", name="requester" value="admin">
        <p><input type="submit"></p>
    </form>
</body>
</html>
