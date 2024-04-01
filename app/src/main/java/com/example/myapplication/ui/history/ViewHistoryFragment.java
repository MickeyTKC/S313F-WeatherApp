package com.example.myapplication.ui.history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.databinding.FragmentViewHistoryBinding;
import com.example.myapplication.model.CurrentWeather;
import com.example.myapplication.model.HistoricalWeather;
import com.example.myapplication.ui.home.HomeViewModel;

public class ViewHistoryFragment extends Fragment {
    private FragmentViewHistoryBinding binding;
    private ViewHistoryModel viewHistoryModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ViewHistoryModel viewHistoryModel =
                new ViewModelProvider(this).get(ViewHistoryModel.class);

        binding = FragmentViewHistoryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
         viewHistoryModel= new ViewModelProvider(this).get(ViewHistoryModel.class);
         final TextView textMinTemp =binding.minTemp;
        viewHistoryModel.getTempMin().observe(getViewLifecycleOwner(), text->{
            textMinTemp.setText(
                    HistoricalWeather.data.get(HistoricalWeather.TEMP_MIN));
        });
    }
}
