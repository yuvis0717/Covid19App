package com.covid19app.ui.testcenter;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.covid19app.MainActivity;
import com.covid19app.R;
import com.covid19app.adapters.TestCenterAdapter;
import com.covid19app.ui.helpline.ItemClickCallback;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TestCentersFragment extends Fragment {

    private TestCentersViewModel testCentersViewModel;
    private View root;

    private static String EXTRA_IS_FROM = "is_from";

    public static TestCentersFragment getInstance(Boolean isFromActivity) {
        TestCentersFragment fragment = new TestCentersFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(EXTRA_IS_FROM, isFromActivity);
        fragment.setArguments(bundle);
        return fragment;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        testCentersViewModel =
                ViewModelProviders.of(this).get(TestCentersViewModel.class);
        root = inflater.inflate(R.layout.fragment_test_centers, container, false);
        if (getArguments() != null && getArguments().getBoolean(EXTRA_IS_FROM, false))
            root.findViewById(R.id.toolbar).setVisibility(View.GONE);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView ivMenu = root.findViewById(R.id.ivMenu);
        final RecyclerView rvTestCenters = root.findViewById(R.id.rvTestCenters);
        TextView tvTitle = root.findViewById(R.id.tvTitle);
        final SwipeRefreshLayout mSwipeRefreshLayout = root.findViewById(R.id.swipeRefresh);
        mSwipeRefreshLayout.setColorSchemeColors(Color.DKGRAY, Color.GREEN, Color.RED, Color.BLUE);
        mSwipeRefreshLayout.setRefreshing(true);
        tvTitle.setText(getString(R.string.title_test_centers));

        rvTestCenters.setLayoutManager(new LinearLayoutManager(getContext()));
        rvTestCenters.setAdapter(new TestCenterAdapter(new ItemClickCallback() {
            @Override
            public void onItemClick(int position) {
                if (rvTestCenters.getAdapter() != null) {
                    TestCentersModel mBean = ((TestCenterAdapter) rvTestCenters.getAdapter()).getCurrentItem(position);
                    String map = "http://maps.google.co.in/maps?q=" + mBean.getAddress();
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(map));
                    startActivity(intent);
                }
            }
        }));

        ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getActivity() != null)
                    ((MainActivity) getActivity()).openMenuDrawer();
            }
        });

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("test_centers");

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mSwipeRefreshLayout.setRefreshing(false);
                ArrayList<TestCentersModel> testCentersList = new ArrayList<>();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    TestCentersModel mBean = postSnapshot.getValue(TestCentersModel.class);
                    testCentersList.add(mBean);
                }
                if (rvTestCenters.getAdapter() != null)
                    ((TestCenterAdapter) rvTestCenters.getAdapter()).submitList(testCentersList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}