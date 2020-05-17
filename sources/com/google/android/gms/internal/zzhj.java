package com.google.android.gms.internal;

@zzzn
public final class zzhj {
    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int zzA(java.lang.String r10) {
        /*
        r9 = 461845907; // 0x1b873593 float:2.2368498E-22 double:2.281821963E-315;
        r8 = -862048943; // 0xffffffffcc9e2d51 float:-8.2930312E7 double:NaN;
        r1 = 0;
        r0 = "UTF-8";
        r0 = r10.getBytes(r0);	 Catch:{ UnsupportedEncodingException -> 0x004b }
    L_0x000d:
        r5 = r0.length;
        r2 = r5 & -4;
        r6 = r2 + 0;
        r3 = r1;
        r2 = r1;
    L_0x0014:
        if (r3 >= r6) goto L_0x0051;
    L_0x0016:
        r4 = r0[r3];
        r4 = r4 & 255;
        r7 = r3 + 1;
        r7 = r0[r7];
        r7 = r7 & 255;
        r7 = r7 << 8;
        r4 = r4 | r7;
        r7 = r3 + 2;
        r7 = r0[r7];
        r7 = r7 & 255;
        r7 = r7 << 16;
        r4 = r4 | r7;
        r7 = r3 + 3;
        r7 = r0[r7];
        r7 = r7 << 24;
        r4 = r4 | r7;
        r4 = r4 * r8;
        r7 = r4 << 15;
        r4 = r4 >>> 17;
        r4 = r4 | r7;
        r4 = r4 * r9;
        r2 = r2 ^ r4;
        r4 = r2 << 13;
        r2 = r2 >>> 19;
        r2 = r2 | r4;
        r2 = r2 * 5;
        r4 = -430675100; // 0xffffffffe6546b64 float:-2.5078068E23 double:NaN;
        r4 = r4 + r2;
        r2 = r3 + 4;
        r3 = r2;
        r2 = r4;
        goto L_0x0014;
    L_0x004b:
        r0 = move-exception;
        r0 = r10.getBytes();
        goto L_0x000d;
    L_0x0051:
        r3 = r5 & 3;
        switch(r3) {
            case 1: goto L_0x007b;
            case 2: goto L_0x0072;
            case 3: goto L_0x006a;
            default: goto L_0x0056;
        };
    L_0x0056:
        r0 = r2;
    L_0x0057:
        r0 = r0 ^ r5;
        r1 = r0 >>> 16;
        r0 = r0 ^ r1;
        r1 = -2048144789; // 0xffffffff85ebca6b float:-2.217365E-35 double:NaN;
        r0 = r0 * r1;
        r1 = r0 >>> 13;
        r0 = r0 ^ r1;
        r1 = -1028477387; // 0xffffffffc2b2ae35 float:-89.34025 double:NaN;
        r0 = r0 * r1;
        r1 = r0 >>> 16;
        r0 = r0 ^ r1;
        return r0;
    L_0x006a:
        r1 = r6 + 2;
        r1 = r0[r1];
        r1 = r1 & 255;
        r1 = r1 << 16;
    L_0x0072:
        r3 = r6 + 1;
        r3 = r0[r3];
        r3 = r3 & 255;
        r3 = r3 << 8;
        r1 = r1 | r3;
    L_0x007b:
        r0 = r0[r6];
        r0 = r0 & 255;
        r0 = r0 | r1;
        r0 = r0 * r8;
        r1 = r0 << 15;
        r0 = r0 >>> 17;
        r0 = r0 | r1;
        r0 = r0 * r9;
        r0 = r0 ^ r2;
        goto L_0x0057;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzhj.zzA(java.lang.String):int");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @android.support.annotation.Nullable
    public static java.lang.String[] zzd(@android.support.annotation.Nullable java.lang.String r13, boolean r14) {
        /*
        r3 = 1;
        r2 = 0;
        if (r13 != 0) goto L_0x0006;
    L_0x0004:
        r0 = 0;
    L_0x0005:
        return r0;
    L_0x0006:
        r6 = new java.util.ArrayList;
        r6.<init>();
        r7 = r13.toCharArray();
        r8 = r13.length();
        r4 = r2;
        r0 = r2;
        r1 = r2;
    L_0x0016:
        if (r1 >= r8) goto L_0x00e3;
    L_0x0018:
        r9 = java.lang.Character.codePointAt(r7, r1);
        r10 = java.lang.Character.charCount(r9);
        r5 = java.lang.Character.isLetter(r9);
        if (r5 == 0) goto L_0x009f;
    L_0x0026:
        r5 = java.lang.Character.UnicodeBlock.of(r9);
        r11 = java.lang.Character.UnicodeBlock.BOPOMOFO;
        if (r5 == r11) goto L_0x0062;
    L_0x002e:
        r11 = java.lang.Character.UnicodeBlock.BOPOMOFO_EXTENDED;
        if (r5 == r11) goto L_0x0062;
    L_0x0032:
        r11 = java.lang.Character.UnicodeBlock.CJK_COMPATIBILITY;
        if (r5 == r11) goto L_0x0062;
    L_0x0036:
        r11 = java.lang.Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS;
        if (r5 == r11) goto L_0x0062;
    L_0x003a:
        r11 = java.lang.Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT;
        if (r5 == r11) goto L_0x0062;
    L_0x003e:
        r11 = java.lang.Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS;
        if (r5 == r11) goto L_0x0062;
    L_0x0042:
        r11 = java.lang.Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A;
        if (r5 == r11) goto L_0x0062;
    L_0x0046:
        r11 = java.lang.Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B;
        if (r5 == r11) goto L_0x0062;
    L_0x004a:
        r11 = java.lang.Character.UnicodeBlock.ENCLOSED_CJK_LETTERS_AND_MONTHS;
        if (r5 == r11) goto L_0x0062;
    L_0x004e:
        r11 = java.lang.Character.UnicodeBlock.HANGUL_JAMO;
        if (r5 == r11) goto L_0x0062;
    L_0x0052:
        r11 = java.lang.Character.UnicodeBlock.HANGUL_SYLLABLES;
        if (r5 == r11) goto L_0x0062;
    L_0x0056:
        r11 = java.lang.Character.UnicodeBlock.HIRAGANA;
        if (r5 == r11) goto L_0x0062;
    L_0x005a:
        r11 = java.lang.Character.UnicodeBlock.KATAKANA;
        if (r5 == r11) goto L_0x0062;
    L_0x005e:
        r11 = java.lang.Character.UnicodeBlock.KATAKANA_PHONETIC_EXTENSIONS;
        if (r5 != r11) goto L_0x009b;
    L_0x0062:
        r5 = r3;
    L_0x0063:
        if (r5 != 0) goto L_0x007c;
    L_0x0065:
        r5 = 65382; // 0xff66 float:9.162E-41 double:3.2303E-319;
        if (r9 < r5) goto L_0x006f;
    L_0x006a:
        r5 = 65437; // 0xff9d float:9.1697E-41 double:3.233E-319;
        if (r9 <= r5) goto L_0x0079;
    L_0x006f:
        r5 = 65441; // 0xffa1 float:9.1702E-41 double:3.2332E-319;
        if (r9 < r5) goto L_0x009d;
    L_0x0074:
        r5 = 65500; // 0xffdc float:9.1785E-41 double:3.23613E-319;
        if (r9 > r5) goto L_0x009d;
    L_0x0079:
        r5 = r3;
    L_0x007a:
        if (r5 == 0) goto L_0x009f;
    L_0x007c:
        r5 = r3;
    L_0x007d:
        if (r5 == 0) goto L_0x00a1;
    L_0x007f:
        if (r4 == 0) goto L_0x008b;
    L_0x0081:
        r4 = new java.lang.String;
        r5 = r1 - r0;
        r4.<init>(r7, r0, r5);
        r6.add(r4);
    L_0x008b:
        r4 = new java.lang.String;
        r4.<init>(r7, r1, r10);
        r6.add(r4);
        r4 = r0;
        r0 = r2;
    L_0x0095:
        r1 = r1 + r10;
        r12 = r0;
        r0 = r4;
        r4 = r12;
        goto L_0x0016;
    L_0x009b:
        r5 = r2;
        goto L_0x0063;
    L_0x009d:
        r5 = r2;
        goto L_0x007a;
    L_0x009f:
        r5 = r2;
        goto L_0x007d;
    L_0x00a1:
        r5 = java.lang.Character.isLetterOrDigit(r9);
        if (r5 != 0) goto L_0x00b6;
    L_0x00a7:
        r5 = java.lang.Character.getType(r9);
        r11 = 6;
        if (r5 == r11) goto L_0x00b6;
    L_0x00ae:
        r5 = java.lang.Character.getType(r9);
        r11 = 8;
        if (r5 != r11) goto L_0x00bc;
    L_0x00b6:
        if (r4 != 0) goto L_0x00b9;
    L_0x00b8:
        r0 = r1;
    L_0x00b9:
        r4 = r0;
        r0 = r3;
        goto L_0x0095;
    L_0x00bc:
        if (r14 == 0) goto L_0x00d4;
    L_0x00be:
        r5 = java.lang.Character.charCount(r9);
        if (r5 != r3) goto L_0x00d4;
    L_0x00c4:
        r5 = java.lang.Character.toChars(r9);
        r5 = r5[r2];
        r9 = 39;
        if (r5 != r9) goto L_0x00d4;
    L_0x00ce:
        if (r4 != 0) goto L_0x00d1;
    L_0x00d0:
        r0 = r1;
    L_0x00d1:
        r4 = r0;
        r0 = r3;
        goto L_0x0095;
    L_0x00d4:
        if (r4 == 0) goto L_0x00fc;
    L_0x00d6:
        r4 = new java.lang.String;
        r5 = r1 - r0;
        r4.<init>(r7, r0, r5);
        r6.add(r4);
        r4 = r0;
        r0 = r2;
        goto L_0x0095;
    L_0x00e3:
        if (r4 == 0) goto L_0x00ee;
    L_0x00e5:
        r2 = new java.lang.String;
        r1 = r1 - r0;
        r2.<init>(r7, r0, r1);
        r6.add(r2);
    L_0x00ee:
        r0 = r6.size();
        r0 = new java.lang.String[r0];
        r0 = r6.toArray(r0);
        r0 = (java.lang.String[]) r0;
        goto L_0x0005;
    L_0x00fc:
        r12 = r4;
        r4 = r0;
        r0 = r12;
        goto L_0x0095;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzhj.zzd(java.lang.String, boolean):java.lang.String[]");
    }
}
