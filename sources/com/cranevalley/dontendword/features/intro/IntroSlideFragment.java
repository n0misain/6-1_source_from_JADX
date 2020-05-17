package com.cranevalley.dontendword.features.intro;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.cranevalley.dontendword.features.bases.BaseFragment;

public class IntroSlideFragment extends BaseFragment {
    private static final String LAYOUT_RESOURCE_KEY = "LAYOUT_RESOURCE_KEY";
    private int mLayoutResource;

    public static IntroSlideFragment newInstance(int layoutResource) {
        Bundle bundle = new Bundle();
        bundle.putInt(LAYOUT_RESOURCE_KEY, layoutResource);
        IntroSlideFragment introSlideFragment = new IntroSlideFragment();
        introSlideFragment.setArguments(bundle);
        return introSlideFragment;
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mLayoutResource = 0;
        Bundle receivedBundle = getArguments();
        if (savedInstanceState != null) {
            this.mLayoutResource = savedInstanceState.getInt(LAYOUT_RESOURCE_KEY);
        } else if (receivedBundle != null) {
            this.mLayoutResource = receivedBundle.getInt(LAYOUT_RESOURCE_KEY);
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(LAYOUT_RESOURCE_KEY, this.mLayoutResource);
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(this.mLayoutResource, container, false);
    }
}
