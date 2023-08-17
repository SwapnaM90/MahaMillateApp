package com.mninetytechnology.mahamillateapp.acitivities.ui.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mninetytechnology.mahamillateapp.R;
import com.mninetytechnology.mahamillateapp.acitivities.base.BaseActivity;
import com.mninetytechnology.mahamillateapp.databinding.ActivityAboutUsBinding;

public class AboutUsActivity extends BaseActivity {
    private ActivityAboutUsBinding binding;
    private ProgressDialog progressDialog;
    private String url_Api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_about_us);
        try {
            progressDialog = new ProgressDialog(this);
            url_Api = "https://milletsindia.org/about-us2/";

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
            binding.wvAboutUs.setWebViewClient(new WebViewClient());
            binding.wvAboutUs.setWebChromeClient(new MyWebChromeClient(url_api));
            binding.wvAboutUs.getSettings().setJavaScriptEnabled(true);
            binding.wvAboutUs.getSettings().setSupportZoom(true);
            binding.wvAboutUs.getSettings().setAllowContentAccess(true);
            binding.wvAboutUs.getSettings().setBuiltInZoomControls(true);
            binding.wvAboutUs.getSettings().setDisplayZoomControls(false);

            binding.wvAboutUs.loadUrl(url_api);
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