package com.twitter.sdk.android.core.models;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class SafeListAdapter implements TypeAdapterFactory {
    public <T> TypeAdapter<T> create(Gson gson, final TypeToken<T> tokenType) {
        final TypeAdapter<T> delegate = gson.getDelegateAdapter(this, tokenType);
        return new TypeAdapter<T>() {
            public void write(JsonWriter out, T value) throws IOException {
                delegate.write(out, value);
            }

            public T read(JsonReader arg0) throws IOException {
                T t = delegate.read(arg0);
                if (!List.class.isAssignableFrom(tokenType.getRawType())) {
                    return t;
                }
                if (t == null) {
                    return Collections.EMPTY_LIST;
                }
                return Collections.unmodifiableList((List) t);
            }
        };
    }
}
