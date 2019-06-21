package com.screenster.sosts.Fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.screenster.sosts.R;
import com.screenster.sosts.Util.SaveSharedPrefernces;
import com.screenster.sosts.Util.helper;


/**
 * Created by dell on 5/17/2018.
 */

public class Membership_Fregment extends Fragment {
    ImageView header_back;
    TextView header_text;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
//    Subscribe_Adapter subscribe_adapter;

//    ArrayList<Subscribe_Plan_Entity> Plan_detail=new ArrayList<>();
//    ArrayList<Subscribe_Plan_Entity> list=new ArrayList<>();

    private ProgressDialog progress_dialog;
    private final int SHOW_PROG_DIALOG = 0, HIDE_PROG_DIALOG = 1, LOAD_QUESTION_SUCCESS = 2;
    private String progress_dialog_msg = "", tag_string_req = "string_req", account_balance;
    private String msg;
    Activity act;
    LinearLayout linear;
    SaveSharedPrefernces ssp;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView= inflater.inflate(R.layout.subscribe, container, false);
act=getActivity();
ssp=new SaveSharedPrefernces();
        helper.intent_location=0;
        helper.my_profie_intent=0;


        linear=rootView.findViewById(R.id.linear);
        linear.setVisibility(View.GONE);

        header_back=(ImageView) rootView.findViewById(R.id.header_back);
        header_back.setVisibility(View.GONE);

        header_text=(TextView) rootView.findViewById(R.id.header_txt);
        header_text.setText("Subscribe");



        recyclerView=
        rootView.findViewById(R.id.subscribe_recycle);
        linearLayoutManager=new LinearLayoutManager(act, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);




        return rootView;
    }
    Handler mHandler = new Handler(new Handler.Callback() {

        @Override
        public boolean handleMessage(android.os.Message msg) {

            switch (msg.what) {
                case SHOW_PROG_DIALOG:
                    showProgDialog();
                    break;

                case HIDE_PROG_DIALOG:
                    hideProgDialog();
                    break;

                case LOAD_QUESTION_SUCCESS:

                    break;

                default:
                    break;
            }

            return false;
        }

    });




    @SuppressLint("InlinedApi")
    private void showProgDialog() {
        progress_dialog = null;
        try {
            if (Build.VERSION.SDK_INT >= 11) {
                progress_dialog = new ProgressDialog(act, AlertDialog.THEME_HOLO_LIGHT);
            } else {
                progress_dialog = new ProgressDialog(act);
            }
            progress_dialog.setMessage(progress_dialog_msg);
            progress_dialog.setCancelable(false);
            progress_dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // hide progress
    private void hideProgDialog() {
        try {
            if (progress_dialog != null && progress_dialog.isShowing())
                progress_dialog.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }








}

