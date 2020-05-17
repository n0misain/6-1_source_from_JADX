package com.mikepenz.iconics.internal;

import android.content.Context;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.utils.Utils;

@RestrictTo({Scope.LIBRARY_GROUP})
public class CheckableIconBundle {
    public boolean mAnimateChanges;
    public IconicsDrawable mCheckedIconBundle;
    public IconicsDrawable mUncheckedIconBundle;

    public void createIcons(Context ctx) {
        this.mCheckedIconBundle = new IconicsDrawable(ctx);
        this.mUncheckedIconBundle = new IconicsDrawable(ctx);
    }

    public StateListDrawable createStates(Context ctx) {
        return Utils.getCheckableIconStateList(ctx, this.mUncheckedIconBundle, this.mCheckedIconBundle, this.mAnimateChanges);
    }
}
