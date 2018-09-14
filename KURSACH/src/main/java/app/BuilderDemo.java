package app;

import models.User;

/**
 * 03.09.2018
 * BuilderDemo
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class BuilderDemo {
    public static void main(String[] args) {
        User user = User.builder()
                .username("sidikov.marsel@gmail.com")
                .firstName("Marsel")
                .lastName("Sidikov")
                .build();
    }
}
