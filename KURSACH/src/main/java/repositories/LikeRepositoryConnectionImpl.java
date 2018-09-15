package repositories;

import lombok.SneakyThrows;
import mappers.RowMapper;
import models.Like;
import java.sql.*;
import java.time.LocalTime;
import java.util.Optional;

public class LikeRepositoryConnectionImpl implements LikeRepository {
    private Connection connection;

    //language=SQL
    private static final String SQL_INSERT_QUERY = "insert into like_table(user_id, target_id, date) " +
            "values (?, ?, ?);";
    //language=SQL
    private static final String SQL_UPDATE_QUERY = "update like_table set user_id = ?, target_id = ?, date = ? where id = ?";


    public LikeRepositoryConnectionImpl(Connection connection) {
        this.connection = connection;
    }

    private RowMapper<Like> likeRowMapper = new RowMapper<Like>() {
        @Override
        @SneakyThrows
        public Like rowMap(ResultSet resultSet) {
            return Like.builder()
                    .from_id(resultSet.getLong("user_id"))
                    .target_id(resultSet.getLong("target_id"))
                    .date(resultSet.getTime("date"))
                    .build();
        }
    };

    @Override
    @SneakyThrows
    public void like(Long user_to, Long user_from) {
        PreparedStatement statement = connection.prepareStatement(SQL_INSERT_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setLong(1, user_from);
        statement.setLong(2, user_to);
        statement.setTime(3, Time.valueOf(LocalTime.ofNanoOfDay(System.currentTimeMillis())));
        statement.executeUpdate();
    }

    @Override
    @SneakyThrows
    public Optional<Like> read(Long id) {
        Statement statement = connection.createStatement();
        ResultSet resultSet =
                statement.executeQuery("SELECT * FROM like_table WHERE id = " + id);
        resultSet.next();
        return Optional.of(likeRowMapper.rowMap(resultSet));
    }

    @Override
    public void create(Like model) {

    }

    @Override
    @SneakyThrows
    public void delete(Long id) {
        Statement statement = connection.createStatement();
        String query = "delete from like_table where id =" + id;
        System.out.println(query);
        int affectedRows = statement.executeUpdate(query);
        System.out.println(affectedRows);
    }

    @Override
    @SneakyThrows
    public void update(Long id, Like model) {
        PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setLong(1, model.getFrom_id());
        statement.setLong(2, model.getTarget_id());
        statement.setTime(3, model.getDate());
        statement.setLong(4, id);
        statement.executeUpdate();
        ResultSet resultSet = statement.getGeneratedKeys();
        while (resultSet.next()) {
            model.setId(resultSet.getLong("id"));
        }
    }
}
