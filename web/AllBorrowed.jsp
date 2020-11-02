<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>Borrowed books</title>
</head>
<body style="background-image: url('img/books.jpg');background-size: cover;">
<form method="post" action="LogoutServlet">
    <div class="backMainPage"><button type="submit">EXIT</button></div>
</form>
<div id="menu">
    <a href="Redirect.jsp"><button>Library</button></a>
</div>
<div class="page">
    <h2>Borrowed books</h2><br>
    <div class="container">
        <c:forEach var="book" items="${allborrowedlist}">
            <div class="entity">
            <p>
                <c:out value="${book.username}"/>:<br>
                <c:out value="${book.name}"/> by <c:out value="${book.author}"/><br>
                Borrowed time: <c:out value="${book.borrowedTime}"/><br>
                Return time: <c:out value="${book.returnTime}"/>
            </p>
                <form method="post" name="borrow-form" action="${pageContext.request.contextPath}/servlet">
                <input style="width: 100%" type="hidden" name="studentName" value="${book.username}">
                <input style="width: 100%" type="hidden" name="bookName" value="${book.name}">
                <button type="submit" name="submit" value="deleteBorrowedBook" id="delete-button">Delete</button><br>
                </form>
            </div>
        </c:forEach>
    </div>
</div>
</body>
