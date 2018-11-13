/*
 * Developed by Razil Minneakhmetov on 11/4/18 4:05 PM.
 * Last modified 11/4/18 4:05 PM.
 * Copyright © 2018. All rights reserved.
 */

package app;

import java.sql.SQLOutput;

public class Application {

    public static void main(String[] args) {
        Connector connector = new Connector();
        Repository repository = new Repository(connector.getConnection());

        TimePicker in  = new TimePicker();
        TimePicker exist = new TimePicker();
        TimePicker existLimit = new TimePicker();

        in.setStart();
        repository.queryIn();
        in.setEnd();

        exist.setStart();
        repository.queryExist();
        exist.setEnd();

        existLimit.setStart();
        repository.queryExistLimit();
        existLimit.setEnd();

        System.out.println("IN time" + in.getDuration());
        System.out.println("EXIST time" + exist.getDuration());
        System.out.println("EXIST LIMIT TIME" + existLimit.getDuration());


    }


}