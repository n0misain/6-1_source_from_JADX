package com.applovin.impl.adview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;

/* renamed from: com.applovin.impl.adview.v */
public class C0491v extends View {
    /* renamed from: A */
    private final int f363A;
    /* renamed from: a */
    protected Paint f364a;
    /* renamed from: b */
    protected Paint f365b;
    /* renamed from: c */
    private Paint f366c;
    /* renamed from: d */
    private Paint f367d;
    /* renamed from: e */
    private RectF f368e;
    /* renamed from: f */
    private float f369f;
    /* renamed from: g */
    private int f370g;
    /* renamed from: h */
    private int f371h;
    /* renamed from: i */
    private int f372i;
    /* renamed from: j */
    private int f373j;
    /* renamed from: k */
    private int f374k;
    /* renamed from: l */
    private float f375l;
    /* renamed from: m */
    private int f376m;
    /* renamed from: n */
    private String f377n;
    /* renamed from: o */
    private String f378o;
    /* renamed from: p */
    private float f379p;
    /* renamed from: q */
    private String f380q;
    /* renamed from: r */
    private float f381r;
    /* renamed from: s */
    private final float f382s;
    /* renamed from: t */
    private final int f383t;
    /* renamed from: u */
    private final int f384u;
    /* renamed from: v */
    private final int f385v;
    /* renamed from: w */
    private final int f386w;
    /* renamed from: x */
    private final int f387x;
    /* renamed from: y */
    private final float f388y;
    /* renamed from: z */
    private final float f389z;

    public C0491v(Context context) {
        this(context, null);
    }

    public C0491v(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public C0491v(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f368e = new RectF();
        this.f372i = 0;
        this.f377n = "";
        this.f378o = "";
        this.f380q = "";
        this.f383t = Color.rgb(66, 145, 241);
        this.f384u = Color.rgb(66, 145, 241);
        this.f385v = Color.rgb(66, 145, 241);
        this.f386w = 0;
        this.f387x = 100;
        this.f388y = C0492w.m432b(getResources(), 14.0f);
        this.f363A = (int) C0492w.m431a(getResources(), 100.0f);
        this.f382s = C0492w.m431a(getResources(), 4.0f);
        this.f389z = C0492w.m432b(getResources(), 18.0f);
        m414b();
        m411a();
    }

    /* renamed from: e */
    private int m409e(int i) {
        int mode = MeasureSpec.getMode(i);
        int size = MeasureSpec.getSize(i);
        if (mode == 1073741824) {
            return size;
        }
        int i2 = this.f363A;
        return mode == Integer.MIN_VALUE ? Math.min(i2, size) : i2;
    }

    /* renamed from: o */
    private float m410o() {
        return (((float) m419d()) / ((float) this.f373j)) * 360.0f;
    }

    /* renamed from: a */
    protected void m411a() {
        this.f364a = new TextPaint();
        this.f364a.setColor(this.f370g);
        this.f364a.setTextSize(this.f369f);
        this.f364a.setAntiAlias(true);
        this.f365b = new TextPaint();
        this.f365b.setColor(this.f371h);
        this.f365b.setTextSize(this.f379p);
        this.f365b.setAntiAlias(true);
        this.f366c = new Paint();
        this.f366c.setColor(this.f374k);
        this.f366c.setStyle(Style.STROKE);
        this.f366c.setAntiAlias(true);
        this.f366c.setStrokeWidth(this.f375l);
        this.f367d = new Paint();
        this.f367d.setColor(this.f376m);
        this.f367d.setAntiAlias(true);
    }

    /* renamed from: a */
    public void m412a(float f) {
        this.f375l = f;
        invalidate();
    }

    /* renamed from: a */
    public void m413a(int i) {
        this.f372i = i;
        if (this.f372i > m421e()) {
            this.f372i %= m421e();
        }
        invalidate();
    }

    /* renamed from: b */
    protected void m414b() {
        this.f374k = this.f383t;
        this.f370g = this.f384u;
        this.f369f = this.f388y;
        m416b(100);
        m413a(0);
        this.f375l = this.f382s;
        this.f376m = 0;
        this.f379p = this.f389z;
        this.f371h = this.f385v;
    }

    /* renamed from: b */
    public void m415b(float f) {
        this.f369f = f;
        invalidate();
    }

    /* renamed from: b */
    public void m416b(int i) {
        if (i > 0) {
            this.f373j = i;
            invalidate();
        }
    }

    /* renamed from: c */
    public float m417c() {
        return this.f375l;
    }

    /* renamed from: c */
    public void m418c(int i) {
        this.f370g = i;
        invalidate();
    }

    /* renamed from: d */
    public int m419d() {
        return this.f372i;
    }

    /* renamed from: d */
    public void m420d(int i) {
        this.f374k = i;
        invalidate();
    }

    /* renamed from: e */
    public int m421e() {
        return this.f373j;
    }

    /* renamed from: f */
    public float m422f() {
        return this.f369f;
    }

    /* renamed from: g */
    public int m423g() {
        return this.f370g;
    }

    /* renamed from: h */
    public int m424h() {
        return this.f374k;
    }

    /* renamed from: i */
    public String m425i() {
        return this.f378o;
    }

    public void invalidate() {
        m411a();
        super.invalidate();
    }

    /* renamed from: j */
    public String m426j() {
        return this.f377n;
    }

    /* renamed from: k */
    public int m427k() {
        return this.f376m;
    }

    /* renamed from: l */
    public String m428l() {
        return this.f380q;
    }

    /* renamed from: m */
    public float m429m() {
        return this.f379p;
    }

    /* renamed from: n */
    public int m430n() {
        return this.f371h;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float f = this.f375l;
        this.f368e.set(f, f, ((float) getWidth()) - f, ((float) getHeight()) - f);
        canvas.drawCircle(((float) getWidth()) / 2.0f, ((float) getHeight()) / 2.0f, ((((float) getWidth()) - this.f375l) + this.f375l) / 2.0f, this.f367d);
        canvas.drawArc(this.f368e, 270.0f, -m410o(), false, this.f366c);
        String str = this.f377n + this.f372i + this.f378o;
        if (!TextUtils.isEmpty(str)) {
            canvas.drawText(str, (((float) getWidth()) - this.f364a.measureText(str)) / 2.0f, (((float) getWidth()) - (this.f364a.descent() + this.f364a.ascent())) / 2.0f, this.f364a);
        }
        if (!TextUtils.isEmpty(m428l())) {
            this.f365b.setTextSize(this.f379p);
            canvas.drawText(m428l(), (((float) getWidth()) - this.f365b.measureText(m428l())) / 2.0f, (((float) getHeight()) - this.f381r) - ((this.f364a.descent() + this.f364a.ascent()) / 2.0f), this.f365b);
        }
    }

    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(m409e(i), m409e(i2));
        this.f381r = (float) (getHeight() - ((getHeight() * 3) / 4));
    }

    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            this.f370g = bundle.getInt("text_color");
            this.f369f = bundle.getFloat("text_size");
            this.f379p = bundle.getFloat("inner_bottom_text_size");
            this.f380q = bundle.getString("inner_bottom_text");
            this.f371h = bundle.getInt("inner_bottom_text_color");
            this.f374k = bundle.getInt("finished_stroke_color");
            this.f375l = bundle.getFloat("finished_stroke_width");
            this.f376m = bundle.getInt("inner_background_color");
            m411a();
            m416b(bundle.getInt("max"));
            m413a(bundle.getInt("progress"));
            this.f377n = bundle.getString("prefix");
            this.f378o = bundle.getString("suffix");
            super.onRestoreInstanceState(bundle.getParcelable("saved_instance"));
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    protected Parcelable onSaveInstanceState() {
        Parcelable bundle = new Bundle();
        bundle.putParcelable("saved_instance", super.onSaveInstanceState());
        bundle.putInt("text_color", m423g());
        bundle.putFloat("text_size", m422f());
        bundle.putFloat("inner_bottom_text_size", m429m());
        bundle.putFloat("inner_bottom_text_color", (float) m430n());
        bundle.putString("inner_bottom_text", m428l());
        bundle.putInt("inner_bottom_text_color", m430n());
        bundle.putInt("finished_stroke_color", m424h());
        bundle.putInt("max", m421e());
        bundle.putInt("progress", m419d());
        bundle.putString("suffix", m425i());
        bundle.putString("prefix", m426j());
        bundle.putFloat("finished_stroke_width", m417c());
        bundle.putInt("inner_background_color", m427k());
        return bundle;
    }
}
