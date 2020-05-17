package com.twitter.sdk.android.core.models;

import java.util.Collections;
import java.util.Map;

public class BindingValues {
    private final Map<String, Object> bindingValues;

    public BindingValues() {
        this(Collections.EMPTY_MAP);
    }

    public BindingValues(Map<String, Object> bindingValues) {
        this.bindingValues = Collections.unmodifiableMap(bindingValues);
    }

    public boolean containsKey(String key) {
        return this.bindingValues.containsKey(key);
    }

    public <T> T get(String key) {
        try {
            return this.bindingValues.get(key);
        } catch (ClassCastException e) {
            return null;
        }
    }
}
