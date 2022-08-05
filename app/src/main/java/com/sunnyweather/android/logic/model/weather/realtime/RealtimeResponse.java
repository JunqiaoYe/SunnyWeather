package com.sunnyweather.android.logic.model.weather.realtime;

import androidx.annotation.NonNull;

import java.util.Objects;

public class RealtimeResponse {

    private final String status;

    private final Result result;

    public RealtimeResponse(String status, Result result) {
        this.status = status;
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public Result getResult() {
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RealtimeResponse that = (RealtimeResponse) o;
        return Objects.equals(status, that.status) && Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, result);
    }

    @NonNull
    @Override
    public String toString() {
        return "RealtimeResponse{" +
                "status='" + status + '\'' +
                ", result=" + result +
                '}';
    }
}
