package com.cranevalley.dontendword.features.bases;

import android.support.v4.app.Fragment;
import com.cranevalley.dontendword.features.MyApplication;

public abstract class BaseFragment extends Fragment {
    public void onDestroyView() {
        super.onDestroyView();
        MyApplication.getRefWatcher(getContext()).watch(this);
    }
}
