package com.screenster.sosts.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.screenster.sosts.R;


public class MemberLogin extends AppCompatActivity {

    EditText login_emailaddress,login_password;
    TextView txt_login,txt_forgot_password,header_txt;
    ImageView headerback;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_login);

        login_emailaddress=(EditText)findViewById(R.id.login_emailaddress);
        login_password=(EditText)findViewById(R.id.login_password);
        txt_login=(TextView) findViewById(R.id.txt_login);
        txt_forgot_password=(TextView) findViewById(R.id.txt_forgot_password);
        headerback = findViewById(R.id.header_back);

        headerback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent back = new Intent(MemberLogin.this, WelcomeActivity.class);
                startActivity(back);

            }
        });
        header_txt = (TextView) findViewById(R.id.header_txt);

        header_txt.setText("Member Login");

        txt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!Patterns.EMAIL_ADDRESS.matcher(login_emailaddress.getText().toString()).matches()){

                    Toast.makeText(MemberLogin.this, "Please enter valid email", Toast.LENGTH_SHORT).show();
                }
                else if(login_password.getText().toString().equals("")){
                    Toast.makeText(MemberLogin.this, "Please enter password.", Toast.LENGTH_SHORT).show();


                }
                else {
                    Intent i = new Intent(MemberLogin.this, MainActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    i.putExtra("flag",1);
                    startActivity(i);
                }
            }
        });

        txt_forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MemberLogin.this, forgot_password.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);
            }
        });

    }

    @Override
    public void onBackPressed() {

    }
}
