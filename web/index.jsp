<%--
  Created by IntelliJ IDEA.
  User: DNS
  Date: 23.10.2019
  Time: 5:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <h3>Пожалуйста, войдите</h3>
    <form action="/login" method="post">
        <p>Login</p>
        <p><input type="text" name="login"></p>
        <p>Password</p>
        <p><input type="text" name="password"></p>
        <p><input type="submit"></p>
    </form>
</body>
</html>
