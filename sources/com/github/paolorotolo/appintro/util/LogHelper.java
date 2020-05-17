package com.github.paolorotolo.appintro.util;

import android.util.Log;

public class LogHelper {
    private static final String LOG_PREFIX = "Log: ";
    private static final int LOG_PREFIX_LENGTH = LOG_PREFIX.length();
    private static final int MAX_LOG_TAG_LENGTH = 23;

    private static String makeLogTag(String str) {
        if (str.length() > 23 - LOG_PREFIX_LENGTH) {
            return LOG_PREFIX + str.substring(0, (23 - LOG_PREFIX_LENGTH) - 1);
        }
        return LOG_PREFIX + str;
    }

    public static String makeLogTag(Class cls) {
        return makeLogTag(cls.getSimpleName());
    }

    /* renamed from: v */
    public static void m1213v(String tag, Object... messages) {
    }

    /* renamed from: d */
    public static void m1209d(String tag, Object... messages) {
    }

    /* renamed from: i */
    public static void m1212i(String tag, Object... messages) {
        log(tag, 4, null, messages);
    }

    /* renamed from: w */
    public static void m1215w(String tag, Object... messages) {
        log(tag, 5, null, messages);
    }

    /* renamed from: w */
    public static void m1214w(String tag, Throwable t, Object... messages) {
        log(tag, 5, t, messages);
    }

    /* renamed from: e */
    public static void m1211e(String tag, Object... messages) {
        log(tag, 6, null, messages);
    }

    /* renamed from: e */
    public static void m1210e(String tag, Throwable t, Object... messages) {
        log(tag, 6, t, messages);
    }

    private static void log(String tag, int level, Throwable t, Object... messages) {
        String message;
        int i = 0;
        if (t == null && messages != null && messages.length == 1) {
            message = messages[0].toString();
        } else {
            StringBuilder sb = new StringBuilder();
            if (messages != null) {
                int length = messages.length;
                while (i < length) {
                    sb.append(messages[i]);
                    i++;
                }
            }
            if (t != null) {
                sb.append("\n").append(Log.getStackTraceString(t));
            }
            message = sb.toString();
        }
        Log.println(level, tag, message);
    }
}
