package com.screenster.sosts.Fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.screenster.sosts.Activity.MainActivity;
import com.screenster.sosts.Activity.Transaction;
import com.screenster.sosts.Adapter.Assignment_Adapter;
import com.screenster.sosts.R;

import java.util.ArrayList;

public class My_Assignments_Fragment extends Fragment {

  RecyclerView assignment_recycler;
  LinearLayout active_linear,approved_linear,expired_linear;
  LinearLayoutManager layoutManager;
    Assignment_Adapter assignment_adapter;
    ArrayList<String> assignment_list = new ArrayList<String>();
    TextView approved_txt,active_txt,total_earning_txt,expired_txt;




    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.my__assignments__fragment, container, false);

        approved_linear=rootView.findViewById(R.id.approved_linear);
        active_linear=rootView.findViewById(R.id.active_linear);
        approved_txt=rootView.findViewById(R.id.approved_txt);
        active_txt=rootView.findViewById(R.id.active_txt);
        total_earning_txt=rootView.findViewById(R.id.total_earning_txt);
        expired_linear=rootView.findViewById(R.id.expired_linear);

        expired_txt=rootView.findViewById(R.id.expired_txt);


        total_earning_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(), Transaction.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                i.putExtra("flag",22);
                startActivity(i);
            }
        });
        assignment_recycler=rootView.findViewById(R.id.assignment_recycler);
        layoutManager=new LinearLayoutManager(getActivity());
        assignment_recycler.setLayoutManager(layoutManager);

        assignment_adapter=new Assignment_Adapter(getActivity(),assignment_list);
        assignment_recycler.setAdapter(assignment_adapter);


        active_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                active_linear.setBackgroundResource(R.drawable.shape_blue);
                approved_linear.setBackgroundResource(R.color.light_grey2);
                approved_txt.setTextColor(getResources().getColor(R.color.grey_color));
                active_txt.setTextColor(getResources().getColor(R.color.white));
                expired_linear.setBackgroundResource(R.color.light_grey2);
                expired_txt.setTextColor(getResources().getColor(R.color.grey_color));


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

        expired_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                active_linear.setBackgroundResource(R.color.light_grey2);
                approved_linear.setBackgroundResource(R.color.light_grey2);
                approved_txt.setTextColor(getResources().getColor(R.color.grey_color));
                active_txt.setTextColor(getResources().getColor(R.color.grey_color));
                expired_linear.setBackgroundResource(R.drawable.shape_blue);
                expired_txt.setTextColor(getResources().getColor(R.color.white));

                Intent i=new Intent(getActivity(), MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                i.putExtra("flag",5);
                startActivity(i);

            }
        });



        return rootView;
    }
}
