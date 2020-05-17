package com.google.android.gms.internal;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;

@zzzn
public final class zzhp {
    private final int zzyY;
    private final zzhf zzza;
    private String zzzi;
    private String zzzj;
    private final boolean zzzk = false;
    private final int zzzl;
    private final int zzzm;

    public zzhp(int i, int i2, int i3) {
        this.zzyY = i;
        if (i2 > 64 || i2 < 0) {
            this.zzzl = 64;
        } else {
            this.zzzl = i2;
        }
        if (i3 <= 0) {
            this.zzzm = 1;
        } else {
            this.zzzm = i3;
        }
        this.zzza = new zzho(this.zzzl);
    }

    private final boolean zza(String str, HashSet<String> hashSet) {
        String[] split = str.split("\n");
        if (split.length == 0) {
            return true;
        }
        for (String str2 : split) {
            int i;
            String stringBuilder;
            String[] zzd;
            Object obj;
            int i2;
            boolean z;
            if (str2.indexOf("'") != -1) {
                StringBuilder stringBuilder2 = new StringBuilder(str2);
                i = 1;
                boolean z2 = false;
                while (i + 2 <= stringBuilder2.length()) {
                    if (stringBuilder2.charAt(i) == '\'') {
                        if (stringBuilder2.charAt(i - 1) == ' ' || !((stringBuilder2.charAt(i + 1) == 's' || stringBuilder2.charAt(i + 1) == 'S') && (i + 2 == stringBuilder2.length() || stringBuilder2.charAt(i + 2) == ' '))) {
                            stringBuilder2.setCharAt(i, ' ');
                        } else {
                            stringBuilder2.insert(i, ' ');
                            i += 2;
                        }
                        z2 = true;
                    }
                    i++;
                }
                stringBuilder = z2 ? stringBuilder2.toString() : null;
                if (stringBuilder != null) {
                    this.zzzj = stringBuilder;
                    zzd = zzhj.zzd(stringBuilder, true);
                    if (zzd.length >= this.zzzm) {
                        for (i = 0; i < zzd.length; i++) {
                            obj = "";
                            for (i2 = 0; i2 < this.zzzm; i2++) {
                                if (i + i2 >= zzd.length) {
                                    z = false;
                                    break;
                                }
                                if (i2 > 0) {
                                    obj = String.valueOf(obj).concat(" ");
                                }
                                String valueOf = String.valueOf(obj);
                                String valueOf2 = String.valueOf(zzd[i + i2]);
                                obj = valueOf2.length() == 0 ? valueOf.concat(valueOf2) : new String(valueOf);
                            }
                            z = true;
                            if (z) {
                                break;
                            }
                            hashSet.add(obj);
                            if (hashSet.size() >= this.zzyY) {
                                return false;
                            }
                        }
                        if (hashSet.size() >= this.zzyY) {
                            return false;
                        }
                    }
                }
            }
            stringBuilder = str2;
            zzd = zzhj.zzd(stringBuilder, true);
            if (zzd.length >= this.zzzm) {
                for (i = 0; i < zzd.length; i++) {
                    obj = "";
                    for (i2 = 0; i2 < this.zzzm; i2++) {
                        if (i + i2 >= zzd.length) {
                            z = false;
                            break;
                        }
                        if (i2 > 0) {
                            obj = String.valueOf(obj).concat(" ");
                        }
                        String valueOf3 = String.valueOf(obj);
                        String valueOf22 = String.valueOf(zzd[i + i2]);
                        if (valueOf22.length() == 0) {
                        }
                    }
                    z = true;
                    if (z) {
                        break;
                    }
                    hashSet.add(obj);
                    if (hashSet.size() >= this.zzyY) {
                        return false;
                    }
                }
                if (hashSet.size() >= this.zzyY) {
                    return false;
                }
            }
        }
        return true;
    }

    public final String zza(ArrayList<String> arrayList, ArrayList<zzhe> arrayList2) {
        Collections.sort(arrayList2, new zzhq(this));
        HashSet hashSet = new HashSet();
        int i = 0;
        while (i < arrayList2.size() && zza(Normalizer.normalize((CharSequence) arrayList.get(((zzhe) arrayList2.get(i)).zzcV()), Form.NFKC).toLowerCase(Locale.US), hashSet)) {
            i++;
        }
        zzhi zzhi = new zzhi();
        this.zzzi = "";
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            try {
                zzhi.write(this.zzza.zzy((String) it.next()));
            } catch (Throwable e) {
                zzajc.zzb("Error while writing hash to byteStream", e);
            }
        }
        return zzhi.toString();
    }
}
