package com.google.android.gms.internal;

import com.facebook.internal.NativeProtocol;
import com.facebook.internal.ServerProtocol;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import org.json.JSONObject;

@zzzn
public class zzwu {
    private final zzaka zzJH;
    private final String zzOf;

    public zzwu(zzaka zzaka) {
        this(zzaka, "");
    }

    public zzwu(zzaka zzaka, String str) {
        this.zzJH = zzaka;
        this.zzOf = str;
    }

    public final void zza(int i, int i2, int i3, int i4, float f, int i5) {
        try {
            this.zzJH.zzb("onScreenInfoChanged", new JSONObject().put(SettingsJsonConstants.ICON_WIDTH_KEY, i).put(SettingsJsonConstants.ICON_HEIGHT_KEY, i2).put("maxSizeWidth", i3).put("maxSizeHeight", i4).put("density", (double) f).put("rotation", i5));
        } catch (Throwable e) {
            zzajc.zzb("Error occured while obtaining screen information.", e);
        }
    }

    public final void zzan(String str) {
        try {
            this.zzJH.zzb("onError", new JSONObject().put("message", str).put(NativeProtocol.WEB_DIALOG_ACTION, this.zzOf));
        } catch (Throwable e) {
            zzajc.zzb("Error occurred while dispatching error event.", e);
        }
    }

    public final void zzao(String str) {
        try {
            this.zzJH.zzb("onReadyEventReceived", new JSONObject().put("js", str));
        } catch (Throwable e) {
            zzajc.zzb("Error occured while dispatching ready Event.", e);
        }
    }

    public final void zzap(String str) {
        try {
            this.zzJH.zzb("onStateChanged", new JSONObject().put(ServerProtocol.DIALOG_PARAM_STATE, str));
        } catch (Throwable e) {
            zzajc.zzb("Error occured while dispatching state change.", e);
        }
    }

    public final void zzb(int i, int i2, int i3, int i4) {
        try {
            this.zzJH.zzb("onSizeChanged", new JSONObject().put("x", i).put("y", i2).put(SettingsJsonConstants.ICON_WIDTH_KEY, i3).put(SettingsJsonConstants.ICON_HEIGHT_KEY, i4));
        } catch (Throwable e) {
            zzajc.zzb("Error occured while dispatching size change.", e);
        }
    }

    public final void zzc(int i, int i2, int i3, int i4) {
        try {
            this.zzJH.zzb("onDefaultPositionReceived", new JSONObject().put("x", i).put("y", i2).put(SettingsJsonConstants.ICON_WIDTH_KEY, i3).put(SettingsJsonConstants.ICON_HEIGHT_KEY, i4));
        } catch (Throwable e) {
            zzajc.zzb("Error occured while dispatching default position.", e);
        }
    }
}
