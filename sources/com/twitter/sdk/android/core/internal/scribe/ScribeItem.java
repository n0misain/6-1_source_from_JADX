package com.twitter.sdk.android.core.internal.scribe;

import com.google.gson.annotations.SerializedName;
import com.twitter.sdk.android.core.internal.VineCardUtils;
import com.twitter.sdk.android.core.models.Card;
import com.twitter.sdk.android.core.models.MediaEntity;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.models.User;
import java.io.Serializable;

public class ScribeItem implements Serializable {
    public static final int TYPE_MESSAGE = 6;
    public static final int TYPE_TWEET = 0;
    public static final int TYPE_USER = 3;
    @SerializedName("card_event")
    public final CardEvent cardEvent;
    @SerializedName("description")
    public final String description;
    @SerializedName("id")
    public final Long id;
    @SerializedName("item_type")
    public final Integer itemType;
    @SerializedName("media_details")
    public final MediaDetails mediaDetails;

    public static class Builder {
        private CardEvent cardEvent;
        private String description;
        private Long id;
        private Integer itemType;
        private MediaDetails mediaDetails;

        public Builder setItemType(int itemType) {
            this.itemType = Integer.valueOf(itemType);
            return this;
        }

        public Builder setId(long id) {
            this.id = Long.valueOf(id);
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setCardEvent(CardEvent cardEvent) {
            this.cardEvent = cardEvent;
            return this;
        }

        public Builder setMediaDetails(MediaDetails mediaDetails) {
            this.mediaDetails = mediaDetails;
            return this;
        }

        public ScribeItem build() {
            return new ScribeItem(this.itemType, this.id, this.description, this.cardEvent, this.mediaDetails);
        }
    }

    public static class CardEvent implements Serializable {
        @SerializedName("promotion_card_type")
        final int promotionCardType;

        public CardEvent(int cardType) {
            this.promotionCardType = cardType;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            if (this.promotionCardType != ((CardEvent) o).promotionCardType) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return this.promotionCardType;
        }
    }

    public static class MediaDetails implements Serializable {
        public static final String GIF_TYPE = "animated_gif";
        public static final int TYPE_AMPLIFY = 2;
        public static final int TYPE_ANIMATED_GIF = 3;
        public static final int TYPE_CONSUMER = 1;
        public static final int TYPE_VINE = 4;
        @SerializedName("content_id")
        public final long contentId;
        @SerializedName("media_type")
        public final int mediaType;
        @SerializedName("publisher_id")
        public final long publisherId;

        public MediaDetails(long contentId, int mediaType, long publisherId) {
            this.contentId = contentId;
            this.mediaType = mediaType;
            this.publisherId = publisherId;
        }

        public boolean equals(Object o) {
            boolean z = true;
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            MediaDetails that = (MediaDetails) o;
            if (this.contentId != that.contentId || this.mediaType != that.mediaType) {
                return false;
            }
            if (this.publisherId != that.publisherId) {
                z = false;
            }
            return z;
        }

        public int hashCode() {
            return (((((int) (this.contentId ^ (this.contentId >>> 32))) * 31) + this.mediaType) * 31) + ((int) (this.publisherId ^ (this.publisherId >>> 32)));
        }
    }

    private ScribeItem(Integer itemType, Long id, String description, CardEvent cardEvent, MediaDetails mediaDetails) {
        this.itemType = itemType;
        this.id = id;
        this.description = description;
        this.cardEvent = cardEvent;
        this.mediaDetails = mediaDetails;
    }

    public static ScribeItem fromTweet(Tweet tweet) {
        return new Builder().setItemType(0).setId(tweet.id).build();
    }

    public static ScribeItem fromUser(User user) {
        return new Builder().setItemType(3).setId(user.id).build();
    }

    public static ScribeItem fromMessage(String message) {
        return new Builder().setItemType(6).setDescription(message).build();
    }

    public static ScribeItem fromTweetCard(long tweetId, Card card) {
        return new Builder().setItemType(0).setId(tweetId).setMediaDetails(createCardDetails(tweetId, card)).build();
    }

    public static ScribeItem fromMediaEntity(long tweetId, MediaEntity mediaEntity) {
        return new Builder().setItemType(0).setId(tweetId).setMediaDetails(createMediaDetails(tweetId, mediaEntity)).build();
    }

    static MediaDetails createMediaDetails(long tweetId, MediaEntity mediaEntity) {
        return new MediaDetails(tweetId, getMediaType(mediaEntity), mediaEntity.id);
    }

    static MediaDetails createCardDetails(long tweetId, Card card) {
        return new MediaDetails(tweetId, 4, Long.valueOf(VineCardUtils.getPublisherId(card)).longValue());
    }

    static int getMediaType(MediaEntity mediaEntity) {
        if ("animated_gif".equals(mediaEntity.type)) {
            return 3;
        }
        return 1;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r6) {
        /*
        r5 = this;
        r1 = 1;
        r2 = 0;
        if (r5 != r6) goto L_0x0006;
    L_0x0004:
        r2 = r1;
    L_0x0005:
        return r2;
    L_0x0006:
        if (r6 == 0) goto L_0x0005;
    L_0x0008:
        r3 = r5.getClass();
        r4 = r6.getClass();
        if (r3 != r4) goto L_0x0005;
    L_0x0012:
        r0 = r6;
        r0 = (com.twitter.sdk.android.core.internal.scribe.ScribeItem) r0;
        r3 = r5.itemType;
        if (r3 == 0) goto L_0x005e;
    L_0x0019:
        r3 = r5.itemType;
        r4 = r0.itemType;
        r3 = r3.equals(r4);
        if (r3 == 0) goto L_0x0005;
    L_0x0023:
        r3 = r5.id;
        if (r3 == 0) goto L_0x0063;
    L_0x0027:
        r3 = r5.id;
        r4 = r0.id;
        r3 = r3.equals(r4);
        if (r3 == 0) goto L_0x0005;
    L_0x0031:
        r3 = r5.description;
        if (r3 == 0) goto L_0x0068;
    L_0x0035:
        r3 = r5.description;
        r4 = r0.description;
        r3 = r3.equals(r4);
        if (r3 == 0) goto L_0x0005;
    L_0x003f:
        r3 = r5.cardEvent;
        if (r3 == 0) goto L_0x006d;
    L_0x0043:
        r3 = r5.cardEvent;
        r4 = r0.cardEvent;
        r3 = r3.equals(r4);
        if (r3 == 0) goto L_0x0005;
    L_0x004d:
        r3 = r5.mediaDetails;
        if (r3 == 0) goto L_0x0072;
    L_0x0051:
        r3 = r5.mediaDetails;
        r4 = r0.mediaDetails;
        r3 = r3.equals(r4);
        if (r3 != 0) goto L_0x005c;
    L_0x005b:
        r1 = r2;
    L_0x005c:
        r2 = r1;
        goto L_0x0005;
    L_0x005e:
        r3 = r0.itemType;
        if (r3 == 0) goto L_0x0023;
    L_0x0062:
        goto L_0x0005;
    L_0x0063:
        r3 = r0.id;
        if (r3 == 0) goto L_0x0031;
    L_0x0067:
        goto L_0x0005;
    L_0x0068:
        r3 = r0.description;
        if (r3 == 0) goto L_0x003f;
    L_0x006c:
        goto L_0x0005;
    L_0x006d:
        r3 = r0.cardEvent;
        if (r3 == 0) goto L_0x004d;
    L_0x0071:
        goto L_0x0005;
    L_0x0072:
        r3 = r0.mediaDetails;
        if (r3 != 0) goto L_0x005b;
    L_0x0076:
        goto L_0x005c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.twitter.sdk.android.core.internal.scribe.ScribeItem.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        int result;
        int hashCode;
        int i = 0;
        if (this.itemType != null) {
            result = this.itemType.hashCode();
        } else {
            result = 0;
        }
        int i2 = result * 31;
        if (this.id != null) {
            hashCode = this.id.hashCode();
        } else {
            hashCode = 0;
        }
        i2 = (i2 + hashCode) * 31;
        if (this.description != null) {
            hashCode = this.description.hashCode();
        } else {
            hashCode = 0;
        }
        i2 = (i2 + hashCode) * 31;
        if (this.cardEvent != null) {
            hashCode = this.cardEvent.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (i2 + hashCode) * 31;
        if (this.mediaDetails != null) {
            i = this.mediaDetails.hashCode();
        }
        return hashCode + i;
    }
}
