package DAO;

import exception.StatementException;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJDBCDAO implements UserDao{
    private Connection connection;

    UserJDBCDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<User> getAllUsers() throws StatementException {
        try {
            String query = "SELECT * FROM Users";
            Statement statement = connection.createStatement();
            statement.execute(query);
            ResultSet resultSet = statement.getResultSet();
            List<User> userList = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("login"),
                        resultSet.getString("password"));
                userList.add(user);
            }
            resultSet.close();
            statement.close();
            return userList;
        } catch (SQLException sqle) {
            throw new StatementException(sqle);
        }
    }

    @Override
    public void addUser(String name, String login, String password) throws StatementException {
        try {
            String update = "INSERT INTO users (name, login, password) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, login);
            preparedStatement.setString(3, password);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException sqle) {
            throw new StatementException(sqle);
        }
    }

    @Override
    public void updateUser(String name, String login, String password) throws StatementException {
        try {
            String update = "UPDATE users SET name = ?, login = ?, password = ? WHERE login LIKE ?";
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, login);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, login);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException sqle) {
            throw new StatementException(sqle);
        }
    }

    @Override
    public void deleteUser(Integer id) throws StatementException {
        try {
            String update = "DELETE FROM Users WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException sqle) {
            throw new StatementException(sqle);
        }
    }

    //проверяет наличие юзера в базе по логину
    @Override
    public boolean checkUser(String login) throws StatementException {
        try {
            String query = "SELECT * FROM Users WHERE login  = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, login);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            //заменить
            return resultSet.next();
        } catch (SQLException sqle) {
            throw new StatementException(sqle);
        }
    }

    public void createTable() throws StatementException {
        try {
        String update = "CREATE TABLE if NOT EXISTS Users (id int auto_increment, name varchar(256), login varchar(256) unique, password varchar(256), primary key(id))";
        Statement statement = connection.createStatement();
        statement.executeUpdate(update);
        statement.close();
        } catch (SQLException sqle) {
            throw new StatementException(sqle);
        }
    }

    @Override
    public void dropTable() throws StatementException {
        try {
            String update = "DROP TABLE if EXISTS Users";
            Statement statement = connection.createStatement();
            statement.executeUpdate(update);
            statement.close();
        } catch (SQLException sqle) {
            throw new StatementException(sqle);
        }
    }
}
