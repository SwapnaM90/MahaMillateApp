package com.mninetytechnology.mahamillateapp.acitivities.ui.profile;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mninetytechnology.mahamillateapp.R;
import com.mninetytechnology.mahamillateapp.acitivities.base.BaseActivity;
import com.mninetytechnology.mahamillateapp.databinding.ActivityPrivacyPolicyBinding;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class PrivacyPolicyActivity extends BaseActivity {
    private ActivityPrivacyPolicyBinding binding;
    private ProgressDialog progressDialog;
    private String url_Api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPrivacyPolicyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        try {
            progressDialog = new ProgressDialog(this);
            url_Api = "https://milletsindia.org/privacy_policy/";

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
            binding.wvPrivacyPolicy.setWebViewClient(new WebViewClient());
            binding.wvPrivacyPolicy.setWebChromeClient(new MyWebChromeClient(url_api));
            binding.wvPrivacyPolicy.getSettings().setJavaScriptEnabled(true);
            binding.wvPrivacyPolicy.getSettings().setSupportZoom(true);
            binding.wvPrivacyPolicy.getSettings().setAllowContentAccess(true);
            binding.wvPrivacyPolicy.getSettings().setBuiltInZoomControls(true);
            binding.wvPrivacyPolicy.getSettings().setDisplayZoomControls(false);

            binding.wvPrivacyPolicy.loadUrl(url_api);
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