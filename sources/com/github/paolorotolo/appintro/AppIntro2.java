package com.github.paolorotolo.appintro;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import com.github.paolorotolo.appintro.util.LogHelper;
import java.util.ArrayList;

public abstract class AppIntro2 extends AppIntroBase {
    private static final String TAG = LogHelper.makeLogTag(AppIntro2.class);
    protected FrameLayout backgroundFrame;
    protected View customBackgroundView;
    private ArrayList<Integer> transitionColors;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.backgroundFrame = (FrameLayout) findViewById(C0864R.id.background);
    }

    protected int getLayoutId() {
        return C0864R.layout.intro_layout2;
    }

    @Deprecated
    public void showDoneButton(boolean showDone) {
        setProgressButtonEnabled(showDone);
    }

    public void setBarColor(@ColorInt int color) {
        ((LinearLayout) findViewById(C0864R.id.bottom)).setBackgroundColor(color);
    }

    public void setImageSkipButton(Drawable imageSkipButton) {
        ((ImageButton) findViewById(C0864R.id.skip)).setImageDrawable(imageSkipButton);
    }

    public void setBackgroundView(View view) {
        this.customBackgroundView = view;
        if (this.customBackgroundView != null) {
            this.backgroundFrame.addView(this.customBackgroundView);
        }
    }

    public void setAnimationColors(ArrayList<Integer> colors) {
        this.transitionColors = colors;
    }
}
