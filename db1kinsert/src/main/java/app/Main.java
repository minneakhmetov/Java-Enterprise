/*
 * Developed by Razil Minneakhmetov on 10/17/18 8:43 AM.
 * Last modified 10/17/18 8:43 AM.
 * Copyright © 2018. All rights reserved.
 */

package app;

import lombok.SneakyThrows;

import java.sql.*;
import java.util.Random;

public class Main {

    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "r1a2z3i4l5";
    private static final String URL = "jdbc:postgresql://localhost:5432/test";

    private static final int CACHE = 10000;

    private static final int NUMBER_OF_INSERTS = 10000;

    private static final int VALUE = 1234;

    private static Connection connection;

    //static Random random = new Random(System.currentTimeMillis());

    @SneakyThrows
    public static void main(String[] args) {
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        //System.out.println(random.nextInt(100));

        Views views = new Views(connection);
        views.dropView();
        views.createView();
        Timestamp start = currentTimeStamp();
        views.selectFromView();
        Timestamp end = currentTimeStamp();
        long viewTime = (end.getNanos() - start.getNanos()) / 1000;
        start = currentTimeStamp();
        views.selectFromTable();
        end = currentTimeStamp();
        long tableTime = (end.getNanos() - start.getNanos()) / 1000;
        System.out.println("Elapsed time for view: " + viewTime + " microseconds.");
        System.out.println("Elapsed time for table: " + tableTime + " microseconds.");


//        dropSequence();
//        deleteAll();
//        createSequence(CACHE, true);
//        Timestamp start = currentTimeStamp();
//        insert(NUMBER_OF_INSERTS);
//        Timestamp end = currentTimeStamp();
//        System.out.println("Elapsed time: " + ((end.getNanos() - start.getNanos()) / 1000) + " microseconds.");

    }

    @SneakyThrows
    public static void deleteAll() {
        Statement statement = connection.createStatement();
        //language=SQL
        String SQL_DELETE_QUERY = "DELETE FROM extra";
        statement.executeUpdate(SQL_DELETE_QUERY);

    }

    @SneakyThrows
    public static void createSequence(int cache, boolean cache_on) {
        Statement statement = connection.createStatement();
        //language=SQL
        String SQL_QUERY = "CREATE SEQUENCE value_seq OWNED BY extra.value";
            if(cache_on) {
                //language=SQL
                SQL_QUERY += " CACHE " + cache;
            }
        //language=SQL
        SQL_QUERY += ";" +
                "SELECT setval('value_seq', (Select max(value) from extra) + 1);" +
                "ALTER TABLE extra ALTER COLUMN value set default nextval('value_seq');";
        statement.execute(SQL_QUERY);
    }

    @SneakyThrows
    public static void insert(int val) {
        Statement statement = connection.createStatement();
        //language=SQL
        String SQL_QUERY =
                "DO\n" +
                        "$do$\n" +
                        "BEGIN \n" +
                        "   FOR i IN 1.." + val + " LOOP\n" +
                        "       INSERT INTO extra (column_col) values (" + VALUE + "); \n" +
                        "   END LOOP;\n" +
                        "END\n" +
                        "$do$;";
        statement.executeUpdate(SQL_QUERY);
        //ResultSet set = statement.executeQuery(SQL_QUERY);
        //System.out.println(set);
    }

    @SneakyThrows
    public static void dropSequence() {
        Statement statement = connection.createStatement();
        //language=SQL
        String SQL_QUERY = "DROP SEQUENCE value_seq CASCADE";
        statement.executeUpdate(SQL_QUERY);
    }

    @SneakyThrows
    public static Timestamp currentTimeStamp() {
        Statement statement = connection.createStatement();
        //language=SQL
        String SQL_QUERY = "SELECT current_timestamp;";
        ResultSet set = statement.executeQuery(SQL_QUERY);
        set.next();
        return set.getTimestamp(1);

    }


}
