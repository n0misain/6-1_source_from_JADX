package com.mikepenz.iconics.context;

import android.content.Context;
import android.support.v7.app.AppCompatDelegate;
import android.util.AttributeSet;
import android.view.LayoutInflater.Factory2;
import android.view.View;

public class IconicsLayoutInflater2 implements Factory2 {
    private AppCompatDelegate appCompatDelegate;
    private final IconicsFactory mIconicsFactory = new IconicsFactory();

    public IconicsLayoutInflater2(AppCompatDelegate appCompatDelegate) {
        this.appCompatDelegate = appCompatDelegate;
    }

    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        return this.mIconicsFactory.onViewCreated(this.appCompatDelegate.createView(parent, name, context, attrs), context, attrs);
    }

    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return this.mIconicsFactory.onViewCreated(this.appCompatDelegate.createView(null, name, context, attrs), context, attrs);
    }
}
