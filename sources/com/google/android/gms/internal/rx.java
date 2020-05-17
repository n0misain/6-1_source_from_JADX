package com.google.android.gms.internal;

public final class rx {
    public String host;
    public boolean secure;
    public String zzbxU;
    public String zzceq;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        rx rxVar = (rx) obj;
        return (this.secure == rxVar.secure && this.host.equals(rxVar.host)) ? this.zzbxU.equals(rxVar.zzbxU) : false;
    }

    public final int hashCode() {
        return (((this.secure ? 1 : 0) + (this.host.hashCode() * 31)) * 31) + this.zzbxU.hashCode();
    }

    public final String toString() {
        String str = this.secure ? "s" : "";
        String str2 = this.host;
        return new StringBuilder((String.valueOf(str).length() + 7) + String.valueOf(str2).length()).append("http").append(str).append("://").append(str2).toString();
    }
}
