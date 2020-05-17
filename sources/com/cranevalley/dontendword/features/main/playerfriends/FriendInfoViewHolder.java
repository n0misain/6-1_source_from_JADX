package com.cranevalley.dontendword.features.main.playerfriends;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.cranevalley.dontendword.C0521R;
import com.cranevalley.dontendword.features.shared.UserInfo;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

class FriendInfoViewHolder extends ViewHolder {
    @BindView(2131820748)
    AppCompatButton chatButton;
    @BindView(2131820689)
    AppCompatTextView displayNameTextView;
    @BindView(2131820688)
    RoundedImageView photoImageView;
    FriendInfoViewHolderListener viewHolderListener;

    FriendInfoViewHolder(View view) {
        super(view);
        ButterKnife.bind((Object) this, view);
    }

    void bindUserInfo(UserInfo userInfo) {
        Picasso.with(this.itemView.getContext()).load(userInfo.photoUrl).placeholder((int) C0521R.drawable.photo_default).fit().centerCrop().into(this.photoImageView);
        this.displayNameTextView.setText(userInfo.displayName);
    }

    void bindNewMessageStatus(boolean newMessage) {
        if (newMessage) {
            this.chatButton.setTextColor(ContextCompat.getColor(this.itemView.getContext(), C0521R.color.colorDanger));
        } else {
            this.chatButton.setTextColor(ContextCompat.getColor(this.itemView.getContext(), C0521R.color.colorThree));
        }
    }

    @OnClick({2131820749})
    void onClickPlay(View sender) {
        if (this.viewHolderListener != null) {
            this.viewHolderListener.onFriendInfoViewHolderClickPlay(getAdapterPosition());
        }
    }

    @OnClick({2131820748})
    void onClickChat(View sender) {
        if (this.viewHolderListener != null) {
            this.viewHolderListener.onFriendInfoViewHolderClickChat(getAdapterPosition());
        }
    }

    @OnClick({2131820747})
    void onClickUnfriend(View sender) {
        if (this.viewHolderListener != null) {
            this.viewHolderListener.onFriendInfoViewHolderClickUnfriend(getAdapterPosition());
        }
    }
}
