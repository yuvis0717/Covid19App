package com.covid19app.ui.facemasktips;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FaceMaskTipsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public FaceMaskTipsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is tools fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}