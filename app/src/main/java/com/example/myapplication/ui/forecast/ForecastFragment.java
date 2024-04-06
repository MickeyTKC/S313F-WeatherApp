package com.example.myapplication.ui.forecast;

import android.os.Bundle;
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
import com.example.myapplication.databinding.FragmentGalleryBinding;
import com.example.myapplication.model.ForecastWeather;
import com.example.myapplication.ui.gallery.GalleryViewModel;

public class ForecastFragment extends Fragment {
    private FragmentForecastBinding binding;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ForecastViewModel forecastViewModel =
                new ViewModelProvider(this).get(ForecastViewModel.class);
        binding = FragmentForecastBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final ListView listView = binding.forecastList;
        forecastViewModel.getList().observe(getViewLifecycleOwner(),list->{
            SimpleAdapter adapter = new SimpleAdapter(
                    this.getContext(),
                    ForecastWeather.data,
                    R.layout.forecast_list,
                    new String[] { ForecastWeather.DESCRIPTION, ForecastWeather.TEMP, ForecastWeather.ICON_SOURCE },
                    new int[] { R.id.weather, R.id.temp , R.id.imageForecastIcon}
            );

            listView.setAdapter(adapter);
        });
        return root;
    }
}
