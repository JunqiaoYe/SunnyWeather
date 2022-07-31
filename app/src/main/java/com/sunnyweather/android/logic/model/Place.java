package com.sunnyweather.android.logic.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Place {

    private final String name;

    @SerializedName("formatted_address")
    private final String address;

    private final Location location;

    public Place(String name, String address, Location location) {
        this.name = name;
        this.address = address;
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Place place = (Place) o;
        return Objects.equals(name, place.name) && Objects.equals(address, place.address) && Objects.equals(location, place.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address, location);
    }

    @NonNull
    @Override
    public String toString() {
        return "Place{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", location=" + location +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Location getLocation() {
        return location;
    }

}
