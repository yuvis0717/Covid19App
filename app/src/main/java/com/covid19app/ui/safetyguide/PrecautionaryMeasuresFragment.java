package com.covid19app.ui.safetyguide;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.covid19app.MainActivity;
import com.covid19app.R;

public class PrecautionaryMeasuresFragment extends Fragment implements View.OnClickListener {

    private PrecautionaryMeasuresViewModel precautionaryMeasuresViewModel;
    private View root;
    private String url = "http://fightcovid.live/corvis/pages/coronavirusinfo";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        precautionaryMeasuresViewModel =
                ViewModelProviders.of(this).get(PrecautionaryMeasuresViewModel.class);
        root = inflater.inflate(R.layout.fragment_precautionary_measures, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tvTitle = root.findViewById(R.id.tvTitle);

        tvTitle.setText("Precautionary Measures");


        ImageView ivMenu = root.findViewById(R.id.ivMenu);
        ivMenu.setOnClickListener(this);

        final WebView webView2 = root.findViewById(R.id.webViewMask);
        webView2.getSettings().setJavaScriptEnabled(true);
        webView2.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);
        webView2.getSettings().setBuiltInZoomControls(false);
        webView2.getSettings().setUseWideViewPort(true);
        webView2.getSettings().setLoadWithOverviewMode(true);
        webView2.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);

        final SwipeRefreshLayout mSwipeRefreshLayout = root.findViewById(R.id.swipeRefresh);
        mSwipeRefreshLayout.setColorSchemeColors(Color.DKGRAY, Color.GREEN, Color.RED, Color.BLUE);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                webView2.loadUrl(url);
            }
        });

        webView2.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                webView.loadUrl(str);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                mSwipeRefreshLayout.setRefreshing(false);
                super.onPageFinished(view, url);
            }

            @Override
            public void onReceivedError(WebView webView, int i, String str, String str2) {
//                progressBar.setVisibility(View.GONE);
                mSwipeRefreshLayout.setRefreshing(false);
                StringBuilder sb = new StringBuilder();
                sb.append("Error:");
                sb.append(str);
                Toast.makeText(getContext(), sb.toString(), Toast.LENGTH_LONG).show();
            }
        });
        webView2.loadUrl(url);
        mSwipeRefreshLayout.setRefreshing(true);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.ivMenu) {
            if (getActivity() != null)
                ((MainActivity) getActivity()).openMenuDrawer();
        }
    }
}