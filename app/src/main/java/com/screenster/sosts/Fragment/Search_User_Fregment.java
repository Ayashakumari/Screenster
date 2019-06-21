package com.screenster.sosts.Fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
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


/**
 * Created by dell on 5/16/2018.
 */

public class Search_User_Fregment extends Fragment {
TextView txt_all,txt_follow,txt_following;
RecyclerView user_list;
LinearLayoutManager layoutManager;
   SaveSharedPrefernces ssp;
   Activity act;

   String type="All";

    private ProgressDialog progress_dialog;
    private final int SHOW_PROG_DIALOG = 0, HIDE_PROG_DIALOG = 1, LOAD_QUESTION_SUCCESS = 2;
    private String progress_dialog_msg = "", tag_string_req = "string_req";
//    ArrayList<Followers_entity> followers_list=new ArrayList<>();
    int page = 0,curSize;
    private int visibleThreshold = 5;
    int firstVisibleItem, visibleItemCount, totalItemCount=0;
    private int previousTotal = 0;
    String result_count = "",followCount="",followingCount="";
    String refresh = "",post_id,uf_post_id;
    int following_type;
    int  flagvalue=0;
    TextView error;
    EditText edt_locationSearch;
    private boolean loading = true;
    String keyword="";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView= inflater.inflate(R.layout.search_user, container, false);

        ssp=new SaveSharedPrefernces();
        act=getActivity();
        txt_all=(TextView) rootView.findViewById(R.id.txt_all);
        txt_follow=(TextView)rootView.findViewById(R.id.txt_follow);
        txt_following=(TextView)rootView.findViewById(R.id.txt_following);
        user_list=(RecyclerView)rootView.findViewById(R.id.user_list);
        error=(TextView)rootView.findViewById(R.id.txt_error);
        edt_locationSearch=rootView.findViewById(R.id.locationSearch);
        layoutManager=new LinearLayoutManager(getContext());
        user_list.setLayoutManager(layoutManager);


        return rootView;

//
    }



}
