package com.covid19app.ui.symptom;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SymptomCheckViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private int currentState = 1;
    private SymptomCheckModel symptomCheckModel;

    public SymptomCheckViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
        symptomCheckModel = new SymptomCheckModel();
    }

    public LiveData<String> getText() {
        return mText;
    }

    int getCurrentState() {
        return currentState;
    }

    void setCurrentState(int currentState) {
        this.currentState = currentState;
    }

    public SymptomCheckModel getSymptomCheckModel() {
        return symptomCheckModel;
    }
}