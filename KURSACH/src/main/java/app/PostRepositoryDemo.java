package app;

import lombok.SneakyThrows;
import models.User;
import repositories.PostRepository;
import repositories.PostRepositoryConnectionImpl;
import repositories.UserRepository;
import repositories.UserRepositoryConnectionImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 03.09.2018
 * UsersRepositoryDemo
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class PostRepositoryDemo {

    // данные для подключения
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "r1a2z3i4l5";
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";

    @SneakyThrows
    public static void main(String[] args) throws SQLException {
        Connection connection =
                DriverManager.getConnection(URL, USERNAME, PASSWORD);

        // UserRepository userRepository = new UserRepositoryConnectionImpl(connection);
        PostRepository postRepository = new PostRepositoryConnectionImpl(connection);
        postRepository.post("всем привет это второй пост", 5L);
        System.out.println("все нормас");

    }
}
