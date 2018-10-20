/*
 * Developed by Razil Minneakhmetov on 10/18/18 12:47 PM.
 * Last modified 10/18/18 12:47 PM.
 * Copyright © 2018. All rights reserved.
 */

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

public class Main {
    static private String FIRST_GENOME_PATH = "C:\\Users\\razil\\Desktop\\Java Enterprise\\genomeDataBase\\src\\main\\resources\\genome\\Genome_1.txt\\";
    static private String SECOND_GENOME_PATH = "C:\\Users\\razil\\Desktop\\Java Enterprise\\genomeDataBase\\src\\main\\resources\\genome\\Genome_2.txt\\";
    static private String URL = "jdbc:postgresql://localhost:5432/test3";
    static private String USERNAME = "postgres";
    static private String PASSWORD = "r1a2z3i4l5";
    static private Connection connection;

    @SneakyThrows
    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

//
        FileReaderClass reader = new FileReaderClass();
        ArrayList<String> firstGenomeTwo = reader.readTwo(FIRST_GENOME_PATH);
        ArrayList<String> firstGenomeFive = reader.readFive(FIRST_GENOME_PATH);
        ArrayList<String> firstGenomeNine = reader.readNine(FIRST_GENOME_PATH);

        ArrayList<String> secondGenomeTwo = reader.readTwo(SECOND_GENOME_PATH);
        ArrayList<String> secondGenomeFive = reader.readFive(SECOND_GENOME_PATH);
        ArrayList<String> secondGenomeNine = reader.readNine(SECOND_GENOME_PATH);

        Repository repository = new Repository(connection);
        repository.deleteAll();
        repository.insertFirstGenome(firstGenomeTwo, firstGenomeFive, firstGenomeNine);
        repository.insertSecondGenome(secondGenomeTwo, secondGenomeFive, secondGenomeNine);
        repository.getResults();
        System.out.println("Elapsed time: " + (System.currentTimeMillis() - time)/ 1000 + " seconds.");
    }
}
