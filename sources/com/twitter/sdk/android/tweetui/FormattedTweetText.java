package com.twitter.sdk.android.tweetui;

import java.util.ArrayList;
import java.util.List;

class FormattedTweetText {
    final List<FormattedMediaEntity> mediaEntities = new ArrayList();
    String text;
    final List<FormattedUrlEntity> urlEntities = new ArrayList();

    FormattedTweetText() {
    }
}
