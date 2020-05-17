package com.anjlab.android.iab.v3;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

class BillingBase {
    private Context context;

    BillingBase(Context context) {
        this.context = context;
    }

    Context getContext() {
        return this.context;
    }

    String getPreferencesBaseKey() {
        return getContext().getPackageName() + "_preferences";
    }

    private SharedPreferences getPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(getContext());
    }

    boolean saveString(String key, String value) {
        SharedPreferences sp = getPreferences();
        if (sp == null) {
            return false;
        }
        Editor spe = sp.edit();
        spe.putString(key, value);
        spe.commit();
        return true;
    }

    String loadString(String key, String defValue) {
        SharedPreferences sp = getPreferences();
        if (sp != null) {
            return sp.getString(key, defValue);
        }
        return defValue;
    }

    boolean saveBoolean(String key, Boolean value) {
        SharedPreferences sp = getPreferences();
        if (sp == null) {
            return false;
        }
        Editor spe = sp.edit();
        spe.putBoolean(key, value.booleanValue());
        spe.commit();
        return true;
    }

    boolean loadBoolean(String key, boolean defValue) {
        SharedPreferences sp = getPreferences();
        if (sp != null) {
            return sp.getBoolean(key, defValue);
        }
        return defValue;
    }
}
