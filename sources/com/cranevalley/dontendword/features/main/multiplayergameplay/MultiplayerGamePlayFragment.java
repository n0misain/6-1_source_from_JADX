package com.cranevalley.dontendword.features.main.multiplayergameplay;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.State;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.cranevalley.dontendword.C0521R;
import com.cranevalley.dontendword.features.bases.BaseFragment;
import com.cranevalley.dontendword.features.main.MainActivity;
import com.cranevalley.dontendword.features.main.chat.ChatFragment;
import com.cranevalley.dontendword.features.main.creategame.CreateGameDialogFragment;
import com.cranevalley.dontendword.features.main.defendchallenge.DefendChallengeDialogFragment;
import com.cranevalley.dontendword.features.main.defendchallenge.DefendChallengeFragmentListener;
import com.cranevalley.dontendword.features.shared.CapitalAlphabetKeyboardDialogFragment;
import com.cranevalley.dontendword.features.shared.CapitalAlphabetKeyboardFragmentListener;
import com.cranevalley.dontendword.features.shared.GameInfo;
import com.cranevalley.dontendword.features.shared.WordInfo;
import com.cranevalley.dontendword.features.shared.lettersadapter.LettersAdapter;
import com.cranevalley.dontendword.features.shared.lettersadapter.LettersAdapterListener;
import com.cranevalley.dontendword.helpers.AppConstant;
import com.cranevalley.dontendword.helpers.AudioHelper;
import com.cranevalley.dontendword.helpers.FirebaseHelper;
import com.cranevalley.dontendword.helpers.Utility;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.makeramen.roundedimageview.RoundedImageView;
import com.orhanobut.logger.Logger;
import com.squareup.picasso.Picasso;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressLint({"SetTextI18n"})
public class MultiplayerGamePlayFragment extends BaseFragment implements LettersAdapterListener, DefendChallengeFragmentListener, CapitalAlphabetKeyboardFragmentListener {
    private static final String BACK_LETTER_KEY = "BACK_LETTER_KEY";
    private static final String CHALLENGED_KEY = "CHALLENGED_KEY";
    private static final String FIXED_LETTERS_KEY = "FIXED_LETTERS_KEY";
    private static final String FRIEND_KEY = "FRIEND_KEY";
    private static final String FRONT_LETTER_KEY = "FRONT_LETTER_KEY";
    private static final String GAME_ID_KEY = "GAME_ID_KEY";
    private static final String NUDGE_TIME_KEY = "NUDGE_TIME_KEY";
    private static final String OPPONENT_DISPLAY_NAME_KEY = "OPPONENT_DISPLAY_NAME_KEY";
    private static final String OPPONENT_PHOTO_URL_KEY = "OPPONENT_PHOTO_URL_KEY";
    private static final String OPPONENT_USER_ID_KEY = "OPPONENT_USER_ID_KEY";
    private static final String REPORT_KEY = "REPORT_KEY";
    private static final String RESULT_KEY = "RESULT_KEY";
    private static final String SERVER_TIME_OFFSET_KEY = "SERVER_TIME_OFFSET_KEY";
    private static final String TIME_SINCE_NUDGE_KEY = "TIME_SINCE_NUDGE_KEY";
    private static final String TURN_KEY = "TURN_KEY";
    @BindView(2131820690)
    AppCompatImageButton addFriendButton;
    @BindView(2131820721)
    AppCompatButton addLetterButton;
    @BindView(2131820799)
    AppCompatButton challengeButton;
    @BindView(2131820748)
    AppCompatImageButton chatButton;
    @BindView(2131820801)
    AppCompatButton claimWinButton;
    @BindView(2131820800)
    AppCompatButton defendButton;
    @BindView(2131820691)
    AppCompatImageView friendIndicatorImageView;
    @BindView(2131820796)
    ProgressBar gamePlayProgressBar;
    @BindView(2131820719)
    RecyclerView lettersRecyclerView;
    private String mBackLetter;
    private Boolean mChallenged;
    private String mFixedLetters;
    private Boolean mFriend;
    private String mFrontLetter;
    private String mGameId;
    private LettersAdapter mLettersAdapter;
    private Long mNudgeTime;
    private String mOpponentDisplayName;
    private String mOpponentPhotoUrl;
    private String mOpponentUserId;
    private ValueEventListener mPlayerFriendsEventListener;
    private DatabaseReference mPlayerFriendsReference;
    private ValueEventListener mPlayerGameEventListener;
    private DatabaseReference mPlayerGameReference;
    private String mReport;
    private String mResult;
    private Long mServerTimeOffset;
    private ValueEventListener mServerTimeOffsetEventListener;
    private DatabaseReference mServerTimeOffsetReference;
    private Long mTimeSinceNudge;
    private Boolean mTurn;
    private Unbinder mUnbinder;
    private Timer mUpdateTimeTimer;
    @BindView(2131820797)
    AppCompatButton nudgeButton;
    @BindView(2131820717)
    AppCompatTextView opponentDisplayNameTextView;
    @BindView(2131820716)
    RoundedImageView opponentPhotoImageView;
    @BindView(2131820798)
    AppCompatButton rematchButton;
    @BindView(2131820718)
    AppCompatTextView reportTextView;
    @BindView(2131820802)
    AppCompatButton shareResultButton;
    @BindView(2131820795)
    AppCompatTextView statusTextView;
    @BindView(2131820803)
    AppCompatTextView timeSinceNudgeTextView;

    /* renamed from: com.cranevalley.dontendword.features.main.multiplayergameplay.MultiplayerGamePlayFragment$1 */
    class C05791 implements ValueEventListener {
        C05791() {
        }

        public void onDataChange(DataSnapshot dataSnapshot) {
            if (dataSnapshot.exists()) {
                MultiplayerGamePlayFragment.this.mOpponentUserId = (String) dataSnapshot.child("opponentUserId").getValue(String.class);
                MultiplayerGamePlayFragment.this.mOpponentDisplayName = (String) dataSnapshot.child("opponentDisplayName").getValue(String.class);
                MultiplayerGamePlayFragment.this.mOpponentPhotoUrl = (String) dataSnapshot.child("opponentPhotoUrl").getValue(String.class);
                MultiplayerGamePlayFragment.this.mFixedLetters = (String) dataSnapshot.child("letters").getValue(String.class);
                MultiplayerGamePlayFragment.this.mResult = (String) dataSnapshot.child("result").getValue(String.class);
                MultiplayerGamePlayFragment.this.mReport = (String) dataSnapshot.child("report").getValue(String.class);
                MultiplayerGamePlayFragment.this.mTurn = (Boolean) dataSnapshot.child("turn").getValue(Boolean.class);
                MultiplayerGamePlayFragment.this.mChallenged = (Boolean) dataSnapshot.child("challenged").getValue(Boolean.class);
                MultiplayerGamePlayFragment.this.mNudgeTime = (Long) dataSnapshot.child("nudgeTime").getValue(Long.class);
            }
            if (MultiplayerGamePlayFragment.this.mNudgeTime == null || MultiplayerGamePlayFragment.this.mServerTimeOffset == null) {
                MultiplayerGamePlayFragment.this.mTimeSinceNudge = null;
            } else {
                MultiplayerGamePlayFragment.this.mTimeSinceNudge = Long.valueOf((System.currentTimeMillis() + MultiplayerGamePlayFragment.this.mServerTimeOffset.longValue()) - MultiplayerGamePlayFragment.this.mNudgeTime.longValue());
            }
            MultiplayerGamePlayFragment.this.updateLettersAdapter();
            MultiplayerGamePlayFragment.this.updateOpponentDisplayNameTextView();
            MultiplayerGamePlayFragment.this.updateStatusTextView();
            MultiplayerGamePlayFragment.this.updateReportTextView();
            MultiplayerGamePlayFragment.this.updateTimeSinceNudgeTextView();
            MultiplayerGamePlayFragment.this.updateOpponentPhotoImageView();
            MultiplayerGamePlayFragment.this.updateAddLetterButton();
            MultiplayerGamePlayFragment.this.updateChallengeButton();
            MultiplayerGamePlayFragment.this.updateDefendButton();
            MultiplayerGamePlayFragment.this.updateNudgeButton();
            MultiplayerGamePlayFragment.this.updateClaimWinButton();
            MultiplayerGamePlayFragment.this.updateRematchButton();
            MultiplayerGamePlayFragment.this.updateShareResultButton();
            MultiplayerGamePlayFragment.this.updateAddFriendButton();
            MultiplayerGamePlayFragment.this.updateChatButton();
        }

        public void onCancelled(DatabaseError databaseError) {
            Logger.m1220e(databaseError.getMessage(), new Object[0]);
        }
    }

    /* renamed from: com.cranevalley.dontendword.features.main.multiplayergameplay.MultiplayerGamePlayFragment$2 */
    class C05802 implements ValueEventListener {
        C05802() {
        }

        public void onDataChange(DataSnapshot dataSnapshot) {
            MultiplayerGamePlayFragment.this.mFriend = Boolean.valueOf(false);
            if (MultiplayerGamePlayFragment.this.mOpponentUserId != null) {
                for (DataSnapshot friendSnapshot : dataSnapshot.getChildren()) {
                    if (friendSnapshot.getKey().equals(MultiplayerGamePlayFragment.this.mOpponentUserId)) {
                        MultiplayerGamePlayFragment.this.mFriend = Boolean.valueOf(true);
                        break;
                    }
                }
            }
            MultiplayerGamePlayFragment.this.updateFriendIndicatorImageView();
            MultiplayerGamePlayFragment.this.updateAddFriendButton();
        }

        public void onCancelled(DatabaseError databaseError) {
            Logger.m1220e(databaseError.getMessage(), new Object[0]);
        }
    }

    /* renamed from: com.cranevalley.dontendword.features.main.multiplayergameplay.MultiplayerGamePlayFragment$3 */
    class C05813 implements ValueEventListener {
        C05813() {
        }

        public void onDataChange(DataSnapshot dataSnapshot) {
            MultiplayerGamePlayFragment.this.mServerTimeOffset = (Long) dataSnapshot.getValue(Long.class);
            if (MultiplayerGamePlayFragment.this.mNudgeTime == null || MultiplayerGamePlayFragment.this.mServerTimeOffset == null) {
                MultiplayerGamePlayFragment.this.mTimeSinceNudge = null;
            } else {
                MultiplayerGamePlayFragment.this.mTimeSinceNudge = Long.valueOf((System.currentTimeMillis() + MultiplayerGamePlayFragment.this.mServerTimeOffset.longValue()) - MultiplayerGamePlayFragment.this.mNudgeTime.longValue());
            }
            MultiplayerGamePlayFragment.this.updateTimeSinceNudgeTextView();
        }

        public void onCancelled(DatabaseError databaseError) {
            Logger.m1220e(databaseError.getMessage(), new Object[0]);
        }
    }

    /* renamed from: com.cranevalley.dontendword.features.main.multiplayergameplay.MultiplayerGamePlayFragment$4 */
    class C05824 extends ItemDecoration {
        C05824() {
        }

        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
            int offset = (int) Utility.getPxFromDp(1.0f);
            outRect.set(offset, offset, offset, offset);
        }
    }

    /* renamed from: com.cranevalley.dontendword.features.main.multiplayergameplay.MultiplayerGamePlayFragment$5 */
    class C05845 extends TimerTask {

        /* renamed from: com.cranevalley.dontendword.features.main.multiplayergameplay.MultiplayerGamePlayFragment$5$1 */
        class C05831 implements Runnable {
            C05831() {
            }

            public void run() {
                MultiplayerGamePlayFragment.this.updateTimeSinceNudgeTextView();
            }
        }

        C05845() {
        }

        public void run() {
            if (MultiplayerGamePlayFragment.this.mNudgeTime == null || MultiplayerGamePlayFragment.this.mServerTimeOffset == null) {
                MultiplayerGamePlayFragment.this.mTimeSinceNudge = null;
            } else {
                MultiplayerGamePlayFragment.this.mTimeSinceNudge = Long.valueOf((System.currentTimeMillis() + MultiplayerGamePlayFragment.this.mServerTimeOffset.longValue()) - MultiplayerGamePlayFragment.this.mNudgeTime.longValue());
            }
            MultiplayerGamePlayFragment.this.getActivity().runOnUiThread(new C05831());
        }
    }

    /* renamed from: com.cranevalley.dontendword.features.main.multiplayergameplay.MultiplayerGamePlayFragment$7 */
    class C05877 implements OnCompleteListener<Void> {
        C05877() {
        }

        public void onComplete(@NonNull Task<Void> task) {
            MultiplayerGamePlayFragment.this.gamePlayProgressBar.setVisibility(8);
        }
    }

    /* renamed from: com.cranevalley.dontendword.features.main.multiplayergameplay.MultiplayerGamePlayFragment$8 */
    class C05908 implements Callback<List<WordInfo>> {

        /* renamed from: com.cranevalley.dontendword.features.main.multiplayergameplay.MultiplayerGamePlayFragment$8$1 */
        class C05881 implements OnCompleteListener<Void> {
            C05881() {
            }

            public void onComplete(@NonNull Task<Void> task) {
                MultiplayerGamePlayFragment.this.gamePlayProgressBar.setVisibility(8);
            }
        }

        /* renamed from: com.cranevalley.dontendword.features.main.multiplayergameplay.MultiplayerGamePlayFragment$8$2 */
        class C05892 implements OnCompleteListener<Void> {
            C05892() {
            }

            public void onComplete(@NonNull Task<Void> task) {
                MultiplayerGamePlayFragment.this.gamePlayProgressBar.setVisibility(8);
            }
        }

        C05908() {
        }

        public void onResponse(Call<List<WordInfo>> call, Response<List<WordInfo>> response) {
            List<WordInfo> wordInfosList = (List) response.body();
            if (wordInfosList.size() > 0) {
                FirebaseHelper.claimWinWithDefinition(MultiplayerGamePlayFragment.this.mGameId, ((WordInfo) wordInfosList.get(0)).getDefinition()).addOnCompleteListener(MultiplayerGamePlayFragment.this.getActivity(), new C05881());
            } else {
                FirebaseHelper.sendChallenge(MultiplayerGamePlayFragment.this.mGameId).addOnCompleteListener(MultiplayerGamePlayFragment.this.getActivity(), new C05892());
            }
        }

        public void onFailure(Call<List<WordInfo>> call, Throwable throwable) {
            Logger.m1220e(throwable.getLocalizedMessage(), new Object[0]);
        }
    }

    /* renamed from: com.cranevalley.dontendword.features.main.multiplayergameplay.MultiplayerGamePlayFragment$9 */
    class C05919 implements OnCompleteListener<Void> {
        C05919() {
        }

        public void onComplete(@NonNull Task<Void> task) {
            MultiplayerGamePlayFragment.this.gamePlayProgressBar.setVisibility(8);
        }
    }

    public static MultiplayerGamePlayFragment newInstance(GameInfo gameInfo) {
        Bundle bundle = new Bundle();
        if (gameInfo.gameId != null) {
            bundle.putString(GAME_ID_KEY, gameInfo.gameId);
        }
        if (gameInfo.opponentUserId != null) {
            bundle.putString(OPPONENT_USER_ID_KEY, gameInfo.opponentUserId);
        }
        if (gameInfo.opponentDisplayName != null) {
            bundle.putString(OPPONENT_DISPLAY_NAME_KEY, gameInfo.opponentDisplayName);
        }
        if (gameInfo.opponentPhotoUrl != null) {
            bundle.putString(OPPONENT_PHOTO_URL_KEY, gameInfo.opponentPhotoUrl);
        }
        if (gameInfo.letters != null) {
            bundle.putString(FIXED_LETTERS_KEY, gameInfo.letters);
        }
        if (gameInfo.turn != null) {
            bundle.putBoolean(TURN_KEY, gameInfo.turn.booleanValue());
        }
        if (gameInfo.challenged != null) {
            bundle.putBoolean(CHALLENGED_KEY, gameInfo.challenged.booleanValue());
        }
        if (gameInfo.nudgeTime != null) {
            bundle.putLong(NUDGE_TIME_KEY, gameInfo.nudgeTime.longValue());
        }
        if (gameInfo.result != null) {
            bundle.putString(RESULT_KEY, gameInfo.result);
        }
        if (gameInfo.report != null) {
            bundle.putString(REPORT_KEY, gameInfo.report);
        }
        MultiplayerGamePlayFragment multiplayerGamePlayFragment = new MultiplayerGamePlayFragment();
        multiplayerGamePlayFragment.setArguments(bundle);
        return multiplayerGamePlayFragment;
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mFixedLetters = "";
        this.mFrontLetter = "";
        this.mBackLetter = "";
        Bundle receivedBundle = getArguments();
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(GAME_ID_KEY)) {
                this.mGameId = savedInstanceState.getString(GAME_ID_KEY);
            }
            if (savedInstanceState.containsKey(OPPONENT_USER_ID_KEY)) {
                this.mOpponentUserId = savedInstanceState.getString(OPPONENT_USER_ID_KEY);
            }
            if (savedInstanceState.containsKey(OPPONENT_DISPLAY_NAME_KEY)) {
                this.mOpponentDisplayName = savedInstanceState.getString(OPPONENT_DISPLAY_NAME_KEY);
            }
            if (savedInstanceState.containsKey(OPPONENT_PHOTO_URL_KEY)) {
                this.mOpponentPhotoUrl = savedInstanceState.getString(OPPONENT_PHOTO_URL_KEY);
            }
            if (savedInstanceState.containsKey(FRIEND_KEY)) {
                this.mFriend = Boolean.valueOf(savedInstanceState.getBoolean(FRIEND_KEY));
            }
            if (savedInstanceState.containsKey(FIXED_LETTERS_KEY)) {
                this.mFixedLetters = savedInstanceState.getString(FIXED_LETTERS_KEY);
            }
            if (savedInstanceState.containsKey(FRONT_LETTER_KEY)) {
                this.mFrontLetter = savedInstanceState.getString(FRONT_LETTER_KEY);
            }
            if (savedInstanceState.containsKey(BACK_LETTER_KEY)) {
                this.mBackLetter = savedInstanceState.getString(BACK_LETTER_KEY);
            }
            if (savedInstanceState.containsKey(RESULT_KEY)) {
                this.mResult = savedInstanceState.getString(RESULT_KEY);
            }
            if (savedInstanceState.containsKey(REPORT_KEY)) {
                this.mReport = savedInstanceState.getString(REPORT_KEY);
            }
            if (savedInstanceState.containsKey(TURN_KEY)) {
                this.mTurn = Boolean.valueOf(savedInstanceState.getBoolean(TURN_KEY));
            }
            if (savedInstanceState.containsKey(CHALLENGED_KEY)) {
                this.mChallenged = Boolean.valueOf(savedInstanceState.getBoolean(CHALLENGED_KEY));
            }
            if (savedInstanceState.containsKey(NUDGE_TIME_KEY)) {
                this.mNudgeTime = Long.valueOf(savedInstanceState.getLong(NUDGE_TIME_KEY));
            }
            if (savedInstanceState.containsKey(SERVER_TIME_OFFSET_KEY)) {
                this.mServerTimeOffset = Long.valueOf(savedInstanceState.getLong(SERVER_TIME_OFFSET_KEY));
            }
            if (savedInstanceState.containsKey(TIME_SINCE_NUDGE_KEY)) {
                this.mTimeSinceNudge = Long.valueOf(savedInstanceState.getLong(TIME_SINCE_NUDGE_KEY));
            }
        } else if (receivedBundle != null) {
            if (receivedBundle.containsKey(GAME_ID_KEY)) {
                this.mGameId = receivedBundle.getString(GAME_ID_KEY);
            }
            if (receivedBundle.containsKey(OPPONENT_USER_ID_KEY)) {
                this.mOpponentUserId = receivedBundle.getString(OPPONENT_USER_ID_KEY);
            }
            if (receivedBundle.containsKey(OPPONENT_DISPLAY_NAME_KEY)) {
                this.mOpponentDisplayName = receivedBundle.getString(OPPONENT_DISPLAY_NAME_KEY);
            }
            if (receivedBundle.containsKey(OPPONENT_PHOTO_URL_KEY)) {
                this.mOpponentPhotoUrl = receivedBundle.getString(OPPONENT_PHOTO_URL_KEY);
            }
            if (receivedBundle.containsKey(FIXED_LETTERS_KEY)) {
                this.mFixedLetters = receivedBundle.getString(FIXED_LETTERS_KEY);
            }
            if (receivedBundle.containsKey(RESULT_KEY)) {
                this.mResult = receivedBundle.getString(RESULT_KEY);
            }
            if (receivedBundle.containsKey(REPORT_KEY)) {
                this.mReport = receivedBundle.getString(REPORT_KEY);
            }
            if (receivedBundle.containsKey(TURN_KEY)) {
                this.mTurn = Boolean.valueOf(receivedBundle.getBoolean(TURN_KEY));
            }
            if (receivedBundle.containsKey(CHALLENGED_KEY)) {
                this.mChallenged = Boolean.valueOf(receivedBundle.getBoolean(CHALLENGED_KEY));
            }
            if (receivedBundle.containsKey(NUDGE_TIME_KEY)) {
                this.mNudgeTime = Long.valueOf(receivedBundle.getLong(NUDGE_TIME_KEY));
            }
        }
        this.mPlayerGameReference = FirebaseHelper.getPlayerGameReference(this.mGameId);
        this.mPlayerFriendsReference = FirebaseHelper.getPlayerFriendsReference();
        this.mServerTimeOffsetReference = FirebaseHelper.getServerTimeOffsetReference();
        this.mPlayerGameEventListener = new C05791();
        this.mPlayerFriendsEventListener = new C05802();
        this.mServerTimeOffsetEventListener = new C05813();
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (this.mGameId != null) {
            outState.putString(GAME_ID_KEY, this.mGameId);
        }
        if (this.mOpponentUserId != null) {
            outState.putString(OPPONENT_USER_ID_KEY, this.mOpponentUserId);
        }
        if (this.mOpponentDisplayName != null) {
            outState.putString(OPPONENT_DISPLAY_NAME_KEY, this.mOpponentDisplayName);
        }
        if (this.mOpponentPhotoUrl != null) {
            outState.putString(OPPONENT_PHOTO_URL_KEY, this.mOpponentPhotoUrl);
        }
        if (this.mFixedLetters != null) {
            outState.putString(FIXED_LETTERS_KEY, this.mFixedLetters);
        }
        if (this.mFrontLetter != null) {
            outState.putString(FRONT_LETTER_KEY, this.mFrontLetter);
        }
        if (this.mBackLetter != null) {
            outState.putString(BACK_LETTER_KEY, this.mBackLetter);
        }
        if (this.mResult != null) {
            outState.putString(RESULT_KEY, this.mResult);
        }
        if (this.mReport != null) {
            outState.putString(REPORT_KEY, this.mReport);
        }
        if (this.mTurn != null) {
            outState.putBoolean(TURN_KEY, this.mTurn.booleanValue());
        }
        if (this.mChallenged != null) {
            outState.putBoolean(CHALLENGED_KEY, this.mChallenged.booleanValue());
        }
        if (this.mFriend != null) {
            outState.putBoolean(FRIEND_KEY, this.mFriend.booleanValue());
        }
        if (this.mNudgeTime != null) {
            outState.putLong(NUDGE_TIME_KEY, this.mNudgeTime.longValue());
        }
        if (this.mServerTimeOffset != null) {
            outState.putLong(SERVER_TIME_OFFSET_KEY, this.mServerTimeOffset.longValue());
        }
        if (this.mTimeSinceNudge != null) {
            outState.putLong(TIME_SINCE_NUDGE_KEY, this.mTimeSinceNudge.longValue());
        }
    }

    public void onDestroy() {
        this.mUpdateTimeTimer = null;
        this.mLettersAdapter.adapterListener = null;
        this.mLettersAdapter = null;
        this.mPlayerGameReference = null;
        this.mPlayerFriendsReference = null;
        this.mServerTimeOffsetReference = null;
        this.mPlayerGameEventListener = null;
        this.mPlayerFriendsEventListener = null;
        this.mServerTimeOffsetEventListener = null;
        super.onDestroy();
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(C0521R.layout.multiplayer_game_play_fragment, container, false);
        this.mUnbinder = ButterKnife.bind((Object) this, rootView);
        this.mLettersAdapter = new LettersAdapter();
        this.mLettersAdapter.adapterListener = this;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(0);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        this.lettersRecyclerView.setLayoutManager(linearLayoutManager);
        this.lettersRecyclerView.setHasFixedSize(true);
        this.lettersRecyclerView.addItemDecoration(new C05824());
        this.lettersRecyclerView.setAdapter(this.mLettersAdapter);
        updateLettersAdapter();
        updateOpponentDisplayNameTextView();
        updateStatusTextView();
        updateReportTextView();
        updateTimeSinceNudgeTextView();
        updateOpponentPhotoImageView();
        updateFriendIndicatorImageView();
        updateAddLetterButton();
        updateChallengeButton();
        updateDefendButton();
        updateNudgeButton();
        updateClaimWinButton();
        updateRematchButton();
        updateShareResultButton();
        updateChatButton();
        updateAddFriendButton();
        return rootView;
    }

    public void onDestroyView() {
        this.mUnbinder.unbind();
        super.onDestroyView();
    }

    public void onResume() {
        super.onResume();
        TimerTask updateTimeTimerTask = new C05845();
        this.mUpdateTimeTimer = new Timer();
        this.mUpdateTimeTimer.scheduleAtFixedRate(updateTimeTimerTask, 0, 60000);
        this.mPlayerGameReference.addValueEventListener(this.mPlayerGameEventListener);
        this.mPlayerFriendsReference.addValueEventListener(this.mPlayerFriendsEventListener);
        this.mServerTimeOffsetReference.addValueEventListener(this.mServerTimeOffsetEventListener);
    }

    public void onPause() {
        this.mUpdateTimeTimer.cancel();
        this.mPlayerGameReference.removeEventListener(this.mPlayerGameEventListener);
        this.mPlayerFriendsReference.removeEventListener(this.mPlayerFriendsEventListener);
        this.mServerTimeOffsetReference.removeEventListener(this.mServerTimeOffsetEventListener);
        super.onPause();
    }

    public void onLettersAdapterClickEditText(EditText editText) {
        CapitalAlphabetKeyboardDialogFragment capitalAlphabetKeyboardFragment = CapitalAlphabetKeyboardDialogFragment.newInstance();
        capitalAlphabetKeyboardFragment.setTargetFragment(this, 0);
        capitalAlphabetKeyboardFragment.setEditable(editText.getText());
        capitalAlphabetKeyboardFragment.setCancelable(true);
        capitalAlphabetKeyboardFragment.show(getFragmentManager(), null);
    }

    public void onLettersAdapterChangeLetters(String frontLetter, String backLetter) {
        this.mFrontLetter = frontLetter;
        this.mBackLetter = backLetter;
        updateAddLetterButton();
    }

    public void onDefendChallengeFragmentClickSubmitWord(DefendChallengeDialogFragment dialogFragment, final String word) {
        dialogFragment.dismiss();
        MainActivity mainActivity = (MainActivity) getActivity();
        if (Utility.getRandomInt(0, 9) == 0) {
            mainActivity.showInterstitial();
        }
        this.gamePlayProgressBar.setVisibility(0);
        mainActivity.requestWordInfosList(word, new Callback<List<WordInfo>>() {

            /* renamed from: com.cranevalley.dontendword.features.main.multiplayergameplay.MultiplayerGamePlayFragment$6$1 */
            class C05851 implements OnCompleteListener<Void> {
                C05851() {
                }

                public void onComplete(@NonNull Task<Void> task) {
                    MultiplayerGamePlayFragment.this.gamePlayProgressBar.setVisibility(8);
                }
            }

            public void onResponse(Call<List<WordInfo>> call, Response<List<WordInfo>> response) {
                List<WordInfo> wordInfosList = (List) response.body();
                String definition = null;
                if (wordInfosList.size() > 0) {
                    definition = ((WordInfo) wordInfosList.get(0)).getDefinition();
                }
                FirebaseHelper.submitDefence(MultiplayerGamePlayFragment.this.mGameId, word, definition).addOnCompleteListener(MultiplayerGamePlayFragment.this.getActivity(), new C05851());
            }

            public void onFailure(Call<List<WordInfo>> call, Throwable throwable) {
                Logger.m1220e(throwable.getLocalizedMessage(), new Object[0]);
            }
        });
    }

    public void onCapitalAlphabetKeyboardFragmentKey(CapitalAlphabetKeyboardDialogFragment dialogFragment, Editable editable, int primaryCode, int[] keyCodes) {
        if (editable == null) {
            return;
        }
        if (primaryCode == -5) {
            editable.clear();
        } else if (primaryCode == -4) {
            dialogFragment.dismiss();
        } else {
            editable.replace(0, editable.length(), String.valueOf((char) primaryCode));
        }
    }

    @UiThread
    private void updateLettersAdapter() {
        this.mLettersAdapter.setLetters(this.mFixedLetters, this.mFrontLetter, this.mBackLetter);
        LettersAdapter lettersAdapter = this.mLettersAdapter;
        boolean z = this.mTurn != null && this.mTurn.booleanValue() && (this.mChallenged == null || !this.mChallenged.booleanValue());
        lettersAdapter.setAddEnabled(z);
        this.mLettersAdapter.notifyDataSetChanged();
    }

    @UiThread
    private void updateOpponentDisplayNameTextView() {
        if (this.mOpponentUserId != null) {
            this.opponentDisplayNameTextView.setText(this.mOpponentDisplayName);
        } else {
            this.opponentDisplayNameTextView.setText("Random Opponent");
        }
    }

    @UiThread
    private void updateStatusTextView() {
        if (this.mResult != null) {
            String str = this.mResult;
            Object obj = -1;
            switch (str.hashCode()) {
                case 86134:
                    if (str.equals("WON")) {
                        obj = null;
                        break;
                    }
                    break;
                case 2342692:
                    if (str.equals("LOST")) {
                        obj = 1;
                        break;
                    }
                    break;
                case 65307530:
                    if (str.equals("DRAWN")) {
                        obj = 2;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case null:
                    this.statusTextView.setText("YOU WON!");
                    return;
                case 1:
                    this.statusTextView.setText("YOU LOST!");
                    return;
                case 2:
                    this.statusTextView.setText("GAME DRAWN!");
                    return;
                default:
                    this.statusTextView.setText("");
                    return;
            }
        } else if (this.mTurn == null || !this.mTurn.booleanValue()) {
            this.statusTextView.setText("OPPONENT'S TURN");
        } else if (this.mChallenged == null || !this.mChallenged.booleanValue()) {
            this.statusTextView.setText("YOUR TURN");
        } else {
            this.statusTextView.setText("YOU ARE CHALLENGED!");
        }
    }

    @UiThread
    private void updateReportTextView() {
        if (this.mResult != null) {
            this.reportTextView.setText(this.mReport);
        } else if (this.mTurn == null || !this.mTurn.booleanValue()) {
            if (this.mOpponentUserId != null) {
                this.reportTextView.setText("Nudge to notify your opponent. Claim win if opponent doesn't respond in 2 days.");
            } else {
                this.reportTextView.setText("Wait for your opponent to join.");
            }
        } else if (this.mChallenged == null || !this.mChallenged.booleanValue()) {
            this.reportTextView.setText("Add a letter to front or back. Challenge if it's a complete word (minimum 4 letters) or not part of any word.");
        } else {
            this.reportTextView.setText("Defend by forming a word that contains the string.");
        }
    }

    @UiThread
    private void updateTimeSinceNudgeTextView() {
        if (this.mTimeSinceNudge != null) {
            long hours = this.mTimeSinceNudge.longValue() / 3600000;
            long minutes = (this.mTimeSinceNudge.longValue() % 3600000) / 60000;
            this.timeSinceNudgeTextView.setText(String.format(Locale.ENGLISH, "%02dh %02dm", new Object[]{Long.valueOf(hours), Long.valueOf(minutes)}));
            this.timeSinceNudgeTextView.setVisibility(0);
            return;
        }
        this.timeSinceNudgeTextView.setVisibility(8);
    }

    @UiThread
    private void updateOpponentPhotoImageView() {
        Picasso.with(getContext()).load(this.mOpponentPhotoUrl).placeholder((int) C0521R.drawable.photo_default).fit().centerCrop().into(this.opponentPhotoImageView);
    }

    @UiThread
    private void updateFriendIndicatorImageView() {
        if (this.mFriend == null || !this.mFriend.booleanValue()) {
            this.friendIndicatorImageView.setVisibility(8);
        } else {
            this.friendIndicatorImageView.setVisibility(0);
        }
    }

    @UiThread
    private void updateAddLetterButton() {
        boolean z = false;
        if (this.mTurn == null || !this.mTurn.booleanValue()) {
            this.addLetterButton.setVisibility(8);
            return;
        }
        this.addLetterButton.setVisibility(0);
        AppCompatButton appCompatButton = this.addLetterButton;
        if ((this.mChallenged == null || !this.mChallenged.booleanValue()) && (this.mFrontLetter.length() > 0 || this.mBackLetter.length() > 0)) {
            z = true;
        }
        appCompatButton.setEnabled(z);
    }

    @UiThread
    private void updateChallengeButton() {
        if (this.mTurn == null || !this.mTurn.booleanValue() || (this.mChallenged != null && this.mChallenged.booleanValue())) {
            this.challengeButton.setVisibility(8);
            return;
        }
        this.challengeButton.setVisibility(0);
        this.challengeButton.setEnabled(true);
    }

    @UiThread
    private void updateDefendButton() {
        if (this.mTurn == null || !this.mTurn.booleanValue() || this.mChallenged == null || !this.mChallenged.booleanValue()) {
            this.defendButton.setVisibility(8);
            return;
        }
        this.defendButton.setVisibility(0);
        this.defendButton.setEnabled(true);
    }

    @UiThread
    private void updateNudgeButton() {
        boolean z = false;
        if (this.mTurn == null || !this.mTurn.booleanValue()) {
            this.nudgeButton.setVisibility(0);
            AppCompatButton appCompatButton = this.nudgeButton;
            if (this.mOpponentUserId != null && this.mNudgeTime == null) {
                z = true;
            }
            appCompatButton.setEnabled(z);
            return;
        }
        this.nudgeButton.setVisibility(8);
    }

    @UiThread
    private void updateClaimWinButton() {
        boolean z = false;
        if (this.mTurn == null || !this.mTurn.booleanValue()) {
            this.claimWinButton.setVisibility(0);
            AppCompatButton appCompatButton = this.claimWinButton;
            if (this.mTimeSinceNudge != null && this.mTimeSinceNudge.longValue() > 172800000) {
                z = true;
            }
            appCompatButton.setEnabled(z);
            return;
        }
        this.claimWinButton.setVisibility(8);
    }

    @UiThread
    private void updateRematchButton() {
        if (this.mResult != null) {
            this.rematchButton.setVisibility(0);
            this.rematchButton.setEnabled(true);
            return;
        }
        this.rematchButton.setVisibility(8);
    }

    @UiThread
    private void updateShareResultButton() {
        if (this.mResult != null) {
            this.shareResultButton.setVisibility(0);
            this.shareResultButton.setEnabled(true);
            return;
        }
        this.shareResultButton.setVisibility(8);
    }

    @UiThread
    private void updateAddFriendButton() {
        boolean z = false;
        if (this.mFriend == null || !this.mFriend.booleanValue()) {
            this.addFriendButton.setVisibility(0);
            AppCompatImageButton appCompatImageButton = this.addFriendButton;
            if (this.mOpponentUserId != null) {
                z = true;
            }
            appCompatImageButton.setEnabled(z);
            return;
        }
        this.addFriendButton.setVisibility(8);
    }

    @UiThread
    private void updateChatButton() {
        this.chatButton.setEnabled(this.mOpponentUserId != null);
    }

    @OnClick({2131820748})
    void onClickChat(View view) {
        AudioHelper.playClickEffect();
        if (this.mOpponentUserId != null && this.mOpponentDisplayName != null) {
            ((MainActivity) getActivity()).replaceContentFragment(ChatFragment.newInstance(this.mOpponentUserId, this.mOpponentDisplayName, this.mOpponentPhotoUrl));
        }
    }

    @OnClick({2131820690})
    void onClickAddFriend(View view) {
        AudioHelper.playClickEffect();
        FirebaseHelper.addFriend(this.mOpponentUserId, this.mOpponentDisplayName, this.mOpponentPhotoUrl);
    }

    @OnClick({2131820721})
    void onClickAddLetter(View view) {
        AudioHelper.playClickEffect();
        MainActivity mainActivity = (MainActivity) getActivity();
        if (Utility.getRandomInt(0, 9) == 0) {
            mainActivity.showInterstitial();
        }
        String addedLetter = "";
        boolean front = false;
        if (this.mFrontLetter.length() > 0) {
            addedLetter = this.mFrontLetter;
            front = true;
        } else if (this.mBackLetter.length() > 0) {
            addedLetter = this.mBackLetter;
            front = false;
        }
        this.mFixedLetters = this.mFrontLetter + this.mFixedLetters + this.mBackLetter;
        this.mFrontLetter = "";
        this.mBackLetter = "";
        this.mLettersAdapter.setLetters(this.mFixedLetters, this.mFrontLetter, this.mBackLetter);
        this.mLettersAdapter.setAddEnabled(false);
        this.mLettersAdapter.notifyDataSetChanged();
        this.gamePlayProgressBar.setVisibility(0);
        this.addLetterButton.setEnabled(false);
        this.challengeButton.setEnabled(false);
        FirebaseHelper.addLetter(this.mGameId, addedLetter, front).addOnCompleteListener(getActivity(), new C05877());
    }

    @OnClick({2131820799})
    void onClickChallenge(View view) {
        AudioHelper.playClickEffect();
        MainActivity mainActivity = (MainActivity) getActivity();
        if (Utility.getRandomInt(0, 9) == 0) {
            mainActivity.showInterstitial();
        }
        this.mFrontLetter = "";
        this.mBackLetter = "";
        this.mLettersAdapter.setLetters(this.mFixedLetters, this.mFrontLetter, this.mBackLetter);
        this.mLettersAdapter.setAddEnabled(false);
        this.mLettersAdapter.notifyDataSetChanged();
        this.gamePlayProgressBar.setVisibility(0);
        this.addLetterButton.setEnabled(false);
        this.challengeButton.setEnabled(false);
        if (this.mFixedLetters == null || this.mFixedLetters.length() <= 3) {
            FirebaseHelper.sendChallenge(this.mGameId).addOnCompleteListener(getActivity(), new C05919());
        } else {
            mainActivity.requestWordInfosList(this.mFixedLetters, new C05908());
        }
    }

    @OnClick({2131820800})
    void onClickDefend(View view) {
        AudioHelper.playClickEffect();
        if (this.mFixedLetters != null) {
            DefendChallengeDialogFragment defendChallengeFragment = DefendChallengeDialogFragment.newInstance(this.mFixedLetters);
            defendChallengeFragment.setTargetFragment(this, 0);
            defendChallengeFragment.setCancelable(true);
            defendChallengeFragment.show(getFragmentManager(), null);
        }
    }

    @OnClick({2131820797})
    void onClickNudge(View view) {
        AudioHelper.playClickEffect();
        this.gamePlayProgressBar.setVisibility(0);
        if (this.mOpponentUserId != null) {
            FirebaseHelper.sendNudge(this.mGameId).addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
                public void onComplete(@NonNull Task<Void> task) {
                    MultiplayerGamePlayFragment.this.gamePlayProgressBar.setVisibility(8);
                }
            });
        }
    }

    @OnClick({2131820801})
    void onClickClaimWin(View view) {
        AudioHelper.playClickEffect();
        this.gamePlayProgressBar.setVisibility(0);
        FirebaseHelper.claimWinForTimeOut(this.mGameId).addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
            public void onComplete(@NonNull Task<Void> task) {
                MultiplayerGamePlayFragment.this.gamePlayProgressBar.setVisibility(8);
            }
        });
    }

    @OnClick({2131820798})
    void onClickRematch(View view) {
        AudioHelper.playClickEffect();
        CreateGameDialogFragment createGameFragment = CreateGameDialogFragment.newInstance(this.mOpponentUserId, this.mOpponentDisplayName, this.mOpponentPhotoUrl);
        createGameFragment.setCancelable(true);
        createGameFragment.show(getFragmentManager(), null);
    }

    @OnClick({2131820802})
    void onClickShareResult(View view) {
        AudioHelper.playClickEffect();
        if (this.mResult != null) {
            String text = String.format(Locale.ENGLISH, "Just %s a game of Don't End a Word with \"%s\"! Download at %s and challenge me!", new Object[]{this.mResult, this.mFixedLetters, AppConstant.ANDROID_APP_URL});
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType("text/plain");
            intent.putExtra("android.intent.extra.TITLE", "Don't End a Word!");
            intent.putExtra("android.intent.extra.SUBJECT", "Snack Sized Wordplay!");
            intent.putExtra("android.intent.extra.TEXT", text);
            startActivity(Intent.createChooser(intent, "Share via..."));
        }
    }
}
