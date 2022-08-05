package com.sunnyweather.android.logic.model.weather.daily;

import androidx.annotation.NonNull;

import java.util.Objects;

public class LifeDescription {

    private final String desc;

    public LifeDescription(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LifeDescription that = (LifeDescription) o;
        return Objects.equals(desc, that.desc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(desc);
    }

    @NonNull
    @Override
    public String toString() {
        return "LifeDescription{" +
                "desc='" + desc + '\'' +
                '}';
    }
}
