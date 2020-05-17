package com.cranevalley.dontendword.features.main.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.cranevalley.dontendword.C0521R;
import com.cranevalley.dontendword.features.bases.BaseFragment;
import com.cranevalley.dontendword.features.main.MainActivity;
import com.cranevalley.dontendword.features.main.home.about.AboutDialogFragment;
import com.cranevalley.dontendword.features.main.home.rules.RulesDialogFragment;
import com.cranevalley.dontendword.features.main.home.settings.SettingsDialogFragment;
import com.cranevalley.dontendword.features.main.multiplayermenu.MultiplayerMenuFragment;
import com.cranevalley.dontendword.features.main.playerprofile.PlayerProfileFragment;
import com.cranevalley.dontendword.helpers.AppConstant;
import com.cranevalley.dontendword.helpers.AudioHelper;
import java.util.Locale;

public class HomeFragment extends BaseFragment {
    private Unbinder mUnbinder;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    public void onDestroyView() {
        this.mUnbinder.unbind();
        super.onDestroyView();
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(C0521R.layout.home_fragment, container, false);
        this.mUnbinder = ButterKnife.bind((Object) this, rootView);
        return rootView;
    }

    @OnClick({2131820750})
    void onClickSinglePlayer(View sender) {
        AudioHelper.playClickEffect();
    }

    @OnClick({2131820751})
    void onClickMultiplayer(View sender) {
        AudioHelper.playClickEffect();
        ((MainActivity) getActivity()).replaceContentFragment(MultiplayerMenuFragment.newInstance());
    }

    @OnClick({2131820752})
    void onClickPlayerProfile(View sender) {
        AudioHelper.playClickEffect();
        ((MainActivity) getActivity()).replaceContentFragment(PlayerProfileFragment.newInstance());
    }

    @OnClick({2131820753})
    void onClickSettings(View sender) {
        AudioHelper.playClickEffect();
        SettingsDialogFragment settingsFragment = SettingsDialogFragment.newInstance();
        settingsFragment.setCancelable(true);
        settingsFragment.show(getFragmentManager(), null);
    }

    @OnClick({2131820754})
    void onClickShare(View sender) {
        AudioHelper.playClickEffect();
        String text = String.format(Locale.ENGLISH, "Let’s play Don’t End a Word! Download at %s and challenge me to a game!", new Object[]{AppConstant.ANDROID_APP_URL});
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.TITLE", "Don't End a Word!");
        intent.putExtra("android.intent.extra.SUBJECT", "Snack Sized Wordplay!");
        intent.putExtra("android.intent.extra.TEXT", text);
        startActivity(Intent.createChooser(intent, "Share via..."));
    }

    @OnClick({2131820755})
    void onClickRules(View sender) {
        AudioHelper.playClickEffect();
        RulesDialogFragment rulesFragment = RulesDialogFragment.newInstance();
        rulesFragment.setCancelable(true);
        rulesFragment.show(getFragmentManager(), null);
    }

    @OnClick({2131820756})
    void onClickAbout(View sender) {
        AudioHelper.playClickEffect();
        AboutDialogFragment aboutFragment = AboutDialogFragment.newInstance();
        aboutFragment.setCancelable(true);
        aboutFragment.show(getFragmentManager(), null);
    }
}
