package com.google.android.gms.internal;

import android.content.Context;
import android.text.TextUtils;
import com.facebook.GraphResponse;
import com.google.android.gms.ads.internal.zzbs;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.io.BufferedOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzzn
public final class zzre implements zzrd {
    private final Context mContext;
    private final zzaje zztW;

    public zzre(Context context, zzaje zzaje) {
        this.mContext = context;
        this.zztW = zzaje;
    }

    private final zzrj zza(zzri zzri) {
        Exception e;
        HttpURLConnection httpURLConnection;
        zzrj zzrj;
        Throwable th;
        HttpURLConnection httpURLConnection2 = null;
        try {
            HttpURLConnection httpURLConnection3 = (HttpURLConnection) zzri.zzez().openConnection();
            try {
                zzbs.zzbz().zza(this.mContext, this.zztW.zzaP, false, httpURLConnection3);
                ArrayList zzeA = zzri.zzeA();
                int size = zzeA.size();
                int i = 0;
                while (i < size) {
                    Object obj = zzeA.get(i);
                    i++;
                    zzrh zzrh = (zzrh) obj;
                    httpURLConnection3.addRequestProperty(zzrh.getKey(), zzrh.getValue());
                }
                if (!TextUtils.isEmpty(zzri.zzeB())) {
                    httpURLConnection3.setDoOutput(true);
                    byte[] bytes = zzri.zzeB().getBytes();
                    httpURLConnection3.setFixedLengthStreamingMode(bytes.length);
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(httpURLConnection3.getOutputStream());
                    bufferedOutputStream.write(bytes);
                    bufferedOutputStream.close();
                }
                List arrayList = new ArrayList();
                if (httpURLConnection3.getHeaderFields() != null) {
                    for (Entry entry : httpURLConnection3.getHeaderFields().entrySet()) {
                        for (String zzrh2 : (List) entry.getValue()) {
                            arrayList.add(new zzrh((String) entry.getKey(), zzrh2));
                        }
                    }
                }
                String zzey = zzri.zzey();
                i = httpURLConnection3.getResponseCode();
                zzbs.zzbz();
                zzrj zzrj2 = new zzrj(this, true, new zzrk(zzey, i, arrayList, zzagz.zza(new InputStreamReader(httpURLConnection3.getInputStream()))), null);
                if (httpURLConnection3 != null) {
                    httpURLConnection3.disconnect();
                }
                return zzrj2;
            } catch (Exception e2) {
                e = e2;
                httpURLConnection = httpURLConnection3;
                try {
                    zzrj = new zzrj(this, false, null, e.toString());
                    if (httpURLConnection != null) {
                        return zzrj;
                    }
                    httpURLConnection.disconnect();
                    return zzrj;
                } catch (Throwable th2) {
                    th = th2;
                    httpURLConnection2 = httpURLConnection;
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                Throwable th4 = th3;
                httpURLConnection2 = httpURLConnection3;
                th = th4;
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                }
                throw th;
            }
        } catch (Exception e3) {
            httpURLConnection = null;
            e = e3;
            zzrj = new zzrj(this, false, null, e.toString());
            if (httpURLConnection != null) {
                return zzrj;
            }
            httpURLConnection.disconnect();
            return zzrj;
        } catch (Throwable th5) {
            th = th5;
            if (httpURLConnection2 != null) {
                httpURLConnection2.disconnect();
            }
            throw th;
        }
    }

    private static JSONObject zza(zzrk zzrk) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("http_request_id", zzrk.zzey());
            if (zzrk.getBody() != null) {
                jSONObject.put("body", zzrk.getBody());
            }
            JSONArray jSONArray = new JSONArray();
            for (zzrh zzrh : zzrk.zzeD()) {
                jSONArray.put(new JSONObject().put("key", zzrh.getKey()).put(Param.VALUE, zzrh.getValue()));
            }
            jSONObject.put("headers", jSONArray);
            jSONObject.put("response_code", zzrk.getResponseCode());
        } catch (Throwable e) {
            zzajc.zzb("Error constructing JSON for http response.", e);
        }
        return jSONObject;
    }

    private static zzri zzc(JSONObject jSONObject) {
        URL url;
        String optString = jSONObject.optString("http_request_id");
        String optString2 = jSONObject.optString("url");
        String optString3 = jSONObject.optString("post_body", null);
        try {
            url = new URL(optString2);
        } catch (Throwable e) {
            zzajc.zzb("Error constructing http request.", e);
            url = null;
        }
        ArrayList arrayList = new ArrayList();
        JSONArray optJSONArray = jSONObject.optJSONArray("headers");
        if (optJSONArray == null) {
            optJSONArray = new JSONArray();
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                arrayList.add(new zzrh(optJSONObject.optString("key"), optJSONObject.optString(Param.VALUE)));
            }
        }
        return new zzri(optString, url, arrayList, optString3);
    }

    public final JSONObject zzR(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONObject jSONObject2 = new JSONObject();
            Object obj = "";
            try {
                obj = jSONObject.optString("http_request_id");
                zzrj zza = zza(zzc(jSONObject));
                if (zza.isSuccess()) {
                    jSONObject2.put("response", zza(zza.zzeC()));
                    jSONObject2.put(GraphResponse.SUCCESS_KEY, true);
                    return jSONObject2;
                }
                jSONObject2.put("response", new JSONObject().put("http_request_id", obj));
                jSONObject2.put(GraphResponse.SUCCESS_KEY, false);
                jSONObject2.put("reason", zza.getReason());
                return jSONObject2;
            } catch (Exception e) {
                try {
                    jSONObject2.put("response", new JSONObject().put("http_request_id", obj));
                    jSONObject2.put(GraphResponse.SUCCESS_KEY, false);
                    jSONObject2.put("reason", e.toString());
                    return jSONObject2;
                } catch (JSONException e2) {
                    return jSONObject2;
                }
            }
        } catch (JSONException e3) {
            zzajc.m1216e("The request is not a valid JSON.");
            try {
                return new JSONObject().put(GraphResponse.SUCCESS_KEY, false);
            } catch (JSONException e4) {
                return new JSONObject();
            }
        }
    }

    public final void zza(zzaka zzaka, Map<String, String> map) {
        zzagt.zza(new zzrf(this, map, zzaka));
    }
}
