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

    public ArrayList<Book> read(Connection connection)
    {
        ArrayList<Book> bookList = new ArrayList();
        try
        {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM books");
            ResultSetMetaData metaData = resultSet.getMetaData();
            int numberOfColumns = metaData.getColumnCount();
            Book book;
            while (resultSet.next())
            {
                String[] bookFields = new String[numberOfColumns];
                for(int a=1; a<=numberOfColumns; a++)
                {
                    bookFields[a-1] = resultSet.getObject(a).toString();
                }
                book = new Book(bookFields);
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

    protected int addBook(String name, String author, int quant)
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int added = 0;
        try
        {
            connection = getConnection();
            preparedStatement = connection.prepareStatement("insert into books (name, author, quantity) values (?, ?, ?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, author);
            preparedStatement.setString(3, Integer.toString(quant));
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

    protected int addStudent(String name, String password)
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int added = 0;
        try
        {
            connection = getConnection();
            preparedStatement = connection.prepareStatement("insert into students (name, password) values (?, ?)");
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


    protected int delete(String id)
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int deleted = 0;
        try
        {
            connection = getConnection();
            preparedStatement = connection.prepareStatement("delete from books where books.book_id = ?");
            preparedStatement.setString(1, id);
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

    protected int update(int id, String name, String author, String movieName, String bookURL, String movieURL, String imageURL)
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int updated = 0;
        try
        {
            connection = getConnection();
            preparedStatement = connection.prepareStatement("update books set book_name=?, book_author=?, book_poster=? where book_id=?");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, author);
            preparedStatement.setString(3, imageURL);
            preparedStatement.setInt(4, id);
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
