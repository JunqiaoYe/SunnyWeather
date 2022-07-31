package com.sunnyweather.android.logic.model;

import androidx.annotation.NonNull;

import java.util.List;
import java.util.Objects;

public class PlaceResponse {

    private final String status;

    private final List<Place> places;

    public PlaceResponse(String status, List<Place> places) {
        this.status = status;
        this.places = places;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlaceResponse that = (PlaceResponse) o;
        return Objects.equals(status, that.status) && Objects.equals(places, that.places);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, places);
    }

    @NonNull
    @Override
    public String toString() {
        return "PlaceResponse{" +
                "status='" + status + '\'' +
                ", places=" + places +
                '}';
    }

    public String getStatus() {
        return status;
    }

    public List<Place> getPlaces() {
        return places;
    }
}
