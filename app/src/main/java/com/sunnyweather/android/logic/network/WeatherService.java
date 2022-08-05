package com.sunnyweather.android.logic.network;

import com.sunnyweather.android.SunnyWeatherApplication;
import com.sunnyweather.android.logic.model.weather.daily.DailyResponse;
import com.sunnyweather.android.logic.model.weather.realtime.RealtimeResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 访问彩云天气天气信息 API 的 Retrofit 接口
 */
public interface WeatherService {

    @GET("v2.5/" + SunnyWeatherApplication.TOKEN + "/{lng},{lat}/realtime.json")
    Call<RealtimeResponse> getRealtimeWeather(@Path("lng") String lng, @Path("lat") String lat);

    @GET("v2.5/" + SunnyWeatherApplication.TOKEN + "/{lng},{lat}/daily.json")
    Call<DailyResponse> getDailyWeather(@Path("lng") String lng, @Path("lat") String lat);
}
