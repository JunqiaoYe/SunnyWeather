package com.sunnyweather.android.ui.place;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.sunnyweather.android.databinding.FragmentPlaceBinding;

public class PlaceFragment extends Fragment {

    private FragmentPlaceBinding binding;

    private PlaceViewModel viewModel;

    private PlaceAdapter adapter;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        requireActivity().getLifecycle().addObserver((LifecycleEventObserver) (source, event) -> {
            LinearLayoutManager layoutManager = new LinearLayoutManager(requireActivity());
            binding.recyclerView.setLayoutManager(layoutManager);
            adapter = new PlaceAdapter(this, viewModel.placeList);
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

            viewModel.placeLiveData.observe(requireActivity(), places -> {
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
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentPlaceBinding.inflate(getLayoutInflater());
        viewModel = new ViewModelProvider(this).get(PlaceViewModel.class);
        return binding.getRoot();
    }
}
