package com.cranevalley.dontendword.features.main.chat;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.cranevalley.dontendword.C0521R;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

class OpponentMessageViewHolder extends ViewHolder {
    @BindView(2131820688)
    RoundedImageView photoImageView;
    @BindView(2131820836)
    AppCompatTextView textTextView;

    OpponentMessageViewHolder(View view) {
        super(view);
        ButterKnife.bind((Object) this, view);
    }

    void bindMessageInfo(MessageInfo messageInfo) {
        this.textTextView.setText(messageInfo.text);
    }

    void bindPhoto(String photoUrl) {
        Picasso.with(this.itemView.getContext()).load(photoUrl).placeholder((int) C0521R.drawable.photo_default).fit().centerCrop().into(this.photoImageView);
    }
}
