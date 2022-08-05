package com.sunnyweather.android.logic.model.weather.realtime;

import androidx.annotation.NonNull;

import java.util.Objects;

public class AQI {

    private final Float chn;

    public AQI(Float chn) {
        this.chn = chn;
    }

    public Float getChn() {
        return chn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AQI aqi = (AQI) o;
        return Objects.equals(chn, aqi.chn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chn);
    }

    @NonNull
    @Override
    public String toString() {
        return "AQI{" +
                "chn=" + chn +
                '}';
    }
}
