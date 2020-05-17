package com.applovin.impl.sdk;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.text.TextUtils;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;
import com.twitter.sdk.android.core.TwitterApiErrorConstants;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Map;
import java.util.Map.Entry;

public class fk extends AppLovinSdkUtils {
    /* renamed from: a */
    private static final char[] f908a = "0123456789abcdef".toCharArray();
    /* renamed from: b */
    private static final char[] f909b = "-'".toCharArray();

    /* renamed from: a */
    public static double m1075a(long j) {
        return ((double) j) / 1000.0d;
    }

    /* renamed from: a */
    public static float m1076a(float f) {
        return 1000.0f * f;
    }

    /* renamed from: a */
    public static int m1077a(Context context) {
        if (context != null) {
            Resources resources = context.getResources();
            if (resources != null) {
                Configuration configuration = resources.getConfiguration();
                if (configuration != null) {
                    return configuration.orientation;
                }
            }
        }
        return 0;
    }

    /* renamed from: a */
    public static int m1078a(String str, int i) {
        return m1094d(str) ? Integer.parseInt(str) : i;
    }

    /* renamed from: a */
    public static Bitmap m1079a(Context context, int i, int i2) {
        int i3 = 1;
        Bitmap bitmap = null;
        FileInputStream fileInputStream = null;
        FileInputStream fileInputStream2 = null;
        try {
            Options options = new Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeResource(context.getResources(), i);
            if (options.outHeight > i2 || options.outWidth > i2) {
                i3 = (int) Math.pow(2.0d, (double) ((int) Math.ceil(Math.log(((double) i2) / ((double) Math.max(options.outHeight, options.outWidth))) / Math.log(0.5d))));
            }
            new Options().inSampleSize = i3;
            bitmap = BitmapFactory.decodeResource(context.getResources(), i);
            try {
                fileInputStream.close();
                fileInputStream2.close();
            } catch (Exception e) {
            }
        } catch (Exception e2) {
            try {
                fileInputStream.close();
                fileInputStream2.close();
            } catch (Exception e3) {
            }
        } catch (Throwable th) {
            try {
                fileInputStream.close();
                fileInputStream2.close();
            } catch (Exception e4) {
            }
            throw th;
        }
        return bitmap;
    }

    /* renamed from: a */
    public static Bitmap m1080a(File file, int i) {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2;
        FileInputStream fileInputStream3;
        Throwable th;
        int i2 = 1;
        FileInputStream fileInputStream4 = null;
        try {
            Options options = new Options();
            options.inJustDecodeBounds = true;
            fileInputStream = new FileInputStream(file);
            try {
                BitmapFactory.decodeStream(fileInputStream, null, options);
                fileInputStream.close();
                if (options.outHeight > i || options.outWidth > i) {
                    i2 = (int) Math.pow(2.0d, (double) ((int) Math.ceil(Math.log(((double) i) / ((double) Math.max(options.outHeight, options.outWidth))) / Math.log(0.5d))));
                }
                Options options2 = new Options();
                options2.inSampleSize = i2;
                InputStream fileInputStream5 = new FileInputStream(file);
                try {
                    Bitmap decodeStream = BitmapFactory.decodeStream(fileInputStream5, null, options2);
                    fileInputStream5.close();
                    try {
                        fileInputStream.close();
                        fileInputStream5.close();
                        return decodeStream;
                    } catch (Exception e) {
                        return decodeStream;
                    }
                } catch (Exception e2) {
                    InputStream inputStream = fileInputStream5;
                    fileInputStream2 = fileInputStream;
                    try {
                        fileInputStream2.close();
                        fileInputStream3.close();
                    } catch (Exception e3) {
                    }
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    InputStream inputStream2 = fileInputStream5;
                    try {
                        fileInputStream.close();
                        fileInputStream4.close();
                    } catch (Exception e4) {
                    }
                    throw th;
                }
            } catch (Exception e5) {
                fileInputStream3 = null;
                fileInputStream2 = fileInputStream;
                fileInputStream2.close();
                fileInputStream3.close();
                return null;
            } catch (Throwable th3) {
                th = th3;
                fileInputStream.close();
                fileInputStream4.close();
                throw th;
            }
        } catch (Exception e6) {
            fileInputStream3 = null;
            fileInputStream2 = null;
            fileInputStream2.close();
            fileInputStream3.close();
            return null;
        } catch (Throwable th4) {
            th = th4;
            fileInputStream = null;
            fileInputStream.close();
            fileInputStream4.close();
            throw th;
        }
    }

    /* renamed from: a */
    public static String m1081a(String str) {
        return (str == null || str.length() <= 4) ? "NOKEY" : str.substring(str.length() - 4);
    }

    /* renamed from: a */
    public static String m1082a(String str, AppLovinSdkImpl appLovinSdkImpl) {
        return m1083a(str, (Integer) appLovinSdkImpl.get(dj.f790s), (String) appLovinSdkImpl.get(dj.f789r));
    }

    /* renamed from: a */
    private static String m1083a(String str, Integer num, String str2) {
        if (str2 == null) {
            throw new IllegalArgumentException("No algorithm specified");
        } else if (str == null || str.length() < 1) {
            return "";
        } else {
            if (str2.length() < 1 || "none".equals(str2)) {
                return str;
            }
            try {
                MessageDigest instance = MessageDigest.getInstance(str2);
                instance.update(str.getBytes(HttpRequest.CHARSET_UTF8));
                str = m1086a(instance.digest());
                return (str == null || num.intValue() <= 0) ? str : str.substring(0, Math.min(num.intValue(), str.length()));
            } catch (Throwable e) {
                throw new RuntimeException("Unknown algorithm \"" + str2 + "\"", e);
            } catch (Throwable e2) {
                throw new RuntimeException("Programming error: UTF-8 is not know encoding", e2);
            }
        }
    }

    /* renamed from: a */
    public static String m1084a(String str, String str2) {
        if (str == null) {
            str = "";
        }
        return str2.replace("{PLACEMENT}", m1093c(str));
    }

    /* renamed from: a */
    static String m1085a(Map map) {
        if (map == null || map.isEmpty()) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Entry entry : map.entrySet()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append("&");
            }
            stringBuilder.append(entry.getKey()).append('=').append(entry.getValue());
        }
        return stringBuilder.toString();
    }

    /* renamed from: a */
    public static String m1086a(byte[] bArr) {
        if (bArr == null) {
            throw new IllegalArgumentException("No data specified");
        }
        char[] cArr = new char[(bArr.length * 2)];
        for (int i = 0; i < bArr.length; i++) {
            cArr[i * 2] = f908a[(bArr[i] & TwitterApiErrorConstants.SPAMMER) >>> 4];
            cArr[(i * 2) + 1] = f908a[bArr[i] & 15];
        }
        return new String(cArr);
    }

    /* renamed from: a */
    public static void m1087a(AppLovinAdLoadListener appLovinAdLoadListener, C0505i c0505i, int i, AppLovinSdk appLovinSdk) {
        if (appLovinAdLoadListener != null) {
            try {
                if (appLovinAdLoadListener instanceof aj) {
                    ((aj) appLovinAdLoadListener).mo2254a(c0505i, i);
                } else {
                    appLovinAdLoadListener.failedToReceiveAd(i);
                }
            } catch (Throwable th) {
                appLovinSdk.getLogger().mo2290e("AppLovinUtils", "Unable process a failure to receive an ad", th);
            }
        }
    }

    /* renamed from: a */
    public static boolean m1088a(int i, int i2) {
        return (i & i2) != 0;
    }

    /* renamed from: a */
    public static boolean m1089a(AppLovinSdk appLovinSdk, String str) {
        for (String startsWith : ((String) ((AppLovinSdkImpl) appLovinSdk).get(dj.f754I)).split(",")) {
            if (str.startsWith(startsWith)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: b */
    public static long m1090b(float f) {
        return (long) Math.round(f);
    }

    /* renamed from: b */
    public static String m1091b(String str) {
        return m1083a(str, Integer.valueOf(-1), CommonUtils.SHA1_INSTANCE);
    }

    /* renamed from: c */
    public static long m1092c(float f) {
        return m1090b(m1076a(f));
    }

    /* renamed from: c */
    public static String m1093c(String str) {
        if (!AppLovinSdkUtils.isValidString(str)) {
            return "";
        }
        try {
            return URLEncoder.encode(str, HttpRequest.CHARSET_UTF8);
        } catch (Throwable e) {
            throw new UnsupportedOperationException(e);
        }
    }

    /* renamed from: d */
    public static boolean m1094d(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        char charAt = str.charAt(0);
        if (charAt == '-' || charAt == '+') {
            int i = 1;
        } else {
            boolean z = false;
        }
        int length = str.length();
        if (i == 1 && length == 1) {
            return false;
        }
        while (i < length) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
            i++;
        }
        return true;
    }

    /* renamed from: e */
    public static int m1095e(String str) {
        return m1078a(str, 0);
    }
}
