package com.example.myapplication.ui.history;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentForecastBinding;
import com.example.myapplication.databinding.FragmentViewHistoryBinding;
import com.example.myapplication.model.ForecastWeather;
import com.example.myapplication.model.HistoricalWeather;
import com.example.myapplication.ui.forecast.ForecastViewModel;

import java.util.ArrayList;

public class ViewHistoryFragment extends Fragment {
    private FragmentViewHistoryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ViewHistoryModel viewHistoryModel =
                new ViewModelProvider(this).get(ViewHistoryModel.class);
        binding = FragmentViewHistoryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final ListView listView = binding.historicalList;
        viewHistoryModel.getList().observe(getViewLifecycleOwner(),list->{
            SimpleAdapter adapter = new SimpleAdapter(
                    this.getContext(),
                    HistoricalWeather.data,
                    R.layout.historical_list,
                    new String[] {
                            HistoricalWeather.TIME,
                            HistoricalWeather.TEMP_MIN,
                            HistoricalWeather.TEMP_MAX,
                            HistoricalWeather.RAIN,
                            HistoricalWeather.WIND,
                    },
                    new int[] { R.id.time, R.id.minTemp, R.id.maxTemp, R.id.rainSum ,R.id.maxWind}
            );

            listView.setAdapter(adapter);
        });
        return root;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
