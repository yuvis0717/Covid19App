package com.covid19app.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.covid19app.MainActivity;
import com.covid19app.R;
import com.covid19app.SymtomsCheckActivity;
import com.covid19app.models.CountryResponseDTO;
import com.covid19app.models.RowsDTO;
import com.covid19app.network.APIInterface;
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

        homeViewModel.getResponseDTOMutableLiveData().observe(this, new Observer<CountryResponseDTO>() {
            @Override
            public void onChanged(@Nullable CountryResponseDTO s) {
                mSwipeRefreshLayout.setRefreshing(false);
                System.out.println("==>onChanged");

                if (s != null && s.getData() != null && s.getData().getRows().size() > 0) {
                    System.out.println("==>" + s.getData().getRows().size());
                    RowsDTO mBean = s.getData().getRows().get(0);
                    tvConfirm.setText(mBean.getTotalCases());
                    tvRecovered.setText(mBean.getTotalRecovered());
                    tvDeaths.setText(mBean.getTotalDeaths());
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