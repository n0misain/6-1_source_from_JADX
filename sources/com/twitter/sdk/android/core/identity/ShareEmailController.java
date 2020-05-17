package com.twitter.sdk.android.core.identity;

import android.os.Bundle;
import android.os.ResultReceiver;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.User;
import io.fabric.sdk.android.Fabric;

class ShareEmailController {
    private static final String EMPTY_EMAIL = "";
    private final ShareEmailClient emailClient;
    private final ResultReceiver resultReceiver;

    /* renamed from: com.twitter.sdk.android.core.identity.ShareEmailController$1 */
    class C10041 extends Callback<User> {
        C10041() {
        }

        public void success(Result<User> result) {
            ShareEmailController.this.handleSuccess((User) result.data);
        }

        public void failure(TwitterException exception) {
            Fabric.getLogger().mo4336e(TwitterCore.TAG, "Failed to get email address.", exception);
            ShareEmailController.this.sendResultCodeError(new TwitterException("Failed to get email address."));
        }
    }

    public ShareEmailController(ShareEmailClient emailClient, ResultReceiver resultReceiver) {
        this.emailClient = emailClient;
        this.resultReceiver = resultReceiver;
    }

    public void executeRequest() {
        this.emailClient.getEmail(newCallback());
    }

    Callback<User> newCallback() {
        return new C10041();
    }

    void handleSuccess(User user) {
        if (user.email == null) {
            sendResultCodeError(new TwitterException("Your application may not have access to email addresses or the user may not have an email address. To request access, please visit https://support.twitter.com/forms/platform."));
        } else if ("".equals(user.email)) {
            sendResultCodeError(new TwitterException("This user does not have an email address."));
        } else {
            sendResultCodeOk(user.email);
        }
    }

    void sendResultCodeOk(String email) {
        Bundle bundle = new Bundle();
        bundle.putString("email", email);
        this.resultReceiver.send(-1, bundle);
    }

    void sendResultCodeError(TwitterException exception) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("error", exception);
        this.resultReceiver.send(1, bundle);
    }

    public void cancelRequest() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("msg", "The user chose not to share their email address at this time.");
        this.resultReceiver.send(0, bundle);
    }
}
