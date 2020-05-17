package com.cranevalley.dontendword.features.main.multiplayermenu.finishedgames;

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

public class FinishedGamesFragment extends BaseFragment implements FinishedGameInfosAdapterListener {
    private static final String FINISHED_GAME_INFOS_KEY = "FINISHED_GAME_INFOS_KEY";
    @BindView(2131820743)
    RecyclerView finishedGamesRecyclerView;
    private FinishedGameInfosAdapter mFinishedGameInfosAdapter;
    private ArrayList<GameInfo> mFinishedGameInfosList;
    private ValueEventListener mPlayerGamesEventListener;
    private DatabaseReference mPlayerGamesReference;
    private Unbinder mUnbinder;

    /* renamed from: com.cranevalley.dontendword.features.main.multiplayermenu.finishedgames.FinishedGamesFragment$1 */
    class C06081 implements ValueEventListener {
        C06081() {
        }

        public void onDataChange(DataSnapshot dataSnapshot) {
            FinishedGamesFragment.this.mFinishedGameInfosList.clear();
            if (dataSnapshot.exists() && dataSnapshot.hasChildren()) {
                for (DataSnapshot dataSnapshotGame : dataSnapshot.getChildren()) {
                    String result = (String) dataSnapshotGame.child("result").getValue(String.class);
                    if (result != null) {
                        GameInfo gameInfo = new GameInfo();
                        gameInfo.gameId = dataSnapshotGame.getKey();
                        gameInfo.opponentUserId = (String) dataSnapshotGame.child("opponentUserId").getValue(String.class);
                        gameInfo.opponentDisplayName = (String) dataSnapshotGame.child("opponentDisplayName").getValue(String.class);
                        gameInfo.opponentPhotoUrl = (String) dataSnapshotGame.child("opponentPhotoUrl").getValue(String.class);
                        gameInfo.letters = (String) dataSnapshotGame.child("letters").getValue(String.class);
                        gameInfo.nudgeTime = (Long) dataSnapshotGame.child("nudgeTime").getValue(Long.class);
                        gameInfo.turn = (Boolean) dataSnapshotGame.child("turn").getValue(Boolean.class);
                        gameInfo.challenged = (Boolean) dataSnapshotGame.child("challenged").getValue(Boolean.class);
                        gameInfo.result = result;
                        gameInfo.report = (String) dataSnapshotGame.child("report").getValue(String.class);
                        FinishedGamesFragment.this.mFinishedGameInfosList.add(gameInfo);
                    }
                }
            }
            Collections.reverse(FinishedGamesFragment.this.mFinishedGameInfosList);
            FinishedGamesFragment.this.handleFinishedGameInfosChange();
        }

        public void onCancelled(DatabaseError databaseError) {
            Logger.m1220e(databaseError.getMessage(), new Object[0]);
        }
    }

    /* renamed from: com.cranevalley.dontendword.features.main.multiplayermenu.finishedgames.FinishedGamesFragment$2 */
    class C06092 extends ItemDecoration {
        C06092() {
        }

        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
            int offset = (int) Utility.getPxFromDp(5.0f);
            outRect.set(offset, offset, offset, offset);
        }
    }

    /* renamed from: com.cranevalley.dontendword.features.main.multiplayermenu.finishedgames.FinishedGamesFragment$3 */
    class C06103 implements Runnable {
        C06103() {
        }

        public void run() {
            FinishedGamesFragment.this.mFinishedGameInfosAdapter.setFinishedGameInfosList(FinishedGamesFragment.this.mFinishedGameInfosList);
            FinishedGamesFragment.this.mFinishedGameInfosAdapter.notifyDataSetChanged();
        }
    }

    public static FinishedGamesFragment newInstance() {
        return new FinishedGamesFragment();
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mFinishedGameInfosList = new ArrayList();
        if (savedInstanceState != null) {
            this.mFinishedGameInfosList = (ArrayList) savedInstanceState.getSerializable(FINISHED_GAME_INFOS_KEY);
        }
        this.mPlayerGamesReference = FirebaseHelper.getPlayerGamesReference();
        this.mPlayerGamesEventListener = new C06081();
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(FINISHED_GAME_INFOS_KEY, this.mFinishedGameInfosList);
    }

    public void onDestroy() {
        this.mFinishedGameInfosList.clear();
        this.mFinishedGameInfosAdapter.adapterListener = null;
        this.mFinishedGameInfosAdapter = null;
        this.mPlayerGamesReference = null;
        this.mPlayerGamesEventListener = null;
        super.onDestroy();
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(C0521R.layout.finished_games_fragment, container, false);
        this.mUnbinder = ButterKnife.bind((Object) this, rootView);
        this.mFinishedGameInfosAdapter = new FinishedGameInfosAdapter(this.mFinishedGameInfosList);
        this.mFinishedGameInfosAdapter.adapterListener = this;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(1);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        this.finishedGamesRecyclerView.setLayoutManager(linearLayoutManager);
        this.finishedGamesRecyclerView.setHasFixedSize(true);
        this.finishedGamesRecyclerView.addItemDecoration(new C06092());
        this.finishedGamesRecyclerView.setAdapter(this.mFinishedGameInfosAdapter);
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

    public void onFinishedGameInfosAdapterSelectGame(GameInfo gameInfo) {
        ((MainActivity) getActivity()).replaceContentFragment(MultiplayerGamePlayFragment.newInstance(gameInfo));
    }

    private void handleFinishedGameInfosChange() {
        getActivity().runOnUiThread(new C06103());
    }
}
