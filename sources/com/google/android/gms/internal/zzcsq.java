package com.google.android.gms.internal;

import android.support.v4.internal.view.SupportMenu;
import com.facebook.appevents.AppEventsConstants;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Locale;
import java.util.regex.Pattern;

final class zzcsq {
    private static final Pattern zzbCf = Pattern.compile("[.]");
    private static final Inet4Address zzbCg = ((Inet4Address) zzeN("127.0.0.1"));
    private static final Inet4Address zzbCh = ((Inet4Address) zzeN("0.0.0.0"));

    static String zza(InetAddress inetAddress) {
        if (inetAddress instanceof Inet4Address) {
            return inetAddress.getHostAddress();
        }
        int i;
        byte[] address = inetAddress.getAddress();
        int[] iArr = new int[8];
        for (i = 0; i < 8; i++) {
            iArr[i] = (((address[i * 2] & 255) << 8) | 0) | (address[(i * 2) + 1] & 255);
        }
        int i2 = 0;
        int i3 = -1;
        int i4 = -1;
        int i5 = -1;
        while (i2 < 9) {
            if (i2 >= 8 || iArr[i2] != 0) {
                if (i3 >= 0) {
                    i = i2 - i3;
                    if (i > i4) {
                        i4 = i3;
                    } else {
                        i = i4;
                        i4 = i5;
                    }
                    i3 = -1;
                    i5 = i4;
                    i4 = i;
                }
            } else if (i3 < 0) {
                i3 = i2;
            }
            i2++;
        }
        if (i4 >= 2) {
            Arrays.fill(iArr, i5, i5 + i4, -1);
        }
        StringBuilder stringBuilder = new StringBuilder(39);
        i4 = 0;
        Object obj = null;
        while (i4 < 8) {
            Object obj2 = iArr[i4] >= 0 ? 1 : null;
            if (obj2 != null) {
                if (obj != null) {
                    stringBuilder.append(':');
                }
                stringBuilder.append(Integer.toHexString(iArr[i4]));
            } else if (i4 == 0 || obj != null) {
                stringBuilder.append("::");
            }
            i4++;
            obj = obj2;
        }
        return stringBuilder.toString();
    }

    static boolean zzeI(String str) {
        return zzeJ(str) != null;
    }

    private static byte[] zzeJ(String str) {
        int i;
        int i2 = 0;
        int i3 = 0;
        for (i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt == '.') {
                i2 = 1;
            } else if (charAt == ':') {
                if (i2 != 0) {
                    return null;
                }
                i3 = 1;
            } else if (Character.digit(charAt, 16) == -1) {
                return null;
            }
        }
        if (i3 == 0) {
            return i2 != 0 ? zzeK(str) : null;
        } else {
            if (i2 != 0) {
                i = str.lastIndexOf(58);
                String substring = str.substring(0, i + 1);
                byte[] zzeK = zzeK(str.substring(i + 1));
                if (zzeK == null) {
                    str = null;
                } else {
                    String toHexString = Integer.toHexString(((zzeK[0] & 255) << 8) | (zzeK[1] & 255));
                    String toHexString2 = Integer.toHexString((zzeK[3] & 255) | ((zzeK[2] & 255) << 8));
                    str = new StringBuilder(((String.valueOf(substring).length() + 1) + String.valueOf(toHexString).length()) + String.valueOf(toHexString2).length()).append(substring).append(toHexString).append(":").append(toHexString2).toString();
                }
                if (str == null) {
                    return null;
                }
            }
            return zzeL(str);
        }
    }

    private static byte[] zzeK(String str) {
        int i = 0;
        byte[] bArr = new byte[4];
        try {
            String[] split = zzbCf.split(str, 4);
            int length = split.length;
            int i2 = 0;
            while (i < length) {
                String str2 = split[i];
                int i3 = i2 + 1;
                int parseInt = Integer.parseInt(str2);
                if (parseInt > 255 || (str2.startsWith(AppEventsConstants.EVENT_PARAM_VALUE_NO) && str2.length() > 1)) {
                    throw new NumberFormatException();
                }
                bArr[i2] = (byte) parseInt;
                i++;
                i2 = i3;
            }
            return i2 != 4 ? null : bArr;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private static byte[] zzeL(String str) {
        String[] split = str.split(":", 10);
        if (split.length < 3 || split.length > 9) {
            return null;
        }
        int i;
        int length;
        int i2 = -1;
        for (i = 1; i < split.length - 1; i++) {
            if (split[i].length() == 0) {
                if (i2 >= 0) {
                    return null;
                }
                i2 = i;
            }
        }
        if (i2 >= 0) {
            length = (split.length - i2) - 1;
            if (split[0].length() == 0) {
                i = i2 - 1;
                if (i != 0) {
                    return null;
                }
            }
            i = i2;
            if (split[split.length - 1].length() == 0) {
                length--;
                if (length != 0) {
                    return null;
                }
            }
            int i3 = length;
            length = i;
            i = i3;
        } else {
            length = split.length;
            i = 0;
        }
        int i4 = 8 - (length + i);
        if (!i2 < 0 ? i4 > 0 : i4 == 0) {
            return null;
        }
        ByteBuffer allocate = ByteBuffer.allocate(16);
        i2 = 0;
        while (i2 < length) {
            try {
                allocate.putShort(zzeM(split[i2]));
                i2++;
            } catch (NumberFormatException e) {
                return null;
            }
        }
        for (i2 = 0; i2 < i4; i2++) {
            allocate.putShort((short) 0);
        }
        while (i > 0) {
            allocate.putShort(zzeM(split[split.length - i]));
            i--;
        }
        return allocate.array();
    }

    private static short zzeM(String str) {
        int parseInt = Integer.parseInt(str, 16);
        if (parseInt <= SupportMenu.USER_MASK) {
            return (short) parseInt;
        }
        throw new NumberFormatException();
    }

    static InetAddress zzeN(String str) {
        byte[] zzeJ = zzeJ(str);
        if (zzeJ != null) {
            return zzq(zzeJ);
        }
        Object[] objArr = new Object[]{str};
        throw new IllegalArgumentException(String.format(Locale.ROOT, "'%s' is not an IP string literal.", objArr));
    }

    private static InetAddress zzq(byte[] bArr) {
        try {
            return InetAddress.getByAddress(bArr);
        } catch (UnknownHostException e) {
            throw new AssertionError(e);
        }
    }
}
