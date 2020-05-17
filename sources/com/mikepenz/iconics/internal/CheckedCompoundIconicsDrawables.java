package com.mikepenz.iconics.internal;

import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import com.mikepenz.iconics.IconicsDrawable;

@RestrictTo({Scope.LIBRARY_GROUP})
public interface CheckedCompoundIconicsDrawables {
    @Nullable
    IconicsDrawable getCheckedIconicsDrawableBottom();

    @Nullable
    IconicsDrawable getCheckedIconicsDrawableEnd();

    @Nullable
    IconicsDrawable getCheckedIconicsDrawableStart();

    @Nullable
    IconicsDrawable getCheckedIconicsDrawableTop();

    void setCheckedDrawableBottom(@Nullable IconicsDrawable iconicsDrawable);

    void setCheckedDrawableEnd(@Nullable IconicsDrawable iconicsDrawable);

    void setCheckedDrawableForAll(@Nullable IconicsDrawable iconicsDrawable);

    void setCheckedDrawableStart(@Nullable IconicsDrawable iconicsDrawable);

    void setCheckedDrawableTop(@Nullable IconicsDrawable iconicsDrawable);
}
