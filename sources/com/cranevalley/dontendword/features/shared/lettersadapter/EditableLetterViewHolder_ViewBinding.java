package com.cranevalley.dontendword.features.shared.lettersadapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.cranevalley.dontendword.C0521R;

public class EditableLetterViewHolder_ViewBinding<T extends EditableLetterViewHolder> implements Unbinder {
    protected T target;
    private View view2131820741;
    private TextWatcher view2131820741TextWatcher;

    @UiThread
    public EditableLetterViewHolder_ViewBinding(final T target, View source) {
        this.target = target;
        View view = Utils.findRequiredView(source, C0521R.id.letterEditText, "field 'letterEditText', method 'onClickLetter', and method 'onTextChanged'");
        target.letterEditText = (AppCompatEditText) Utils.castView(view, C0521R.id.letterEditText, "field 'letterEditText'", AppCompatEditText.class);
        this.view2131820741 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onClickLetter(p0);
            }
        });
        this.view2131820741TextWatcher = new TextWatcher() {
            public void onTextChanged(CharSequence p0, int p1, int p2, int p3) {
                target.onTextChanged(p0, p1, p2, p3);
            }

            public void beforeTextChanged(CharSequence p0, int p1, int p2, int p3) {
            }

            public void afterTextChanged(Editable p0) {
            }
        };
        ((TextView) view).addTextChangedListener(this.view2131820741TextWatcher);
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.letterEditText = null;
        this.view2131820741.setOnClickListener(null);
        ((TextView) this.view2131820741).removeTextChangedListener(this.view2131820741TextWatcher);
        this.view2131820741TextWatcher = null;
        this.view2131820741 = null;
        this.target = null;
    }
}
