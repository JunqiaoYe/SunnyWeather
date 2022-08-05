package com.sunnyweather.android.logic.model.weather.daily;

import androidx.annotation.NonNull;

import java.util.List;
import java.util.Objects;

public class LifeIndex {

    private final List<LifeDescription> ultraviolet;

    private final List<LifeDescription> carWashing;

    private final List<LifeDescription> dressing;

    private final List<LifeDescription> coldRisk;

    public LifeIndex(List<LifeDescription> ultraviolet,
                     List<LifeDescription> carWashing,
                     List<LifeDescription> dressing,
                     List<LifeDescription> coldRisk) {
        this.ultraviolet = ultraviolet;
        this.carWashing = carWashing;
        this.dressing = dressing;
        this.coldRisk = coldRisk;
    }

    public List<LifeDescription> getUltraviolet() {
        return ultraviolet;
    }

    public List<LifeDescription> getCarWashing() {
        return carWashing;
    }

    public List<LifeDescription> getDressing() {
        return dressing;
    }

    public List<LifeDescription> getColdRisk() {
        return coldRisk;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LifeIndex lifeIndex = (LifeIndex) o;
        return Objects.equals(ultraviolet, lifeIndex.ultraviolet) &&
                Objects.equals(carWashing, lifeIndex.carWashing) &&
                Objects.equals(dressing, lifeIndex.dressing) &&
                Objects.equals(coldRisk, lifeIndex.coldRisk);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ultraviolet, carWashing, dressing, coldRisk);
    }

    @NonNull
    @Override
    public String toString() {
        return "LifeIndex{" +
                "ultraviolet=" + ultraviolet +
                ", carWashing=" + carWashing +
                ", dressing=" + dressing +
                ", coldRisk=" + coldRisk +
                '}';
    }
}
