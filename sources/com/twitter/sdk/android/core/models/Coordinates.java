package com.twitter.sdk.android.core.models;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class Coordinates {
    public static final int INDEX_LATITUDE = 1;
    public static final int INDEX_LONGITUDE = 0;
    @SerializedName("coordinates")
    public final List<Double> coordinates;
    @SerializedName("type")
    public final String type;

    public Coordinates(Double longitude, Double latitude, String type) {
        List<Double> coords = new ArrayList(2);
        coords.add(0, longitude);
        coords.add(1, latitude);
        this.coordinates = coords;
        this.type = type;
    }

    public Double getLongitude() {
        return (Double) this.coordinates.get(0);
    }

    public Double getLatitude() {
        return (Double) this.coordinates.get(1);
    }
}
