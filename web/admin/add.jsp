<%--
  Created by IntelliJ IDEA.
  User: DNS
  Date: 16.10.2019
  Time: 6:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавить пользователя</title>
</head>
<body>
    <form action="/admin/add" method="post">
        <p>Login</p>
        <p><input type="text" name="login"></p>
        <p>Name</p>
        <p><input type="text" name="name"></p>
        <p>Password</p>
        <p><input type="text" name="password"></p>
        <p><input type="submit"></p>
    </form>
</body>
</html>
