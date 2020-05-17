package com.twitter.sdk.android.tweetcomposer;

import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.tweetcomposer.internal.CardService;

class ComposerApiClient extends TwitterApiClient {
    ComposerApiClient(TwitterSession session) {
        super(session);
    }

    StatusesService getComposerStatusesService() {
        return (StatusesService) getService(StatusesService.class);
    }

    CardService getCardService() {
        return (CardService) getService(CardService.class);
    }
}
