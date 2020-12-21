<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: r0dr1g3z
  Date: 14.12.2020
  Time: 22:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>EDYTOWANIE ADMINA</h1>
<form:form method="post" modelAttribute="user">
    <form:hidden path="id"/>
    <label>Email</label>
    <form:input path="username"/><br>
    <label>Hasło</label>
    <form:password path="password"/><br>
    <label>Imię</label>
    <form:input path="firstName"/><br>
    <label>Nazwisko</label>
    <form:input path="lastName"/><br>
    <input type="submit" value="Edytuj">
</form:form>
</body>
</html>
