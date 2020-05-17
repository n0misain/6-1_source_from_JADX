package com.orhanobut.logger;

import android.util.Log;

public class LogcatLogStrategy implements LogStrategy {
    public void log(int priority, String tag, String message) {
        Log.println(priority, tag, message);
    }
}
