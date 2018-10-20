/*
 * Developed by Razil Minneakhmetov on 10/17/18 8:43 AM.
 * Last modified 10/17/18 8:43 AM.
 * Copyright © 2018. All rights reserved.
 */

package app;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Random;

public class Views {

    Random random = new Random(System.currentTimeMillis());
    Random random1 = new Random(System.currentTimeMillis());
    //language=SQL
    public static final String SQL_CREATE_VIEW = "CREATE VIEW USERS_TABLE_VIEW_IDEA as (select * from user_table)";
    //language=SQL
    public static final String SQL_SELECT_FROM_VIEW = "SELECT * FROM USERS_TABLE_VIEW_IDEA";

    //language=SQL
    public static final String SQL_SELECT_FROM_TABLE = "SELECT * FROM USER_TABLE";
    //language=SQL
    public static final String SQL_DROP_VIEW = "DROP VIEW USERS_TABLE_VIEW_IDEA";

    private Connection connection;

    public Views(Connection connection) {
        this.connection = connection;
    }

    @SneakyThrows
    public void createView(){
        Statement statement = connection.createStatement();
        statement.executeUpdate(SQL_CREATE_VIEW);
    }

    @SneakyThrows
    public void selectFromView(){
        Statement statement = connection.createStatement();
        statement.executeQuery(SQL_SELECT_FROM_VIEW + " WHERE id  = " + random.nextInt(100));
    }

    @SneakyThrows
    public void selectFromTable(){
        Statement statement = connection.createStatement();
        statement.executeQuery(SQL_SELECT_FROM_TABLE + " WHERE id  = " + random1.nextInt(100));
    }

    @SneakyThrows
    public void dropView(){
        Statement statement = connection.createStatement();
        statement.executeUpdate(SQL_DROP_VIEW);
    }


}
