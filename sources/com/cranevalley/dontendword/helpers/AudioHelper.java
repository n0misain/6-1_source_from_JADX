package com.cranevalley.dontendword.helpers;

import android.content.Context;
import android.media.MediaPlayer;
import com.cranevalley.dontendword.C0521R;

public final class AudioHelper {
    private static MediaPlayer sClickMediaPlayer;
    private static MediaPlayer sLoseMediaPlayer;
    private static MediaPlayer sMusicMediaPlayer;
    private static MediaPlayer sWinMediaPlayer;

    public static void initialize(Context context) {
        Context applicationContext = context.getApplicationContext();
        sClickMediaPlayer = MediaPlayer.create(applicationContext, C0521R.raw.effect_click);
        sClickMediaPlayer.setLooping(false);
        sWinMediaPlayer = MediaPlayer.create(applicationContext, C0521R.raw.effect_win);
        sWinMediaPlayer.setLooping(false);
        sLoseMediaPlayer = MediaPlayer.create(applicationContext, C0521R.raw.effect_lose);
        sLoseMediaPlayer.setLooping(false);
        sMusicMediaPlayer = MediaPlayer.create(applicationContext, C0521R.raw.music_background);
        sMusicMediaPlayer.setLooping(true);
    }

    public static void release() {
        if (sClickMediaPlayer != null) {
            sClickMediaPlayer.release();
            sClickMediaPlayer = null;
        }
        if (sWinMediaPlayer != null) {
            sWinMediaPlayer.release();
            sWinMediaPlayer = null;
        }
        if (sLoseMediaPlayer != null) {
            sLoseMediaPlayer.release();
            sLoseMediaPlayer = null;
        }
        if (sMusicMediaPlayer != null) {
            sMusicMediaPlayer.release();
            sMusicMediaPlayer = null;
        }
    }

    public static void setEffectsVolume(float volume) {
        sClickMediaPlayer.setVolume(volume, volume);
        sWinMediaPlayer.setVolume(volume, volume);
        sLoseMediaPlayer.setVolume(volume, volume);
    }

    public static void setMusicVolume(float volume) {
        sMusicMediaPlayer.setVolume(volume, volume);
    }

    public static void pauseMusic() {
        sMusicMediaPlayer.pause();
    }

    public static void resumeMusic() {
        sMusicMediaPlayer.start();
    }

    public static void playClickEffect() {
        if (sClickMediaPlayer.isPlaying()) {
            sClickMediaPlayer.seekTo(0);
        } else {
            sClickMediaPlayer.start();
        }
    }

    public static void playWinEffect() {
        if (sWinMediaPlayer.isPlaying()) {
            sWinMediaPlayer.seekTo(0);
        } else {
            sWinMediaPlayer.start();
        }
    }

    public static void playLoseEffect() {
        if (sLoseMediaPlayer.isPlaying()) {
            sLoseMediaPlayer.seekTo(0);
        } else {
            sLoseMediaPlayer.start();
        }
    }

    public static void playBackgroundMusic() {
        if (!sMusicMediaPlayer.isPlaying()) {
            sMusicMediaPlayer.start();
        }
    }

    private AudioHelper() {
    }
}
