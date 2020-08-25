package com.covid19app.ui.safetyguide;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PrecautionaryMeasuresViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PrecautionaryMeasuresViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

}