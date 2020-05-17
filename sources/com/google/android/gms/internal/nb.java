package com.google.android.gms.internal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.support.v4.media.session.PlaybackStateCompat;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.google.firebase.database.DatabaseException;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class nb implements ui {
    private static final Charset zzayt = Charset.forName(HttpRequest.CHARSET_UTF8);
    private final SQLiteDatabase zzbZD;
    private final wl zzbZE;
    private boolean zzbZF;
    private long zzbZG = 0;

    public nb(Context context, qd qdVar, String str) {
        try {
            String encode = URLEncoder.encode(str, "utf-8");
            this.zzbZE = qdVar.zzgP("Persistence");
            this.zzbZD = zzN(context, encode);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    private static xm zzB(byte[] bArr) {
        try {
            return xp.zza(yr.zzgW(new String(bArr, zzayt)), xd.zzJb());
        } catch (Throwable e) {
            Throwable th = e;
            String str = "Could not deserialize node: ";
            String valueOf = String.valueOf(new String(bArr, zzayt));
            throw new RuntimeException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str), th);
        }
    }

    private final void zzFw() {
        zd.zzb(this.zzbZF, "Transaction expected to already be in progress.");
    }

    private static SQLiteDatabase zzN(Context context, String str) {
        try {
            SQLiteDatabase writableDatabase = new ne(context, str).getWritableDatabase();
            writableDatabase.rawQuery("PRAGMA locking_mode = EXCLUSIVE", null).close();
            writableDatabase.beginTransaction();
            writableDatabase.endTransaction();
            return writableDatabase;
        } catch (Throwable e) {
            if (e instanceof SQLiteDatabaseLockedException) {
                throw new DatabaseException("Failed to gain exclusive lock to Firebase Database's offline persistence. This generally means you are using Firebase Database from multiple processes in your app. Keep in mind that multi-process Android apps execute the code in your Application class in all processes, so you may need to avoid initializing FirebaseDatabase in your Application class. If you are intentionally using Firebase Database from multiple processes, you can only enable offline persistence (i.e. call setPersistenceEnabled(true)) in one of them.", e);
            }
            throw e;
        }
    }

    private static byte[] zzQ(List<byte[]> list) {
        int i = 0;
        for (byte[] length : list) {
            i = length.length + i;
        }
        Object obj = new byte[i];
        i = 0;
        for (byte[] length2 : list) {
            System.arraycopy(length2, 0, obj, i, length2.length);
            i = length2.length + i;
        }
        return obj;
    }

    private static byte[] zzW(Object obj) {
        try {
            return yr.zzak(obj).getBytes(zzayt);
        } catch (Throwable e) {
            throw new RuntimeException("Could not serialize leaf node", e);
        }
    }

    private static int zza(qr qrVar, List<String> list, int i) {
        int i2 = i + 1;
        String zzc = zzc(qrVar);
        if (((String) list.get(i)).startsWith(zzc)) {
            while (i2 < list.size() && ((String) list.get(i2)).equals(zza(qrVar, i2 - i))) {
                i2++;
            }
            if (i2 < list.size()) {
                String str = (String) list.get(i2);
                String valueOf = String.valueOf(zzc);
                zzc = String.valueOf(".part-");
                if (str.startsWith(zzc.length() != 0 ? valueOf.concat(zzc) : new String(valueOf))) {
                    throw new IllegalStateException("Run did not finish with all parts");
                }
            }
            return i2 - i;
        }
        throw new IllegalStateException("Extracting split nodes needs to start with path prefix");
    }

    private final int zza(String str, qr qrVar) {
        String zzgC = zzgC(zzc(qrVar));
        return this.zzbZD.delete(str, "path >= ? AND path < ?", new String[]{r1, zzgC});
    }

    private final Cursor zza(qr qrVar, String[] strArr) {
        String zzc = zzc(qrVar);
        String zzgC = zzgC(zzc);
        String[] strArr2 = new String[(qrVar.size() + 3)];
        int i = 0;
        StringBuilder stringBuilder = new StringBuilder("(");
        qr qrVar2 = qrVar;
        while (!qrVar2.isEmpty()) {
            stringBuilder.append("path");
            stringBuilder.append(" = ? OR ");
            strArr2[i] = zzc(qrVar2);
            qrVar2 = qrVar2.zzHe();
            i++;
        }
        stringBuilder.append("path");
        stringBuilder.append(" = ?)");
        strArr2[i] = zzc(qr.zzGZ());
        String valueOf = String.valueOf(stringBuilder.toString());
        String valueOf2 = String.valueOf(" OR (path > ? AND path < ?)");
        String concat = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
        strArr2[qrVar.size() + 1] = zzc;
        strArr2[qrVar.size() + 2] = zzgC;
        return this.zzbZD.query("serverCache", strArr, concat, strArr2, null, null, "path");
    }

    private static String zza(qr qrVar, int i) {
        String valueOf = String.valueOf(zzc(qrVar));
        String valueOf2 = String.valueOf(String.format(".part-%04d", new Object[]{Integer.valueOf(i)}));
        return valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
    }

    private final void zza(qr qrVar, long j, String str, byte[] bArr) {
        zzFw();
        this.zzbZD.delete("writes", "id = ?", new String[]{String.valueOf(j)});
        if (bArr.length >= 262144) {
            List zzd = zzd(bArr, 262144);
            for (int i = 0; i < zzd.size(); i++) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("id", Long.valueOf(j));
                contentValues.put("path", zzc(qrVar));
                contentValues.put("type", str);
                contentValues.put("part", Integer.valueOf(i));
                contentValues.put("node", (byte[]) zzd.get(i));
                this.zzbZD.insertWithOnConflict("writes", null, contentValues, 5);
            }
            return;
        }
        ContentValues contentValues2 = new ContentValues();
        contentValues2.put("id", Long.valueOf(j));
        contentValues2.put("path", zzc(qrVar));
        contentValues2.put("type", str);
        contentValues2.put("part", null);
        contentValues2.put("node", bArr);
        this.zzbZD.insertWithOnConflict("writes", null, contentValues2, 5);
    }

    private final void zza(qr qrVar, qr qrVar2, uv<Long> uvVar, uv<Long> uvVar2, uj ujVar, List<za<qr, xm>> list) {
        if (uvVar.getValue() != null) {
            if (((Integer) ujVar.zza(Integer.valueOf(0), new nc(this, uvVar2))).intValue() > 0) {
                qr zzh = qrVar.zzh(qrVar2);
                if (this.zzbZE.zzIH()) {
                    this.zzbZE.zzb(String.format("Need to rewrite %d nodes below path %s", new Object[]{Integer.valueOf(r0), zzh}), null, new Object[0]);
                }
                ujVar.zza(null, new nd(this, uvVar2, list, qrVar2, zzb(zzh)));
                return;
            }
            return;
        }
        Iterator it = uvVar.zzHS().iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            wp wpVar = (wp) entry.getKey();
            uj zzd = ujVar.zzd((wp) entry.getKey());
            zza(qrVar, qrVar2.zza(wpVar), (uv) entry.getValue(), uvVar2.zze(wpVar), zzd, list);
        }
    }

    private final void zza(qr qrVar, xm xmVar, boolean z) {
        int i;
        int i2;
        long currentTimeMillis = System.currentTimeMillis();
        if (z) {
            i = 0;
            i2 = 0;
            for (xl xlVar : xmVar) {
                i2 += zza("serverCache", qrVar.zza(xlVar.zzJk()));
                i = zzc(qrVar.zza(xlVar.zzJk()), xlVar.zzFn()) + i;
            }
        } else {
            i2 = zza("serverCache", qrVar);
            i = zzc(qrVar, xmVar);
        }
        currentTimeMillis = System.currentTimeMillis() - currentTimeMillis;
        if (this.zzbZE.zzIH()) {
            this.zzbZE.zzb(String.format("Persisted a total of %d rows and deleted %d rows for a set at %s in %dms", new Object[]{Integer.valueOf(i), Integer.valueOf(i2), qrVar.toString(), Long.valueOf(currentTimeMillis)}), null, new Object[0]);
        }
    }

    private final xm zzb(qr qrVar) {
        List arrayList = new ArrayList();
        List arrayList2 = new ArrayList();
        long currentTimeMillis = System.currentTimeMillis();
        Cursor zza = zza(qrVar, new String[]{"path", Param.VALUE});
        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
        long currentTimeMillis3 = System.currentTimeMillis();
        while (zza.moveToNext()) {
            try {
                arrayList.add(zza.getString(0));
                arrayList2.add(zza.getBlob(1));
            } finally {
                zza.close();
            }
        }
        long currentTimeMillis4 = System.currentTimeMillis() - currentTimeMillis3;
        long currentTimeMillis5 = System.currentTimeMillis();
        xm zzJb = xd.zzJb();
        Object obj = null;
        Map hashMap = new HashMap();
        int i = 0;
        while (i < arrayList2.size()) {
            int zza2;
            qr qrVar2;
            xm zzB;
            Object obj2;
            xm xmVar;
            if (((String) arrayList.get(i)).endsWith(".part-0000")) {
                String str = (String) arrayList.get(i);
                qr qrVar3 = new qr(str.substring(0, str.length() - 10));
                zza2 = zza(qrVar3, arrayList, i);
                if (this.zzbZE.zzIH()) {
                    this.zzbZE.zzb("Loading split node with " + zza2 + " parts.", null, new Object[0]);
                }
                zza2 = (i + zza2) - 1;
                qrVar2 = qrVar3;
                zzB = zzB(zzQ(arrayList2.subList(i, i + zza2)));
            } else {
                xm zzB2 = zzB((byte[]) arrayList2.get(i));
                qr qrVar4 = new qr((String) arrayList.get(i));
                zzB = zzB2;
                zza2 = i;
                qrVar2 = qrVar4;
            }
            if (qrVar2.zzHf() != null && qrVar2.zzHf().zzIN()) {
                hashMap.put(qrVar2, zzB);
                obj2 = obj;
                xmVar = zzJb;
            } else if (qrVar2.zzi(qrVar)) {
                zd.zzb(obj == null, "Descendants of path must come after ancestors.");
                Object obj3 = obj;
                xmVar = zzB.zzN(qr.zza(qrVar2, qrVar));
                obj2 = obj3;
            } else if (qrVar.zzi(qrVar2)) {
                obj2 = 1;
                xmVar = zzJb.zzl(qr.zza(qrVar, qrVar2), zzB);
            } else {
                throw new IllegalStateException(String.format("Loading an unrelated row with path %s for %s", new Object[]{qrVar2, qrVar}));
            }
            i = zza2 + 1;
            zzJb = xmVar;
            obj = obj2;
        }
        for (Entry entry : hashMap.entrySet()) {
            zzJb = zzJb.zzl(qr.zza(qrVar, (qr) entry.getKey()), (xm) entry.getValue());
        }
        long currentTimeMillis6 = System.currentTimeMillis() - currentTimeMillis5;
        long currentTimeMillis7 = System.currentTimeMillis() - currentTimeMillis;
        if (this.zzbZE.zzIH()) {
            this.zzbZE.zzb(String.format("Loaded a total of %d rows for a total of %d nodes at %s in %dms (Query: %dms, Loading: %dms, Serializing: %dms)", new Object[]{Integer.valueOf(arrayList2.size()), Integer.valueOf(yy.zzo(zzJb)), qrVar, Long.valueOf(currentTimeMillis7), Long.valueOf(currentTimeMillis2), Long.valueOf(currentTimeMillis4), Long.valueOf(currentTimeMillis6)}), null, new Object[0]);
        }
        return zzJb;
    }

    private final int zzc(qr qrVar, xm xmVar) {
        long zzn = yy.zzn(xmVar);
        if (!(xmVar instanceof wr) || zzn <= PlaybackStateCompat.ACTION_PREPARE) {
            zzd(qrVar, xmVar);
            return 1;
        }
        if (this.zzbZE.zzIH()) {
            this.zzbZE.zzb(String.format("Node estimated serialized size at path %s of %d bytes exceeds limit of %d bytes. Splitting up.", new Object[]{qrVar, Long.valueOf(zzn), Integer.valueOf(16384)}), null, new Object[0]);
        }
        int i = 0;
        for (xl xlVar : xmVar) {
            i = zzc(qrVar.zza(xlVar.zzJk()), xlVar.zzFn()) + i;
        }
        if (!xmVar.zzIR().isEmpty()) {
            zzd(qrVar.zza(wp.zzIL()), xmVar.zzIR());
            i++;
        }
        zzd(qrVar, xd.zzJb());
        return i + 1;
    }

    private static String zzc(qr qrVar) {
        return qrVar.isEmpty() ? "/" : String.valueOf(qrVar.toString()).concat("/");
    }

    private static List<byte[]> zzd(byte[] bArr, int i) {
        int length = ((bArr.length - 1) / 262144) + 1;
        List<byte[]> arrayList = new ArrayList(length);
        for (int i2 = 0; i2 < length; i2++) {
            int min = Math.min(262144, bArr.length - (i2 * 262144));
            Object obj = new byte[min];
            System.arraycopy(bArr, i2 * 262144, obj, 0, min);
            arrayList.add(obj);
        }
        return arrayList;
    }

    private final void zzd(qr qrVar, xm xmVar) {
        byte[] zzW = zzW(xmVar.getValue(true));
        if (zzW.length >= 262144) {
            List zzd = zzd(zzW, 262144);
            if (this.zzbZE.zzIH()) {
                this.zzbZE.zzb("Saving huge leaf node with " + zzd.size() + " parts.", null, new Object[0]);
            }
            for (int i = 0; i < zzd.size(); i++) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("path", zza(qrVar, i));
                contentValues.put(Param.VALUE, (byte[]) zzd.get(i));
                this.zzbZD.insertWithOnConflict("serverCache", null, contentValues, 5);
            }
            return;
        }
        ContentValues contentValues2 = new ContentValues();
        contentValues2.put("path", zzc(qrVar));
        contentValues2.put(Param.VALUE, zzW);
        this.zzbZD.insertWithOnConflict("serverCache", null, contentValues2, 5);
    }

    private static String zzgC(String str) {
        String valueOf = String.valueOf(str.substring(0, str.length() - 1));
        return new StringBuilder(String.valueOf(valueOf).length() + 1).append(valueOf).append('0').toString();
    }

    private static String zzj(Collection<Long> collection) {
        StringBuilder stringBuilder = new StringBuilder();
        Object obj = 1;
        for (Long longValue : collection) {
            long longValue2 = longValue.longValue();
            if (obj == null) {
                stringBuilder.append(",");
            }
            stringBuilder.append(longValue2);
            obj = null;
        }
        return stringBuilder.toString();
    }

    public final void beginTransaction() {
        zd.zzb(!this.zzbZF, "runInTransaction called when an existing transaction is already in progress.");
        if (this.zzbZE.zzIH()) {
            this.zzbZE.zzb("Starting transaction.", null, new Object[0]);
        }
        this.zzbZD.beginTransaction();
        this.zzbZF = true;
        this.zzbZG = System.currentTimeMillis();
    }

    public final void endTransaction() {
        this.zzbZD.endTransaction();
        this.zzbZF = false;
        long currentTimeMillis = System.currentTimeMillis() - this.zzbZG;
        if (this.zzbZE.zzIH()) {
            this.zzbZE.zzb(String.format("Transaction completed. Elapsed: %dms", new Object[]{Long.valueOf(currentTimeMillis)}), null, new Object[0]);
        }
    }

    public final void setTransactionSuccessful() {
        this.zzbZD.setTransactionSuccessful();
    }

    public final List<tm> zzFs() {
        String[] strArr = new String[]{"id", "path", "type", "part", "node"};
        long currentTimeMillis = System.currentTimeMillis();
        Cursor query = this.zzbZD.query("writes", strArr, null, null, null, null, "id, part");
        List<tm> arrayList = new ArrayList();
        while (query.moveToNext()) {
            try {
                byte[] blob;
                Object tmVar;
                long j = query.getLong(0);
                qr qrVar = new qr(query.getString(1));
                String string = query.getString(2);
                if (query.isNull(3)) {
                    blob = query.getBlob(4);
                } else {
                    List arrayList2 = new ArrayList();
                    do {
                        arrayList2.add(query.getBlob(4));
                        if (!query.moveToNext()) {
                            break;
                        }
                    } while (query.getLong(0) == j);
                    query.moveToPrevious();
                    blob = zzQ(arrayList2);
                }
                Object zzgW = yr.zzgW(new String(blob, zzayt));
                if ("o".equals(string)) {
                    tmVar = new tm(j, qrVar, xp.zza(zzgW, xd.zzJb()), true);
                } else if ("m".equals(string)) {
                    tmVar = new tm(j, qrVar, pz.zzD((Map) zzgW));
                } else {
                    String str = "Got invalid write type: ";
                    String valueOf = String.valueOf(string);
                    throw new IllegalStateException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
                }
                arrayList.add(tmVar);
            } catch (Throwable e) {
                throw new RuntimeException("Failed to load writes", e);
            } catch (Throwable th) {
                query.close();
            }
        }
        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
        if (this.zzbZE.zzIH()) {
            this.zzbZE.zzb(String.format("Loaded %d writes in %dms", new Object[]{Integer.valueOf(arrayList.size()), Long.valueOf(currentTimeMillis2)}), null, new Object[0]);
        }
        query.close();
        return arrayList;
    }

    public final long zzFt() {
        long j = null;
        Cursor rawQuery = this.zzbZD.rawQuery(String.format("SELECT sum(length(%s) + length(%s)) FROM %s", new Object[]{Param.VALUE, "path", "serverCache"}), null);
        try {
            if (rawQuery.moveToFirst()) {
                j = rawQuery.getLong(0);
                return j;
            }
            throw new IllegalStateException("Couldn't read database result!");
        } finally {
            rawQuery.close();
        }
    }

    public final List<un> zzFu() {
        String[] strArr = new String[]{"id", "path", "queryParams", "lastUse", "complete", "active"};
        long currentTimeMillis = System.currentTimeMillis();
        Cursor query = this.zzbZD.query("trackedQueries", strArr, null, null, null, null, "id");
        List<un> arrayList = new ArrayList();
        while (query.moveToNext()) {
            try {
                arrayList.add(new un(query.getLong(0), new vt(new qr(query.getString(1)), vq.zzF(yr.zzgV(query.getString(2)))), query.getLong(3), query.getInt(4) != 0, query.getInt(5) != 0));
            } catch (Throwable e) {
                throw new RuntimeException(e);
            } catch (Throwable th) {
                query.close();
            }
        }
        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
        if (this.zzbZE.zzIH()) {
            this.zzbZE.zzb(String.format("Loaded %d tracked queries in %dms", new Object[]{Integer.valueOf(arrayList.size()), Long.valueOf(currentTimeMillis2)}), null, new Object[0]);
        }
        query.close();
        return arrayList;
    }

    public final void zzFv() {
        zzFw();
        long currentTimeMillis = System.currentTimeMillis();
        int delete = this.zzbZD.delete("writes", null, null);
        currentTimeMillis = System.currentTimeMillis() - currentTimeMillis;
        if (this.zzbZE.zzIH()) {
            this.zzbZE.zzb(String.format("Deleted %d (all) write(s) in %dms", new Object[]{Integer.valueOf(delete), Long.valueOf(currentTimeMillis)}), null, new Object[0]);
        }
    }

    public final xm zza(qr qrVar) {
        return zzb(qrVar);
    }

    public final void zza(long j, Set<wp> set) {
        zzFw();
        long currentTimeMillis = System.currentTimeMillis();
        String valueOf = String.valueOf(j);
        this.zzbZD.delete("trackedKeys", "id = ?", new String[]{valueOf});
        for (wp wpVar : set) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("id", Long.valueOf(j));
            contentValues.put("key", wpVar.asString());
            this.zzbZD.insertWithOnConflict("trackedKeys", null, contentValues, 5);
        }
        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
        if (this.zzbZE.zzIH()) {
            this.zzbZE.zzb(String.format("Set %d tracked query keys for tracked query %d in %dms", new Object[]{Integer.valueOf(set.size()), Long.valueOf(j), Long.valueOf(currentTimeMillis2)}), null, new Object[0]);
        }
    }

    public final void zza(long j, Set<wp> set, Set<wp> set2) {
        zzFw();
        long currentTimeMillis = System.currentTimeMillis();
        String str = "id = ? AND key = ?";
        String valueOf = String.valueOf(j);
        for (wp wpVar : set2) {
            this.zzbZD.delete("trackedKeys", str, new String[]{valueOf, wpVar.asString()});
        }
        for (wp wpVar2 : set) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("id", Long.valueOf(j));
            contentValues.put("key", wpVar2.asString());
            this.zzbZD.insertWithOnConflict("trackedKeys", null, contentValues, 5);
        }
        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
        if (this.zzbZE.zzIH()) {
            this.zzbZE.zzb(String.format("Updated tracked query keys (%d added, %d removed) for tracked query id %d in %dms", new Object[]{Integer.valueOf(set.size()), Integer.valueOf(set2.size()), Long.valueOf(j), Long.valueOf(currentTimeMillis2)}), null, new Object[0]);
        }
    }

    public final void zza(qr qrVar, pz pzVar) {
        zzFw();
        long currentTimeMillis = System.currentTimeMillis();
        Iterator it = pzVar.iterator();
        int i = 0;
        int i2 = 0;
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            i += zza("serverCache", qrVar.zzh((qr) entry.getKey()));
            i2 = zzc(qrVar.zzh((qr) entry.getKey()), (xm) entry.getValue()) + i2;
        }
        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
        if (this.zzbZE.zzIH()) {
            this.zzbZE.zzb(String.format("Persisted a total of %d rows and deleted %d rows for a merge at %s in %dms", new Object[]{Integer.valueOf(i2), Integer.valueOf(i), qrVar.toString(), Long.valueOf(currentTimeMillis2)}), null, new Object[0]);
        }
    }

    public final void zza(qr qrVar, pz pzVar, long j) {
        zzFw();
        long currentTimeMillis = System.currentTimeMillis();
        qr qrVar2 = qrVar;
        long j2 = j;
        zza(qrVar2, j2, "m", zzW(pzVar.zzaD(true)));
        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
        if (this.zzbZE.zzIH()) {
            this.zzbZE.zzb(String.format("Persisted user merge in %dms", new Object[]{Long.valueOf(currentTimeMillis2)}), null, new Object[0]);
        }
    }

    public final void zza(qr qrVar, uj ujVar) {
        if (ujVar.zzHN()) {
            zzFw();
            long currentTimeMillis = System.currentTimeMillis();
            Cursor zza = zza(qrVar, new String[]{"rowid", "path"});
            uv uvVar = new uv(null);
            uv uvVar2 = new uv(null);
            while (zza.moveToNext()) {
                long j = zza.getLong(0);
                qr qrVar2 = new qr(zza.getString(1));
                wl wlVar;
                String valueOf;
                String valueOf2;
                if (qrVar.zzi(qrVar2)) {
                    qr zza2 = qr.zza(qrVar, qrVar2);
                    if (ujVar.zzv(zza2)) {
                        uvVar = uvVar.zzb(zza2, Long.valueOf(j));
                    } else if (ujVar.zzw(zza2)) {
                        uvVar2 = uvVar2.zzb(zza2, Long.valueOf(j));
                    } else {
                        wlVar = this.zzbZE;
                        valueOf = String.valueOf(qrVar);
                        valueOf2 = String.valueOf(qrVar2);
                        wlVar.zze(new StringBuilder((String.valueOf(valueOf).length() + 88) + String.valueOf(valueOf2).length()).append("We are pruning at ").append(valueOf).append(" and have data at ").append(valueOf2).append(" that isn't marked for pruning or keeping. Ignoring.").toString(), null);
                    }
                } else {
                    wlVar = this.zzbZE;
                    valueOf = String.valueOf(qrVar);
                    valueOf2 = String.valueOf(qrVar2);
                    wlVar.zze(new StringBuilder((String.valueOf(valueOf).length() + 67) + String.valueOf(valueOf2).length()).append("We are pruning at ").append(valueOf).append(" but we have data stored higher up at ").append(valueOf2).append(". Ignoring.").toString(), null);
                }
            }
            int i = 0;
            int i2 = 0;
            if (!uvVar.isEmpty()) {
                List arrayList = new ArrayList();
                zza(qrVar, qr.zzGZ(), uvVar, uvVar2, ujVar, arrayList);
                Collection values = uvVar.values();
                String valueOf3 = String.valueOf(zzj(values));
                this.zzbZD.delete("serverCache", new StringBuilder(String.valueOf(valueOf3).length() + 11).append("rowid IN (").append(valueOf3).append(")").toString(), null);
                ArrayList arrayList2 = (ArrayList) arrayList;
                int size = arrayList2.size();
                int i3 = 0;
                while (i3 < size) {
                    int i4 = i3 + 1;
                    za zaVar = (za) arrayList2.get(i3);
                    zzc(qrVar.zzh((qr) zaVar.getFirst()), (xm) zaVar.zzJG());
                    i3 = i4;
                }
                i = values.size();
                i2 = arrayList.size();
            }
            long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
            if (this.zzbZE.zzIH()) {
                this.zzbZE.zzb(String.format("Pruned %d rows with %d nodes resaved in %dms", new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Long.valueOf(currentTimeMillis2)}), null, new Object[0]);
            }
        }
    }

    public final void zza(qr qrVar, xm xmVar) {
        zzFw();
        zza(qrVar, xmVar, false);
    }

    public final void zza(qr qrVar, xm xmVar, long j) {
        zzFw();
        long currentTimeMillis = System.currentTimeMillis();
        qr qrVar2 = qrVar;
        long j2 = j;
        zza(qrVar2, j2, "o", zzW(xmVar.getValue(true)));
        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
        if (this.zzbZE.zzIH()) {
            this.zzbZE.zzb(String.format("Persisted user overwrite in %dms", new Object[]{Long.valueOf(currentTimeMillis2)}), null, new Object[0]);
        }
    }

    public final void zza(un unVar) {
        zzFw();
        long currentTimeMillis = System.currentTimeMillis();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", Long.valueOf(unVar.id));
        contentValues.put("path", zzc(unVar.zzcgi.zzFq()));
        contentValues.put("queryParams", unVar.zzcgi.zzIu().zzIr());
        contentValues.put("lastUse", Long.valueOf(unVar.zzcgj));
        contentValues.put("complete", Boolean.valueOf(unVar.complete));
        contentValues.put("active", Boolean.valueOf(unVar.zzbpf));
        this.zzbZD.insertWithOnConflict("trackedQueries", null, contentValues, 5);
        currentTimeMillis = System.currentTimeMillis() - currentTimeMillis;
        if (this.zzbZE.zzIH()) {
            this.zzbZE.zzb(String.format("Saved new tracked query in %dms", new Object[]{Long.valueOf(currentTimeMillis)}), null, new Object[0]);
        }
    }

    public final void zzal(long j) {
        zzFw();
        long currentTimeMillis = System.currentTimeMillis();
        int delete = this.zzbZD.delete("writes", "id = ?", new String[]{String.valueOf(j)});
        currentTimeMillis = System.currentTimeMillis() - currentTimeMillis;
        if (this.zzbZE.zzIH()) {
            this.zzbZE.zzb(String.format("Deleted %d write(s) with writeId %d in %dms", new Object[]{Integer.valueOf(delete), Long.valueOf(j), Long.valueOf(currentTimeMillis)}), null, new Object[0]);
        }
    }

    public final void zzam(long j) {
        zzFw();
        String valueOf = String.valueOf(j);
        this.zzbZD.delete("trackedQueries", "id = ?", new String[]{valueOf});
        this.zzbZD.delete("trackedKeys", "id = ?", new String[]{valueOf});
    }

    public final void zzan(long j) {
        zzFw();
        long currentTimeMillis = System.currentTimeMillis();
        ContentValues contentValues = new ContentValues();
        contentValues.put("active", Boolean.valueOf(false));
        contentValues.put("lastUse", Long.valueOf(j));
        this.zzbZD.updateWithOnConflict("trackedQueries", contentValues, "active = 1", new String[0], 5);
        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
        if (this.zzbZE.zzIH()) {
            this.zzbZE.zzb(String.format("Reset active tracked queries in %dms", new Object[]{Long.valueOf(currentTimeMillis2)}), null, new Object[0]);
        }
    }

    public final Set<wp> zzao(long j) {
        return zze(Collections.singleton(Long.valueOf(j)));
    }

    public final void zzb(qr qrVar, xm xmVar) {
        zzFw();
        zza(qrVar, xmVar, true);
    }

    public final Set<wp> zze(Set<Long> set) {
        String[] strArr = new String[]{"key"};
        long currentTimeMillis = System.currentTimeMillis();
        String valueOf = String.valueOf("id IN (");
        String valueOf2 = String.valueOf(zzj(set));
        Cursor query = this.zzbZD.query(true, "trackedKeys", strArr, new StringBuilder((String.valueOf(valueOf).length() + 1) + String.valueOf(valueOf2).length()).append(valueOf).append(valueOf2).append(")").toString(), null, null, null, null, null);
        Set<wp> hashSet = new HashSet();
        while (query.moveToNext()) {
            try {
                hashSet.add(wp.zzgT(query.getString(0)));
            } catch (Throwable th) {
                query.close();
            }
        }
        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
        if (this.zzbZE.zzIH()) {
            this.zzbZE.zzb(String.format("Loaded %d tracked queries keys for tracked queries %s in %dms", new Object[]{Integer.valueOf(hashSet.size()), set.toString(), Long.valueOf(currentTimeMillis2)}), null, new Object[0]);
        }
        query.close();
        return hashSet;
    }
}
