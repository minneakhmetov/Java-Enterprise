/*
 * Developed by Razil Minneakhmetov on 10/25/18 7:49 PM.
 * Last modified 10/25/18 7:49 PM.
 * Copyright © 2018. All rights reserved.
 */

package context;

import lombok.SneakyThrows;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import repositories.AvatarRepository;
import repositories.CartRepository;
import repositories.ProductRepository;
import repositories.UserRepository;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DBConnector implements ServletContextListener {

    @Override
    @SneakyThrows
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Class.forName("org.postgresql.Driver");
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/stickershop");
        dataSource.setUsername("postgres");
        dataSource.setPassword("r1a2z3i4l5");
        AvatarRepository avatarRepository = new AvatarRepository(dataSource);
        CartRepository cartRepository = new CartRepository(dataSource);
        ProductRepository productRepository = new ProductRepository(dataSource);
        UserRepository userRepository = new UserRepository(dataSource);

        ServletContext context = servletContextEvent.getServletContext();

        context.setAttribute("avatarRepository", avatarRepository);
        context.setAttribute("cartRepository", cartRepository);
        context.setAttribute("productRepository", productRepository);
        context.setAttribute("userRepository", userRepository);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}