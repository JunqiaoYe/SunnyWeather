package com.sunnyweather.android.logic.model.weather.realtime;

import androidx.annotation.NonNull;

import java.util.Objects;

public class Result {

    private final Realtime realtime;

    public Result(Realtime realtime) {
        this.realtime = realtime;
    }

    public Realtime getRealtime() {
        return realtime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result result = (Result) o;
        return Objects.equals(realtime, result.realtime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(realtime);
    }

    @NonNull
    @Override
    public String toString() {
        return "Result{" +
                "realtime=" + realtime +
                '}';
    }
}
