package com.twitter.sdk.android.tweetui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import com.twitter.sdk.android.core.internal.scribe.ScribeItem;
import com.twitter.sdk.android.core.models.MediaEntity;
import com.twitter.sdk.android.tweetui.internal.SwipeToDismissTouchListener.Callback;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class GalleryActivity extends Activity {
    public static final String GALLERY_ITEM = "GALLERY_ITEM";
    static final String MEDIA_ENTITY = "MEDIA_ENTITY";
    GalleryItem galleryItem;
    final GalleryScribeClient galleryScribeClient = new GalleryScribeClientImpl(TweetUi.getInstance());

    /* renamed from: com.twitter.sdk.android.tweetui.GalleryActivity$1 */
    class C10351 implements OnPageChangeListener {
        int galleryPosition = -1;

        C10351() {
        }

        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if (this.galleryPosition == -1 && position == 0 && ((double) positionOffset) == 0.0d) {
                GalleryActivity.this.scribeImpressionEvent(position);
                this.galleryPosition++;
            }
        }

        public void onPageSelected(int position) {
            if (this.galleryPosition >= 0) {
                GalleryActivity.this.scribeNavigateEvent();
            }
            this.galleryPosition++;
            GalleryActivity.this.scribeImpressionEvent(position);
        }

        public void onPageScrollStateChanged(int state) {
        }
    }

    /* renamed from: com.twitter.sdk.android.tweetui.GalleryActivity$2 */
    class C10362 implements Callback {
        C10362() {
        }

        public void onDismiss() {
            GalleryActivity.this.scribeDismissEvent();
            GalleryActivity.this.finish();
            GalleryActivity.this.overridePendingTransition(0, C1043R.anim.tw__slide_out);
        }

        public void onMove(float translationY) {
        }
    }

    public static class GalleryItem implements Serializable {
        public final List<MediaEntity> mediaEntities;
        public final int mediaEntityIndex;
        public final long tweetId;

        public GalleryItem(int mediaEntityIndex, List<MediaEntity> mediaEntities) {
            this(0, mediaEntityIndex, mediaEntities);
        }

        public GalleryItem(long tweetId, int mediaEntityIndex, List<MediaEntity> mediaEntities) {
            this.tweetId = tweetId;
            this.mediaEntityIndex = mediaEntityIndex;
            this.mediaEntities = mediaEntities;
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C1043R.layout.tw__gallery_activity);
        this.galleryItem = getGalleryItem();
        if (savedInstanceState == null) {
            scribeShowEvent();
        }
        GalleryAdapter adapter = new GalleryAdapter(this, getSwipeToDismissCallback());
        adapter.addAll(this.galleryItem.mediaEntities);
        ViewPager viewPager = (ViewPager) findViewById(C1043R.id.tw__view_pager);
        viewPager.setPageMargin(getResources().getDimensionPixelSize(C1043R.dimen.tw__gallery_page_margin));
        viewPager.addOnPageChangeListener(getOnPageChangeListener());
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(this.galleryItem.mediaEntityIndex);
    }

    OnPageChangeListener getOnPageChangeListener() {
        return new C10351();
    }

    Callback getSwipeToDismissCallback() {
        return new C10362();
    }

    GalleryItem getGalleryItem() {
        MediaEntity entity = (MediaEntity) getIntent().getSerializableExtra(MEDIA_ENTITY);
        if (entity != null) {
            return new GalleryItem(0, Collections.singletonList(entity));
        }
        return (GalleryItem) getIntent().getSerializableExtra(GALLERY_ITEM);
    }

    public void onBackPressed() {
        scribeDismissEvent();
        super.onBackPressed();
        overridePendingTransition(0, C1043R.anim.tw__slide_out);
    }

    void scribeShowEvent() {
        this.galleryScribeClient.show();
    }

    void scribeDismissEvent() {
        this.galleryScribeClient.dismiss();
    }

    void scribeImpressionEvent(int mediaEntityPosition) {
        this.galleryScribeClient.impression(ScribeItem.fromMediaEntity(this.galleryItem.tweetId, (MediaEntity) this.galleryItem.mediaEntities.get(mediaEntityPosition)));
    }

    void scribeNavigateEvent() {
        this.galleryScribeClient.navigate();
    }
}
