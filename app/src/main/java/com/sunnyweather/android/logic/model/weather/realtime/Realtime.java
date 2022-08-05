package com.sunnyweather.android.logic.model.weather.realtime;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Realtime {

    private final Float temperature;

    private final String skycon;

    @SerializedName("air_quality")
    private final AirQuality airQuality;

    public Realtime(Float temperature, String skycon, AirQuality airQuality) {
        this.temperature = temperature;
        this.skycon = skycon;
        this.airQuality = airQuality;
    }

    public Float getTemperature() {
        return temperature;
    }

    public String getSkycon() {
        return skycon;
    }

    public AirQuality getAirQuality() {
        return airQuality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Realtime realtime = (Realtime) o;
        return Objects.equals(temperature, realtime.temperature) && Objects.equals(skycon, realtime.skycon) && Objects.equals(airQuality, realtime.airQuality);
    }

    @Override
    public int hashCode() {
        return Objects.hash(temperature, skycon, airQuality);
    }

    @NonNull
    @Override
    public String toString() {
        return "Realtime{" +
                "temperature=" + temperature +
                ", skycon='" + skycon + '\'' +
                ", airQuality=" + airQuality +
                '}';
    }
}
