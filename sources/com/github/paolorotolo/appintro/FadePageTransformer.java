package com.github.paolorotolo.appintro;

import android.support.v4.view.ViewPager.PageTransformer;
import android.view.View;

class FadePageTransformer implements PageTransformer {
    FadePageTransformer() {
    }

    public void transformPage(View view, float position) {
        view.setTranslationX(((float) view.getWidth()) * (-position));
        if (position <= -1.0f || position >= 1.0f) {
            view.setAlpha(0.0f);
            view.setClickable(false);
        } else if (position == 0.0f) {
            view.setAlpha(1.0f);
            view.setClickable(true);
        } else {
            view.setAlpha(1.0f - Math.abs(position));
        }
    }
}
