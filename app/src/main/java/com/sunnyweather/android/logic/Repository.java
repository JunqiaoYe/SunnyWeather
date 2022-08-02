package com.sunnyweather.android.logic;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.sunnyweather.android.logic.model.Place;
import com.sunnyweather.android.logic.model.PlaceResponse;
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
}
