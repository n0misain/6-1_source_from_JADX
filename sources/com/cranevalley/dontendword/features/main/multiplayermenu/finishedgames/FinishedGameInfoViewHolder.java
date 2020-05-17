package com.cranevalley.dontendword.features.main.multiplayermenu.finishedgames;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.View.OnClickListener;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.cranevalley.dontendword.C0521R;
import com.cranevalley.dontendword.features.shared.GameInfo;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

class FinishedGameInfoViewHolder extends ViewHolder implements OnClickListener {
    @BindView(2131820689)
    AppCompatTextView displayNameTextView;
    @BindView(2131820725)
    AppCompatTextView lettersTextView;
    @BindView(2131820688)
    RoundedImageView photoImageView;
    @BindView(2131820742)
    AppCompatTextView resultTextView;
    FinishedGameInfoViewHolderListener viewHolderListener;

    FinishedGameInfoViewHolder(View view) {
        super(view);
        ButterKnife.bind((Object) this, view);
        view.setOnClickListener(this);
    }

    public void onClick(View v) {
        if (this.viewHolderListener != null) {
            this.viewHolderListener.onFinishedGameInfoViewHolderClickItem(getAdapterPosition());
        }
    }

    void bindFinishedGameInfo(GameInfo gameInfo) {
        Picasso.with(this.itemView.getContext()).load(gameInfo.opponentPhotoUrl).placeholder((int) C0521R.drawable.photo_default).fit().centerCrop().into(this.photoImageView);
        this.displayNameTextView.setText(gameInfo.opponentDisplayName);
        this.lettersTextView.setText(gameInfo.letters);
        this.resultTextView.setText(gameInfo.result);
    }
}
