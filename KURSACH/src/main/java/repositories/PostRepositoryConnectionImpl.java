package repositories;

import mappers.RowMapper;
import models.Post;
import models.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class PostRepositoryConnectionImpl implements PostRepository {

    private Connection connection;

    //language=SQL
    private static final String SQL_INSERT_QUERY = "insert into active_users(first_name, last_name, username, hashpassword) " +
            "values (?, ?, ?, ?);";
    //language=SQL
    private static final String SQL_UPDATE_QUERY = "update active_users set first_name = ?, last_name = ?, username = ?, hashpassword = ? where id = ?";

    public PostRepositoryConnectionImpl(Connection connection) {
        this.connection = connection;
    }

    private RowMapper<Post> userRowMapper = new RowMapper<Post>() {
        @Override
        public Post rowMap(ResultSet resultSet) {
            Post post = null;
            try {
                post = Post.builder()
                        .username(resultSet.getString("username"))
                        .hashPassword(resultSet.getString("hashpassword"))
                        .firstName(resultSet.getString("first_name"))
                        .lastName(resultSet.getString("last_name"))
                        .id(resultSet.getLong("id"))
                        .build();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return post;
        }
    };

    @Override
    public Optional<PostRepository> read(Long id) {
        return Optional.empty();
    }

    @Override
    public void create(PostRepository model) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void update(Long id, PostRepository model) {

    }
}
