package com.covid19app.ui.news;

import android.graphics.Color;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.covid19app.MainActivity;
import com.covid19app.R;
import com.covid19app.adapters.HelplineAdapter;
import com.covid19app.models.news.ArticlesItem;

import java.util.List;

public class NewsFragment extends Fragment {

    private NewsViewModel newsViewModel;
    private View root;

    private static String EXTRA_IS_FROM = "is_from";

    public static NewsFragment getInstance(Boolean isFromActivity) {
        NewsFragment fragment = new NewsFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(EXTRA_IS_FROM, isFromActivity);
        fragment.setArguments(bundle);
        return fragment;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        newsViewModel =
                ViewModelProviders.of(this).get(NewsViewModel.class);
        root = inflater.inflate(R.layout.fragment_news, container, false);
        if (getArguments() != null && getArguments().getBoolean(EXTRA_IS_FROM, false))
            root.findViewById(R.id.toolbar).setVisibility(View.GONE);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView ivMenu = root.findViewById(R.id.ivMenu);
        final RecyclerView rvNews = root.findViewById(R.id.rvNews);
        TextView tvTitle = root.findViewById(R.id.tvTitle);
        final SwipeRefreshLayout mSwipeRefreshLayout = root.findViewById(R.id.swipeRefresh);
        mSwipeRefreshLayout.setColorSchemeColors(Color.DKGRAY, Color.GREEN, Color.RED, Color.BLUE);
        mSwipeRefreshLayout.setRefreshing(true);

        tvTitle.setText(getString(R.string.title_news));
        rvNews.setLayoutManager(new LinearLayoutManager(getContext()));
        rvNews.setAdapter(new NewsAdapter());

        ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getActivity() != null)
                    ((MainActivity) getActivity()).openMenuDrawer();
            }
        });


        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                newsViewModel.callCovidRecordApi();
            }
        });

        newsViewModel.getNewsResponseMutableLiveData().observe(getViewLifecycleOwner(), new Observer<List<ArticlesItem>>() {
            @Override
            public void onChanged(List<ArticlesItem> articlesItems) {
                mSwipeRefreshLayout.setRefreshing(false);
                if (rvNews.getAdapter() != null)
                    ((NewsAdapter) rvNews.getAdapter()).submitList(articlesItems);
            }
        });
        newsViewModel.callCovidRecordApi();
    }
}