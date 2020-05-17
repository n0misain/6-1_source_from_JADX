package com.twitter.sdk.android.core.internal.scribe;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import io.fabric.sdk.android.services.events.EventTransform;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class ScribeEvent {
    private static final String CURRENT_FORMAT_VERSION = "2";
    @SerializedName("_category_")
    final String category;
    @SerializedName("event_namespace")
    final EventNamespace eventNamespace;
    @SerializedName("format_version")
    final String formatVersion;
    @SerializedName("items")
    final List<ScribeItem> items;
    @SerializedName("ts")
    final String timestamp;

    public static class Transform implements EventTransform<ScribeEvent> {
        private final Gson gson;

        public Transform(Gson gson) {
            this.gson = gson;
        }

        public byte[] toBytes(ScribeEvent event) throws IOException {
            return this.gson.toJson((Object) event).getBytes(HttpRequest.CHARSET_UTF8);
        }
    }

    public ScribeEvent(String category, EventNamespace eventNamespace, long timestamp) {
        this(category, eventNamespace, timestamp, Collections.emptyList());
    }

    public ScribeEvent(String category, EventNamespace eventNamespace, long timestamp, List<ScribeItem> items) {
        this.category = category;
        this.eventNamespace = eventNamespace;
        this.timestamp = String.valueOf(timestamp);
        this.formatVersion = CURRENT_FORMAT_VERSION;
        this.items = Collections.unmodifiableList(items);
    }

    public String toString() {
        return "event_namespace=" + this.eventNamespace + ", ts=" + this.timestamp + ", format_version=" + this.formatVersion + ", _category_=" + this.category + ", items=" + ("[" + TextUtils.join(", ", this.items) + "]");
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ScribeEvent that = (ScribeEvent) o;
        if (this.category == null ? that.category != null : !this.category.equals(that.category)) {
            return false;
        }
        if (this.eventNamespace == null ? that.eventNamespace != null : !this.eventNamespace.equals(that.eventNamespace)) {
            return false;
        }
        if (this.formatVersion == null ? that.formatVersion != null : !this.formatVersion.equals(that.formatVersion)) {
            return false;
        }
        if (this.timestamp == null ? that.timestamp != null : !this.timestamp.equals(that.timestamp)) {
            return false;
        }
        if (this.items != null) {
            if (this.items.equals(that.items)) {
                return true;
            }
        } else if (that.items == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int result;
        int hashCode;
        int i = 0;
        if (this.eventNamespace != null) {
            result = this.eventNamespace.hashCode();
        } else {
            result = 0;
        }
        int i2 = result * 31;
        if (this.timestamp != null) {
            hashCode = this.timestamp.hashCode();
        } else {
            hashCode = 0;
        }
        i2 = (i2 + hashCode) * 31;
        if (this.formatVersion != null) {
            hashCode = this.formatVersion.hashCode();
        } else {
            hashCode = 0;
        }
        i2 = (i2 + hashCode) * 31;
        if (this.category != null) {
            hashCode = this.category.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (i2 + hashCode) * 31;
        if (this.items != null) {
            i = this.items.hashCode();
        }
        return hashCode + i;
    }
}
