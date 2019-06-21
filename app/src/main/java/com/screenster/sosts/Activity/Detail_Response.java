package com.screenster.sosts.Activity;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.screenster.sosts.Adapter.User_Response_Adapter;
import com.screenster.sosts.R;

import java.util.ArrayList;

public class Detail_Response extends AppCompatActivity {
    User_Response_Adapter user_response_adapter;
    RecyclerView detail_response_recycle;
    ImageView profile_detail_img,header_back;
    TextView user_name,real_new_txt,accident_description_txt,job_note_txt,need_txt,user_response_txt,txt_error_msg,
            description_detail,price_txt,per_pic_txt,pic_price_txt,pic_need_txt,total_pic_txt,description_txt,header_txt;

    LinearLayoutManager layoutManager;
    ArrayList<String>user_response_list = new ArrayList<String>();

    ScrollView scroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail__response);

        scroll=findViewById(R.id.scroll);
        header_txt=findViewById(R.id.header_txt);
        header_txt.setText("RealNews");
        header_back=findViewById(R.id.header_back);
        header_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        scroll.fullScroll(ScrollView.FOCUS_UP);
        scroll.smoothScrollTo(0,0);


        detail_response_recycle=findViewById(R.id.detail_response_recycle);
        txt_error_msg=findViewById(R.id.txt_error_msg);
        detail_response_recycle.setFocusable(false);
        profile_detail_img=findViewById(R.id.profile_detail_img);
        user_name=findViewById(R.id.user_name);
        real_new_txt=findViewById(R.id.real_new_txt);
        accident_description_txt=findViewById(R.id.accident_description_txt);
        job_note_txt=findViewById(R.id.job_note_txt);
        need_txt=findViewById(R.id.need_txt);
        user_response_txt=findViewById(R.id.user_response_txt);
        description_detail=findViewById(R.id.description_detail);
        price_txt=findViewById(R.id.price_txt);
        per_pic_txt=findViewById(R.id.per_pic_txt);
        pic_need_txt=findViewById(R.id.pic_need_txt);
        total_pic_txt=findViewById(R.id.total_pic_txt);
        description_txt=findViewById(R.id.description_txt);
        pic_price_txt=findViewById(R.id.pic_price_txt);


        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
        Typeface custom_regular = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");
        user_name.setTypeface(custom_regular);
        real_new_txt.setTypeface(custom_regular);
        accident_description_txt.setTypeface(custom_font);
        job_note_txt.setTypeface(custom_regular);
        description_detail.setTypeface(custom_regular);
        price_txt.setTypeface(custom_regular);
        per_pic_txt.setTypeface(custom_font);
        total_pic_txt.setTypeface(custom_regular);
        pic_price_txt.setTypeface(custom_regular);
        need_txt.setTypeface(custom_font);
        user_response_txt.setTypeface(custom_regular);
        pic_need_txt.setTypeface(custom_font);

        layoutManager=new LinearLayoutManager(this);
        detail_response_recycle.setLayoutManager(layoutManager);

        user_response_adapter=new User_Response_Adapter(this,user_response_list);
        detail_response_recycle.setAdapter(user_response_adapter);


    }
}
