<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>Students</title>
</head>
<body style="background-image: url('img/books.jpg');background-size: cover;">
<form method="post" action="LogoutServlet">
    <div class="backMainPage"><button type="submit">EXIT</button></div>
</form>
<div id="menu">
    <a href="Redirect.jsp"><button>Library</button></a>
</div>
<div class="page">
    <h2>Students</h2><br>
    <div class="container">
        <c:forEach var="student" items="${studentsList}">
            <div class="entity">
            <p>
                Name: <c:out value="${student.name}"/><br>
                Password: <c:out value="${student.password}"/>
            </p><br>
            <input style="width: 100%" type="hidden" name="studentName" value="${student.name}">
            <button type="submit" name="submit" value="deleteStudent" id="delete-button">Delete</button><br>
                <form method="post" action="${pageContext.request.contextPath}/servlet">
                    <h3>Update:</h3>
                    Name:<input style="width: 100%" type="text" name="studentName" value="${student.name}">
                    Password:<input style="width: 100%" type="password" name="password" value="${student.password}">
                    <button type="submit" name="submit" value="updateStudent">Update:</button>
                </form>
            </div>
        </c:forEach>
                <form method="post" action="${pageContext.request.contextPath}/servlet">
                    <h3>Add new student:</h3>
                    Name:<input style="width: 100%" type="text" name="studentName" value="">
                    Password:<input style="width: 100%" type="text" name="password" value="">
                    <button type="submit" name="submit" value="addStudent">Add:</button>
                </form>
    </div>
</div>
</body>
