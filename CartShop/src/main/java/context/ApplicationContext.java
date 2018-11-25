/*
 * Developed by Razil Minneakhmetov on 11/25/18 2:48 PM.
 * Last modified 11/25/18 2:48 PM.
 * Copyright © 2018. All rights reserved.
 */

package context;

import lombok.SneakyThrows;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import repositories.*;

import javax.sql.DataSource;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class ApplicationContext {
    private static ApplicationContext context = new ApplicationContext();

    private Map<String, Object> components;

    private ApplicationContext() {
        components = new HashMap<String, Object>();
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/stickershop");
        dataSource.setUsername("postgres");
        dataSource.setPassword("r1a2z3i4l5");

        components.put("dataSource", dataSource);
        components.put("avatarRepository", loadRepo("AvatarRepository"));
        components.put("cartRepository", loadRepo("CartRepository"));
        components.put("productRepository", loadRepo("ProductRepository"));
        components.put("userRepository", loadRepo("UserRepository"));
        components.put("cartService", loadService("CartService", (CartRepository) components.get("CartRepository")));
        components.put("loginService", loadService("LoginService", (UserRepository) components.get("UserRepository")));
        components.put("productService", loadService("ProductService",
                (ProductRepository) components.get("productRepository"), (CartRepository) components.get("cartRepository")));
        components.put("vkAuthService", loadService("VKAuthService"));

    }

    public static ApplicationContext getContext() {
        return context;
    }

    public <T> T getComponent(Class<T> componentClass) {
        for (Object component : components.values()) {
            if (componentClass.isAssignableFrom(component.getClass())) {
                return (T) component;
            }
        }
        return null;
    }

    public Object getAttribute(String key){
        return components.get(key);
    }

    public void setAttribute(String key, Object value){
        components.put(key, value);
    }

    @SneakyThrows
    private <T> T loadRepo(String className) {
        Class<T> userClass = (Class<T>) Class.forName("repositories." + className);
        Constructor<T> constructor = userClass.getConstructor(DataSource.class);
        T object = constructor.newInstance(components.get("dataSource"));
        return object;
    }

    @SneakyThrows
    private <T> T loadService(String className, Repository ... repos) {
        Class<T> userClass = (Class<T>) Class.forName("services." + className);
        Constructor<T> constructor;
        T object;
        try {
            constructor = userClass.getConstructor();
            object = constructor.newInstance();
        } catch (NoSuchMethodException e) {
            try {
                constructor = userClass.getConstructor(Repository.class);
                object = constructor.newInstance(repos[0]);
            } catch (NoSuchMethodException e1) {
                constructor = userClass.getConstructor(Repository.class, Repository.class);
                object = constructor.newInstance(repos[0], repos[1]);
            }
        }
        return object;
    }


}