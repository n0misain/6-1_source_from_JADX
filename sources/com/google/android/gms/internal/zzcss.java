package com.google.android.gms.internal;

import android.net.Uri;
import android.text.TextUtils;
import com.applovin.sdk.AppLovinTargetingData;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class zzcss {
    private static final String TAG = zzcss.class.getSimpleName();
    private static final char[] zzbCj = "0123456789ABCDEF".toCharArray();
    private static final Pattern zzbCk = Pattern.compile("/\\.\\.");
    private static final Pattern zzbCl = Pattern.compile("0[1-7][0-7]*");
    private static final Pattern zzbCm = Pattern.compile("0x[0-9a-f]*", 2);
    private static final Pattern zzbCn = Pattern.compile("^((?:0x[0-9a-f]+|[0-9\\\\.])+)$", 2);
    private final String mPath;
    private final String zzD;
    private final String zzbCo;
    private final String zzbCp;
    private final int zzbCq;
    private final String zzvi;

    static class zza {
        private static final String[] zzbCr = new String[]{"http", "https", "ftp"};
        private final String zzajd;
        private final String zzbCo;
        private final Uri zzbCs;
        private final URI zzbCt;
        private final Boolean zzbCu;
        private final Integer zzbCv;

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private zza(java.lang.String r5) {
            /*
            r4 = this;
            r1 = -1;
            r3 = 0;
            r4.<init>();
            r4.zzajd = r5;
            r0 = r4.zzajd;
            r0 = android.net.Uri.parse(r0);
            r4.zzbCs = r0;
            r0 = new java.net.URI;	 Catch:{ URISyntaxException -> 0x0039, all -> 0x003d }
            r2 = r4.zzajd;	 Catch:{ URISyntaxException -> 0x0039, all -> 0x003d }
            r0.<init>(r2);	 Catch:{ URISyntaxException -> 0x0039, all -> 0x003d }
            r4.zzbCt = r3;
        L_0x0018:
            r0 = r4.getScheme();
            r4.zzbCo = r0;
            r0 = r4.zzAn();
            r0 = java.lang.Boolean.valueOf(r0);
            r4.zzbCu = r0;
            r0 = r4.zzbCv;
            if (r0 == 0) goto L_0x0041;
        L_0x002c:
            r0 = r4.zzbCv;
            r0 = r0.intValue();
        L_0x0032:
            r0 = java.lang.Integer.valueOf(r0);
            r4.zzbCv = r0;
            return;
        L_0x0039:
            r0 = move-exception;
            r4.zzbCt = r3;
            goto L_0x0018;
        L_0x003d:
            r0 = move-exception;
            r4.zzbCt = r3;
            throw r0;
        L_0x0041:
            r0 = r4.zzbCs;
            if (r0 == 0) goto L_0x004d;
        L_0x0045:
            r0 = r4.zzbCs;
            r0 = r0.getPort();
            if (r0 != r1) goto L_0x0032;
        L_0x004d:
            r0 = r1;
            goto L_0x0032;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcss.zza.<init>(java.lang.String):void");
        }

        private final int getPort() {
            return this.zzbCv.intValue();
        }

        private final String getScheme() {
            if (this.zzbCo != null) {
                return this.zzbCo;
            }
            String scheme = this.zzbCs != null ? this.zzbCs.getScheme() : null;
            TextUtils.isEmpty(scheme);
            if (TextUtils.isEmpty(scheme) && !TextUtils.isEmpty(this.zzajd)) {
                int indexOf = this.zzajd.indexOf(":");
                if (indexOf != -1) {
                    String toLowerCase = this.zzajd.substring(0, indexOf).toLowerCase(Locale.US);
                    if (zzeU(toLowerCase)) {
                        scheme = toLowerCase;
                    }
                }
                if (TextUtils.isEmpty(scheme)) {
                    this.zzajd.startsWith("www.");
                    scheme = "http";
                }
            }
            return scheme != null ? scheme.toLowerCase(Locale.US) : null;
        }

        private final boolean zzAn() {
            return this.zzbCu != null ? this.zzbCu.booleanValue() : zzeU(this.zzbCo);
        }

        private static boolean zzeU(String str) {
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            for (String equals : zzbCr) {
                if (equals.equals(str)) {
                    return true;
                }
            }
            return false;
        }
    }

    public zzcss(String str) {
        if ((!TextUtils.isEmpty(str) ? 1 : 0) == 0) {
            this.zzD = null;
            this.zzbCo = null;
            this.zzbCp = null;
            this.zzbCq = -1;
            this.mPath = null;
            this.zzvi = null;
            return;
        }
        String replaceAll = str.replaceAll("^\\s+", "").replaceAll("\\s+$", "").replaceAll("[\\t\\n\\r]", "");
        int indexOf = replaceAll.indexOf(35);
        if (indexOf != -1) {
            replaceAll = replaceAll.substring(0, indexOf);
        }
        zza zza = new zza(replaceAll);
        if (zza.zzAn()) {
            String str2;
            Object obj;
            this.zzbCo = zza.getScheme();
            this.zzbCq = zza.getPort();
            if (this.zzbCo != null) {
                str2 = this.zzbCo;
                replaceAll = replaceAll.replaceAll(new StringBuilder(String.valueOf(str2).length() + 2).append("^").append(str2).append(":").toString(), "");
            }
            str2 = zzeS(replaceAll.replaceAll("^/+", ""));
            int indexOf2 = str2.indexOf(63);
            if (indexOf2 != -1) {
                int i = indexOf2 + 1;
                replaceAll = i < str2.length() ? str2.substring(i) : "";
                str2 = str2.substring(0, indexOf2);
            } else {
                replaceAll = null;
            }
            if (TextUtils.isEmpty(str2)) {
                obj = null;
            } else {
                indexOf2 = str2.indexOf(47);
                String substring = indexOf2 != -1 ? str2.substring(0, indexOf2) : str2;
                indexOf2 = substring.indexOf(64);
                if (indexOf2 != -1) {
                    substring = substring.substring(indexOf2 + 1);
                }
                if (this.zzbCq != -1) {
                    substring = substring.replaceAll(":" + this.zzbCq + "$", "");
                }
                String replaceAll2 = substring.replaceAll("^\\.*", "").replaceAll("\\.*$", "").replaceAll("\\.+", ".");
                substring = zzeO(replaceAll2);
                if (substring == null) {
                    substring = replaceAll2;
                }
                obj = substring.toLowerCase(Locale.getDefault());
            }
            if (TextUtils.isEmpty(obj)) {
                this.zzD = null;
                this.zzbCp = null;
                this.mPath = null;
                this.zzvi = null;
                return;
            }
            String zzeQ = zzeQ(str2);
            this.zzbCp = zzeR(obj);
            this.mPath = zzeR(zzeQ);
            if (!TextUtils.isEmpty(replaceAll)) {
                replaceAll = zzeR(replaceAll);
            }
            this.zzvi = replaceAll;
            this.zzD = str2;
            return;
        }
        this.zzD = null;
        this.zzbCo = null;
        this.zzbCp = null;
        this.zzbCq = -1;
        this.mPath = null;
        this.zzvi = null;
    }

    private static boolean isHexDigit(char c) {
        return (c >= '0' && c <= '9') || ((c >= 'A' && c <= 'F') || (c >= 'a' && c <= AppLovinTargetingData.GENDER_FEMALE));
    }

    private final List<String> zzAl() {
        if (TextUtils.isEmpty(this.zzbCp)) {
            return null;
        }
        List<String> arrayList = new ArrayList();
        char[] toCharArray = this.zzbCp.toCharArray();
        Object obj = null;
        for (int length = toCharArray.length - 2; length > 0 && arrayList.size() < 4; length--) {
            if (toCharArray[length] == '.') {
                if (obj != null) {
                    arrayList.add(this.zzbCp.substring(length + 1));
                } else {
                    obj = 1;
                }
            }
        }
        arrayList.add(this.zzbCp);
        return arrayList;
    }

    private final List<String> zzAm() {
        if (TextUtils.isEmpty(this.mPath)) {
            return null;
        }
        List<String> arrayList = new ArrayList();
        char[] toCharArray = this.mPath.toCharArray();
        for (int i = 0; i < toCharArray.length && arrayList.size() < 4; i++) {
            if (toCharArray[i] == '/') {
                arrayList.add(this.mPath.substring(0, i + 1));
            }
        }
        if (!(arrayList.isEmpty() || ((String) arrayList.get(arrayList.size() - 1)).equals(this.mPath))) {
            arrayList.add(this.mPath);
        }
        if (!TextUtils.isEmpty(this.zzvi)) {
            String str = this.mPath;
            String str2 = this.zzvi;
            arrayList.add(new StringBuilder((String.valueOf(str).length() + 1) + String.valueOf(str2).length()).append(str).append("?").append(str2).toString());
        }
        return arrayList;
    }

    private static String zzeO(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        CharSequence replaceAll = str.replaceAll("^\\[", "").replaceAll("\\]$", "");
        String zza;
        if (zzcsq.zzeI(replaceAll)) {
            zza = zzcsq.zza(zzcsq.zzeN(replaceAll));
            if (!zza.contains(":")) {
                return zza;
            }
            return String.format("[%s]", new Object[]{zza});
        } else if (TextUtils.isDigitsOnly(str)) {
            replaceAll = zzeP(str);
            return !TextUtils.isEmpty(replaceAll) ? replaceAll : null;
        } else if (!zzbCn.matcher(replaceAll).find()) {
            return null;
        } else {
            Matcher matcher = zzbCl.matcher(replaceAll);
            StringBuffer stringBuffer = new StringBuffer();
            while (matcher.find()) {
                matcher.appendReplacement(stringBuffer, Integer.parseInt(matcher.group(), 8));
            }
            matcher.appendTail(stringBuffer);
            matcher = zzbCm.matcher(stringBuffer.toString());
            stringBuffer = new StringBuffer();
            while (matcher.find()) {
                matcher.appendReplacement(stringBuffer, Integer.parseInt(matcher.group().substring(2), 16));
            }
            matcher.appendTail(stringBuffer);
            zza = stringBuffer.toString();
            if (!zza.contains(":")) {
                return zza;
            }
            return String.format("[%s]", new Object[]{zza});
        }
    }

    private static String zzeP(String str) {
        int i = 0;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            BigInteger bigInteger = new BigInteger(str);
            byte[] toByteArray = bigInteger.toByteArray();
            if (toByteArray.length < 4) {
                return null;
            }
            if (bigInteger.equals(new BigInteger(new byte[]{(byte) 0, r4[0], r4[1], r4[2], Arrays.copyOfRange(toByteArray, toByteArray.length - 4, toByteArray.length)[3]}))) {
                return Inet4Address.getByAddress(Arrays.copyOfRange(toByteArray, toByteArray.length - 4, toByteArray.length)).getHostAddress();
            }
            byte[] copyOfRange;
            if (toByteArray.length >= 16) {
                copyOfRange = Arrays.copyOfRange(toByteArray, toByteArray.length - 16, toByteArray.length);
            } else {
                copyOfRange = new byte[16];
                int length = 16 - toByteArray.length;
                int i2 = 1;
                int i3 = 0;
                while (i2 <= length) {
                    int i4 = i3 + 1;
                    copyOfRange[i3] = (byte) 0;
                    i2++;
                    i3 = i4;
                }
                while (i < toByteArray.length) {
                    i2 = i3 + 1;
                    copyOfRange[i3] = toByteArray[i];
                    i++;
                    i3 = i2;
                }
            }
            return String.format("[%s]", new Object[]{Inet6Address.getByAddress(copyOfRange).getHostAddress()});
        } catch (NumberFormatException e) {
            return null;
        } catch (ArrayIndexOutOfBoundsException e2) {
            return null;
        } catch (UnknownHostException e3) {
            return null;
        }
    }

    private final String zzeQ(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        int indexOf = str.indexOf(47);
        String replaceAll = (indexOf != -1 ? str.substring(indexOf) : "/").replaceAll("/\\./", "/").replaceAll("/\\.$", "/");
        if (zzbCk.matcher(replaceAll).find()) {
            try {
                replaceAll = new URI(this.zzbCo, "host", replaceAll, null).normalize().getRawPath();
            } catch (URISyntaxException e) {
            }
        }
        return replaceAll.replaceAll("/+", "/");
    }

    private static String zzeR(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            byte[] bytes = str.getBytes(HttpRequest.CHARSET_UTF8);
            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : bytes) {
                char c = (char) (b & 255);
                if (c <= ' ' || c > '~' || c == '#' || c == '%') {
                    stringBuilder.append("%");
                    stringBuilder.append(zzbCj[c >>> 4]);
                    stringBuilder.append(zzbCj[c & 15]);
                } else {
                    stringBuilder.append(c);
                }
            }
            return stringBuilder.toString();
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    private static String zzeS(String str) {
        Object obj = null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        int i = 0;
        String str2 = str;
        while (!str2.equals(obj) && i < 1024) {
            i++;
            String str3 = str2;
            str2 = zzeT(str2);
        }
        return str2;
    }

    private static String zzeT(String str) {
        try {
            byte[] bytes = str.replace("\\x", "%").getBytes(HttpRequest.CHARSET_UTF8);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(bytes.length);
            int i = 0;
            while (i < bytes.length) {
                byte b = bytes[i];
                if (((char) (b & 255)) < '') {
                    Byte valueOf;
                    if (i + 2 < bytes.length && ((char) (bytes[i] & 255)) == '%') {
                        char c = (char) (bytes[i + 1] & 255);
                        char c2 = (char) (bytes[i + 2] & 255);
                        if (isHexDigit(c) && isHexDigit(c2)) {
                            valueOf = Byte.valueOf((byte) ((Integer.parseInt(c, 16) << 4) + Integer.parseInt(c2, 16)));
                            if (valueOf != null) {
                                byteArrayOutputStream.write(valueOf.byteValue());
                                i += 2;
                                i++;
                            }
                        }
                    }
                    valueOf = null;
                    if (valueOf != null) {
                        byteArrayOutputStream.write(valueOf.byteValue());
                        i += 2;
                        i++;
                    }
                }
                byteArrayOutputStream.write(b);
                i++;
            }
            try {
                return new String(byteArrayOutputStream.toByteArray(), HttpRequest.CHARSET_UTF8);
            } catch (UnsupportedEncodingException e) {
                return null;
            }
        } catch (UnsupportedEncodingException e2) {
            return null;
        }
    }

    public final List<zzcsp> zzAk() {
        List list;
        List<String> zzAm;
        if (!TextUtils.isEmpty(this.zzD)) {
            String zzeO = zzeO(this.zzbCp);
            List arrayList;
            if (zzeO != null) {
                arrayList = new ArrayList();
                arrayList.add(zzeO);
            } else {
                arrayList = zzAl();
            }
            if (r0 == null || r0.isEmpty()) {
                list = null;
                if (zzAm != null || zzAm.isEmpty()) {
                    return null;
                }
                MessageDigest instance;
                try {
                    instance = MessageDigest.getInstance("SHA-256");
                } catch (NoSuchAlgorithmException e) {
                    instance = null;
                }
                if (instance == null) {
                    return null;
                }
                List<zzcsp> arrayList2 = new ArrayList(zzAm.size());
                for (String str : zzAm) {
                    if (!TextUtils.isEmpty(str)) {
                        try {
                            arrayList2.add(new zzcsp(instance.digest(str.getBytes(HttpRequest.CHARSET_UTF8))));
                        } catch (UnsupportedEncodingException e2) {
                        }
                        instance.reset();
                    }
                }
                return !arrayList2.isEmpty() ? arrayList2 : null;
            } else {
                zzAm = zzAm();
                if (zzAm == null || zzAm.isEmpty()) {
                    list = null;
                    if (zzAm != null) {
                    }
                    return null;
                }
                List linkedList = new LinkedList();
                for (String str2 : r0) {
                    for (String zzeO2 : zzAm) {
                        String valueOf = String.valueOf(str2);
                        zzeO2 = String.valueOf(zzeO2);
                        linkedList.add(zzeO2.length() != 0 ? valueOf.concat(zzeO2) : new String(valueOf));
                    }
                }
                if (!linkedList.isEmpty()) {
                    list = linkedList;
                    if (zzAm != null) {
                    }
                    return null;
                }
            }
        }
        list = null;
        if (zzAm != null) {
        }
        return null;
    }
}
