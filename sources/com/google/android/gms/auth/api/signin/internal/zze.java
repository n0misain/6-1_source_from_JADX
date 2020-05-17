package com.google.android.gms.auth.api.signin.internal;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.PendingResults;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.internal.zzbdb;
import com.google.android.gms.internal.zzbec;
import com.google.android.gms.internal.zzbgb;
import java.util.HashSet;

public final class zze {
    private static zzbgb zzaml = new zzbgb("GoogleSignInCommon", new String[0]);

    public static GoogleSignInResult getSignInResultFromIntent(Intent intent) {
        if (intent == null || (!intent.hasExtra("googleSignInStatus") && !intent.hasExtra("googleSignInAccount"))) {
            return null;
        }
        GoogleSignInAccount googleSignInAccount = (GoogleSignInAccount) intent.getParcelableExtra("googleSignInAccount");
        Status status = (Status) intent.getParcelableExtra("googleSignInStatus");
        if (googleSignInAccount != null) {
            status = Status.zzaBm;
        }
        return new GoogleSignInResult(googleSignInAccount, status);
    }

    public static Intent zza(Context context, GoogleSignInOptions googleSignInOptions) {
        zzaml.zzb("GoogleSignInCommon", "getSignInIntent()");
        Parcelable signInConfiguration = new SignInConfiguration(context.getPackageName(), googleSignInOptions);
        Intent intent = new Intent("com.google.android.gms.auth.GOOGLE_SIGN_IN");
        intent.setClass(context, SignInHubActivity.class);
        intent.putExtra("config", signInConfiguration);
        return intent;
    }

    public static OptionalPendingResult<GoogleSignInResult> zza(GoogleApiClient googleApiClient, Context context, GoogleSignInOptions googleSignInOptions) {
        Result googleSignInResult;
        zzy zzaj = zzy.zzaj(context);
        zzaml.zzb("GoogleSignInCommon", "getEligibleSavedSignInResult()");
        zzbo.zzu(googleSignInOptions);
        GoogleSignInOptions zzmO = zzaj.zzmO();
        if (zzmO != null) {
            Account account = zzmO.getAccount();
            Account account2 = googleSignInOptions.getAccount();
            boolean equals = account == null ? account2 == null : account.equals(account2);
            if (equals && !googleSignInOptions.zzmB() && ((!googleSignInOptions.isIdTokenRequested() || (zzmO.isIdTokenRequested() && googleSignInOptions.getServerClientId().equals(zzmO.getServerClientId()))) && new HashSet(zzmO.zzmA()).containsAll(new HashSet(googleSignInOptions.zzmA())))) {
                GoogleSignInAccount zzmN = zzaj.zzmN();
                if (!(zzmN == null || zzmN.zzmw())) {
                    googleSignInResult = new GoogleSignInResult(zzmN, Status.zzaBm);
                    if (googleSignInResult == null) {
                        zzaml.zzb("GoogleSignInCommon", "Eligible saved sign in result found");
                        return PendingResults.zzb(googleSignInResult, googleApiClient);
                    }
                    zzaml.zzb("GoogleSignInCommon", "trySilentSignIn()");
                    return new zzbec(googleApiClient.zzd(new zzf(googleApiClient, zzaj, googleSignInOptions)));
                }
            }
        }
        googleSignInResult = null;
        if (googleSignInResult == null) {
            zzaml.zzb("GoogleSignInCommon", "trySilentSignIn()");
            return new zzbec(googleApiClient.zzd(new zzf(googleApiClient, zzaj, googleSignInOptions)));
        }
        zzaml.zzb("GoogleSignInCommon", "Eligible saved sign in result found");
        return PendingResults.zzb(googleSignInResult, googleApiClient);
    }

    public static PendingResult<Status> zza(GoogleApiClient googleApiClient, Context context) {
        zzaml.zzb("GoogleSignInCommon", "Signing out");
        zzai(context);
        return googleApiClient.zze(new zzh(googleApiClient));
    }

    private static void zzai(Context context) {
        zzy.zzaj(context).zzmP();
        for (GoogleApiClient zzpl : GoogleApiClient.zzpk()) {
            zzpl.zzpl();
        }
        zzbdb.zzql();
    }

    public static PendingResult<Status> zzb(GoogleApiClient googleApiClient, Context context) {
        zzaml.zzb("GoogleSignInCommon", "Revoking access");
        zzai(context);
        return googleApiClient.zze(new zzj(googleApiClient));
    }
}
