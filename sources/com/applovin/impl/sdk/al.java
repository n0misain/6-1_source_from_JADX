package com.applovin.impl.sdk;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;
import com.twitter.sdk.android.core.TwitterApiErrorConstants;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class al {
    /* renamed from: a */
    private final AppLovinLogger f495a;
    /* renamed from: b */
    private final AppLovinSdkImpl f496b;
    /* renamed from: c */
    private final String f497c = "FileManager";
    /* renamed from: d */
    private final Object f498d;

    al(AppLovinSdk appLovinSdk) {
        this.f496b = (AppLovinSdkImpl) appLovinSdk;
        this.f495a = appLovinSdk.getLogger();
        this.f498d = new Object();
    }

    /* renamed from: a */
    long m517a(long j) {
        return j / 1048576;
    }

    /* renamed from: a */
    public File m518a(String str, Context context, boolean z) {
        if (AppLovinSdkUtils.isValidString(str)) {
            this.f495a.mo2288d("FileManager", "Looking up cached resource: " + str);
            if (!m523a(context) && !z) {
                return null;
            }
            File file;
            if (str.contains(SettingsJsonConstants.APP_ICON_KEY)) {
                str = str.replace("/", EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR).replace(".", EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR);
            }
            synchronized (this.f498d) {
                File b = m529b(context);
                file = new File(b, str);
                try {
                    b.mkdirs();
                } catch (Exception e) {
                    return null;
                }
            }
            return file;
        }
        this.f496b.getLogger().mo2288d("FileManager", "Nothing to look up, skipping...");
        return null;
    }

    /* renamed from: a */
    String m519a(Context context, String str, String str2, boolean z) throws MalformedURLException {
        return m520a(context, str, false, str2, z);
    }

    /* renamed from: a */
    String m520a(Context context, String str, boolean z, String str2, boolean z2) throws MalformedURLException {
        if (str == null || str.equals("")) {
            this.f496b.getLogger().mo2288d("FileManager", "Nothing to cache, skipping...");
            return null;
        } else if (!z2 || fk.m1089a(this.f496b, str)) {
            this.f496b.getLogger().mo2288d("FileManager", "Starting caching of resource " + str);
            String lastPathSegment = Uri.parse(str).getLastPathSegment();
            if (AppLovinSdkUtils.isValidString(lastPathSegment) && AppLovinSdkUtils.isValidString(str2)) {
                lastPathSegment = str2 + lastPathSegment;
            }
            File a = m518a(lastPathSegment, context, false);
            if (a == null || !a.exists()) {
                this.f496b.getLogger().mo2288d("FileManager", "File does not exists for " + str + " begin caching...");
                return m526a(a, str) ? z ? Uri.fromFile(a).toString() : lastPathSegment : null;
            } else {
                this.f496b.getLogger().mo2288d("FileManager", "File exists for " + str);
                return z ? Uri.fromFile(a).toString() : lastPathSegment;
            }
        } else {
            this.f496b.getLogger().mo2288d("FileManager", "Domain is not whitelisted, skipping precache for URL " + str);
            return null;
        }
    }

    /* renamed from: a */
    void m521a(long j, Context context) {
        long c = (long) m531c();
        if (c == -1) {
            this.f495a.mo2288d("FileManager", "Cache has no maximum size set; skipping drop...");
        } else if (m517a(j) > c) {
            this.f495a.mo2288d("FileManager", "Cache has exceeded maximum size; dropping...");
            m536g(context);
            this.f496b.m468b().m878a("cache_drop_count");
        } else {
            this.f495a.mo2288d("FileManager", "Cache is present but under size limit; not dropping...");
        }
    }

    /* renamed from: a */
    boolean m522a() {
        return ((Boolean) this.f496b.get(dj.aJ)).booleanValue();
    }

    /* renamed from: a */
    protected boolean m523a(Context context) {
        if (C0516u.m1160a("android.permission.WRITE_EXTERNAL_STORAGE", context)) {
            return true;
        }
        if (C0516u.m1165d() && !((Boolean) this.f496b.get(dj.bG)).booleanValue()) {
            return true;
        }
        this.f496b.getLogger().mo2294w("FileManager", "Application lacks required WRITE_EXTERNAL_STORAGE manifest permission.");
        return false;
    }

    /* renamed from: a */
    boolean m524a(ByteArrayOutputStream byteArrayOutputStream, File file) {
        FileOutputStream fileOutputStream;
        boolean z;
        Throwable e;
        this.f495a.mo2288d("FileManager", "Writing resource to filesystem: " + file.getName());
        synchronized (this.f498d) {
            try {
                fileOutputStream = new FileOutputStream(file);
                try {
                    byteArrayOutputStream.writeTo(fileOutputStream);
                    z = true;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Exception e2) {
                        }
                    }
                } catch (IOException e3) {
                    e = e3;
                    try {
                        this.f495a.mo2290e("FileManager", "Unable to write data to file", e);
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (Exception e4) {
                                z = false;
                            }
                        }
                        z = false;
                        return z;
                    } catch (Throwable th) {
                        e = th;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (Exception e5) {
                            }
                        }
                        throw e;
                    }
                }
            } catch (IOException e6) {
                e = e6;
                fileOutputStream = null;
                this.f495a.mo2290e("FileManager", "Unable to write data to file", e);
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                z = false;
                return z;
            } catch (Throwable th2) {
                e = th2;
                fileOutputStream = null;
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                throw e;
            }
        }
        return z;
    }

    /* renamed from: a */
    boolean m525a(File file) {
        boolean delete;
        this.f495a.mo2288d("FileManager", "Removing file " + file.getName() + " from filesystem...");
        synchronized (this.f498d) {
            try {
                delete = file.delete();
            } catch (Throwable e) {
                this.f495a.mo2290e("FileManager", "Failed to remove file " + file.getName() + " from filesystem!", e);
                delete = false;
            }
        }
        return delete;
    }

    /* renamed from: a */
    boolean m526a(File file, String str) {
        ByteArrayOutputStream byteArrayOutputStream;
        Throwable th;
        HttpURLConnection httpURLConnection;
        InputStream inputStream = null;
        if (file == null) {
            this.f495a.mo2294w("FileManager", "Unable to start caching of " + str + " into null localResource");
            return false;
        }
        this.f495a.mo2288d("FileManager", "Starting caching of " + str + " into " + file.getAbsoluteFile());
        if (((Boolean) this.f496b.get(dj.bw)).booleanValue() && !str.contains("https://")) {
            this.f496b.getLogger().mo2294w("FileManager", "Plaintext HTTP operation requested; upgrading to HTTPS due to universal SSL setting...");
            str = str.replace("http://", "https://");
        }
        InputStream inputStream2 = null;
        ByteArrayOutputStream byteArrayOutputStream2;
        try {
            byteArrayOutputStream2 = new ByteArrayOutputStream();
            try {
                HttpURLConnection httpURLConnection2 = (HttpURLConnection) new URL(str).openConnection();
                try {
                    httpURLConnection2.setConnectTimeout(((Integer) this.f496b.get(dj.f791t)).intValue());
                    httpURLConnection2.setReadTimeout(((Integer) this.f496b.get(dj.f793v)).intValue());
                    httpURLConnection2.setDefaultUseCaches(true);
                    httpURLConnection2.setUseCaches(true);
                    httpURLConnection2.setAllowUserInteraction(false);
                    httpURLConnection2.setInstanceFollowRedirects(true);
                    int responseCode = httpURLConnection2.getResponseCode();
                    if (responseCode < Callback.DEFAULT_DRAG_ANIMATION_DURATION || responseCode >= TwitterApiErrorConstants.REGISTRATION_INVALID_INPUT) {
                        if (null != null) {
                            try {
                                inputStream2.close();
                            } catch (Exception e) {
                            }
                        }
                        if (byteArrayOutputStream2 != null) {
                            try {
                                byteArrayOutputStream2.close();
                            } catch (Exception e2) {
                            }
                        }
                        if (httpURLConnection2 != null) {
                            try {
                                httpURLConnection2.disconnect();
                            } catch (Exception e3) {
                            }
                        }
                        return false;
                    }
                    inputStream = httpURLConnection2.getInputStream();
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = inputStream.read(bArr, 0, bArr.length);
                        if (read < 0) {
                            break;
                        }
                        try {
                            byteArrayOutputStream2.write(bArr, 0, read);
                        } catch (Exception e4) {
                            try {
                                byteArrayOutputStream2.close();
                            } catch (Exception e5) {
                            }
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (Exception e6) {
                                }
                            }
                            if (byteArrayOutputStream2 != null) {
                                try {
                                    byteArrayOutputStream2.close();
                                } catch (Exception e7) {
                                }
                            }
                            if (httpURLConnection2 != null) {
                                try {
                                    httpURLConnection2.disconnect();
                                } catch (Exception e8) {
                                }
                            }
                            return false;
                        }
                    }
                    if (m524a(byteArrayOutputStream2, file)) {
                        this.f495a.mo2288d("FileManager", "Caching completed for " + file);
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (Exception e9) {
                            }
                        }
                        if (byteArrayOutputStream2 != null) {
                            try {
                                byteArrayOutputStream2.close();
                            } catch (Exception e10) {
                            }
                        }
                        if (httpURLConnection2 != null) {
                            try {
                                httpURLConnection2.disconnect();
                            } catch (Exception e11) {
                            }
                        }
                        return true;
                    }
                    this.f495a.mo2289e("FileManager", "Failed to cache \"" + str + "\" into \"" + file.getAbsolutePath() + "\"");
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Exception e12) {
                        }
                    }
                    if (byteArrayOutputStream2 != null) {
                        try {
                            byteArrayOutputStream2.close();
                        } catch (Exception e13) {
                        }
                    }
                    if (httpURLConnection2 != null) {
                        try {
                            httpURLConnection2.disconnect();
                        } catch (Exception e14) {
                        }
                    }
                    return false;
                } catch (Throwable e15) {
                    byteArrayOutputStream = byteArrayOutputStream2;
                    HttpURLConnection httpURLConnection3 = httpURLConnection2;
                    th = e15;
                    httpURLConnection = httpURLConnection3;
                    try {
                        this.f495a.mo2290e("FileManager", "Failed to cache \"" + str + "\" into \"" + file.getAbsolutePath() + "\"", th);
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (Exception e16) {
                            }
                        }
                        if (byteArrayOutputStream != null) {
                            try {
                                byteArrayOutputStream.close();
                            } catch (Exception e17) {
                            }
                        }
                        if (httpURLConnection != null) {
                            try {
                                httpURLConnection.disconnect();
                            } catch (Exception e18) {
                            }
                        }
                        return false;
                    } catch (Throwable th2) {
                        th = th2;
                        byteArrayOutputStream2 = byteArrayOutputStream;
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (Exception e19) {
                            }
                        }
                        if (byteArrayOutputStream2 != null) {
                            try {
                                byteArrayOutputStream2.close();
                            } catch (Exception e20) {
                            }
                        }
                        if (httpURLConnection != null) {
                            try {
                                httpURLConnection.disconnect();
                            } catch (Exception e21) {
                            }
                        }
                        throw th;
                    }
                } catch (Throwable e152) {
                    Throwable th3 = e152;
                    httpURLConnection = httpURLConnection2;
                    th = th3;
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (byteArrayOutputStream2 != null) {
                        byteArrayOutputStream2.close();
                    }
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    throw th;
                }
            } catch (IOException e22) {
                th = e22;
                httpURLConnection = null;
                byteArrayOutputStream = byteArrayOutputStream2;
                this.f495a.mo2290e("FileManager", "Failed to cache \"" + str + "\" into \"" + file.getAbsolutePath() + "\"", th);
                if (inputStream != null) {
                    inputStream.close();
                }
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                return false;
            } catch (Throwable th4) {
                th = th4;
                httpURLConnection = null;
                if (inputStream != null) {
                    inputStream.close();
                }
                if (byteArrayOutputStream2 != null) {
                    byteArrayOutputStream2.close();
                }
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                throw th;
            }
        } catch (IOException e23) {
            th = e23;
            httpURLConnection = null;
            byteArrayOutputStream = null;
            this.f495a.mo2290e("FileManager", "Failed to cache \"" + str + "\" into \"" + file.getAbsolutePath() + "\"", th);
            if (inputStream != null) {
                inputStream.close();
            }
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            return false;
        } catch (Throwable th5) {
            th = th5;
            httpURLConnection = null;
            byteArrayOutputStream2 = null;
            if (inputStream != null) {
                inputStream.close();
            }
            if (byteArrayOutputStream2 != null) {
                byteArrayOutputStream2.close();
            }
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            throw th;
        }
    }

    /* renamed from: a */
    public boolean m527a(String str, Context context) {
        boolean b;
        synchronized (this.f498d) {
            b = m530b(str, context, false);
        }
        return b;
    }

    /* renamed from: b */
    long m528b() {
        long longValue = ((Long) this.f496b.get(dj.aK)).longValue();
        return (longValue < 0 || !m522a()) ? -1 : longValue;
    }

    /* renamed from: b */
    File m529b(Context context) {
        return m523a(context) ? new File(context.getExternalFilesDir(null), "al") : new File(context.getCacheDir(), "al");
    }

    /* renamed from: b */
    public boolean m530b(String str, Context context, boolean z) {
        boolean z2;
        synchronized (this.f498d) {
            File a = m518a(str, context, z);
            z2 = (a == null || !a.exists() || a.isDirectory()) ? false : true;
        }
        return z2;
    }

    /* renamed from: c */
    int m531c() {
        int intValue = ((Integer) this.f496b.get(dj.aL)).intValue();
        return (intValue < 0 || !m522a()) ? -1 : intValue;
    }

    /* renamed from: c */
    public List m532c(Context context) {
        File b = m529b(context);
        if (!b.isDirectory()) {
            return new ArrayList(0);
        }
        List asList;
        synchronized (this.f498d) {
            asList = Arrays.asList(b.listFiles());
        }
        return asList;
    }

    /* renamed from: d */
    public boolean m533d(Context context) {
        if (((Boolean) this.f496b.get(dj.bH)).booleanValue()) {
            try {
                m518a(".nomedia", context, false);
                File file = new File(m529b(context), ".nomedia");
                if (file != null) {
                    if (file.exists()) {
                        return true;
                    }
                    this.f496b.getLogger().mo2288d("FileManager", "Dropping .nomedia file at " + file.getAbsolutePath());
                    return file.createNewFile();
                }
            } catch (Throwable e) {
                this.f496b.getLogger().mo2295w("FileManager", "Failed to create nomedia file", e);
            }
        }
        return false;
    }

    /* renamed from: e */
    void m534e(Context context) {
        try {
            if (!m522a()) {
                return;
            }
            if (this.f496b.isEnabled()) {
                this.f495a.mo2289e("FileManager", "Cannot empty file cache after SDK has completed initialization and ad loads are in progress!");
                return;
            }
            this.f495a.mo2288d("FileManager", "Compacting cache...");
            synchronized (this.f498d) {
                m521a(m535f(context), context);
            }
        } catch (Throwable e) {
            this.f495a.mo2290e("FileManager", "Caught exception while compacting cache!", e);
            this.f496b.getSettingsManager().m819a(dj.aJ, Boolean.valueOf(false));
            this.f496b.getSettingsManager().m822b();
        }
    }

    /* renamed from: f */
    long m535f(Context context) {
        long j = 0;
        long b = m528b();
        Object obj = b != -1 ? 1 : null;
        long toSeconds = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
        synchronized (this.f498d) {
            for (File file : m532c(context)) {
                Object obj2 = null;
                if (obj != null && toSeconds - TimeUnit.MILLISECONDS.toSeconds(file.lastModified()) > b) {
                    this.f495a.mo2288d("FileManager", "File " + file.getName() + " has expired, removing...");
                    m525a(file);
                    obj2 = 1;
                }
                if (obj2 != null) {
                    this.f496b.m468b().m878a("cached_files_expired");
                } else {
                    j += file.length();
                }
            }
        }
        return j;
    }

    /* renamed from: g */
    void m536g(Context context) {
        synchronized (this.f498d) {
            for (File a : m532c(context)) {
                m525a(a);
            }
        }
    }
}
