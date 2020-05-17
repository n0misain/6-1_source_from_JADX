package com.twitter.sdk.android.core.identity;

import android.app.Activity;
import com.twitter.sdk.android.core.TwitterCore;
import io.fabric.sdk.android.Fabric;
import java.util.concurrent.atomic.AtomicReference;

class AuthState {
    final AtomicReference<AuthHandler> authHandlerRef = new AtomicReference(null);

    AuthState() {
    }

    public boolean beginAuthorize(Activity activity, AuthHandler authHandler) {
        boolean result = false;
        if (isAuthorizeInProgress()) {
            Fabric.getLogger().mo4346w(TwitterCore.TAG, "Authorize already in progress");
        } else if (authHandler.authorize(activity)) {
            result = this.authHandlerRef.compareAndSet(null, authHandler);
            if (!result) {
                Fabric.getLogger().mo4346w(TwitterCore.TAG, "Failed to update authHandler, authorize already in progress.");
            }
        }
        return result;
    }

    public void endAuthorize() {
        this.authHandlerRef.set(null);
    }

    public boolean isAuthorizeInProgress() {
        return this.authHandlerRef.get() != null;
    }

    public AuthHandler getAuthHandler() {
        return (AuthHandler) this.authHandlerRef.get();
    }
}
