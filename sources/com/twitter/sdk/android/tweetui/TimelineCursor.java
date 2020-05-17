package com.twitter.sdk.android.tweetui;

import com.twitter.sdk.android.core.models.Identifiable;
import java.util.List;

public class TimelineCursor {
    public final Long maxPosition;
    public final Long minPosition;

    public TimelineCursor(Long minPosition, Long maxPosition) {
        this.minPosition = minPosition;
        this.maxPosition = maxPosition;
    }

    TimelineCursor(List<? extends Identifiable> items) {
        Long valueOf;
        Long l = null;
        if (items.size() > 0) {
            valueOf = Long.valueOf(((Identifiable) items.get(items.size() - 1)).getId());
        } else {
            valueOf = null;
        }
        this.minPosition = valueOf;
        if (items.size() > 0) {
            l = Long.valueOf(((Identifiable) items.get(0)).getId());
        }
        this.maxPosition = l;
    }
}
