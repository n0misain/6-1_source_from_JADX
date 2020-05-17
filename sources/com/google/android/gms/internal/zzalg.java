package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.annotation.Nullable;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import com.google.android.gms.ads.internal.zzbs;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@zzzn
@TargetApi(11)
public class zzalg extends zzakb {
    public zzalg(zzaka zzaka, boolean z) {
        super(zzaka, z);
    }

    protected final WebResourceResponse zza(WebView webView, String str, @Nullable Map<String, String> map) {
        String str2;
        Exception e;
        if (webView instanceof zzaka) {
            zzaka zzaka = (zzaka) webView;
            if (this.zztr != null) {
                this.zztr.zza(str, map, 1);
            }
            if (!"mraid.js".equalsIgnoreCase(new File(str).getName())) {
                return super.shouldInterceptRequest(webView, str);
            }
            if (zzaka.zziw() != null) {
                zzaka.zziw().zzfL();
            }
            String str3 = zzaka.zzam().zzAt ? (String) zzbs.zzbL().zzd(zzmo.zzCP) : zzaka.zziA() ? (String) zzbs.zzbL().zzd(zzmo.zzCO) : (String) zzbs.zzbL().zzd(zzmo.zzCN);
            try {
                Context context = zzaka.getContext();
                str2 = zzaka.zziz().zzaP;
                Map hashMap = new HashMap();
                hashMap.put("User-Agent", zzbs.zzbz().zzs(context, str2));
                hashMap.put(HttpRequest.HEADER_CACHE_CONTROL, "max-stale=3600");
                str2 = (String) new zzaie(context).zzb(str3, hashMap).get(60, TimeUnit.SECONDS);
                return str2 == null ? null : new WebResourceResponse("application/javascript", HttpRequest.CHARSET_UTF8, new ByteArrayInputStream(str2.getBytes(HttpRequest.CHARSET_UTF8)));
            } catch (IOException e2) {
                e = e2;
                str3 = "Could not fetch MRAID JS. ";
                str2 = String.valueOf(e.getMessage());
                zzajc.zzaT(str2.length() == 0 ? str3.concat(str2) : new String(str3));
                return null;
            } catch (ExecutionException e3) {
                e = e3;
                str3 = "Could not fetch MRAID JS. ";
                str2 = String.valueOf(e.getMessage());
                if (str2.length() == 0) {
                }
                zzajc.zzaT(str2.length() == 0 ? str3.concat(str2) : new String(str3));
                return null;
            } catch (InterruptedException e4) {
                e = e4;
                str3 = "Could not fetch MRAID JS. ";
                str2 = String.valueOf(e.getMessage());
                if (str2.length() == 0) {
                }
                zzajc.zzaT(str2.length() == 0 ? str3.concat(str2) : new String(str3));
                return null;
            } catch (TimeoutException e5) {
                e = e5;
                str3 = "Could not fetch MRAID JS. ";
                str2 = String.valueOf(e.getMessage());
                if (str2.length() == 0) {
                }
                zzajc.zzaT(str2.length() == 0 ? str3.concat(str2) : new String(str3));
                return null;
            }
        }
        zzajc.zzaT("Tried to intercept request from a WebView that wasn't an AdWebView.");
        return null;
    }
}
