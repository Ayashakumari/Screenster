package com.screenster.sosts.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.screenster.sosts.R;

public class ApproveAndReject extends AppCompatActivity {

    TextView txt_username,txt_time,txtDone,txt_apprve_cast,txt_balance_account,txt_approved,txt_rejected;
    ImageView accept_profile_img,img_car_ster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approve_and_reject);

        accept_profile_img=findViewById(R.id.accept_profile_img);
        img_car_ster=findViewById(R.id.img_car_ster);
        txt_username=findViewById(R.id.txt_username);
        txt_time=findViewById(R.id.txt_time);
        txtDone=findViewById(R.id.txtDone);
        txt_apprve_cast=findViewById(R.id.txt_apprve_cast);
        txt_balance_account=findViewById(R.id.txt_balance_account);
        txt_approved=findViewById(R.id.txt_approved);
        txt_rejected=findViewById(R.id.txt_rejected);

        txtDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           /*     Intent i=new Intent(ApproveAndReject.this,MainActivity.class);
                i.putExtra("flag",1);
                i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);*/

           finish();
            }
        });
    }
}
