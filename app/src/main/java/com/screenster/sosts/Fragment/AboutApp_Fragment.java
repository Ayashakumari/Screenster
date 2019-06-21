package com.screenster.sosts.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.RelativeLayout;


import com.screenster.sosts.R;

import org.json.JSONObject;

/**
 * Created by dev on 16-04-2018.
 */

public class AboutApp_Fragment extends Fragment {

    WebView terms_webView;
    String url = "https://www.google.com/";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.about_app, container, false);

        terms_webView=rootView.findViewById(R.id.aboutus_webView);
        terms_webView.getSettings().setLoadsImagesAutomatically(true);
        terms_webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        terms_webView.getSettings().setJavaScriptEnabled(true);
        terms_webView.loadUrl(url);


       return rootView;
    }

}