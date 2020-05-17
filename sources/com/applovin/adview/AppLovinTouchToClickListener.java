package com.applovin.adview;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;

public class AppLovinTouchToClickListener implements OnTouchListener {
    /* renamed from: a */
    private long f50a;
    /* renamed from: b */
    private float f51b;
    /* renamed from: c */
    private float f52c;
    /* renamed from: d */
    private Context f53d;
    /* renamed from: e */
    private OnClickListener f54e;

    public AppLovinTouchToClickListener(Context context, OnClickListener onClickListener) {
        this.f53d = context;
        this.f54e = onClickListener;
    }

    /* renamed from: a */
    private float m116a(float f) {
        return f / this.f53d.getResources().getDisplayMetrics().density;
    }

    /* renamed from: a */
    private float m117a(float f, float f2, float f3, float f4) {
        float f5 = f - f3;
        float f6 = f2 - f4;
        return m116a((float) Math.sqrt((double) ((f5 * f5) + (f6 * f6))));
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                this.f50a = System.currentTimeMillis();
                this.f51b = motionEvent.getX();
                this.f52c = motionEvent.getY();
                break;
            case 1:
                if (System.currentTimeMillis() - this.f50a < 1000 && m117a(this.f51b, this.f52c, motionEvent.getX(), motionEvent.getY()) < 10.0f) {
                    this.f54e.onClick(view);
                    break;
                }
        }
        return true;
    }
}
