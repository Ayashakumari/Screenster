package com.screenster.sosts.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;


import com.screenster.sosts.Adapter.JobEntry_Adapter;
import com.screenster.sosts.R;


import java.util.ArrayList;

public class Assignment_Detail extends AppCompatActivity {
    JobEntry_Adapter jobEntry_adapter;
    LinearLayoutManager layoutManager;
    ArrayList<String> entry_list = new ArrayList<String>();
    RecyclerView entry_recycle;
    ImageView profile_detail_img,header_back;
    TextView title_name,profile_name,total_pic_txt,header_txt,post_entry_txt,description_txt,job_note_txt,entries_txt,accident_description_txt,need_txt;
    ScrollView scroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assignment__detail);
        scroll=(ScrollView)findViewById(R.id.scroll);
        header_txt=findViewById(R.id.header_txt);
        header_txt.setText("Real News");
        header_back=findViewById(R.id.header_back);
        accident_description_txt=findViewById(R.id.accident_description_txt);
        post_entry_txt=findViewById(R.id.post_entry_txt);
        entries_txt=findViewById(R.id.entries_txt);


        entry_recycle=findViewById(R.id.entry_recycle);
        layoutManager=new LinearLayoutManager(this);
        entry_recycle.setLayoutManager(layoutManager);

        entry_list.add("Awaiting for Approval");
        entry_list.add("Approved you have won $5.00");




        jobEntry_adapter=new JobEntry_Adapter(this,entry_list);
        entry_recycle.setAdapter(jobEntry_adapter);
        entry_recycle.setFocusable(false);
        scroll.fullScroll(ScrollView.FOCUS_UP);
        scroll.smoothScrollTo(0,0);
        total_pic_txt=findViewById(R.id.total_pic_txt);
        profile_detail_img=findViewById(R.id.profile_detail_img);
        title_name=findViewById(R.id.user_name);
        profile_name=findViewById(R.id.profile_name);
        description_txt=findViewById(R.id.description_txt);
        job_note_txt=findViewById(R.id.job_note_txt);

        need_txt=findViewById(R.id.need_txt);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
        Typeface custom_regular = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");

        title_name.setTypeface(custom_regular);
        profile_name.setTypeface(custom_regular);
        description_txt.setTypeface(custom_regular);
        job_note_txt.setTypeface(custom_regular);
        entries_txt.setTypeface(custom_regular);
        accident_description_txt.setTypeface(custom_font);
        need_txt.setTypeface(custom_font);
        total_pic_txt.setTypeface(custom_regular);

        header_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        post_entry_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Assignment_Detail.this,RealNewsActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);
            }
        });




    }
}
