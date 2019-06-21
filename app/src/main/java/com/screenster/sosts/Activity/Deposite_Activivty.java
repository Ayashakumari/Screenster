package com.screenster.sosts.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.screenster.sosts.R;

public class Deposite_Activivty extends AppCompatActivity {

    EditText deposite_ammount_txt;
    TextView header_txt;
    ImageView header_back;
    int flag = 0;
    Intent flag_intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deposite);
        deposite_ammount_txt=findViewById(R.id.deposite_ammount_txt);

        header_txt=(TextView)findViewById(R.id.header_txt);
        header_txt.setText("Deposit");
        header_back=findViewById(R.id.header_back);
        flag_intent = getIntent();
        flag = flag_intent.getIntExtra("flag", 0);

        header_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag==21)
                {
                 /*   Intent i=new Intent(Deposite_Activivty.this,Transaction.class);
                    i.putExtra("flag",12);
                    startActivity(i);*/

                 finish();
                }
                else
                {
                    finish();
                }

            }
        });

    }
}
