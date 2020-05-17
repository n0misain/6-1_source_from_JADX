package com.cranevalley.dontendword.features.main.home.rules;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Point;
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
import com.cranevalley.dontendword.features.intro.IntroActivity;
import com.cranevalley.dontendword.helpers.AudioHelper;

public class RulesDialogFragment extends DialogFragment {
    private Unbinder mUnbinder;
    @BindView(2131820852)
    ProgressBar rulesProgressBar;
    @BindView(2131820851)
    WebView rulesWebView;

    /* renamed from: com.cranevalley.dontendword.features.main.home.rules.RulesDialogFragment$1 */
    class C05711 extends WebViewClient {
        C05711() {
        }

        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            RulesDialogFragment.this.rulesProgressBar.setIndeterminate(false);
            RulesDialogFragment.this.rulesProgressBar.setVisibility(8);
        }
    }

    public static RulesDialogFragment newInstance() {
        return new RulesDialogFragment();
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(C0521R.layout.rules_dialog_fragment, container, false);
        this.mUnbinder = ButterKnife.bind((Object) this, rootView);
        this.rulesWebView.setBackgroundColor(0);
        this.rulesWebView.setWebViewClient(new C05711());
        this.rulesWebView.loadUrl("file:///android_res/raw/rules.html");
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
            Point pointSize = new Point();
            window.getWindowManager().getDefaultDisplay().getSize(pointSize);
            window.setLayout((int) (((double) pointSize.x) * 0.95d), (int) (((double) pointSize.y) * 0.8d));
            window.setGravity(17);
        }
    }

    @OnClick({2131820853})
    void onClickShowIntro(View view) {
        AudioHelper.playClickEffect();
        startActivity(new Intent(getContext(), IntroActivity.class));
    }

    @OnClick({2131820687})
    void onClickClose(View view) {
        AudioHelper.playClickEffect();
        dismiss();
    }
}
