package com.cranevalley.dontendword.features.shared.lettersadapter;

import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

class EditableLetterViewHolder extends ViewHolder {
    @BindView(2131820741)
    AppCompatEditText letterEditText;
    EditableLetterViewHolderListener viewHolderListener;

    EditableLetterViewHolder(View view) {
        super(view);
        ButterKnife.bind((Object) this, view);
        setIsRecyclable(false);
        this.letterEditText.setInputType(0);
    }

    void bindLetter(String letter) {
        this.letterEditText.setText(letter);
    }

    String getLetter() {
        return this.letterEditText.getText().toString();
    }

    void clearLetter() {
        this.letterEditText.getText().clear();
    }

    @OnClick({2131820741})
    void onClickLetter(View view) {
        if (this.viewHolderListener != null) {
            this.viewHolderListener.onEditableLetterViewHolderClickEditText(getAdapterPosition(), this.letterEditText);
        }
    }

    @OnTextChanged({2131820741})
    void onTextChanged(CharSequence sequence, int start, int before, int count) {
        if (this.viewHolderListener != null) {
            this.viewHolderListener.onEditableLetterViewHolderChangeText(getAdapterPosition(), this.letterEditText.getText().toString());
        }
    }
}
