package com.cranevalley.dontendword.features.main.home.about;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.cranevalley.dontendword.C0521R;
import com.cranevalley.dontendword.helpers.AudioHelper;

public class AboutDialogFragment extends DialogFragment {
    @BindView(2131820685)
    ProgressBar aboutProgressBar;
    @BindView(2131820684)
    WebView aboutWebView;
    private Unbinder mUnbinder;

    /* renamed from: com.cranevalley.dontendword.features.main.home.about.AboutDialogFragment$1 */
    class C05681 extends WebViewClient {
        C05681() {
        }

        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            AboutDialogFragment.this.aboutProgressBar.setIndeterminate(false);
            AboutDialogFragment.this.aboutProgressBar.setVisibility(8);
        }
    }

    public static AboutDialogFragment newInstance() {
        return new AboutDialogFragment();
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(C0521R.layout.about_dialog_fragment, container, false);
        this.mUnbinder = ButterKnife.bind((Object) this, rootView);
        this.aboutWebView.setBackgroundColor(0);
        this.aboutWebView.setWebViewClient(new C05681());
        this.aboutWebView.loadUrl("file:///android_res/raw/about.html");
        return rootView;
    }

    public void onDestroyView() {
        this.mUnbinder.unbind();
        super.onDestroyView();
    }

    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        Window window = dialog.getWindow();
        if (window != null) {
            window.requestFeature(1);
        }
        return dialog;
    }

    public void onResume() {
        super.onResume();
        Window window = getDialog().getWindow();
        if (window != null) {
            Point point = new Point();
            window.getWindowManager().getDefaultDisplay().getSize(point);
            window.setLayout((int) (((double) point.x) * 0.95d), (int) (((double) point.y) * 0.8d));
            window.setGravity(17);
        }
    }

    @OnClick({2131820686})
    void onClickFeedback(View view) {
        AudioHelper.playClickEffect();
        Intent intent = new Intent("android.intent.action.SENDTO");
        intent.setType("text/plain");
        intent.setData(Uri.parse("mailto:cranevalley.deaw@gmail.com"));
        intent.putExtra("android.intent.extra.SUBJECT", "Feedback : Don't End a Word");
        intent.addFlags(268435456);
        startActivity(intent);
    }

    @OnClick({2131820687})
    void onClickClose(View view) {
        AudioHelper.playClickEffect();
        dismiss();
    }
}
