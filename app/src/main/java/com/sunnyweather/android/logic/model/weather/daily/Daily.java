package com.sunnyweather.android.logic.model.weather.daily;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

public class Daily {

    private final List<Temperature> temperature;

    private final List<Skycon> skycon;

    @SerializedName("life_index")
    private final LifeIndex lifeIndex;

    public Daily(List<Temperature> temperature, List<Skycon> skycon, LifeIndex lifeIndex) {
        this.temperature = temperature;
        this.skycon = skycon;
        this.lifeIndex = lifeIndex;
    }

    public List<Temperature> getTemperature() {
        return temperature;
    }

    public List<Skycon> getSkycon() {
        return skycon;
    }

    public LifeIndex getLifeIndex() {
        return lifeIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Daily daily = (Daily) o;
        return Objects.equals(temperature, daily.temperature) &&
                Objects.equals(skycon, daily.skycon) &&
                Objects.equals(lifeIndex, daily.lifeIndex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(temperature, skycon, lifeIndex);
    }

    @NonNull
    @Override
    public String toString() {
        return "Daily{" +
                "temperature=" + temperature +
                ", skycon=" + skycon +
                ", lifeIndex=" + lifeIndex +
                '}';
    }
}
