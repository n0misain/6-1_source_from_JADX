package com.cranevalley.dontendword.helpers;

import android.content.Context;
import android.content.SharedPreferences;

public final class PreferencesHelper {
    private static final String EFFECTS_ENABLED_KEY = "EFFECTS_ENABLED_KEY";
    private static final String INTRO_SEEN_KEY = "INTRO_SEEN_KEY";
    private static final String MUSIC_ENABLED_KEY = "MUSIC_ENABLED_KEY";
    private static final String NOTIFICATIONS_ENABLED_KEY = "NOTIFICATIONS_ENABLED_KEY";
    private static final String PREFERENCES_NAME = "PREFERENCES_NAME";
    private static SharedPreferences sSharedPreferences;

    public static void initialize(Context context) {
        sSharedPreferences = context.getApplicationContext().getSharedPreferences(PREFERENCES_NAME, 0);
    }

    public static boolean isEffectsEnabled() {
        return sSharedPreferences.getBoolean(EFFECTS_ENABLED_KEY, true);
    }

    public static void setEffectsEnabled(boolean effectsEnabled) {
        sSharedPreferences.edit().putBoolean(EFFECTS_ENABLED_KEY, effectsEnabled).apply();
    }

    public static boolean isMusicEnabled() {
        return sSharedPreferences.getBoolean(MUSIC_ENABLED_KEY, true);
    }

    public static void setMusicEnabled(boolean musicEnabled) {
        sSharedPreferences.edit().putBoolean(MUSIC_ENABLED_KEY, musicEnabled).apply();
    }

    public static boolean isNotificationsEnabled() {
        return sSharedPreferences.getBoolean(NOTIFICATIONS_ENABLED_KEY, true);
    }

    public static void setNotificationsEnabled(boolean notificationsEnabled) {
        sSharedPreferences.edit().putBoolean(NOTIFICATIONS_ENABLED_KEY, notificationsEnabled).apply();
    }

    public static boolean isIntroSeen() {
        return sSharedPreferences.getBoolean(INTRO_SEEN_KEY, false);
    }

    public static void setIntroSeen(boolean introSeen) {
        sSharedPreferences.edit().putBoolean(INTRO_SEEN_KEY, introSeen).apply();
    }

    private PreferencesHelper() {
    }
}
