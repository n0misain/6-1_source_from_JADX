package com.cranevalley.dontendword.features.main.playerprofile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.cranevalley.dontendword.C0521R;
import com.cranevalley.dontendword.features.bases.BaseFragment;
import com.cranevalley.dontendword.features.main.MainActivity;
import com.cranevalley.dontendword.features.main.playerprofile.setdisplayname.SetDisplayNameDialogFragment;
import com.cranevalley.dontendword.helpers.AudioHelper;
import com.cranevalley.dontendword.helpers.FirebaseHelper;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.TwitterAuthProvider;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.makeramen.roundedimageview.RoundedImageView;
import com.orhanobut.logger.Logger;
import com.squareup.picasso.Picasso;
import java.util.Locale;

public class PlayerProfileFragment extends BaseFragment {
    @BindView(2131820845)
    FloatingActionButton connectFacebookButton;
    @BindView(2131820849)
    FloatingActionButton connectGoogleButton;
    @BindView(2131820847)
    FloatingActionButton connectTwitterButton;
    @BindView(2131820689)
    AppCompatTextView displayNameTextView;
    @BindView(2131820843)
    AppCompatTextView drawCountTextView;
    @BindView(2131820846)
    AppCompatTextView facebookConnectIndicatorTextView;
    @BindView(2131820840)
    AppCompatTextView gameCountTextView;
    @BindView(2131820850)
    AppCompatTextView googleConnectIndicatorTextView;
    @BindView(2131820842)
    AppCompatTextView loseCountTextView;
    private ValueEventListener mPlayerGamesEventListener;
    private DatabaseReference mPlayerGamesReference;
    private ValueEventListener mPlayerUserInfoEventListener;
    private DatabaseReference mPlayerUserInfoReference;
    private Unbinder mUnbinder;
    @BindView(2131820844)
    AppCompatTextView ongoingCountTextView;
    @BindView(2131820688)
    RoundedImageView photoImageView;
    @BindView(2131820839)
    AppCompatImageButton setDisplayNameButton;
    @BindView(2131820848)
    AppCompatTextView twitterConnectIndicatorTextView;
    @BindView(2131820841)
    AppCompatTextView winCountTextView;

    /* renamed from: com.cranevalley.dontendword.features.main.playerprofile.PlayerProfileFragment$1 */
    class C06221 implements ValueEventListener {
        C06221() {
        }

        public void onDataChange(DataSnapshot dataSnapshot) {
            String playerDisplayName = null;
            String playerPhotoUrl = null;
            if (dataSnapshot.exists()) {
                playerDisplayName = (String) dataSnapshot.child("displayName").getValue(String.class);
                playerPhotoUrl = (String) dataSnapshot.child("photoUrl").getValue(String.class);
            }
            PlayerProfileFragment.this.handleUserInfoChange(playerDisplayName, playerPhotoUrl);
        }

        public void onCancelled(DatabaseError databaseError) {
            Logger.m1220e(databaseError.getMessage(), new Object[0]);
        }
    }

    /* renamed from: com.cranevalley.dontendword.features.main.playerprofile.PlayerProfileFragment$2 */
    class C06232 implements ValueEventListener {
        C06232() {
        }

        public void onDataChange(DataSnapshot dataSnapshot) {
            int gameCount = 0;
            int winCount = 0;
            int loseCount = 0;
            int drawCount = 0;
            int ongoingCount = 0;
            if (dataSnapshot.exists()) {
                for (DataSnapshot gameSnapshot : dataSnapshot.getChildren()) {
                    gameCount++;
                    String result = (String) gameSnapshot.child("result").getValue(String.class);
                    if (result != null) {
                        Object obj = -1;
                        switch (result.hashCode()) {
                            case 86134:
                                if (result.equals("WON")) {
                                    obj = null;
                                    break;
                                }
                                break;
                            case 2342692:
                                if (result.equals("LOST")) {
                                    obj = 1;
                                    break;
                                }
                                break;
                            case 65307530:
                                if (result.equals("DRAWN")) {
                                    obj = 2;
                                    break;
                                }
                                break;
                        }
                        switch (obj) {
                            case null:
                                winCount++;
                                break;
                            case 1:
                                loseCount++;
                                break;
                            case 2:
                                drawCount++;
                                break;
                            default:
                                ongoingCount++;
                                break;
                        }
                    }
                    ongoingCount++;
                }
            }
            PlayerProfileFragment.this.handleStatisticsChange(gameCount, winCount, loseCount, drawCount, ongoingCount);
        }

        public void onCancelled(DatabaseError databaseError) {
            Logger.m1220e(databaseError.getMessage(), new Object[0]);
        }
    }

    public static PlayerProfileFragment newInstance() {
        return new PlayerProfileFragment();
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mPlayerUserInfoReference = FirebaseHelper.getPlayerUserInfoReference();
        this.mPlayerGamesReference = FirebaseHelper.getPlayerGamesReference();
        this.mPlayerUserInfoEventListener = new C06221();
        this.mPlayerGamesEventListener = new C06232();
    }

    public void onDestroy() {
        this.mPlayerUserInfoReference = null;
        this.mPlayerGamesReference = null;
        this.mPlayerUserInfoEventListener = null;
        this.mPlayerGamesEventListener = null;
        super.onDestroy();
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(C0521R.layout.player_profile_fragment, container, false);
        this.mUnbinder = ButterKnife.bind((Object) this, rootView);
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null) {
            for (UserInfo userInfo : firebaseUser.getProviderData()) {
                if (FacebookAuthProvider.PROVIDER_ID.equals(userInfo.getProviderId())) {
                    this.setDisplayNameButton.setVisibility(8);
                    this.connectFacebookButton.setEnabled(false);
                    this.facebookConnectIndicatorTextView.setVisibility(0);
                } else if (TwitterAuthProvider.PROVIDER_ID.equals(userInfo.getProviderId())) {
                    this.setDisplayNameButton.setVisibility(8);
                    this.connectTwitterButton.setEnabled(false);
                    this.twitterConnectIndicatorTextView.setVisibility(0);
                } else if (GoogleAuthProvider.PROVIDER_ID.equals(userInfo.getProviderId())) {
                    this.setDisplayNameButton.setVisibility(8);
                    this.connectGoogleButton.setEnabled(false);
                    this.googleConnectIndicatorTextView.setVisibility(0);
                }
            }
        }
        return rootView;
    }

    public void onDestroyView() {
        this.mUnbinder.unbind();
        super.onDestroyView();
    }

    public void onResume() {
        super.onResume();
        this.mPlayerUserInfoReference.addValueEventListener(this.mPlayerUserInfoEventListener);
        this.mPlayerGamesReference.addValueEventListener(this.mPlayerGamesEventListener);
    }

    public void onPause() {
        this.mPlayerUserInfoReference.removeEventListener(this.mPlayerUserInfoEventListener);
        this.mPlayerGamesReference.removeEventListener(this.mPlayerGamesEventListener);
        super.onPause();
    }

    private void handleUserInfoChange(final String displayName, final String photoUrl) {
        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                PlayerProfileFragment.this.displayNameTextView.setText(displayName);
                Picasso.with(PlayerProfileFragment.this.getContext()).load(photoUrl).placeholder((int) C0521R.drawable.photo_default).fit().centerCrop().into(PlayerProfileFragment.this.photoImageView);
            }
        });
    }

    private void handleStatisticsChange(int gameCount, int winCount, int loseCount, int drawCount, int ongoingCount) {
        final int i = gameCount;
        final int i2 = winCount;
        final int i3 = loseCount;
        final int i4 = drawCount;
        final int i5 = ongoingCount;
        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                PlayerProfileFragment.this.gameCountTextView.setText(String.format(Locale.ENGLISH, "Games: %d", new Object[]{Integer.valueOf(i)}));
                PlayerProfileFragment.this.winCountTextView.setText(String.format(Locale.ENGLISH, "%d", new Object[]{Integer.valueOf(i2)}));
                PlayerProfileFragment.this.loseCountTextView.setText(String.format(Locale.ENGLISH, "%d", new Object[]{Integer.valueOf(i3)}));
                PlayerProfileFragment.this.drawCountTextView.setText(String.format(Locale.ENGLISH, "%d", new Object[]{Integer.valueOf(i4)}));
                PlayerProfileFragment.this.ongoingCountTextView.setText(String.format(Locale.ENGLISH, "%d", new Object[]{Integer.valueOf(i5)}));
            }
        });
    }

    @OnClick({2131820839})
    void onClickSetDisplayName(View sender) {
        AudioHelper.playClickEffect();
        SetDisplayNameDialogFragment setDisplayNameFragment = SetDisplayNameDialogFragment.newInstance();
        setDisplayNameFragment.setCancelable(true);
        setDisplayNameFragment.show(getFragmentManager(), null);
    }

    @OnClick({2131820845})
    void onClickConnectFacebook(View sender) {
        AudioHelper.playClickEffect();
        ((MainActivity) getActivity()).connectFacebook();
    }

    @OnClick({2131820847})
    void onClickConnectTwitter(View sender) {
        AudioHelper.playClickEffect();
        ((MainActivity) getActivity()).connectTwitter();
    }

    @OnClick({2131820849})
    void onClickConnectGoogle(View sender) {
        AudioHelper.playClickEffect();
        ((MainActivity) getActivity()).connectGoogle();
    }
}
