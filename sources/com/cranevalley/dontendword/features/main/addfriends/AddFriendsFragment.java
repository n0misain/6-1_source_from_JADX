package com.cranevalley.dontendword.features.main.addfriends;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.AdapterDataObserver;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.State;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import butterknife.Unbinder;
import com.cranevalley.dontendword.C0521R;
import com.cranevalley.dontendword.features.bases.BaseFragment;
import com.cranevalley.dontendword.features.shared.UserInfo;
import com.cranevalley.dontendword.helpers.AudioHelper;
import com.cranevalley.dontendword.helpers.FirebaseHelper;
import com.cranevalley.dontendword.helpers.Utility;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.orhanobut.logger.Logger;
import java.util.ArrayList;
import java.util.HashSet;

public class AddFriendsFragment extends BaseFragment implements AddFriendInfosAdapterListener {
    private static final String ALL_USER_INFOS_KEY = "ALL_USER_INFOS_KEY";
    private static final String FRIEND_USER_IDS_KEY = "FRIEND_USER_IDS_KEY";
    @BindView(2131820695)
    RecyclerView addFriendsRecyclerView;
    @BindView(2131820696)
    LinearLayout emptyNoteLayout;
    private AdapterDataObserver mAdapterDataObserver;
    private AddFriendInfosAdapter mAddFriendInfosAdapter;
    private ArrayList<UserInfo> mAllUserInfosList;
    private DatabaseReference mAllUserInfosReference;
    private ValueEventListener mAllUserInfosValueEventListener;
    private HashSet<String> mFriendUserIdsSet;
    private DatabaseReference mPlayerFriendsReference;
    private ValueEventListener mPlayerFriendsValueEventListener;
    private Unbinder mUnbinder;
    @BindView(2131820693)
    AppCompatEditText searchUsersEditText;

    /* renamed from: com.cranevalley.dontendword.features.main.addfriends.AddFriendsFragment$1 */
    class C05351 implements ValueEventListener {
        C05351() {
        }

        public void onDataChange(DataSnapshot dataSnapshot) {
            AddFriendsFragment.this.mFriendUserIdsSet.clear();
            if (dataSnapshot.exists()) {
                for (DataSnapshot friendSnapshot : dataSnapshot.getChildren()) {
                    AddFriendsFragment.this.mFriendUserIdsSet.add(friendSnapshot.getKey());
                }
            }
            AddFriendsFragment.this.handleFriendUserIdsChange();
        }

        public void onCancelled(DatabaseError databaseError) {
            Logger.m1220e(databaseError.getMessage(), new Object[0]);
        }
    }

    /* renamed from: com.cranevalley.dontendword.features.main.addfriends.AddFriendsFragment$3 */
    class C05373 extends AdapterDataObserver {
        C05373() {
        }

        public void onChanged() {
            super.onChanged();
            if (AddFriendsFragment.this.mAddFriendInfosAdapter == null) {
                return;
            }
            if (AddFriendsFragment.this.mAddFriendInfosAdapter.getItemCount() > 0) {
                AddFriendsFragment.this.addFriendsRecyclerView.setVisibility(0);
                AddFriendsFragment.this.emptyNoteLayout.setVisibility(8);
                return;
            }
            AddFriendsFragment.this.addFriendsRecyclerView.setVisibility(8);
            AddFriendsFragment.this.emptyNoteLayout.setVisibility(0);
        }
    }

    /* renamed from: com.cranevalley.dontendword.features.main.addfriends.AddFriendsFragment$4 */
    class C05384 extends ItemDecoration {
        C05384() {
        }

        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
            int offset = (int) Utility.getPxFromDp(5.0f);
            outRect.set(offset, offset, offset, offset);
        }
    }

    /* renamed from: com.cranevalley.dontendword.features.main.addfriends.AddFriendsFragment$5 */
    class C05395 implements Runnable {
        C05395() {
        }

        public void run() {
            AddFriendsFragment.this.mAddFriendInfosAdapter.setFriendUserIdsSet(AddFriendsFragment.this.mFriendUserIdsSet);
            AddFriendsFragment.this.mAddFriendInfosAdapter.notifyDataSetChanged();
        }
    }

    /* renamed from: com.cranevalley.dontendword.features.main.addfriends.AddFriendsFragment$6 */
    class C05406 implements Runnable {
        C05406() {
        }

        public void run() {
            AddFriendsFragment.this.mAddFriendInfosAdapter.setAllUserInfosList(AddFriendsFragment.this.mAllUserInfosList);
            AddFriendsFragment.this.mAddFriendInfosAdapter.notifyDataSetChanged();
        }
    }

    public static AddFriendsFragment newInstance() {
        return new AddFriendsFragment();
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mFriendUserIdsSet = new HashSet();
        this.mAllUserInfosList = new ArrayList();
        if (savedInstanceState != null) {
            this.mFriendUserIdsSet = (HashSet) savedInstanceState.getSerializable(FRIEND_USER_IDS_KEY);
            this.mAllUserInfosList = (ArrayList) savedInstanceState.getSerializable(ALL_USER_INFOS_KEY);
        }
        this.mPlayerFriendsReference = FirebaseHelper.getPlayerFriendsReference();
        this.mAllUserInfosReference = FirebaseHelper.getAllUserInfosReference();
        this.mPlayerFriendsValueEventListener = new C05351();
        final String playerUserId = FirebaseHelper.getUserId(FirebaseAuth.getInstance().getCurrentUser());
        this.mAllUserInfosValueEventListener = new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                AddFriendsFragment.this.mAllUserInfosList.clear();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                        UserInfo userInfo = new UserInfo();
                        userInfo.userId = userSnapshot.getKey();
                        userInfo.displayName = (String) userSnapshot.child("displayName").getValue(String.class);
                        userInfo.photoUrl = (String) userSnapshot.child("photoUrl").getValue(String.class);
                        if (!userInfo.userId.equals(playerUserId)) {
                            AddFriendsFragment.this.mAllUserInfosList.add(userInfo);
                        }
                    }
                }
                AddFriendsFragment.this.handleAllUserInfosChange();
            }

            public void onCancelled(DatabaseError databaseError) {
                Logger.m1220e(databaseError.getMessage(), new Object[0]);
            }
        };
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(FRIEND_USER_IDS_KEY, this.mFriendUserIdsSet);
        outState.putSerializable(ALL_USER_INFOS_KEY, this.mAllUserInfosList);
    }

    public void onDestroy() {
        this.mFriendUserIdsSet.clear();
        this.mFriendUserIdsSet = null;
        this.mAllUserInfosList.clear();
        this.mAllUserInfosList = null;
        this.mAddFriendInfosAdapter.unregisterAdapterDataObserver(this.mAdapterDataObserver);
        this.mAddFriendInfosAdapter.adapterListener = null;
        this.mAddFriendInfosAdapter = null;
        this.mPlayerFriendsReference = null;
        this.mAllUserInfosReference = null;
        this.mPlayerFriendsValueEventListener = null;
        this.mAllUserInfosValueEventListener = null;
        super.onDestroy();
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(C0521R.layout.add_friends_fragment, container, false);
        this.mUnbinder = ButterKnife.bind((Object) this, rootView);
        this.mAdapterDataObserver = new C05373();
        this.mAddFriendInfosAdapter = new AddFriendInfosAdapter(this.searchUsersEditText.getText().toString(), this.mFriendUserIdsSet, this.mAllUserInfosList);
        this.mAddFriendInfosAdapter.registerAdapterDataObserver(this.mAdapterDataObserver);
        this.mAddFriendInfosAdapter.adapterListener = this;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(1);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        this.addFriendsRecyclerView.setLayoutManager(linearLayoutManager);
        this.addFriendsRecyclerView.setHasFixedSize(true);
        this.addFriendsRecyclerView.addItemDecoration(new C05384());
        this.addFriendsRecyclerView.setAdapter(this.mAddFriendInfosAdapter);
        return rootView;
    }

    public void onDestroyView() {
        this.mUnbinder.unbind();
        super.onDestroyView();
    }

    public void onResume() {
        super.onResume();
        this.mPlayerFriendsReference.addValueEventListener(this.mPlayerFriendsValueEventListener);
        this.mAllUserInfosReference.orderByChild("displayName").addValueEventListener(this.mAllUserInfosValueEventListener);
    }

    public void onPause() {
        this.mPlayerFriendsReference.removeEventListener(this.mPlayerFriendsValueEventListener);
        this.mAllUserInfosReference.removeEventListener(this.mAllUserInfosValueEventListener);
        super.onPause();
    }

    public void onAddFriendInfosAdapterAddFriend(UserInfo userInfo) {
        AudioHelper.playClickEffect();
        FirebaseHelper.addFriend(userInfo.userId, userInfo.displayName, userInfo.photoUrl);
    }

    private void handleFriendUserIdsChange() {
        getActivity().runOnUiThread(new C05395());
    }

    private void handleAllUserInfosChange() {
        getActivity().runOnUiThread(new C05406());
    }

    private boolean validateSearchText() {
        if (this.searchUsersEditText.getText().toString().length() < 2) {
            this.searchUsersEditText.setError("Minimum 2 characters");
            return false;
        }
        this.searchUsersEditText.setError(null);
        return true;
    }

    @OnEditorAction({2131820693})
    boolean onEditorActionSearchUsers(TextView sender, int actionId, KeyEvent event) {
        if (actionId == 6 && validateSearchText()) {
            this.mAddFriendInfosAdapter.setSearchText(this.searchUsersEditText.getText().toString());
            this.mAddFriendInfosAdapter.notifyDataSetChanged();
        }
        return false;
    }

    @OnClick({2131820694})
    void onClickSearchUsers(View sender) {
        AudioHelper.playClickEffect();
        if (validateSearchText()) {
            this.mAddFriendInfosAdapter.setSearchText(this.searchUsersEditText.getText().toString());
            this.mAddFriendInfosAdapter.notifyDataSetChanged();
        }
    }
}
