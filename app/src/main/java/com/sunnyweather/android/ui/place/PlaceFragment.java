package com.sunnyweather.android.ui.place;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.sunnyweather.android.SunnyWeatherApplication;
import com.sunnyweather.android.databinding.FragmentPlaceBinding;
import com.sunnyweather.android.logic.model.place.Place;
import com.sunnyweather.android.ui.weather.WeatherActivity;

public class PlaceFragment extends Fragment {

    public boolean firstCreated;

    public PlaceViewModel viewModel;

    private FragmentPlaceBinding binding;

    private PlaceAdapter adapter;

    public PlaceFragment() {
        firstCreated = true;
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        requireActivity().getLifecycle().addObserver(new LifecycleEventObserver() {
            @Override
            public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
                if (event.getTargetState() == Lifecycle.State.CREATED && firstCreated) {
                    // 判断选中的城市是否已有存储
                    if (viewModel.isPlaceSaved()) {
                        Log.i("PlaceFragment", "place is saved");
                        Place place = viewModel.getSavedPlace();
                        Intent intent = new Intent(context, WeatherActivity.class);
                        intent.putExtra(SunnyWeatherApplication.INTENT_STRING_EXTRA_LNG, place.getLocation().getLng());
                        intent.putExtra(SunnyWeatherApplication.INTENT_STRING_EXTRA_LAT, place.getLocation().getLat());
                        intent.putExtra(SunnyWeatherApplication.INTENT_STRING_EXTRA_PLACE_NAME, place.getName());
                        PlaceFragment.this.startActivity(intent);
                        PlaceFragment.this.requireActivity().finish();
                    } else {
                        Log.i("PlaceFragment", "place is not saved");

                        LinearLayoutManager layoutManager = new LinearLayoutManager(PlaceFragment.this.requireActivity());
                        binding.recyclerView.setLayoutManager(layoutManager);
                        adapter = new PlaceAdapter(PlaceFragment.this, viewModel.placeList);
                        binding.recyclerView.setAdapter(adapter);

                        binding.searchPlaceEdit.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                            }

                            @Override
                            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                            }

                            @Override
                            public void afterTextChanged(Editable editable) {
                                String content = editable.toString();
                                if (!content.isEmpty()) {
                                    viewModel.searchPlaces(content);
                                } else {
                                    binding.recyclerView.setVisibility(View.GONE);
                                    binding.bgImageView.setVisibility(View.VISIBLE);
                                    viewModel.placeList.clear();
                                    adapter.notifyDataSetChanged();
                                }
                            }
                        });

                        viewModel.placeLiveData.observe(PlaceFragment.this.requireActivity(), places -> {
                            if (!places.isEmpty()) {
                                binding.recyclerView.setVisibility(View.VISIBLE);
                                binding.bgImageView.setVisibility(View.GONE);
                                viewModel.placeList.clear();
                                viewModel.placeList.addAll(places);
                                adapter.notifyDataSetChanged();
                            } else {
                                Toast.makeText(PlaceFragment.this.getActivity(),
                                        "未能查询到任何地点",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    getLifecycle().removeObserver(this);
                }
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentPlaceBinding.inflate(inflater);
        viewModel = new ViewModelProvider(this).get(PlaceViewModel.class);
        Log.i("PlaceFragment", "onCreateView start");
        return binding.getRoot();
    }
}
