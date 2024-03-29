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

import com.example.myapplication.MainActivity;
import com.example.myapplication.databinding.FragmentHomeBinding;
import com.example.myapplication.model.CurrentWeather;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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
        // Declare TextView
        final TextView textCurrentDate = binding.textCurrentDate;
        final TextView textCurrentAddress = binding.textCurrentAddress;
        final TextView textCurrentTemp = binding.textCurrentTemp;
        final TextView textCurrentTempDetail = binding.textCurrentTempDetail;
        final TextView textCurrentWind = binding.textCurrentWind;
        final TextView textCurrentVisibility = binding.textCurrentVisibility;
        // SetText to TextView


        homeViewModel.getCurrentDate().observe(getViewLifecycleOwner(), textCurrentDate::setText);
        homeViewModel.getCurrentAddress().observe(getViewLifecycleOwner(), textCurrentAddress::setText);
        homeViewModel.getCurrentTemp().observe(getViewLifecycleOwner(), textCurrentTemp::setText);
        homeViewModel.getCurrentTempDetail().observe(getViewLifecycleOwner(), textCurrentTempDetail::setText);
        homeViewModel.getCurrentWind().observe(getViewLifecycleOwner(), textCurrentWind::setText);
        homeViewModel.getCurrentVisibility().observe(getViewLifecycleOwner(), textCurrentVisibility::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}