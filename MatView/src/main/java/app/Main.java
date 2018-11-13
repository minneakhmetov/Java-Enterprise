/*
 * Developed by Razil Minneakhmetov on 11/4/18 1:50 PM.
 * Last modified 11/4/18 1:50 PM.
 * Copyright © 2018. All rights reserved.
 */

package app;

import lombok.SneakyThrows;

public class Main {

    @SneakyThrows
    public static void main(String[] args) {
        System.out.println("querying...");
        Thread.sleep(500);
        Connector connector = new Connector();
        Repository repository = new Repository(connector.getConnection());
        repository.refresh();
    }
}