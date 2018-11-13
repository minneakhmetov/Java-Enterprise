/*
 * Developed by Razil Minneakhmetov on 11/4/18 1:50 PM.
 * Last modified 11/4/18 1:50 PM.
 * Copyright © 2018. All rights reserved.
 */

package app;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.Statement;

public class Repository {

    private Connection connection;

    public Repository(Connection connection) {
        this.connection = connection;
    }

    private static final String SQL_QUERY = "refresh materialized view user_view_mat;";

    @SneakyThrows
    public void refresh() {
        Statement statement = connection.createStatement();
        statement.execute(SQL_QUERY);
    }
}