package com.sunnyweather.android.logic.network;

import androidx.annotation.NonNull;

import com.sunnyweather.android.logic.model.PlaceResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

//        PlaceResponse[] placeResponse = new PlaceResponse[1];
//
//        placeService.searchPlaces(query).enqueue(new Callback<PlaceResponse>() {
//            @Override
//            public void onResponse(@NonNull Call<PlaceResponse> call, @NonNull Response<PlaceResponse> response) {
//                placeResponse[0] = response.body();
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<PlaceResponse> call, @NonNull Throwable t) {
//                t.printStackTrace();
//            }
//        });
//
//        return placeResponse[0];
        return placeService.searchPlaces(query).execute().body();
    }

}
