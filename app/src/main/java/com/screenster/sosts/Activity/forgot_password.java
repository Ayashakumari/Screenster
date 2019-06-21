package com.screenster.sosts.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.screenster.sosts.R;


public class forgot_password extends AppCompatActivity {

    EditText forgot_emailaddress;
    TextView txt_submit,header_txt;
    ImageView header_back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        header_txt=(TextView)findViewById(R.id.header_txt);
        header_txt.setText("Forgot Password");
        header_back = (ImageView) findViewById(R.id.header_back);
        header_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        forgot_emailaddress=(EditText)findViewById(R.id.login_emailaddress);
        txt_submit=(TextView) findViewById(R.id.txt_submit);

        txt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!Patterns.EMAIL_ADDRESS.matcher(forgot_emailaddress.getText().toString()).matches()){

                    Toast.makeText(forgot_password.this, "Please enter valid email", Toast.LENGTH_SHORT).show();

                }
                else {
                    Intent i = new Intent(forgot_password.this, MemberLogin.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(i);
                }
            }
        });

    }

    @Override
    public void onBackPressed() {

    }
}
