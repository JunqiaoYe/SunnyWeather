package com.sunnyweather.android.logic.dao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.sunnyweather.android.SunnyWeatherApplication;
import com.sunnyweather.android.logic.model.place.Place;

/**
 * SharePreferences: 记录选中的城市
 */
public class PlaceDao {

    private static PlaceDao instance;

    private PlaceDao() {}

    public static PlaceDao getInstance() {
        if (instance == null) {
            instance = new PlaceDao();
        }
        return instance;
    }

    @SuppressLint("CommitPrefEdits")
    public SharedPreferences.Editor savePlace(Place place) {
        Log.i("PlaceDao", "save place");
        return sharePreferences().edit().putString("place", new Gson().toJson(place));
    }

    public Place getSavedPlace() {
        Log.i("PlaceDao", "get saved place");
        String placeJson = sharePreferences().getString("place", "");
        return new Gson().fromJson(placeJson, Place.class);
    }

    public boolean isPlaceSaved() {
        Log.i("PlaceDao", "is place saved?");
        return sharePreferences().contains("place");
    }

    private SharedPreferences sharePreferences() {
        return SunnyWeatherApplication.getContext().getSharedPreferences("sunny_weather", Context.MODE_PRIVATE);
    }

}
