package Servlets;

import JavaClasses.DB;
import JavaClasses.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/StudentsServlet")
public class StudentsServlet extends HttpServlet {
    DB db = new DB();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try
        {
            Connection connection = db.getConnection();
            if(connection != null) {
                ArrayList<User> studentsList = db.readStudents(connection);
                connection.close();
                request.setAttribute("studentsList", studentsList);
            }
        }
        catch (SQLException exception)
        {
            exception.printStackTrace();
        }
        request.getRequestDispatcher("Students.jsp").forward(request, response);
    }
}