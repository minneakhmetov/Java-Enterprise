package app;

import lombok.SneakyThrows;
import models.User;
import repositories.UserRepository;
import repositories.UserRepositoryConnectionImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;

/**
 * 03.09.2018
 * UsersRepositoryDemo
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class UsersRepositoryDemo {

    // данные для подключения
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "r1a2z3i4l5";
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";

    @SneakyThrows
    public static void main(String[] args) throws SQLException {
        Connection connection =
                DriverManager.getConnection(URL, USERNAME, PASSWORD);
        UserRepository userRepository = new UserRepositoryConnectionImpl(connection);
      // Optional<User> user = userRepository.read(1L);
        User user = User.builder()
                .firstName("я")
                .lastName("фамилия")
                .username("юзернаме")
                .hashPassword("777")
                .build();
        userRepository.create(user);
       // System.out.println(user);
        //userRepository.delete(1L);
        System.out.println("все нормас");
        //System.out.println(user);
    }
}
