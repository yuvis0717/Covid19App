package com.covid19app.ui.helpline;

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
import com.covid19app.adapters.HelplineAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HelpLineFragment extends Fragment {

    private HelpLineViewModel helpLineViewModel;
    private View root;

    private static String EXTRA_IS_FROM = "is_from";

    public static HelpLineFragment getInstance(Boolean isFromActivity) {
        HelpLineFragment fragment = new HelpLineFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(EXTRA_IS_FROM, isFromActivity);
        fragment.setArguments(bundle);
        return fragment;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        helpLineViewModel =
                ViewModelProviders.of(this).get(HelpLineViewModel.class);
        root = inflater.inflate(R.layout.fragment_helpline, container, false);
        if (getArguments() != null && getArguments().getBoolean(EXTRA_IS_FROM, false))
            root.findViewById(R.id.toolbar).setVisibility(View.GONE);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView ivMenu = root.findViewById(R.id.ivMenu);
        final RecyclerView rvHelpLine = root.findViewById(R.id.rvHelpLine);
        TextView tvTitle = root.findViewById(R.id.tvTitle);
        final SwipeRefreshLayout mSwipeRefreshLayout = root.findViewById(R.id.swipeRefresh);
        mSwipeRefreshLayout.setColorSchemeColors(Color.DKGRAY, Color.GREEN, Color.RED, Color.BLUE);
        mSwipeRefreshLayout.setRefreshing(true);

        tvTitle.setText(getString(R.string.title_help_line));
        rvHelpLine.setLayoutManager(new LinearLayoutManager(getContext()));
        rvHelpLine.setAdapter(new HelplineAdapter(new ItemClickCallback() {
            @Override
            public void onItemClick(int position) {
                if (rvHelpLine.getAdapter() != null) {
                    HelplineModel mBean = ((HelplineAdapter) rvHelpLine.getAdapter()).getCurrentItem(position);
                    String phone = mBean.getNumber();
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
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

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("helplines");

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
                ArrayList<HelplineModel> helplineList = new ArrayList<>();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    HelplineModel mBean = postSnapshot.getValue(HelplineModel.class);
                    helplineList.add(mBean);
                }
                if (rvHelpLine.getAdapter() != null)
                    ((HelplineAdapter) rvHelpLine.getAdapter()).submitList(helplineList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}