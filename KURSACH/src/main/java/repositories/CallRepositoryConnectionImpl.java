package repositories;

import lombok.SneakyThrows;
import mappers.RowMapper;
import models.Call;
import org.postgresql.util.PSQLException;

import java.sql.*;
import java.time.LocalTime;
import java.util.Optional;
import java.util.Random;

public class CallRepositoryConnectionImpl implements CallRepository {

    private Connection connection;
    Random random = new Random();

    //language=SQL
    private static final String SQL_INSERT_QUERY = "insert into call_table(call_id, to_id, from_id, duration, date) " +
            "values (?, ?, ?, ?, ?);";
    //language=SQL
    private static final String SQL_UPDATE_QUERY = "update call_table set call_id = ?, to_id = ?, from_id = ?, duration = ?, date = ? where id = ?";

    public CallRepositoryConnectionImpl(Connection connection) {
        this.connection = connection;
    }

    private RowMapper<Call> callRowMapper = new RowMapper<Call>() {
        @Override
        @SneakyThrows
        public Call rowMap(ResultSet resultSet) {
            return Call.builder()
                    .id(resultSet.getLong("id"))
                    .call_id(resultSet.getLong("call_id"))
                    .to_id(resultSet.getLong("to_id"))
                    .from_id(resultSet.getLong("from_id"))
                    .duration(resultSet.getInt("duration"))
                    .date(resultSet.getTime("date"))
                    .build();
        }
    };


    @Override
    @SneakyThrows
    public Optional<Call> read(Long id) {
        Statement statement = connection.createStatement();
        ResultSet resultSet =
                statement.executeQuery("SELECT * FROM call_table WHERE id = " + id);
        resultSet.next();
        return Optional.of(callRowMapper.rowMap(resultSet));
    }

    @Override
    public void create(Call model) {

    }

    @Override
    @SneakyThrows
    public void delete(Long id) {
        Statement statement = connection.createStatement();
        String query = "delete from call_table where id =" + id;
        System.out.println(query);
        int affectedRows = statement.executeUpdate(query);
        System.out.println(affectedRows);
    }

    @Override
    @SneakyThrows
    public void update(Long id, Call model) {
//        PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
//        statement.setLong(1, model.getCall_id());
//        statement.setLong(2, model.getTo_id());
//        statement.setLong(3, model.getFrom_id());
//        statement.setInt(4, model.getDuration());
//        statement.setTime(5, model.getDate());
//        statement.setLong(6, id);
//        statement.executeUpdate();
//        ResultSet resultSet = statement.getGeneratedKeys();
//        while (resultSet.next()) {
//            model.setId(resultSet.getLong("id"));
//        }
    }

    @Override
    @SneakyThrows
    public void call(Long user_to, Long user_from) {
        PreparedStatement statement = connection.prepareStatement(SQL_INSERT_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setLong(1, callId(user_to, user_from));
        statement.setLong(2, user_to);
        statement.setLong(3, user_from);
        statement.setInt(4, random.nextInt(50));
        statement.setTime(5, Time.valueOf(LocalTime.ofNanoOfDay(System.nanoTime())));
        statement.executeUpdate();
    }

    @SneakyThrows
    private Long callId(Long user_to, Long user_from){
        //language=SQL
        String SELECT_QUERY = "SELECT id FROM call_chat where (from_id = " + user_from + " and to_id = " + user_to + ") or (from_id = " + user_to + " and to_id = " + user_from + ");";
        //language=SQL
        String INSERT_QUERY = "INSERT into call_chat(from_id, to_id) values (?, ?)";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SELECT_QUERY);
        resultSet.next();
        try {
            return resultSet.getLong("id");

        } catch (PSQLException e){
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, user_from);
            preparedStatement.setLong(2, user_to);
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            return resultSet.getLong("id");
        }
    }

}
