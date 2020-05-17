package com.cranevalley.dontendword.features.main.home.settings;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.cranevalley.dontendword.C0521R;
import com.kyleduo.switchbutton.SwitchButton;

public class SettingsDialogFragment_ViewBinding<T extends SettingsDialogFragment> implements Unbinder {
    protected T target;
    private View view2131820687;
    private View view2131820857;
    private View view2131820858;
    private View view2131820859;
    private View view2131820860;

    @UiThread
    public SettingsDialogFragment_ViewBinding(final T target, View source) {
        this.target = target;
        target.removeAdsIndicatorImageView = (AppCompatImageView) Utils.findRequiredViewAsType(source, C0521R.id.removeAdsIndicatorImageView, "field 'removeAdsIndicatorImageView'", AppCompatImageView.class);
        View view = Utils.findRequiredView(source, C0521R.id.purchaseRemoveAdsButton, "field 'purchaseRemoveAdsButton' and method 'onClickPurchaseRemoveAds'");
        target.purchaseRemoveAdsButton = (AppCompatButton) Utils.castView(view, C0521R.id.purchaseRemoveAdsButton, "field 'purchaseRemoveAdsButton'", AppCompatButton.class);
        this.view2131820860 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onClickPurchaseRemoveAds(p0);
            }
        });
        view = Utils.findRequiredView(source, C0521R.id.effectsSwitch, "field 'effectsSwitch' and method 'onCheckedChangedEffects'");
        target.effectsSwitch = (SwitchButton) Utils.castView(view, C0521R.id.effectsSwitch, "field 'effectsSwitch'", SwitchButton.class);
        this.view2131820857 = view;
        ((CompoundButton) view).setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton p0, boolean p1) {
                target.onCheckedChangedEffects(p0, p1);
            }
        });
        view = Utils.findRequiredView(source, C0521R.id.musicSwitch, "field 'musicSwitch' and method 'onCheckedChangedMusic'");
        target.musicSwitch = (SwitchButton) Utils.castView(view, C0521R.id.musicSwitch, "field 'musicSwitch'", SwitchButton.class);
        this.view2131820858 = view;
        ((CompoundButton) view).setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton p0, boolean p1) {
                target.onCheckedChangedMusic(p0, p1);
            }
        });
        view = Utils.findRequiredView(source, C0521R.id.notificationsSwitch, "field 'notificationsSwitch' and method 'onCheckedChangedNotifications'");
        target.notificationsSwitch = (SwitchButton) Utils.castView(view, C0521R.id.notificationsSwitch, "field 'notificationsSwitch'", SwitchButton.class);
        this.view2131820859 = view;
        ((CompoundButton) view).setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton p0, boolean p1) {
                target.onCheckedChangedNotifications(p0, p1);
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
        target.removeAdsIndicatorImageView = null;
        target.purchaseRemoveAdsButton = null;
        target.effectsSwitch = null;
        target.musicSwitch = null;
        target.notificationsSwitch = null;
        this.view2131820860.setOnClickListener(null);
        this.view2131820860 = null;
        ((CompoundButton) this.view2131820857).setOnCheckedChangeListener(null);
        this.view2131820857 = null;
        ((CompoundButton) this.view2131820858).setOnCheckedChangeListener(null);
        this.view2131820858 = null;
        ((CompoundButton) this.view2131820859).setOnCheckedChangeListener(null);
        this.view2131820859 = null;
        this.view2131820687.setOnClickListener(null);
        this.view2131820687 = null;
        this.target = null;
    }
}
