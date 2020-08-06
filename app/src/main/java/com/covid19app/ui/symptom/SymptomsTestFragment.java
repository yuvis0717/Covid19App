package com.covid19app.ui.symptom;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.covid19app.R;
import com.google.android.material.button.MaterialButton;

public class SymptomsTestFragment extends Fragment {
    private static String BUNDLE_TYPE = "type";

    private SymptomCheckViewModel symptomCheckViewModel;
    private View root;
    private int typeFragment = 0;

    static SymptomsTestFragment getInstance(int type) {
        SymptomsTestFragment fragment = new SymptomsTestFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(BUNDLE_TYPE, type);
        fragment.setArguments(bundle);
        return fragment;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        symptomCheckViewModel =
                ViewModelProviders.of(this).get(SymptomCheckViewModel.class);
        root = inflater.inflate(R.layout.fragment_symptoms_test, container, false);
        if (getArguments() != null && getArguments().containsKey(BUNDLE_TYPE))
            typeFragment = getArguments().getInt(BUNDLE_TYPE);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView tvQue1 = root.findViewById(R.id.tvQue1);
        RadioButton rbMale = root.findViewById(R.id.rbMale);
        RadioButton rbFemale = root.findViewById(R.id.rbFemale);
        RadioButton rbOther = root.findViewById(R.id.rbOther);

        RadioGroup rbGroup = root.findViewById(R.id.rbGroup);
        rbGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

            }
        });


        switch (typeFragment) {
            case 0: {
                tvQue1.setText(getString(R.string.lbl_que1));
                rbMale.setText(getString(R.string.q1_option_1));
                rbFemale.setText(getString(R.string.q1_option_2));
                rbOther.setText(getString(R.string.q1_option_3));
                break;
            }
            case 1: {
                tvQue1.setText(getString(R.string.lbl_que3));
                rbMale.setText(getString(R.string.q3_option_1));
                rbFemale.setText(getString(R.string.q3_option_2));
                rbOther.setVisibility(View.GONE);
                break;
            }

            case 2: {
                tvQue1.setText(getString(R.string.lbl_que4));
                rbMale.setText(getString(R.string.q3_option_1));
                rbFemale.setText(getString(R.string.q3_option_2));
                rbOther.setVisibility(View.GONE);
                break;
            }
        }
    }


    String isDataSave() {
        RadioGroup rbGroup = root.findViewById(R.id.rbGroup);
        int selectedId = rbGroup.getCheckedRadioButtonId();
        RadioButton radioButton = root.findViewById(selectedId);

        return radioButton.getText().toString();
    }

    public int getTypeFragment() {
        return typeFragment;
    }
}