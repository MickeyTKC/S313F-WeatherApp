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

        //Minimum temperature of past 7 days
        ArrayList<TextView> textMin = new ArrayList<>();
        textMin.add(binding.minTempMon);
        textMin.add(binding.minTempTue);
        textMin.add(binding.minTempWed);
        textMin.add(binding.minTempThu);
        textMin.add(binding.minTempFri);
        textMin.add(binding.minTempSat);
        textMin.add(binding.minTempSun);

        //Maximum temperature for past 7 days
        ArrayList<TextView> textMax = new ArrayList<>();
        textMax.add(binding.maxTempMon);
        textMax.add(binding.maxTempTue);
        textMax.add(binding.maxTempWed);
        textMax.add(binding.maxTempThu);
        textMax.add(binding.maxTempFri);
        textMax.add(binding.maxTempSat);
        textMax.add(binding.maxTempSun);

        viewHistoryModel.getTempMin().observe(getViewLifecycleOwner(), text -> {
            for(int i = 0; i < 7; i++){

                String tempMinKey = HistoricalWeather.TEMP_MIN + i;
                String tempMinValue = HistoricalWeather.data.get(tempMinKey);
                TextView textMinDay = textMin.get(i);

                if (tempMinValue != null) {
                    textMinDay.append(tempMinValue);

                    Log.d("ViewHistoryFragment", "minTemp value: " + tempMinValue);

                }else {
                    Log.d("ViewHistoryFragment", "minTemp value is null");
                }
                textMinDay.setText(tempMinValue);
            }
        });

        viewHistoryModel.getTempMax().observe(getViewLifecycleOwner(), text -> {
            for(int i = 0; i < 7; i++){
                String tempMaxKey = HistoricalWeather.TEMP_MAX + i;
                String tempMaxValue = HistoricalWeather.data.get(tempMaxKey);
                TextView textMaxDay = textMax.get(i);
                if (tempMaxValue != null) {
                    textMaxDay.append(tempMaxValue);
                    Log.d("ViewHistoryFragment", "maxTemp value: " + tempMaxValue);
                }else {
                    Log.d("ViewHistoryFragment", "maxTemp value is null");
                }
                textMaxDay.setText(tempMaxValue);
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
