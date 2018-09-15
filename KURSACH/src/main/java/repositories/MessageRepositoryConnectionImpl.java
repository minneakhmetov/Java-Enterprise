package repositories;

import lombok.SneakyThrows;
import mappers.RowMapper;
import models.Message;
import org.postgresql.util.PSQLException;
import java.sql.*;
import java.time.LocalTime;
import java.util.Optional;

public class MessageRepositoryConnectionImpl implements MessageRepository {

    private Connection connection;

    //language=SQL
    private static final String SQL_INSERT_QUERY = "insert into message_table(chat_id, to_id, from_id, text, date) " +
            "values (?, ?, ?, ?, ?);";
    //language=SQL
    private static final String SQL_UPDATE_QUERY = "update message_table set chat_id = ?, to_id = ?, from_id = ?, text = ?, date = ? where id = ?";

    public MessageRepositoryConnectionImpl(Connection connection) {
        this.connection = connection;
    }

    private RowMapper<Message> messageRowMapper = new RowMapper<Message>() {
        @Override
        @SneakyThrows
        public Message rowMap(ResultSet resultSet) {
            return Message.builder()
                    .id(resultSet.getLong("id"))
                    .chat_id(resultSet.getLong("chat_id"))
                    .to_id(resultSet.getLong("to_id"))
                    .from_id(resultSet.getLong("from_id"))
                    .text(resultSet.getString("text"))
                    .date(resultSet.getTime("date"))
                    .build();

        }
    };

    @Override
    @SneakyThrows
    public void write(Long user_to, Long user_from, String text) {
        PreparedStatement statement = connection.prepareStatement(SQL_INSERT_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setLong(1, chatId(user_to, user_from));
        statement.setLong(2, user_to);
        statement.setLong(3, user_from);
        statement.setString(4, text);
        statement.setTime(5, Time.valueOf(LocalTime.ofNanoOfDay(System.nanoTime())));
        statement.executeUpdate();
        //ResultSet resultSet = statement.getGeneratedKeys();

    }

    @Override
    @SneakyThrows
    public Optional<Message> read(Long id) {
        Statement statement = connection.createStatement();
        ResultSet resultSet =
                statement.executeQuery("SELECT * FROM message_table WHERE id = " + id);
        resultSet.next();
        return Optional.of(messageRowMapper.rowMap(resultSet));
    }

    @Override
    public void create(Message model) {
        return; //nety
    }

    @Override
    @SneakyThrows
    public void delete(Long id) {
        Statement statement = connection.createStatement();
        String query = "delete from message_table where id =" + id;
        System.out.println(query);
        int affectedRows = statement.executeUpdate(query);
        System.out.println(affectedRows);
    }

    @Override
    @SneakyThrows
    public void update(Long id, Message model) {
        PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setLong(1, model.getChat_id());
        statement.setLong(2, model.getTo_id());
        statement.setLong(3, model.getFrom_id());
        statement.setString(4, model.getText());
        statement.setTime(5, model.getDate());
        statement.setLong(6, id);
        statement.executeUpdate();
        ResultSet resultSet = statement.getGeneratedKeys();
        while (resultSet.next()) {
            model.setId(resultSet.getLong("id"));
        }
    }
    @SneakyThrows
    private Long chatId(Long user_to, Long user_from){
        //language=SQL
        String SELECT_QUERY = "SELECT id FROM chat where (from_id = " + user_from + " and to_id = " + user_to + ") or (from_id = " + user_to + " and to_id = " + user_from + ");";
        //language=SQL
        String INSERT_QUERY = "INSERT into chat(from_id, to_id) values (?, ?)";
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
