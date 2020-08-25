package com.covid19app.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.covid19app.MainActivity;
import com.covid19app.R;
import com.covid19app.SymtomsCheckActivity;
import com.covid19app.models.BreakdownsDTO;
import com.covid19app.models.CountryResponseDTO;
import com.covid19app.models.RowsDTO;
import com.google.android.material.card.MaterialCardView;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private HomeViewModel homeViewModel;
    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        root = inflater.inflate(R.layout.fragment_home, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView ivMenu = root.findViewById(R.id.ivMenu);
        MaterialCardView cvChecker = root.findViewById(R.id.cvChecker);
        MaterialCardView cvTestCenters = root.findViewById(R.id.cvTestCenters);
        MaterialCardView cvHelpLine = root.findViewById(R.id.cvHelpLine);
        final TextView tvConfirm = root.findViewById(R.id.tvConfirm);
        final TextView tvRecovered = root.findViewById(R.id.tvRecovered);
        final TextView tvDeaths = root.findViewById(R.id.tvDeaths);
        final SwipeRefreshLayout mSwipeRefreshLayout = root.findViewById(R.id.swipeRefresh);

        mSwipeRefreshLayout.setRefreshing(true);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                homeViewModel.callCovidRecordApi();
            }
        });


        ivMenu.setOnClickListener(this);
        cvChecker.setOnClickListener(this);
        cvTestCenters.setOnClickListener(this);
        cvHelpLine.setOnClickListener(this);

        homeViewModel.getResponseDTOMutableLiveData().observe(getViewLifecycleOwner(), new Observer<BreakdownsDTO>() {
            @Override
            public void onChanged(@Nullable BreakdownsDTO s) {
                mSwipeRefreshLayout.setRefreshing(false);
                System.out.println("==>onChanged");
                if (s != null) {
                    tvConfirm.setText(String.valueOf(s.getTotalConfirmedCases()));
                    tvRecovered.setText(String.valueOf(s.getTotalRecoveredCases()));
                    tvDeaths.setText(String.valueOf(s.getTotalDeaths()));
                }
            }
        });

        homeViewModel.callCovidRecordApi();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivMenu: {
                if (getActivity() != null)
                    ((MainActivity) getActivity()).openMenuDrawer();

                break;
            }
            case R.id.cvChecker: {
                SymtomsCheckActivity.getInstance(getContext(), 0);
                break;
            }
            case R.id.cvTestCenters: {
                SymtomsCheckActivity.getInstance(getContext(), 1);
                break;
            }

            case R.id.cvHelpLine: {
                SymtomsCheckActivity.getInstance(getContext(), 2);
                break;
            }

        }

    }
}