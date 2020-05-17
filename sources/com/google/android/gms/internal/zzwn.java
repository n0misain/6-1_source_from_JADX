package com.google.android.gms.internal;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.text.TextUtils;
import android.webkit.URLUtil;
import com.google.android.gms.C0866R;
import com.google.android.gms.ads.internal.zzbs;
import java.util.Map;

@zzzn
public final class zzwn extends zzwu {
    private final Context mContext;
    private final Map<String, String> zzHa;

    public zzwn(zzaka zzaka, Map<String, String> map) {
        super(zzaka, "storePicture");
        this.zzHa = map;
        this.mContext = zzaka.zzis();
    }

    public final void execute() {
        if (this.mContext == null) {
            zzan("Activity context is not available");
            return;
        }
        zzbs.zzbz();
        if (zzagz.zzH(this.mContext).zzdG()) {
            String str = (String) this.zzHa.get("iurl");
            if (TextUtils.isEmpty(str)) {
                zzan("Image url cannot be empty.");
                return;
            } else if (URLUtil.isValidUrl(str)) {
                String lastPathSegment = Uri.parse(str).getLastPathSegment();
                zzbs.zzbz();
                if (zzagz.zzaK(lastPathSegment)) {
                    Resources resources = zzbs.zzbD().getResources();
                    zzbs.zzbz();
                    Builder zzG = zzagz.zzG(this.mContext);
                    zzG.setTitle(resources != null ? resources.getString(C0866R.string.s1) : "Save image");
                    zzG.setMessage(resources != null ? resources.getString(C0866R.string.s2) : "Allow Ad to store image in Picture gallery?");
                    zzG.setPositiveButton(resources != null ? resources.getString(C0866R.string.s3) : "Accept", new zzwo(this, str, lastPathSegment));
                    zzG.setNegativeButton(resources != null ? resources.getString(C0866R.string.s4) : "Decline", new zzwp(this));
                    zzG.create().show();
                    return;
                }
                r1 = "Image type not recognized: ";
                str = String.valueOf(lastPathSegment);
                zzan(str.length() != 0 ? r1.concat(str) : new String(r1));
                return;
            } else {
                r1 = "Invalid image url: ";
                str = String.valueOf(str);
                zzan(str.length() != 0 ? r1.concat(str) : new String(r1));
                return;
            }
        }
        zzan("Feature is not supported by the device.");
    }
}
