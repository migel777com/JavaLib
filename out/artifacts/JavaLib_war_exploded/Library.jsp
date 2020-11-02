<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>Library</title>
</head>
<body style="background-image: url('img/books.jpg');background-size: cover;">
<form method="post" action="LogoutServlet">
    <div class="backMainPage"><button type="submit">EXIT</button></div>
</form>
<% String logged= null;
    Cookie[] cookies = request.getCookies();
    if(cookies !=null){
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("loggedin")) logged= cookie.getValue();
        }
    }
    request.setAttribute("logged", logged);
%>
<c:set var="loggedNew" value="${logged}"/>
<div id="menu">
    <c:if test = "${loggedNew!='admin'}">
    <form method="get" action="BorrowedServlet"><button type="submit">Borrowed books</button></form>
    </c:if>
    <c:if test = "${loggedNew=='admin'}">
        <form method="get" action="StudentsServlet"><button type="submit">Students</button></form>
        <form method="get" action="AdminBorrowedServlet"><button type="submit">All borrowed books</button></form>
    </c:if>
</div>
<div class="page">
      <h2>Books collection</h2><br>
        <div class="container">
            <c:forEach var="book" items="${bookList}">
                <div class="entity">
                    <form method="post" name="borrow-form" action="${pageContext.request.contextPath}/servlet">
                    <p><img class="cover" src="<c:url value="${book.image}"/>"/></p>
                    <p><c:out value="${book.name}"/></p>
                    <p>by <c:out value="${book.author}"/></p><br>
                    <div class="borrow-button-block">
                        <input style="width: 100%" type="hidden" name="bookName" value="<c:out value="${book.name}"/>">
                        <c:if test = "${loggedNew=='admin'}">
                            <input style="width: 100%" type="text" name="studentName" placeholder="student login">
                            <button type="submit" name="submit" value="addBorrowedBook" id="borrow-button">Borrow</button> -
                            <button type="submit" name="submit" value="deleteBook" id="delete-button">Delete</button> <br>
                            <input style="width: 100%" type="text" name="bookName" value="${book.name}">
                            <input style="width: 100%" type="text" name="bookAuthor" value="${book.author}">
                            <input style="width: 100%" type="number" name="quant" value="${book.quantity}">
                            <input style="width: 100%" type="text" name="image" value="${book.image}">
                            <button type="submit" name="submit" value="updateBook" id="update-button">Update</button>
                        </c:if>
                    </div>
                    </form>
                </div>
            </c:forEach>
            <c:if test = "${loggedNew=='admin'}">
            <div>
                <form method="post" action="${pageContext.request.contextPath}/servlet">
                <h3>Add new book:</h3>
                Name:<input style="width: 100%" type="text" name="bookName" value="">
                Author:<input style="width: 100%" type="text" name="bookAuthor" value="">
                Quantity:<input style="width: 100%" type="number" name="quant" value="">
                Image url:<input style="width: 100%" type="text" name="bookImage" value="">
                <button type="submit" name="submit" value="addBook">Add:</button>
                </form>
            </div>
            </c:if>
        </div>
</div>
</body>
