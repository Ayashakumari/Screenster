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
import android.widget.EditText;
import android.widget.TextView;

import com.screenster.sosts.R;
import com.screenster.sosts.Util.SaveSharedPrefernces;


import java.util.ArrayList;

/**
 * Created by USER on 4/3/2018.
 */

public class AssignmentFragment extends Fragment {
private RecyclerView recyclerView;
LinearLayoutManager layoutManager;

   String group_id="";
  ArrayList chat_list_detail=new ArrayList();
TextView error_msg;

String type="mygroup";

    private int previousTotal = 0;
    String refresh = "", chatCount;
    private boolean loading = true;
    private int visibleThreshold = 5;
    int page = 0, curSize;
    EditText edt_locationSearch;
    int firstVisibleItem, visibleItemCount, totalItemCount;
    int flagValue=0;

    Activity act;
    SaveSharedPrefernces ssp;

    private ProgressDialog progress_dialog;
    private final int SHOW_PROG_DIALOG = 0, HIDE_PROG_DIALOG = 1, LOAD_QUESTION_SUCCESS = 2;
    private String progress_dialog_msg = "", tag_string_req = "string_req";
TextView txt_all,txt_follow,txt_following;
//    ArrayList<RecentChatEntity> recent_list=new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.assignment, container, false);


        act=getActivity();
        ssp=new SaveSharedPrefernces();
        txt_all=(TextView) rootView.findViewById(R.id.txt_all);
        txt_follow=(TextView)rootView.findViewById(R.id.txt_follow);
        txt_following=(TextView)rootView.findViewById(R.id.txt_following);
        error_msg=(TextView) rootView.findViewById(R.id.error_msg);
      //  recyclerView=(RecyclerView)rootView.findViewById(R.id.chat_recycle);
        layoutManager=new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);








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


