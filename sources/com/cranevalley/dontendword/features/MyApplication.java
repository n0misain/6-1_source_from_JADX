package com.cranevalley.dontendword.features;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import com.cranevalley.dontendword.helpers.AudioHelper;
import com.cranevalley.dontendword.helpers.PreferencesHelper;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

public class MyApplication extends Application {
    private RefWatcher mRefWatcher;

    public static RefWatcher getRefWatcher(Context context) {
        return ((MyApplication) context.getApplicationContext()).mRefWatcher;
    }

    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public void onCreate() {
        float f = 1.0f;
        super.onCreate();
        if (!LeakCanary.isInAnalyzerProcess(this)) {
            float f2;
            this.mRefWatcher = LeakCanary.install(this);
            Logger.addLogAdapter(new AndroidLogAdapter());
            PreferencesHelper.initialize(this);
            AudioHelper.initialize(this);
            if (PreferencesHelper.isEffectsEnabled()) {
                f2 = 1.0f;
            } else {
                f2 = 0.0f;
            }
            AudioHelper.setEffectsVolume(f2);
            if (!PreferencesHelper.isMusicEnabled()) {
                f = 0.0f;
            }
            AudioHelper.setMusicVolume(f);
        }
    }
}
