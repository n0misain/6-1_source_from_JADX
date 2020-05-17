package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteFullException;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.common.util.zze;
import java.util.ArrayList;
import java.util.List;

public final class zzcfh extends zzchj {
    private final zzcfi zzbqF = new zzcfi(this, super.getContext(), zzcem.zzxD());
    private boolean zzbqG;

    zzcfh(zzcgl zzcgl) {
        super(zzcgl);
    }

    @WorkerThread
    private final SQLiteDatabase getWritableDatabase() {
        if (this.zzbqG) {
            return null;
        }
        SQLiteDatabase writableDatabase = this.zzbqF.getWritableDatabase();
        if (writableDatabase != null) {
            return writableDatabase;
        }
        this.zzbqG = true;
        return null;
    }

    @WorkerThread
    @TargetApi(11)
    private final boolean zza(int i, byte[] bArr) {
        super.zzwp();
        super.zzjC();
        if (this.zzbqG) {
            return false;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("type", Integer.valueOf(i));
        contentValues.put("entry", bArr);
        zzcem.zzxN();
        int i2 = 0;
        int i3 = 5;
        while (i2 < 5) {
            SQLiteDatabase sQLiteDatabase = null;
            Cursor cursor = null;
            try {
                sQLiteDatabase = getWritableDatabase();
                if (sQLiteDatabase == null) {
                    this.zzbqG = true;
                    if (sQLiteDatabase != null) {
                        sQLiteDatabase.close();
                    }
                    return false;
                }
                sQLiteDatabase.beginTransaction();
                long j = 0;
                cursor = sQLiteDatabase.rawQuery("select count(1) from messages", null);
                if (cursor != null && cursor.moveToFirst()) {
                    j = cursor.getLong(0);
                }
                if (j >= 100000) {
                    super.zzwF().zzyx().log("Data loss, local db full");
                    j = (100000 - j) + 1;
                    long delete = (long) sQLiteDatabase.delete("messages", "rowid in (select rowid from messages order by rowid asc limit ?)", new String[]{Long.toString(j)});
                    if (delete != j) {
                        super.zzwF().zzyx().zzd("Different delete count than expected in local db. expected, received, difference", Long.valueOf(j), Long.valueOf(delete), Long.valueOf(j - delete));
                    }
                }
                sQLiteDatabase.insertOrThrow("messages", null, contentValues);
                sQLiteDatabase.setTransactionSuccessful();
                sQLiteDatabase.endTransaction();
                if (cursor != null) {
                    cursor.close();
                }
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.close();
                }
                return true;
            } catch (SQLiteFullException e) {
                super.zzwF().zzyx().zzj("Error writing entry to local database", e);
                this.zzbqG = true;
                if (cursor != null) {
                    cursor.close();
                }
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.close();
                }
                i2++;
            } catch (SQLiteException e2) {
                if (VERSION.SDK_INT < 11 || !(e2 instanceof SQLiteDatabaseLockedException)) {
                    if (sQLiteDatabase != null) {
                        if (sQLiteDatabase.inTransaction()) {
                            sQLiteDatabase.endTransaction();
                        }
                    }
                    super.zzwF().zzyx().zzj("Error writing entry to local database", e2);
                    this.zzbqG = true;
                } else {
                    SystemClock.sleep((long) i3);
                    i3 += 20;
                }
                if (cursor != null) {
                    cursor.close();
                }
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.close();
                }
                i2++;
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.close();
                }
            }
        }
        super.zzwF().zzyz().log("Failed to write entry to local database");
        return false;
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    public final boolean zza(zzcez zzcez) {
        Parcel obtain = Parcel.obtain();
        zzcez.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        if (marshall.length <= 131072) {
            return zza(0, marshall);
        }
        super.zzwF().zzyz().log("Event is too long for local database. Sending event directly to service");
        return false;
    }

    public final boolean zza(zzcji zzcji) {
        Parcel obtain = Parcel.obtain();
        zzcji.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        if (marshall.length <= 131072) {
            return zza(1, marshall);
        }
        super.zzwF().zzyz().log("User property too long for local database. Sending directly to service");
        return false;
    }

    @TargetApi(11)
    public final List<zza> zzbp(int i) {
        SQLiteDatabase sQLiteDatabase;
        Cursor cursor;
        Cursor cursor2;
        SQLiteException sQLiteException;
        Throwable th;
        Object obj;
        int i2;
        Throwable th2;
        Parcel obtain;
        super.zzjC();
        super.zzwp();
        if (this.zzbqG) {
            return null;
        }
        List<zza> arrayList = new ArrayList();
        if (!super.getContext().getDatabasePath(zzcem.zzxD()).exists()) {
            return arrayList;
        }
        int i3 = 5;
        int i4 = 0;
        while (i4 < 5) {
            SQLiteDatabase sQLiteDatabase2 = null;
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                if (writableDatabase == null) {
                    try {
                        this.zzbqG = true;
                        if (writableDatabase != null) {
                            writableDatabase.close();
                        }
                        return null;
                    } catch (SQLiteFullException e) {
                        sQLiteDatabase = writableDatabase;
                        SQLiteFullException sQLiteFullException = e;
                        cursor = null;
                    } catch (SQLiteException e2) {
                        cursor2 = null;
                        sQLiteException = e2;
                        sQLiteDatabase2 = writableDatabase;
                        SQLiteException sQLiteException2 = sQLiteException;
                        if (VERSION.SDK_INT >= 11 || !(obj instanceof SQLiteDatabaseLockedException)) {
                            if (sQLiteDatabase2 != null) {
                                try {
                                    if (sQLiteDatabase2.inTransaction()) {
                                        sQLiteDatabase2.endTransaction();
                                    }
                                } catch (Throwable th3) {
                                    th = th3;
                                }
                            }
                            super.zzwF().zzyx().zzj("Error reading entries from local database", obj);
                            this.zzbqG = true;
                            i2 = i3;
                        } else {
                            SystemClock.sleep((long) i3);
                            i2 = i3 + 20;
                        }
                        if (cursor2 != null) {
                            cursor2.close();
                        }
                        if (sQLiteDatabase2 != null) {
                            sQLiteDatabase2.close();
                        }
                        i4++;
                        i3 = i2;
                    } catch (Throwable th4) {
                        cursor2 = null;
                        th2 = th4;
                        sQLiteDatabase2 = writableDatabase;
                        th = th2;
                    }
                } else {
                    writableDatabase.beginTransaction();
                    cursor2 = writableDatabase.query("messages", new String[]{"rowid", "type", "entry"}, null, null, null, null, "rowid asc", Integer.toString(100));
                    long j = -1;
                    while (cursor2.moveToNext()) {
                        try {
                            j = cursor2.getLong(0);
                            int i5 = cursor2.getInt(1);
                            byte[] blob = cursor2.getBlob(2);
                            if (i5 == 0) {
                                Parcel obtain2 = Parcel.obtain();
                                try {
                                    obtain2.unmarshall(blob, 0, blob.length);
                                    obtain2.setDataPosition(0);
                                    zzcez zzcez = (zzcez) zzcez.CREATOR.createFromParcel(obtain2);
                                    obtain2.recycle();
                                    if (zzcez != null) {
                                        arrayList.add(zzcez);
                                    }
                                } catch (zzc e3) {
                                    super.zzwF().zzyx().log("Failed to load event from local database");
                                    obtain2.recycle();
                                } catch (Throwable th42) {
                                    obtain2.recycle();
                                    throw th42;
                                }
                            } else if (i5 == 1) {
                                obtain = Parcel.obtain();
                                try {
                                    obtain.unmarshall(blob, 0, blob.length);
                                    obtain.setDataPosition(0);
                                    r1 = (zzcji) zzcji.CREATOR.createFromParcel(obtain);
                                    obtain.recycle();
                                } catch (zzc e4) {
                                    super.zzwF().zzyx().log("Failed to load user property from local database");
                                    obtain.recycle();
                                    r1 = null;
                                } catch (Throwable th422) {
                                    obtain.recycle();
                                    throw th422;
                                }
                                if (r1 != null) {
                                    arrayList.add(r1);
                                }
                            } else if (i5 == 2) {
                                obtain = Parcel.obtain();
                                try {
                                    obtain.unmarshall(blob, 0, blob.length);
                                    obtain.setDataPosition(0);
                                    r1 = (zzcek) zzcek.CREATOR.createFromParcel(obtain);
                                    obtain.recycle();
                                } catch (zzc e5) {
                                    super.zzwF().zzyx().log("Failed to load user property from local database");
                                    obtain.recycle();
                                    r1 = null;
                                } catch (Throwable th4222) {
                                    obtain.recycle();
                                    throw th4222;
                                }
                                if (r1 != null) {
                                    arrayList.add(r1);
                                }
                            } else {
                                super.zzwF().zzyx().log("Unknown record type in local database");
                            }
                        } catch (SQLiteFullException e6) {
                            SQLiteFullException sQLiteFullException2 = e6;
                            cursor = cursor2;
                            sQLiteDatabase = writableDatabase;
                            obj = sQLiteFullException2;
                        } catch (SQLiteException e22) {
                            sQLiteException = e22;
                            sQLiteDatabase2 = writableDatabase;
                            obj = sQLiteException;
                        } catch (Throwable th42222) {
                            th2 = th42222;
                            sQLiteDatabase2 = writableDatabase;
                            th = th2;
                        }
                    }
                    if (writableDatabase.delete("messages", "rowid <= ?", new String[]{Long.toString(j)}) < arrayList.size()) {
                        super.zzwF().zzyx().log("Fewer entries removed from local database than expected");
                    }
                    writableDatabase.setTransactionSuccessful();
                    writableDatabase.endTransaction();
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    if (writableDatabase != null) {
                        writableDatabase.close();
                    }
                    return arrayList;
                }
            } catch (SQLiteFullException e7) {
                obj = e7;
                sQLiteDatabase = null;
                cursor = null;
                try {
                    super.zzwF().zzyx().zzj("Error reading entries from local database", obj);
                    this.zzbqG = true;
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (sQLiteDatabase != null) {
                        sQLiteDatabase.close();
                        i2 = i3;
                    } else {
                        i2 = i3;
                    }
                    i4++;
                    i3 = i2;
                } catch (Throwable th5) {
                    th = th5;
                    Cursor cursor3 = cursor;
                    sQLiteDatabase2 = sQLiteDatabase;
                    cursor2 = cursor3;
                }
            } catch (SQLiteException e8) {
                obj = e8;
                cursor2 = null;
                if (VERSION.SDK_INT >= 11) {
                }
                if (sQLiteDatabase2 != null) {
                    if (sQLiteDatabase2.inTransaction()) {
                        sQLiteDatabase2.endTransaction();
                    }
                }
                super.zzwF().zzyx().zzj("Error reading entries from local database", obj);
                this.zzbqG = true;
                i2 = i3;
                if (cursor2 != null) {
                    cursor2.close();
                }
                if (sQLiteDatabase2 != null) {
                    sQLiteDatabase2.close();
                }
                i4++;
                i3 = i2;
            } catch (Throwable th6) {
                th = th6;
                cursor2 = null;
            }
        }
        super.zzwF().zzyz().log("Failed to read events from database in reasonable time");
        return null;
        if (cursor2 != null) {
            cursor2.close();
        }
        if (sQLiteDatabase2 != null) {
            sQLiteDatabase2.close();
        }
        throw th;
    }

    public final boolean zzc(zzcek zzcek) {
        super.zzwB();
        byte[] zza = zzcjl.zza((Parcelable) zzcek);
        if (zza.length <= 131072) {
            return zza(2, zza);
        }
        super.zzwF().zzyz().log("Conditional user property too long for local database. Sending directly to service");
        return false;
    }

    public final /* bridge */ /* synthetic */ void zzjC() {
        super.zzjC();
    }

    protected final void zzjD() {
    }

    public final /* bridge */ /* synthetic */ zze zzkq() {
        return super.zzkq();
    }

    public final /* bridge */ /* synthetic */ zzcfj zzwA() {
        return super.zzwA();
    }

    public final /* bridge */ /* synthetic */ zzcjl zzwB() {
        return super.zzwB();
    }

    public final /* bridge */ /* synthetic */ zzcgf zzwC() {
        return super.zzwC();
    }

    public final /* bridge */ /* synthetic */ zzcja zzwD() {
        return super.zzwD();
    }

    public final /* bridge */ /* synthetic */ zzcgg zzwE() {
        return super.zzwE();
    }

    public final /* bridge */ /* synthetic */ zzcfl zzwF() {
        return super.zzwF();
    }

    public final /* bridge */ /* synthetic */ zzcfw zzwG() {
        return super.zzwG();
    }

    public final /* bridge */ /* synthetic */ zzcem zzwH() {
        return super.zzwH();
    }

    public final /* bridge */ /* synthetic */ void zzwo() {
        super.zzwo();
    }

    public final /* bridge */ /* synthetic */ void zzwp() {
        super.zzwp();
    }

    public final /* bridge */ /* synthetic */ void zzwq() {
        super.zzwq();
    }

    public final /* bridge */ /* synthetic */ zzcec zzwr() {
        return super.zzwr();
    }

    public final /* bridge */ /* synthetic */ zzcej zzws() {
        return super.zzws();
    }

    public final /* bridge */ /* synthetic */ zzchl zzwt() {
        return super.zzwt();
    }

    public final /* bridge */ /* synthetic */ zzcfg zzwu() {
        return super.zzwu();
    }

    public final /* bridge */ /* synthetic */ zzcet zzwv() {
        return super.zzwv();
    }

    public final /* bridge */ /* synthetic */ zzcid zzww() {
        return super.zzww();
    }

    public final /* bridge */ /* synthetic */ zzchz zzwx() {
        return super.zzwx();
    }

    public final /* bridge */ /* synthetic */ zzcfh zzwy() {
        return super.zzwy();
    }

    public final /* bridge */ /* synthetic */ zzcen zzwz() {
        return super.zzwz();
    }
}
