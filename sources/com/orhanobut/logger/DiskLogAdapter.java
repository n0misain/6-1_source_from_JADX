package com.orhanobut.logger;

public class DiskLogAdapter implements LogAdapter {
    private final FormatStrategy formatStrategy;

    public DiskLogAdapter() {
        this.formatStrategy = CsvFormatStrategy.newBuilder().build();
    }

    public DiskLogAdapter(FormatStrategy formatStrategy) {
        this.formatStrategy = formatStrategy;
    }

    public boolean isLoggable(int priority, String tag) {
        return true;
    }

    public void log(int priority, String tag, String message) {
        this.formatStrategy.log(priority, tag, message);
    }
}
