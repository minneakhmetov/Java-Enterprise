/*
 * Developed by Razil Minneakhmetov on 10/17/18 9:07 AM.
 * Last modified 10/17/18 9:07 AM.
 * Copyright © 2018. All rights reserved.
 */

package app;

import lombok.SneakyThrows;
import org.postgresql.util.PSQLException;

import java.sql.*;

public class SQLTimer {

    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "r1a2z3i4l5";
    private static final String URL = "jdbc:postgresql://localhost:5432/";
    private static final String DATABASE_NAME = "test2";

    private static final String BEFORE_QUERYING_PATH = "C:\\Users\\razil\\Desktop\\бд\\sql\\before\\";
    private static final String QUERYING_PATH = "C:\\Users\\razil\\Desktop\\бд\\sql\\after\\";

    private static final String SQL_DROP_DATABASE = "DROP DATABASE " + DATABASE_NAME;
    private static final String SQL_CREATE_DATABASE = "CREATE DATABASE " + DATABASE_NAME;

    private static Connection connection;

    @SneakyThrows
    public static void main(String[] args) {
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        FileReader beforeQuerying = new FileReader(BEFORE_QUERYING_PATH);
        FileReader querying = new FileReader(QUERYING_PATH);

        createDataBase();
        connection = DriverManager.getConnection(URL + DATABASE_NAME, USERNAME, PASSWORD);
        //String[] queryingArray = querying.getQueries();
        //String[] beforeQueryingArray = beforeQuerying.getQueries();
        executeNotTimeQueries(beforeQuerying.getQueries());
        executeTimeQueries(querying.getQueries());
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        dropDataBase();
    }

    @SneakyThrows
    public static void executeNotTimeQueries(String[] queries) {
        for (int i = 0; i < queries.length; i++) {
            try {
                Statement statement = connection.createStatement();
                statement.execute(queries[i]);
            } catch (PSQLException e) {
                e.printStackTrace();
                System.err.println();
                System.err.println("File number: " + i);
                System.err.println("In script: " + queries[i]);
                return;
            }
        }
    }

    @SneakyThrows
    public static void executeTimeQueries(String[] queries) {
        Timestamp start = currentTimeStamp();
        for (int i = 0; i < queries.length; i++) {
            try {
                Timestamp scriptStart = currentTimeStamp();
                Statement statement = connection.createStatement();
                statement.execute(queries[i]);
                Timestamp scriptEnd = currentTimeStamp();
                System.out.println("Elapsed time for script " + i + ": " + ((scriptEnd.getNanos() - scriptStart.getNanos()) / 1000) + " microseconds.");
            } catch (PSQLException e) {
                e.printStackTrace();
                System.err.println();
                System.err.println("File number: " + i);
                System.err.println("In script: " + queries[i]);
                return;
            }
        }
        Timestamp end = currentTimeStamp();
        System.out.println("Elapsed all scripts time: " + ((end.getNanos() - start.getNanos()) / 1000) + " microseconds.");
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

    @SneakyThrows
    public static void createDataBase() {
        Statement statement = connection.createStatement();
        statement.executeUpdate(SQL_CREATE_DATABASE);
    }

    @SneakyThrows
    public static void dropDataBase() {
        Statement statement = connection.createStatement();
        statement.executeUpdate(SQL_DROP_DATABASE);
    }
}
