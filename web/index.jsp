<%--
  Created by IntelliJ IDEA.
  User: DNS
  Date: 16.10.2019
  Time: 5:26
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
  <title></title>
  <style type="text/css">
    TABLE {
      width: 700px;
      border-collapse: collapse;
    }
    TD, TH {
      padding: 3px;
      border: 1px solid black;
      text-align: center;
    }
    TH {
      background: #b0e0e6;
    }
  </style>
</head>
<h1>Текущие пользователи:</h1>
<table>
  <tr>
    <td>id</td>
    <td>login</td>
    <td>name</td>
    <td>password</td>
    <td>Эксклюзивные возможности</td>
  </tr>
  <c:forEach var="user" items="${userList}">
    <tr>
      <td><c:out value="${user.id}"/></td>
      <td><c:out value="${user.login}"/></td>
      <td><c:out value="${user.name}"/></td>
      <td><c:out value="${user.password}"/></td>
      <td>
        <form action="/delete" method="post">
          <button name="id" value="${user.id}">Удалить пользователя</button>
        </form>
        <form action="/update" method="get">
          <button name="id" value="${user.id}">Изменить пользователя:</button>
            <input hidden="hidden", name="login", value="${user.login}">
        </form>
      </td>
    </tr>
  </c:forEach>
</table>
<p><form action="add.jsp">
    <button>Добавить пользователя:</button>
</form></p>
</body>
</html>
