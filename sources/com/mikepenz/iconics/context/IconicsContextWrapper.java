package com.mikepenz.iconics.context;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.view.LayoutInflater;

public class IconicsContextWrapper extends ContextWrapper {
    private LayoutInflater mInflater;

    private IconicsContextWrapper(Context base) {
        super(base);
    }

    public static ContextWrapper wrap(Context base) {
        return new IconicsContextWrapper(base);
    }

    public Resources getResources() {
        return super.getResources();
    }

    public Object getSystemService(String name) {
        if (!"layout_inflater".equals(name)) {
            return super.getSystemService(name);
        }
        if (this.mInflater == null) {
            this.mInflater = new InternalLayoutInflater(LayoutInflater.from(getBaseContext()), this, false);
        }
        return this.mInflater;
    }
}
