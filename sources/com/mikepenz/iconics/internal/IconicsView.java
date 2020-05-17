package com.mikepenz.iconics.internal;

import android.content.Context;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.util.AttributeSet;

@RestrictTo({Scope.LIBRARY_GROUP})
public interface IconicsView {
    @RestrictTo({Scope.LIBRARY_GROUP})
    void applyAttr(Context context, AttributeSet attributeSet, int i);

    @RestrictTo({Scope.LIBRARY_GROUP})
    void initialize(Context context, AttributeSet attributeSet, int i);
}
