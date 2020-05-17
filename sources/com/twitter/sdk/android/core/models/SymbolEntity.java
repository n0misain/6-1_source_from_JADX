package com.twitter.sdk.android.core.models;

import com.google.gson.annotations.SerializedName;

public class SymbolEntity extends Entity {
    @SerializedName("text")
    public final String text;

    public /* bridge */ /* synthetic */ int getEnd() {
        return super.getEnd();
    }

    public /* bridge */ /* synthetic */ int getStart() {
        return super.getStart();
    }

    public SymbolEntity(String text, int start, int end) {
        super(start, end);
        this.text = text;
    }
}
