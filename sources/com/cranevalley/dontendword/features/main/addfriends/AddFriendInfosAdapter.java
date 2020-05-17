package com.cranevalley.dontendword.features.main.addfriends;

import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.cranevalley.dontendword.C0521R;
import com.cranevalley.dontendword.features.shared.UserInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

class AddFriendInfosAdapter extends Adapter<AddFriendInfoViewHolder> implements AddFriendInfoViewHolderListener {
    AddFriendInfosAdapterListener adapterListener;
    private List<UserInfo> mAllUserInfosList;
    private List<UserInfo> mFilteredUserInfosList = new ArrayList();
    private Set<String> mFriendUserIdsSet;
    private String mSearchText;

    AddFriendInfosAdapter(String searchText, Set<String> friendUserIdsSet, List<UserInfo> allUserInfosList) {
        this.mSearchText = searchText;
        this.mFriendUserIdsSet = friendUserIdsSet;
        this.mAllUserInfosList = allUserInfosList;
        filter();
    }

    public int getItemCount() {
        return this.mFilteredUserInfosList.size();
    }

    public AddFriendInfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AddFriendInfoViewHolder(LayoutInflater.from(parent.getContext()).inflate(C0521R.layout.add_friend_info_view_holder, parent, false));
    }

    public void onBindViewHolder(AddFriendInfoViewHolder holder, int position) {
        UserInfo userInfo = (UserInfo) this.mFilteredUserInfosList.get(position);
        holder.bindUserInfo(userInfo);
        holder.bindFriendStatus(this.mFriendUserIdsSet.contains(userInfo.userId));
        holder.viewHolderListener = this;
    }

    public void onAddFriendInfoViewHolderClickAddFriend(int index) {
        UserInfo userInfo = (UserInfo) this.mFilteredUserInfosList.get(index);
        if (this.adapterListener != null) {
            this.adapterListener.onAddFriendInfosAdapterAddFriend(userInfo);
        }
    }

    void setSearchText(String searchText) {
        this.mSearchText = searchText;
        filter();
    }

    void setFriendUserIdsSet(Set<String> friendUserIdsSet) {
        this.mFriendUserIdsSet = friendUserIdsSet;
        filter();
    }

    void setAllUserInfosList(List<UserInfo> allUserInfosList) {
        this.mAllUserInfosList = allUserInfosList;
        filter();
    }

    private void filter() {
        this.mFilteredUserInfosList.clear();
        if (this.mSearchText != null && this.mSearchText.length() >= 2) {
            for (UserInfo userInfo : this.mAllUserInfosList) {
                if (userInfo.displayName.toLowerCase().contains(this.mSearchText.toLowerCase())) {
                    this.mFilteredUserInfosList.add(userInfo);
                }
            }
        }
    }
}
