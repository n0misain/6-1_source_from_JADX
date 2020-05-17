package com.cranevalley.dontendword.features.shared.lettersadapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.cranevalley.dontendword.C0521R;

public class FixedLetterViewHolder_ViewBinding<T extends FixedLetterViewHolder> implements Unbinder {
    protected T target;

    @UiThread
    public FixedLetterViewHolder_ViewBinding(T target, View source) {
        this.target = target;
        target.letterTextView = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0521R.id.letterTextView, "field 'letterTextView'", AppCompatTextView.class);
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.letterTextView = null;
        this.target = null;
    }
}
