package com.cranevalley.dontendword.features.main.home.settings;

import android.app.Dialog;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CompoundButton;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.cranevalley.dontendword.C0521R;
import com.cranevalley.dontendword.features.main.MainActivity;
import com.cranevalley.dontendword.helpers.AudioHelper;
import com.cranevalley.dontendword.helpers.PreferencesHelper;
import com.kyleduo.switchbutton.SwitchButton;

public class SettingsDialogFragment extends DialogFragment {
    @BindView(2131820857)
    SwitchButton effectsSwitch;
    private Unbinder mUnbinder;
    @BindView(2131820858)
    SwitchButton musicSwitch;
    @BindView(2131820859)
    SwitchButton notificationsSwitch;
    @BindView(2131820860)
    AppCompatButton purchaseRemoveAdsButton;
    @BindView(2131820861)
    AppCompatImageView removeAdsIndicatorImageView;

    public static SettingsDialogFragment newInstance() {
        return new SettingsDialogFragment();
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(C0521R.layout.settings_dialog_fragment, container, false);
        this.mUnbinder = ButterKnife.bind((Object) this, rootView);
        this.effectsSwitch.setCheckedImmediatelyNoEvent(PreferencesHelper.isEffectsEnabled());
        this.musicSwitch.setCheckedImmediatelyNoEvent(PreferencesHelper.isMusicEnabled());
        this.notificationsSwitch.setCheckedImmediatelyNoEvent(PreferencesHelper.isNotificationsEnabled());
        if (((MainActivity) getActivity()).isRemoveAdsPurchased()) {
            this.purchaseRemoveAdsButton.setVisibility(8);
            this.removeAdsIndicatorImageView.setVisibility(0);
        } else {
            this.purchaseRemoveAdsButton.setVisibility(0);
            this.removeAdsIndicatorImageView.setVisibility(8);
        }
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
            window.setLayout((int) (((double) pointSize.x) * 0.95d), -2);
            window.setGravity(17);
        }
    }

    @OnCheckedChanged({2131820857})
    void onCheckedChangedEffects(CompoundButton sender, boolean isChecked) {
        PreferencesHelper.setEffectsEnabled(isChecked);
        AudioHelper.setEffectsVolume(isChecked ? 1.0f : 0.0f);
    }

    @OnCheckedChanged({2131820858})
    void onCheckedChangedMusic(CompoundButton sender, boolean isChecked) {
        PreferencesHelper.setMusicEnabled(isChecked);
        AudioHelper.setMusicVolume(isChecked ? 1.0f : 0.0f);
    }

    @OnCheckedChanged({2131820859})
    void onCheckedChangedNotifications(CompoundButton sender, boolean isChecked) {
        PreferencesHelper.setNotificationsEnabled(isChecked);
    }

    @OnClick({2131820860})
    void onClickPurchaseRemoveAds(View sender) {
        AudioHelper.playClickEffect();
        ((MainActivity) getActivity()).purchaseRemoveAds();
    }

    @OnClick({2131820687})
    void onClickClose(View sender) {
        AudioHelper.playClickEffect();
        dismiss();
    }
}
