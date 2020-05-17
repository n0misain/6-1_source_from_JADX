package com.orhanobut.logger;

import android.os.Environment;
import android.os.HandlerThread;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CsvFormatStrategy implements FormatStrategy {
    private static final String NEW_LINE = System.getProperty("line.separator");
    private static final String NEW_LINE_REPLACEMENT = " <br> ";
    private static final String SEPARATOR = ",";
    private final Date date;
    private final SimpleDateFormat dateFormat;
    private final LogStrategy logStrategy;
    private final String tag;

    public static final class Builder {
        private static final int MAX_BYTES = 512000;
        Date date;
        SimpleDateFormat dateFormat;
        LogStrategy logStrategy;
        String tag;

        private Builder() {
            this.tag = "PRETTY_LOGGER";
        }

        public Builder date(Date val) {
            this.date = val;
            return this;
        }

        public Builder dateFormat(SimpleDateFormat val) {
            this.dateFormat = val;
            return this;
        }

        public Builder logStrategy(LogStrategy val) {
            this.logStrategy = val;
            return this;
        }

        public Builder tag(String tag) {
            this.tag = tag;
            return this;
        }

        public CsvFormatStrategy build() {
            if (this.date == null) {
                this.date = new Date();
            }
            if (this.dateFormat == null) {
                this.dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss.SSS", Locale.UK);
            }
            if (this.logStrategy == null) {
                String folder = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separatorChar + "logger";
                HandlerThread ht = new HandlerThread("AndroidFileLogger." + folder);
                ht.start();
                this.logStrategy = new DiskLogStrategy(new WriteHandler(ht.getLooper(), folder, MAX_BYTES));
            }
            return new CsvFormatStrategy();
        }
    }

    private CsvFormatStrategy(Builder builder) {
        this.date = builder.date;
        this.dateFormat = builder.dateFormat;
        this.logStrategy = builder.logStrategy;
        this.tag = builder.tag;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public void log(int priority, String onceOnlyTag, String message) {
        String tag = formatTag(onceOnlyTag);
        this.date.setTime(System.currentTimeMillis());
        StringBuilder builder = new StringBuilder();
        builder.append(Long.toString(this.date.getTime()));
        builder.append(SEPARATOR);
        builder.append(this.dateFormat.format(this.date));
        builder.append(SEPARATOR);
        builder.append(Utils.logLevel(priority));
        builder.append(SEPARATOR);
        builder.append(tag);
        if (message.contains(NEW_LINE)) {
            message = message.replaceAll(NEW_LINE, NEW_LINE_REPLACEMENT);
        }
        builder.append(SEPARATOR);
        builder.append(message);
        builder.append(NEW_LINE);
        this.logStrategy.log(priority, tag, builder.toString());
    }

    private String formatTag(String tag) {
        if (Utils.isEmpty(tag) || Utils.equals(this.tag, tag)) {
            return this.tag;
        }
        return this.tag + "-" + tag;
    }
}
