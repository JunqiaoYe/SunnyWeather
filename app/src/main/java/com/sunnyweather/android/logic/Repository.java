package com.sunnyweather.android.logic;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.sunnyweather.android.logic.model.place.Place;
import com.sunnyweather.android.logic.model.place.PlaceResponse;
import com.sunnyweather.android.logic.model.weather.Weather;
import com.sunnyweather.android.logic.model.weather.daily.DailyResponse;
import com.sunnyweather.android.logic.model.weather.realtime.RealtimeResponse;
import com.sunnyweather.android.logic.network.SunnyWeatherNetwork;

import java.io.IOException;
import java.util.List;

/**
 * 仓库层统一封装入口
 */
public class Repository {

    private static Repository instance;

    private Repository() {}

    public static Repository getInstance() {
        if (instance == null) {
            instance = new Repository();
        }
        return instance;
    }

    public LiveData<List<Place>> searchPlaces(String query) {
        MutableLiveData<List<Place>> liveData = new MutableLiveData<>();
        new Thread(() -> {
            try {
                PlaceResponse placeResponse = SunnyWeatherNetwork.getInstance().searchPlaces(query);
                if (placeResponse == null) {
                    throw new RuntimeException("placeResponse is null");
                }
                if (placeResponse.getStatus().equals("ok")) {
                    List<Place> places = placeResponse.getPlaces();
                    liveData.postValue(places);
                } else {
                    throw new RuntimeException("response status is " + placeResponse.getStatus());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        return liveData;
    }

    public LiveData<Weather> refreshWeather(String lng, String lat) {
        MutableLiveData<Weather> liveData = new MutableLiveData<>();
        new Thread(() -> {
            try {
                RealtimeResponse realtimeResponse = SunnyWeatherNetwork.getInstance().getRealtimeWeather(lng, lat);
                DailyResponse dailyResponse = SunnyWeatherNetwork.getInstance().getDailyWeather(lng, lat);
                if (realtimeResponse == null || dailyResponse == null) {
                    throw new RuntimeException("realtimeResponse or dailyResponse is null");
                }
                if (realtimeResponse.getStatus().equals("ok") && dailyResponse.getStatus().equals("ok")) {
                    Weather weather = new Weather(realtimeResponse.getResult().getRealtime(),
                            dailyResponse.getResult().getDaily());
                    liveData.postValue(weather);
                } else {
                    throw new RuntimeException("realtime response status is " + realtimeResponse.getStatus() + "\n" +
                            "daily response status is " + dailyResponse.getStatus());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        return liveData;
    }
}
