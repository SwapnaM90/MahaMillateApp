package com.mninetytechnology.mahamillateapp.acitivities.ui.organisation.news;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.mninetytechnology.mahamillateapp.R;
import com.mninetytechnology.mahamillateapp.databinding.ActivityNewsBinding;

public class OrganisationNewsActivity extends AppCompatActivity {
    private ActivityNewsBinding binding;
    private ProgressDialog progressDialog;
    private String url_Api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        try {
            progressDialog = new ProgressDialog(this);
            url_Api = "https://mahamillets.org/";

            progressDialog.setMessage(getString(R.string.loading));
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(true);
            progressDialog.show();

            LoadUrlWebView(url_Api);
        } catch (Exception e) {
            Log.w("TAG", "onCreate", e);
        }

    }

    private void LoadUrlWebView(String url_api) {
        try {
            binding.wvNews.setWebViewClient(new WebViewClient());
            binding.wvNews.setWebChromeClient(new MyWebChromeClient(url_api));
            binding.wvNews.getSettings().setJavaScriptEnabled(true);
            binding.wvNews.getSettings().setSupportZoom(true);
            binding.wvNews.getSettings().setAllowContentAccess(true);
            binding.wvNews.getSettings().setBuiltInZoomControls(true);
            binding.wvNews.getSettings().setDisplayZoomControls(false);

            binding.wvNews.loadUrl(url_api);
        } catch (Exception e) {
            Log.w("TAG", "setUpNavigationView", e);
        }
    }

    private class MyWebChromeClient extends WebChromeClient {
        private final String urlAccount;

        public MyWebChromeClient(String urlAccount) {
            this.urlAccount = urlAccount;
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            try {
                //Tools.LogCat(context, "INSIDE MyWebChromeClient | onProgressChanged / newProgress1:" + newProgress);
                progressDialog.setMessage(newProgress + "% " + getString(R.string.loading));
                if (newProgress < 100 && !progressDialog.isShowing()) {
                    if (progressDialog != null)
                        progressDialog.show();
                }
                if (newProgress == 100) {
                    if (progressDialog != null)
                        progressDialog.dismiss();
                }
            } catch (Exception e) {
                Log.w("onProgressChanged", e);
            }
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
        }

    }
}