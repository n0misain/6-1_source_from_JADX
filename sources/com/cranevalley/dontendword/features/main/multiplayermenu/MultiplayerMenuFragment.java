package com.cranevalley.dontendword.features.main.multiplayermenu;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.cranevalley.dontendword.C0521R;
import com.cranevalley.dontendword.features.bases.BaseFragment;
import com.cranevalley.dontendword.features.main.MainActivity;
import com.cranevalley.dontendword.features.main.creategame.CreateGameDialogFragment;
import com.cranevalley.dontendword.features.main.multiplayergameplay.MultiplayerGamePlayFragment;
import com.cranevalley.dontendword.features.main.playerfriends.PlayerFriendsFragment;
import com.cranevalley.dontendword.features.shared.GameInfo;
import com.cranevalley.dontendword.helpers.AudioHelper;
import com.cranevalley.dontendword.helpers.FirebaseHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.orhanobut.logger.Logger;

public class MultiplayerMenuFragment extends BaseFragment {
    @BindView(2131820810)
    ProgressBar findRandomProgressBar;
    private PlayerGamesPagerAdapter mPlayerGamesPagerAdapter;
    private Unbinder mUnbinder;
    @BindView(2131820808)
    LinearLayout playFriendLayout;
    @BindView(2131820807)
    LinearLayout playRandomLayout;
    @BindView(2131820805)
    TabLayout playerGamesTabLayout;
    @BindView(2131820809)
    ViewPager playerGamesViewPager;

    public static MultiplayerMenuFragment newInstance() {
        return new MultiplayerMenuFragment();
    }

    public void onDestroy() {
        this.mPlayerGamesPagerAdapter = null;
        super.onDestroy();
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(C0521R.layout.multiplayer_menu_fragment, container, false);
        this.mUnbinder = ButterKnife.bind((Object) this, rootView);
        this.mPlayerGamesPagerAdapter = new PlayerGamesPagerAdapter(getChildFragmentManager());
        this.playerGamesViewPager.setAdapter(this.mPlayerGamesPagerAdapter);
        this.playerGamesTabLayout.setupWithViewPager(this.playerGamesViewPager);
        return rootView;
    }

    public void onDestroyView() {
        this.mUnbinder.unbind();
        super.onDestroyView();
    }

    @OnClick({2131820807})
    void onClickPlayRandom(View sender) {
        AudioHelper.playClickEffect();
        this.findRandomProgressBar.setVisibility(0);
        this.playRandomLayout.setEnabled(false);
        this.playFriendLayout.setEnabled(false);
        final String playerUserId = FirebaseHelper.getUserId(FirebaseAuth.getInstance().getCurrentUser());
        FirebaseHelper.getRandomGamesReference().addListenerForSingleValueEvent(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean gameFound = false;
                for (DataSnapshot gameSnapshot : dataSnapshot.getChildren()) {
                    String opponentUserId = (String) gameSnapshot.child("opponentUserId").getValue(String.class);
                    if (opponentUserId != null && !opponentUserId.equals(playerUserId)) {
                        gameFound = true;
                        final GameInfo gameInfo = new GameInfo();
                        gameInfo.gameId = gameSnapshot.getKey();
                        gameInfo.opponentUserId = opponentUserId;
                        gameInfo.opponentDisplayName = (String) gameSnapshot.child("opponentDisplayName").getValue(String.class);
                        gameInfo.opponentPhotoUrl = (String) gameSnapshot.child("opponentPhotoUrl").getValue(String.class);
                        gameInfo.letters = (String) gameSnapshot.child("letters").getValue(String.class);
                        gameInfo.turn = Boolean.valueOf(false);
                        FirebaseHelper.joinRandomGame(gameInfo.gameId, gameInfo.opponentUserId, gameInfo.opponentDisplayName, gameInfo.opponentPhotoUrl, gameInfo.letters).addOnCompleteListener(MultiplayerMenuFragment.this.getActivity(), new OnCompleteListener<Void>() {
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    MultiplayerMenuFragment.this.findRandomProgressBar.setVisibility(8);
                                    MultiplayerMenuFragment.this.playRandomLayout.setEnabled(true);
                                    MultiplayerMenuFragment.this.playFriendLayout.setEnabled(true);
                                    ((MainActivity) MultiplayerMenuFragment.this.getActivity()).replaceContentFragment(MultiplayerGamePlayFragment.newInstance(gameInfo));
                                }
                            }
                        });
                        break;
                    }
                }
                if (!gameFound) {
                    MultiplayerMenuFragment.this.findRandomProgressBar.setVisibility(8);
                    MultiplayerMenuFragment.this.playRandomLayout.setEnabled(true);
                    MultiplayerMenuFragment.this.playFriendLayout.setEnabled(true);
                    CreateGameDialogFragment createGameFragment = CreateGameDialogFragment.newInstance();
                    createGameFragment.setCancelable(true);
                    createGameFragment.show(MultiplayerMenuFragment.this.getFragmentManager(), null);
                }
            }

            public void onCancelled(DatabaseError databaseError) {
                Logger.m1220e(databaseError.getMessage(), new Object[0]);
            }
        });
    }

    @OnClick({2131820808})
    void onClickPlayFriend(View sender) {
        AudioHelper.playClickEffect();
        ((MainActivity) getActivity()).replaceContentFragment(PlayerFriendsFragment.newInstance());
    }
}
