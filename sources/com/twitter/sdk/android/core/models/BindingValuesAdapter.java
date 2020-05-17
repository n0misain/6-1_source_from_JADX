package com.twitter.sdk.android.core.models;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class BindingValuesAdapter implements JsonSerializer<BindingValues>, JsonDeserializer<BindingValues> {
    private static final String BOOLEAN_MEMBER = "boolean_value";
    private static final String BOOLEAN_TYPE = "BOOLEAN";
    private static final String IMAGE_TYPE = "IMAGE";
    private static final String IMAGE_VALUE_MEMBER = "image_value";
    private static final String STRING_TYPE = "STRING";
    private static final String TYPE_MEMBER = "type";
    private static final String TYPE_VALUE_MEMBER = "string_value";
    private static final String USER_TYPE = "USER";
    private static final String USER_VALUE_MEMBER = "user_value";

    public JsonElement serialize(BindingValues src, Type typeOfSrc, JsonSerializationContext context) {
        return null;
    }

    public BindingValues deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (!json.isJsonObject()) {
            return new BindingValues();
        }
        Set<Entry<String, JsonElement>> members = json.getAsJsonObject().entrySet();
        Map<String, Object> bindingHash = new HashMap(32);
        for (Entry<String, JsonElement> member : members) {
            bindingHash.put((String) member.getKey(), getValue(((JsonElement) member.getValue()).getAsJsonObject(), context));
        }
        return new BindingValues(bindingHash);
    }

    Object getValue(JsonObject obj, JsonDeserializationContext context) {
        JsonElement typeObj = obj.get("type");
        if (typeObj == null || !typeObj.isJsonPrimitive()) {
            return null;
        }
        String asString = typeObj.getAsString();
        Object obj2 = -1;
        switch (asString.hashCode()) {
            case -1838656495:
                if (asString.equals(STRING_TYPE)) {
                    obj2 = null;
                    break;
                }
                break;
            case 2614219:
                if (asString.equals(USER_TYPE)) {
                    obj2 = 2;
                    break;
                }
                break;
            case 69775675:
                if (asString.equals("IMAGE")) {
                    obj2 = 1;
                    break;
                }
                break;
            case 782694408:
                if (asString.equals(BOOLEAN_TYPE)) {
                    obj2 = 3;
                    break;
                }
                break;
        }
        switch (obj2) {
            case null:
                return context.deserialize(obj.get(TYPE_VALUE_MEMBER), String.class);
            case 1:
                return context.deserialize(obj.get(IMAGE_VALUE_MEMBER), ImageValue.class);
            case 2:
                return context.deserialize(obj.get(USER_VALUE_MEMBER), UserValue.class);
            case 3:
                return context.deserialize(obj.get(BOOLEAN_MEMBER), Boolean.class);
            default:
                return null;
        }
    }
}
