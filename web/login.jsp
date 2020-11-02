<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="js/login-register.js"></script>
    <h1 style="background-color: antiquewhite; border-radius: 3px;">Your ID</h1><br>
    <div class="msg">
        <span id="message" class="error"></span>
    </div>
    <form method="post" name="log-form" action="LoginServlet">
            <div id="log-block">
                <div>
                    <b><label for="login">Login:</label></b>
                </div>
                <div>
                    <input type="text" id="login" name="login" placeholder="login">
                </div>
                <div>
                    <b><label for="password">Password:</label></b>
                </div>
                <div>
                    <input type="password" id="password" name="password" placeholder="password">
                </div>
                <div id="log-block-button">
                    <input type="submit" id="log-button" name="log-button" value="Login">
                </div>
            </div>
    </form>