package io.fabric.sdk.android.services.common;

import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import com.twitter.sdk.android.core.TwitterApiErrorConstants;

public class ResponseParser {
    public static final int ResponseActionDiscard = 0;
    public static final int ResponseActionRetry = 1;

    public static int parse(int statusCode) {
        if (statusCode >= Callback.DEFAULT_DRAG_ANIMATION_DURATION && statusCode <= TwitterApiErrorConstants.DEVICE_REGISTRATION_RATE_EXCEEDED) {
            return 0;
        }
        if (statusCode >= TwitterApiErrorConstants.REGISTRATION_INVALID_INPUT && statusCode <= 399) {
            return 1;
        }
        if (statusCode >= 400 && statusCode <= 499) {
            return 0;
        }
        if (statusCode >= 500) {
            return 1;
        }
        return 1;
    }
}
