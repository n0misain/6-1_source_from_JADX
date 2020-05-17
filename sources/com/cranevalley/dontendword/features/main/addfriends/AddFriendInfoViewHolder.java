package com.cranevalley.dontendword.features.main.addfriends;

import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatImageView;
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

class AddFriendInfoViewHolder extends ViewHolder {
    @BindView(2131820690)
    AppCompatImageButton addFriendButton;
    @BindView(2131820689)
    AppCompatTextView displayNameTextView;
    @BindView(2131820691)
    AppCompatImageView friendIndicatorImageView;
    @BindView(2131820688)
    RoundedImageView photoImageView;
    AddFriendInfoViewHolderListener viewHolderListener;

    AddFriendInfoViewHolder(View view) {
        super(view);
        ButterKnife.bind((Object) this, view);
    }

    void bindUserInfo(UserInfo userInfo) {
        Picasso.with(this.itemView.getContext()).load(userInfo.photoUrl).placeholder((int) C0521R.drawable.photo_default).fit().centerCrop().into(this.photoImageView);
        this.displayNameTextView.setText(userInfo.displayName);
    }

    void bindFriendStatus(boolean friend) {
        if (friend) {
            this.addFriendButton.setVisibility(8);
            this.friendIndicatorImageView.setVisibility(0);
            return;
        }
        this.addFriendButton.setVisibility(0);
        this.friendIndicatorImageView.setVisibility(8);
    }

    @OnClick({2131820690})
    void onClickAddFriend(View sender) {
        if (this.viewHolderListener != null) {
            this.viewHolderListener.onAddFriendInfoViewHolderClickAddFriend(getAdapterPosition());
        }
    }
}
