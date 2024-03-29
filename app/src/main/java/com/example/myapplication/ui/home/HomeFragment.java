package com.example.myapplication.ui.home;

import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {
    private static String tag = "HomeFragment";
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        // Setup Binding
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final TextView textCurrentDate = binding.textCurrentDate;

        final TextView textCurrentAddress = binding.textCurrentAddress;
        final TextView textCurrentTemp = binding.textCurrentTemp;
        final TextView textCurrentTempDetail = binding.textCurrentTempDetail;
        final TextView textCurrentWind = binding.textCurrentWind;
        final TextView textCurrentVisibility = binding.textCurrentVisibility;

        homeViewModel.getText().observe(getViewLifecycleOwner(), textCurrentDate::setText);
        homeViewModel.getText().observe(getViewLifecycleOwner(), textCurrentAddress::setText);
        homeViewModel.getText().observe(getViewLifecycleOwner(), textCurrentTemp::setText);
        homeViewModel.getText().observe(getViewLifecycleOwner(), textCurrentTempDetail::setText);
        homeViewModel.getText().observe(getViewLifecycleOwner(), textCurrentWind::setText);
        homeViewModel.getText().observe(getViewLifecycleOwner(), textCurrentVisibility::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}