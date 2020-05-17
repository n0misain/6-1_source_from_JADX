package com.applovin.impl.adview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import com.applovin.sdk.AppLovinSdk;

public class cb extends C0471x {
    /* renamed from: c */
    private float f311c = 30.0f;
    /* renamed from: d */
    private float f312d = 2.0f;
    /* renamed from: e */
    private float f313e = 8.0f;
    /* renamed from: f */
    private float f314f = 2.0f;
    /* renamed from: g */
    private float f315g = 1.0f;

    public cb(AppLovinSdk appLovinSdk, Context context) {
        super(appLovinSdk, context);
    }

    /* renamed from: a */
    protected float m370a() {
        return this.f311c * this.f315g;
    }

    /* renamed from: a */
    public void m371a(float f) {
        this.f315g = f;
    }

    /* renamed from: a */
    public void mo2181a(int i) {
        m371a(((float) i) / this.f311c);
    }

    /* renamed from: b */
    protected float m373b() {
        return this.f313e * this.f315g;
    }

    /* renamed from: c */
    protected float m374c() {
        return this.f314f * this.f315g;
    }

    /* renamed from: d */
    protected float m375d() {
        return m370a() / 2.0f;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float d = m375d();
        Paint paint = new Paint(1);
        paint.setARGB(80, 0, 0, 0);
        canvas.drawCircle(d, d, d, paint);
        Paint paint2 = new Paint(1);
        paint2.setColor(-1);
        paint2.setStyle(Style.STROKE);
        paint2.setStrokeWidth(m374c());
        float b = m373b();
        float a = m370a() - b;
        canvas.drawLine(b, b, a, a, paint2);
        canvas.drawLine(b, a, a, b, paint2);
    }
}
