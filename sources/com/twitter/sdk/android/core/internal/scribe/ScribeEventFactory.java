package com.twitter.sdk.android.core.internal.scribe;

import java.util.Collections;
import java.util.List;

public class ScribeEventFactory {
    public static ScribeEvent newScribeEvent(EventNamespace ns, long timestamp, String language, String advertisingId) {
        return newScribeEvent(ns, "", timestamp, language, advertisingId, Collections.emptyList());
    }

    public static ScribeEvent newScribeEvent(EventNamespace ns, String eventInfo, long timestamp, String language, String advertisingId, List<ScribeItem> items) {
        String str = ns.client;
        Object obj = -1;
        switch (str.hashCode()) {
            case 114757:
                if (str.equals(SyndicationClientEvent.CLIENT_NAME)) {
                    obj = null;
                    break;
                }
                break;
        }
        switch (obj) {
            case null:
                return new SyndicationClientEvent(ns, eventInfo, timestamp, language, advertisingId, items);
            default:
                return new SyndicatedSdkImpressionEvent(ns, timestamp, language, advertisingId, items);
        }
    }
}
