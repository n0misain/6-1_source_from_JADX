package com.cranevalley.dontendword.features.main.creategame;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.State;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ProgressBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.cranevalley.dontendword.C0521R;
import com.cranevalley.dontendword.features.main.MainActivity;
import com.cranevalley.dontendword.features.main.multiplayergameplay.MultiplayerGamePlayFragment;
import com.cranevalley.dontendword.features.shared.CapitalAlphabetKeyboardDialogFragment;
import com.cranevalley.dontendword.features.shared.CapitalAlphabetKeyboardFragmentListener;
import com.cranevalley.dontendword.features.shared.GameInfo;
import com.cranevalley.dontendword.features.shared.lettersadapter.LettersAdapter;
import com.cranevalley.dontendword.features.shared.lettersadapter.LettersAdapterListener;
import com.cranevalley.dontendword.helpers.AudioHelper;
import com.cranevalley.dontendword.helpers.FirebaseHelper;
import com.cranevalley.dontendword.helpers.Utility;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

@SuppressLint({"SetTextI18n"})
public class CreateGameDialogFragment extends DialogFragment implements LettersAdapterListener, CapitalAlphabetKeyboardFragmentListener {
    private static final String ADD_ENABLED_KEY = "ADD_ENABLED_KEY";
    private static final String BACK_LETTER_KEY = "BACK_LETTER_KEY";
    private static final String FIXED_LETTERS_KEY = "FIXED_LETTERS_KEY";
    private static final String FRONT_LETTER_KEY = "FRONT_LETTER_KEY";
    private static final String GAME_ID_KEY = "GAME_ID_KEY";
    private static final String OPPONENT_DISPLAY_NAME_KEY = "OPPONENT_DISPLAY_NAME_KEY";
    private static final String OPPONENT_PHOTO_URL_KEY = "OPPONENT_PHOTO_URL_KEY";
    private static final String OPPONENT_USER_ID_KEY = "OPPONENT_USER_ID_KEY";
    @BindView(2131820721)
    AppCompatButton addLetterButton;
    @BindView(2131820720)
    ProgressBar createGameProgressBar;
    @BindView(2131820719)
    RecyclerView lettersRecyclerView;
    private boolean mAddEnabled;
    private String mBackLetter;
    private String mFixedLetters;
    private String mFrontLetter;
    private String mGameId;
    private LettersAdapter mLettersAdapter;
    private String mOpponentDisplayName;
    private String mOpponentPhotoUrl;
    private String mOpponentUserId;
    private Unbinder mUnbinder;
    @BindView(2131820722)
    AppCompatButton openGameButton;
    @BindView(2131820717)
    AppCompatTextView opponentDisplayNameTextView;
    @BindView(2131820716)
    RoundedImageView opponentPhotoImageView;
    @BindView(2131820718)
    AppCompatTextView reportTextView;

    /* renamed from: com.cranevalley.dontendword.features.main.creategame.CreateGameDialogFragment$1 */
    class C05511 extends ItemDecoration {
        C05511() {
        }

        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
            int offset = (int) Utility.getPxFromDp(1.0f);
            outRect.set(offset, offset, offset, offset);
        }
    }

    /* renamed from: com.cranevalley.dontendword.features.main.creategame.CreateGameDialogFragment$2 */
    class C05522 implements OnCompleteListener<Void> {
        C05522() {
        }

        public void onComplete(@NonNull Task<Void> task) {
            CreateGameDialogFragment.this.createGameProgressBar.setVisibility(8);
            if (task.isSuccessful()) {
                CreateGameDialogFragment.this.reportTextView.setText("Game created successfully!");
                CreateGameDialogFragment.this.addLetterButton.setVisibility(8);
                CreateGameDialogFragment.this.openGameButton.setVisibility(0);
                return;
            }
            CreateGameDialogFragment.this.reportTextView.setText("Failed to create game!");
        }
    }

    public static CreateGameDialogFragment newInstance(String opponentUserId, String opponentDisplayName, String opponentPhotoUrl) {
        Bundle bundle = new Bundle();
        bundle.putString(OPPONENT_USER_ID_KEY, opponentUserId);
        bundle.putString(OPPONENT_DISPLAY_NAME_KEY, opponentDisplayName);
        bundle.putString(OPPONENT_PHOTO_URL_KEY, opponentPhotoUrl);
        CreateGameDialogFragment createGameFragment = new CreateGameDialogFragment();
        createGameFragment.setArguments(bundle);
        return createGameFragment;
    }

    public static CreateGameDialogFragment newInstance() {
        return new CreateGameDialogFragment();
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mFixedLetters = getStarterLetter();
        this.mFrontLetter = "";
        this.mBackLetter = "";
        this.mAddEnabled = true;
        Bundle receivedBundle = getArguments();
        if (savedInstanceState != null) {
            this.mOpponentUserId = savedInstanceState.getString(OPPONENT_USER_ID_KEY);
            this.mOpponentDisplayName = savedInstanceState.getString(OPPONENT_DISPLAY_NAME_KEY);
            this.mOpponentPhotoUrl = savedInstanceState.getString(OPPONENT_PHOTO_URL_KEY);
            this.mFixedLetters = savedInstanceState.getString(FIXED_LETTERS_KEY);
            this.mFrontLetter = savedInstanceState.getString(FRONT_LETTER_KEY);
            this.mBackLetter = savedInstanceState.getString(BACK_LETTER_KEY);
            this.mGameId = savedInstanceState.getString(GAME_ID_KEY);
            this.mAddEnabled = savedInstanceState.getBoolean(ADD_ENABLED_KEY);
        } else if (receivedBundle != null) {
            this.mOpponentUserId = receivedBundle.getString(OPPONENT_USER_ID_KEY);
            this.mOpponentDisplayName = receivedBundle.getString(OPPONENT_DISPLAY_NAME_KEY);
            this.mOpponentPhotoUrl = receivedBundle.getString(OPPONENT_PHOTO_URL_KEY);
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(OPPONENT_USER_ID_KEY, this.mOpponentUserId);
        outState.putString(OPPONENT_DISPLAY_NAME_KEY, this.mOpponentDisplayName);
        outState.putString(OPPONENT_PHOTO_URL_KEY, this.mOpponentPhotoUrl);
        outState.putString(FIXED_LETTERS_KEY, this.mFixedLetters);
        outState.putString(FRONT_LETTER_KEY, this.mFrontLetter);
        outState.putString(BACK_LETTER_KEY, this.mBackLetter);
        outState.putBoolean(ADD_ENABLED_KEY, this.mAddEnabled);
        outState.putString(GAME_ID_KEY, this.mGameId);
    }

    public void onDestroy() {
        this.mLettersAdapter.adapterListener = null;
        this.mLettersAdapter = null;
        super.onDestroy();
    }

    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        Window window = dialog.getWindow();
        if (window != null) {
            window.requestFeature(1);
        }
        return dialog;
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(C0521R.layout.create_game_dialog_fragment, container, false);
        this.mUnbinder = ButterKnife.bind((Object) this, rootView);
        this.mLettersAdapter = new LettersAdapter(this.mFixedLetters, this.mFrontLetter, this.mBackLetter, this.mAddEnabled);
        this.mLettersAdapter.adapterListener = this;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(0);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        this.lettersRecyclerView.setLayoutManager(linearLayoutManager);
        this.lettersRecyclerView.setHasFixedSize(true);
        this.lettersRecyclerView.addItemDecoration(new C05511());
        this.lettersRecyclerView.setAdapter(this.mLettersAdapter);
        if (this.mOpponentUserId != null) {
            this.opponentDisplayNameTextView.setText(this.mOpponentDisplayName);
            Picasso.with(getContext()).load(this.mOpponentPhotoUrl).placeholder((int) C0521R.drawable.photo_default).fit().centerCrop().into(this.opponentPhotoImageView);
        }
        return rootView;
    }

    public void onDestroyView() {
        this.mUnbinder.unbind();
        super.onDestroyView();
    }

    public void onResume() {
        super.onResume();
        Window window = getDialog().getWindow();
        if (window != null) {
            Point point = new Point();
            window.getWindowManager().getDefaultDisplay().getSize(point);
            window.setLayout((int) (((double) point.x) * 0.85d), -2);
            window.setGravity(17);
        }
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
        AppCompatButton appCompatButton = this.addLetterButton;
        boolean z = this.mFrontLetter.length() > 0 || this.mBackLetter.length() > 0;
        appCompatButton.setEnabled(z);
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

    private String getStarterLetter() {
        char[] starterLettersArray = new char[]{'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'B', 'B', 'C', 'C', 'D', 'D', 'D', 'D', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'F', 'F', 'G', 'G', 'G', 'H', 'H', 'I', 'I', 'I', 'I', 'I', 'I', 'I', 'I', 'I', 'J', 'K', 'L', 'L', 'L', 'L', 'M', 'M', 'N', 'N', 'N', 'N', 'N', 'N', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'P', 'P', 'Q', 'R', 'R', 'R', 'R', 'R', 'R', 'S', 'S', 'S', 'S', 'T', 'T', 'T', 'T', 'T', 'T', 'U', 'U', 'U', 'U', 'V', 'V', 'W', 'W', 'X', 'Y', 'Y', 'Z'};
        return String.valueOf(starterLettersArray[Utility.getRandomInt(0, starterLettersArray.length - 1)]);
    }

    @OnClick({2131820721})
    void onClickAddLetter(View view) {
        Task<Void> createGameTask;
        AudioHelper.playClickEffect();
        this.mFixedLetters = this.mFrontLetter + this.mFixedLetters + this.mBackLetter;
        this.mFrontLetter = "";
        this.mBackLetter = "";
        this.mAddEnabled = false;
        this.mLettersAdapter.setLetters(this.mFixedLetters, this.mFrontLetter, this.mBackLetter);
        this.mLettersAdapter.setAddEnabled(this.mAddEnabled);
        this.mLettersAdapter.notifyDataSetChanged();
        this.createGameProgressBar.setVisibility(0);
        this.reportTextView.setText("Creating game...");
        this.addLetterButton.setEnabled(false);
        this.mGameId = FirebaseHelper.getPlayerGamesReference().push().getKey();
        if (this.mOpponentUserId != null) {
            createGameTask = FirebaseHelper.createFriendGame(this.mGameId, this.mOpponentUserId, this.mOpponentDisplayName, this.mOpponentPhotoUrl, this.mFixedLetters);
        } else {
            createGameTask = FirebaseHelper.createRandomGame(this.mGameId, this.mFixedLetters);
        }
        createGameTask.addOnCompleteListener(getActivity(), new C05522());
    }

    @OnClick({2131820722})
    void onClickOpenGame(View view) {
        AudioHelper.playClickEffect();
        dismiss();
        GameInfo gameInfo = new GameInfo();
        gameInfo.gameId = this.mGameId;
        gameInfo.opponentUserId = this.mOpponentUserId;
        gameInfo.opponentDisplayName = this.mOpponentDisplayName;
        gameInfo.opponentPhotoUrl = this.mOpponentPhotoUrl;
        gameInfo.letters = this.mFrontLetter + this.mFixedLetters + this.mBackLetter;
        gameInfo.turn = Boolean.valueOf(false);
        ((MainActivity) getActivity()).replaceContentFragment(MultiplayerGamePlayFragment.newInstance(gameInfo));
    }

    @OnClick({2131820687})
    void onClickClose(View view) {
        AudioHelper.playClickEffect();
        dismiss();
    }
}
