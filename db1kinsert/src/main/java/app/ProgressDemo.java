/*
 * Developed by Razil Minneakhmetov on 10/20/18 6:13 PM.
 * Last modified 10/20/18 6:13 PM.
 * Copyright © 2018. All rights reserved.
 */

package app;

import lombok.SneakyThrows;

public class ProgressDemo {

    @SneakyThrows
    static void updateProgress() {
        // progress bar width in chars
        int time = 30;
        int seconds = 60;
        long timeNow = System.currentTimeMillis()/1000;
        for (int i = 0; System.currentTimeMillis()/1000 < timeNow + seconds ; i++) {
            System.out.print("\r");
            System.out.print("|    Querying...");
            Thread.sleep(time);

            System.out.print("\r");
            System.out.print("/    Querying...");
            Thread.sleep(time);

            System.out.print("\r");
            System.out.print("--   Querying...");
            Thread.sleep(time);

            System.out.print("\r");
            System.out.print("\\    Querying...");
            Thread.sleep(time);
        }
    }

    public static void main(String[] args) {
        updateProgress();
    }
}
