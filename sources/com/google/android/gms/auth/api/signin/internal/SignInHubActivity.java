package com.google.android.gms.auth.api.signin.internal;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.SignInAccount;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;

@KeepName
public class SignInHubActivity extends FragmentActivity {
    private int zzamA;
    private Intent zzamB;
    private zzy zzamw;
    private boolean zzamx = false;
    private SignInConfiguration zzamy;
    private boolean zzamz;

    class zza implements LoaderCallbacks<Void> {
        private /* synthetic */ SignInHubActivity zzamC;

        private zza(SignInHubActivity signInHubActivity) {
            this.zzamC = signInHubActivity;
        }

        public final Loader<Void> onCreateLoader(int i, Bundle bundle) {
            return new zzb(this.zzamC, GoogleApiClient.zzpk());
        }

        public final /* synthetic */ void onLoadFinished(Loader loader, Object obj) {
            this.zzamC.setResult(this.zzamC.zzamA, this.zzamC.zzamB);
            this.zzamC.finish();
        }

        public final void onLoaderReset(Loader<Void> loader) {
        }
    }

    private final void zzU(int i) {
        Parcelable status = new Status(i);
        Intent intent = new Intent();
        intent.putExtra("googleSignInStatus", status);
        setResult(0, intent);
        finish();
    }

    private final void zzmM() {
        getSupportLoaderManager().initLoader(0, null, new zza());
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return true;
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (!this.zzamx) {
            setResult(0);
            switch (i) {
                case 40962:
                    if (intent != null) {
                        SignInAccount signInAccount = (SignInAccount) intent.getParcelableExtra(GoogleSignInApi.EXTRA_SIGN_IN_ACCOUNT);
                        if (signInAccount != null && signInAccount.zzmD() != null) {
                            Parcelable zzmD = signInAccount.zzmD();
                            this.zzamw.zza(zzmD, this.zzamy.zzmL());
                            intent.removeExtra(GoogleSignInApi.EXTRA_SIGN_IN_ACCOUNT);
                            intent.putExtra("googleSignInAccount", zzmD);
                            this.zzamz = true;
                            this.zzamA = i2;
                            this.zzamB = intent;
                            zzmM();
                            return;
                        } else if (intent.hasExtra("errorCode")) {
                            zzU(intent.getIntExtra("errorCode", 8));
                            return;
                        }
                    }
                    zzU(8);
                    return;
                default:
                    return;
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.zzamw = zzy.zzaj(this);
        Intent intent = getIntent();
        if (!"com.google.android.gms.auth.GOOGLE_SIGN_IN".equals(intent.getAction())) {
            String str = "AuthSignInClient";
            String str2 = "Unknown action: ";
            String valueOf = String.valueOf(intent.getAction());
            Log.e(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            finish();
        }
        this.zzamy = (SignInConfiguration) intent.getParcelableExtra("config");
        if (this.zzamy == null) {
            Log.e("AuthSignInClient", "Activity started with invalid configuration.");
            setResult(0);
            finish();
        } else if (bundle == null) {
            Intent intent2 = new Intent("com.google.android.gms.auth.GOOGLE_SIGN_IN");
            intent2.setPackage("com.google.android.gms");
            intent2.putExtra("config", this.zzamy);
            try {
                startActivityForResult(intent2, 40962);
            } catch (ActivityNotFoundException e) {
                this.zzamx = true;
                Log.w("AuthSignInClient", "Could not launch sign in Intent. Google Play Service is probably being updated...");
                zzU(17);
            }
        } else {
            this.zzamz = bundle.getBoolean("signingInGoogleApiClients");
            if (this.zzamz) {
                this.zzamA = bundle.getInt("signInResultCode");
                this.zzamB = (Intent) bundle.getParcelable("signInResultData");
                zzmM();
            }
        }
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("signingInGoogleApiClients", this.zzamz);
        if (this.zzamz) {
            bundle.putInt("signInResultCode", this.zzamA);
            bundle.putParcelable("signInResultData", this.zzamB);
        }
    }
}
