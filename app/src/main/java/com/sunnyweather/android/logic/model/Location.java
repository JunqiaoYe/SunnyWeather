package com.sunnyweather.android.logic.model;

import androidx.annotation.NonNull;

import java.util.Objects;

public class Location {

    private final String lat;

    private final String lng;

    public Location(String lat, String lng) {
        this.lat = lat;
        this.lng = lng;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Objects.equals(lat, location.lat) && Objects.equals(lng, location.lng);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lat, lng);
    }

    @NonNull
    @Override
    public String toString() {
        return "Location{" +
                "lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                '}';
    }

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }
}
