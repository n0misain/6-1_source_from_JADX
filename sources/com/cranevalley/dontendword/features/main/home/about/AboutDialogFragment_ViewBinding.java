package com.cranevalley.dontendword.features.main.home.about;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.cranevalley.dontendword.C0521R;

public class AboutDialogFragment_ViewBinding<T extends AboutDialogFragment> implements Unbinder {
    protected T target;
    private View view2131820686;
    private View view2131820687;

    @UiThread
    public AboutDialogFragment_ViewBinding(final T target, View source) {
        this.target = target;
        target.aboutWebView = (WebView) Utils.findRequiredViewAsType(source, C0521R.id.aboutWebView, "field 'aboutWebView'", WebView.class);
        target.aboutProgressBar = (ProgressBar) Utils.findRequiredViewAsType(source, C0521R.id.aboutProgressBar, "field 'aboutProgressBar'", ProgressBar.class);
        View view = Utils.findRequiredView(source, C0521R.id.feedbackButton, "method 'onClickFeedback'");
        this.view2131820686 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onClickFeedback(p0);
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
        target.aboutWebView = null;
        target.aboutProgressBar = null;
        this.view2131820686.setOnClickListener(null);
        this.view2131820686 = null;
        this.view2131820687.setOnClickListener(null);
        this.view2131820687 = null;
        this.target = null;
    }
}
