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
<h1>EDYTOWANIE FUNDACJI</h1>
<form:form method="post" modelAttribute="institution">
    <form:hidden path="id"/>
    <label>Nazwa</label>
    <form:input path="name"/><br>
    <label>Opis</label>
    <form:input path="description"/><br>
    <input type="submit" value="Edytuj">
</form:form>
</body>
</html>
