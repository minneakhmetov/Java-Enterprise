/*
 * Developed by Razil Minneakhmetov on 11/4/18 4:08 PM.
 * Last modified 11/4/18 4:08 PM.
 * Copyright © 2018. All rights reserved.
 */

package app;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.Statement;

public class Repository {

    private Connection connection;

    private static final String SQL_QUERY_IN = "select id from user_table where id in (1,2, 3);";
    private static final String SQL_QUERY_EXIST = "select id from user_table where exists (select 1, 2, 3)";
    private static final String SQL_QUERY_EXIST_LIMIT = "select id from user_table where exists (select 1, 2, 3 limit 1)";

    public Repository(Connection connection) {
        this.connection = connection;
    }

    @SneakyThrows
    private void query(String query){
        Statement statement = connection.createStatement();
        statement.execute(query);
    }

    public void queryIn(){
        query(SQL_QUERY_IN);
    }

    public void queryExist(){
        query(SQL_QUERY_EXIST);
    }
    public void queryExistLimit(){
        query(SQL_QUERY_EXIST_LIMIT);
    }


}