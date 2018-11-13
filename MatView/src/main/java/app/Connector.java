/*
 * Developed by Razil Minneakhmetov on 11/4/18 1:50 PM.
 * Last modified 11/4/18 1:50 PM.
 * Copyright © 2018. All rights reserved.
 */

package app;


import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connector {

    private Connection connection;
    private static final String URL = "jdbc:postgresql://localhost:5432/test";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "r1a2z3i4l5";

    @SneakyThrows
    public Connector() {
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public Connection getConnection() {
        return connection;
    }
}