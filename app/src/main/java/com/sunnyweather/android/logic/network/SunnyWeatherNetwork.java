package com.sunnyweather.android.logic.network;

import android.util.Log;

import com.sunnyweather.android.logic.model.place.PlaceResponse;
import com.sunnyweather.android.logic.model.weather.daily.DailyResponse;
import com.sunnyweather.android.logic.model.weather.realtime.RealtimeResponse;

import java.io.IOException;

/**
 * 网络数据源访问入口
 */
public class SunnyWeatherNetwork {

    private final PlaceService placeService;

    private final WeatherService weatherService;

    private static SunnyWeatherNetwork instance;

    private SunnyWeatherNetwork() {
        placeService = ServiceCreator.getInstance().create(PlaceService.class);
        weatherService = ServiceCreator.getInstance().create(WeatherService.class);
    }

    public synchronized static SunnyWeatherNetwork getInstance() {
        if (instance == null) {
            instance = new SunnyWeatherNetwork();
        }
        return instance;
    }

    public PlaceResponse searchPlaces(String query) throws IOException {
        Log.i("SunnyWeatherNetwork", "searchPlaces run, query = " + query);
        return placeService.searchPlaces(query).execute().body();
    }

    public RealtimeResponse getRealtimeWeather(String lng, String lat) throws IOException {
        Log.i("SunnyWeatherNetwork", "getRealtimeWeather run, lng = " + lng + ". lat = " + lat);
        return weatherService.getRealtimeWeather(lng, lat).execute().body();
    }

    public DailyResponse getDailyWeather(String lng, String lat) throws IOException {
        Log.i("SunnyWeatherNetwork", "getDailyWeather run, lng = " + lng + ". lat = " + lat);
        return weatherService.getDailyWeather(lng, lat).execute().body();
    }
}
