package com.google.android.gms.internal;

public class wp implements Comparable<wp> {
    private static final wp zzchN = new wp("[MIN_KEY]");
    private static final wp zzchO = new wp("[MAX_KEY]");
    private static final wp zzchP = new wp(".priority");
    private static final wp zzchQ = new wp(".info");
    private final String key;

    static class zza extends wp {
        private final int zzaIG;

        zza(String str, int i) {
            super(str);
            this.zzaIG = i;
        }

        protected final int intValue() {
            return this.zzaIG;
        }

        public final String toString() {
            String zzj = this.key;
            return new StringBuilder(String.valueOf(zzj).length() + 20).append("IntegerChildName(\"").append(zzj).append("\")").toString();
        }

        protected final boolean zzIO() {
            return true;
        }
    }

    private wp(String str) {
        this.key = str;
    }

    public static wp zzIJ() {
        return zzchN;
    }

    public static wp zzIK() {
        return zzchO;
    }

    public static wp zzIL() {
        return zzchP;
    }

    public static wp zzIM() {
        return zzchQ;
    }

    public static wp zzgT(String str) {
        Integer zzha = zd.zzha(str);
        return zzha != null ? new zza(str, zzha.intValue()) : str.equals(".priority") ? zzchP : new wp(str);
    }

    public final String asString() {
        return this.key;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return zzi((wp) obj);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof wp)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        return this.key.equals(((wp) obj).key);
    }

    public int hashCode() {
        return this.key.hashCode();
    }

    protected int intValue() {
        return 0;
    }

    public String toString() {
        String str = this.key;
        return new StringBuilder(String.valueOf(str).length() + 12).append("ChildKey(\"").append(str).append("\")").toString();
    }

    public final boolean zzIN() {
        return this == zzchP;
    }

    protected boolean zzIO() {
        return false;
    }

    public final int zzi(wp wpVar) {
        if (this == wpVar) {
            return 0;
        }
        if (this == zzchN || wpVar == zzchO) {
            return -1;
        }
        if (wpVar == zzchN || this == zzchO) {
            return 1;
        }
        if (!zzIO()) {
            return wpVar.zzIO() ? 1 : this.key.compareTo(wpVar.key);
        } else {
            if (!wpVar.zzIO()) {
                return -1;
            }
            int zzo = zd.zzo(intValue(), wpVar.intValue());
            return zzo == 0 ? zd.zzo(this.key.length(), wpVar.key.length()) : zzo;
        }
    }
}
