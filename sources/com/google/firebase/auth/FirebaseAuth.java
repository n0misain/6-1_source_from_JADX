package com.google.firebase.auth;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.telephony.PhoneNumberUtils;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.common.util.zzq;
import com.google.android.gms.internal.aad;
import com.google.android.gms.internal.aae;
import com.google.android.gms.internal.iq;
import com.google.android.gms.internal.jz;
import com.google.android.gms.internal.ke;
import com.google.android.gms.internal.kh;
import com.google.android.gms.internal.kx;
import com.google.android.gms.internal.lf;
import com.google.android.gms.internal.ll;
import com.google.android.gms.internal.lp;
import com.google.android.gms.internal.lr;
import com.google.android.gms.internal.ls;
import com.google.android.gms.internal.lw;
import com.google.android.gms.internal.ly;
import com.google.android.gms.internal.lz;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

public class FirebaseAuth implements aad {
    private static FirebaseAuth zzbWf;
    private static Map<String, FirebaseAuth> zzbgQ = new ArrayMap();
    private FirebaseApp zzbVZ;
    private List<AuthStateListener> zzbVd;
    private List<IdTokenListener> zzbWa;
    private iq zzbWb;
    private FirebaseUser zzbWc;
    private ly zzbWd;
    private lz zzbWe;

    public interface AuthStateListener {
        void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth);
    }

    public interface IdTokenListener {
        void onIdTokenChanged(@NonNull FirebaseAuth firebaseAuth);
    }

    class zza implements ll {
        private /* synthetic */ FirebaseAuth zzbWh;

        zza(FirebaseAuth firebaseAuth) {
            this.zzbWh = firebaseAuth;
        }

        public final void zza(@NonNull kx kxVar, @NonNull FirebaseUser firebaseUser) {
            zzbo.zzu(kxVar);
            zzbo.zzu(firebaseUser);
            firebaseUser.zza(kxVar);
            this.zzbWh.zza(firebaseUser, kxVar, true);
        }
    }

    class zzb extends zza implements ll, lw {
        private /* synthetic */ FirebaseAuth zzbWh;

        zzb(FirebaseAuth firebaseAuth) {
            this.zzbWh = firebaseAuth;
            super(firebaseAuth);
        }

        public final void onError(Status status) {
            if (status.getStatusCode() == 17011 || status.getStatusCode() == 17021 || status.getStatusCode() == 17005) {
                this.zzbWh.signOut();
            }
        }
    }

    public FirebaseAuth(FirebaseApp firebaseApp) {
        this(firebaseApp, ke.zza(firebaseApp.getApplicationContext(), new kh(firebaseApp.getOptions().getApiKey()).zzEP()), new ly(firebaseApp.getApplicationContext(), firebaseApp.zzEr()));
    }

    private FirebaseAuth(FirebaseApp firebaseApp, iq iqVar, ly lyVar) {
        this.zzbVZ = (FirebaseApp) zzbo.zzu(firebaseApp);
        this.zzbWb = (iq) zzbo.zzu(iqVar);
        this.zzbWd = (ly) zzbo.zzu(lyVar);
        this.zzbWa = new CopyOnWriteArrayList();
        this.zzbVd = new CopyOnWriteArrayList();
        this.zzbWe = lz.zzFb();
        this.zzbWc = this.zzbWd.zzFa();
        if (this.zzbWc != null) {
            kx zzh = this.zzbWd.zzh(this.zzbWc);
            if (zzh != null) {
                zza(this.zzbWc, zzh, false);
            }
        }
    }

    @Keep
    public static FirebaseAuth getInstance() {
        return zzb(FirebaseApp.getInstance());
    }

    @Keep
    public static FirebaseAuth getInstance(@NonNull FirebaseApp firebaseApp) {
        return zzb(firebaseApp);
    }

    private final void zza(@Nullable FirebaseUser firebaseUser) {
        if (firebaseUser != null) {
            String valueOf = String.valueOf(firebaseUser.getUid());
            Log.d("FirebaseAuth", new StringBuilder(String.valueOf(valueOf).length() + 45).append("Notifying id token listeners about user ( ").append(valueOf).append(" ).").toString());
        } else {
            Log.d("FirebaseAuth", "Notifying id token listeners about a sign-out event.");
        }
        this.zzbWe.execute(new zzh(this, new aae(firebaseUser != null ? firebaseUser.zzEI() : null)));
    }

    private static synchronized FirebaseAuth zzb(@NonNull FirebaseApp firebaseApp) {
        FirebaseAuth firebaseAuth;
        synchronized (FirebaseAuth.class) {
            firebaseAuth = (FirebaseAuth) zzbgQ.get(firebaseApp.zzEr());
            if (firebaseAuth == null) {
                firebaseAuth = new lr(firebaseApp);
                firebaseApp.zza((aad) firebaseAuth);
                if (zzbWf == null) {
                    zzbWf = firebaseAuth;
                }
                zzbgQ.put(firebaseApp.zzEr(), firebaseAuth);
            }
        }
        return firebaseAuth;
    }

    private final void zzb(@Nullable FirebaseUser firebaseUser) {
        if (firebaseUser != null) {
            String valueOf = String.valueOf(firebaseUser.getUid());
            Log.d("FirebaseAuth", new StringBuilder(String.valueOf(valueOf).length() + 47).append("Notifying auth state listeners about user ( ").append(valueOf).append(" ).").toString());
        } else {
            Log.d("FirebaseAuth", "Notifying auth state listeners about a sign-out event.");
        }
        this.zzbWe.execute(new zzi(this));
    }

    public void addAuthStateListener(@NonNull AuthStateListener authStateListener) {
        this.zzbVd.add(authStateListener);
        this.zzbWe.execute(new zzg(this, authStateListener));
    }

    public void addIdTokenListener(@NonNull IdTokenListener idTokenListener) {
        this.zzbWa.add(idTokenListener);
        this.zzbWe.execute(new zzf(this, idTokenListener));
    }

    @NonNull
    public Task<Void> applyActionCode(@NonNull String str) {
        zzbo.zzcF(str);
        return this.zzbWb.zze(this.zzbVZ, str);
    }

    @NonNull
    public Task<ActionCodeResult> checkActionCode(@NonNull String str) {
        zzbo.zzcF(str);
        return this.zzbWb.zzd(this.zzbVZ, str);
    }

    @NonNull
    public Task<Void> confirmPasswordReset(@NonNull String str, @NonNull String str2) {
        zzbo.zzcF(str);
        zzbo.zzcF(str2);
        return this.zzbWb.zza(this.zzbVZ, str, str2);
    }

    @NonNull
    public Task<AuthResult> createUserWithEmailAndPassword(@NonNull String str, @NonNull String str2) {
        zzbo.zzcF(str);
        zzbo.zzcF(str2);
        return this.zzbWb.zza(this.zzbVZ, str, str2, new zza(this));
    }

    @NonNull
    public Task<ProviderQueryResult> fetchProvidersForEmail(@NonNull String str) {
        zzbo.zzcF(str);
        return this.zzbWb.zza(this.zzbVZ, str);
    }

    @Nullable
    public FirebaseUser getCurrentUser() {
        return this.zzbWc;
    }

    public void removeAuthStateListener(@NonNull AuthStateListener authStateListener) {
        this.zzbVd.remove(authStateListener);
    }

    public void removeIdTokenListener(@NonNull IdTokenListener idTokenListener) {
        this.zzbWa.remove(idTokenListener);
    }

    @NonNull
    public Task<Void> sendPasswordResetEmail(@NonNull String str) {
        zzbo.zzcF(str);
        return this.zzbWb.zzb(this.zzbVZ, str);
    }

    @NonNull
    public Task<AuthResult> signInAnonymously() {
        return (this.zzbWc == null || !this.zzbWc.isAnonymous()) ? this.zzbWb.zza(this.zzbVZ, new zza(this)) : Tasks.forResult(new lp((ls) this.zzbWc));
    }

    @NonNull
    public Task<AuthResult> signInWithCredential(@NonNull AuthCredential authCredential) {
        zzbo.zzu(authCredential);
        if (authCredential instanceof EmailAuthCredential) {
            EmailAuthCredential emailAuthCredential = (EmailAuthCredential) authCredential;
            return this.zzbWb.zzb(this.zzbVZ, emailAuthCredential.getEmail(), emailAuthCredential.getPassword(), new zza(this));
        } else if (!(authCredential instanceof PhoneAuthCredential)) {
            return this.zzbWb.zza(this.zzbVZ, authCredential, new zza(this));
        } else {
            return this.zzbWb.zza(this.zzbVZ, (PhoneAuthCredential) authCredential, new zza(this));
        }
    }

    @NonNull
    public Task<AuthResult> signInWithCustomToken(@NonNull String str) {
        zzbo.zzcF(str);
        return this.zzbWb.zza(this.zzbVZ, str, new zza(this));
    }

    @NonNull
    public Task<AuthResult> signInWithEmailAndPassword(@NonNull String str, @NonNull String str2) {
        zzbo.zzcF(str);
        zzbo.zzcF(str2);
        return this.zzbWb.zzb(this.zzbVZ, str, str2, new zza(this));
    }

    public void signOut() {
        zzED();
    }

    @NonNull
    public Task<String> verifyPasswordResetCode(@NonNull String str) {
        zzbo.zzcF(str);
        return this.zzbWb.zzf(this.zzbVZ, str);
    }

    public final void zzED() {
        if (this.zzbWc != null) {
            ly lyVar = this.zzbWd;
            zzbo.zzu(this.zzbWc);
            lyVar.clear(String.format("com.google.firebase.auth.GET_TOKEN_RESPONSE.%s", new Object[]{r1.getUid()}));
            this.zzbWc = null;
        }
        this.zzbWd.clear("com.google.firebase.auth.FIREBASE_USER");
        zza(null);
        zzb(null);
    }

    @NonNull
    public final Task<Void> zza(@NonNull FirebaseUser firebaseUser, @NonNull AuthCredential authCredential) {
        zzbo.zzu(firebaseUser);
        zzbo.zzu(authCredential);
        if (!EmailAuthCredential.class.isAssignableFrom(authCredential.getClass())) {
            return authCredential instanceof PhoneAuthCredential ? this.zzbWb.zzb(this.zzbVZ, firebaseUser, (PhoneAuthCredential) authCredential, new zzb(this)) : this.zzbWb.zza(this.zzbVZ, firebaseUser, authCredential, new zzb(this));
        } else {
            EmailAuthCredential emailAuthCredential = (EmailAuthCredential) authCredential;
            return this.zzbWb.zza(this.zzbVZ, firebaseUser, emailAuthCredential.getEmail(), emailAuthCredential.getPassword(), new zzb(this));
        }
    }

    @NonNull
    public final Task<Void> zza(@NonNull FirebaseUser firebaseUser, @NonNull PhoneAuthCredential phoneAuthCredential) {
        zzbo.zzu(firebaseUser);
        zzbo.zzu(phoneAuthCredential);
        return this.zzbWb.zza(this.zzbVZ, firebaseUser, phoneAuthCredential, new zzb(this));
    }

    @NonNull
    public final Task<Void> zza(@NonNull FirebaseUser firebaseUser, @NonNull UserProfileChangeRequest userProfileChangeRequest) {
        zzbo.zzu(firebaseUser);
        zzbo.zzu(userProfileChangeRequest);
        return this.zzbWb.zza(this.zzbVZ, firebaseUser, userProfileChangeRequest, new zzb(this));
    }

    @NonNull
    public final Task<AuthResult> zza(@NonNull FirebaseUser firebaseUser, @NonNull String str) {
        zzbo.zzcF(str);
        zzbo.zzu(firebaseUser);
        return this.zzbWb.zzc(this.zzbVZ, firebaseUser, str, new zzb(this));
    }

    @NonNull
    public final Task<GetTokenResult> zza(@Nullable FirebaseUser firebaseUser, boolean z) {
        if (firebaseUser == null) {
            return Tasks.forException(jz.zzK(new Status(17495)));
        }
        kx zzEG = this.zzbWc.zzEG();
        return (!zzEG.isValid() || z) ? this.zzbWb.zza(this.zzbVZ, firebaseUser, zzEG.zzES(), new zzj(this)) : Tasks.forResult(new GetTokenResult(zzEG.getAccessToken()));
    }

    public final void zza(@NonNull FirebaseUser firebaseUser, @NonNull kx kxVar, boolean z) {
        Object obj;
        Object obj2 = 1;
        zzbo.zzu(firebaseUser);
        zzbo.zzu(kxVar);
        if (this.zzbWc == null) {
            obj = 1;
        } else {
            obj = !this.zzbWc.zzEG().getAccessToken().equals(kxVar.getAccessToken()) ? 1 : null;
            boolean equals = this.zzbWc.getUid().equals(firebaseUser.getUid());
            if (equals && obj == null) {
                obj = null;
            } else {
                int i = 1;
            }
            if (equals) {
                obj2 = null;
            }
        }
        zzbo.zzu(firebaseUser);
        if (this.zzbWc == null) {
            this.zzbWc = firebaseUser;
        } else {
            this.zzbWc.zzax(firebaseUser.isAnonymous());
            this.zzbWc.zzP(firebaseUser.getProviderData());
        }
        if (z) {
            this.zzbWd.zzg(this.zzbWc);
        }
        if (obj != null) {
            if (this.zzbWc != null) {
                this.zzbWc.zza(kxVar);
            }
            zza(this.zzbWc);
        }
        if (obj2 != null) {
            zzb(this.zzbWc);
        }
        if (z) {
            this.zzbWd.zza(firebaseUser, kxVar);
        }
    }

    @NonNull
    public final void zza(@NonNull String str, long j, TimeUnit timeUnit, @NonNull OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, @Nullable Activity activity, @NonNull Executor executor, boolean z) {
        long convert;
        Context applicationContext = this.zzbVZ.getApplicationContext();
        zzbo.zzu(applicationContext);
        zzbo.zzcF(str);
        TelephonyManager telephonyManager = (TelephonyManager) applicationContext.getSystemService("phone");
        Object simCountryIso = telephonyManager != null ? telephonyManager.getSimCountryIso() : null;
        if (TextUtils.isEmpty(simCountryIso)) {
            Locale locale = Locale.getDefault();
            simCountryIso = locale != null ? locale.getCountry() : null;
        }
        String toUpperCase = TextUtils.isEmpty(simCountryIso) ? "US" : simCountryIso.toUpperCase(Locale.US);
        String stripSeparators = PhoneNumberUtils.stripSeparators(str);
        if (zzq.zzse()) {
            CharSequence formatNumberToE164 = PhoneNumberUtils.formatNumberToE164(stripSeparators, toUpperCase);
            if (TextUtils.isEmpty(formatNumberToE164)) {
                toUpperCase = stripSeparators;
            } else {
                CharSequence charSequence = formatNumberToE164;
                convert = TimeUnit.SECONDS.convert(j, timeUnit);
                if (convert >= 0 || convert > 120) {
                    throw new IllegalArgumentException("We only support 0-120 seconds for sms-auto-retrieval timeout");
                }
                this.zzbWb.zza(this.zzbVZ, new lf(stripSeparators, convert < 30 ? 30 : convert, z), onVerificationStateChangedCallbacks, activity, executor);
                return;
            }
        }
        if ("US".equals(toUpperCase)) {
            if (stripSeparators == null) {
                toUpperCase = null;
            } else {
                int length = stripSeparators.length();
                if (!stripSeparators.startsWith("+")) {
                    if (length == 11 && stripSeparators.startsWith(AppEventsConstants.EVENT_PARAM_VALUE_YES)) {
                        toUpperCase = "+".concat(stripSeparators);
                    } else if (length == 10) {
                        toUpperCase = "+1".concat(stripSeparators);
                    }
                }
                toUpperCase = stripSeparators;
            }
        }
        convert = TimeUnit.SECONDS.convert(j, timeUnit);
        if (convert >= 0) {
        }
        throw new IllegalArgumentException("We only support 0-120 seconds for sms-auto-retrieval timeout");
        stripSeparators = toUpperCase;
        convert = TimeUnit.SECONDS.convert(j, timeUnit);
        if (convert >= 0) {
        }
        throw new IllegalArgumentException("We only support 0-120 seconds for sms-auto-retrieval timeout");
    }

    @NonNull
    public final Task<GetTokenResult> zzaw(boolean z) {
        return zza(this.zzbWc, z);
    }

    public final Task<AuthResult> zzb(@NonNull FirebaseUser firebaseUser, @NonNull AuthCredential authCredential) {
        zzbo.zzu(firebaseUser);
        zzbo.zzu(authCredential);
        if (!EmailAuthCredential.class.isAssignableFrom(authCredential.getClass())) {
            return authCredential instanceof PhoneAuthCredential ? this.zzbWb.zzc(this.zzbVZ, firebaseUser, authCredential, new zzb(this)) : this.zzbWb.zzb(this.zzbVZ, firebaseUser, authCredential, new zzb(this));
        } else {
            EmailAuthCredential emailAuthCredential = (EmailAuthCredential) authCredential;
            return this.zzbWb.zzb(this.zzbVZ, firebaseUser, emailAuthCredential.getEmail(), emailAuthCredential.getPassword(), new zzb(this));
        }
    }

    @NonNull
    public final Task<Void> zzb(@NonNull FirebaseUser firebaseUser, @NonNull String str) {
        zzbo.zzu(firebaseUser);
        zzbo.zzcF(str);
        return this.zzbWb.zza(this.zzbVZ, firebaseUser, str, new zzb(this));
    }

    @NonNull
    public final Task<Void> zzc(@NonNull FirebaseUser firebaseUser) {
        zzbo.zzu(firebaseUser);
        return this.zzbWb.zza(this.zzbVZ, firebaseUser, new zzb(this));
    }

    @NonNull
    public final Task<AuthResult> zzc(@NonNull FirebaseUser firebaseUser, @NonNull AuthCredential authCredential) {
        zzbo.zzu(authCredential);
        zzbo.zzu(firebaseUser);
        return this.zzbWb.zzd(this.zzbVZ, firebaseUser, authCredential, new zzb(this));
    }

    @NonNull
    public final Task<Void> zzc(@NonNull FirebaseUser firebaseUser, @NonNull String str) {
        zzbo.zzu(firebaseUser);
        zzbo.zzcF(str);
        return this.zzbWb.zzb(this.zzbVZ, firebaseUser, str, new zzb(this));
    }

    @NonNull
    public final Task<Void> zzd(@NonNull FirebaseUser firebaseUser) {
        zzbo.zzu(firebaseUser);
        return this.zzbWb.zza(firebaseUser, new zzk(this, firebaseUser));
    }

    @NonNull
    public final Task<Void> zzgp(@NonNull String str) {
        zzbo.zzcF(str);
        return this.zzbWb.zzc(this.zzbVZ, str);
    }
}
