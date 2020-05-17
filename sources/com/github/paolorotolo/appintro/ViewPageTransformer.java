package com.github.paolorotolo.appintro;

import android.annotation.SuppressLint;
import android.support.v4.view.ViewPager.PageTransformer;
import android.view.View;

class ViewPageTransformer implements PageTransformer {
    private static final float MIN_ALPHA_SLIDE = 0.35f;
    private static final float MIN_ALPHA_ZOOM = 0.5f;
    private static final float MIN_SCALE_DEPTH = 0.75f;
    private static final float MIN_SCALE_ZOOM = 0.85f;
    private static final float SCALE_FACTOR_SLIDE = 0.85f;
    private final TransformType mTransformType;

    enum TransformType {
        FLOW,
        DEPTH,
        ZOOM,
        SLIDE_OVER,
        FADE
    }

    ViewPageTransformer(TransformType transformType) {
        this.mTransformType = transformType;
    }

    @SuppressLint({"NewApi"})
    public void transformPage(View page, float position) {
        float scale;
        float alpha;
        float translationX;
        switch (this.mTransformType) {
            case FLOW:
                page.setRotationY(-30.0f * position);
                return;
            case SLIDE_OVER:
                if (position < 0.0f && position > -1.0f) {
                    scale = (Math.abs(Math.abs(position) - 1.0f) * 0.14999998f) + 0.85f;
                    alpha = Math.max(MIN_ALPHA_SLIDE, 1.0f - Math.abs(position));
                    int pageWidth = page.getWidth();
                    float translateValue = position * ((float) (-pageWidth));
                    if (translateValue <= ((float) (-pageWidth))) {
                        translationX = 0.0f;
                        break;
                    } else {
                        translationX = translateValue;
                        break;
                    }
                }
                alpha = 1.0f;
                scale = 1.0f;
                translationX = 0.0f;
                break;
                break;
            case DEPTH:
                if (position > 0.0f && position < 1.0f) {
                    alpha = 1.0f - position;
                    scale = MIN_SCALE_DEPTH + (0.25f * (1.0f - Math.abs(position)));
                    translationX = ((float) page.getWidth()) * (-position);
                    break;
                }
                alpha = 1.0f;
                scale = 1.0f;
                translationX = 0.0f;
                break;
                break;
            case ZOOM:
                if (position >= -1.0f && position <= 1.0f) {
                    scale = Math.max(0.85f, 1.0f - Math.abs(position));
                    alpha = MIN_ALPHA_ZOOM + (((scale - 0.85f) / 0.14999998f) * MIN_ALPHA_ZOOM);
                    float vMargin = (((float) page.getHeight()) * (1.0f - scale)) / 2.0f;
                    float hMargin = (((float) page.getWidth()) * (1.0f - scale)) / 2.0f;
                    if (position >= 0.0f) {
                        translationX = (-hMargin) + (vMargin / 2.0f);
                        break;
                    } else {
                        translationX = hMargin - (vMargin / 2.0f);
                        break;
                    }
                }
                alpha = 1.0f;
                scale = 1.0f;
                translationX = 0.0f;
                break;
                break;
            case FADE:
                if (position <= -1.0f || position >= 1.0f) {
                    page.setAlpha(0.0f);
                    page.setClickable(false);
                    return;
                } else if (position == 0.0f) {
                    page.setAlpha(1.0f);
                    page.setClickable(true);
                    return;
                } else {
                    page.setAlpha(1.0f - Math.abs(position));
                    return;
                }
            default:
                return;
        }
        page.setAlpha(alpha);
        page.setTranslationX(translationX);
        page.setScaleX(scale);
        page.setScaleY(scale);
    }
}
