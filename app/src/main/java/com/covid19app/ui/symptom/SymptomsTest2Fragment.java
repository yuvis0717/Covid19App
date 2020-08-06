package com.covid19app.ui.symptom;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.covid19app.R;
import com.google.android.material.textfield.TextInputLayout;

public class SymptomsTest2Fragment extends Fragment {

    private SymptomCheckViewModel symptomCheckViewModel;
    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        symptomCheckViewModel =
                ViewModelProviders.of(this).get(SymptomCheckViewModel.class);
        root = inflater.inflate(R.layout.fragment_symptoms_test2, container, false);

        return root;
    }

    UserDetail saveData() {
        AppCompatEditText etName = root.findViewById(R.id.etName);
        TextInputLayout tilName = root.findViewById(R.id.tilName);

        AppCompatEditText etAge = root.findViewById(R.id.etAge);
        TextInputLayout tilAge = root.findViewById(R.id.tilAge);


        if (TextUtils.isEmpty(etName.getText().toString().trim())) {
            tilName.setError("Please Enter your name.");
            return null;
        } else if (TextUtils.isEmpty(etAge.getText().toString().trim())) {
            tilAge.setError("Please Enter your age.");
            return null;
        } else {
            return new UserDetail(etName.getText().toString().trim(), etAge.getText().toString().trim());
        }
    }
}