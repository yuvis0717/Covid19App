package com.covid19app.ui.symptom;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.covid19app.R;

import java.util.ArrayList;
import java.util.List;

public class SymptomsTest5Fragment extends Fragment {

    private SymptomCheckViewModel symptomCheckViewModel;
    private View root;
    private CheckBox cbNone;
    private CheckBox cbFerver;
    private CheckBox cbDryCough;
    private CheckBox cbBreath;
    private CheckBox cbHeadache;
    private CheckBox cbNoseRunning;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        symptomCheckViewModel =
                ViewModelProviders.of(this).get(SymptomCheckViewModel.class);
        root = inflater.inflate(R.layout.fragment_symptoms_test5, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cbNone = root.findViewById(R.id.cbNone);
        cbFerver = root.findViewById(R.id.cbFerver);
        cbDryCough = root.findViewById(R.id.cbDryCough);
        cbBreath = root.findViewById(R.id.cbBreath);
        cbHeadache = root.findViewById(R.id.cbHeadache);
        cbNoseRunning = root.findViewById(R.id.cbNoseRunning);
    }

    List<String> saveData() {
        ArrayList<String> list = new ArrayList<>();
        if (cbNone.isChecked())
            list.add(cbNone.getText().toString());
        if (cbFerver.isChecked())
            list.add(cbFerver.getText().toString());
        if (cbDryCough.isChecked())
            list.add(cbDryCough.getText().toString());
        if (cbBreath.isChecked())
            list.add(cbBreath.getText().toString());
        if (cbHeadache.isChecked())
            list.add(cbHeadache.getText().toString());
        if (cbNoseRunning.isChecked())
            list.add(cbNoseRunning.getText().toString());


        return list;
    }
}