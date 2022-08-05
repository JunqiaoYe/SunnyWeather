package com.sunnyweather.android.logic.model.weather;

import androidx.annotation.NonNull;

import com.sunnyweather.android.logic.model.weather.daily.Daily;
import com.sunnyweather.android.logic.model.weather.realtime.Realtime;

import java.util.Objects;

/**
 * 封装 Realtime 和 Daily 对象
 */
public class Weather {

    private final Realtime realtime;

    private final Daily daily;

    public Weather(Realtime realtime, Daily daily) {
        this.realtime = realtime;
        this.daily = daily;
    }

    public Realtime getRealtime() {
        return realtime;
    }

    public Daily getDaily() {
        return daily;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Weather weather = (Weather) o;
        return Objects.equals(realtime, weather.realtime) && Objects.equals(daily, weather.daily);
    }

    @Override
    public int hashCode() {
        return Objects.hash(realtime, daily);
    }

    @NonNull
    @Override
    public String toString() {
        return "Weather{" +
                "realtime=" + realtime +
                ", daily=" + daily +
                '}';
    }
}
