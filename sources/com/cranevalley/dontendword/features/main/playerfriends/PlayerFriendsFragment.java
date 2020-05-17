package com.cranevalley.dontendword.features.main.playerfriends;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.AdapterDataObserver;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.State;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.cranevalley.dontendword.C0521R;
import com.cranevalley.dontendword.features.bases.BaseFragment;
import com.cranevalley.dontendword.features.main.MainActivity;
import com.cranevalley.dontendword.features.main.addfriends.AddFriendsFragment;
import com.cranevalley.dontendword.features.main.chat.ChatFragment;
import com.cranevalley.dontendword.features.main.creategame.CreateGameDialogFragment;
import com.cranevalley.dontendword.features.shared.UserInfo;
import com.cranevalley.dontendword.helpers.AudioHelper;
import com.cranevalley.dontendword.helpers.FirebaseHelper;
import com.cranevalley.dontendword.helpers.Utility;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.orhanobut.logger.Logger;
import java.util.ArrayList;
import java.util.HashSet;

public class PlayerFriendsFragment extends BaseFragment implements FriendInfosAdapterListener {
    private static final String FRIEND_USER_INFOS_KEY = "FRIEND_USER_INFOS_KEY";
    private static final String NEW_MESSAGE_USER_IDS_KEY = "NEW_MESSAGE_USER_IDS_KEY";
    @BindView(2131820696)
    LinearLayout emptyNoteLayout;
    private AdapterDataObserver mAdapterDataObserver;
    private FriendInfosAdapter mFriendInfosAdapter;
    private ArrayList<UserInfo> mFriendUserInfosList;
    private HashSet<String> mNewMessageUserIdsSet;
    private ValueEventListener mPlayerFriendsEventListener;
    private DatabaseReference mPlayerFriendsReference;
    private ValueEventListener mPlayerNewMessagesEventListener;
    private DatabaseReference mPlayerNewMessagesReference;
    private Unbinder mUnbinder;
    @BindView(2131820837)
    RecyclerView playerFriendsRecyclerView;

    /* renamed from: com.cranevalley.dontendword.features.main.playerfriends.PlayerFriendsFragment$1 */
    class C06141 implements ValueEventListener {
        C06141() {
        }

        public void onDataChange(DataSnapshot dataSnapshot) {
            PlayerFriendsFragment.this.mNewMessageUserIdsSet.clear();
            if (dataSnapshot.exists()) {
                for (DataSnapshot newMessageUserSnapshot : dataSnapshot.getChildren()) {
                    PlayerFriendsFragment.this.mNewMessageUserIdsSet.add(newMessageUserSnapshot.getKey());
                }
            }
            PlayerFriendsFragment.this.updateDisplayForNewMessageUserIds();
        }

        public void onCancelled(DatabaseError databaseError) {
            Logger.m1220e(databaseError.getMessage(), new Object[0]);
        }
    }

    /* renamed from: com.cranevalley.dontendword.features.main.playerfriends.PlayerFriendsFragment$2 */
    class C06152 implements ValueEventListener {
        C06152() {
        }

        public void onDataChange(DataSnapshot dataSnapshot) {
            PlayerFriendsFragment.this.mFriendUserInfosList.clear();
            if (dataSnapshot.exists()) {
                for (DataSnapshot friendSnapshot : dataSnapshot.getChildren()) {
                    UserInfo userInfo = new UserInfo();
                    userInfo.userId = friendSnapshot.getKey();
                    userInfo.displayName = (String) friendSnapshot.child("displayName").getValue(String.class);
                    userInfo.photoUrl = (String) friendSnapshot.child("photoUrl").getValue(String.class);
                    PlayerFriendsFragment.this.mFriendUserInfosList.add(userInfo);
                }
            }
            PlayerFriendsFragment.this.updateDisplayForFriendInfos();
        }

        public void onCancelled(DatabaseError databaseError) {
            Logger.m1220e(databaseError.getMessage(), new Object[0]);
        }
    }

    /* renamed from: com.cranevalley.dontendword.features.main.playerfriends.PlayerFriendsFragment$3 */
    class C06163 extends AdapterDataObserver {
        C06163() {
        }

        public void onChanged() {
            super.onChanged();
            if (PlayerFriendsFragment.this.mFriendInfosAdapter == null) {
                return;
            }
            if (PlayerFriendsFragment.this.mFriendInfosAdapter.getItemCount() > 0) {
                PlayerFriendsFragment.this.playerFriendsRecyclerView.setVisibility(0);
                PlayerFriendsFragment.this.emptyNoteLayout.setVisibility(8);
                return;
            }
            PlayerFriendsFragment.this.playerFriendsRecyclerView.setVisibility(8);
            PlayerFriendsFragment.this.emptyNoteLayout.setVisibility(0);
        }
    }

    /* renamed from: com.cranevalley.dontendword.features.main.playerfriends.PlayerFriendsFragment$4 */
    class C06174 extends ItemDecoration {
        C06174() {
        }

        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
            int offset = (int) Utility.getPxFromDp(5.0f);
            outRect.set(offset, offset, offset, offset);
        }
    }

    /* renamed from: com.cranevalley.dontendword.features.main.playerfriends.PlayerFriendsFragment$6 */
    class C06196 implements Runnable {
        C06196() {
        }

        public void run() {
            PlayerFriendsFragment.this.mFriendInfosAdapter.setNewMessageUserIdsSet(PlayerFriendsFragment.this.mNewMessageUserIdsSet);
            PlayerFriendsFragment.this.mFriendInfosAdapter.notifyDataSetChanged();
        }
    }

    /* renamed from: com.cranevalley.dontendword.features.main.playerfriends.PlayerFriendsFragment$7 */
    class C06207 implements Runnable {
        C06207() {
        }

        public void run() {
            PlayerFriendsFragment.this.mFriendInfosAdapter.setFriendUserInfosList(PlayerFriendsFragment.this.mFriendUserInfosList);
            PlayerFriendsFragment.this.mFriendInfosAdapter.notifyDataSetChanged();
        }
    }

    public static PlayerFriendsFragment newInstance() {
        return new PlayerFriendsFragment();
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mNewMessageUserIdsSet = new HashSet();
        this.mFriendUserInfosList = new ArrayList();
        if (savedInstanceState != null) {
            this.mNewMessageUserIdsSet = (HashSet) savedInstanceState.getSerializable(NEW_MESSAGE_USER_IDS_KEY);
            this.mFriendUserInfosList = (ArrayList) savedInstanceState.getSerializable(FRIEND_USER_INFOS_KEY);
        }
        this.mPlayerNewMessagesReference = FirebaseHelper.getPlayerNewMessagesReference();
        this.mPlayerFriendsReference = FirebaseHelper.getPlayerFriendsReference();
        this.mPlayerNewMessagesEventListener = new C06141();
        this.mPlayerFriendsEventListener = new C06152();
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(NEW_MESSAGE_USER_IDS_KEY, this.mNewMessageUserIdsSet);
        outState.putSerializable(FRIEND_USER_INFOS_KEY, this.mFriendUserInfosList);
    }

    public void onDestroy() {
        this.mNewMessageUserIdsSet.clear();
        this.mNewMessageUserIdsSet = null;
        this.mFriendUserInfosList.clear();
        this.mFriendUserInfosList = null;
        this.mFriendInfosAdapter.unregisterAdapterDataObserver(this.mAdapterDataObserver);
        this.mFriendInfosAdapter.adapterListener = null;
        this.mFriendInfosAdapter = null;
        this.mPlayerNewMessagesReference = null;
        this.mPlayerFriendsReference = null;
        this.mPlayerNewMessagesEventListener = null;
        this.mPlayerFriendsEventListener = null;
        super.onDestroy();
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(C0521R.layout.player_friends_fragment, container, false);
        this.mUnbinder = ButterKnife.bind((Object) this, rootView);
        this.mAdapterDataObserver = new C06163();
        this.mFriendInfosAdapter = new FriendInfosAdapter(this.mNewMessageUserIdsSet, this.mFriendUserInfosList);
        this.mFriendInfosAdapter.registerAdapterDataObserver(this.mAdapterDataObserver);
        this.mFriendInfosAdapter.adapterListener = this;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(1);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        this.playerFriendsRecyclerView.setLayoutManager(linearLayoutManager);
        this.playerFriendsRecyclerView.setHasFixedSize(true);
        this.playerFriendsRecyclerView.addItemDecoration(new C06174());
        this.playerFriendsRecyclerView.setAdapter(this.mFriendInfosAdapter);
        return rootView;
    }

    public void onDestroyView() {
        this.mUnbinder.unbind();
        super.onDestroyView();
    }

    public void onResume() {
        super.onResume();
        this.mPlayerNewMessagesReference.addValueEventListener(this.mPlayerNewMessagesEventListener);
        this.mPlayerFriendsReference.addValueEventListener(this.mPlayerFriendsEventListener);
    }

    public void onPause() {
        this.mPlayerNewMessagesReference.removeEventListener(this.mPlayerNewMessagesEventListener);
        this.mPlayerFriendsReference.removeEventListener(this.mPlayerFriendsEventListener);
        super.onPause();
    }

    public void onFriendInfosAdapterClickPlay(UserInfo userInfo) {
        AudioHelper.playClickEffect();
        CreateGameDialogFragment createGameFragment = CreateGameDialogFragment.newInstance(userInfo.userId, userInfo.displayName, userInfo.photoUrl);
        createGameFragment.setCancelable(true);
        createGameFragment.show(getFragmentManager(), null);
    }

    public void onFriendInfosAdapterClickChat(UserInfo userInfo) {
        AudioHelper.playClickEffect();
        ((MainActivity) getActivity()).replaceContentFragment(ChatFragment.newInstance(userInfo.userId, userInfo.displayName, userInfo.photoUrl));
    }

    public void onFriendInfosAdapterClickUnfriend(final UserInfo userInfo) {
        AudioHelper.playClickEffect();
        new Builder(getContext()).setMessage("Do you really want to unfriend " + userInfo.displayName + "?").setCancelable(true).setPositiveButton((CharSequence) "Yes", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                FirebaseHelper.removeFriend(userInfo.userId);
            }
        }).setNegativeButton((CharSequence) "No", null).show();
    }

    private void updateDisplayForNewMessageUserIds() {
        getActivity().runOnUiThread(new C06196());
    }

    private void updateDisplayForFriendInfos() {
        getActivity().runOnUiThread(new C06207());
    }

    @OnClick({2131820838})
    void onClickAddFriends(View sender) {
        AudioHelper.playClickEffect();
        ((MainActivity) getActivity()).replaceContentFragment(AddFriendsFragment.newInstance());
    }
}
