<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: r0dr1g3z
  Date: 17.12.2020
  Time: 18:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="/">Strona główna</a><br><br>
<form:form method="post" modelAttribute="mail">
    <label>Email</label>
    <form:input path="to"/><br>
    <label>Tytuł</label>
    <form:input path="subject"/><br>
    <label>Wiadomość</label>
    <form:input path="text"/><br>
    <input type="submit" value="Wyślij">
</form:form> 
</body>
</html>
