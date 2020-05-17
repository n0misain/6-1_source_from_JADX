package com.google.android.gms.internal;

import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.HashMap;

public abstract class zzbs<K, V> {
    private static final String TAG = zzbs.class.getSimpleName();

    protected static <K, V> HashMap<K, V> zzj(String str) {
        try {
            if (!TextUtils.isEmpty(str)) {
                return (HashMap) new ObjectInputStream(new ByteArrayInputStream(Base64.decode(str.getBytes(), 0))).readObject();
            }
        } catch (IOException e) {
            Log.d(TAG, "decode object failure");
            return null;
        } catch (ClassNotFoundException e2) {
            Log.d(TAG, "decode object failure");
            return null;
        }
        return null;
    }

    public String toString() {
        try {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(zzv());
            objectOutputStream.close();
            return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
        } catch (IOException e) {
            return null;
        }
    }

    protected abstract void zzi(String str);

    protected abstract HashMap<K, V> zzv();
}
