package com.cranevalley.dontendword.features.intro;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import com.cranevalley.dontendword.C0521R;
import com.cranevalley.dontendword.helpers.PreferencesHelper;
import com.github.paolorotolo.appintro.AppIntro;

public class IntroActivity extends AppIntro {
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addSlide(IntroSlideFragment.newInstance(C0521R.layout.intro_slide_0_fragment));
        addSlide(IntroSlideFragment.newInstance(C0521R.layout.intro_slide_1_fragment));
        addSlide(IntroSlideFragment.newInstance(C0521R.layout.intro_slide_2_fragment));
        addSlide(IntroSlideFragment.newInstance(C0521R.layout.intro_slide_3_fragment));
        addSlide(IntroSlideFragment.newInstance(C0521R.layout.intro_slide_4_fragment));
        setBarColor(ContextCompat.getColor(this, C0521R.color.colorThree));
        setSeparatorColor(ContextCompat.getColor(this, C0521R.color.colorFour));
        setIndicatorColor(ContextCompat.getColor(this, C0521R.color.colorZero), ContextCompat.getColor(this, C0521R.color.colorOne));
        showSkipButton(true);
        setSkipText("SKIP");
        setColorSkipButton(ContextCompat.getColor(this, C0521R.color.colorZero));
        setProgressButtonEnabled(true);
        setDoneText("DONE");
        setColorDoneText(ContextCompat.getColor(this, C0521R.color.colorZero));
        setVibrate(false);
        setDepthAnimation();
        PreferencesHelper.setIntroSeen(true);
    }

    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        finish();
    }

    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        finish();
    }
}
