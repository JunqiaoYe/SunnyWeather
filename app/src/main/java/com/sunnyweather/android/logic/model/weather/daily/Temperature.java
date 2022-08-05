package com.sunnyweather.android.logic.model.weather.daily;

import androidx.annotation.NonNull;

import java.util.Objects;

public class Temperature {

    private final Float max;

    private final Float min;

    public Temperature(Float max, Float min) {
        this.max = max;
        this.min = min;
    }

    public Float getMax() {
        return max;
    }

    public Float getMin() {
        return min;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Temperature that = (Temperature) o;
        return Objects.equals(max, that.max) && Objects.equals(min, that.min);
    }

    @Override
    public int hashCode() {
        return Objects.hash(max, min);
    }

    @NonNull
    @Override
    public String toString() {
        return "Temperature{" +
                "max=" + max +
                ", min=" + min +
                '}';
    }
}
