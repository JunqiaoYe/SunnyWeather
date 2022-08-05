package com.sunnyweather.android;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

/**
 * 全局获取 Context
 */
public class SunnyWeatherApplication extends Application {

    public static final String TOKEN = "Rv7CAgYtWrERy0Hy";  // 彩云天气 API 令牌

    public static final String INTENT_STRING_EXTRA_LNG = "location_lng";

    public static final String INTENT_STRING_EXTRA_LAT = "location_lat";

    public static final String INTENT_STRING_EXTRA_PLACE_NAME = "place_name";

    @SuppressLint("StaticFieldLeak")
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
