package com.twitter.sdk.android.tweetui;

import android.graphics.Color;

final class ColorUtils {
    private ColorUtils() {
    }

    static int calculateOpacityTransform(double opacity, int overlayColor, int primaryColor) {
        return Color.rgb((int) (((1.0d - opacity) * ((double) Color.red(primaryColor))) + (((double) Color.red(overlayColor)) * opacity)), (int) (((1.0d - opacity) * ((double) Color.green(primaryColor))) + (((double) Color.green(overlayColor)) * opacity)), (int) (((1.0d - opacity) * ((double) Color.blue(primaryColor))) + (((double) Color.blue(overlayColor)) * opacity)));
    }

    static boolean isLightColor(int color) {
        return ((0.21d * ((double) Color.red(color))) + (0.72d * ((double) Color.green(color)))) + (0.07d * ((double) Color.blue(color))) > 128.0d;
    }
}
