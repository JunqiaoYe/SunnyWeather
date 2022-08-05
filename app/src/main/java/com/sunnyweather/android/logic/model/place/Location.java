package com.sunnyweather.android.logic.model.place;

import androidx.annotation.NonNull;

import java.util.Objects;

public class Location {

    private final String lng;

    private final String lat;

    public Location(String lng, String lat) {
        this.lng = lng;
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public String getLat() {
        return lat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Objects.equals(lng, location.lng) && Objects.equals(lat, location.lat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lng, lat);
    }

    @NonNull
    @Override
    public String toString() {
        return "Location{" +
                "lng='" + lng + '\'' +
                ", lat='" + lat + '\'' +
                '}';
    }
}
