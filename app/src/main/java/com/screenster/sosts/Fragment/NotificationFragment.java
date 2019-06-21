package com.screenster.sosts.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;


import com.screenster.sosts.Adapter.NotificationAdapter;
import com.screenster.sosts.R;

import java.util.ArrayList;

/**
 * Created by USER on 8/13/2018.
 */

public class NotificationFragment extends Fragment {

    NotificationAdapter adapter;
    LinearLayoutManager layoutManager;
    ArrayList<String> Notification_list=new ArrayList<>();

    private RecyclerView recList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_notification, container, false);

        recList = (RecyclerView)rootView.findViewById(R.id.notification_recycler_view);


        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);
        adapter=new NotificationAdapter(getActivity(),Notification_list);
        recList.setAdapter(adapter);


        return rootView;
    }





}
