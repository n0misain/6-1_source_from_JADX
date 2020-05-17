package com.twitter.sdk.android.tweetui.internal;

import android.os.Build.VERSION;
import com.twitter.sdk.android.core.models.MediaEntity;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.models.TweetEntities;
import com.twitter.sdk.android.core.models.VideoInfo.Variant;
import java.util.ArrayList;
import java.util.List;

public final class TweetMediaUtils {
    private static final String CONTENT_TYPE_HLS = "application/x-mpegURL";
    private static final String CONTENT_TYPE_MP4 = "video/mp4";
    public static final String GIF_TYPE = "animated_gif";
    public static final String PHOTO_TYPE = "photo";
    public static final String VIDEO_TYPE = "video";

    private TweetMediaUtils() {
    }

    public static MediaEntity getPhotoEntity(Tweet tweet) {
        List<MediaEntity> mediaEntityList = getAllMediaEntities(tweet);
        for (int i = mediaEntityList.size() - 1; i >= 0; i--) {
            MediaEntity entity = (MediaEntity) mediaEntityList.get(i);
            if (entity.type != null && isPhotoType(entity)) {
                return entity;
            }
        }
        return null;
    }

    public static List<MediaEntity> getPhotoEntities(Tweet tweet) {
        List<MediaEntity> photoEntities = new ArrayList();
        TweetEntities extendedEntities = tweet.extendedEtities;
        if (!(extendedEntities == null || extendedEntities.media == null || extendedEntities.media.size() <= 0)) {
            for (int i = 0; i <= extendedEntities.media.size() - 1; i++) {
                MediaEntity entity = (MediaEntity) extendedEntities.media.get(i);
                if (entity.type != null && isPhotoType(entity)) {
                    photoEntities.add(entity);
                }
            }
        }
        return photoEntities;
    }

    public static boolean hasPhoto(Tweet tweet) {
        return getPhotoEntity(tweet) != null;
    }

    public static MediaEntity getVideoEntity(Tweet tweet) {
        for (MediaEntity mediaEntity : getAllMediaEntities(tweet)) {
            if (mediaEntity.type != null && isVideoType(mediaEntity)) {
                return mediaEntity;
            }
        }
        return null;
    }

    public static boolean hasSupportedVideo(Tweet tweet) {
        MediaEntity entity = getVideoEntity(tweet);
        return (entity == null || getSupportedVariant(entity) == null) ? false : true;
    }

    static boolean isPhotoType(MediaEntity mediaEntity) {
        return "photo".equals(mediaEntity.type);
    }

    static boolean isVideoType(MediaEntity mediaEntity) {
        return "video".equals(mediaEntity.type) || "animated_gif".equals(mediaEntity.type);
    }

    public static Variant getSupportedVariant(MediaEntity mediaEntity) {
        for (Variant variant : mediaEntity.videoInfo.variants) {
            if (isVariantSupported(variant)) {
                return variant;
            }
        }
        return null;
    }

    public static boolean isLooping(MediaEntity mediaEntity) {
        return "animated_gif".equals(mediaEntity.type);
    }

    static boolean isVariantSupported(Variant variant) {
        if ((VERSION.SDK_INT < 21 || !CONTENT_TYPE_HLS.equals(variant.contentType)) && !CONTENT_TYPE_MP4.equals(variant.contentType)) {
            return false;
        }
        return true;
    }

    static List<MediaEntity> getAllMediaEntities(Tweet tweet) {
        List<MediaEntity> entities = new ArrayList();
        if (!(tweet.entities == null || tweet.entities.media == null)) {
            entities.addAll(tweet.entities.media);
        }
        if (!(tweet.extendedEtities == null || tweet.extendedEtities.media == null)) {
            entities.addAll(tweet.extendedEtities.media);
        }
        return entities;
    }
}
