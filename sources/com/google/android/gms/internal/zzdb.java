package com.google.android.gms.internal;

import android.content.Context;
import android.os.Build.VERSION;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.internal.zzbs;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.zze;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class zzdb {
    private static final String TAG = zzdb.class.getSimpleName();
    private static Object zzqQ = new Object();
    private static zze zzqS = null;
    protected Context zzqD;
    private Context zzqE;
    private ExecutorService zzqF;
    private DexClassLoader zzqG;
    private zzcw zzqH;
    private byte[] zzqI;
    private volatile AdvertisingIdClient zzqJ = null;
    private Future zzqK = null;
    private volatile zzax zzqL = null;
    private Future zzqM = null;
    private zzcn zzqN;
    private GoogleApiClient zzqO = null;
    protected boolean zzqP = false;
    private boolean zzqR = false;
    protected boolean zzqT = false;
    private Map<Pair<String, String>, zzea> zzqU;
    private boolean zzqV = false;
    private volatile boolean zzqk = false;

    private zzdb(Context context) {
        this.zzqD = context;
        this.zzqE = context.getApplicationContext();
        this.zzqU = new HashMap();
    }

    private final void zzM() {
        try {
            if (this.zzqJ == null && this.zzqE != null) {
                AdvertisingIdClient advertisingIdClient = new AdvertisingIdClient(this.zzqE);
                advertisingIdClient.start();
                this.zzqJ = advertisingIdClient;
            }
        } catch (GooglePlayServicesNotAvailableException e) {
            this.zzqJ = null;
        } catch (IOException e2) {
            this.zzqJ = null;
        } catch (GooglePlayServicesRepairableException e3) {
            this.zzqJ = null;
        }
    }

    @VisibleForTesting
    private final zzax zzN() {
        zzax zzax = null;
        try {
            zzax = zzcaq.zzn(this.zzqD, this.zzqD.getPackageName(), Integer.toString(this.zzqD.getPackageManager().getPackageInfo(this.zzqD.getPackageName(), 0).versionCode));
        } catch (Throwable th) {
        }
        return zzax;
    }

    public static zzdb zza(Context context, String str, String str2, boolean z) {
        File file;
        boolean z2 = true;
        zzdb zzdb = new zzdb(context);
        try {
            zzdb.zzqF = Executors.newCachedThreadPool();
            zzdb.zzqk = z;
            if (z) {
                zzdb.zzqK = zzdb.zzqF.submit(new zzdc(zzdb));
            }
            zzdb.zzqF.execute(new zzde(zzdb));
            try {
                zzqS = zze.zzoW();
                zzdb.zzqP = zze.zzau(zzdb.zzqD) > 0;
                if (zzqS.isGooglePlayServicesAvailable(zzdb.zzqD) != 0) {
                    z2 = false;
                }
                zzdb.zzqR = z2;
                if (zzdb.zzqD.getApplicationContext() != null) {
                    zzdb.zzqO = new Builder(zzdb.zzqD).addApi(zzazn.API).build();
                }
            } catch (Throwable th) {
            }
            zzdb.zza(0, true);
            if (zzdg.zzS()) {
                if (((Boolean) zzbs.zzbL().zzd(zzmo.zzFa)).booleanValue()) {
                    throw new IllegalStateException("Task Context initialization must not be called from the UI thread.");
                }
            }
            zzdb.zzqH = new zzcw(null);
            zzdb.zzqI = zzdb.zzqH.zzl(str);
            File cacheDir = zzdb.zzqD.getCacheDir();
            if (cacheDir == null) {
                cacheDir = zzdb.zzqD.getDir("dex", 0);
                if (cacheDir == null) {
                    throw new zzcy();
                }
            }
            File file2 = cacheDir;
            String str3 = "1489418796403";
            file = new File(String.format("%s/%s.jar", new Object[]{file2, str3}));
            if (!file.exists()) {
                byte[] zza = zzdb.zzqH.zza(zzdb.zzqI, str2);
                file.createNewFile();
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(zza, 0, zza.length);
                fileOutputStream.close();
            }
            zzdb.zzb(file2, str3);
            zzdb.zzqG = new DexClassLoader(file.getAbsolutePath(), file2.getAbsolutePath(), null, zzdb.zzqD.getClassLoader());
            zza(file);
            zzdb.zza(file2, str3);
            zzm(String.format("%s/%s.dex", new Object[]{file2, str3}));
            zzdb.zzqN = new zzcn(zzdb);
            zzdb.zzqV = true;
        } catch (Throwable e) {
            throw new zzcy(e);
        } catch (Throwable e2) {
            throw new zzcy(e2);
        } catch (Throwable e22) {
            throw new zzcy(e22);
        } catch (Throwable e222) {
            throw new zzcy(e222);
        } catch (Throwable e2222) {
            throw new zzcy(e2222);
        } catch (zzcy e3) {
        } catch (Throwable th2) {
            zza(file);
            zzdb.zza(file2, str3);
            zzm(String.format("%s/%s.dex", new Object[]{file2, str3}));
        }
        return zzdb;
    }

    private static void zza(File file) {
        if (file.exists()) {
            file.delete();
            return;
        }
        Log.d(TAG, String.format("File %s not found. No need for deletion", new Object[]{file.getAbsolutePath()}));
    }

    private final void zza(File file, String str) {
        FileOutputStream fileOutputStream;
        FileInputStream fileInputStream;
        Throwable th;
        FileOutputStream fileOutputStream2 = null;
        File file2 = new File(String.format("%s/%s.tmp", new Object[]{file, str}));
        if (!file2.exists()) {
            File file3 = new File(String.format("%s/%s.dex", new Object[]{file, str}));
            if (file3.exists()) {
                long length = file3.length();
                if (length > 0) {
                    byte[] bArr = new byte[((int) length)];
                    FileInputStream fileInputStream2;
                    try {
                        fileInputStream2 = new FileInputStream(file3);
                        try {
                            if (fileInputStream2.read(bArr) <= 0) {
                                try {
                                    fileInputStream2.close();
                                } catch (IOException e) {
                                }
                                zza(file3);
                                return;
                            }
                            adp zzbc = new zzbc();
                            zzbc.zzcG = VERSION.SDK.getBytes();
                            zzbc.zzcF = str.getBytes();
                            bArr = this.zzqH.zzc(this.zzqI, bArr).getBytes();
                            zzbc.data = bArr;
                            zzbc.zzcE = zzbv.zzb(bArr);
                            file2.createNewFile();
                            fileOutputStream = new FileOutputStream(file2);
                            try {
                                byte[] zzc = adp.zzc(zzbc);
                                fileOutputStream.write(zzc, 0, zzc.length);
                                fileOutputStream.close();
                                try {
                                    fileInputStream2.close();
                                } catch (IOException e2) {
                                }
                                try {
                                    fileOutputStream.close();
                                } catch (IOException e3) {
                                }
                                zza(file3);
                            } catch (IOException e4) {
                                fileInputStream = fileInputStream2;
                                if (fileInputStream != null) {
                                    try {
                                        fileInputStream.close();
                                    } catch (IOException e5) {
                                    }
                                }
                                if (fileOutputStream != null) {
                                    try {
                                        fileOutputStream.close();
                                    } catch (IOException e6) {
                                    }
                                }
                                zza(file3);
                            } catch (NoSuchAlgorithmException e7) {
                                fileInputStream = fileInputStream2;
                                if (fileInputStream != null) {
                                    fileInputStream.close();
                                }
                                if (fileOutputStream != null) {
                                    fileOutputStream.close();
                                }
                                zza(file3);
                            } catch (zzcx e8) {
                                fileInputStream = fileInputStream2;
                                if (fileInputStream != null) {
                                    fileInputStream.close();
                                }
                                if (fileOutputStream != null) {
                                    fileOutputStream.close();
                                }
                                zza(file3);
                            } catch (Throwable th2) {
                                Throwable th3 = th2;
                                fileOutputStream2 = fileOutputStream;
                                th = th3;
                                if (fileInputStream2 != null) {
                                    try {
                                        fileInputStream2.close();
                                    } catch (IOException e9) {
                                    }
                                }
                                if (fileOutputStream2 != null) {
                                    try {
                                        fileOutputStream2.close();
                                    } catch (IOException e10) {
                                    }
                                }
                                zza(file3);
                                throw th;
                            }
                        } catch (IOException e11) {
                            fileOutputStream = null;
                            fileInputStream = fileInputStream2;
                            if (fileInputStream != null) {
                                fileInputStream.close();
                            }
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                            zza(file3);
                        } catch (NoSuchAlgorithmException e12) {
                            fileOutputStream = null;
                            fileInputStream = fileInputStream2;
                            if (fileInputStream != null) {
                                fileInputStream.close();
                            }
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                            zza(file3);
                        } catch (zzcx e13) {
                            fileOutputStream = null;
                            fileInputStream = fileInputStream2;
                            if (fileInputStream != null) {
                                fileInputStream.close();
                            }
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                            zza(file3);
                        } catch (Throwable th4) {
                            th = th4;
                            if (fileInputStream2 != null) {
                                fileInputStream2.close();
                            }
                            if (fileOutputStream2 != null) {
                                fileOutputStream2.close();
                            }
                            zza(file3);
                            throw th;
                        }
                    } catch (IOException e14) {
                        fileOutputStream = null;
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        zza(file3);
                    } catch (NoSuchAlgorithmException e15) {
                        fileOutputStream = null;
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        zza(file3);
                    } catch (zzcx e16) {
                        fileOutputStream = null;
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        zza(file3);
                    } catch (Throwable th5) {
                        th = th5;
                        fileInputStream2 = null;
                        if (fileInputStream2 != null) {
                            fileInputStream2.close();
                        }
                        if (fileOutputStream2 != null) {
                            fileOutputStream2.close();
                        }
                        zza(file3);
                        throw th;
                    }
                }
            }
        }
    }

    private static boolean zza(int i, zzax zzax) {
        if (i < 4) {
            if (zzax == null) {
                return true;
            }
            if (((Boolean) zzbs.zzbL().zzd(zzmo.zzFd)).booleanValue() && (zzax.zzaT == null || zzax.zzaT.equals("0000000000000000000000000000000000000000000000000000000000000000"))) {
                return true;
            }
            if (((Boolean) zzbs.zzbL().zzd(zzmo.zzFe)).booleanValue() && (zzax.zzbZ == null || zzax.zzbZ.zzcx == null || zzax.zzbZ.zzcx.longValue() == -2)) {
                return true;
            }
        }
        return false;
    }

    private final boolean zzb(File file, String str) {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2;
        Throwable th;
        FileOutputStream fileOutputStream = null;
        File file2 = new File(String.format("%s/%s.tmp", new Object[]{file, str}));
        if (!file2.exists()) {
            return false;
        }
        File file3 = new File(String.format("%s/%s.dex", new Object[]{file, str}));
        if (file3.exists()) {
            return false;
        }
        FileOutputStream fileOutputStream2;
        try {
            long length = file2.length();
            if (length <= 0) {
                zza(file2);
                return false;
            }
            byte[] bArr = new byte[((int) length)];
            fileInputStream = new FileInputStream(file2);
            try {
                if (fileInputStream.read(bArr) <= 0) {
                    Log.d(TAG, "Cannot read the cache data.");
                    zza(file2);
                    try {
                        fileInputStream.close();
                    } catch (IOException e) {
                    }
                    return false;
                }
                zzbc zzbc = (zzbc) adp.zza(new zzbc(), bArr);
                if (str.equals(new String(zzbc.zzcF)) && Arrays.equals(zzbc.zzcE, zzbv.zzb(zzbc.data)) && Arrays.equals(zzbc.zzcG, VERSION.SDK.getBytes())) {
                    byte[] zza = this.zzqH.zza(this.zzqI, new String(zzbc.data));
                    file3.createNewFile();
                    fileOutputStream2 = new FileOutputStream(file3);
                    try {
                        fileOutputStream2.write(zza, 0, zza.length);
                        try {
                            fileInputStream.close();
                        } catch (IOException e2) {
                        }
                        try {
                            fileOutputStream2.close();
                        } catch (IOException e3) {
                        }
                        return true;
                    } catch (IOException e4) {
                        fileInputStream2 = fileInputStream;
                        if (fileInputStream2 != null) {
                            try {
                                fileInputStream2.close();
                            } catch (IOException e5) {
                            }
                        }
                        if (fileOutputStream2 != null) {
                            try {
                                fileOutputStream2.close();
                            } catch (IOException e6) {
                            }
                        }
                        return false;
                    } catch (NoSuchAlgorithmException e7) {
                        fileInputStream2 = fileInputStream;
                        if (fileInputStream2 != null) {
                            fileInputStream2.close();
                        }
                        if (fileOutputStream2 != null) {
                            fileOutputStream2.close();
                        }
                        return false;
                    } catch (zzcx e8) {
                        fileInputStream2 = fileInputStream;
                        if (fileInputStream2 != null) {
                            fileInputStream2.close();
                        }
                        if (fileOutputStream2 != null) {
                            fileOutputStream2.close();
                        }
                        return false;
                    } catch (Throwable th2) {
                        fileOutputStream = fileOutputStream2;
                        th = th2;
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e9) {
                            }
                        }
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e10) {
                            }
                        }
                        throw th;
                    }
                }
                zza(file2);
                try {
                    fileInputStream.close();
                } catch (IOException e11) {
                }
                return false;
            } catch (IOException e12) {
                fileOutputStream2 = null;
                fileInputStream2 = fileInputStream;
                if (fileInputStream2 != null) {
                    fileInputStream2.close();
                }
                if (fileOutputStream2 != null) {
                    fileOutputStream2.close();
                }
                return false;
            } catch (NoSuchAlgorithmException e13) {
                fileOutputStream2 = null;
                fileInputStream2 = fileInputStream;
                if (fileInputStream2 != null) {
                    fileInputStream2.close();
                }
                if (fileOutputStream2 != null) {
                    fileOutputStream2.close();
                }
                return false;
            } catch (zzcx e14) {
                fileOutputStream2 = null;
                fileInputStream2 = fileInputStream;
                if (fileInputStream2 != null) {
                    fileInputStream2.close();
                }
                if (fileOutputStream2 != null) {
                    fileOutputStream2.close();
                }
                return false;
            } catch (Throwable th3) {
                th = th3;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                throw th;
            }
        } catch (IOException e15) {
            fileOutputStream2 = null;
            fileInputStream2 = null;
            if (fileInputStream2 != null) {
                fileInputStream2.close();
            }
            if (fileOutputStream2 != null) {
                fileOutputStream2.close();
            }
            return false;
        } catch (NoSuchAlgorithmException e16) {
            fileOutputStream2 = null;
            fileInputStream2 = null;
            if (fileInputStream2 != null) {
                fileInputStream2.close();
            }
            if (fileOutputStream2 != null) {
                fileOutputStream2.close();
            }
            return false;
        } catch (zzcx e17) {
            fileOutputStream2 = null;
            fileInputStream2 = null;
            if (fileInputStream2 != null) {
                fileInputStream2.close();
            }
            if (fileOutputStream2 != null) {
                fileOutputStream2.close();
            }
            return false;
        } catch (Throwable th4) {
            th = th4;
            fileInputStream = null;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            throw th;
        }
    }

    private static void zzm(String str) {
        zza(new File(str));
    }

    public final Context getApplicationContext() {
        return this.zzqE;
    }

    public final Context getContext() {
        return this.zzqD;
    }

    public final boolean isInitialized() {
        return this.zzqV;
    }

    public final ExecutorService zzC() {
        return this.zzqF;
    }

    public final DexClassLoader zzD() {
        return this.zzqG;
    }

    public final zzcw zzE() {
        return this.zzqH;
    }

    public final byte[] zzF() {
        return this.zzqI;
    }

    public final GoogleApiClient zzG() {
        return this.zzqO;
    }

    public final boolean zzH() {
        return this.zzqP;
    }

    public final zzcn zzI() {
        return this.zzqN;
    }

    public final boolean zzJ() {
        return this.zzqR;
    }

    public final zzax zzK() {
        return this.zzqL;
    }

    public final Future zzL() {
        return this.zzqM;
    }

    public final AdvertisingIdClient zzO() {
        if (!this.zzqk) {
            return null;
        }
        if (this.zzqJ != null) {
            return this.zzqJ;
        }
        if (this.zzqK != null) {
            try {
                this.zzqK.get(2000, TimeUnit.MILLISECONDS);
                this.zzqK = null;
            } catch (InterruptedException e) {
            } catch (ExecutionException e2) {
            } catch (TimeoutException e3) {
                this.zzqK.cancel(true);
            }
        }
        return this.zzqJ;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzP() {
        /*
        r2 = this;
        r1 = zzqQ;	 Catch:{ Throwable -> 0x001e }
        monitor-enter(r1);	 Catch:{ Throwable -> 0x001e }
        r0 = r2.zzqT;	 Catch:{ all -> 0x001b }
        if (r0 == 0) goto L_0x0009;
    L_0x0007:
        monitor-exit(r1);	 Catch:{ all -> 0x001b }
    L_0x0008:
        return;
    L_0x0009:
        r0 = r2.zzqR;	 Catch:{ all -> 0x001b }
        if (r0 == 0) goto L_0x0020;
    L_0x000d:
        r0 = r2.zzqO;	 Catch:{ all -> 0x001b }
        if (r0 == 0) goto L_0x0020;
    L_0x0011:
        r0 = r2.zzqO;	 Catch:{ all -> 0x001b }
        r0.connect();	 Catch:{ all -> 0x001b }
        r0 = 1;
        r2.zzqT = r0;	 Catch:{ all -> 0x001b }
    L_0x0019:
        monitor-exit(r1);	 Catch:{ all -> 0x001b }
        goto L_0x0008;
    L_0x001b:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x001b }
        throw r0;	 Catch:{ Throwable -> 0x001e }
    L_0x001e:
        r0 = move-exception;
        goto L_0x0008;
    L_0x0020:
        r0 = 0;
        r2.zzqT = r0;	 Catch:{ all -> 0x001b }
        goto L_0x0019;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzdb.zzP():void");
    }

    public final void zzQ() {
        synchronized (zzqQ) {
            if (this.zzqT && this.zzqO != null) {
                this.zzqO.disconnect();
                this.zzqT = false;
            }
        }
    }

    @VisibleForTesting
    final void zza(int i, boolean z) {
        if (this.zzqR) {
            Future submit = this.zzqF.submit(new zzdd(this, i, z));
            if (i == 0) {
                this.zzqM = submit;
            }
        }
    }

    public final boolean zza(String str, String str2, Class<?>... clsArr) {
        if (this.zzqU.containsKey(new Pair(str, str2))) {
            return false;
        }
        this.zzqU.put(new Pair(str, str2), new zzea(this, str, str2, clsArr));
        return true;
    }

    @VisibleForTesting
    final zzax zzb(int i, boolean z) {
        if (i > 0 && z) {
            try {
                Thread.sleep((long) (i * 1000));
            } catch (InterruptedException e) {
            }
        }
        return zzN();
    }

    public final Method zzc(String str, String str2) {
        zzea zzea = (zzea) this.zzqU.get(new Pair(str, str2));
        return zzea == null ? null : zzea.zzY();
    }

    public final int zzy() {
        return this.zzqN != null ? zzcn.zzy() : Integer.MIN_VALUE;
    }
}
