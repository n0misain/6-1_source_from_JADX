package com.twitter.sdk.android.core.internal.scribe;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class SyndicationClientEvent extends ScribeEvent {
    public static final String CLIENT_NAME = "tfw";
    private static final String SCRIBE_CATEGORY = "tfw_client_event";
    @SerializedName("event_info")
    public final String eventInfo;
    @SerializedName("external_ids")
    public final ExternalIds externalIds;
    @SerializedName("language")
    public final String language;

    public class ExternalIds {
        @SerializedName("6")
        public final String adId;

        public ExternalIds(String adId) {
            this.adId = adId;
        }
    }

    public SyndicationClientEvent(EventNamespace eventNamespace, String eventInfo, long timestamp, String language, String adId, List<ScribeItem> items) {
        super(SCRIBE_CATEGORY, eventNamespace, timestamp, items);
        this.language = language;
        this.eventInfo = eventInfo;
        this.externalIds = new ExternalIds(adId);
    }
}
