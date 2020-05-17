package com.twitter.sdk.android.tweetui;

import com.twitter.sdk.android.core.models.MediaEntity;

class FormattedMediaEntity extends FormattedUrlEntity {
    final String mediaUrlHttps;
    final String type;

    FormattedMediaEntity(MediaEntity entity) {
        super(entity);
        this.type = entity.type;
        this.mediaUrlHttps = entity.mediaUrlHttps;
    }
}
