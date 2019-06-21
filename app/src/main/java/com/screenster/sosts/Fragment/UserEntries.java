package com.screenster.sosts.Fragment;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.screenster.sosts.Activity.MainActivity;
import com.screenster.sosts.Activity.Transaction;
import com.screenster.sosts.Adapter.NotificationAdapter;
import com.screenster.sosts.Adapter.TransactionAdapter;
import com.screenster.sosts.Adapter.UserEntriesAdapter;
import com.screenster.sosts.Fragment.My_Assignments_Fragment;
import com.screenster.sosts.R;

import java.util.ArrayList;

public class UserEntries extends Fragment {

    UserEntriesAdapter adapter;

    TextView header_txt;
    ImageView header_back;
    ArrayList<String> UserEntries_list=new ArrayList<>();
    RecyclerView recList2;
    int flag=0;
    Context context;
    Intent flag_intent;
    LinearLayout active_linear,approved_linear,expired_linear;
    TextView approved_txt,active_txt,total_earning_txt,expired_txt;

    LinearLayoutManager llm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.activity_user_entries, container, false);

        approved_linear=rootView.findViewById(R.id.approved_linear);
        active_linear=rootView.findViewById(R.id.active_linear);
        approved_txt=rootView.findViewById(R.id.approved_txt);
        active_txt=rootView.findViewById(R.id.active_txt);
        total_earning_txt=rootView.findViewById(R.id.total_earning_txt);
        expired_linear=rootView.findViewById(R.id.expired_linear);

        expired_txt=rootView.findViewById(R.id.expired_txt);
        expired_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        total_earning_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(), Transaction.class);
                startActivity(i);
            }
        });


        recList2 = (RecyclerView)rootView.findViewById(R.id.user_entries_recycler_view);
        flag_intent=getActivity().getIntent();
        flag=flag_intent.getIntExtra("flag",0);
        Log.d("flag", String.valueOf(flag));

//        header_txt=rootView.findViewById(R.id.header_txt);
//        header_txt.setText("Penny Cohen");
//        header_back=rootView.findViewById(R.id.header_back);
//        header_back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if(flag==11)
//                {
//                    Intent i=new Intent(getActivity(),MainActivity.class);
//                    i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                    i.putExtra("flag",12);
//                    startActivity(i);
//                }
//                else {
//                    Intent i=new Intent(getActivity(),MainActivity.class);
//                    i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                    i.putExtra("flag",5);
//                    startActivity(i);
//                }
//            }
//        });
//



        llm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recList2.setLayoutManager(llm);
        UserEntries_list.add("Approved");
        UserEntries_list.add("Reject");
        UserEntries_list.add("Awaiting for approval");
        adapter=new UserEntriesAdapter(getActivity(),UserEntries_list);
        recList2.setAdapter(adapter);


        active_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                active_linear.setBackgroundResource(R.drawable.shape_blue);
                approved_linear.setBackgroundResource(R.color.light_grey2);
                approved_txt.setTextColor(getResources().getColor(R.color.grey_color));
                active_txt.setTextColor(getResources().getColor(R.color.grey_color));
                expired_linear.setBackgroundResource(R.color.light_grey2);
                expired_txt.setTextColor(getResources().getColor(R.color.grey_color));

                My_Assignments_Fragment my_assignments_fragment = new My_Assignments_Fragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_frame, my_assignments_fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();


            }
        });

        approved_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                approved_linear.setBackgroundResource(R.drawable.shape_blue);
                active_linear.setBackgroundResource(R.color.light_grey2);
                approved_txt.setTextColor(getResources().getColor(R.color.grey_color));
                active_txt.setTextColor(getResources().getColor(R.color.grey_color));
                expired_linear.setBackgroundResource(R.color.light_grey2);
                expired_txt.setTextColor(getResources().getColor(R.color.grey_color));


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
