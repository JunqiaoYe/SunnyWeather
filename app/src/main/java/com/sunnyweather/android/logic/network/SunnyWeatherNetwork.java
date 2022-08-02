package com.sunnyweather.android.logic.network;

import android.util.Log;

import com.sunnyweather.android.logic.model.PlaceResponse;

import java.io.IOException;

/**
 * 网络数据源访问入口
 */
public class SunnyWeatherNetwork {

    private final PlaceService placeService;

    private static SunnyWeatherNetwork instance;

    private SunnyWeatherNetwork() {
        placeService = ServiceCreator.getInstance().create(PlaceService.class);
    }

    public synchronized static SunnyWeatherNetwork getInstance() {
        if (instance == null) {
            instance = new SunnyWeatherNetwork();
        }
        return instance;
    }

    public PlaceResponse searchPlaces(String query) throws IOException {
        Log.i("SunnyWeatherNetwork", "searchPlaces run");
        return placeService.searchPlaces(query).execute().body();
    }
}
