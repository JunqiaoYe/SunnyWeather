package com.sunnyweather.android.ui.place;

import android.content.SharedPreferences;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.sunnyweather.android.logic.Repository;
import com.sunnyweather.android.logic.model.place.Place;

import java.util.ArrayList;
import java.util.List;

public class PlaceViewModel extends ViewModel {

    private final MutableLiveData<String> searchLiveData = new MutableLiveData<>();

    public final List<Place> placeList = new ArrayList<>();

    public final LiveData<List<Place>> placeLiveData = Transformations.switchMap(searchLiveData,
            query -> Repository.getInstance().searchPlaces(query));

    public void searchPlaces(String query) {
        searchLiveData.setValue(query);
    }

    // 记录选中的城市
    public SharedPreferences.Editor savePlace(Place place) {
        return Repository.getInstance().savePlace(place);
    }
    public Place getSavedPlace() {
        return Repository.getInstance().getSavedPlace();
    }
    public boolean isPlaceSaved() {
        return Repository.getInstance().isPlaceSaved();
    }

}
