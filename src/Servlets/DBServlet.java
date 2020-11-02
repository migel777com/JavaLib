package Servlets;

import JavaClasses.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

public class DBServlet extends HttpServlet
{
    DB db = new DB();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String submit = request.getParameter("submit");

        Connection connection = db.getConnection();
        ArrayList<Books> books = db.readBooks(connection);
        connection = db.getConnection();
        ArrayList<User> users = db.readStudents(connection);
        connection = db.getConnection();
        ArrayList<Books> borrowed = db.readBorrowedBooks(connection);
        int error_code=0;


        switch (submit)
        {
            case "addBook":
            {
                String bookName = request.getParameter("bookName");
                String bookAuthor = request.getParameter("bookAuthor");
                int quant = Integer.parseInt(request.getParameter("quant"));
                String image = request.getParameter("bookImage");

                int added = db.addBook(bookName, bookAuthor, quant, image);
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

                for (Books book: borrowed) {
                    if (book.getName().equals(bookName)) {
                        error_code = 1;
                        doGet(request, response, error_code);
                    }
                }
                int deleted = db.deleteBook(bookName);
                request.setAttribute("crud", "d"+deleted);
                break;
            }
            case "deleteStudent":
            {
                String studentName = request.getParameter("studentName");

                for (Books book: borrowed) {
                    if (book.getUsername().equals(studentName)) {
                        error_code = 1;
                        doGet(request, response, error_code);
                    }
                }

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
                String img = request.getParameter("image");

                int updated = db.updateBook(bookName, bookAuthor, quant, img);
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
                db.addBorrowedBook("Story of the Great Alibek", "admin");
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
        request.getRequestDispatcher("Library.jsp").forward(request, response);
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
        request.getRequestDispatcher("Library.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response, int error_code) throws ServletException, IOException {
        try
        {
            Connection connection = db.getConnection();
            if(connection != null) {
                ArrayList<Books> bookList = db.readBooks(connection);
                connection = db.getConnection();
                ArrayList<User> userList = db.readStudents(connection);
                connection = db.getConnection();
                ArrayList<Books> borList = db.readBorrowedBooks(connection);
                connection.close();
                request.setAttribute("bookList", bookList);
                request.setAttribute("userList" , userList);
                request.setAttribute("borList", borList);
            }
        }
        catch (SQLException exception)
        {
            exception.printStackTrace();
        }

        request.getRequestDispatcher("error.jsp").forward(request, response);
    }
}