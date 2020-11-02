package JavaClasses;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

public class DB {

    public Connection getConnection()
    {
        Context initialContext = null;
        Connection connection = null;
        try
        {
            initialContext = new InitialContext();
            Context envCtx = (Context)initialContext.lookup("java:comp/env");
            DataSource ds = (DataSource)envCtx.lookup("jdbc/TestDB");
            connection = ds.getConnection();
        }
        catch (NamingException | SQLException e)
        {
            e.printStackTrace();
        }
        return connection;
    }

    public ArrayList<Books> readBooks(Connection connection)
    {
        ArrayList<Books> bookList = new ArrayList<>();
        try
        {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM books WHERE quantity > 0");
            ResultSetMetaData metaData = resultSet.getMetaData();
            int numberOfColumns = metaData.getColumnCount();
            Books book;
            while (resultSet.next())
            {
                String[] bookFields = new String[numberOfColumns];
                for(int a=1; a<=numberOfColumns; a++)
                {
                    bookFields[a-1] = resultSet.getObject(a).toString();
                }
                book = new Books(bookFields);
                bookList.add(book);
            }
            resultSet.close();
            connection.close();
            statement.close();
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return bookList;
    }

    public ArrayList<User> readStudents(Connection connection)
    {
        ArrayList<User> userList = new ArrayList<>();
        try
        {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            ResultSetMetaData metaData = resultSet.getMetaData();
            int numberOfColumns = metaData.getColumnCount();
            User user;
            while (resultSet.next())
            {
                String[] userFields = new String[numberOfColumns];
                for(int a=1; a<=numberOfColumns; a++)
                {
                    userFields[a-1] = resultSet.getObject(a).toString();
                }
                user = new Student(userFields);
                userList.add(user);
            }
            resultSet.close();
            connection.close();
            statement.close();
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return userList;
    }


    public ArrayList<Books> readBorrowedBooks(Connection connection, String name)
    {
        ArrayList<Books> bookList = new ArrayList<>();
        try
        {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT b.book_id, b.name, b.author, b.quantity, borrowed_time, return_time, b.image, u.username " +
                    "FROM borrowed bor JOIN users u ON bor.user_id = u.user_id JOIN books b ON bor.book_id = b.book_id " +
                    "WHERE u.username = '" + name + "'");
            ResultSetMetaData metaData = resultSet.getMetaData();
            int numberOfColumns = metaData.getColumnCount();
            Books book;
            while (resultSet.next())
            {
                String[] bookFields = new String[numberOfColumns];
                for(int a=1; a<=numberOfColumns; a++)
                {
                    bookFields[a-1] = resultSet.getObject(a).toString();
                }
                book = new Books(bookFields, "withDate");
                bookList.add(book);
            }
            resultSet.close();
            connection.close();
            statement.close();
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return bookList;
    }

    public ArrayList<Books> readBorrowedBooks(Connection connection)
    {
        ArrayList<Books> bookList = new ArrayList<>();
        try
        {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT b.book_id, b.name, b.author, b.quantity, borrowed_time, return_time, b.image, u.username " +
                    "FROM borrowed bor JOIN users u ON bor.user_id = u.user_id JOIN books b ON bor.book_id = b.book_id ");
            ResultSetMetaData metaData = resultSet.getMetaData();
            int numberOfColumns = metaData.getColumnCount();
            Books book;
            while (resultSet.next())
            {
                String[] bookFields = new String[numberOfColumns];
                for(int a=1; a<=numberOfColumns; a++)
                {
                    bookFields[a-1] = resultSet.getObject(a).toString();
                }
                book = new Books(bookFields, "withDate");
                bookList.add(book);
            }
            resultSet.close();
            connection.close();
            statement.close();
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return bookList;
    }

    public int addBook(String name, String author, int quant, String image)
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int added = 0;
        try
        {
            connection = getConnection();
            preparedStatement = connection.prepareStatement("insert into books (name, author, quantity, image) values (?, ?, ?, ?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, author);
            preparedStatement.setInt(3, quant);
            preparedStatement.setString(4, image);
            added = preparedStatement.executeUpdate();
            connection.close();
            preparedStatement.close();
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return added;
    }

    public int addStudent(String name, String password)
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int added = 0;
        try
        {
            connection = getConnection();
            preparedStatement = connection.prepareStatement("insert into users (username, password, isAdmin) values (?, ?, false)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);
            added = preparedStatement.executeUpdate();
            connection.close();
            preparedStatement.close();
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return added;
    }

    public int decrementBook(String name)
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int updated = 0;
        try
        {
            connection = getConnection();
            preparedStatement = connection.prepareStatement("UPDATE books SET quantity = quantity - 1 WHERE name = ?");
            preparedStatement.setString(1, name);
            updated = preparedStatement.executeUpdate();
            connection.close();
            preparedStatement.close();
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return updated;
    }

    public int addBorrowedBook(String bookName, String studentName)
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int added = 0;
        try
        {
            connection = getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO borrowed \n" +
                    "    SELECT u.user_id, b.book_id \n" +
                    "    FROM users u CROSS JOIN books b\n" +
                    "    WHERE u.username = ? and b.name = ?");
            preparedStatement.setString(1, studentName);
            preparedStatement.setString(2, bookName);
            added = preparedStatement.executeUpdate();
            connection.close();
            preparedStatement.close();
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        if (added>0) decrementBook(bookName);
        return added;
    }


    public int deleteStudent(String name)
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int deleted = 0;
        try
        {
            connection = getConnection();
            preparedStatement = connection.prepareStatement("delete from users where username = ?");
            preparedStatement.setString(1, name);
            deleted = preparedStatement.executeUpdate();
            connection.close();
            preparedStatement.close();
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return deleted;
    }

    public int deleteBook(String name)
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int deleted = 0;
        try
        {
            connection = getConnection();
            preparedStatement = connection.prepareStatement("delete from books where name = ?");
            preparedStatement.setString(1, name);
            deleted = preparedStatement.executeUpdate();
            connection.close();
            preparedStatement.close();
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return deleted;
    }

    public int deleteBorrowedBook(String username, String bookname)
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int deleted = 0;
        try
        {
            connection = getConnection();
            preparedStatement = connection.prepareStatement("delete from borrowed where " +
                    "user_id IN (SELECT user_id FROM users WHERE username = ?) AND " +
                    "book_id IN (SELECT book_id FROM books WHERE name = ?)");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, bookname);
            deleted = preparedStatement.executeUpdate();
            connection.close();
            preparedStatement.close();
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return deleted;
    }

    public int updateBook(String name, String author, int quant, String image)
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int updated = 0;
        try
        {
            connection = getConnection();
            preparedStatement = connection.prepareStatement("update books set name=?, author=?, quantity=?, image=? where name=?");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, author);
            preparedStatement.setInt(3, quant);
            preparedStatement.setString(4, image);
            preparedStatement.setString(5, name);
            updated = preparedStatement.executeUpdate();
            connection.close();
            preparedStatement.close();
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return updated;
    }

    public int updateStudent(String name, String password)
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int updated = 0;
        try
        {
            connection = getConnection();
            preparedStatement = connection.prepareStatement("update users set username=?, password=? where username=?");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, name);
            updated = preparedStatement.executeUpdate();
            connection.close();
            preparedStatement.close();
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return updated;
    }

}
