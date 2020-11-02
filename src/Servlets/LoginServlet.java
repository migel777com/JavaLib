package Servlets;


import JavaClasses.DB;
import JavaClasses.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    DB db = new DB();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String loginAfter = null;
        String passwordAfter = null;
        //Cookie[] cookies = request.getCookies();

        Connection connection = db.getConnection();
        ArrayList<User> userList = new ArrayList<>();
        userList = db.readStudents(connection);
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User user = null;

        for (User item : userList) {
            if (item.getName().equals(login)) {
                user = item;
                break;
            }
        }

        assert user != null;
        if(user.getPassword().equals(password)){

            Cookie loggedin = new Cookie("loggedin", login);
            loggedin.setMaxAge(30*60);
            response.addCookie(loggedin);
            HttpSession session = request.getSession();
            session.setMaxInactiveInterval(60);
            response.sendRedirect("Redirect.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}