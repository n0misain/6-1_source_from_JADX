package com.twitter.sdk.android.tweetui;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.twitter.sdk.android.core.models.MediaEntity;
import com.twitter.sdk.android.tweetui.internal.GalleryImageView;
import com.twitter.sdk.android.tweetui.internal.SwipeToDismissTouchListener.Callback;
import java.util.ArrayList;
import java.util.List;

class GalleryAdapter extends PagerAdapter {
    final Callback callback;
    final Context context;
    final List<MediaEntity> items = new ArrayList();

    GalleryAdapter(Context context, Callback callback) {
        this.context = context;
        this.callback = callback;
    }

    void addAll(List<MediaEntity> entities) {
        this.items.addAll(entities);
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.items.size();
    }

    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public Object instantiateItem(ViewGroup container, int position) {
        Target root = new GalleryImageView(this.context);
        root.setSwipeToDismissCallback(this.callback);
        container.addView(root);
        Picasso.with(this.context).load(((MediaEntity) this.items.get(position)).mediaUrlHttps).into(root);
        return root;
    }

    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
