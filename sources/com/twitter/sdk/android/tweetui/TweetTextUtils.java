package com.twitter.sdk.android.tweetui;

import android.text.TextUtils;
import com.twitter.sdk.android.core.models.MediaEntity;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.models.UrlEntity;
import com.twitter.sdk.android.tweetui.internal.util.HtmlEntities;
import com.twitter.sdk.android.tweetui.internal.util.HtmlEntities.Unescaped;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

final class TweetTextUtils {
    private TweetTextUtils() {
    }

    static FormattedTweetText formatTweetText(Tweet tweet) {
        if (tweet == null) {
            return null;
        }
        FormattedTweetText adjustedTweet = new FormattedTweetText();
        convertEntities(adjustedTweet, tweet);
        format(adjustedTweet, tweet);
        return adjustedTweet;
    }

    static void convertEntities(FormattedTweetText formattedTweetText, Tweet tweet) {
        if (tweet.entities != null) {
            List<UrlEntity> coreUrls = tweet.entities.urls;
            if (coreUrls != null) {
                for (UrlEntity entity : coreUrls) {
                    formattedTweetText.urlEntities.add(new FormattedUrlEntity(entity));
                }
            }
            List<MediaEntity> coreMedia = tweet.entities.media;
            if (coreMedia != null) {
                for (MediaEntity entity2 : coreMedia) {
                    formattedTweetText.mediaEntities.add(new FormattedMediaEntity(entity2));
                }
            }
        }
    }

    static void format(FormattedTweetText formattedTweetText, Tweet tweet) {
        if (!TextUtils.isEmpty(tweet.text)) {
            Unescaped u = HtmlEntities.HTML40.unescape(tweet.text);
            StringBuilder result = new StringBuilder(u.unescaped);
            adjustIndicesForEscapedChars(formattedTweetText.urlEntities, u.indices);
            adjustIndicesForEscapedChars(formattedTweetText.mediaEntities, u.indices);
            adjustIndicesForSupplementaryChars(result, formattedTweetText);
            formattedTweetText.text = result.toString();
        }
    }

    static void adjustIndicesForEscapedChars(List<? extends FormattedUrlEntity> entities, List<int[]> indices) {
        if (entities != null && indices != null && !indices.isEmpty()) {
            int size = indices.size();
            int m = 0;
            int diff = 0;
            for (FormattedUrlEntity entity : entities) {
                int inDiff = 0;
                for (int i = m; i < size; i++) {
                    int[] index = (int[]) indices.get(i);
                    int start = index[0];
                    int end = index[1];
                    int len = end - start;
                    if (end < entity.start) {
                        diff += len;
                        m++;
                    } else if (end < entity.end) {
                        inDiff += len;
                    }
                }
                entity.start -= diff;
                entity.end -= diff + inDiff;
            }
        }
    }

    static void adjustIndicesForSupplementaryChars(StringBuilder content, FormattedTweetText formattedTweetText) {
        List<Integer> highSurrogateIndices = new ArrayList();
        int len = content.length() - 1;
        int i = 0;
        while (i < len) {
            if (Character.isHighSurrogate(content.charAt(i)) && Character.isLowSurrogate(content.charAt(i + 1))) {
                highSurrogateIndices.add(Integer.valueOf(i));
            }
            i++;
        }
        adjustEntitiesWithOffsets(formattedTweetText.urlEntities, highSurrogateIndices);
        adjustEntitiesWithOffsets(formattedTweetText.mediaEntities, highSurrogateIndices);
    }

    static void adjustEntitiesWithOffsets(List<? extends FormattedUrlEntity> entities, List<Integer> indices) {
        if (entities != null && indices != null) {
            for (FormattedUrlEntity entity : entities) {
                int start = entity.start;
                int offset = 0;
                Iterator i$ = indices.iterator();
                while (i$.hasNext() && ((Integer) i$.next()).intValue() - offset <= start) {
                    offset++;
                }
                entity.start += offset;
                entity.end += offset;
            }
        }
    }
}
