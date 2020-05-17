package com.orhanobut.logger;

public final class Logger {
    public static final int ASSERT = 7;
    public static final int DEBUG = 3;
    public static final int ERROR = 6;
    public static final int INFO = 4;
    public static final int VERBOSE = 2;
    public static final int WARN = 5;
    private static Printer printer = new LoggerPrinter();

    private Logger() {
    }

    public static void printer(Printer printer) {
        printer = printer;
    }

    public static void addLogAdapter(LogAdapter adapter) {
        printer.addAdapter(adapter);
    }

    public static void clearLogAdapters() {
        printer.clearLogAdapters();
    }

    /* renamed from: t */
    public static Printer m1223t(String tag) {
        return printer.mo4193t(tag);
    }

    public static void log(int priority, String tag, String message, Throwable throwable) {
        printer.log(priority, tag, message, throwable);
    }

    /* renamed from: d */
    public static void m1219d(String message, Object... args) {
        printer.mo4187d(message, args);
    }

    /* renamed from: d */
    public static void m1218d(Object object) {
        printer.mo4186d(object);
    }

    /* renamed from: e */
    public static void m1220e(String message, Object... args) {
        printer.mo4189e(null, message, args);
    }

    /* renamed from: e */
    public static void m1221e(Throwable throwable, String message, Object... args) {
        printer.mo4189e(throwable, message, args);
    }

    /* renamed from: i */
    public static void m1222i(String message, Object... args) {
        printer.mo4190i(message, args);
    }

    /* renamed from: v */
    public static void m1224v(String message, Object... args) {
        printer.mo4194v(message, args);
    }

    /* renamed from: w */
    public static void m1225w(String message, Object... args) {
        printer.mo4195w(message, args);
    }

    public static void wtf(String message, Object... args) {
        printer.wtf(message, args);
    }

    public static void json(String json) {
        printer.json(json);
    }

    public static void xml(String xml) {
        printer.xml(xml);
    }
}
