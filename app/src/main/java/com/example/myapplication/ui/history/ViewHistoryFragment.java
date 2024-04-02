package com.example.myapplication.ui.history;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentViewHistoryBinding;
import com.example.myapplication.model.HistoricalWeather;

import java.util.ArrayList;

public class ViewHistoryFragment extends Fragment {
    private FragmentViewHistoryBinding binding;
    private ViewHistoryModel viewHistoryModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        viewHistoryModel =
                new ViewModelProvider(this).get(ViewHistoryModel.class);
        binding = FragmentViewHistoryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        viewHistoryModel = new ViewModelProvider(this).get(ViewHistoryModel.class);
        final TextView textMinTemp = binding.minTemp;
        viewHistoryModel.getTempMin().observe(getViewLifecycleOwner(), text -> {
            StringBuilder minTempValuesBuilder = new StringBuilder();
            for (int index = 0; index < 7; index++) {
                String tempMinKey = HistoricalWeather.TEMP_MIN + index;
                String tempMinValue = HistoricalWeather.data.get(tempMinKey);
                if (tempMinValue != null) {
                    minTempValuesBuilder.append(tempMinValue).append(" ");
                    Log.d("ViewHistoryFragment", "minTemp value: " + tempMinValue);
                } else {
                    Log.d("ViewHistoryFragment", "minTemp value is null");
                }
            }
            String minTempValues = minTempValuesBuilder.toString().trim();
            textMinTemp.setText(minTempValues);
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
