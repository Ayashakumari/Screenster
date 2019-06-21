package com.screenster.sosts.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;


import com.screenster.sosts.Adapter.List_Adapter;
import com.screenster.sosts.R;
import com.screenster.sosts.Util.helper;


import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by USER on 4/3/2018.
 */

public class ListViewFragment extends Fragment {

    RecyclerView recyclerView;

    static public RelativeLayout search_layout_view;
    LinearLayoutManager layoutManager;
    ArrayList arrayList = new ArrayList(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8"));

 //   ArrayList<String> dataModelsearch= new ArrayList<String >();
    List_Adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_view, container, false);

        search_layout_view = (RelativeLayout) rootView.findViewById(R.id.search_layout);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.list_recycle);
        search_layout_view.setVisibility(View.GONE);
        helper.freg_status = 0;
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter= new List_Adapter(getActivity());
        recyclerView.setAdapter(adapter);



        return rootView;


    }


}

