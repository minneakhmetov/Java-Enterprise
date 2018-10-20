package repositories;

import lombok.*;
import mappers.RowMapper;
import models.User;

import java.sql.*;
import java.util.*;


public class UserRepositoryConnectionImpl implements UserRepository {

    private Connection connection;

    //language=SQL
    private static final String SQL_INSERT_QUERY = "insert into active_users(first_name, last_name, username, hash_password) " +
            "values (?, ?, ?, ?);";
    //language=SQL
    private static final String SQL_UPDATE_QUERY = "update active_users set first_name = ?, last_name = ?, username = ?, hash_password = ? where id = ?";

    public UserRepositoryConnectionImpl(Connection connection) {
        this.connection = connection;
    }

    private RowMapper<User> userRowMapper = new RowMapper<User>() {
        @Override
        @SneakyThrows
        public User rowMap(ResultSet resultSet) {
            return User.builder()
                    .username(resultSet.getString("username"))
                    .hashPassword(resultSet.getString("hashpassword"))
                    .firstName(resultSet.getString("first_name"))
                    .lastName(resultSet.getString("last_name"))
                    .id(resultSet.getLong("id"))
                    .build();

        }
    };

    @Override
    public List<User> findAllByFirstName(String firstName) {
        return null;
    }

    @Override
    @SneakyThrows
    public Optional<User> read(Long id) {
        Statement statement = connection.createStatement();
        ResultSet resultSet =
                statement.executeQuery("SELECT * FROM active_users WHERE id = " + id);
        resultSet.next();
        return Optional.of(userRowMapper.rowMap(resultSet));

    }

    @Override
    @SneakyThrows
    public void create(User model) {
//            PreparedStatement statement = connection.prepareStatement(SQL_INSERT_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
//            connection.setAutoCommit(false);
//            statement.setString(1, model.getFirstName());
//            statement.setString(2, model.getLastName());
//            statement.setString(3, model.getUsername());
//            statement.setString(4, model.getHashPassword());
//            statement.executeUpdate();
//            connection.commit();
//            connection.setAutoCommit(true);
        PreparedStatement statement = connection.prepareStatement(SQL_INSERT_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setString(1, model.getFirstName());
        statement.setString(2, model.getLastName());
        statement.setString(3, model.getUsername());
        statement.setString(4, model.getHashPassword());
        statement.executeUpdate(); // сука 2 часа потратил
        ResultSet resultSet = statement.getGeneratedKeys();
        while (resultSet.next()) {
            model.setId(resultSet.getLong("id"));
        }
    }

    @Override
    @SneakyThrows
    public void delete(Long id) {
        Statement statement = connection.createStatement();
        String query = "delete from active_users where id =" + id;
        System.out.println(query);
        int affectedRows = statement.executeUpdate(query);
        System.out.println(affectedRows);
    }

    @Override
    @SneakyThrows
    public void update(Long id, User model) {
        PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setString(1, model.getFirstName());
        statement.setString(2, model.getLastName());
        statement.setString(3, model.getUsername());
        statement.setString(4, model.getHashPassword());
        statement.setLong(5, id);
        statement.executeUpdate();
        ResultSet resultSet = statement.getGeneratedKeys();
        while (resultSet.next()) {
            model.setId(resultSet.getLong("id"));
        }
    }

    @Override
    @SneakyThrows
    public List<User> findAll() {
        Statement statement = connection.createStatement();
        ResultSet resultSet =
                statement.executeQuery("SELECT * FROM active_users");
        List<User> users = new ArrayList<>();
        while (resultSet.next()) {
            User newUser = userRowMapper.rowMap(resultSet);
            users.add(newUser);
        }
        return users;
    }
}



