package com.cranevalley.dontendword.helpers;

import android.content.res.Resources;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import java.util.Random;

public class Utility {
    private static Random random = new Random();

    public static int getRandomInt(int min, int max) {
        if (min > max) {
            int temp = min;
            min = max;
            max = temp;
        }
        return random.nextInt((max + 1) - min) + min;
    }

    public static float getRandomFloat(float min, float max) {
        if (min > max) {
            float temp = min;
            min = max;
            max = temp;
        }
        return (random.nextFloat() * (max - min)) + min;
    }

    public static String getRandomString(String allowedChars, int length) {
        StringBuilder stringBuilder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            stringBuilder.append(allowedChars.charAt(random.nextInt(allowedChars.length())));
        }
        return stringBuilder.toString();
    }

    public static float getDpFromPx(float px) {
        return px / Resources.getSystem().getDisplayMetrics().density;
    }

    public static float getPxFromDp(float dp) {
        return Resources.getSystem().getDisplayMetrics().density * dp;
    }

    public static void hideKeyboard(View view) {
        ((InputMethodManager) view.getContext().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
