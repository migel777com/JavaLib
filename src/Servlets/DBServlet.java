package Servlets;

import JavaClasses.Books;
import JavaClasses.DB;
import JavaClasses.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBServlet extends HttpServlet
{
    DB db = new DB();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String submit = request.getParameter("submit");
        switch (submit)
        {
            case "addBook":
            {
                String bookName = request.getParameter("bookName");
                String bookAuthor = request.getParameter("bookAuthor");
                int quant = Integer.parseInt(request.getParameter("quant"));

                int added = db.addBook(bookName, bookAuthor, quant);
                request.setAttribute("crud", "c"+added);
                break;
            }
            case "addStudent":
            {
                String studentName = request.getParameter("studentName");
                String password = request.getParameter("password");

                int added = db.addStudent(studentName, password);
                request.setAttribute("crud", "c"+added);
                break;
            }
            case "addBorrowedBook":
            {
                String bookName = request.getParameter("bookName");
                String studentName = request.getParameter("studentName");

                int added = db.addBorrowedBook(bookName, studentName);
                request.setAttribute("crud", "c"+added);
                doGet(request, response, studentName);
                break;
            }
            case "deleteBook":
            {
                String bookName = request.getParameter("bookName");
                int deleted = db.deleteBook(bookName);
                request.setAttribute("crud", "d"+deleted);
                break;
            }
            case "deleteStudent":
            {
                String studentName = request.getParameter("studentName");
                int deleted = db.deleteStudent(studentName);
                request.setAttribute("crud", "d"+deleted);
                break;
            }
            case "deleteBorrowedBook":
            {
                String bookName = request.getParameter("bookName");
                String studentName = request.getParameter("studentName");
                int deleted = db.deleteBorrowedBook(studentName, bookName);
                request.setAttribute("crud", "d"+deleted);
                break;
            }
            case "updateBook":
            {
                String bookName = request.getParameter("bookName");
                String bookAuthor = request.getParameter("bookAuthor");
                int quant = Integer.parseInt(request.getParameter("quant"));

                int updated = db.updateBook(bookName, bookAuthor, quant);
                request.setAttribute("crud", "u"+updated);
                break;
            }
            case "updateStudent":
            {
                String studentName = request.getParameter("studentName");
                String password = request.getParameter("password");

                int updated = db.updateStudent(studentName, password);
                request.setAttribute("crud", "u"+updated);
                break;
            }
            default:
            {
                request.setAttribute("crud", "s0");
                break;
            }
        }
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try
        {
            Connection connection = db.getConnection();
            if(connection != null) {
                ArrayList<Books> bookList = db.readBooks(connection);
                connection = db.getConnection();
                ArrayList<User> userList = db.readStudents(connection);
                connection = db.getConnection();
                ArrayList<Books> borList = db.readBorrowedBooks(connection, "admin");
                connection.close();
                //db.addBook("Story of the Great Alibek", "Baltabekov Galymzhan", 3);
                //db.addStudent("migel", "123456789");
                //db.addBorrowedBook("Story of the Great Alibek", "admin");
                //db.deleteStudent("enemy");
                //db.deleteBook("Bad Stories Collection");
                //db.deleteBorrowedBook("migel" , "Story of the Great Alibek");
                //db.updateBook("Story of the Great Alibek", "Baltabekov Galymzhan", 5);
                //db.updateStudent("migel", "123456789");
                request.setAttribute("bookList", bookList);
                request.setAttribute("userList" , userList);
                request.setAttribute("borList", borList);
            }
        }
        catch (SQLException exception)
        {
            exception.printStackTrace();
        }
        request.getRequestDispatcher("main.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response, String name) throws ServletException, IOException {
        try
        {
            Connection connection = db.getConnection();
            if(connection != null) {
                ArrayList<Books> bookList = db.readBooks(connection);
                connection = db.getConnection();
                ArrayList<User> userList = db.readStudents(connection);
                connection = db.getConnection();
                ArrayList<Books> borList = db.readBorrowedBooks(connection, name);
                connection.close();
                //db.addBook("Story of the Great Alibek", "Baltabekov Galymzhan", 3);
                //db.addStudent("migel", "123456789");
                //db.addBorrowedBook("Story of the Great Alibek", "admin");
                //db.deleteStudent("enemy");
                //db.deleteBook("Bad Stories Collection");
                //db.deleteBorrowedBook("migel" , "Story of the Great Alibek");
                //db.updateBook("Story of the Great Alibek", "Baltabekov Galymzhan", 5);
                //db.updateStudent("migel", "123456789");
                request.setAttribute("bookList", bookList);
                request.setAttribute("userList" , userList);
                request.setAttribute("borList", borList);
            }
        }
        catch (SQLException exception)
        {
            exception.printStackTrace();
        }
        request.getRequestDispatcher("main.jsp").forward(request, response);
    }
}

