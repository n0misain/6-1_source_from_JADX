package com.applovin.impl.adview;

import android.content.Context;
import android.widget.VideoView;

public class AppLovinVideoView extends VideoView {
    /* renamed from: a */
    private int f188a = 0;
    /* renamed from: b */
    private int f189b = 0;
    /* renamed from: c */
    private float f190c = 0.0f;

    public AppLovinVideoView(Context context) {
        super(context, null, 0);
    }

    protected void onMeasure(int i, int i2) {
        if (this.f188a <= 0 || this.f189b <= 0) {
            super.onMeasure(i, i2);
            return;
        }
        int defaultSize = getDefaultSize(this.f188a, i);
        int defaultSize2 = getDefaultSize(this.f189b, i2);
        int i3 = (int) (((float) defaultSize) / this.f190c);
        if (defaultSize2 <= i3) {
            i3 = defaultSize2;
        }
        defaultSize2 = (int) (((float) i3) * this.f190c);
        if (defaultSize <= defaultSize2) {
            defaultSize2 = defaultSize;
        }
        setMeasuredDimension(defaultSize2, i3);
    }

    public void setVideoSize(int i, int i2) {
        this.f188a = i;
        this.f189b = i2;
        this.f190c = ((float) this.f188a) / ((float) this.f189b);
        try {
            getHolder().setFixedSize(i, i2);
            requestLayout();
            invalidate();
        } catch (Exception e) {
        }
    }
}
