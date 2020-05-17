package com.cranevalley.dontendword.features.main.defendchallenge;

import android.app.Dialog;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import butterknife.Unbinder;
import com.cranevalley.dontendword.C0521R;
import com.cranevalley.dontendword.features.shared.CapitalAlphabetKeyboardDialogFragment;
import com.cranevalley.dontendword.features.shared.CapitalAlphabetKeyboardFragmentListener;
import com.cranevalley.dontendword.helpers.AudioHelper;

public class DefendChallengeDialogFragment extends DialogFragment implements CapitalAlphabetKeyboardFragmentListener {
    private static final String LETTERS_KEY = "LETTERS_KEY";
    @BindView(2131820726)
    AppCompatButton addStringButton;
    @BindView(2131820725)
    AppCompatTextView lettersTextView;
    private DefendChallengeFragmentListener mFragmentListener;
    private String mLetters;
    private Unbinder mUnbinder;
    @BindView(2131820728)
    AppCompatButton submitWordButton;
    @BindView(2131820727)
    AppCompatEditText wordEditText;

    public static DefendChallengeDialogFragment newInstance(String letters) {
        Bundle bundle = new Bundle();
        bundle.putString(LETTERS_KEY, letters);
        DefendChallengeDialogFragment defendChallengeFragment = new DefendChallengeDialogFragment();
        defendChallengeFragment.setArguments(bundle);
        return defendChallengeFragment;
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mLetters = "";
        Bundle receivedBundle = getArguments();
        if (savedInstanceState != null) {
            this.mLetters = savedInstanceState.getString(LETTERS_KEY);
        } else if (receivedBundle != null) {
            this.mLetters = receivedBundle.getString(LETTERS_KEY);
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(LETTERS_KEY, this.mLetters);
    }

    public void onDestroy() {
        this.mFragmentListener = null;
        super.onDestroy();
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

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(C0521R.layout.defend_challenge_dialog_fragment, container, false);
        this.mUnbinder = ButterKnife.bind((Object) this, rootView);
        this.lettersTextView.setText(this.mLetters);
        this.lettersTextView.setSelected(true);
        this.wordEditText.setInputType(0);
        return rootView;
    }

    public void onDestroyView() {
        this.mUnbinder.unbind();
        super.onDestroyView();
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getTargetFragment() instanceof DefendChallengeFragmentListener) {
            this.mFragmentListener = (DefendChallengeFragmentListener) getTargetFragment();
        }
    }

    public void onResume() {
        super.onResume();
        Window window = getDialog().getWindow();
        if (window != null) {
            Point pointSize = new Point();
            window.getWindowManager().getDefaultDisplay().getSize(pointSize);
            window.setLayout((int) (((double) pointSize.x) * 0.9d), -2);
            window.setGravity(17);
        }
    }

    public void onCapitalAlphabetKeyboardFragmentKey(CapitalAlphabetKeyboardDialogFragment dialogFragment, Editable editable, int primaryCode, int[] keyCodes) {
        if (editable == null) {
            return;
        }
        if (primaryCode == -5) {
            if (editable.length() > 0) {
                editable.delete(editable.length() - 1, editable.length());
            }
        } else if (primaryCode == -4) {
            dialogFragment.dismiss();
        } else {
            editable.append(String.valueOf((char) primaryCode));
        }
    }

    @OnTextChanged({2131820727})
    void onTextChanged(CharSequence sequence, int start, int before, int count) {
        this.submitWordButton.setEnabled(this.wordEditText.getText().toString().contains(this.mLetters));
    }

    @OnClick({2131820727})
    void onClickWord(View view) {
        CapitalAlphabetKeyboardDialogFragment capitalAlphabetKeyboardFragment = CapitalAlphabetKeyboardDialogFragment.newInstance();
        capitalAlphabetKeyboardFragment.setTargetFragment(this, 0);
        capitalAlphabetKeyboardFragment.setEditable(this.wordEditText.getText());
        capitalAlphabetKeyboardFragment.setCancelable(true);
        capitalAlphabetKeyboardFragment.show(getFragmentManager(), null);
    }

    @OnClick({2131820726})
    void onClickAddString(View view) {
        AudioHelper.playClickEffect();
        this.wordEditText.append(this.mLetters);
    }

    @OnClick({2131820728})
    void onClickSubmitWord(View view) {
        AudioHelper.playClickEffect();
        this.addStringButton.setEnabled(false);
        this.submitWordButton.setEnabled(false);
        if (this.mFragmentListener != null) {
            this.mFragmentListener.onDefendChallengeFragmentClickSubmitWord(this, this.wordEditText.getText().toString());
        }
    }

    @OnClick({2131820687})
    void onClickClose(View view) {
        AudioHelper.playClickEffect();
        dismiss();
    }
}
