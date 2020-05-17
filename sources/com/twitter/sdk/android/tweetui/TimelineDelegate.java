package com.twitter.sdk.android.tweetui;

import android.database.DataSetObservable;
import android.database.DataSetObserver;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Identifiable;
import java.util.ArrayList;
import java.util.List;

class TimelineDelegate<T extends Identifiable> {
    static final long CAPACITY = 200;
    List<T> itemList;
    final DataSetObservable listAdapterObservable;
    final Timeline<T> timeline;
    final TimelineStateHolder timelineStateHolder;

    class DefaultCallback extends Callback<TimelineResult<T>> {
        final Callback<TimelineResult<T>> developerCallback;
        final TimelineStateHolder timelineStateHolder;

        DefaultCallback(Callback<TimelineResult<T>> developerCb, TimelineStateHolder timelineStateHolder) {
            this.developerCallback = developerCb;
            this.timelineStateHolder = timelineStateHolder;
        }

        public void success(Result<TimelineResult<T>> result) {
            this.timelineStateHolder.finishTimelineRequest();
            if (this.developerCallback != null) {
                this.developerCallback.success(result);
            }
        }

        public void failure(TwitterException exception) {
            this.timelineStateHolder.finishTimelineRequest();
            if (this.developerCallback != null) {
                this.developerCallback.failure(exception);
            }
        }
    }

    class NextCallback extends DefaultCallback {
        NextCallback(Callback<TimelineResult<T>> developerCb, TimelineStateHolder timelineStateHolder) {
            super(developerCb, timelineStateHolder);
        }

        public void success(Result<TimelineResult<T>> result) {
            if (((TimelineResult) result.data).items.size() > 0) {
                ArrayList<T> receivedItems = new ArrayList(((TimelineResult) result.data).items);
                receivedItems.addAll(TimelineDelegate.this.itemList);
                TimelineDelegate.this.itemList = receivedItems;
                TimelineDelegate.this.notifyDataSetChanged();
                this.timelineStateHolder.setNextCursor(((TimelineResult) result.data).timelineCursor);
            }
            super.success(result);
        }
    }

    class PreviousCallback extends DefaultCallback {
        PreviousCallback(TimelineStateHolder timelineStateHolder) {
            super(null, timelineStateHolder);
        }

        public void success(Result<TimelineResult<T>> result) {
            if (((TimelineResult) result.data).items.size() > 0) {
                TimelineDelegate.this.itemList.addAll(((TimelineResult) result.data).items);
                TimelineDelegate.this.notifyDataSetChanged();
                this.timelineStateHolder.setPreviousCursor(((TimelineResult) result.data).timelineCursor);
            }
            super.success(result);
        }
    }

    class RefreshCallback extends NextCallback {
        RefreshCallback(Callback<TimelineResult<T>> developerCb, TimelineStateHolder timelineStateHolder) {
            super(developerCb, timelineStateHolder);
        }

        public void success(Result<TimelineResult<T>> result) {
            if (((TimelineResult) result.data).items.size() > 0) {
                TimelineDelegate.this.itemList.clear();
            }
            super.success(result);
        }
    }

    public TimelineDelegate(Timeline<T> timeline) {
        this(timeline, null, null);
    }

    TimelineDelegate(Timeline<T> timeline, DataSetObservable observable, List<T> items) {
        if (timeline == null) {
            throw new IllegalArgumentException("Timeline must not be null");
        }
        this.timeline = timeline;
        this.timelineStateHolder = new TimelineStateHolder();
        if (observable == null) {
            this.listAdapterObservable = new DataSetObservable();
        } else {
            this.listAdapterObservable = observable;
        }
        if (items == null) {
            this.itemList = new ArrayList();
        } else {
            this.itemList = items;
        }
    }

    public void refresh(Callback<TimelineResult<T>> developerCb) {
        this.timelineStateHolder.resetCursors();
        loadNext(this.timelineStateHolder.positionForNext(), new RefreshCallback(developerCb, this.timelineStateHolder));
    }

    public void next(Callback<TimelineResult<T>> developerCb) {
        loadNext(this.timelineStateHolder.positionForNext(), new NextCallback(developerCb, this.timelineStateHolder));
    }

    public void previous() {
        loadPrevious(this.timelineStateHolder.positionForPrevious(), new PreviousCallback(this.timelineStateHolder));
    }

    public int getCount() {
        return this.itemList.size();
    }

    public Timeline getTimeline() {
        return this.timeline;
    }

    public T getItem(int position) {
        if (isLastPosition(position)) {
            previous();
        }
        return (Identifiable) this.itemList.get(position);
    }

    public long getItemId(int position) {
        return ((Identifiable) this.itemList.get(position)).getId();
    }

    public void setItemById(T item) {
        for (int i = 0; i < this.itemList.size(); i++) {
            if (item.getId() == ((Identifiable) this.itemList.get(i)).getId()) {
                this.itemList.set(i, item);
            }
        }
        notifyDataSetChanged();
    }

    boolean withinMaxCapacity() {
        return ((long) this.itemList.size()) < 200;
    }

    boolean isLastPosition(int position) {
        return position == this.itemList.size() + -1;
    }

    void loadNext(Long minPosition, Callback<TimelineResult<T>> cb) {
        if (!withinMaxCapacity()) {
            cb.failure(new TwitterException("Max capacity reached"));
        } else if (this.timelineStateHolder.startTimelineRequest()) {
            this.timeline.next(minPosition, cb);
        } else {
            cb.failure(new TwitterException("Request already in flight"));
        }
    }

    void loadPrevious(Long maxPosition, Callback<TimelineResult<T>> cb) {
        if (!withinMaxCapacity()) {
            cb.failure(new TwitterException("Max capacity reached"));
        } else if (this.timelineStateHolder.startTimelineRequest()) {
            this.timeline.previous(maxPosition, cb);
        } else {
            cb.failure(new TwitterException("Request already in flight"));
        }
    }

    public void registerDataSetObserver(DataSetObserver observer) {
        this.listAdapterObservable.registerObserver(observer);
    }

    public void unregisterDataSetObserver(DataSetObserver observer) {
        this.listAdapterObservable.unregisterObserver(observer);
    }

    public void notifyDataSetChanged() {
        this.listAdapterObservable.notifyChanged();
    }

    public void notifyDataSetInvalidated() {
        this.listAdapterObservable.notifyInvalidated();
    }
}
