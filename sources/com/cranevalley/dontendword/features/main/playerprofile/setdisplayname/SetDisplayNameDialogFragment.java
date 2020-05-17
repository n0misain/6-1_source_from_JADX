package com.cranevalley.dontendword.features.main.playerprofile.setdisplayname;

import android.app.Dialog;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.AppCompatEditText;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import butterknife.Unbinder;
import com.cranevalley.dontendword.C0521R;
import com.cranevalley.dontendword.helpers.AudioHelper;
import com.cranevalley.dontendword.helpers.FirebaseHelper;

public class SetDisplayNameDialogFragment extends DialogFragment {
    @BindView(2131820854)
    AppCompatEditText displayNameEditText;
    private Unbinder mUnbinder;

    public static SetDisplayNameDialogFragment newInstance() {
        return new SetDisplayNameDialogFragment();
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(C0521R.layout.set_display_name_dialog_fragment, container, false);
        this.mUnbinder = ButterKnife.bind((Object) this, rootView);
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
            window.setLayout((int) (((double) point.x) * 0.85d), -2);
            window.setGravity(17);
        }
    }

    private boolean validateDisplayName() {
        String displayName = this.displayNameEditText.getText().toString();
        if (displayName.length() < 6) {
            this.displayNameEditText.setError("Minimum 6 characters");
            return false;
        } else if (displayName.length() > 20) {
            this.displayNameEditText.setError("Maximum 20 characters");
            return false;
        } else {
            this.displayNameEditText.setError(null);
            return true;
        }
    }

    @OnEditorAction({2131820854})
    boolean onEditorActionDisplayName(TextView sender, int actionId, KeyEvent event) {
        if (actionId == 6 && validateDisplayName()) {
            FirebaseHelper.setDisplayName(this.displayNameEditText.getText().toString());
            dismiss();
        }
        return false;
    }

    @OnClick({2131820856})
    void onClickSubmit(View view) {
        AudioHelper.playClickEffect();
        if (validateDisplayName()) {
            FirebaseHelper.setDisplayName(this.displayNameEditText.getText().toString());
            dismiss();
        }
    }

    @OnClick({2131820855})
    void onClickCancel(View view) {
        AudioHelper.playClickEffect();
        dismiss();
    }
}
