package com.cranevalley.dontendword.features.shared;

import android.inputmethodservice.KeyboardView;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.cranevalley.dontendword.C0521R;

public class CapitalAlphabetKeyboardDialogFragment_ViewBinding<T extends CapitalAlphabetKeyboardDialogFragment> implements Unbinder {
    protected T target;

    @UiThread
    public CapitalAlphabetKeyboardDialogFragment_ViewBinding(T target, View source) {
        this.target = target;
        target.capitalAlphabetKeyboardView = (KeyboardView) Utils.findRequiredViewAsType(source, C0521R.id.capitalAlphabetKeyboardView, "field 'capitalAlphabetKeyboardView'", KeyboardView.class);
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.capitalAlphabetKeyboardView = null;
        this.target = null;
    }
}
