package com.applovin.impl.sdk;

import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import com.applovin.sdk.AppLovinErrorCodes;
import com.applovin.sdk.AppLovinLogger;
import com.twitter.sdk.android.core.TwitterApiErrorConstants;
import io.fabric.sdk.android.services.network.HttpRequest;
import io.fabric.sdk.android.services.network.UrlUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.applovin.impl.sdk.v */
class C0517v {
    /* renamed from: a */
    private static final Object f964a = new JSONObject();
    /* renamed from: b */
    private final AppLovinSdkImpl f965b;
    /* renamed from: c */
    private final AppLovinLogger f966c;

    C0517v(AppLovinSdkImpl appLovinSdkImpl) {
        this.f965b = appLovinSdkImpl;
        this.f966c = appLovinSdkImpl.getLogger();
    }

    /* renamed from: a */
    private int m1167a(Throwable th) {
        if (th instanceof UnknownHostException) {
            return AppLovinErrorCodes.NO_NETWORK;
        }
        if (th instanceof SocketTimeoutException) {
            return AppLovinErrorCodes.FETCH_AD_TIMEOUT;
        }
        if (!(th instanceof IOException)) {
            return th instanceof JSONException ? -104 : -1;
        } else {
            String message = th.getMessage();
            return (message == null || !message.toLowerCase(Locale.ENGLISH).contains("authentication challenge")) ? -100 : 401;
        }
    }

    /* renamed from: a */
    private HttpURLConnection m1168a(String str, String str2, int i) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        httpURLConnection.setRequestMethod(str2);
        httpURLConnection.setConnectTimeout(i < 0 ? ((Integer) this.f965b.get(dj.f791t)).intValue() : i);
        if (i < 0) {
            i = ((Integer) this.f965b.get(dj.f793v)).intValue();
        }
        httpURLConnection.setReadTimeout(i);
        httpURLConnection.setDefaultUseCaches(false);
        httpURLConnection.setAllowUserInteraction(false);
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setInstanceFollowRedirects(true);
        httpURLConnection.setDoInput(true);
        return httpURLConnection;
    }

    /* renamed from: a */
    private static void m1169a(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (Exception e) {
            }
        }
    }

    /* renamed from: a */
    private void m1170a(String str, int i, String str2, String str3, Object obj, C0499w c0499w) throws JSONException {
        this.f966c.mo2288d("ConnectionManager", i + " received from from \"" + str3 + "\": " + str);
        if (i < Callback.DEFAULT_DRAG_ANIMATION_DURATION || i >= TwitterApiErrorConstants.REGISTRATION_INVALID_INPUT) {
            this.f966c.mo2289e("ConnectionManager", i + " error received from \"" + str3 + "\"");
            c0499w.mo2274a(i);
            return;
        }
        Object obj2 = (str == null || str.length() <= 2) ? null : 1;
        if (!(i == AppLovinErrorCodes.NO_FILL || obj2 == null)) {
            try {
                if (!(obj instanceof String)) {
                    if (obj instanceof fl) {
                        str = fm.m1104a(str, this.f965b);
                    } else if (obj instanceof JSONObject) {
                        Object jSONObject = new JSONObject(str);
                    } else {
                        this.f966c.mo2289e("ConnectionManager", "Unable to handle '" + obj.getClass().getName() + "'");
                        str = obj;
                    }
                }
                obj = str;
            } catch (Throwable e) {
                this.f966c.mo2290e("ConnectionManager", "Invalid JSON returned from \"" + str3 + "\"", e);
            } catch (Throwable e2) {
                this.f966c.mo2290e("ConnectionManager", "Invalid XML returned from \"" + str3 + "\"", e2);
            }
        }
        c0499w.mo2275a(obj, i);
    }

    /* renamed from: a */
    private void m1171a(String str, String str2, int i, long j) {
        this.f966c.mo2291i("ConnectionManager", "Successful " + str + " returned " + i + " in " + (((float) (System.currentTimeMillis() - j)) / 1000.0f) + " s over " + C0518x.m1176a(this.f965b) + " to \"" + str2 + "\"");
    }

    /* renamed from: a */
    private void m1172a(String str, String str2, int i, long j, Throwable th) {
        this.f966c.mo2290e("ConnectionManager", "Failed " + str + " returned " + i + " in " + (((float) (System.currentTimeMillis() - j)) / 1000.0f) + " s over " + C0518x.m1176a(this.f965b) + " to \"" + str2 + "\"", th);
    }

    /* renamed from: a */
    private static void m1173a(HttpURLConnection httpURLConnection) {
        if (httpURLConnection != null) {
            try {
                httpURLConnection.disconnect();
            } catch (Exception e) {
            }
        }
    }

    /* renamed from: a */
    void m1174a(String str, C0499w c0499w) {
        m1175a(str, HttpRequest.METHOD_GET, -1, null, "", true, c0499w);
    }

    /* renamed from: a */
    void m1175a(String str, String str2, int i, JSONObject jSONObject, Object obj, boolean z, C0499w c0499w) {
        long currentTimeMillis;
        InputStream inputStream;
        HttpURLConnection a;
        Throwable th;
        HttpURLConnection httpURLConnection;
        int a2;
        Throwable th2;
        InputStream inputStream2;
        if (str == null) {
            throw new IllegalArgumentException("No endpoint specified");
        } else if (str2 == null) {
            throw new IllegalArgumentException("No method specified");
        } else if (c0499w == null) {
            throw new IllegalArgumentException("No callback specified");
        } else if (str.toLowerCase().startsWith("http")) {
            String replace;
            String jSONObject2;
            PrintWriter printWriter;
            String contentType;
            if (((Boolean) this.f965b.get(dj.bw)).booleanValue()) {
                if (!str.contains("https://")) {
                    this.f965b.getLogger().mo2294w("ConnectionManager", "Plaintext HTTP operation requested; upgrading to HTTPS due to universal SSL setting...");
                    replace = str.replace("http://", "https://");
                    currentTimeMillis = System.currentTimeMillis();
                    inputStream = null;
                    this.f966c.mo2291i("ConnectionManager", "Sending " + str2 + " request to \"" + replace + "\"...");
                    a = m1168a(replace, str2, i);
                    if (jSONObject != null) {
                        try {
                            jSONObject2 = jSONObject.toString();
                            this.f966c.mo2288d("ConnectionManager", "Request to \"" + replace + "\" is " + jSONObject2);
                            a.setRequestProperty(HttpRequest.HEADER_CONTENT_TYPE, "application/json; charset=utf-8");
                            a.setDoOutput(true);
                            a.setFixedLengthStreamingMode(jSONObject2.getBytes(Charset.forName(HttpRequest.CHARSET_UTF8)).length);
                            printWriter = new PrintWriter(new OutputStreamWriter(a.getOutputStream(), UrlUtils.UTF8));
                            printWriter.print(jSONObject2);
                            printWriter.close();
                        } catch (Throwable th3) {
                            th2 = th3;
                            C0517v.m1169a(inputStream);
                            C0517v.m1173a(a);
                            throw th2;
                        }
                    }
                    try {
                        a2 = a.getResponseCode();
                        contentType = a.getContentType();
                        if (a2 <= 0) {
                            m1171a(str2, replace, a2, currentTimeMillis);
                            inputStream2 = a.getInputStream();
                            if (z) {
                                c0499w.mo2275a(obj, a2);
                            } else {
                                try {
                                    m1170a(C0518x.m1177a(inputStream2), a.getResponseCode(), contentType, replace, obj, c0499w);
                                } catch (MalformedURLException e) {
                                    if (z) {
                                        try {
                                            c0499w.mo2274a(-901);
                                            C0517v.m1169a(inputStream2);
                                            C0517v.m1173a(a);
                                        } catch (Throwable th4) {
                                            inputStream = inputStream2;
                                            th2 = th4;
                                            C0517v.m1169a(inputStream);
                                            C0517v.m1173a(a);
                                            throw th2;
                                        }
                                    }
                                    c0499w.mo2275a(obj, -901);
                                    C0517v.m1169a(inputStream2);
                                    C0517v.m1173a(a);
                                }
                            }
                        }
                        m1172a(str2, replace, a2, currentTimeMillis, null);
                        c0499w.mo2274a(a2);
                        inputStream2 = null;
                    } catch (MalformedURLException e2) {
                        inputStream2 = null;
                        if (z) {
                            c0499w.mo2274a(-901);
                            C0517v.m1169a(inputStream2);
                            C0517v.m1173a(a);
                        }
                        c0499w.mo2275a(obj, -901);
                        C0517v.m1169a(inputStream2);
                        C0517v.m1173a(a);
                    }
                    C0517v.m1169a(inputStream2);
                    C0517v.m1173a(a);
                }
            }
            replace = str;
            currentTimeMillis = System.currentTimeMillis();
            inputStream = null;
            try {
                this.f966c.mo2291i("ConnectionManager", "Sending " + str2 + " request to \"" + replace + "\"...");
                a = m1168a(replace, str2, i);
                if (jSONObject != null) {
                    jSONObject2 = jSONObject.toString();
                    this.f966c.mo2288d("ConnectionManager", "Request to \"" + replace + "\" is " + jSONObject2);
                    a.setRequestProperty(HttpRequest.HEADER_CONTENT_TYPE, "application/json; charset=utf-8");
                    a.setDoOutput(true);
                    a.setFixedLengthStreamingMode(jSONObject2.getBytes(Charset.forName(HttpRequest.CHARSET_UTF8)).length);
                    printWriter = new PrintWriter(new OutputStreamWriter(a.getOutputStream(), UrlUtils.UTF8));
                    printWriter.print(jSONObject2);
                    printWriter.close();
                }
                a2 = a.getResponseCode();
                contentType = a.getContentType();
                if (a2 <= 0) {
                    m1172a(str2, replace, a2, currentTimeMillis, null);
                    c0499w.mo2274a(a2);
                    inputStream2 = null;
                } else {
                    m1171a(str2, replace, a2, currentTimeMillis);
                    inputStream2 = a.getInputStream();
                    if (z) {
                        c0499w.mo2275a(obj, a2);
                    } else {
                        m1170a(C0518x.m1177a(inputStream2), a.getResponseCode(), contentType, replace, obj, c0499w);
                    }
                }
                C0517v.m1169a(inputStream2);
                C0517v.m1173a(a);
            } catch (Throwable th42) {
                a = null;
                th2 = th42;
                C0517v.m1169a(inputStream);
                C0517v.m1173a(a);
                throw th2;
            }
        } else {
            this.f966c.userError("ConnectionManager", "Requested postback submission to non HTTP endpoint " + str + "; skipping...");
            c0499w.mo2274a(AppLovinErrorCodes.INVALID_URL);
        }
    }
}
