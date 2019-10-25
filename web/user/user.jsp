<%--
  Created by IntelliJ IDEA.
  User: DNS
  Date: 24.10.2019
  Time: 4:52
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome!</title>
</head>
<body>
    <p>Hello <c:out value="${user.name}"/>!</p>
    <p>Your data:</p>
    <p>Login: <c:out value="${user.login}"/></p>
    <p>Password: <c:out value="${user.password}"/></p>
    <p>Name: <c:out value="${user.name}"/></p>
    <form action="/update" method="get">
        <button name="id" value="${user.id}">Редактировать аккаунт:</button>
        <input hidden="hidden", name="login", value="${user.login}">
        <input hidden="hidden", name="role", value="${user.role}">
    </form>
    <form action="/login" method="get">
        <button>Выйти</button>
    </form>
</body>
</html>
