package com.covid19app.ui.helpline;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class HelpLineViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<List<HelplineModel>> helplineList;

    public HelpLineViewModel() {
        mText = new MutableLiveData<>();
        helplineList = new MutableLiveData<>();
        mText.setValue("This is share fragment");

        ArrayList<HelplineModel> list = new ArrayList<>();
        list.add(new HelplineModel("Quebec", "12345678"));
        list.add(new HelplineModel("Montreal", "1234568790"));
        list.add(new HelplineModel("Quebec city", "12345689"));
        list.add(new HelplineModel("Laval", "12345689"));
        list.add(new HelplineModel("Longueuil", "12345689"));
        list.add(new HelplineModel("Sherbrook", "12345689"));
        list.add(new HelplineModel("Levis", "12345689"));
        helplineList.setValue(list);
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<List<HelplineModel>> getHelplineList() {
        return helplineList;
    }
}