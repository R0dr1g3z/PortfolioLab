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
<a href="/admin/institutionAdd">DODAJ FUNDACJĘ</a><br><br>
<table>
<tr>
    <th>Nazwa</th>
    <th>Opis</th>
    <th>Akcje</th>
</tr>
<c:forEach items="${institutions}" var="institution">
    <tr>
    <td><c:out value="${institution.name}"/></td>
    <td><c:out value="${institution.description}"/></td>
        <td>
            <a href="/admin/institutionEdit/${institution.id}">EDYTUJ</a>
            <a href="/admin/institutionDelete/${institution.id}">USUŃ</a>
        </td>
    </tr>
</c:forEach>
</table>
</body>
</html>
