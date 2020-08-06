package com.covid19app.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebViewUtility {
    static ProgressDialog progressDialog;
    static WebView webView;
    private Handler handler = new Handler() {
        public void handleMessage(Message message) {
            if (message.what == 1) {
                WebViewUtility.this.webViewGoBack();
            }
        }
    };

    public static void startWebView(String str, WebView webView2, final Activity activity1) {
        webView2.getSettings().setJavaScriptEnabled(true);
        webView2.setScrollBarStyle(View.SCROLLBARS_INSIDE_INSET);
        webView2.getSettings().setBuiltInZoomControls(false);
        webView2.getSettings().setUseWideViewPort(true);
        webView2.getSettings().setLoadWithOverviewMode(true);
        ProgressDialog progressDialog2 = new ProgressDialog(activity1);
        progressDialog = progressDialog2;
        progressDialog2.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        webView2.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                webView.loadUrl(str);
                return true;
            }

            public void onPageFinished(WebView webView, String str) {
                if (WebViewUtility.progressDialog.isShowing()) {
                    WebViewUtility.progressDialog.dismiss();
                }
            }

            public void onReceivedError(WebView webView, int i, String str, String str2) {
                Activity activity = activity1;
                StringBuilder sb = new StringBuilder();
                sb.append("Error:");
                sb.append(str);
                Toast.makeText(activity, sb.toString(), Toast.LENGTH_LONG).show();
            }
        });
        webView2.loadUrl(str);
    }

    /* access modifiers changed from: private */
    public void webViewGoBack() {
        webView.goBack();
    }

    public static void destroyDialogBox() {
        ProgressDialog progressDialog2 = progressDialog;
        if (progressDialog2 != null && progressDialog2.isShowing()) {
            progressDialog.dismiss();
        }
        webView = null;
    }
}
