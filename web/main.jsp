<%--
  Created by IntelliJ IDEA.
  User: Migel
  Date: 31.10.2020
  Time: 0:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach var="book" items="${bookList}">
    <c:out value="${book.name}"/>
    <c:out value="${book.author}"/>
</c:forEach>

<c:forEach var="user" items="${userList}">
    <c:out value="${user.name}"/>
    <c:out value="${user.password}"/>
</c:forEach>

<c:forEach var="book" items="${borList}">
    <c:out value="${book.name}"/>
    <c:out value="${book.author}"/>
    <c:out value="${book.borrowedTime}"/>
    <c:out value="${book.returnTime}"/>
</c:forEach>
</body>
</html>
