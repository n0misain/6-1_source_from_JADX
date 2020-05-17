package com.cranevalley.dontendword.features.main.multiplayermenu.currentgames;

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

class CurrentGameInfoViewHolder extends ViewHolder implements OnClickListener {
    @BindView(2131820689)
    AppCompatTextView displayNameTextView;
    @BindView(2131820725)
    AppCompatTextView lettersTextView;
    private int mRelativeIndex;
    private int mSection;
    @BindView(2131820688)
    RoundedImageView photoImageView;
    CurrentGameInfoViewHolderListener viewHolderListener;

    CurrentGameInfoViewHolder(View view) {
        super(view);
        ButterKnife.bind((Object) this, view);
        view.setOnClickListener(this);
    }

    public void onClick(View v) {
        if (this.viewHolderListener != null) {
            this.viewHolderListener.onCurrentGameInfoViewHolderClickItem(this.mSection, this.mRelativeIndex);
        }
    }

    void setSection(int section) {
        this.mSection = section;
    }

    void setRelativeIndex(int relativeIndex) {
        this.mRelativeIndex = relativeIndex;
    }

    void bindGameInfo(GameInfo gameInfo) {
        Picasso.with(this.itemView.getContext()).load(gameInfo.opponentPhotoUrl).placeholder((int) C0521R.drawable.photo_default).fit().centerCrop().into(this.photoImageView);
        this.displayNameTextView.setText(gameInfo.opponentDisplayName);
        this.lettersTextView.setText(gameInfo.letters);
    }
}
