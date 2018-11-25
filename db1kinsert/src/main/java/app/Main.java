/*
 * Developed by Razil Minneakhmetov on 10/17/18 8:43 AM.
 * Last modified 10/17/18 8:43 AM.
 * Copyright © 2018. All rights reserved.
 */

package app;

import lombok.SneakyThrows;
import uk.co.szmg.grafana.GrafanaClient;
import uk.co.szmg.grafana.GrafanaEndpoint;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

public class Main {

    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "r1a2z3i4l5";
    private static final String URL = "jdbc:postgresql://localhost:5432/test";

    private static final int CACHE = 10000;

    private static final int NUMBER_OF_INSERTS = 10000;

    private static final int VALUE = 1234;

    private static final int TIME_SECONDS = 60;

    private static Connection connection;


    //static Random random = new Random(System.currentTimeMillis());

    @SneakyThrows
    public static void main(String[] args) {
//        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//        FileReader reader = new FileReader();
//        String query = reader.parseSQLQuery();
//        //System.out.println(random.nextInt(100));
//        TPSManager manager = new TPSManager(connection);
//        ArrayList<Long> arrayList = new ArrayList<Long>();
//        manager.reportWriter("Report #" + manager.number + " for " + manager.getNumberOfInserts() + " inserts."  );
//        long startTime = System.currentTimeMillis()/1000;
//
//        for (int i = 0; System.currentTimeMillis()/1000 < startTime + TIME_SECONDS; i++) {
//            //Timestamp start = currentTimeStamp();
//            long start = System.currentTimeMillis();
//            manager.executeQuery(query);
//            long end = System.currentTimeMillis();
//            //Timestamp end = currentTimeStamp();
//            long time = end - start;
//            arrayList.add(time);
//            manager.reportWriter("Iteration #" + i + ". Elapsed time " + time + " milliseconds.");
//        }
//
//        long averageTime = 0;
//        for (Long anArrayList : arrayList)
//            averageTime += anArrayList;
//        averageTime /= arrayList.size();
//
//        manager.reportWriter("_______________________End________________________");
//        manager.reportWriter("Average time: " + averageTime);
//        manager.reportWriter("Iteration amount: " + arrayList.size());
//        manager.reportWriter("TPS: " + (averageTime/arrayList.size()));


//        Views views = new Views(connection);
//        views.dropView();
//        views.createView();
//        Timestamp start = currentTimeStamp();
//        views.selectFromView();
//        Timestamp end = currentTimeStamp();
//        long viewTime = (end.getNanos() - start.getNanos()) / 1000;
//        start = currentTimeStamp();
//        views.selectFromTable();
//        end = currentTimeStamp();
//        long tableTime = (end.getNanos() - start.getNanos()) / 1000;
//        System.out.println("Elapsed time for view: " + viewTime + " microseconds.");
//        System.out.println("Elapsed time for table: " + tableTime + " microseconds.");


//        dropSequence();
//        deleteAll();
//        createSequence(CACHE, true);
//        Timestamp start = currentTimeStamp();
//        insert(NUMBER_OF_INSERTS);
//        Timestamp end = currentTimeStamp();
//        System.out.println("Elapsed time: " + ((end.getNanos() - start.getNanos()) / 1000) + " microseconds.");

//        Properties properties = new Properties();
//        properties.load(new FileInputStream("C:\\Users\\razil\\Desktop\\JavaFX11-example-master\\src\\main\\resources\\properties\\connect.properties"));
//        System.out.println(properties.getProperty("database.ip"));
//        properties.setProperty("database.ip", "localhost");
//
//        System.out.println(properties.getProperty("database.ip"));

//        File file = new File("C:\\Users\\razil\\Desktop\\exists_limit.5700");
//        File dir = new File("C:\\Users\\razil\\Desktop\\folder\\");
//        System.out.println(file.renameTo(new File(dir, file.getName())));

//        Runtime runtime =  Runtime.getRuntime();
//        File dir = new File("C:\\Users\\razil\\Desktop\\");
////        Process auth = runtime.exec("cmd /c set PGPASSWORD=r1a2z3i4l5");
////        auth.waitFor();
//        Process process = runtime.exec("cmd /c exec.bat", null, dir);
//        //process.waitFor();
//
//        BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
//        String s = null;
//        while ((s = stdInput.readLine()) != null) {
//            System.out.println(s);
//        }



//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
//        bw.write("r1a2z3i4l5");
//        //bw.eriteLine();
//        bw.flush();
//
//        //process.getOutputStream()
//
//
//        stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
//        s = null;
//        while ((s = stdInput.readLine()) != null) {
//            System.out.println(s);
//        }

        String string = "number of transactions actually processed: 334642";
        System.out.println(string.split("number of transactions actually processed: ")[1]);




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
        if (cache_on) {
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
