package com.twitter.sdk.android.core;

public class TwitterAuthException extends TwitterException {
    private static final long serialVersionUID = 577033016879783994L;

    public TwitterAuthException(String detailMessage) {
        super(detailMessage);
    }

    public TwitterAuthException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }
}
