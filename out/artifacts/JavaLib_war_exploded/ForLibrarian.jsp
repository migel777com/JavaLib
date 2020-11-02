<%--
  Created by IntelliJ IDEA.
  User: ALSER
  Date: 01.11.2020
  Time: 23:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of students</title>
</head>
<body>
<c:forEach var="user" items="${userList}">
    <c:out value="${user.name}"/>
    <c:out value="${user.password}"/>
    <br>
</c:forEach>
</body>
</html>
