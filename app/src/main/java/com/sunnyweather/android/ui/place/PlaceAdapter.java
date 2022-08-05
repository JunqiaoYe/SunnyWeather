package com.sunnyweather.android.ui.place;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.sunnyweather.android.SunnyWeatherApplication;
import com.sunnyweather.android.databinding.PlaceItemBinding;
import com.sunnyweather.android.logic.model.place.Place;
import com.sunnyweather.android.ui.weather.WeatherActivity;

import java.util.List;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.ViewHolder> {

    private final Fragment fragment;

    private final List<Place> placeList;

    public PlaceAdapter(Fragment fragment, List<Place> placeList) {
        this.fragment = fragment;
        this.placeList = placeList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PlaceItemBinding binding = PlaceItemBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent,
                false);
        ViewHolder holder = new ViewHolder(binding);
        holder.itemView.setOnClickListener(view -> {
            int position = holder.getBindingAdapterPosition();
            Place place = placeList.get(position);
            Intent intent = new Intent(parent.getContext(), WeatherActivity.class);
            intent.putExtra(SunnyWeatherApplication.INTENT_STRING_EXTRA_LNG, place.getLocation().getLng());
            intent.putExtra(SunnyWeatherApplication.INTENT_STRING_EXTRA_LAT, place.getLocation().getLat());
            intent.putExtra(SunnyWeatherApplication.INTENT_STRING_EXTRA_PLACE_NAME, place.getName());
            fragment.startActivity(intent);
//            fragment.requireActivity().finish();
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Place place = placeList.get(position);
        holder.placeName.setText(place.getName());
        holder.placeAddress.setText(place.getAddress());
    }

    @Override
    public int getItemCount() {
        return placeList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView placeName;

        TextView placeAddress;

        public ViewHolder(@NonNull PlaceItemBinding binding) {
            super(binding.getRoot());

            placeName = binding.placeName;
            placeAddress = binding.placeAddress;
        }
    }
}
