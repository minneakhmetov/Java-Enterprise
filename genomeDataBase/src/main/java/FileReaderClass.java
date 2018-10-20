/*
 * Developed by Razil Minneakhmetov on 10/18/18 12:48 PM.
 * Last modified 10/18/18 12:48 PM.
 * Copyright © 2018. All rights reserved.
 */

import lombok.SneakyThrows;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReaderClass {

    @SneakyThrows
    public ArrayList<String> readTwo(String path) {
        String first = "";
        String second = "";
        ArrayList<String> arrayList = new ArrayList<String>();

        FileReader reader = new FileReader(path);

        first = "" + (char) reader.read();
        second = "" + (char) reader.read();
        arrayList.add(first + second);

        // читаем посимвольно
        int c;
        while ((c = reader.read()) != -1) {
            if (c != '\n') {
                first = second;
                second = "" + (char) c;
                arrayList.add(first + second);
            }

        }
        return arrayList;
    }

    @SneakyThrows
    public ArrayList<String> readFive(String path) {
        String first = "";
        String second = "";
        String third = "";
        String forth = "";
        String fifth = "";
        ArrayList<String> arrayList = new ArrayList<String>();

        FileReader reader = new FileReader(path);

        first = "" + (char) reader.read();
        second = "" + (char) reader.read();
        third = "" + (char) reader.read();
        forth = "" + (char) reader.read();
        fifth = "" + (char) reader.read();
        arrayList.add(first + second + third + forth + fifth);

        // читаем посимвольно
        int c;
        while ((c = reader.read()) != -1) {
            if (c != '\n') {
                first = second;
                second = third;
                third = forth;
                forth = fifth;
                fifth = "" + (char) c;
                arrayList.add(first + second + third + forth + fifth);
            }

        }
        return arrayList;
    }


    @SneakyThrows
    public ArrayList<String> readNine(String path) {
        String first = "";
        String second = "";
        String third = "";
        String forth = "";
        String fifth = "";
        String sixth = "";
        String seventh = "";
        String eighth = "";
        String ninth = "";
        ArrayList<String> arrayList = new ArrayList<String>();

        FileReader reader = new FileReader(path);

        first = "" + (char) reader.read();
        second = "" + (char) reader.read();
        third = "" + (char) reader.read();
        forth = "" + (char) reader.read();
        fifth = "" + (char) reader.read();
        sixth = "" + (char) reader.read();
        seventh = "" + (char) reader.read();
        eighth = "" + (char) reader.read();
        ninth = "" + (char) reader.read();
        arrayList.add(first + second + third + forth + fifth + sixth + seventh + eighth + ninth);

        // читаем посимвольно
        int c;
        while ((c = reader.read()) != -1) {
            if (c != '\n') {
                first = second;
                second = third;
                third = forth;
                forth = fifth;
                fifth = sixth;
                sixth = seventh;
                seventh = eighth;
                eighth = ninth;
                ninth = "" + (char) c;
                arrayList.add(first + second + third + forth + fifth + sixth + seventh + eighth + ninth);
            }

        }
        return arrayList;
    }

    @SneakyThrows
    public String parseSQLQuery() {
        String SQL_PATH = "C:\\Users\\razil\\Desktop\\Java Enterprise\\genomeDataBase\\src\\main\\resources\\sql\\query.sql\\";
        Scanner scanner = new Scanner(new File(SQL_PATH));
        String result = "";
        while (scanner.hasNext()){
            result += scanner.nextLine() + '\n';
        }
        return result;
    }


}
