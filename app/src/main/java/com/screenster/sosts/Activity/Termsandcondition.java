package com.screenster.sosts.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.screenster.sosts.R;

public class Termsandcondition extends AppCompatActivity {
    WebView terms_webview;
    Context context;
    String url = "http://seemcodersapps.com/vogi/index/terms";
    ImageView wrong;
    TextView yes_agree_txt,terms_txt,terms_data_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_termsandcondition);
        yes_agree_txt=findViewById(R.id.yes_agree_txt);
        wrong = findViewById(R.id.bt_wrong);
        terms_data_txt=findViewById(R.id.terms_data_txt);
        terms_txt=findViewById(R.id.terms_txt);

        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
        Typeface custom_regular = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");

        terms_data_txt.setTypeface(custom_font);
        terms_txt.setTypeface(custom_regular);

        yes_agree_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Termsandcondition.this,ChooseAccount.class);
                startActivity(intent);
            }
        });

        wrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

    }
}
