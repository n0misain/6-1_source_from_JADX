package com.cranevalley.dontendword.features.shared;

import android.app.Dialog;
import android.graphics.Point;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.inputmethodservice.KeyboardView.OnKeyboardActionListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.cranevalley.dontendword.C0521R;

public class CapitalAlphabetKeyboardDialogFragment extends DialogFragment implements OnKeyboardActionListener {
    @BindView(2131820697)
    KeyboardView capitalAlphabetKeyboardView;
    private Editable mEditable;
    private CapitalAlphabetKeyboardFragmentListener mFragmentListener;
    private Unbinder mUnbinder;

    public static CapitalAlphabetKeyboardDialogFragment newInstance() {
        return new CapitalAlphabetKeyboardDialogFragment();
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(2, 0);
        if (savedInstanceState != null) {
            dismiss();
        }
    }

    public void onDestroy() {
        this.mFragmentListener = null;
        this.mEditable = null;
        super.onDestroy();
    }

    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setCanceledOnTouchOutside(true);
        return dialog;
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(C0521R.layout.capital_alphabet_keyboard_dialog_fragment, container, false);
        this.mUnbinder = ButterKnife.bind((Object) this, rootView);
        this.capitalAlphabetKeyboardView.setKeyboard(new Keyboard(getContext(), C0521R.xml.capital_alphabet_keyboard));
        this.capitalAlphabetKeyboardView.setPreviewEnabled(false);
        this.capitalAlphabetKeyboardView.setOnKeyboardActionListener(this);
        return rootView;
    }

    public void onDestroyView() {
        this.mUnbinder.unbind();
        super.onDestroyView();
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getTargetFragment() instanceof CapitalAlphabetKeyboardFragmentListener) {
            this.mFragmentListener = (CapitalAlphabetKeyboardFragmentListener) getTargetFragment();
        }
    }

    public void onResume() {
        super.onResume();
        Window window = getDialog().getWindow();
        if (window != null) {
            Point pointSize = new Point();
            window.getWindowManager().getDefaultDisplay().getSize(pointSize);
            window.setLayout(pointSize.x, -2);
            window.setGravity(81);
        }
    }

    public void onKey(int primaryCode, int[] keyCodes) {
        if (this.mFragmentListener != null) {
            this.mFragmentListener.onCapitalAlphabetKeyboardFragmentKey(this, this.mEditable, primaryCode, keyCodes);
        }
    }

    public void onPress(int primaryCode) {
    }

    public void onRelease(int primaryCode) {
    }

    public void onText(CharSequence text) {
    }

    public void swipeLeft() {
    }

    public void swipeRight() {
    }

    public void swipeUp() {
    }

    public void swipeDown() {
    }

    public void setEditable(Editable editable) {
        this.mEditable = editable;
    }
}
