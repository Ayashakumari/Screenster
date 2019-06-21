package com.screenster.sosts.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.screenster.sosts.Adapter.BusinessNotificationAdapter;
import com.screenster.sosts.Adapter.NotificationAdapter;
import com.screenster.sosts.R;

import java.util.ArrayList;

/**
 * Created by USER on 8/22/2018.
 */

public class BusinessNotificationFragment extends Fragment {

    BusinessNotificationAdapter adapter_business;
    LinearLayoutManager layoutManager;
    ArrayList<String> Business_Notification_list=new ArrayList<>();

    private RecyclerView recList_business;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.business_notification, container, false);

        recList_business = (RecyclerView)rootView.findViewById(R.id.business_notification_recycler_view);


        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList_business.setLayoutManager(llm);
        adapter_business=new BusinessNotificationAdapter(getActivity(),Business_Notification_list);
        recList_business.setAdapter(adapter_business);


        return rootView;
    }





}

