package com.sunnyweather.android.logic.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit 构建器
 */
public class ServiceCreator {

    private static final String BASE_URL = "https://api.caiyunapp.com/";

    private static ServiceCreator instance;

    private ServiceCreator() {}

    public static ServiceCreator getInstance() {
        if (instance == null) {
            instance = new ServiceCreator();
        }
        return instance;
    }

    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public <T> T create(Class<T> serviceClass) {
        return retrofit.create(serviceClass);
    }

}
