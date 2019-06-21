package com.screenster.sosts.Fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.screenster.sosts.R;
import com.screenster.sosts.Util.SaveSharedPrefernces;
import com.screenster.sosts.Util.helper;



import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by dev on 09-04-2018.
 */

public class TermsOfUseFragment extends Fragment {

  private RelativeLayout neighbour_location;
        WebView terms_webView;
    String url = "http://seemcodersapps.com/findpros/terms";



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.term_of_use, container, false);
        terms_webView=rootView.findViewById(R.id.terms_webView);
        terms_webView.getSettings().setLoadsImagesAutomatically(true);
        terms_webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        terms_webView.getSettings().setJavaScriptEnabled(true);
        terms_webView.loadUrl(url);

        return  rootView;
    }














}