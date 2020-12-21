<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: r0dr1g3z
  Date: 14.12.2020
  Time: 21:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="/resources/css/my.css" rel="stylesheet">
</head>
<body>
<a href="/admin/home">Strona główna</a><br><br>
<a href="/admin/adminAdd">DODAJ ADMINA</a><br><br>
<table>
    <tr>
        <th>Email</th>
        <th>Imię i nazwisko</th>
        <th>Akcje</th>
    </tr>
    <c:forEach items="${admins}" var="admin">
        <tr>
            <td><c:out value="${admin.username}"/></td>
            <td><c:out value="${admin.fullName}"/></td>
            <td>
                <a href="/admin/adminEdit/${admin.id}">EDYTUJ</a>
                <a href="/admin/adminDelete/${admin.id}">USUŃ</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
