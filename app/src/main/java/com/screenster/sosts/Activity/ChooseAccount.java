package com.screenster.sosts.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.screenster.sosts.R;
import com.screenster.sosts.Util.SaveSharedPrefernces;
import com.screenster.sosts.Util.helper;


public class ChooseAccount extends AppCompatActivity {
    TextView getstarted,chooseaccount,useraccount,businessaccount,continues;
    ImageView cross,business_img,user_img;
    RelativeLayout business_account_relative,user_account_relative;
    SaveSharedPrefernces ssp;

    String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_account);

        ssp=new SaveSharedPrefernces();

        getstarted = findViewById(R.id.getr_started);
        chooseaccount = findViewById(R.id.choose_acc);
        useraccount = findViewById(R.id.user_account);
        businessaccount = findViewById(R.id.business_account);
        continues = findViewById(R.id.btn_contunue);

        cross = findViewById(R.id.bt_close);
        business_img=findViewById(R.id.business_img);
        user_img=findViewById(R.id.user_img);

        user_account_relative=findViewById(R.id.user_account_relative);

        business_account_relative=findViewById(R.id.business_account_relative);

        type="user_account";

        ssp.setKEY_User_type(ChooseAccount.this,"user_account");

        user_account_relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                user_account_relative.setBackground(getResources().getDrawable(R.drawable.useraccount));
                business_account_relative.setBackground(getResources().getDrawable(R.drawable.alreadyaccount));
                user_img.setVisibility(View.VISIBLE);
                business_img.setVisibility(View.GONE);

                ssp.setKEY_User_type(ChooseAccount.this,"user_account");
                Log.d("user_typ1",ssp.getKEY_User_type(ChooseAccount.this));

                type="user_account";
            }
        });

        business_account_relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                business_img.setVisibility(View.VISIBLE);
                user_img.setVisibility(View.GONE);
                user_account_relative.setBackground(getResources().getDrawable(R.drawable.alreadyaccount));

                business_account_relative.setBackground(getResources().getDrawable(R.drawable.useraccount));

                ssp.setKEY_User_type(ChooseAccount.this,"business_account");
                Log.d("user_typ2",ssp.getKEY_User_type(ChooseAccount.this));
                type="business_account";

            }
        });
        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent choose = new Intent(ChooseAccount.this, WelcomeActivity.class);
                choose.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(choose);

            }
        });
        continues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ssp.getKEY_User_type(ChooseAccount.this).equals("user_account")) {

                    if(TextUtils.isEmpty(helper.alreadyaccounts)) {

                        Intent choose = new Intent(ChooseAccount.this, CreatesaccountActivity.class);
                        ssp.setKEY_User_type(ChooseAccount.this, "user_account");
                        startActivity(choose);
                    }
                    else
                    {
                        Intent choose = new Intent(ChooseAccount.this, MemberLogin.class);
                        ssp.setKEY_User_type(ChooseAccount.this, "user_account");
                        startActivity(choose);
                    }
                }

                    else
                    {
                        if(TextUtils.isEmpty(helper.alreadyaccounts)) {
                            Intent choose = new Intent(ChooseAccount.this, CreatesaccountActivity.class);
                            ssp.setKEY_User_type(ChooseAccount.this, "business_account");
                            startActivity(choose);
                        }
                        else
                            {
                                Intent choose = new Intent(ChooseAccount.this, MemberLogin.class);
                                ssp.setKEY_User_type(ChooseAccount.this, "business_account");
                                startActivity(choose);
                            }

                    }



            }
        });

    }
}
