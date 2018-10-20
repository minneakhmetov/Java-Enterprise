/*
 * Developed by Razil Minneakhmetov on 10/20/18 12:55 PM.
 * Last modified 10/20/18 12:55 PM.
 * Copyright © 2018. All rights reserved.
 */

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Repository {

    private Connection connection;
    //language=SQl
    private static final String SQL_INSERT_QUERY_FIRST_GENOME_TWO = "INSERT INTO first_genome_two (two) values (?)";
    //language=SQl
    private static final String SQL_INSERT_QUERY_FIRST_GENOME_FIVE = "INSERT INTO first_genome_five (five) values (?)";
    //language=SQl
    private static final String SQL_INSERT_QUERY_FIRST_GENOME_NINE = "INSERT INTO first_genome_nine (nine) values (?)";
    //language=SQl
    private static final String SQL_INSERT_QUERY_SECOND_GENOME_TWO = "INSERT INTO second_genome_two (two) values (?)";
    //language=SQl
    private static final String SQL_INSERT_QUERY_SECOND_GENOME_FIVE = "INSERT INTO second_genome_five (five) values (?)";
    //language=SQl
    private static final String SQL_INSERT_QUERY_SECOND_GENOME_NINE = "INSERT INTO second_genome_nine (nine) values (?)";
    //language=SQL
    private static final String SQL_DELETE_QUERY_FIRST_GENOME_TWO = "DELETE FROM first_genome_two";
    //language=SQL
    private static final String SQL_DELETE_QUERY_FIRST_GENOME_FIVE = "DELETE FROM first_genome_five";
    //language=SQL
    private static final String SQL_DELETE_QUERY_FIRST_GENOME_NINE = "DELETE FROM first_genome_nine";
    //language=SQL
    private static final String SQL_DELETE_QUERY_SECOND_GENOME_TWO = "DELETE FROM second_genome_two";
    //language=SQL
    private static final String SQL_DELETE_QUERY_SECOND_GENOME_FIVE = "DELETE FROM second_genome_five";
    //language=SQL
    private static final String SQL_DELETE_QUERY_SECOND_GENOME_NINE = "DELETE FROM second_genome_nine";



    public Repository(Connection connection) {
        this.connection = connection;
    }

    @SneakyThrows
    public void insertFirstGenome(ArrayList<String> two, ArrayList<String> five, ArrayList<String> nine){
        connection.setAutoCommit(false);
        PreparedStatement statementTwo = connection.prepareStatement(SQL_INSERT_QUERY_FIRST_GENOME_TWO);
        PreparedStatement statementFive = connection.prepareStatement(SQL_INSERT_QUERY_FIRST_GENOME_FIVE);
        PreparedStatement statementNine = connection.prepareStatement(SQL_INSERT_QUERY_FIRST_GENOME_NINE);
        for (int i = 0; i < two.size(); i++) {
            statementTwo.setString(1, two.get(i));
            statementTwo.addBatch();
        }
        for (int i = 0; i < five.size(); i++) {
            statementFive.setString(1, five.get(i));
            statementFive.addBatch();
        }
        for (int i = 0; i < nine.size(); i++) {
            statementNine.setString(1, nine.get(i));
            statementNine.addBatch();
        }
        statementTwo.executeBatch();
        statementFive.executeBatch();
        statementNine.executeBatch();
        connection.commit();
        connection.setAutoCommit(true);
    }

    @SneakyThrows
    public void insertSecondGenome(ArrayList<String> two, ArrayList<String> five, ArrayList<String> nine){
        connection.setAutoCommit(false);
        PreparedStatement statementTwo = connection.prepareStatement(SQL_INSERT_QUERY_SECOND_GENOME_TWO);
        PreparedStatement statementFive = connection.prepareStatement(SQL_INSERT_QUERY_SECOND_GENOME_FIVE);
        PreparedStatement statementNine = connection.prepareStatement(SQL_INSERT_QUERY_SECOND_GENOME_NINE);
        for (int i = 0; i < two.size(); i++) {
            statementTwo.setString(1, two.get(i));
            statementTwo.addBatch();
        }
        for (int i = 0; i < five.size(); i++) {
            statementFive.setString(1, five.get(i));
            statementFive.addBatch();
        }
        for (int i = 0; i < nine.size(); i++) {
            statementNine.setString(1, nine.get(i));
            statementNine.addBatch();
        }
        statementTwo.executeBatch();
        statementFive.executeBatch();
        statementNine.executeBatch();
        connection.commit();
        connection.setAutoCommit(true);
    }

    @SneakyThrows
    public void deleteAll(){
        Statement firstGenomeTwo = connection.createStatement();
        Statement firstGenomeFive = connection.createStatement();
        Statement firstGenomeNine = connection.createStatement();
        Statement secondGenomeTwo = connection.createStatement();
        Statement secondGenomeFive = connection.createStatement();
        Statement secondGenomeNine = connection.createStatement();
        firstGenomeTwo.execute(SQL_DELETE_QUERY_FIRST_GENOME_TWO);
        firstGenomeFive.execute(SQL_DELETE_QUERY_FIRST_GENOME_FIVE);
        firstGenomeNine.execute(SQL_DELETE_QUERY_FIRST_GENOME_NINE);
        secondGenomeTwo.execute(SQL_DELETE_QUERY_SECOND_GENOME_TWO);
        secondGenomeFive.execute(SQL_DELETE_QUERY_SECOND_GENOME_FIVE);
        secondGenomeNine.execute(SQL_DELETE_QUERY_SECOND_GENOME_NINE);

    }

    @SneakyThrows
    public void getResults(){
        FileReaderClass reader = new FileReaderClass();
        Statement statement = connection.createStatement();
        ResultSet set = statement.executeQuery(reader.parseSQLQuery());
        set.next();
        System.out.println("Two-letter genome equality: " + (set.getFloat("percent_two") * 100) + "%");
        System.out.println("Five-letter genome equality: " + (set.getFloat("percent_five") * 100) + "%");
        System.out.println("Nine-letter genome equality: " + (set.getFloat("percent_nine") * 100) + "%");
        System.out.println("Unique two-letter first genome amount: " + set.getInt("count_first_genome_two"));
        System.out.println("Unique five-letter first genome amount: " + set.getInt("count_first_genome_five"));
        System.out.println("Unique nine-letter first genome amount: " + set.getInt("count_first_genome_nine"));
        System.out.println("Unique two-letter second genome amount: " + set.getInt("count_second_genome_two"));
        System.out.println("Unique five-letter second genome amount: " + set.getInt("count_second_genome_five"));
        System.out.println("Unique nine-letter second genome amount: " + set.getInt("count_second_genome_nine"));
        System.out.println("|A ⋂ B| Two-letter: " + set.getInt("intersect_genome_two"));
        System.out.println("|A ⋂ B| Five-letter: " + set.getInt("intersect_genome_five"));
        System.out.println("|A ⋂ B| Nine-letter: " + set.getInt("intersect_genome_nine"));
        System.out.println("|A ⋃ B| Two-letter: " + set.getInt("union_genome_two"));
        System.out.println("|A ⋃ B| Five-letter: " + set.getInt("union_genome_five"));
        System.out.println("|A ⋃ B| Nine-letter: " + set.getInt("union_genome_nine"));

    }

}