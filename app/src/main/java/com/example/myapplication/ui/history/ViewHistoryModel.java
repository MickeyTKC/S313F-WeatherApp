package com.example.myapplication.ui.history;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ViewHistoryModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public ViewHistoryModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is view history fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
