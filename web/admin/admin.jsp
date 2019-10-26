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
    <td>role</td>
    <td>Эксклюзивные возможности</td>
  </tr>
  <c:forEach var="user" items="${userList}">
    <tr>
      <td><c:out value="${user.id}"/></td>
      <td><c:out value="${user.login}"/></td>
      <td><c:out value="${user.name}"/></td>
      <td><c:out value="${user.password}"/></td>
      <td><c:out value="${user.role}"/></td>
      <td>
        <form action="/admin/delete" method="post">
          <button name="id" value="${user.id}">Удалить пользователя</button>
        </form>
        <form action="/admin/update" method="get">
          <button name="id" value="${user.id}">Изменить пользователя:</button>
            <input hidden="hidden", name="login", value="${user.login}">
            <input hidden="hidden", name="role", value="${user.role}">
            <input hidden="hidden", name="requester" value="admin">
        </form>
        <form action="/user" method="post">
          <button name="login", value="${user.login}">Войти как пользователь</button>
          <input hidden="hidden", name="requester" value="admin">
        </form>
      </td>
    </tr>
  </c:forEach>
</table>
<p><form action="/admin/add.jsp">
    <button>Добавить пользователя:</button>
</form></p>
<p><form action="/exit" method="get">
  <button>Выйти</button>
</form></p>
</body>
</html>
