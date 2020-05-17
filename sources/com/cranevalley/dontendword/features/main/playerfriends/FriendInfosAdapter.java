package com.cranevalley.dontendword.features.main.playerfriends;

import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.cranevalley.dontendword.C0521R;
import com.cranevalley.dontendword.features.shared.UserInfo;
import java.util.List;
import java.util.Set;

class FriendInfosAdapter extends Adapter<FriendInfoViewHolder> implements FriendInfoViewHolderListener {
    FriendInfosAdapterListener adapterListener;
    private List<UserInfo> mFriendUserInfosList;
    private Set<String> mNewMessageUserIdsSet;

    FriendInfosAdapter(Set<String> newMessageUserIdsSet, List<UserInfo> friendUserInfosList) {
        this.mNewMessageUserIdsSet = newMessageUserIdsSet;
        this.mFriendUserInfosList = friendUserInfosList;
    }

    public int getItemCount() {
        return this.mFriendUserInfosList.size();
    }

    public FriendInfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FriendInfoViewHolder(LayoutInflater.from(parent.getContext()).inflate(C0521R.layout.friend_info_view_holder, parent, false));
    }

    public void onBindViewHolder(FriendInfoViewHolder holder, int position) {
        UserInfo userInfo = (UserInfo) this.mFriendUserInfosList.get(position);
        holder.bindUserInfo(userInfo);
        holder.bindNewMessageStatus(this.mNewMessageUserIdsSet.contains(userInfo.userId));
        holder.viewHolderListener = this;
    }

    public void onFriendInfoViewHolderClickPlay(int index) {
        UserInfo userInfo = (UserInfo) this.mFriendUserInfosList.get(index);
        if (this.adapterListener != null) {
            this.adapterListener.onFriendInfosAdapterClickPlay(userInfo);
        }
    }

    public void onFriendInfoViewHolderClickChat(int index) {
        UserInfo userInfo = (UserInfo) this.mFriendUserInfosList.get(index);
        if (this.adapterListener != null) {
            this.adapterListener.onFriendInfosAdapterClickChat(userInfo);
        }
    }

    public void onFriendInfoViewHolderClickUnfriend(int index) {
        UserInfo userInfo = (UserInfo) this.mFriendUserInfosList.get(index);
        if (this.adapterListener != null) {
            this.adapterListener.onFriendInfosAdapterClickUnfriend(userInfo);
        }
    }

    void setNewMessageUserIdsSet(Set<String> newMessageUserIdsSet) {
        this.mNewMessageUserIdsSet = newMessageUserIdsSet;
    }

    void setFriendUserInfosList(List<UserInfo> friendUserInfosList) {
        this.mFriendUserInfosList = friendUserInfosList;
    }
}
