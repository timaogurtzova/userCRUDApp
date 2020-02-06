package dao;

import exeption.DBException;
import model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDAO implements UserDAO {

    private Connection connection;

    UserJdbcDAO (Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<User> getAllUser() throws DBException {
        String sql = "SELECT id, name, age, password, city FROM users";
        List<User> users = null;
        try (Statement statement = connection.createStatement()) {
            users = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String password = resultSet.getString("password");
                String city = resultSet.getString("city");
                User user = new User(id, name, age, password, city);
                users.add(user);
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new DBException(e);
        }
        return users;
    }

    @Override
    public User getUserById(long id) throws DBException {
        String sql = "SELECT name, age, password, city FROM users WHERE id = ?";
        User user = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String password = resultSet.getString("password");
                String city = resultSet.getString("city");
                user = new User(id, name, age, password, city);
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new DBException(e);
        }
        return user;
    }

    @Override
    public boolean validateUser(long id, String name, String password) throws DBException {
        boolean validateOrNot = false;
        String sql = "SELECT id FROM users WHERE name = ? AND password = ? AND id = ?";
        User user = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, password);
            statement.setLong(3, id);
            ResultSet resultSet = statement.executeQuery();
            validateOrNot = resultSet.next();
            resultSet.close();
        } catch (SQLException e) {
            throw new DBException(e);
        }
        return validateOrNot;
    }

    @Override
    public long getCountUserThisCity(String city) throws DBException {
        long count = 0L;
        String sql = "SELECT COUNT (city) FROM users WHERE city = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, city);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new DBException(e);
        }
        return count;
    }

    @Override
    public void updateUserById(long id, User user) throws DBException {
        String sql = "UPDATE users SET name = ? , age = ?, password = ?, city = ?  WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setInt(2, user.getAge());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getCity());
            preparedStatement.setLong(5, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public void addUser(User user) throws DBException {
        String sql = "INSERT INTO users (name, age, password, city) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setInt(2, user.getAge());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getCity());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public void deleteUserById (long id) throws DBException {
        String sql = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

}
