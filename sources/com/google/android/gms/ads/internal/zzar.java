package com.google.android.gms.ads.internal;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.view.View.OnClickListener;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.internal.zzaff;
import com.google.android.gms.internal.zzajc;
import com.google.android.gms.internal.zzaka;
import com.google.android.gms.internal.zznq;
import com.google.android.gms.internal.zzns;
import com.google.android.gms.internal.zzos;
import com.google.android.gms.internal.zzot;
import com.google.android.gms.internal.zzrd;
import com.google.android.gms.internal.zzuh;
import com.google.android.gms.internal.zzvc;
import com.google.android.gms.internal.zzvf;
import com.google.android.gms.internal.zzzn;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import org.json.JSONException;
import org.json.JSONObject;

@zzzn
public final class zzar {
    static zzrd zza(@Nullable zzvc zzvc, @Nullable zzvf zzvf, zzab zzab) {
        return new zzaw(zzvc, zzab, zzvf);
    }

    private static String zza(@Nullable Bitmap bitmap) {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (bitmap == null) {
            zzajc.zzaT("Bitmap is null. Returning empty string");
            return "";
        }
        bitmap.compress(CompressFormat.PNG, 100, byteArrayOutputStream);
        String encodeToString = Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
        String valueOf = String.valueOf("data:image/png;base64,");
        encodeToString = String.valueOf(encodeToString);
        return encodeToString.length() != 0 ? valueOf.concat(encodeToString) : new String(valueOf);
    }

    static String zza(@Nullable zzos zzos) {
        if (zzos == null) {
            zzajc.zzaT("Image is null. Returning empty string");
            return "";
        }
        try {
            Uri uri = zzos.getUri();
            if (uri != null) {
                return uri.toString();
            }
        } catch (RemoteException e) {
            zzajc.zzaT("Unable to get image uri. Trying data uri next");
        }
        return zzb(zzos);
    }

    private static JSONObject zza(@Nullable Bundle bundle, String str) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (bundle == null || TextUtils.isEmpty(str)) {
            return jSONObject;
        }
        JSONObject jSONObject2 = new JSONObject(str);
        Iterator keys = jSONObject2.keys();
        while (keys.hasNext()) {
            String str2 = (String) keys.next();
            if (bundle.containsKey(str2)) {
                if ("image".equals(jSONObject2.getString(str2))) {
                    Object obj = bundle.get(str2);
                    if (obj instanceof Bitmap) {
                        jSONObject.put(str2, zza((Bitmap) obj));
                    } else {
                        zzajc.zzaT("Invalid type. An image type extra should return a bitmap");
                    }
                } else if (bundle.get(str2) instanceof Bitmap) {
                    zzajc.zzaT("Invalid asset type. Bitmap should be returned only for image type");
                } else {
                    jSONObject.put(str2, String.valueOf(bundle.get(str2)));
                }
            }
        }
        return jSONObject;
    }

    public static boolean zza(zzaka zzaka, zzuh zzuh, CountDownLatch countDownLatch) {
        boolean z;
        try {
            View view = zzaka.getView();
            if (view == null) {
                zzajc.zzaT("AdWebView is null");
                z = false;
            } else {
                view.setVisibility(4);
                List list = zzuh.zzMG.zzLV;
                if (list == null || list.isEmpty()) {
                    zzajc.zzaT("No template ids present in mediation response");
                    z = false;
                } else {
                    zzaka.zziw().zza("/nativeExpressAssetsLoaded", new zzau(countDownLatch));
                    zzaka.zziw().zza("/nativeExpressAssetsLoadingFailed", new zzav(countDownLatch));
                    zzvc zzfq = zzuh.zzMH.zzfq();
                    zzvf zzfr = zzuh.zzMH.zzfr();
                    if (list.contains("2") && zzfq != null) {
                        zzaka.zziw().zza(new zzas(new zznq(zzfq.getHeadline(), zzfq.getImages(), zzfq.getBody(), zzfq.zzeh(), zzfq.getCallToAction(), zzfq.getStarRating(), zzfq.getStore(), zzfq.getPrice(), null, zzfq.getExtras(), null, null), zzuh.zzMG.zzLU, zzaka));
                    } else if (!list.contains(AppEventsConstants.EVENT_PARAM_VALUE_YES) || zzfr == null) {
                        zzajc.zzaT("No matching template id and mapper");
                        z = false;
                    } else {
                        zzaka.zziw().zza(new zzat(new zzns(zzfr.getHeadline(), zzfr.getImages(), zzfr.getBody(), zzfr.zzem(), zzfr.getCallToAction(), zzfr.getAdvertiser(), null, zzfr.getExtras(), null, null), zzuh.zzMG.zzLU, zzaka));
                    }
                    String str = zzuh.zzMG.zzLS;
                    String str2 = zzuh.zzMG.zzLT;
                    if (str2 != null) {
                        zzaka.loadDataWithBaseURL(str2, str, "text/html", HttpRequest.CHARSET_UTF8, null);
                    } else {
                        zzaka.loadData(str, "text/html", HttpRequest.CHARSET_UTF8);
                    }
                    z = true;
                }
            }
        } catch (Throwable e) {
            zzajc.zzc("Unable to invoke load assets", e);
            z = false;
        } catch (RuntimeException e2) {
            countDownLatch.countDown();
            throw e2;
        }
        if (!z) {
            countDownLatch.countDown();
        }
        return z;
    }

    private static String zzb(zzos zzos) {
        try {
            IObjectWrapper zzeg = zzos.zzeg();
            if (zzeg == null) {
                zzajc.zzaT("Drawable is null. Returning empty string");
                return "";
            }
            Drawable drawable = (Drawable) zzn.zzE(zzeg);
            if (drawable instanceof BitmapDrawable) {
                return zza(((BitmapDrawable) drawable).getBitmap());
            }
            zzajc.zzaT("Drawable is not an instance of BitmapDrawable. Returning empty string");
            return "";
        } catch (RemoteException e) {
            zzajc.zzaT("Unable to get drawable. Returning empty string");
            return "";
        }
    }

    private static void zzb(zzaka zzaka) {
        OnClickListener zziL = zzaka.zziL();
        if (zziL != null) {
            zziL.onClick(zzaka.getView());
        }
    }

    @Nullable
    public static View zzd(@Nullable zzaff zzaff) {
        if (zzaff == null) {
            zzajc.m1216e("AdState is null");
            return null;
        } else if (zze(zzaff) && zzaff.zzPg != null) {
            return zzaff.zzPg.getView();
        } else {
            try {
                IObjectWrapper view = zzaff.zzMH != null ? zzaff.zzMH.getView() : null;
                if (view != null) {
                    return (View) zzn.zzE(view);
                }
                zzajc.zzaT("View in mediation adapter is null.");
                return null;
            } catch (Throwable e) {
                zzajc.zzc("Could not get View from mediation adapter.", e);
                return null;
            }
        }
    }

    @Nullable
    private static zzos zzd(Object obj) {
        return obj instanceof IBinder ? zzot.zzi((IBinder) obj) : null;
    }

    public static boolean zze(@Nullable zzaff zzaff) {
        return (zzaff == null || !zzaff.zzTo || zzaff.zzMG == null || zzaff.zzMG.zzLS == null) ? false : true;
    }
}
