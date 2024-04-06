package com.example.myapplication.ui.forecast;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

import java.util.HashMap;

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
                    new String[] { ForecastWeather.DT_TXT, ForecastWeather.DESCRIPTION, ForecastWeather.TEMP, ForecastWeather.ICON_SOURCE, ForecastWeather.WIND, ForecastWeather.VISIBILITY },
                    new int[] { R.id.dt, R.id.weather, R.id.temp , R.id.imageForecastIcon, R.id.textForecastWind, R.id.textForecastVisibility}
            );

            listView.setAdapter(adapter);
            listView.setOnItemClickListener(
                    new AdapterView.OnItemClickListener() {
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            HashMap<String, String> f_data = ForecastWeather.data.get(position);
                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                            builder.setTitle(getResources().getString(R.string.current_forecast_header)+"\n" + f_data.get(ForecastWeather.DT_TXT));
                            builder.setMessage(f_data.get(ForecastWeather.WEATHER)
                                    + "\n" + getResources().getString(R.string.current_temp_header) +": "+ f_data.get(ForecastWeather.TEMP)
                                    + "\n" + getResources().getString(R.string.current_wind_header) +": "+ f_data.get(ForecastWeather.WIND)
                                    + "\n" + getResources().getString(R.string.current_temp_visibility_header) +": "+ f_data.get(ForecastWeather.VISIBILITY));
                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();
                        }
                    }
            );

        });
        return root;
    }
}
