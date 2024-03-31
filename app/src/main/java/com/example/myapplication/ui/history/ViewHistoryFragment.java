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

public class ViewHistoryFragment extends Fragment {
    private FragmentViewHistoryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ViewHistoryModel viewHistoryModel =
                new ViewModelProvider(this).get(ViewHistoryModel.class);

        binding = FragmentViewHistoryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textViewHistory;
        viewHistoryModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

}
