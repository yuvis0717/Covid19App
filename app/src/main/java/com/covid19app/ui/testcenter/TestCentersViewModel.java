package com.covid19app.ui.testcenter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class TestCentersViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public TestCentersViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is send fragment");

    }

    public LiveData<String> getText() {
        return mText;
    }

}