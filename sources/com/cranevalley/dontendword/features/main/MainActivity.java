package com.cranevalley.dontendword.features.main;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.BillingProcessor.IBillingHandler;
import com.anjlab.android.iab.v3.TransactionDetails;
import com.applovin.adview.AppLovinInterstitialAd;
import com.applovin.sdk.AppLovinSdk;
import com.cranevalley.dontendword.C0521R;
import com.cranevalley.dontendword.features.bases.BaseActivity;
import com.cranevalley.dontendword.features.initialization.InitializationActivity;
import com.cranevalley.dontendword.features.intro.IntroActivity;
import com.cranevalley.dontendword.features.main.home.HomeFragment;
import com.cranevalley.dontendword.features.shared.WordInfo;
import com.cranevalley.dontendword.features.shared.WordnikApi;
import com.cranevalley.dontendword.features.shared.WordnikClient;
import com.cranevalley.dontendword.helpers.AppConstant;
import com.cranevalley.dontendword.helpers.AudioHelper;
import com.cranevalley.dontendword.helpers.FirebaseHelper;
import com.cranevalley.dontendword.helpers.PreferencesHelper;
import com.facebook.CallbackManager;
import com.facebook.CallbackManager.Factory;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuth.AuthStateListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.TwitterAuthProvider;
import com.google.firebase.iid.FirebaseInstanceId;
import com.orhanobut.logger.Logger;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;
import io.fabric.sdk.android.Fabric;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends BaseActivity implements IBillingHandler {
    private static final int GOOGLE_REQUEST_CODE = 1111;
    @BindView(2131820767)
    FrameLayout bannerLayout;
    private AuthStateListener mAuthStateListener;
    private AdView mBannerAdView;
    private BillingProcessor mBillingProcessor;
    private CallbackManager mCallbackManager;
    private GoogleApiClient mGoogleApiClient;
    private InterstitialAd mInterstitialAd;
    private String mPlayerUserId;
    private Callback<TwitterSession> mSessionCallback;
    private TwitterAuthClient mTwitterAuthClient;
    private WordnikApi mWordnikApi;

    /* renamed from: com.cranevalley.dontendword.features.main.MainActivity$1 */
    class C05251 implements OnClickListener {
        C05251() {
        }

        public void onClick(DialogInterface dialog, int which) {
            MainActivity.this.finish();
        }
    }

    /* renamed from: com.cranevalley.dontendword.features.main.MainActivity$2 */
    class C05262 implements AuthStateListener {
        C05262() {
        }

        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
            if (firebaseUser != null) {
                String playerUserId = firebaseUser.getUid();
                if (MainActivity.this.mPlayerUserId == null) {
                    MainActivity.this.mPlayerUserId = playerUserId;
                    return;
                } else if (!playerUserId.equals(MainActivity.this.mPlayerUserId)) {
                    MainActivity.this.handleReinitialization();
                    return;
                } else {
                    return;
                }
            }
            MainActivity.this.handleReinitialization();
        }
    }

    /* renamed from: com.cranevalley.dontendword.features.main.MainActivity$3 */
    class C05273 implements FacebookCallback<LoginResult> {
        C05273() {
        }

        public void onSuccess(LoginResult loginResult) {
            MainActivity.this.linkSocialAccount("Facebook", FacebookAuthProvider.getCredential(loginResult.getAccessToken().getToken()));
        }

        public void onCancel() {
        }

        public void onError(FacebookException exception) {
            Logger.m1220e(exception.getLocalizedMessage(), new Object[0]);
        }
    }

    /* renamed from: com.cranevalley.dontendword.features.main.MainActivity$4 */
    class C05284 extends Callback<TwitterSession> {
        C05284() {
        }

        public void success(Result<TwitterSession> result) {
            TwitterAuthToken twitterAuthToken = (TwitterAuthToken) ((TwitterSession) result.data).getAuthToken();
            MainActivity.this.linkSocialAccount(TwitterCore.TAG, TwitterAuthProvider.getCredential(twitterAuthToken.token, twitterAuthToken.secret));
        }

        public void failure(TwitterException exception) {
            Logger.m1220e(exception.getLocalizedMessage(), new Object[0]);
        }
    }

    /* renamed from: com.cranevalley.dontendword.features.main.MainActivity$5 */
    class C05295 implements OnConnectionFailedListener {
        C05295() {
        }

        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
            Logger.m1220e(connectionResult.getErrorMessage(), new Object[0]);
        }
    }

    /* renamed from: com.cranevalley.dontendword.features.main.MainActivity$6 */
    class C05306 extends AdListener {
        C05306() {
        }

        public void onAdClosed() {
            super.onAdClosed();
            MainActivity.this.mInterstitialAd.loadAd(new Builder().build());
        }
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0521R.layout.main_activity);
        ButterKnife.bind((Activity) this);
        initializeFirebase();
        initializeBillingProcessor();
        initializeFacebook();
        initializeTwitter();
        initializeGoogle();
        initializeWordnik();
        initializeAdmob();
        initializeApplovin();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(C0521R.id.contentLayout, HomeFragment.newInstance()).commit();
        }
        if (!PreferencesHelper.isIntroSeen()) {
            startActivity(new Intent(this, IntroActivity.class));
        }
        Bundle extrasBundle = getIntent().getExtras();
        if (extrasBundle != null) {
            for (String key : extrasBundle.keySet()) {
                Logger.m1220e("Key: " + key + " Value: " + extrasBundle.get(key), new Object[0]);
            }
        }
        AudioHelper.playBackgroundMusic();
    }

    protected void onDestroy() {
        this.mAuthStateListener = null;
        this.mBillingProcessor.release();
        this.mBillingProcessor = null;
        LoginManager.getInstance().unregisterCallback(this.mCallbackManager);
        this.mSessionCallback = null;
        this.mTwitterAuthClient = null;
        this.mGoogleApiClient = null;
        super.onDestroy();
    }

    protected void onStart() {
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(this.mAuthStateListener);
    }

    protected void onStop() {
        FirebaseAuth.getInstance().removeAuthStateListener(this.mAuthStateListener);
        super.onStop();
    }

    protected void onResumeFragments() {
        super.onResumeFragments();
        String token = FirebaseInstanceId.getInstance().getToken();
        if (token != null) {
            FirebaseHelper.addToken(token);
        }
        AudioHelper.resumeMusic();
    }

    protected void onPause() {
        AudioHelper.pauseMusic();
        super.onPause();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        this.mBillingProcessor.handleActivityResult(requestCode, resultCode, data);
        this.mCallbackManager.onActivityResult(requestCode, resultCode, data);
        this.mTwitterAuthClient.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GOOGLE_REQUEST_CODE) {
            GoogleSignInResult signInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (signInResult.isSuccess() && signInResult.getSignInAccount() != null) {
                linkSocialAccount("Google", GoogleAuthProvider.getCredential(signInResult.getSignInAccount().getIdToken(), null));
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStackImmediate();
        } else {
            new AlertDialog.Builder(this).setTitle((CharSequence) "Alert!").setMessage((CharSequence) "Do you really want to quit?").setCancelable(true).setPositiveButton((CharSequence) "Yes", new C05251()).setNegativeButton((CharSequence) "No", null).show();
        }
    }

    public void onBillingInitialized() {
        if (BillingProcessor.isIabServiceAvailable(this) && this.mBillingProcessor.isOneTimePurchaseSupported()) {
            this.mBillingProcessor.loadOwnedPurchasesFromGoogle();
        }
    }

    public void onProductPurchased(String productId, TransactionDetails transactionDetails) {
        if (productId.equals(AppConstant.REMOVE_ADS_PRODUCT_ID)) {
            hideAdmobBanner();
        }
    }

    public void onPurchaseHistoryRestored() {
        if (this.mBillingProcessor.isPurchased(AppConstant.REMOVE_ADS_PRODUCT_ID)) {
            hideAdmobBanner();
        } else {
            showAdmobBanner();
        }
    }

    public void onBillingError(int errorCode, Throwable error) {
        Logger.m1220e(error.getLocalizedMessage(), new Object[0]);
    }

    public void replaceContentFragmentNoBackstack(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().setCustomAnimations(C0521R.anim.slide_in_right, C0521R.anim.slide_out_left, C0521R.anim.slide_in_left, C0521R.anim.slide_out_right).replace(C0521R.id.contentLayout, fragment).commit();
    }

    public void replaceContentFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().setCustomAnimations(C0521R.anim.slide_in_right, C0521R.anim.slide_out_left, C0521R.anim.slide_in_left, C0521R.anim.slide_out_right).replace(C0521R.id.contentLayout, fragment).addToBackStack(null).commit();
    }

    public void connectFacebook() {
        LoginManager.getInstance().logInWithReadPermissions((Activity) this, Arrays.asList(new String[]{"email", "public_profile"}));
    }

    public void connectTwitter() {
        this.mTwitterAuthClient.authorize(this, this.mSessionCallback);
    }

    public void connectGoogle() {
        startActivityForResult(Auth.GoogleSignInApi.getSignInIntent(this.mGoogleApiClient), GOOGLE_REQUEST_CODE);
    }

    public void requestWordInfosList(String letters, retrofit2.Callback<List<WordInfo>> callback) {
        this.mWordnikApi.requestWordInfosList(letters.toLowerCase(), AppConstant.WORDNIK_API_KEY, 1).enqueue(callback);
    }

    public void purchaseRemoveAds() {
        if (BillingProcessor.isIabServiceAvailable(this) && this.mBillingProcessor.isOneTimePurchaseSupported()) {
            this.mBillingProcessor.purchase(this, AppConstant.REMOVE_ADS_PRODUCT_ID);
        }
    }

    public boolean isRemoveAdsPurchased() {
        return this.mBillingProcessor.isPurchased(AppConstant.REMOVE_ADS_PRODUCT_ID);
    }

    public void showInterstitial() {
        if (this.mBillingProcessor.isInitialized() && !this.mBillingProcessor.isPurchased(AppConstant.REMOVE_ADS_PRODUCT_ID)) {
            if (AppLovinInterstitialAd.isAdReadyToDisplay(this)) {
                AppLovinInterstitialAd.show(this);
            } else if (this.mInterstitialAd.isLoaded()) {
                this.mInterstitialAd.show();
            }
        }
    }

    private void initializeFirebase() {
        this.mAuthStateListener = new C05262();
    }

    private void initializeBillingProcessor() {
        this.mBillingProcessor = new BillingProcessor(this, AppConstant.BILLING_LICENSE_KEY, this);
    }

    private void initializeFacebook() {
        this.mCallbackManager = Factory.create();
        LoginManager.getInstance().registerCallback(this.mCallbackManager, new C05273());
    }

    private void initializeTwitter() {
        TwitterAuthConfig authConfig = new TwitterAuthConfig(AppConstant.TWITTER_API_KEY, AppConstant.TWITTER_API_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        this.mTwitterAuthClient = new TwitterAuthClient();
        this.mSessionCallback = new C05284();
    }

    private void initializeGoogle() {
        this.mGoogleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, new C05295()).addApi(Auth.GOOGLE_SIGN_IN_API, new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestScopes(new Scope(Scopes.PROFILE), new Scope[0]).requestIdToken(AppConstant.GOOGLE_CLIENT_ID).build()).build();
    }

    private void initializeWordnik() {
        this.mWordnikApi = (WordnikApi) WordnikClient.getClient().create(WordnikApi.class);
    }

    private void initializeAdmob() {
        MobileAds.initialize(this, AppConstant.ADMOB_APP_ID);
        LayoutParams layoutParamsBanner = new LayoutParams(-2, -2);
        layoutParamsBanner.gravity = 17;
        this.mBannerAdView = new AdView(this);
        this.mBannerAdView.setAdUnitId(AppConstant.ADMOB_BANNER_UNIT_ID);
        this.mBannerAdView.setAdSize(AdSize.SMART_BANNER);
        this.mBannerAdView.setLayoutParams(layoutParamsBanner);
        this.mInterstitialAd = new InterstitialAd(this);
        this.mInterstitialAd.setAdUnitId(AppConstant.ADMOB_INTERSTITIAL_UNIT_ID);
        this.mInterstitialAd.setAdListener(new C05306());
        this.mInterstitialAd.loadAd(new Builder().build());
    }

    private void initializeApplovin() {
        AppLovinSdk.initializeSdk(this);
    }

    private void handleReinitialization() {
        startActivity(new Intent(this, InitializationActivity.class));
        finish();
    }

    private void linkSocialAccount(final String providerName, final AuthCredential authCredential) {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null) {
            firebaseUser.linkWithCredential(authCredential).addOnCompleteListener((Activity) this, new OnCompleteListener<AuthResult>() {

                /* renamed from: com.cranevalley.dontendword.features.main.MainActivity$7$1 */
                class C05321 implements OnClickListener {

                    /* renamed from: com.cranevalley.dontendword.features.main.MainActivity$7$1$1 */
                    class C05311 implements OnFailureListener {
                        C05311() {
                        }

                        public void onFailure(@NonNull Exception exception) {
                            Logger.m1220e(exception.getLocalizedMessage(), new Object[0]);
                        }
                    }

                    C05321() {
                    }

                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseAuth.getInstance().signInWithCredential(authCredential).addOnFailureListener(MainActivity.this, new C05311());
                    }
                }

                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        MainActivity.this.handleReinitialization();
                    } else {
                        new AlertDialog.Builder(MainActivity.this).setTitle((CharSequence) "Error!").setMessage(providerName + " account is already linked with another account. Do you want to switch to that account?").setCancelable(true).setPositiveButton((CharSequence) "Yes", new C05321()).setNegativeButton((CharSequence) "No", null).show();
                    }
                }
            });
        }
    }

    private void showAdmobBanner() {
        if (this.mBannerAdView != null) {
            this.bannerLayout.addView(this.mBannerAdView);
            this.mBannerAdView.loadAd(new Builder().build());
        }
    }

    private void hideAdmobBanner() {
        if (this.mBannerAdView != null) {
            this.bannerLayout.removeView(this.mBannerAdView);
        }
    }
}
