package com.google.android.gms.internal;

import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Debug.MemoryInfo;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.applinks.AppLinkData;
import com.facebook.internal.AnalyticsEvents;
import com.google.android.gms.ads.internal.zzbs;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import io.fabric.sdk.android.services.network.HttpRequest;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzzn
public final class zzabt {
    private static final SimpleDateFormat zzUK = new SimpleDateFormat("yyyyMMdd", Locale.US);

    public static zzaai zza(Context context, zzaae zzaae, String str) {
        String optString;
        try {
            String str2;
            JSONObject jSONObject = new JSONObject(str);
            String optString2 = jSONObject.optString("ad_base_url", null);
            Object optString3 = jSONObject.optString("ad_url", null);
            String optString4 = jSONObject.optString("ad_size", null);
            String optString5 = jSONObject.optString("ad_slot_size", optString4);
            boolean z = (zzaae == null || zzaae.zzSF == 0) ? false : true;
            CharSequence optString6 = jSONObject.optString("ad_json", null);
            if (optString6 == null) {
                optString6 = jSONObject.optString("ad_html", null);
            }
            if (optString6 == null) {
                optString6 = jSONObject.optString("body", null);
            }
            long j = -1;
            String optString7 = jSONObject.optString("debug_dialog", null);
            String optString8 = jSONObject.optString("debug_signals", null);
            long j2 = jSONObject.has("interstitial_timeout") ? (long) (jSONObject.getDouble("interstitial_timeout") * 1000.0d) : -1;
            optString = jSONObject.optString("orientation", null);
            int i = -1;
            if ("portrait".equals(optString)) {
                i = zzbs.zzbB().zzhU();
            } else if ("landscape".equals(optString)) {
                i = zzbs.zzbB().zzhT();
            }
            zzaai zzaai = null;
            if (!TextUtils.isEmpty(optString6) || TextUtils.isEmpty(optString3)) {
                CharSequence charSequence = optString6;
            } else {
                zzaai = zzabm.zza(zzaae, context, zzaae.zzvT.zzaP, optString3, null, null, null, null);
                optString2 = zzaai.zzPi;
                str2 = zzaai.body;
                j = zzaai.zzTs;
            }
            if (str2 == null) {
                return new zzaai(0);
            }
            long j3;
            String optString9;
            String str3;
            boolean optBoolean;
            JSONArray optJSONArray = jSONObject.optJSONArray("click_urls");
            List list = zzaai == null ? null : zzaai.zzMa;
            if (optJSONArray != null) {
                list = zza(optJSONArray, list);
            }
            optJSONArray = jSONObject.optJSONArray("impression_urls");
            List list2 = zzaai == null ? null : zzaai.zzMb;
            if (optJSONArray != null) {
                list2 = zza(optJSONArray, list2);
            }
            optJSONArray = jSONObject.optJSONArray("manual_impression_urls");
            List list3 = zzaai == null ? null : zzaai.zzTq;
            if (optJSONArray != null) {
                list3 = zza(optJSONArray, list3);
            }
            if (zzaai != null) {
                if (zzaai.orientation != -1) {
                    i = zzaai.orientation;
                }
                if (zzaai.zzTn > 0) {
                    j3 = zzaai.zzTn;
                    optString9 = jSONObject.optString("active_view");
                    str3 = null;
                    optBoolean = jSONObject.optBoolean("ad_is_javascript", false);
                    if (optBoolean) {
                        str3 = jSONObject.optString("ad_passback_url", null);
                    }
                    return new zzaai(zzaae, optString2, str2, list, list2, j3, jSONObject.optBoolean("mediation", false), jSONObject.optLong("mediation_config_cache_time_milliseconds", -1), list3, jSONObject.optLong("refresh_interval_milliseconds", -1), i, optString4, j, optString7, optBoolean, str3, optString9, jSONObject.optBoolean("custom_render_allowed", false), z, zzaae.zzSH, jSONObject.optBoolean("content_url_opted_out", true), jSONObject.optBoolean("prefetch", false), jSONObject.optString("gws_query_id", ""), SettingsJsonConstants.ICON_HEIGHT_KEY.equals(jSONObject.optString("fluid", "")), jSONObject.optBoolean("native_express", false), zzaee.zza(jSONObject.optJSONArray("rewards")), zza(jSONObject.optJSONArray("video_start_urls"), null), zza(jSONObject.optJSONArray("video_complete_urls"), null), jSONObject.optBoolean("use_displayed_impression", false), zzaak.zze(jSONObject.optJSONObject("auto_protection_configuration")), zzaae.zzSV, jSONObject.optString("set_cookie", ""), zza(jSONObject.optJSONArray("remote_ping_urls"), null), jSONObject.optBoolean("render_in_browser", zzaae.zzMe), optString5, zzaeq.zzf(jSONObject.optJSONObject("safe_browsing")), optString8, jSONObject.optBoolean("content_vertical_opted_out", true), zzaae.zzTh);
                }
            }
            j3 = j2;
            optString9 = jSONObject.optString("active_view");
            str3 = null;
            optBoolean = jSONObject.optBoolean("ad_is_javascript", false);
            if (optBoolean) {
                str3 = jSONObject.optString("ad_passback_url", null);
            }
            return new zzaai(zzaae, optString2, str2, list, list2, j3, jSONObject.optBoolean("mediation", false), jSONObject.optLong("mediation_config_cache_time_milliseconds", -1), list3, jSONObject.optLong("refresh_interval_milliseconds", -1), i, optString4, j, optString7, optBoolean, str3, optString9, jSONObject.optBoolean("custom_render_allowed", false), z, zzaae.zzSH, jSONObject.optBoolean("content_url_opted_out", true), jSONObject.optBoolean("prefetch", false), jSONObject.optString("gws_query_id", ""), SettingsJsonConstants.ICON_HEIGHT_KEY.equals(jSONObject.optString("fluid", "")), jSONObject.optBoolean("native_express", false), zzaee.zza(jSONObject.optJSONArray("rewards")), zza(jSONObject.optJSONArray("video_start_urls"), null), zza(jSONObject.optJSONArray("video_complete_urls"), null), jSONObject.optBoolean("use_displayed_impression", false), zzaak.zze(jSONObject.optJSONObject("auto_protection_configuration")), zzaae.zzSV, jSONObject.optString("set_cookie", ""), zza(jSONObject.optJSONArray("remote_ping_urls"), null), jSONObject.optBoolean("render_in_browser", zzaae.zzMe), optString5, zzaeq.zzf(jSONObject.optJSONObject("safe_browsing")), optString8, jSONObject.optBoolean("content_vertical_opted_out", true), zzaae.zzTh);
        } catch (JSONException e) {
            String str4 = "Could not parse the inline ad response: ";
            optString = String.valueOf(e.getMessage());
            zzajc.zzaT(optString.length() != 0 ? str4.concat(optString) : new String(str4));
            return new zzaai(0);
        }
    }

    @Nullable
    private static List<String> zza(@Nullable JSONArray jSONArray, @Nullable List<String> list) throws JSONException {
        if (jSONArray == null) {
            return null;
        }
        if (list == null) {
            list = new LinkedList();
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            list.add(jSONArray.getString(i));
        }
        return list;
    }

    @Nullable
    public static JSONObject zza(Context context, zzabk zzabk) {
        Object obj;
        Object obj2;
        int i;
        String str;
        zzaae zzaae = zzabk.zzUj;
        Location location = zzabk.zzzV;
        zzacb zzacb = zzabk.zzUk;
        Bundle bundle = zzabk.zzSG;
        JSONObject jSONObject = zzabk.zzUl;
        HashMap hashMap = new HashMap();
        hashMap.put("extra_caps", zzbs.zzbL().zzd(zzmo.zzFl));
        if (zzabk.zzSN.size() > 0) {
            hashMap.put("eid", TextUtils.join(",", zzabk.zzSN));
        }
        if (zzaae.zzSy != null) {
            hashMap.put("ad_pos", zzaae.zzSy);
        }
        zzir zzir = zzaae.zzSz;
        String zzhK = zzafo.zzhK();
        if (zzhK != null) {
            hashMap.put("abf", zzhK);
        }
        if (zzir.zzzN != -1) {
            hashMap.put("cust_age", zzUK.format(new Date(zzir.zzzN)));
        }
        if (zzir.extras != null) {
            hashMap.put(AppLinkData.ARGUMENTS_EXTRAS_KEY, zzir.extras);
        }
        if (zzir.zzzO != -1) {
            hashMap.put("cust_gender", Integer.valueOf(zzir.zzzO));
        }
        if (zzir.zzzP != null) {
            hashMap.put("kw", zzir.zzzP);
        }
        if (zzir.zzzR != -1) {
            hashMap.put("tag_for_child_directed_treatment", Integer.valueOf(zzir.zzzR));
        }
        if (zzir.zzzQ) {
            hashMap.put("adtest", "on");
        }
        if (zzir.versionCode >= 2) {
            if (zzir.zzzS) {
                hashMap.put("d_imp_hdr", Integer.valueOf(1));
            }
            if (!TextUtils.isEmpty(zzir.zzzT)) {
                hashMap.put("ppid", zzir.zzzT);
            }
            if (zzir.zzzU != null) {
                zzlt zzlt = zzir.zzzU;
                if (Color.alpha(zzlt.zzBw) != 0) {
                    hashMap.put("acolor", zzu(zzlt.zzBw));
                }
                if (Color.alpha(zzlt.backgroundColor) != 0) {
                    hashMap.put("bgcolor", zzu(zzlt.backgroundColor));
                }
                if (!(Color.alpha(zzlt.zzBx) == 0 || Color.alpha(zzlt.zzBy) == 0)) {
                    hashMap.put("gradientto", zzu(zzlt.zzBx));
                    hashMap.put("gradientfrom", zzu(zzlt.zzBy));
                }
                if (Color.alpha(zzlt.zzBz) != 0) {
                    hashMap.put("bcolor", zzu(zzlt.zzBz));
                }
                hashMap.put("bthick", Integer.toString(zzlt.zzBA));
                switch (zzlt.zzBB) {
                    case 0:
                        obj = "none";
                        break;
                    case 1:
                        obj = "dashed";
                        break;
                    case 2:
                        obj = "dotted";
                        break;
                    case 3:
                        obj = "solid";
                        break;
                    default:
                        obj = null;
                        break;
                }
                if (obj != null) {
                    hashMap.put("btype", obj);
                }
                switch (zzlt.zzBC) {
                    case 0:
                        obj = "light";
                        break;
                    case 1:
                        obj = Param.MEDIUM;
                        break;
                    case 2:
                        obj = "dark";
                        break;
                    default:
                        obj = null;
                        break;
                }
                if (obj != null) {
                    hashMap.put("callbuttoncolor", obj);
                }
                if (zzlt.zzBD != null) {
                    hashMap.put("channel", zzlt.zzBD);
                }
                if (Color.alpha(zzlt.zzBE) != 0) {
                    hashMap.put("dcolor", zzu(zzlt.zzBE));
                }
                if (zzlt.zzBF != null) {
                    hashMap.put("font", zzlt.zzBF);
                }
                if (Color.alpha(zzlt.zzBG) != 0) {
                    hashMap.put("hcolor", zzu(zzlt.zzBG));
                }
                hashMap.put("headersize", Integer.toString(zzlt.zzBH));
                if (zzlt.zzBI != null) {
                    hashMap.put("q", zzlt.zzBI);
                }
            }
        }
        if (zzir.versionCode >= 3 && zzir.zzzW != null) {
            hashMap.put("url", zzir.zzzW);
        }
        if (zzir.versionCode >= 5) {
            if (zzir.zzzY != null) {
                hashMap.put("custom_targeting", zzir.zzzY);
            }
            if (zzir.zzzZ != null) {
                hashMap.put("category_exclusions", zzir.zzzZ);
            }
            if (zzir.zzAa != null) {
                hashMap.put("request_agent", zzir.zzAa);
            }
        }
        if (zzir.versionCode >= 6 && zzir.zzAb != null) {
            hashMap.put("request_pkg", zzir.zzAb);
        }
        if (zzir.versionCode >= 7) {
            hashMap.put("is_designed_for_families", Boolean.valueOf(zzir.zzAc));
        }
        if (zzaae.zzvX.zzAu == null) {
            hashMap.put("format", zzaae.zzvX.zzAs);
            if (zzaae.zzvX.zzAw) {
                hashMap.put("fluid", SettingsJsonConstants.ICON_HEIGHT_KEY);
            }
        } else {
            zziv[] zzivArr = zzaae.zzvX.zzAu;
            int length = zzivArr.length;
            obj = null;
            obj2 = null;
            i = 0;
            while (i < length) {
                zziv zziv = zzivArr[i];
                if (!zziv.zzAw && r3 == null) {
                    hashMap.put("format", zziv.zzAs);
                    obj2 = 1;
                }
                if (zziv.zzAw && r2 == null) {
                    hashMap.put("fluid", SettingsJsonConstants.ICON_HEIGHT_KEY);
                    obj = 1;
                }
                if (obj2 == null || r2 == null) {
                    i++;
                }
            }
        }
        if (zzaae.zzvX.width == -1) {
            hashMap.put("smart_w", "full");
        }
        if (zzaae.zzvX.height == -2) {
            hashMap.put("smart_h", "auto");
        }
        if (zzaae.zzvX.zzAu != null) {
            StringBuilder stringBuilder = new StringBuilder();
            obj = null;
            for (zziv zziv2 : zzaae.zzvX.zzAu) {
                if (zziv2.zzAw) {
                    obj = 1;
                } else {
                    int i2;
                    if (stringBuilder.length() != 0) {
                        stringBuilder.append("|");
                    }
                    if (zziv2.width == -1) {
                        i2 = (int) (((float) zziv2.widthPixels) / zzacb.zzxR);
                    } else {
                        try {
                            i2 = zziv2.width;
                        } catch (JSONException e) {
                            str = "Problem serializing ad request to JSON: ";
                            zzhK = String.valueOf(e.getMessage());
                            zzajc.zzaT(zzhK.length() != 0 ? str.concat(zzhK) : new String(str));
                            return null;
                        }
                    }
                    stringBuilder.append(i2);
                    stringBuilder.append("x");
                    stringBuilder.append(zziv2.height == -2 ? (int) (((float) zziv2.heightPixels) / zzacb.zzxR) : zziv2.height);
                }
            }
            if (obj != null) {
                if (stringBuilder.length() != 0) {
                    stringBuilder.insert(0, "|");
                }
                stringBuilder.insert(0, "320x50");
            }
            hashMap.put("sz", stringBuilder);
        }
        if (zzaae.zzSF != 0) {
            hashMap.put("native_version", Integer.valueOf(zzaae.zzSF));
            hashMap.put("native_templates", zzaae.zzwq);
            str = "native_image_orientation";
            zzon zzon = zzaae.zzwj;
            switch (zzon != null ? zzon.zzIo : 0) {
                case 0:
                    obj = "any";
                    break;
                case 1:
                    obj = "portrait";
                    break;
                case 2:
                    obj = "landscape";
                    break;
                default:
                    obj = "not_set";
                    break;
            }
            hashMap.put(str, obj);
            if (!zzaae.zzSO.isEmpty()) {
                hashMap.put("native_custom_templates", zzaae.zzSO);
            }
            if (!TextUtils.isEmpty(zzaae.zzTi)) {
                try {
                    hashMap.put("native_advanced_settings", new JSONArray(zzaae.zzTi));
                } catch (Throwable e2) {
                    zzajc.zzc("Problem creating json from native advanced settings", e2);
                }
            }
        }
        if (zzaae.zzwn != null && zzaae.zzwn.size() > 0) {
            for (Integer num : zzaae.zzwn) {
                if (num.intValue() == 2) {
                    hashMap.put("iba", Boolean.valueOf(true));
                } else if (num.intValue() == 1) {
                    hashMap.put("ina", Boolean.valueOf(true));
                }
            }
        }
        if (zzaae.zzvX.zzAx) {
            hashMap.put("ene", Boolean.valueOf(true));
        }
        if (zzaae.zzwl != null) {
            hashMap.put("is_icon_ad", Boolean.valueOf(true));
            hashMap.put("icon_ad_expansion_behavior", Integer.valueOf(zzaae.zzwl.zzAR));
        }
        hashMap.put("slotname", zzaae.zzvR);
        hashMap.put("pn", zzaae.applicationInfo.packageName);
        if (zzaae.zzSA != null) {
            hashMap.put("vc", Integer.valueOf(zzaae.zzSA.versionCode));
        }
        hashMap.put("ms", zzabk.zzSB);
        hashMap.put("seq_num", zzaae.zzSC);
        hashMap.put("session_id", zzaae.zzSD);
        hashMap.put("js", zzaae.zzvT.zzaP);
        zzacn zzacn = zzabk.zzUh;
        Bundle bundle2 = zzaae.zzTa;
        Bundle bundle3 = zzabk.zzUg;
        hashMap.put("am", Integer.valueOf(zzacb.zzVF));
        hashMap.put("cog", zzt(zzacb.zzVG));
        hashMap.put("coh", zzt(zzacb.zzVH));
        if (!TextUtils.isEmpty(zzacb.zzVI)) {
            hashMap.put("carrier", zzacb.zzVI);
        }
        hashMap.put("gl", zzacb.zzVJ);
        if (zzacb.zzVK) {
            hashMap.put("simulator", Integer.valueOf(1));
        }
        if (zzacb.zzVL) {
            hashMap.put("is_sidewinder", Integer.valueOf(1));
        }
        hashMap.put("ma", zzt(zzacb.zzVM));
        hashMap.put("sp", zzt(zzacb.zzVN));
        hashMap.put("hl", zzacb.zzVO);
        if (!TextUtils.isEmpty(zzacb.zzVP)) {
            hashMap.put("mv", zzacb.zzVP);
        }
        hashMap.put("muv", Integer.valueOf(zzacb.zzVR));
        if (zzacb.zzVS != -2) {
            hashMap.put("cnt", Integer.valueOf(zzacb.zzVS));
        }
        hashMap.put("gnt", Integer.valueOf(zzacb.zzVT));
        hashMap.put("pt", Integer.valueOf(zzacb.zzVU));
        hashMap.put("rm", Integer.valueOf(zzacb.zzVV));
        hashMap.put("riv", Integer.valueOf(zzacb.zzVW));
        Bundle bundle4 = new Bundle();
        bundle4.putString("build_build", zzacb.zzWb);
        bundle4.putString("build_device", zzacb.zzWc);
        Bundle bundle5 = new Bundle();
        bundle5.putBoolean("is_charging", zzacb.zzVY);
        bundle5.putDouble("battery_level", zzacb.zzVX);
        bundle4.putBundle("battery", bundle5);
        bundle5 = new Bundle();
        bundle5.putInt("active_network_state", zzacb.zzWa);
        bundle5.putBoolean("active_network_metered", zzacb.zzVZ);
        if (zzacn != null) {
            Bundle bundle6 = new Bundle();
            bundle6.putInt("predicted_latency_micros", zzacn.zzWm);
            bundle6.putLong("predicted_down_throughput_bps", zzacn.zzWn);
            bundle6.putLong("predicted_up_throughput_bps", zzacn.zzWo);
            bundle5.putBundle("predictions", bundle6);
        }
        bundle4.putBundle("network", bundle5);
        Bundle bundle7 = new Bundle();
        bundle7.putBoolean("is_browser_custom_tabs_capable", zzacb.zzWd);
        bundle4.putBundle("browser", bundle7);
        if (bundle2 != null) {
            String str2 = "android_mem_info";
            bundle6 = new Bundle();
            bundle6.putString("runtime_free", Long.toString(bundle2.getLong("runtime_free_memory", -1)));
            bundle6.putString("runtime_max", Long.toString(bundle2.getLong("runtime_max_memory", -1)));
            bundle6.putString("runtime_total", Long.toString(bundle2.getLong("runtime_total_memory", -1)));
            bundle6.putString("web_view_count", Integer.toString(bundle2.getInt("web_view_count", 0)));
            MemoryInfo memoryInfo = (MemoryInfo) bundle2.getParcelable("debug_memory_info");
            if (memoryInfo != null) {
                bundle6.putString("debug_info_dalvik_private_dirty", Integer.toString(memoryInfo.dalvikPrivateDirty));
                bundle6.putString("debug_info_dalvik_pss", Integer.toString(memoryInfo.dalvikPss));
                bundle6.putString("debug_info_dalvik_shared_dirty", Integer.toString(memoryInfo.dalvikSharedDirty));
                bundle6.putString("debug_info_native_private_dirty", Integer.toString(memoryInfo.nativePrivateDirty));
                bundle6.putString("debug_info_native_pss", Integer.toString(memoryInfo.nativePss));
                bundle6.putString("debug_info_native_shared_dirty", Integer.toString(memoryInfo.nativeSharedDirty));
                bundle6.putString("debug_info_other_private_dirty", Integer.toString(memoryInfo.otherPrivateDirty));
                bundle6.putString("debug_info_other_pss", Integer.toString(memoryInfo.otherPss));
                bundle6.putString("debug_info_other_shared_dirty", Integer.toString(memoryInfo.otherSharedDirty));
            }
            bundle4.putBundle(str2, bundle6);
        }
        bundle7 = new Bundle();
        bundle7.putBundle("parental_controls", bundle3);
        if (!TextUtils.isEmpty(zzacb.zzVQ)) {
            bundle7.putString("package_version", zzacb.zzVQ);
        }
        bundle4.putBundle("play_store", bundle7);
        hashMap.put("device", bundle4);
        bundle3 = new Bundle();
        bundle3.putString("doritos", zzabk.zzUi);
        if (((Boolean) zzbs.zzbL().zzd(zzmo.zzDM)).booleanValue()) {
            obj2 = null;
            boolean z = false;
            if (zzabk.zzqi != null) {
                obj2 = zzabk.zzqi.getId();
                z = zzabk.zzqi.isLimitAdTrackingEnabled();
            }
            if (TextUtils.isEmpty(obj2)) {
                zzji.zzds();
                bundle3.putString("pdid", zzaiy.zzW(context));
                bundle3.putString("pdidtype", "ssaid");
            } else {
                bundle3.putString("rdid", obj2);
                bundle3.putBoolean("is_lat", z);
                bundle3.putString("idtype", "adid");
            }
        }
        hashMap.put("pii", bundle3);
        hashMap.put("platform", Build.MANUFACTURER);
        hashMap.put("submodel", Build.MODEL);
        if (location != null) {
            zza(hashMap, location);
        } else if (zzaae.zzSz.versionCode >= 2 && zzaae.zzSz.zzzV != null) {
            zza(hashMap, zzaae.zzSz.zzzV);
        }
        if (zzaae.versionCode >= 2) {
            hashMap.put("quality_signals", zzaae.zzSE);
        }
        if (zzaae.versionCode >= 4 && zzaae.zzSH) {
            hashMap.put("forceHttps", Boolean.valueOf(zzaae.zzSH));
        }
        if (bundle != null) {
            hashMap.put("content_info", bundle);
        }
        if (zzaae.versionCode >= 5) {
            hashMap.put("u_sd", Float.valueOf(zzaae.zzxR));
            hashMap.put("sh", Integer.valueOf(zzaae.zzSJ));
            hashMap.put("sw", Integer.valueOf(zzaae.zzSI));
        } else {
            hashMap.put("u_sd", Float.valueOf(zzacb.zzxR));
            hashMap.put("sh", Integer.valueOf(zzacb.zzSJ));
            hashMap.put("sw", Integer.valueOf(zzacb.zzSI));
        }
        if (zzaae.versionCode >= 6) {
            if (!TextUtils.isEmpty(zzaae.zzSK)) {
                try {
                    hashMap.put("view_hierarchy", new JSONObject(zzaae.zzSK));
                } catch (Throwable e22) {
                    zzajc.zzc("Problem serializing view hierarchy to JSON", e22);
                }
            }
            hashMap.put("correlation_id", Long.valueOf(zzaae.zzSL));
        }
        if (zzaae.versionCode >= 7) {
            hashMap.put("request_id", zzaae.zzSM);
        }
        if (zzaae.versionCode >= 12 && !TextUtils.isEmpty(zzaae.zzSQ)) {
            hashMap.put("anchor", zzaae.zzSQ);
        }
        if (zzaae.versionCode >= 13) {
            hashMap.put("android_app_volume", Float.valueOf(zzaae.zzSR));
        }
        if (zzaae.versionCode >= 18) {
            hashMap.put("android_app_muted", Boolean.valueOf(zzaae.zzSX));
        }
        if (zzaae.versionCode >= 14 && zzaae.zzSS > 0) {
            hashMap.put("target_api", Integer.valueOf(zzaae.zzSS));
        }
        if (zzaae.versionCode >= 15) {
            hashMap.put("scroll_index", Integer.valueOf(zzaae.zzST == -1 ? -1 : zzaae.zzST));
        }
        if (zzaae.versionCode >= 16) {
            hashMap.put("_activity_context", Boolean.valueOf(zzaae.zzSU));
        }
        if (zzaae.versionCode >= 18) {
            if (!TextUtils.isEmpty(zzaae.zzSY)) {
                try {
                    hashMap.put("app_settings", new JSONObject(zzaae.zzSY));
                } catch (Throwable e222) {
                    zzajc.zzc("Problem creating json from app settings", e222);
                }
            }
            hashMap.put("render_in_browser", Boolean.valueOf(zzaae.zzMe));
        }
        if (zzaae.versionCode >= 18) {
            hashMap.put("android_num_video_cache_tasks", Integer.valueOf(zzaae.zzSZ));
        }
        zzaje zzaje = zzaae.zzvT;
        boolean z2 = zzabk.zzUm;
        Bundle bundle8 = new Bundle();
        bundle7 = new Bundle();
        bundle7.putString("cl", "162978962");
        bundle7.putString("rapid_rc", "dev");
        bundle7.putString("rapid_rollup", HttpRequest.METHOD_HEAD);
        bundle8.putBundle("build_meta", bundle7);
        bundle8.putString("mf", Boolean.toString(((Boolean) zzbs.zzbL().zzd(zzmo.zzFn)).booleanValue()));
        bundle8.putBoolean("instant_app", zzbha.zzaP(context).zzsl());
        bundle8.putBoolean("lite", zzaje.zzaaR);
        bundle8.putBoolean("local_service", z2);
        hashMap.put("sdk_env", bundle8);
        hashMap.put("cache_state", jSONObject);
        if (zzaae.versionCode >= 19) {
            hashMap.put("gct", zzaae.zzTb);
        }
        if (zzaae.versionCode >= 21 && zzaae.zzTc) {
            hashMap.put("de", AppEventsConstants.EVENT_PARAM_VALUE_YES);
        }
        if (((Boolean) zzbs.zzbL().zzd(zzmo.zzDY)).booleanValue()) {
            zzhK = zzaae.zzvX.zzAs;
            obj2 = (zzhK.equals("interstitial_mb") || zzhK.equals("reward_mb")) ? 1 : null;
            bundle3 = zzaae.zzTd;
            obj = bundle3 != null ? 1 : null;
            if (!(obj2 == null || obj == null)) {
                bundle7 = new Bundle();
                bundle7.putBundle("interstitial_pool", bundle3);
                hashMap.put("counters", bundle7);
            }
        }
        if (zzaae.versionCode >= 22 && zzbs.zzbY().zzp(context)) {
            hashMap.put("gmp_app_id", zzaae.zzTe);
            if ("TIME_OUT".equals(zzaae.zzTf)) {
                hashMap.put("sai_timeout", zzbs.zzbL().zzd(zzmo.zzDB));
            } else if (zzaae.zzTf == null) {
                hashMap.put("fbs_aiid", "");
            } else {
                hashMap.put("fbs_aiid", zzaae.zzTf);
            }
            hashMap.put("fbs_aeid", zzaae.zzTg);
        }
        zzhK = (String) zzbs.zzbL().zzd(zzmo.zzCE);
        if (!(zzhK == null || zzhK.isEmpty())) {
            if (VERSION.SDK_INT >= ((Integer) zzbs.zzbL().zzd(zzmo.zzCF)).intValue()) {
                HashMap hashMap2 = new HashMap();
                for (String str3 : zzhK.split(",")) {
                    hashMap2.put(str3, zzaiw.zzaQ(str3));
                }
                hashMap.put("video_decoders", hashMap2);
            }
        }
        if (zzajc.zzz(2)) {
            str = "Ad Request JSON: ";
            zzhK = String.valueOf(zzbs.zzbz().zzj(hashMap).toString(2));
            zzafr.m1217v(zzhK.length() != 0 ? str.concat(zzhK) : new String(str));
        }
        return zzbs.zzbz().zzj(hashMap);
    }

    private static void zza(HashMap<String, Object> hashMap, Location location) {
        HashMap hashMap2 = new HashMap();
        Float valueOf = Float.valueOf(location.getAccuracy() * 1000.0f);
        Long valueOf2 = Long.valueOf(location.getTime() * 1000);
        Long valueOf3 = Long.valueOf((long) (location.getLatitude() * 1.0E7d));
        Long valueOf4 = Long.valueOf((long) (location.getLongitude() * 1.0E7d));
        hashMap2.put("radius", valueOf);
        hashMap2.put("lat", valueOf3);
        hashMap2.put("long", valueOf4);
        hashMap2.put("time", valueOf2);
        hashMap.put("uule", hashMap2);
    }

    public static JSONObject zzb(zzaai zzaai) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (zzaai.zzPi != null) {
            jSONObject.put("ad_base_url", zzaai.zzPi);
        }
        if (zzaai.zzTr != null) {
            jSONObject.put("ad_size", zzaai.zzTr);
        }
        jSONObject.put(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE, zzaai.zzAv);
        if (zzaai.zzAv) {
            jSONObject.put("ad_json", zzaai.body);
        } else {
            jSONObject.put("ad_html", zzaai.body);
        }
        if (zzaai.zzTt != null) {
            jSONObject.put("debug_dialog", zzaai.zzTt);
        }
        if (zzaai.zzTK != null) {
            jSONObject.put("debug_signals", zzaai.zzTK);
        }
        if (zzaai.zzTn != -1) {
            jSONObject.put("interstitial_timeout", ((double) zzaai.zzTn) / 1000.0d);
        }
        if (zzaai.orientation == zzbs.zzbB().zzhU()) {
            jSONObject.put("orientation", "portrait");
        } else if (zzaai.orientation == zzbs.zzbB().zzhT()) {
            jSONObject.put("orientation", "landscape");
        }
        if (zzaai.zzMa != null) {
            jSONObject.put("click_urls", zzm(zzaai.zzMa));
        }
        if (zzaai.zzMb != null) {
            jSONObject.put("impression_urls", zzm(zzaai.zzMb));
        }
        if (zzaai.zzTq != null) {
            jSONObject.put("manual_impression_urls", zzm(zzaai.zzTq));
        }
        if (zzaai.zzTw != null) {
            jSONObject.put("active_view", zzaai.zzTw);
        }
        jSONObject.put("ad_is_javascript", zzaai.zzTu);
        if (zzaai.zzTv != null) {
            jSONObject.put("ad_passback_url", zzaai.zzTv);
        }
        jSONObject.put("mediation", zzaai.zzTo);
        jSONObject.put("custom_render_allowed", zzaai.zzTx);
        jSONObject.put("content_url_opted_out", zzaai.zzTy);
        jSONObject.put("content_vertical_opted_out", zzaai.zzTL);
        jSONObject.put("prefetch", zzaai.zzTz);
        if (zzaai.zzMg != -1) {
            jSONObject.put("refresh_interval_milliseconds", zzaai.zzMg);
        }
        if (zzaai.zzTp != -1) {
            jSONObject.put("mediation_config_cache_time_milliseconds", zzaai.zzTp);
        }
        if (!TextUtils.isEmpty(zzaai.zzTC)) {
            jSONObject.put("gws_query_id", zzaai.zzTC);
        }
        jSONObject.put("fluid", zzaai.zzAw ? SettingsJsonConstants.ICON_HEIGHT_KEY : "");
        jSONObject.put("native_express", zzaai.zzAx);
        if (zzaai.zzTE != null) {
            jSONObject.put("video_start_urls", zzm(zzaai.zzTE));
        }
        if (zzaai.zzTF != null) {
            jSONObject.put("video_complete_urls", zzm(zzaai.zzTF));
        }
        if (zzaai.zzTD != null) {
            zzaee zzaee = zzaai.zzTD;
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("rb_type", zzaee.type);
            jSONObject2.put("rb_amount", zzaee.zzWW);
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(jSONObject2);
            jSONObject.put("rewards", jSONArray);
        }
        jSONObject.put("use_displayed_impression", zzaai.zzTG);
        jSONObject.put("auto_protection_configuration", zzaai.zzTH);
        jSONObject.put("render_in_browser", zzaai.zzMe);
        return jSONObject;
    }

    @Nullable
    private static JSONArray zzm(List<String> list) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        for (String put : list) {
            jSONArray.put(put);
        }
        return jSONArray;
    }

    private static Integer zzt(boolean z) {
        return Integer.valueOf(z ? 1 : 0);
    }

    private static String zzu(int i) {
        return String.format(Locale.US, "#%06x", new Object[]{Integer.valueOf(ViewCompat.MEASURED_SIZE_MASK & i)});
    }
}
