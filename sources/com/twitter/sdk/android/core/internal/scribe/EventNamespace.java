package com.twitter.sdk.android.core.internal.scribe;

import com.google.gson.annotations.SerializedName;

public class EventNamespace {
    @SerializedName("action")
    public final String action;
    @SerializedName("client")
    public final String client;
    @SerializedName("component")
    public final String component;
    @SerializedName("element")
    public final String element;
    @SerializedName("page")
    public final String page;
    @SerializedName("section")
    public final String section;

    public static class Builder {
        private String action;
        private String client;
        private String component;
        private String element;
        private String page;
        private String section;

        public Builder setClient(String client) {
            this.client = client;
            return this;
        }

        public Builder setPage(String page) {
            this.page = page;
            return this;
        }

        public Builder setSection(String section) {
            this.section = section;
            return this;
        }

        public Builder setComponent(String component) {
            this.component = component;
            return this;
        }

        public Builder setElement(String element) {
            this.element = element;
            return this;
        }

        public Builder setAction(String action) {
            this.action = action;
            return this;
        }

        public EventNamespace builder() {
            return new EventNamespace(this.client, this.page, this.section, this.component, this.element, this.action);
        }
    }

    public EventNamespace(String client, String page, String section, String component, String element, String action) {
        this.client = client;
        this.page = page;
        this.section = section;
        this.component = component;
        this.element = element;
        this.action = action;
    }

    public String toString() {
        return "client=" + this.client + ", page=" + this.page + ", section=" + this.section + ", component=" + this.component + ", element=" + this.element + ", action=" + this.action;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EventNamespace that = (EventNamespace) o;
        if (this.action == null ? that.action != null : !this.action.equals(that.action)) {
            return false;
        }
        if (this.client == null ? that.client != null : !this.client.equals(that.client)) {
            return false;
        }
        if (this.component == null ? that.component != null : !this.component.equals(that.component)) {
            return false;
        }
        if (this.element == null ? that.element != null : !this.element.equals(that.element)) {
            return false;
        }
        if (this.page == null ? that.page != null : !this.page.equals(that.page)) {
            return false;
        }
        if (this.section != null) {
            if (this.section.equals(that.section)) {
                return true;
            }
        } else if (that.section == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int result;
        int hashCode;
        int i = 0;
        if (this.client != null) {
            result = this.client.hashCode();
        } else {
            result = 0;
        }
        int i2 = result * 31;
        if (this.page != null) {
            hashCode = this.page.hashCode();
        } else {
            hashCode = 0;
        }
        i2 = (i2 + hashCode) * 31;
        if (this.section != null) {
            hashCode = this.section.hashCode();
        } else {
            hashCode = 0;
        }
        i2 = (i2 + hashCode) * 31;
        if (this.component != null) {
            hashCode = this.component.hashCode();
        } else {
            hashCode = 0;
        }
        i2 = (i2 + hashCode) * 31;
        if (this.element != null) {
            hashCode = this.element.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (i2 + hashCode) * 31;
        if (this.action != null) {
            i = this.action.hashCode();
        }
        return hashCode + i;
    }
}
