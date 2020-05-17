package com.cranevalley.dontendword.features.shared.lettersadapter;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;

class FixedLetterViewHolder extends ViewHolder {
    @BindView(2131820744)
    AppCompatTextView letterTextView;

    FixedLetterViewHolder(View view) {
        super(view);
        ButterKnife.bind((Object) this, view);
    }

    void bindLetter(String letter) {
        this.letterTextView.setText(letter);
    }
}
