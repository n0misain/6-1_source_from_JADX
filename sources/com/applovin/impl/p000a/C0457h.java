package com.applovin.impl.p000a;

import com.twitter.sdk.android.core.TwitterApiErrorConstants;

/* renamed from: com.applovin.impl.a.h */
public enum C0457h {
    UNSPECIFIED(-1),
    XML_PARSING(100),
    GENERAL_WRAPPER_ERROR(TwitterApiErrorConstants.REGISTRATION_INVALID_INPUT),
    TIMED_OUT(301),
    WRAPPER_LIMIT_REACHED(TwitterApiErrorConstants.REGISTRATION_OPERATION_FAILED),
    NO_WRAPPER_RESPONSE(TwitterApiErrorConstants.REGISTRATION_PHONE_NORMALIZATION_FAILED),
    GENERAL_LINEAR_ERROR(400),
    NO_MEDIA_FILE_PROVIDED(401),
    MEDIA_FILE_TIMEOUT(402),
    MEDIA_FILE_ERROR(405),
    GENERAL_COMPANION_AD_ERROR(600),
    UNABLE_TO_FETCH_COMPANION_AD_RESOURCE(603),
    CAN_NOT_FIND_COMPANION_AD_RESOURCE(604);
    
    /* renamed from: n */
    private final int f119n;

    private C0457h(int i) {
        this.f119n = i;
    }

    /* renamed from: a */
    public int m211a() {
        return this.f119n;
    }
}
