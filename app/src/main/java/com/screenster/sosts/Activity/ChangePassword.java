package com.screenster.sosts.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.screenster.sosts.R;

public class ChangePassword extends AppCompatActivity {

    TextView btn_update,header_txt;
    private ImageView header_back;
    EditText confirm_password,newpassword,oldpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        btn_update=findViewById(R.id.btn_update);
        oldpassword=findViewById(R.id.oldpassword);
        newpassword=findViewById(R.id.newpassword);
        confirm_password=findViewById(R.id.confirm_password);
        header_txt=(TextView)findViewById(R.id.header_txt);
        header_txt.setText("Change Password");

        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Roboto-Light.ttf");
        Typeface custom_regular = Typeface.createFromAsset(getAssets(),  "fonts/Roboto-Regular.ttf");


        oldpassword.setTypeface(custom_font);
        newpassword.setTypeface(custom_font);
        confirm_password.setTypeface(custom_font);
        header_txt.setTypeface(custom_regular);
        btn_update.setTypeface(custom_font);

        header_back = (ImageView) findViewById(R.id.header_back);
        header_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(oldpassword.getText().toString().equals("")){
                    Toast.makeText(ChangePassword.this, getResources().getString(R.string.enter_old_pass), Toast.LENGTH_SHORT).show();
                }

                else if(!newpassword.getText().toString().equals(confirm_password.getText().toString())){
                    Log.d("Changepassword",newpassword.getText().toString()+"fjkv"+confirm_password.getText().toString());
                    Toast.makeText(ChangePassword.this, getResources().getString(R.string.password_does_not), Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent i = new Intent(ChangePassword.this, MemberLogin.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(i);

                }

            }
        });

    }
}

