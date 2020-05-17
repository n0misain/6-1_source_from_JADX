package com.twitter.sdk.android.tweetui;

import android.content.res.Resources;
import android.support.v4.util.SparseArrayCompat;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

final class TweetDateUtils {
    static final SimpleDateFormat DATE_TIME_RFC822 = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.ENGLISH);
    static final long INVALID_DATE = -1;
    static final DateFormatter RELATIVE_DATE_FORMAT = new DateFormatter();

    static class DateFormatter {
        private Locale currentLocale;
        private final SparseArrayCompat<SimpleDateFormat> dateFormatArray = new SparseArrayCompat();

        DateFormatter() {
        }

        synchronized String formatLongDateString(Resources res, Date date) {
            return getDateFormat(res, C1043R.string.tw__relative_date_format_long).format(date);
        }

        synchronized String formatShortDateString(Resources res, Date date) {
            return getDateFormat(res, C1043R.string.tw__relative_date_format_short).format(date);
        }

        private synchronized DateFormat getDateFormat(Resources res, int patternId) {
            SimpleDateFormat format;
            if (this.currentLocale == null || this.currentLocale != res.getConfiguration().locale) {
                this.currentLocale = res.getConfiguration().locale;
                this.dateFormatArray.clear();
            }
            format = (SimpleDateFormat) this.dateFormatArray.get(patternId);
            if (format == null) {
                format = new SimpleDateFormat(res.getString(patternId), Locale.getDefault());
                this.dateFormatArray.put(patternId, format);
            }
            return format;
        }
    }

    private TweetDateUtils() {
    }

    static long apiTimeToLong(String apiTime) {
        long j = -1;
        if (apiTime != null) {
            try {
                j = DATE_TIME_RFC822.parse(apiTime).getTime();
            } catch (ParseException e) {
            }
        }
        return j;
    }

    static boolean isValidTimestamp(String timestamp) {
        return apiTimeToLong(timestamp) != -1;
    }

    static String dotPrefix(String timestamp) {
        return "• " + timestamp;
    }

    static String getRelativeTimeString(Resources res, long currentTimeMillis, long timestamp) {
        long diff = currentTimeMillis - timestamp;
        if (diff < 0) {
            return RELATIVE_DATE_FORMAT.formatLongDateString(res, new Date(timestamp));
        }
        if (diff < 60000) {
            int secs = (int) (diff / 1000);
            return res.getQuantityString(C1043R.plurals.tw__time_secs, secs, new Object[]{Integer.valueOf(secs)});
        } else if (diff < 3600000) {
            int mins = (int) (diff / 60000);
            return res.getQuantityString(C1043R.plurals.tw__time_mins, mins, new Object[]{Integer.valueOf(mins)});
        } else if (diff < 86400000) {
            int hours = (int) (diff / 3600000);
            return res.getQuantityString(C1043R.plurals.tw__time_hours, hours, new Object[]{Integer.valueOf(hours)});
        } else {
            Calendar now = Calendar.getInstance();
            now.setTimeInMillis(currentTimeMillis);
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(timestamp);
            Date d = new Date(timestamp);
            if (now.get(1) == c.get(1)) {
                return RELATIVE_DATE_FORMAT.formatShortDateString(res, d);
            }
            return RELATIVE_DATE_FORMAT.formatLongDateString(res, d);
        }
    }
}
