package com.cranevalley.dontendword.features.initialization;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.cranevalley.dontendword.C0521R;
import com.cranevalley.dontendword.features.bases.BaseActivity;
import com.cranevalley.dontendword.features.main.MainActivity;
import com.cranevalley.dontendword.helpers.FirebaseHelper;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuth.AuthStateListener;
import com.google.firebase.auth.FirebaseUser;
import com.orhanobut.logger.Logger;

public class InitializationActivity extends BaseActivity {
    private AuthStateListener mAuthStateListener;

    /* renamed from: com.cranevalley.dontendword.features.initialization.InitializationActivity$1 */
    class C05241 implements AuthStateListener {

        /* renamed from: com.cranevalley.dontendword.features.initialization.InitializationActivity$1$1 */
        class C05231 implements OnFailureListener {
            C05231() {
            }

            public void onFailure(@NonNull Exception exception) {
                Logger.m1220e(exception.getLocalizedMessage(), new Object[0]);
            }
        }

        C05241() {
        }

        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
            if (firebaseUser != null) {
                InitializationActivity.this.handleInitializationFinish(firebaseUser);
            } else {
                FirebaseAuth.getInstance().signInAnonymously().addOnFailureListener(InitializationActivity.this, new C05231());
            }
        }
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0521R.layout.initialization_activity);
        initializeFirebase();
    }

    protected void onDestroy() {
        this.mAuthStateListener = null;
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

    private void initializeFirebase() {
        this.mAuthStateListener = new C05241();
    }

    private void handleInitializationFinish(FirebaseUser firebaseUser) {
        String displayName = FirebaseHelper.getDisplayName(firebaseUser);
        String photoUrl = FirebaseHelper.getPhotoUrl(firebaseUser);
        if (displayName != null) {
            FirebaseHelper.setDisplayName(displayName);
        }
        if (photoUrl != null) {
            FirebaseHelper.setPhotoUrl(photoUrl);
        }
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
