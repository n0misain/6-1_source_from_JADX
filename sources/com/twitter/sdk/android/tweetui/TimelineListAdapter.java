package com.twitter.sdk.android.tweetui;

import android.content.Context;
import android.database.DataSetObserver;
import android.widget.BaseAdapter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.models.Identifiable;

abstract class TimelineListAdapter<T extends Identifiable> extends BaseAdapter {
    protected final Context context;
    protected final TimelineDelegate<T> delegate;

    public TimelineListAdapter(Context context, Timeline<T> timeline) {
        this(context, new TimelineDelegate(timeline));
    }

    TimelineListAdapter(Context context, TimelineDelegate<T> delegate) {
        if (context == null) {
            throw new IllegalArgumentException("Context must not be null");
        }
        this.context = context;
        this.delegate = delegate;
        delegate.refresh(null);
    }

    public void refresh(Callback<TimelineResult<T>> cb) {
        this.delegate.refresh(cb);
    }

    public int getCount() {
        return this.delegate.getCount();
    }

    public T getItem(int position) {
        return this.delegate.getItem(position);
    }

    public long getItemId(int position) {
        return this.delegate.getItemId(position);
    }

    public void registerDataSetObserver(DataSetObserver observer) {
        this.delegate.registerDataSetObserver(observer);
    }

    public void unregisterDataSetObserver(DataSetObserver observer) {
        this.delegate.unregisterDataSetObserver(observer);
    }

    public void notifyDataSetChanged() {
        this.delegate.notifyDataSetChanged();
    }

    public void notifyDataSetInvalidated() {
        this.delegate.notifyDataSetInvalidated();
    }
}
