package com.twitter.sdk.android.core.internal;

import android.text.TextUtils;
import com.twitter.sdk.android.core.models.User;

public final class UserUtils {

    public enum AvatarSize {
        NORMAL("_normal"),
        BIGGER("_bigger"),
        MINI("_mini"),
        ORIGINAL("_original"),
        REASONABLY_SMALL("_reasonably_small");
        
        private final String suffix;

        private AvatarSize(String suffix) {
            this.suffix = suffix;
        }

        String getSuffix() {
            return this.suffix;
        }
    }

    private UserUtils() {
    }

    public static String getProfileImageUrlHttps(User user, AvatarSize size) {
        if (user == null || user.profileImageUrlHttps == null) {
            return null;
        }
        String url = user.profileImageUrlHttps;
        if (size == null || url == null) {
            return url;
        }
        switch (size) {
            case NORMAL:
            case BIGGER:
            case MINI:
            case ORIGINAL:
            case REASONABLY_SMALL:
                return url.replace(AvatarSize.NORMAL.getSuffix(), size.getSuffix());
            default:
                return url;
        }
    }

    public static CharSequence formatScreenName(CharSequence screenName) {
        if (TextUtils.isEmpty(screenName)) {
            return "";
        }
        return screenName.charAt(0) != '@' ? "@" + screenName : screenName;
    }
}
