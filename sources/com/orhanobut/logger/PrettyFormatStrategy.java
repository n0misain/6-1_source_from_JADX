package com.orhanobut.logger;

public class PrettyFormatStrategy implements FormatStrategy {
    private static final String BOTTOM_BORDER = "└────────────────────────────────────────────────────────────────────────────────────────────────────────────────";
    private static final char BOTTOM_LEFT_CORNER = '└';
    private static final int CHUNK_SIZE = 4000;
    private static final String DOUBLE_DIVIDER = "────────────────────────────────────────────────────────";
    private static final char HORIZONTAL_LINE = '│';
    private static final String MIDDLE_BORDER = "├┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄";
    private static final char MIDDLE_CORNER = '├';
    private static final int MIN_STACK_OFFSET = 5;
    private static final String SINGLE_DIVIDER = "┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄";
    private static final String TOP_BORDER = "┌────────────────────────────────────────────────────────────────────────────────────────────────────────────────";
    private static final char TOP_LEFT_CORNER = '┌';
    private final LogStrategy logStrategy;
    private final int methodCount;
    private final int methodOffset;
    private final boolean showThreadInfo;
    private final String tag;

    public static class Builder {
        LogStrategy logStrategy;
        int methodCount;
        int methodOffset;
        boolean showThreadInfo;
        String tag;

        private Builder() {
            this.methodCount = 2;
            this.methodOffset = 0;
            this.showThreadInfo = true;
            this.tag = "PRETTY_LOGGER";
        }

        public Builder methodCount(int val) {
            this.methodCount = val;
            return this;
        }

        public Builder methodOffset(int val) {
            this.methodOffset = val;
            return this;
        }

        public Builder showThreadInfo(boolean val) {
            this.showThreadInfo = val;
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

        public PrettyFormatStrategy build() {
            if (this.logStrategy == null) {
                this.logStrategy = new LogcatLogStrategy();
            }
            return new PrettyFormatStrategy();
        }
    }

    private PrettyFormatStrategy(Builder builder) {
        this.methodCount = builder.methodCount;
        this.methodOffset = builder.methodOffset;
        this.showThreadInfo = builder.showThreadInfo;
        this.logStrategy = builder.logStrategy;
        this.tag = builder.tag;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public void log(int priority, String onceOnlyTag, String message) {
        String tag = formatTag(onceOnlyTag);
        logTopBorder(priority, tag);
        logHeaderContent(priority, tag, this.methodCount);
        byte[] bytes = message.getBytes();
        int length = bytes.length;
        if (length <= CHUNK_SIZE) {
            if (this.methodCount > 0) {
                logDivider(priority, tag);
            }
            logContent(priority, tag, message);
            logBottomBorder(priority, tag);
            return;
        }
        if (this.methodCount > 0) {
            logDivider(priority, tag);
        }
        for (int i = 0; i < length; i += CHUNK_SIZE) {
            logContent(priority, tag, new String(bytes, i, Math.min(length - i, CHUNK_SIZE)));
        }
        logBottomBorder(priority, tag);
    }

    private void logTopBorder(int logType, String tag) {
        logChunk(logType, tag, TOP_BORDER);
    }

    private void logHeaderContent(int logType, String tag, int methodCount) {
        StackTraceElement[] trace = Thread.currentThread().getStackTrace();
        if (this.showThreadInfo) {
            logChunk(logType, tag, "│ Thread: " + Thread.currentThread().getName());
            logDivider(logType, tag);
        }
        String level = "";
        int stackOffset = getStackOffset(trace) + this.methodOffset;
        if (methodCount + stackOffset > trace.length) {
            methodCount = (trace.length - stackOffset) - 1;
        }
        for (int i = methodCount; i > 0; i--) {
            int stackIndex = i + stackOffset;
            if (stackIndex < trace.length) {
                StringBuilder builder = new StringBuilder();
                builder.append(HORIZONTAL_LINE).append(' ').append(level).append(getSimpleClassName(trace[stackIndex].getClassName())).append(".").append(trace[stackIndex].getMethodName()).append(" ").append(" (").append(trace[stackIndex].getFileName()).append(":").append(trace[stackIndex].getLineNumber()).append(")");
                level = level + "   ";
                logChunk(logType, tag, builder.toString());
            }
        }
    }

    private void logBottomBorder(int logType, String tag) {
        logChunk(logType, tag, BOTTOM_BORDER);
    }

    private void logDivider(int logType, String tag) {
        logChunk(logType, tag, MIDDLE_BORDER);
    }

    private void logContent(int logType, String tag, String chunk) {
        for (String line : chunk.split(System.getProperty("line.separator"))) {
            logChunk(logType, tag, "│ " + line);
        }
    }

    private void logChunk(int priority, String tag, String chunk) {
        this.logStrategy.log(priority, tag, chunk);
    }

    private String getSimpleClassName(String name) {
        return name.substring(name.lastIndexOf(".") + 1);
    }

    private int getStackOffset(StackTraceElement[] trace) {
        for (int i = 5; i < trace.length; i++) {
            String name = trace[i].getClassName();
            if (!name.equals(LoggerPrinter.class.getName()) && !name.equals(Logger.class.getName())) {
                return i - 1;
            }
        }
        return -1;
    }

    private String formatTag(String tag) {
        if (Utils.isEmpty(tag) || Utils.equals(this.tag, tag)) {
            return this.tag;
        }
        return this.tag + "-" + tag;
    }
}
