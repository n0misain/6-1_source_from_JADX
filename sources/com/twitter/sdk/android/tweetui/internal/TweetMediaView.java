package com.twitter.sdk.android.tweetui.internal;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.twitter.sdk.android.core.IntentUtils;
import com.twitter.sdk.android.core.internal.VineCardUtils;
import com.twitter.sdk.android.core.internal.scribe.ScribeItem;
import com.twitter.sdk.android.core.models.Card;
import com.twitter.sdk.android.core.models.ImageValue;
import com.twitter.sdk.android.core.models.MediaEntity;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.C1043R;
import com.twitter.sdk.android.tweetui.GalleryActivity;
import com.twitter.sdk.android.tweetui.GalleryActivity.GalleryItem;
import com.twitter.sdk.android.tweetui.PlayerActivity;
import com.twitter.sdk.android.tweetui.PlayerActivity.PlayerItem;
import com.twitter.sdk.android.tweetui.TweetMediaClickListener;
import com.twitter.sdk.android.tweetui.TweetUi;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.List;

public class TweetMediaView extends ViewGroup implements OnClickListener {
    static final int MAX_IMAGE_VIEW_COUNT = 4;
    static final String SIZED_IMAGE_SMALL = ":small";
    final DependencyProvider dependencyProvider;
    private int imageCount;
    private final OverlayImageView[] imageViews;
    boolean internalRoundedCornersEnabled;
    int mediaBgColor;
    private final int mediaDividerSize;
    private List<MediaEntity> mediaEntities;
    private final Path path;
    int photoErrorResId;
    final float[] radii;
    private final RectF rect;
    Tweet tweet;
    TweetMediaClickListener tweetMediaClickListener;

    static class DependencyProvider {
        DependencyProvider() {
        }

        Picasso getImageLoader() {
            return TweetUi.getInstance().getImageLoader();
        }
    }

    static class PicassoCallback implements Callback {
        final WeakReference<ImageView> imageViewWeakReference;

        PicassoCallback(ImageView imageView) {
            this.imageViewWeakReference = new WeakReference(imageView);
        }

        public void onSuccess() {
            ImageView imageView = (ImageView) this.imageViewWeakReference.get();
            if (imageView != null) {
                imageView.setBackgroundResource(17170445);
            }
        }

        public void onError() {
        }
    }

    static class Size {
        static final Size EMPTY = new Size();
        final int height;
        final int width;

        private Size() {
            this(0, 0);
        }

        private Size(int width, int height) {
            this.width = width;
            this.height = height;
        }

        static Size fromSize(int w, int h) {
            int boundedWidth = Math.max(w, 0);
            int boundedHeight = Math.max(h, 0);
            return (boundedWidth == 0 && boundedHeight == 0) ? EMPTY : new Size(boundedWidth, boundedHeight);
        }
    }

    public TweetMediaView(Context context) {
        this(context, null);
    }

    public TweetMediaView(Context context, AttributeSet attrs) {
        this(context, attrs, new DependencyProvider());
    }

    TweetMediaView(Context context, AttributeSet attrs, DependencyProvider dependencyProvider) {
        super(context, attrs);
        this.imageViews = new OverlayImageView[4];
        this.mediaEntities = Collections.emptyList();
        this.path = new Path();
        this.rect = new RectF();
        this.radii = new float[8];
        this.mediaBgColor = -16777216;
        this.dependencyProvider = dependencyProvider;
        this.mediaDividerSize = getResources().getDimensionPixelSize(C1043R.dimen.tw__media_view_divider_size);
        this.photoErrorResId = C1043R.drawable.tw__ic_tweet_photo_error_dark;
    }

    public void setRoundedCornersRadii(int topLeft, int topRight, int bottomRight, int bottomLeft) {
        this.radii[0] = (float) topLeft;
        this.radii[1] = (float) topLeft;
        this.radii[2] = (float) topRight;
        this.radii[3] = (float) topRight;
        this.radii[4] = (float) bottomRight;
        this.radii[5] = (float) bottomRight;
        this.radii[6] = (float) bottomLeft;
        this.radii[7] = (float) bottomLeft;
        requestLayout();
    }

    public void setMediaBgColor(int mediaBgColor) {
        this.mediaBgColor = mediaBgColor;
    }

    public void setTweetMediaClickListener(TweetMediaClickListener tweetMediaClickListener) {
        this.tweetMediaClickListener = tweetMediaClickListener;
    }

    public void setPhotoErrorResId(int photoErrorResId) {
        this.photoErrorResId = photoErrorResId;
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        if (this.imageCount > 0) {
            layoutImages();
        }
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Size size;
        if (this.imageCount > 0) {
            size = measureImages(widthMeasureSpec, heightMeasureSpec);
        } else {
            size = Size.EMPTY;
        }
        setMeasuredDimension(size.width, size.height);
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.path.reset();
        this.rect.set(0.0f, 0.0f, (float) w, (float) h);
        this.path.addRoundRect(this.rect, this.radii, Direction.CW);
        this.path.close();
    }

    protected void dispatchDraw(Canvas canvas) {
        if (!this.internalRoundedCornersEnabled || VERSION.SDK_INT < 18) {
            super.dispatchDraw(canvas);
            return;
        }
        int saveState = canvas.save();
        canvas.clipPath(this.path);
        super.dispatchDraw(canvas);
        canvas.restoreToCount(saveState);
    }

    public void onClick(View view) {
        Integer mediaEntityIndex = (Integer) view.getTag(C1043R.id.tw__entity_index);
        MediaEntity mediaEntity;
        if (this.tweetMediaClickListener != null) {
            if (this.mediaEntities.isEmpty()) {
                mediaEntity = null;
            } else {
                mediaEntity = (MediaEntity) this.mediaEntities.get(mediaEntityIndex.intValue());
            }
            this.tweetMediaClickListener.onMediaEntityClick(this.tweet, mediaEntity);
        } else if (this.mediaEntities.isEmpty()) {
            launchVideoPlayer(this.tweet);
        } else {
            mediaEntity = (MediaEntity) this.mediaEntities.get(mediaEntityIndex.intValue());
            if (TweetMediaUtils.isVideoType(mediaEntity)) {
                launchVideoPlayer(mediaEntity);
            } else if (TweetMediaUtils.isPhotoType(mediaEntity)) {
                launchPhotoGallery(mediaEntityIndex.intValue());
            }
        }
    }

    public void launchVideoPlayer(MediaEntity entity) {
        if (TweetMediaUtils.getSupportedVariant(entity) != null) {
            Intent intent = new Intent(getContext(), PlayerActivity.class);
            intent.putExtra(PlayerActivity.PLAYER_ITEM, new PlayerItem(TweetMediaUtils.getSupportedVariant(entity).url, TweetMediaUtils.isLooping(entity)));
            IntentUtils.safeStartActivity(getContext(), intent);
        }
    }

    public void launchVideoPlayer(Tweet tweet) {
        Card card = tweet.card;
        Intent intent = new Intent(getContext(), PlayerActivity.class);
        intent.putExtra(PlayerActivity.PLAYER_ITEM, new PlayerItem(VineCardUtils.getStreamUrl(card), true, getContext().getResources().getString(C1043R.string.tw__cta_text), VineCardUtils.getCallToActionUrl(card)));
        intent.putExtra(PlayerActivity.SCRIBE_ITEM, ScribeItem.fromTweetCard(tweet.id, card));
        IntentUtils.safeStartActivity(getContext(), intent);
    }

    public void launchPhotoGallery(int mediaEntityIndex) {
        Intent intent = new Intent(getContext(), GalleryActivity.class);
        intent.putExtra(GalleryActivity.GALLERY_ITEM, new GalleryItem(this.tweet.id, mediaEntityIndex, this.mediaEntities));
        IntentUtils.safeStartActivity(getContext(), intent);
    }

    public void setTweetMediaEntities(Tweet tweet, List<MediaEntity> mediaEntities) {
        if (tweet != null && mediaEntities != null && !mediaEntities.isEmpty() && !mediaEntities.equals(this.mediaEntities)) {
            this.tweet = tweet;
            this.mediaEntities = mediaEntities;
            clearImageViews();
            initializeImageViews((List) mediaEntities);
            if (TweetMediaUtils.isPhotoType((MediaEntity) mediaEntities.get(0))) {
                this.internalRoundedCornersEnabled = true;
            } else {
                this.internalRoundedCornersEnabled = false;
            }
            requestLayout();
        }
    }

    public void setVineCard(Tweet tweet) {
        if (tweet != null && tweet.card != null && VineCardUtils.isVine(tweet.card)) {
            this.tweet = tweet;
            this.mediaEntities = Collections.emptyList();
            clearImageViews();
            initializeImageViews(tweet.card);
            this.internalRoundedCornersEnabled = false;
            requestLayout();
        }
    }

    Size measureImages(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int halfWidth = (width - this.mediaDividerSize) / 2;
        int halfHeight = (height - this.mediaDividerSize) / 2;
        switch (this.imageCount) {
            case 1:
                measureImageView(0, width, height);
                break;
            case 2:
                measureImageView(0, halfWidth, height);
                measureImageView(1, halfWidth, height);
                break;
            case 3:
                measureImageView(0, halfWidth, height);
                measureImageView(1, halfWidth, halfHeight);
                measureImageView(2, halfWidth, halfHeight);
                break;
            case 4:
                measureImageView(0, halfWidth, halfHeight);
                measureImageView(1, halfWidth, halfHeight);
                measureImageView(2, halfWidth, halfHeight);
                measureImageView(3, halfWidth, halfHeight);
                break;
        }
        return Size.fromSize(width, height);
    }

    void measureImageView(int i, int width, int height) {
        this.imageViews[i].measure(MeasureSpec.makeMeasureSpec(width, 1073741824), MeasureSpec.makeMeasureSpec(height, 1073741824));
    }

    void layoutImages() {
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        int halfWidth = (width - this.mediaDividerSize) / 2;
        int halfHeight = (height - this.mediaDividerSize) / 2;
        int middle = halfWidth + this.mediaDividerSize;
        switch (this.imageCount) {
            case 1:
                layoutImage(0, 0, 0, width, height);
                return;
            case 2:
                layoutImage(0, 0, 0, halfWidth, height);
                layoutImage(1, halfWidth + this.mediaDividerSize, 0, width, height);
                return;
            case 3:
                layoutImage(0, 0, 0, halfWidth, height);
                layoutImage(1, middle, 0, width, halfHeight);
                layoutImage(2, middle, halfHeight + this.mediaDividerSize, width, height);
                return;
            case 4:
                layoutImage(0, 0, 0, halfWidth, halfHeight);
                layoutImage(2, 0, halfHeight + this.mediaDividerSize, halfWidth, height);
                layoutImage(1, middle, 0, width, halfHeight);
                layoutImage(3, middle, halfHeight + this.mediaDividerSize, width, height);
                return;
            default:
                return;
        }
    }

    void layoutImage(int i, int left, int top, int right, int bottom) {
        ImageView view = this.imageViews[i];
        if (view.getLeft() != left || view.getTop() != top || view.getRight() != right || view.getBottom() != bottom) {
            view.layout(left, top, right, bottom);
        }
    }

    void clearImageViews() {
        for (int index = 0; index < this.imageCount; index++) {
            ImageView imageView = this.imageViews[index];
            if (imageView != null) {
                imageView.setVisibility(8);
            }
        }
        this.imageCount = 0;
    }

    void initializeImageViews(List<MediaEntity> mediaEntities) {
        this.imageCount = Math.min(4, mediaEntities.size());
        for (int index = 0; index < this.imageCount; index++) {
            OverlayImageView imageView = getOrCreateImageView(index);
            MediaEntity mediaEntity = (MediaEntity) mediaEntities.get(index);
            setAltText(imageView, mediaEntity.altText);
            setMediaImage(imageView, getSizedImagePath(mediaEntity));
            setOverlayImage(imageView, TweetMediaUtils.isVideoType(mediaEntity));
        }
    }

    void initializeImageViews(Card card) {
        this.imageCount = 1;
        OverlayImageView imageView = getOrCreateImageView(0);
        ImageValue imageValue = VineCardUtils.getImageValue(card);
        setAltText(imageView, imageValue.alt);
        setMediaImage(imageView, imageValue.url);
        setOverlayImage(imageView, true);
    }

    OverlayImageView getOrCreateImageView(int index) {
        OverlayImageView imageView = this.imageViews[index];
        if (imageView == null) {
            imageView = new OverlayImageView(getContext());
            imageView.setLayoutParams(generateDefaultLayoutParams());
            imageView.setOnClickListener(this);
            this.imageViews[index] = imageView;
            addView(imageView, index);
        } else {
            measureImageView(index, 0, 0);
            layoutImage(index, 0, 0, 0, 0);
        }
        imageView.setVisibility(0);
        imageView.setBackgroundColor(this.mediaBgColor);
        imageView.setTag(C1043R.id.tw__entity_index, Integer.valueOf(index));
        return imageView;
    }

    String getSizedImagePath(MediaEntity mediaEntity) {
        if (this.imageCount > 1) {
            return mediaEntity.mediaUrlHttps + SIZED_IMAGE_SMALL;
        }
        return mediaEntity.mediaUrlHttps;
    }

    void setAltText(ImageView imageView, String description) {
        if (TextUtils.isEmpty(description)) {
            imageView.setContentDescription(getResources().getString(C1043R.string.tw__tweet_media));
        } else {
            imageView.setContentDescription(description);
        }
    }

    void setOverlayImage(OverlayImageView imageView, boolean isVideo) {
        if (isVideo) {
            imageView.setOverlayDrawable(getContext().getResources().getDrawable(C1043R.drawable.tw__player_overlay));
        } else {
            imageView.setOverlayDrawable(null);
        }
    }

    void setMediaImage(ImageView imageView, String imagePath) {
        Picasso imageLoader = this.dependencyProvider.getImageLoader();
        if (imageLoader != null) {
            imageLoader.load(imagePath).fit().centerCrop().error(this.photoErrorResId).into(imageView, new PicassoCallback(imageView));
        }
    }
}
