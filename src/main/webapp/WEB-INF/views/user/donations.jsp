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
<a href="/">Strona główna</a><br><br>
<table>
    <tr>
        <th>Ilość worków</th>
        <th>Fundacja</th>
        <th>Data</th>
        <th>Godzina</th>
        <th>Uwagi</th>
    </tr>
    <c:forEach items="${donations}" var="donation">
        <tr>
            <td><c:out value="${donation.quantity}"/></td>
            <td><c:out value="${donation.institution.name}"/></td>
            <td><c:out value="${donation.pickUpDate}"/></td>
            <td><c:out value="${donation.pickUpTime}"/></td>
            <td><c:out value="${donation.pickUpComment}"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
