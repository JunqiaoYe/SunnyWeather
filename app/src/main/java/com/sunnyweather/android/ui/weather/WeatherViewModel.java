package com.sunnyweather.android.ui.weather;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.sunnyweather.android.logic.Repository;
import com.sunnyweather.android.logic.model.place.Location;
import com.sunnyweather.android.logic.model.weather.Weather;

public class WeatherViewModel extends ViewModel {

    public String locationLng = "";

    public String locationLat = "";

    public String placeName = "";

    private final MutableLiveData<Location> locationLiveData = new MutableLiveData<>();

    public final LiveData<Weather> weatherLiveData = Transformations.switchMap(locationLiveData,
            location -> Repository.getInstance().refreshWeather(location.getLng(), location.getLat()));

    public void refreshWeather(String lng, String lat) {
        locationLiveData.setValue(new Location(lng, lat));
    }
}
