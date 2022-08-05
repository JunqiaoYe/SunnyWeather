package com.sunnyweather.android.logic.model.weather.realtime;

import androidx.annotation.NonNull;

import java.util.Objects;

public class AirQuality {

    private final AQI aqi;

    public AirQuality(AQI aqi) {
        this.aqi = aqi;
    }

    public AQI getAqi() {
        return aqi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AirQuality that = (AirQuality) o;
        return Objects.equals(aqi, that.aqi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aqi);
    }

    @NonNull
    @Override
    public String toString() {
        return "AirQuality{" +
                "aqi=" + aqi +
                '}';
    }
}
