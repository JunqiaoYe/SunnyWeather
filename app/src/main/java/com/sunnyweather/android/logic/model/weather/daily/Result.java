package com.sunnyweather.android.logic.model.weather.daily;

import androidx.annotation.NonNull;

import java.util.Objects;

public class Result {

    private final Daily daily;

    public Result(Daily daily) {
        this.daily = daily;
    }

    public Daily getDaily() {
        return daily;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result result = (Result) o;
        return Objects.equals(daily, result.daily);
    }

    @Override
    public int hashCode() {
        return Objects.hash(daily);
    }

    @NonNull
    @Override
    public String toString() {
        return "Result{" +
                "daily=" + daily +
                '}';
    }
}
