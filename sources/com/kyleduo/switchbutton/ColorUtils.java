package com.kyleduo.switchbutton;

import android.content.res.ColorStateList;

public class ColorUtils {
    private static final int CHECKED_ATTR = 16842912;
    private static final int ENABLE_ATTR = 16842910;
    private static final int PRESSED_ATTR = 16842919;

    public static ColorStateList generateThumbColorWithTintColor(int tintColor) {
        states = new int[6][];
        states[1] = new int[]{-16842910};
        states[2] = new int[]{PRESSED_ATTR, -16842912};
        states[3] = new int[]{PRESSED_ATTR, CHECKED_ATTR};
        states[4] = new int[]{CHECKED_ATTR};
        states[5] = new int[]{-16842912};
        return new ColorStateList(states, new int[]{tintColor - -1442840576, -4539718, tintColor - -1728053248, tintColor - -1728053248, -16777216 | tintColor, -1118482});
    }

    public static ColorStateList generateBackColorWithTintColor(int tintColor) {
        states = new int[6][];
        states[1] = new int[]{-16842910};
        states[2] = new int[]{CHECKED_ATTR, PRESSED_ATTR};
        states[3] = new int[]{-16842912, PRESSED_ATTR};
        states[4] = new int[]{CHECKED_ATTR};
        states[5] = new int[]{-16842912};
        return new ColorStateList(states, new int[]{tintColor - -520093696, 268435456, tintColor - -805306368, 536870912, tintColor - -805306368, 536870912});
    }
}
