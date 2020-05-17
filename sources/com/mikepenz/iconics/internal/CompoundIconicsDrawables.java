package com.mikepenz.iconics.internal;

import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import com.mikepenz.iconics.IconicsDrawable;

@RestrictTo({Scope.LIBRARY_GROUP})
public interface CompoundIconicsDrawables {
    @Nullable
    IconicsDrawable getIconicsDrawableBottom();

    @Nullable
    IconicsDrawable getIconicsDrawableEnd();

    @Nullable
    IconicsDrawable getIconicsDrawableStart();

    @Nullable
    IconicsDrawable getIconicsDrawableTop();

    void setDrawableBottom(@Nullable IconicsDrawable iconicsDrawable);

    void setDrawableEnd(@Nullable IconicsDrawable iconicsDrawable);

    void setDrawableForAll(@Nullable IconicsDrawable iconicsDrawable);

    void setDrawableStart(@Nullable IconicsDrawable iconicsDrawable);

    void setDrawableTop(@Nullable IconicsDrawable iconicsDrawable);
}
