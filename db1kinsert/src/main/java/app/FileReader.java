/*
 * Developed by Razil Minneakhmetov on 10/17/18 9:13 AM.
 * Last modified 10/17/18 9:13 AM.
 * Copyright © 2018. All rights reserved.
 */

package app;

import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.io.File;
import java.util.Scanner;

@NoArgsConstructor
public class FileReader {

    private String folderPath;

    public FileReader(String folderPath) {
        this.folderPath = folderPath;
    }

    @SneakyThrows
    public String[] getQueries(){
        File folder = new File(folderPath);
        String[] result = new String[folder.listFiles().length];
        int count = 0;
        for(File file : folder.listFiles()){
            Scanner scanner = new Scanner(file);
            result[count] = "";
            System.err.println(file.getName());
            while (scanner.hasNext()){
                result[count] += scanner.nextLine() + '\n';
            }
            count++;
        }
        return result;
    }

    @SneakyThrows
    public String parseSQLQuery() {
        String SQL_PATH = "C:\\Users\\razil\\Desktop\\Java Enterprise\\db1kinsert\\src\\main\\resources\\sql\\query.sql\\";
        Scanner scanner = new Scanner(new File(SQL_PATH));
        String result = "";
        while (scanner.hasNext()){
            result += scanner.nextLine() + '\n';
        }
        return result;
    }
}
