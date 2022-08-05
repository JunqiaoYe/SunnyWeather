package com.sunnyweather.android.ui.weather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sunnyweather.android.R;
import com.sunnyweather.android.SunnyWeatherApplication;
import com.sunnyweather.android.databinding.ActivityWeatherBinding;
import com.sunnyweather.android.databinding.ForecastBinding;
import com.sunnyweather.android.databinding.LifeIndexBinding;
import com.sunnyweather.android.databinding.NowBinding;
import com.sunnyweather.android.logic.model.Sky;
import com.sunnyweather.android.logic.model.weather.Weather;
import com.sunnyweather.android.logic.model.weather.daily.Daily;
import com.sunnyweather.android.logic.model.weather.daily.LifeIndex;
import com.sunnyweather.android.logic.model.weather.daily.Skycon;
import com.sunnyweather.android.logic.model.weather.daily.Temperature;
import com.sunnyweather.android.logic.model.weather.realtime.Realtime;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class WeatherActivity extends AppCompatActivity {

    private ActivityWeatherBinding activityWeatherBinding;

    private WeatherViewModel viewModel;

    private NowBinding nowBinding;

    private ForecastBinding forecastBinding;

    private LifeIndexBinding lifeIndexBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityWeatherBinding = ActivityWeatherBinding.inflate(getLayoutInflater());
        setContentView(activityWeatherBinding.getRoot());
        viewModel = new ViewModelProvider(this).get(WeatherViewModel.class);
        Log.i("WeatherActivity", "viewModel is " + viewModel);
        nowBinding = NowBinding.inflate(getLayoutInflater());
        forecastBinding = ForecastBinding.inflate(getLayoutInflater());
        lifeIndexBinding = LifeIndexBinding.inflate(getLayoutInflater());

        if (viewModel.locationLng.isEmpty()) {
            viewModel.locationLng = getIntent().getStringExtra(SunnyWeatherApplication.INTENT_STRING_EXTRA_LNG);
        }
        if ((viewModel.locationLat.isEmpty())) {
            viewModel.locationLat = getIntent().getStringExtra(SunnyWeatherApplication.INTENT_STRING_EXTRA_LAT);
        }
        if (viewModel.placeName.isEmpty()) {
            viewModel.placeName = getIntent().getStringExtra(SunnyWeatherApplication.INTENT_STRING_EXTRA_PLACE_NAME);
        }
        viewModel.weatherLiveData.observe(this, weather -> {
            if (weather != null) {
                showWeatherInfo(weather);
            } else {
                Toast.makeText(this, "无法成功获取天气信息", Toast.LENGTH_SHORT).show();
            }
        });
        viewModel.refreshWeather(viewModel.locationLng, viewModel.locationLat);
    }

    private void showWeatherInfo(Weather weather) {
        Realtime realtime = weather.getRealtime();
        Daily daily = weather.getDaily();

        // 填充 now.xml 布局中的数据
        String currentTempText = realtime.getTemperature().intValue() + " ℃";
        String currentPM25Text = "空气指数 " + realtime.getAirQuality().getAqi().getChn().intValue();
        nowBinding.placeName.setText(viewModel.placeName);
        nowBinding.currentTemp.setText(currentTempText);
        nowBinding.currentSky.setText(Sky.getSky(realtime.getSkycon()).getInfo());
        nowBinding.currentAQI.setText(currentPM25Text);
        nowBinding.nowLayout.setBackgroundResource(Sky.getSky(realtime.getSkycon()).getBg());
        Log.i("WeatherActivity", "placeName is " + viewModel.placeName);
        Log.i("WeatherActivity", "currentTemp is " + currentTempText);
        Log.i("WeatherActivity", "currentSky is " + Sky.getSky(realtime.getSkycon()).getInfo());
        Log.i("WeatherActivity", "currentAQI is " + currentPM25Text);
        Log.i("WeatherActivity", "nowLayout background is " + Sky.getSky(realtime.getSkycon()).getBg());

        // 填充 forecast.xml 布局中的数据
        forecastBinding.forecastLayout.removeAllViews();
        int days = daily.getSkycon().size();
        for (int i = 0; i < days; i++) {
            Skycon skycon = daily.getSkycon().get(i);
            Temperature temperature = daily.getTemperature().get(i);
            View view = LayoutInflater.from(this).inflate(R.layout.forecast_item,
                    forecastBinding.forecastLayout,
                    false);
            TextView dateInfo = view.findViewById(R.id.dateInfo);
            ImageView skyIcon = view.findViewById(R.id.skyIcon);
            TextView skyInfo = view.findViewById(R.id.skyInfo);
            TextView temperatureInfo = view.findViewById(R.id.temperatureInfo);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

            dateInfo.setText(simpleDateFormat.format(skycon.getDate()));
            Sky sky = Sky.getSky(skycon.getValue());
            skyIcon.setImageResource(sky.getIcon());
            skyInfo.setText(sky.getInfo());
            String tempText = temperature.getMin().intValue() + " ~ " + temperature.getMax().intValue() + " ℃";
            temperatureInfo.setText(tempText);
            Log.i("WeatherActivity", "dateInfo is " + simpleDateFormat.format(skycon.getDate()));
            Log.i("WeatherActivity", "skyIcon is " + sky.getIcon());
            Log.i("WeatherActivity", "skyInfo is " + sky.getInfo());
            Log.i("WeatherActivity", "temperatureInfo is " + tempText);
            forecastBinding.forecastLayout.addView(view);
        }

        // 填充 life_index.xml 布局中的数据
        LifeIndex lifeIndex = daily.getLifeIndex();
        lifeIndexBinding.ultravioletText.setText(lifeIndex.getUltraviolet().get(0).getDesc());
        lifeIndexBinding.carWashingText.setText(lifeIndex.getCarWashing().get(0).getDesc());
        lifeIndexBinding.dressingText.setText(lifeIndex.getDressing().get(0).getDesc());
        lifeIndexBinding.coldRiskText.setText(lifeIndex.getColdRisk().get(0).getDesc());
        Log.i("WeatherActivity", "ultravioletText is " + lifeIndex.getUltraviolet().get(0).getDesc());
        Log.i("WeatherActivity", "carWashingText is " + lifeIndex.getCarWashing().get(0).getDesc());
        Log.i("WeatherActivity", "dressingText is " + lifeIndex.getDressing().get(0).getDesc());
        Log.i("WeatherActivity", "coldRiskText is " + lifeIndex.getColdRisk().get(0).getDesc());
        activityWeatherBinding.weatherLayout.setVisibility(View.VISIBLE);
    }
}