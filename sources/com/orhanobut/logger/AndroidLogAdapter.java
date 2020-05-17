package com.orhanobut.logger;

public class AndroidLogAdapter implements LogAdapter {
    private final FormatStrategy formatStrategy;

    public AndroidLogAdapter() {
        this.formatStrategy = PrettyFormatStrategy.newBuilder().build();
    }

    public AndroidLogAdapter(FormatStrategy formatStrategy) {
        this.formatStrategy = formatStrategy;
    }

    public boolean isLoggable(int priority, String tag) {
        return true;
    }

    public void log(int priority, String tag, String message) {
        this.formatStrategy.log(priority, tag, message);
    }
}
