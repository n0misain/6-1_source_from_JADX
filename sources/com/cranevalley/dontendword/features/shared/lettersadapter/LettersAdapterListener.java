package com.cranevalley.dontendword.features.shared.lettersadapter;

import android.widget.EditText;

public interface LettersAdapterListener {
    void onLettersAdapterChangeLetters(String str, String str2);

    void onLettersAdapterClickEditText(EditText editText);
}
