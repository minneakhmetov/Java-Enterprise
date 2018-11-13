/*
 * Developed by Razil Minneakhmetov on 11/4/18 5:18 PM.
 * Last modified 11/4/18 5:18 PM.
 * Copyright © 2018. All rights reserved.
 */

package app;

public class TimePicker {
    long start;
    long end;
    long duration;

    public void setStart() {
        this.start = System.currentTimeMillis();
    }

    public void setEnd() {
        this.end = System.currentTimeMillis();
    }
    public long getDuration() {
        duration = end - start;
        return duration;
    }

}