/*
 * Developed by Razil Minneakhmetov on 10/20/18 3:35 PM.
 * Last modified 10/20/18 3:35 PM.
 * Copyright © 2018. All rights reserved.
 */

package app;

import lombok.Getter;
import lombok.SneakyThrows;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.Statement;


public class TPSManager {
    private Connection connection;

    private static final String REPORT_PATH = "C:\\Users\\razil\\Desktop\\Java Enterprise\\db1kinsert\\src\\main\\resources\\reports\\";

    private static final int NUMBER_OF_INSERTS = 1000000;
    private static final int VALUE = 1234;

    private static String FILE_NAME;
    public int number;

    public TPSManager(Connection connection) {
        this.connection = connection;
        File[] dir = new File(REPORT_PATH).listFiles();
        if (dir == null) {
            FILE_NAME = "report_0.txt";
            number = 0;
        }
        else {
            FILE_NAME = "report_" + dir.length + ".txt";
            number = dir.length;
        }

    }

    @SneakyThrows
    public void insert() {
        Statement statement = connection.createStatement();
        //language=SQL
        String SQL_QUERY =
                "DO\n" +
                        "$do$\n" +
                        "BEGIN \n" +
                        "   FOR i IN 1.." + NUMBER_OF_INSERTS + " LOOP\n" +
                        "       INSERT INTO extra (column_col) values (" + VALUE + "); \n" +
                        "   END LOOP;\n" +
                        "END\n" +
                        "$do$;" +
                        "DELETE FROM extra;";
        statement.executeUpdate(SQL_QUERY);
        //ResultSet set = statement.executeQuery(SQL_QUERY);
        //System.out.println(set);
    }

    @SneakyThrows
    public void executeQuery(String query){
        Statement statement = connection.createStatement();
        statement.execute(query);
    }

    @SneakyThrows
    public void reportWriter(String string) {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(REPORT_PATH + FILE_NAME), true)); // соединяем FileWriter с BufferedWitter
        bufferedWriter.write(string + "\n");
        bufferedWriter.close(); // закрываем поток
    }

    public static int getNumberOfInserts() {
        return NUMBER_OF_INSERTS;
    }

}
