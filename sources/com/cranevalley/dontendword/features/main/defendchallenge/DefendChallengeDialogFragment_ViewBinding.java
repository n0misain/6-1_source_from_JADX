package com.cranevalley.dontendword.features.main.defendchallenge;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.cranevalley.dontendword.C0521R;

public class DefendChallengeDialogFragment_ViewBinding<T extends DefendChallengeDialogFragment> implements Unbinder {
    protected T target;
    private View view2131820687;
    private View view2131820726;
    private View view2131820727;
    private TextWatcher view2131820727TextWatcher;
    private View view2131820728;

    @UiThread
    public DefendChallengeDialogFragment_ViewBinding(final T target, View source) {
        this.target = target;
        target.lettersTextView = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0521R.id.lettersTextView, "field 'lettersTextView'", AppCompatTextView.class);
        View view = Utils.findRequiredView(source, C0521R.id.wordEditText, "field 'wordEditText', method 'onClickWord', and method 'onTextChanged'");
        target.wordEditText = (AppCompatEditText) Utils.castView(view, C0521R.id.wordEditText, "field 'wordEditText'", AppCompatEditText.class);
        this.view2131820727 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onClickWord(p0);
            }
        });
        this.view2131820727TextWatcher = new TextWatcher() {
            public void onTextChanged(CharSequence p0, int p1, int p2, int p3) {
                target.onTextChanged(p0, p1, p2, p3);
            }

            public void beforeTextChanged(CharSequence p0, int p1, int p2, int p3) {
            }

            public void afterTextChanged(Editable p0) {
            }
        };
        ((TextView) view).addTextChangedListener(this.view2131820727TextWatcher);
        view = Utils.findRequiredView(source, C0521R.id.addStringButton, "field 'addStringButton' and method 'onClickAddString'");
        target.addStringButton = (AppCompatButton) Utils.castView(view, C0521R.id.addStringButton, "field 'addStringButton'", AppCompatButton.class);
        this.view2131820726 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onClickAddString(p0);
            }
        });
        view = Utils.findRequiredView(source, C0521R.id.submitWordButton, "field 'submitWordButton' and method 'onClickSubmitWord'");
        target.submitWordButton = (AppCompatButton) Utils.castView(view, C0521R.id.submitWordButton, "field 'submitWordButton'", AppCompatButton.class);
        this.view2131820728 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onClickSubmitWord(p0);
            }
        });
        view = Utils.findRequiredView(source, C0521R.id.closeButton, "method 'onClickClose'");
        this.view2131820687 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onClickClose(p0);
            }
        });
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.lettersTextView = null;
        target.wordEditText = null;
        target.addStringButton = null;
        target.submitWordButton = null;
        this.view2131820727.setOnClickListener(null);
        ((TextView) this.view2131820727).removeTextChangedListener(this.view2131820727TextWatcher);
        this.view2131820727TextWatcher = null;
        this.view2131820727 = null;
        this.view2131820726.setOnClickListener(null);
        this.view2131820726 = null;
        this.view2131820728.setOnClickListener(null);
        this.view2131820728 = null;
        this.view2131820687.setOnClickListener(null);
        this.view2131820687 = null;
        this.target = null;
    }
}
