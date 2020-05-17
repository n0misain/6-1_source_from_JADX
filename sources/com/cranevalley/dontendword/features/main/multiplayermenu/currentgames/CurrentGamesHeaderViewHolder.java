package com.cranevalley.dontendword.features.main.multiplayermenu.currentgames;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;

class CurrentGamesHeaderViewHolder extends ViewHolder {
    @BindView(2131820724)
    AppCompatTextView titleTextView;

    CurrentGamesHeaderViewHolder(View view) {
        super(view);
        ButterKnife.bind((Object) this, view);
    }

    void setTitle(String title) {
        this.titleTextView.setText(title);
    }
}
