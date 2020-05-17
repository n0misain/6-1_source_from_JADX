package com.cranevalley.dontendword.features.main.home.rules;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.cranevalley.dontendword.C0521R;

public class RulesDialogFragment_ViewBinding<T extends RulesDialogFragment> implements Unbinder {
    protected T target;
    private View view2131820687;
    private View view2131820853;

    @UiThread
    public RulesDialogFragment_ViewBinding(final T target, View source) {
        this.target = target;
        target.rulesWebView = (WebView) Utils.findRequiredViewAsType(source, C0521R.id.rulesWebView, "field 'rulesWebView'", WebView.class);
        target.rulesProgressBar = (ProgressBar) Utils.findRequiredViewAsType(source, C0521R.id.rulesProgressBar, "field 'rulesProgressBar'", ProgressBar.class);
        View view = Utils.findRequiredView(source, C0521R.id.showIntroButton, "method 'onClickShowIntro'");
        this.view2131820853 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onClickShowIntro(p0);
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
        target.rulesWebView = null;
        target.rulesProgressBar = null;
        this.view2131820853.setOnClickListener(null);
        this.view2131820853 = null;
        this.view2131820687.setOnClickListener(null);
        this.view2131820687 = null;
        this.target = null;
    }
}
