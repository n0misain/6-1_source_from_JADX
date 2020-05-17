package com.cranevalley.dontendword.features.main.chat;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;

class PlayerMessageViewHolder extends ViewHolder {
    @BindView(2131820836)
    AppCompatTextView textTextView;

    PlayerMessageViewHolder(View view) {
        super(view);
        ButterKnife.bind((Object) this, view);
    }

    void bindMessageInfo(MessageInfo messageInfo) {
        this.textTextView.setText(messageInfo.text);
    }
}
