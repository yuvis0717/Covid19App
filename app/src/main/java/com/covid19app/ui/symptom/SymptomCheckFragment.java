package com.covid19app.ui.symptom;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.covid19app.MainActivity;
import com.covid19app.R;
import com.covid19app.SymptomsCheckResultActivity;
import com.covid19app.models.Constant;
import com.covid19app.models.UserInformation;
import com.covid19app.utils.CommanUtils;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.kofigyan.stateprogressbar.StateProgressBar;

import java.util.List;

public class SymptomCheckFragment extends Fragment implements View.OnClickListener {

    private SymptomCheckViewModel symptomCheckViewModel;
    private View root;
    private StateProgressBar progressBar;
    private static String EXTRA_IS_FROM = "is_from";
    private DatabaseReference mDatabase;

    public static SymptomCheckFragment getInstance(Boolean isFromActivity) {
        SymptomCheckFragment fragment = new SymptomCheckFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(EXTRA_IS_FROM, isFromActivity);
        fragment.setArguments(bundle);
        return fragment;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        symptomCheckViewModel =
                ViewModelProviders.of(this).get(SymptomCheckViewModel.class);
        root = inflater.inflate(R.layout.fragment_symptom_check, container, false);

        if (getArguments() != null && getArguments().getBoolean(EXTRA_IS_FROM, false))
            root.findViewById(R.id.toolbar).setVisibility(View.GONE);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        TextView tvTitle = root.findViewById(R.id.tvTitle);
        tvTitle.setText(getString(R.string.title_symptom_checker));
        progressBar = root.findViewById(R.id.progressBar);
        progressBar.enableAnimationToCurrentState(true);

        ImageView ivMenu = root.findViewById(R.id.ivMenu);
        MaterialButton btnNext = root.findViewById(R.id.btnNext);

        ivMenu.setOnClickListener(this);
        btnNext.setOnClickListener(this);

        addFragment(SymptomsTestFragment.getInstance(0));
    }

    private void addFragment(Fragment fragment) {
        FragmentManager fm = getChildFragmentManager();
        FragmentTransaction tm = fm.beginTransaction();
        tm.replace(R.id.flLoadFragment, fragment);
        tm.commit();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivMenu: {
                if (getActivity() != null && getActivity() instanceof MainActivity)
                    ((MainActivity) getActivity()).openMenuDrawer();
                break;
            }
            case R.id.btnNext: {
                Fragment fragment = getChildFragmentManager().findFragmentById(R.id.flLoadFragment);
                int currentState = symptomCheckViewModel.getCurrentState();

                if (fragment instanceof SymptomsTestFragment) {
                    if (((SymptomsTestFragment) fragment).getTypeFragment() == 0) {
                        String gender = ((SymptomsTestFragment) fragment).isDataSave();
                        symptomCheckViewModel.getSymptomCheckModel().setGender(gender);
                    } else if (((SymptomsTestFragment) fragment).getTypeFragment() == 1) {
                        String contactWithPatient = ((SymptomsTestFragment) fragment).isDataSave();
                        symptomCheckViewModel.getSymptomCheckModel().setContactWithPatient(contactWithPatient);
                    } else if (((SymptomsTestFragment) fragment).getTypeFragment() == 2) {
                        String publicExposedPlace = ((SymptomsTestFragment) fragment).isDataSave();
                        symptomCheckViewModel.getSymptomCheckModel().setPublicExposedPlace(publicExposedPlace);
                    }
                } else if (fragment instanceof SymptomsTest2Fragment) {
                    UserDetail age = ((SymptomsTest2Fragment) fragment).saveData();
                    if (age == null)
                        return;
                    else {
                        String name = "";
                        UserInformation information = Constant.getInfo();
                        if (information != null) {
                            name = information.getName() + " " + information.getLastName();
                        }
                        symptomCheckViewModel.getSymptomCheckModel().setName(age.getUserName() != null ? age.getUserName() : name);
                        symptomCheckViewModel.getSymptomCheckModel().setAge(age.getUserAge());
                    }
                } else if (fragment instanceof SymptomsTest5Fragment) {
                    List<String> symptoms = ((SymptomsTest5Fragment) fragment).saveData();
                    if (symptoms.size() <= 0) {
                        Toast.makeText(getContext(), "Please select any one from above", Toast.LENGTH_LONG).show();
                        return;
                    } else {
                        StringBuilder rString = new StringBuilder();
                        String sep = ", ";
                        for (int i = 0; i < symptoms.size(); i++) {
                            rString.append(symptoms.get(i));
                            if (i < (symptoms.size() - 1))
                                rString.append(sep);
                        }
                        symptomCheckViewModel.getSymptomCheckModel().setSymptoms(rString.toString());
                        symptomCheckViewModel.getSymptomCheckModel().setSymtomsList(symptoms);
                    }
                }
                switch (currentState) {
                    case 1: {
                        progressBar.setCurrentStateNumber(StateProgressBar.StateNumber.TWO);
                        addFragment(new SymptomsTest2Fragment());
                        break;
                    }
                    case 2: {
                        progressBar.setCurrentStateNumber(StateProgressBar.StateNumber.THREE);
                        if (getActivity() != null)
                            CommanUtils.hideKeyboard(getActivity());
                        addFragment(SymptomsTestFragment.getInstance(1));
                        break;
                    }

                    case 3: {
                        progressBar.setCurrentStateNumber(StateProgressBar.StateNumber.FOUR);
                        addFragment(SymptomsTestFragment.getInstance(2));
                        break;
                    }

                    case 4: {
                        progressBar.setCurrentStateNumber(StateProgressBar.StateNumber.FIVE);
                        addFragment(new SymptomsTest5Fragment());
                        break;
                    }

                    case 5: {
                        String id = mDatabase.push().getKey();
                        if (id != null) {
                            SymptomCheckModel mBean = symptomCheckViewModel.getSymptomCheckModel();
                            mBean.setId(id);
                            try {
                                int age = Integer.valueOf(mBean.getAge());
                                boolean contactWithCovid = mBean.getContactWithPatient().equalsIgnoreCase("yes") ||
                                        mBean.getPublicExposedPlace().equalsIgnoreCase("yes");

                                boolean highRisk = mBean.getSymtomsList().contains(getString(R.string.que5_option_2)) &&
                                        mBean.getSymtomsList().contains(getString(R.string.que5_option_3)) &&
                                        mBean.getSymtomsList().contains(getString(R.string.que5_option_4)) &&
                                        mBean.getSymtomsList().contains(getString(R.string.que5_option_5));

                                boolean mediumRisk = mBean.getSymtomsList().contains(getString(R.string.que5_option_2)) &&
                                        mBean.getSymtomsList().contains(getString(R.string.que5_option_3)) &&
                                        mBean.getSymtomsList().contains(getString(R.string.que5_option_4));

                                boolean lowRisk = mBean.getSymtomsList().size() > 1;

                                if (((age < 15 || age > 50) || contactWithCovid) && highRisk) {
                                    mBean.setRisk("High");
                                } else if (mediumRisk) {
                                    mBean.setRisk("Medium");
                                } else if (lowRisk) {
                                    mBean.setRisk("Low");
                                } else {
                                    mBean.setRisk("No Risk");
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            mDatabase.child("users").child(id).setValue(symptomCheckViewModel.getSymptomCheckModel()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    if (getActivity() != null)
                                        getActivity().onBackPressed();
                                    progressBar.checkStateCompleted(true);
                                    Intent intent = new Intent(getContext(), SymptomsCheckResultActivity.class);
                                    intent.putExtra("data", new Gson().toJson(symptomCheckViewModel.getSymptomCheckModel()));
                                    startActivityForResult(intent, 100);
                                }
                            });


                        } else {
                            Toast.makeText(getContext(), "Something went wrong please try again.!!!", Toast.LENGTH_LONG).show();
                        }
                    }
                }
                symptomCheckViewModel.setCurrentState(currentState + 1);
                break;
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);

    }
}