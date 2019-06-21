package com.screenster.sosts.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.screenster.sosts.Activity.Deposite_Activivty;
import com.screenster.sosts.Activity.MainActivity;
import com.screenster.sosts.Activity.PostcompaignActivity;
import com.screenster.sosts.Activity.Transaction;
import com.screenster.sosts.Adapter.My_Compaign_Adapter;
import com.screenster.sosts.R;
import android.support.v4.app.Fragment;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Seemtech on 8/20/2018.
 */

public class My_Compaigns extends Fragment {

    My_Compaign_Adapter my_compaign_adapter;
    ArrayList<Integer> my_compaign_list=new ArrayList<Integer>();
    RecyclerView mycompaign_recycler;
    TextView deposite_txt,approved_txt,active_txt,expired_txt,total_earning_txt;
    LinearLayoutManager layoutManager;
    LinearLayout expired_linear,active_linear,approved_linear;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.my_compaigns, container, false);
        mycompaign_recycler=rootView.findViewById(R.id.mycompaign_recycler);

        deposite_txt=rootView.findViewById(R.id.deposite_txt);
        approved_linear=rootView.findViewById(R.id.approved_linear);
        active_linear=rootView.findViewById(R.id.active_linear);
        expired_linear=rootView.findViewById(R.id.expired_linear);
        approved_txt=rootView.findViewById(R.id.approved_txt);
        active_txt=rootView.findViewById(R.id.active_txt);
        expired_txt=rootView.findViewById(R.id.expired_txt);
        total_earning_txt=rootView.findViewById(R.id.total_earning_txt);
        total_earning_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(), Transaction.class);
                i.putExtra("flag",2);
                i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);

            }
        });

        MainActivity.header_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(), PostcompaignActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                i.putExtra("flag",31);
                startActivity(i);
            }
        });


        layoutManager=new LinearLayoutManager(getActivity());
        mycompaign_recycler.setLayoutManager(layoutManager);

        my_compaign_adapter=new My_Compaign_Adapter(getActivity(),my_compaign_list);
        mycompaign_recycler.setAdapter(my_compaign_adapter);


        expired_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                expired_linear.setBackgroundResource(R.drawable.shape_blue);
                active_linear.setBackgroundResource(R.color.light_grey2);
                expired_txt.setTextColor(getResources().getColor(R.color.white));
                active_txt.setTextColor(getResources().getColor(R.color.grey_color));
                approved_linear.setBackgroundResource(R.color.light_grey2);
                approved_txt.setTextColor(getResources().getColor(R.color.grey_color));

                Intent i=new Intent(getActivity(), MainActivity.class);
                i.putExtra("flag",12);
                startActivity(i);

            }
        });

        active_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                active_linear.setBackgroundResource(R.drawable.shape_blue);
                approved_linear.setBackgroundResource(R.color.light_grey2);
                approved_txt.setTextColor(getResources().getColor(R.color.grey_color));
                active_txt.setTextColor(getResources().getColor(R.color.white));
                expired_linear.setBackgroundResource(R.color.light_grey2);
                expired_txt.setTextColor(getResources().getColor(R.color.grey_color));
                Intent i=new Intent(getActivity(), MainActivity.class);
                i.putExtra("flag",12);

            }
        });

        approved_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                approved_linear.setBackgroundResource(R.drawable.shape_blue);
                active_linear.setBackgroundResource(R.color.light_grey2);
                approved_txt.setTextColor(getResources().getColor(R.color.white));
                active_txt.setTextColor(getResources().getColor(R.color.grey_color));
                expired_linear.setBackgroundResource(R.color.light_grey2);
                expired_txt.setTextColor(getResources().getColor(R.color.grey_color));

                UserEntries userEntries = new UserEntries();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_frame, userEntries);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        deposite_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(), Deposite_Activivty.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);
            }
        });


        return rootView;
    }
}
