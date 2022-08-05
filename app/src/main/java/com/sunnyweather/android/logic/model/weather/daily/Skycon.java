package com.sunnyweather.android.logic.model.weather.daily;

import androidx.annotation.NonNull;

import java.util.Date;
import java.util.Objects;

public class Skycon {

    private final Date date;

    private final String value;

    public Skycon(Date date, String value) {
        this.date = date;
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skycon skycon = (Skycon) o;
        return Objects.equals(date, skycon.date) && Objects.equals(value, skycon.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, value);
    }

    @NonNull
    @Override
    public String toString() {
        return "Skycon{" +
                "date=" + date +
                ", value='" + value + '\'' +
                '}';
    }
}
