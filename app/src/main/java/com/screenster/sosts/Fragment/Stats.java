package com.screenster.sosts.Fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.screenster.sosts.Activity.ApproveAndReject;
import com.screenster.sosts.Activity.MainActivity;
import com.screenster.sosts.Activity.PostcompaignActivity;
import com.screenster.sosts.R;

public class Stats extends Fragment {

    RelativeLayout relative_compaign,relative_approve,rejected_relative,spent_relative;
    TextView txt_compaign_state,txt_approved_state,txt_rejected_state,post_campaign,txt_spent_state,txt_compaign_count,txt_approved_count,txt_rejected_count,txt_spent_count;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.activity_stats, container, false);

        relative_compaign= (RelativeLayout)rootView.findViewById(R.id.relative_compaign);
        relative_approve= (RelativeLayout)rootView.findViewById(R.id.relative_approve);
        rejected_relative= (RelativeLayout)rootView.findViewById(R.id.rejected_relative);
        spent_relative= (RelativeLayout)rootView.findViewById(R.id.spent_relative);
        post_campaign=rootView.findViewById(R.id.post_campaign);

        txt_compaign_state= (TextView)rootView.findViewById(R.id.txt_compaign_state);
        txt_approved_state= (TextView)rootView.findViewById(R.id.txt_approved_state);
        txt_rejected_state= (TextView)rootView.findViewById(R.id.txt_rejected_state);
        txt_spent_state= (TextView)rootView.findViewById(R.id.txt_spent_state);
        txt_compaign_count= (TextView)rootView.findViewById(R.id.txt_compaign_count);
        txt_approved_count= (TextView)rootView.findViewById(R.id.txt_approved_count);
        txt_rejected_count= (TextView)rootView.findViewById(R.id.txt_rejected_count);
        txt_spent_count= (TextView)rootView.findViewById(R.id.txt_spent_count);

        post_campaign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), PostcompaignActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });


        relative_compaign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(), MainActivity.class);
                i.putExtra("flag",12);
                i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);
            }
        });

        relative_approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(), ApproveAndReject.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);
            }
        });

        rejected_relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(), ApproveAndReject.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);
            }
        });



        return rootView;
    }
}
