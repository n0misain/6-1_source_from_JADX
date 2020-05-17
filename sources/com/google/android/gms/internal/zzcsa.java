package com.google.android.gms.internal;

import android.os.SystemClock;
import android.text.TextUtils;
import android.util.SparseArray;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.safetynet.HarmfulAppsData;
import com.google.android.gms.safetynet.SafeBrowsingData;
import com.google.android.gms.safetynet.SafeBrowsingThreat;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.safetynet.SafetyNetApi.AttestationResult;
import com.google.android.gms.safetynet.SafetyNetApi.HarmfulAppsResult;
import com.google.android.gms.safetynet.SafetyNetApi.RecaptchaTokenResult;
import com.google.android.gms.safetynet.SafetyNetApi.SafeBrowsingResult;
import com.google.android.gms.safetynet.SafetyNetApi.VerifyAppsUserResult;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class zzcsa implements SafetyNetApi {
    private static final String TAG = zzcsa.class.getSimpleName();
    protected static SparseArray<zzcsr> zzbBM;
    protected static long zzbBN;

    static class zza implements AttestationResult {
        private final Status mStatus;
        private final com.google.android.gms.safetynet.zza zzbBV;

        public zza(Status status, com.google.android.gms.safetynet.zza zza) {
            this.mStatus = status;
            this.zzbBV = zza;
        }

        public final String getJwsResult() {
            return this.zzbBV == null ? null : this.zzbBV.getJwsResult();
        }

        public final Status getStatus() {
            return this.mStatus;
        }
    }

    static abstract class zzb extends zzcrv<AttestationResult> {
        protected zzcrw zzbBW = new zzcsi(this);

        public zzb(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        protected final /* synthetic */ Result zzb(Status status) {
            return new zza(status, null);
        }
    }

    static abstract class zzc extends zzcrv<VerifyAppsUserResult> {
        protected zzcrw zzbBW = new zzcsj(this);

        public zzc(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        protected final /* synthetic */ Result zzb(Status status) {
            return new zzj(status, false);
        }
    }

    static abstract class zzd extends zzcrv<HarmfulAppsResult> {
        protected final zzcrw zzbBW = new zzcsk(this);

        public zzd(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        protected final /* synthetic */ Result zzb(Status status) {
            return new zzg(status, null);
        }
    }

    static abstract class zze extends zzcrv<RecaptchaTokenResult> {
        protected zzcrw zzbBW = new zzcsl(this);

        public zze(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        protected final /* synthetic */ Result zzb(Status status) {
            return new zzh(status, null);
        }
    }

    static abstract class zzf extends zzcrv<SafeBrowsingResult> {
        protected zzcrw zzbBW = new zzcsm(this);

        public zzf(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        protected final /* synthetic */ Result zzb(Status status) {
            return new zzi(status, null);
        }
    }

    static class zzg implements HarmfulAppsResult {
        private final Status mStatus;
        private final com.google.android.gms.safetynet.zzd zzbCc;

        public zzg(Status status, com.google.android.gms.safetynet.zzd zzd) {
            this.mStatus = status;
            this.zzbCc = zzd;
        }

        public final List<HarmfulAppsData> getHarmfulAppsList() {
            return this.zzbCc == null ? Collections.emptyList() : Arrays.asList(this.zzbCc.zzbBH);
        }

        public final long getLastScanTimeMs() {
            return this.zzbCc == null ? 0 : this.zzbCc.zzbBG;
        }

        public final Status getStatus() {
            return this.mStatus;
        }
    }

    static class zzh implements RecaptchaTokenResult {
        private final Status mStatus;
        private final com.google.android.gms.safetynet.zzf zzbCd;

        public zzh(Status status, com.google.android.gms.safetynet.zzf zzf) {
            this.mStatus = status;
            this.zzbCd = zzf;
        }

        public final Status getStatus() {
            return this.mStatus;
        }

        public final String getTokenResult() {
            return this.zzbCd == null ? null : this.zzbCd.getTokenResult();
        }
    }

    static class zzi implements SafeBrowsingResult {
        private Status mStatus;
        private String zzbBI = null;
        private final SafeBrowsingData zzbCe;

        public zzi(Status status, SafeBrowsingData safeBrowsingData) {
            this.mStatus = status;
            this.zzbCe = safeBrowsingData;
            if (this.zzbCe != null) {
                this.zzbBI = this.zzbCe.getMetadata();
            } else if (this.mStatus.isSuccess()) {
                this.mStatus = new Status(8);
            }
        }

        public final List<SafeBrowsingThreat> getDetectedThreats() {
            List<SafeBrowsingThreat> arrayList = new ArrayList();
            if (this.zzbBI == null) {
                return arrayList;
            }
            try {
                JSONArray jSONArray = new JSONObject(this.zzbBI).getJSONArray("matches");
                for (int i = 0; i < jSONArray.length(); i++) {
                    try {
                        arrayList.add(new SafeBrowsingThreat(Integer.parseInt(jSONArray.getJSONObject(i).getString("threat_type"))));
                    } catch (JSONException e) {
                    } catch (NumberFormatException e2) {
                    }
                }
                return arrayList;
            } catch (JSONException e3) {
                return arrayList;
            }
        }

        public final String getMetadata() {
            return this.zzbBI;
        }

        public final Status getStatus() {
            return this.mStatus;
        }
    }

    static class zzj implements VerifyAppsUserResult {
        private Status mStatus;
        private boolean zzzE;

        public zzj(Status status, boolean z) {
            this.mStatus = status;
            this.zzzE = z;
        }

        public final Status getStatus() {
            return this.mStatus;
        }

        public final boolean isVerifyAppsEnabled() {
            return (this.mStatus == null || !this.mStatus.isSuccess()) ? false : this.zzzE;
        }
    }

    public static PendingResult<SafeBrowsingResult> zza(GoogleApiClient googleApiClient, String str, int i, String str2, int... iArr) {
        if (iArr.length == 0) {
            throw new IllegalArgumentException("Null threatTypes in lookupUri");
        } else if (!TextUtils.isEmpty(str)) {
            return googleApiClient.zzd(new zzcsd(googleApiClient, iArr, i, str, str2));
        } else {
            throw new IllegalArgumentException("Null or empty uri in lookupUri");
        }
    }

    public static PendingResult<AttestationResult> zza(GoogleApiClient googleApiClient, byte[] bArr, String str) {
        return googleApiClient.zzd(new zzcsb(googleApiClient, bArr, str));
    }

    public PendingResult<AttestationResult> attest(GoogleApiClient googleApiClient, byte[] bArr) {
        return zza(googleApiClient, bArr, null);
    }

    public PendingResult<VerifyAppsUserResult> enableVerifyApps(GoogleApiClient googleApiClient) {
        return googleApiClient.zzd(new zzcsf(this, googleApiClient));
    }

    public PendingResult<VerifyAppsUserResult> isVerifyAppsEnabled(GoogleApiClient googleApiClient) {
        return googleApiClient.zzd(new zzcse(this, googleApiClient));
    }

    public boolean isVerifyAppsEnabled(android.content.Context r7) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x003a in list [B:11:0x0037]
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:43)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/574268151.run(Unknown Source)
*/
        /*
        r6 = this;
        r1 = 0;
        r0 = new com.google.android.gms.common.api.GoogleApiClient$Builder;
        r0.<init>(r7);
        r2 = com.google.android.gms.safetynet.SafetyNet.API;
        r0 = r0.addApi(r2);
        r2 = r0.build();
        r4 = 3;
        r0 = java.util.concurrent.TimeUnit.SECONDS;	 Catch:{ all -> 0x0044 }
        r0 = r2.blockingConnect(r4, r0);	 Catch:{ all -> 0x0044 }
        r0 = r0.isSuccess();	 Catch:{ all -> 0x0044 }
        if (r0 == 0) goto L_0x003d;	 Catch:{ all -> 0x0044 }
    L_0x001e:
        r0 = r6.isVerifyAppsEnabled(r2);	 Catch:{ all -> 0x0044 }
        r4 = 3;	 Catch:{ all -> 0x0044 }
        r3 = java.util.concurrent.TimeUnit.SECONDS;	 Catch:{ all -> 0x0044 }
        r0 = r0.await(r4, r3);	 Catch:{ all -> 0x0044 }
        r0 = (com.google.android.gms.safetynet.SafetyNetApi.VerifyAppsUserResult) r0;	 Catch:{ all -> 0x0044 }
        if (r0 == 0) goto L_0x003b;	 Catch:{ all -> 0x0044 }
    L_0x002e:
        r0 = r0.isVerifyAppsEnabled();	 Catch:{ all -> 0x0044 }
        if (r0 == 0) goto L_0x003b;
    L_0x0034:
        r0 = 1;
    L_0x0035:
        if (r2 == 0) goto L_0x003a;
    L_0x0037:
        r2.disconnect();
    L_0x003a:
        return r0;
    L_0x003b:
        r0 = r1;
        goto L_0x0035;
    L_0x003d:
        if (r2 == 0) goto L_0x0042;
    L_0x003f:
        r2.disconnect();
    L_0x0042:
        r0 = r1;
        goto L_0x003a;
    L_0x0044:
        r0 = move-exception;
        if (r2 == 0) goto L_0x004a;
    L_0x0047:
        r2.disconnect();
    L_0x004a:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcsa.isVerifyAppsEnabled(android.content.Context):boolean");
    }

    public PendingResult<HarmfulAppsResult> listHarmfulApps(GoogleApiClient googleApiClient) {
        return googleApiClient.zzd(new zzcsg(this, googleApiClient));
    }

    public PendingResult<SafeBrowsingResult> lookupUri(GoogleApiClient googleApiClient, String str, String str2, int... iArr) {
        return zza(googleApiClient, str, 1, str2, iArr);
    }

    public PendingResult<SafeBrowsingResult> lookupUri(GoogleApiClient googleApiClient, String str, int... iArr) {
        return zza(googleApiClient, str, 1, null, iArr);
    }

    public PendingResult<SafeBrowsingResult> lookupUri(GoogleApiClient googleApiClient, List<Integer> list, String str) {
        return zza(googleApiClient, list, str, null);
    }

    public boolean lookupUriInLocalBlacklist(String str, int... iArr) {
        if (iArr == null) {
            throw new IllegalArgumentException("Null threatTypes in lookupUri");
        } else if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Null or empty uri in lookupUri");
        } else if (zzbBM == null || zzbBN == 0 || SystemClock.elapsedRealtime() - zzbBN >= 1200000) {
            return true;
        } else {
            if (zzbBM == null || zzbBM.size() == 0) {
                return true;
            }
            List<zzcsp> zzAk = new zzcss(str).zzAk();
            if (zzAk == null || zzAk.isEmpty()) {
                return true;
            }
            for (zzcsp zzcsp : zzAk) {
                for (int i : iArr) {
                    zzcsr zzcsr = (zzcsr) zzbBM.get(i);
                    if (zzcsr == null) {
                        return true;
                    }
                    if (zzcsr.zzr(zzcsp.zzbu(4).getBytes())) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    public PendingResult<RecaptchaTokenResult> verifyWithRecaptcha(GoogleApiClient googleApiClient, String str) {
        if (!TextUtils.isEmpty(str)) {
            return googleApiClient.zzd(new zzcsh(this, googleApiClient, str));
        }
        throw new IllegalArgumentException("Null or empty site key in verifyWithRecaptcha");
    }

    public final PendingResult<SafeBrowsingResult> zza(GoogleApiClient googleApiClient, List<Integer> list, String str, String str2) {
        if (list == null) {
            throw new IllegalArgumentException("Null threatTypes in lookupUri");
        } else if (!TextUtils.isEmpty(str)) {
            return googleApiClient.zzd(new zzcsc(this, googleApiClient, list, str, str2));
        } else {
            throw new IllegalArgumentException("Null or empty uri in lookupUri");
        }
    }
}
