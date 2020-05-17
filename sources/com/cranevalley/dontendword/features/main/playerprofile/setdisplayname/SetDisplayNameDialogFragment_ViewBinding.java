package com.cranevalley.dontendword.features.main.playerprofile.setdisplayname;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatEditText;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.cranevalley.dontendword.C0521R;

public class SetDisplayNameDialogFragment_ViewBinding<T extends SetDisplayNameDialogFragment> implements Unbinder {
    protected T target;
    private View view2131820854;
    private View view2131820855;
    private View view2131820856;

    @UiThread
    public SetDisplayNameDialogFragment_ViewBinding(final T target, View source) {
        this.target = target;
        View view = Utils.findRequiredView(source, C0521R.id.displayNameEditText, "field 'displayNameEditText' and method 'onEditorActionDisplayName'");
        target.displayNameEditText = (AppCompatEditText) Utils.castView(view, C0521R.id.displayNameEditText, "field 'displayNameEditText'", AppCompatEditText.class);
        this.view2131820854 = view;
        ((TextView) view).setOnEditorActionListener(new OnEditorActionListener() {
            public boolean onEditorAction(TextView p0, int p1, KeyEvent p2) {
                return target.onEditorActionDisplayName(p0, p1, p2);
            }
        });
        view = Utils.findRequiredView(source, C0521R.id.submitButton, "method 'onClickSubmit'");
        this.view2131820856 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onClickSubmit(p0);
            }
        });
        view = Utils.findRequiredView(source, C0521R.id.cancelButton, "method 'onClickCancel'");
        this.view2131820855 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onClickCancel(p0);
            }
        });
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.displayNameEditText = null;
        ((TextView) this.view2131820854).setOnEditorActionListener(null);
        this.view2131820854 = null;
        this.view2131820856.setOnClickListener(null);
        this.view2131820856 = null;
        this.view2131820855.setOnClickListener(null);
        this.view2131820855 = null;
        this.target = null;
    }
}
