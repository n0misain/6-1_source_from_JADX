package com.applovin.impl.adview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import com.applovin.sdk.AppLovinSdk;

public class ci extends C0471x {
    /* renamed from: c */
    private float f331c = 30.0f;
    /* renamed from: d */
    private float f332d = 2.0f;
    /* renamed from: e */
    private float f333e = 10.0f;
    /* renamed from: f */
    private float f334f = 3.0f;
    /* renamed from: g */
    private float f335g = 1.0f;

    public ci(AppLovinSdk appLovinSdk, Context context) {
        super(appLovinSdk, context);
    }

    /* renamed from: a */
    protected float m391a() {
        return this.f331c * this.f335g;
    }

    /* renamed from: a */
    public void m392a(float f) {
        this.f335g = f;
    }

    /* renamed from: a */
    public void mo2181a(int i) {
        m392a(((float) i) / this.f331c);
    }

    /* renamed from: b */
    protected float m394b() {
        return this.f333e * this.f335g;
    }

    /* renamed from: c */
    protected float m395c() {
        return this.f334f * this.f335g;
    }

    /* renamed from: d */
    protected float m396d() {
        return m391a() / 2.0f;
    }

    /* renamed from: e */
    protected float m397e() {
        return this.f332d * this.f335g;
    }

    /* renamed from: f */
    protected float m398f() {
        return m396d() - m397e();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float d = m396d();
        Paint paint = new Paint(1);
        paint.setColor(-1);
        canvas.drawCircle(d, d, d, paint);
        Paint paint2 = new Paint(1);
        paint2.setColor(-16777216);
        canvas.drawCircle(d, d, m398f(), paint2);
        Paint paint3 = new Paint(paint);
        paint3.setStyle(Style.STROKE);
        paint3.setStrokeWidth(m395c());
        float b = m394b();
        float a = m391a() - b;
        canvas.drawLine(b, b, a, a, paint3);
        canvas.drawLine(b, a, a, b, paint3);
    }
}
