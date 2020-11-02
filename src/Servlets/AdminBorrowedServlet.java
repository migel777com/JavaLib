package Servlets;

import JavaClasses.Books;
import JavaClasses.DB;
import JavaClasses.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/AdminBorrowedServlet")
public class AdminBorrowedServlet extends HttpServlet {
    DB db = new DB();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String logged= null;
        Cookie[] cookies = request.getCookies();
        if(cookies !=null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("loggedin")) logged= cookie.getValue();
            }
        }

        try
        {
            Connection connection = db.getConnection();
            if(connection != null && logged != null && logged.equals("admin")) {
                ArrayList<Books> borList = db.readBorrowedBooks(connection);
                connection.close();
                request.setAttribute("allborrowedlist", borList);
            }
        }
        catch (SQLException exception)
        {
            exception.printStackTrace();
        }
        request.getRequestDispatcher("AllBorrowed.jsp").forward(request, response);
    }
}