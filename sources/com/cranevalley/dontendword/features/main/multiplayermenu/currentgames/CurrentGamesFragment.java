package com.cranevalley.dontendword.features.main.multiplayermenu.currentgames;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.State;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.cranevalley.dontendword.C0521R;
import com.cranevalley.dontendword.features.bases.BaseFragment;
import com.cranevalley.dontendword.features.main.MainActivity;
import com.cranevalley.dontendword.features.main.multiplayergameplay.MultiplayerGamePlayFragment;
import com.cranevalley.dontendword.features.shared.GameInfo;
import com.cranevalley.dontendword.helpers.FirebaseHelper;
import com.cranevalley.dontendword.helpers.Utility;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.orhanobut.logger.Logger;
import java.util.ArrayList;
import java.util.Collections;

public class CurrentGamesFragment extends BaseFragment implements CurrentGameInfosAdapterListener {
    private static final String OPPONENT_TURN_GAME_INFOS_KEY = "OPPONENT_TURN_GAME_INFOS_KEY";
    private static final String PLAYER_TURN_GAME_INFOS_KEY = "PLAYER_TURN_GAME_INFOS_KEY";
    @BindView(2131820723)
    RecyclerView currentGamesRecyclerView;
    private CurrentGameInfosAdapter mCurrentGameInfosAdapter;
    private ArrayList<GameInfo> mOpponentTurnGameInfosList;
    private ValueEventListener mPlayerGamesEventListener;
    private DatabaseReference mPlayerGamesReference;
    private ArrayList<GameInfo> mPlayerTurnGameInfosList;
    private Unbinder mUnbinder;

    /* renamed from: com.cranevalley.dontendword.features.main.multiplayermenu.currentgames.CurrentGamesFragment$1 */
    class C06051 implements ValueEventListener {
        C06051() {
        }

        public void onDataChange(DataSnapshot dataSnapshot) {
            CurrentGamesFragment.this.mPlayerTurnGameInfosList.clear();
            CurrentGamesFragment.this.mOpponentTurnGameInfosList.clear();
            if (dataSnapshot.exists() && dataSnapshot.hasChildren()) {
                for (DataSnapshot dataSnapshotGame : dataSnapshot.getChildren()) {
                    if (((String) dataSnapshotGame.child("result").getValue(String.class)) == null) {
                        GameInfo gameInfo = new GameInfo();
                        gameInfo.gameId = dataSnapshotGame.getKey();
                        gameInfo.opponentUserId = (String) dataSnapshotGame.child("opponentUserId").getValue(String.class);
                        gameInfo.opponentDisplayName = (String) dataSnapshotGame.child("opponentDisplayName").getValue(String.class);
                        gameInfo.opponentPhotoUrl = (String) dataSnapshotGame.child("opponentPhotoUrl").getValue(String.class);
                        gameInfo.letters = (String) dataSnapshotGame.child("letters").getValue(String.class);
                        gameInfo.nudgeTime = (Long) dataSnapshotGame.child("nudgeTime").getValue(Long.class);
                        gameInfo.turn = (Boolean) dataSnapshotGame.child("turn").getValue(Boolean.class);
                        gameInfo.challenged = (Boolean) dataSnapshotGame.child("challenged").getValue(Boolean.class);
                        if (gameInfo.turn == null || !gameInfo.turn.booleanValue()) {
                            CurrentGamesFragment.this.mOpponentTurnGameInfosList.add(gameInfo);
                        } else {
                            CurrentGamesFragment.this.mPlayerTurnGameInfosList.add(gameInfo);
                        }
                    }
                }
            }
            Collections.reverse(CurrentGamesFragment.this.mPlayerTurnGameInfosList);
            Collections.reverse(CurrentGamesFragment.this.mOpponentTurnGameInfosList);
            CurrentGamesFragment.this.handleCurrentGameInfosChange();
        }

        public void onCancelled(DatabaseError databaseError) {
            Logger.m1220e(databaseError.getMessage(), new Object[0]);
        }
    }

    /* renamed from: com.cranevalley.dontendword.features.main.multiplayermenu.currentgames.CurrentGamesFragment$2 */
    class C06062 extends ItemDecoration {
        C06062() {
        }

        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
            int offset = (int) Utility.getPxFromDp(5.0f);
            outRect.set(offset, offset, offset, offset);
        }
    }

    /* renamed from: com.cranevalley.dontendword.features.main.multiplayermenu.currentgames.CurrentGamesFragment$3 */
    class C06073 implements Runnable {
        C06073() {
        }

        public void run() {
            CurrentGamesFragment.this.mCurrentGameInfosAdapter.setPlayerTurnGameInfosList(CurrentGamesFragment.this.mPlayerTurnGameInfosList);
            CurrentGamesFragment.this.mCurrentGameInfosAdapter.setOpponentTurnGameInfosList(CurrentGamesFragment.this.mOpponentTurnGameInfosList);
            CurrentGamesFragment.this.mCurrentGameInfosAdapter.notifyDataSetChanged();
        }
    }

    public static CurrentGamesFragment newInstance() {
        return new CurrentGamesFragment();
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mPlayerTurnGameInfosList = new ArrayList();
        this.mOpponentTurnGameInfosList = new ArrayList();
        if (savedInstanceState != null) {
            this.mPlayerTurnGameInfosList = (ArrayList) savedInstanceState.getSerializable(PLAYER_TURN_GAME_INFOS_KEY);
            this.mOpponentTurnGameInfosList = (ArrayList) savedInstanceState.getSerializable(OPPONENT_TURN_GAME_INFOS_KEY);
        }
        this.mPlayerGamesReference = FirebaseHelper.getPlayerGamesReference();
        this.mPlayerGamesEventListener = new C06051();
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(PLAYER_TURN_GAME_INFOS_KEY, this.mPlayerTurnGameInfosList);
        outState.putSerializable(OPPONENT_TURN_GAME_INFOS_KEY, this.mOpponentTurnGameInfosList);
    }

    public void onDestroy() {
        this.mPlayerTurnGameInfosList.clear();
        this.mOpponentTurnGameInfosList.clear();
        this.mCurrentGameInfosAdapter.adapterListener = null;
        this.mCurrentGameInfosAdapter = null;
        this.mPlayerGamesReference = null;
        this.mPlayerGamesEventListener = null;
        super.onDestroy();
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(C0521R.layout.current_games_fragment, container, false);
        this.mUnbinder = ButterKnife.bind((Object) this, rootView);
        this.mCurrentGameInfosAdapter = new CurrentGameInfosAdapter(this.mPlayerTurnGameInfosList, this.mOpponentTurnGameInfosList);
        this.mCurrentGameInfosAdapter.adapterListener = this;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(1);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        this.currentGamesRecyclerView.setLayoutManager(linearLayoutManager);
        this.currentGamesRecyclerView.setHasFixedSize(true);
        this.currentGamesRecyclerView.addItemDecoration(new C06062());
        this.currentGamesRecyclerView.setAdapter(this.mCurrentGameInfosAdapter);
        return rootView;
    }

    public void onDestroyView() {
        this.mUnbinder.unbind();
        super.onDestroyView();
    }

    public void onResume() {
        super.onResume();
        this.mPlayerGamesReference.orderByChild("nudgeTime").addValueEventListener(this.mPlayerGamesEventListener);
    }

    public void onPause() {
        this.mPlayerGamesReference.removeEventListener(this.mPlayerGamesEventListener);
        super.onPause();
    }

    public void onCurrentGameInfosAdapterSelectGame(GameInfo gameInfo) {
        ((MainActivity) getActivity()).replaceContentFragment(MultiplayerGamePlayFragment.newInstance(gameInfo));
    }

    private void handleCurrentGameInfosChange() {
        getActivity().runOnUiThread(new C06073());
    }
}
