package com.cranevalley.dontendword.features.main.playerfriends;

import com.cranevalley.dontendword.features.shared.UserInfo;

interface FriendInfosAdapterListener {
    void onFriendInfosAdapterClickChat(UserInfo userInfo);

    void onFriendInfosAdapterClickPlay(UserInfo userInfo);

    void onFriendInfosAdapterClickUnfriend(UserInfo userInfo);
}
