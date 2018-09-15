package repositories;

import lombok.SneakyThrows;
import mappers.RowMapper;
import models.Post;

import java.sql.*;
import java.time.LocalTime;
import java.util.Optional;

public class PostRepositoryConnectionImpl implements PostRepository {

    private Connection connection;

    //language=SQL
    private static final String SQL_INSERT_QUERY = "insert into post_table(user_id, text, date) " +
            "values (?, ?, ?);";
    //language=SQL
    private static final String SQL_UPDATE_QUERY = "update post_table set user_id = ?, text = ?, date = ? where id = ?";

    public PostRepositoryConnectionImpl(Connection connection) {
        this.connection = connection;
    }

    private RowMapper<Post> postRowMapper = new RowMapper<Post>() {
        @Override
        @SneakyThrows
        public Post rowMap(ResultSet resultSet) {
            return Post.builder()
                    .date(resultSet.getTime("date"))
                    .text(resultSet.getString("text"))
                    .user_id(resultSet.getLong("user_id"))
                    .id(resultSet.getLong("id"))
                    .build();

        }
    };

    @Override
    @SneakyThrows
    public Optional<Post> read(Long id) {
        Statement statement = connection.createStatement();
        ResultSet resultSet =
                statement.executeQuery("SELECT * FROM post_table WHERE id = " + id);
        resultSet.next();
        return Optional.of(postRowMapper.rowMap(resultSet));
    }

    @Override
    @SneakyThrows
    public void create(Post model) {
        PreparedStatement statement = connection.prepareStatement(SQL_INSERT_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setLong(1, model.getUser_id());
        statement.setString(2, model.getText());
        statement.setTime(3, model.getDate());
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
        String query = "delete from post_table where id =" + id;
        System.out.println(query);
        int affectedRows = statement.executeUpdate(query);
        System.out.println(affectedRows);

    }

    @Override
    @SneakyThrows
    public void update(Long id, Post model) {
        PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setLong(1, model.getUser_id());
        statement.setString(2, model.getText());
        statement.setTime(3, model.getDate());
        statement.setLong(4, id);
        statement.executeUpdate();
        ResultSet resultSet = statement.getGeneratedKeys();
        while (resultSet.next()) {
            model.setId(resultSet.getLong("id"));
        }
    }

    @Override
    @SneakyThrows
    public void post(String text, Long id_user) {
        PreparedStatement statement = connection.prepareStatement(SQL_INSERT_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setLong(1, id_user);
        statement.setString(2, text);
        statement.setTime(3, Time.valueOf(LocalTime.ofNanoOfDay(System.nanoTime())));
        statement.executeUpdate(); // сука 2 часа потратил
//        ResultSet resultSet = statement.getGeneratedKeys();
//        while (resultSet.next()) {
//            model.setId(resultSet.getLong("id"));
//        }
    }
}
