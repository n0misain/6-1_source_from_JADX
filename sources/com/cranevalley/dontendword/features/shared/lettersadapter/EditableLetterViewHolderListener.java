package com.cranevalley.dontendword.features.shared.lettersadapter;

import android.widget.EditText;

interface EditableLetterViewHolderListener {
    void onEditableLetterViewHolderChangeText(int i, String str);

    void onEditableLetterViewHolderClickEditText(int i, EditText editText);
}
