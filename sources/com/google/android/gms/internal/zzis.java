package com.google.android.gms.internal;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

@zzzn
public final class zzis {
    private Bundle mExtras;
    private long zzAd;
    private int zzAe;
    private List<String> zzAf;
    private boolean zzAg;
    private int zzAh;
    private String zzAi;
    private zzlt zzAj;
    private String zzAk;
    private Bundle zzAl;
    private Bundle zzAm;
    private List<String> zzAn;
    private String zzAo;
    private String zzAp;
    private boolean zzAq;
    private Location zzde;
    private boolean zzsu;

    public zzis() {
        this.zzAd = -1;
        this.mExtras = new Bundle();
        this.zzAe = -1;
        this.zzAf = new ArrayList();
        this.zzAg = false;
        this.zzAh = -1;
        this.zzsu = false;
        this.zzAi = null;
        this.zzAj = null;
        this.zzde = null;
        this.zzAk = null;
        this.zzAl = new Bundle();
        this.zzAm = new Bundle();
        this.zzAn = new ArrayList();
        this.zzAo = null;
        this.zzAp = null;
        this.zzAq = false;
    }

    public zzis(zzir zzir) {
        this.zzAd = zzir.zzzN;
        this.mExtras = zzir.extras;
        this.zzAe = zzir.zzzO;
        this.zzAf = zzir.zzzP;
        this.zzAg = zzir.zzzQ;
        this.zzAh = zzir.zzzR;
        this.zzsu = zzir.zzzS;
        this.zzAi = zzir.zzzT;
        this.zzAj = zzir.zzzU;
        this.zzde = zzir.zzzV;
        this.zzAk = zzir.zzzW;
        this.zzAl = zzir.zzzX;
        this.zzAm = zzir.zzzY;
        this.zzAn = zzir.zzzZ;
        this.zzAo = zzir.zzAa;
        this.zzAp = zzir.zzAb;
    }

    public final zzis zza(@Nullable Location location) {
        this.zzde = null;
        return this;
    }

    public final zzir zzdj() {
        return new zzir(7, this.zzAd, this.mExtras, this.zzAe, this.zzAf, this.zzAg, this.zzAh, this.zzsu, this.zzAi, this.zzAj, this.zzde, this.zzAk, this.zzAl, this.zzAm, this.zzAn, this.zzAo, this.zzAp, false);
    }
}
