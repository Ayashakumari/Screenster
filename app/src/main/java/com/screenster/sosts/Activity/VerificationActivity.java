package com.screenster.sosts.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.screenster.sosts.R;


public class VerificationActivity extends AppCompatActivity {
    EditText verify_code1, verify_code2, verify_code3, verify_code4;
    String otp1, otp2, otp3, otp4, otp5, otp6, otp, code_country,phone_number;
    TextView change, verify_mobile_no, verify_country_code,call_otp,sms_otp,call_me;
    EditText edt_mobile_number;
    TextView mobile_number, counter_time_txt;
    RelativeLayout counter_relative;
    LinearLayout resend_call;
    String ctry_code, mnum;
    int flag = 0;
    EditText txt_country_code;
    TextView btn_verify, header_title, resend_pin_txt;
    ImageView header_back;
    TextView verified_code, did_not, enter_six, counter_txt,verified_code11;
    private ProgressDialog progress_dialog;
    private final int SHOW_PROG_DIALOG = 0, HIDE_PROG_DIALOG = 1, LOAD_QUESTION_SUCCESS = 2;
    private String progress_dialog_msg = "", tag_string_req = "string_req", account_balance;
    private String msg;
    Dialog dialog1;
    int pi_intent;

    long s1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        pi_intent=getIntent().getIntExtra("pk_intent",0);





        call_otp=findViewById(R.id.call_otp);
        sms_otp=findViewById(R.id.sms_otp);
        call_me=findViewById(R.id.call_me);
        verified_code11=findViewById(R.id.verified_code11);

        header_back = findViewById(R.id.header_menu);
        counter_time_txt = findViewById(R.id.counter_time_txt);
        header_title = findViewById(R.id.header_txt);
        header_title.setText("Verification");
        //did_not = findViewById(R.id.did_not);

        counter_txt = findViewById(R.id.counter_txt);
        verified_code = findViewById(R.id.verified_code);
        resend_call = findViewById(R.id.resend_call);
        verify_code1 = (EditText) findViewById(R.id.txt_verify1);
        verify_code2 = (EditText) findViewById(R.id.txt_verify2);
        verify_code3 = (EditText) findViewById(R.id.txt_verify3);
        verify_code4 = (EditText) findViewById(R.id.txt_verify4);
        verify_country_code = findViewById(R.id.edt_country_code);
        verify_mobile_no = findViewById(R.id.change_code_mobile);

        change = findViewById(R.id.txt_change);

        btn_verify = findViewById(R.id.text_verify);
        counter_relative = findViewById(R.id.counter_relative);

        resend_pin_txt = findViewById(R.id.resend_pin_txt);


        ctry_code = getIntent().getStringExtra("country_code");
        mnum = getIntent().getStringExtra("mobile_num");


        if (!TextUtils.isEmpty(ctry_code)) {
            verify_country_code.setText(ctry_code);
        }

        if (!TextUtils.isEmpty(mnum)) {

            verify_mobile_no.setText(mnum);
        }




        header_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });

        mCountDownTimer.start();
        mCountDownTimer2.start();

        resend_pin_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
        Typeface custom_regular = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");
        verified_code.setTypeface(custom_font);
        verified_code11.setTypeface(custom_font);
        change.setTypeface(custom_regular);
        btn_verify.setTypeface(custom_font);
        verify_country_code.setTypeface(custom_font);
        verify_mobile_no.setTypeface(custom_font);
        verify_code1.setTypeface(custom_font);
        verify_code2.setTypeface(custom_font);
        verify_code3.setTypeface(custom_font);
        verify_code4.setTypeface(custom_font);
        //did_not.setTypeface(custom_font);
        resend_pin_txt.setTypeface(custom_font);
        //enter_six.setTypeface(custom_font);
        counter_txt.setTypeface(custom_font);
        counter_time_txt.setTypeface(custom_font);
        call_otp.setTypeface(custom_font);
        sms_otp.setTypeface(custom_font);
/*
        mobilenoo.setTypeface(custom_regular);
        skip_txt.setTypeface(custom_font);
        signup_agree.setTypeface(custom_font);
        privacy_policy.setTypeface(custom_font);
        terms.setTypeface(custom_font);
        application.setTypeface(custom_font);



*/



        call_me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Calling();

            }
        });


        verify_code1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (verify_code1.getText().toString().length() == 1)     //size as per your requirement
                {
                    verify_code2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

                otp1 = verify_code1.getText().toString();
                Log.d("otp1", "" + otp1);
                // TODO Auto-generated method stub
            }


        });

        verify_code2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (verify_code2.getText().toString().length() == 1)     //size as per your requirement
                {
                    verify_code3.requestFocus();
                }


                if (verify_code2.getText().toString().length() == 0)     //size as per your requirement
                {
                    verify_code1.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                otp2 = verify_code2.getText().toString();
                Log.d("otp2", "" + otp2);


            }
        });

        verify_code3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (verify_code3.getText().toString().length() == 1)     //size as per your requirement
                {
                    verify_code4.requestFocus();
                }

                if (verify_code3.getText().toString().length() == 0)     //size as per your requirement
                {
                    verify_code2.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                otp3 = verify_code3.getText().toString();
                Log.d("otp3", "" + otp3);


            }
        });

        verify_code4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (verify_code4.getText().toString().length() == 0)     //size as per your requirement
                {
                    verify_code3.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                otp4 = verify_code4.getText().toString();
                Log.d("otp4", "" + otp4);
            }
        });



        btn_verify.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                otp1 = verify_code1.getText().toString();
                otp2 = verify_code2.getText().toString();
                otp3 = verify_code3.getText().toString();
                otp4 = verify_code4.getText().toString();

                otp = otp1 + otp2 + otp3 + otp4 + otp5 + otp6;

                if (otp1.equals("") || otp2.equals("") || otp3.equals("") || otp4.equals("")){

                    Toast.makeText(VerificationActivity.this, "please enter the verification code", Toast.LENGTH_LONG).show();

                } else {

                   // flag = 8;

                    Intent createaccount = new Intent(VerificationActivity.this, MemberLogin.class);
                    startActivity(createaccount);


                }


            }
        });

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {


                mCountDownTimer.cancel();

                dialog1 = new Dialog(VerificationActivity.this);
                dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog1.setContentView(R.layout.dialog_mobileverification);
                
                dialog1.getWindow().setBackgroundDrawable(
                        new ColorDrawable(Color.TRANSPARENT));


             dialog1.setOnDismissListener(new DialogInterface.OnDismissListener() {
                 @Override
                 public void onDismiss(DialogInterface dialogInterface) {


                     mCountDownTimer.start();


                 }
             });

                TextView txt_submit_change_no = (TextView) dialog1.findViewById(R.id.txt_submit_change_no);
                edt_mobile_number = dialog1.findViewById(R.id.edt_mobile_number);
                txt_country_code = dialog1.findViewById(R.id.txt_country_code);
                code_country = GetCountryZipCode();
                Log.d("Country_Code_Local", " " + code_country);
                if (!TextUtils.isEmpty(code_country)) {
                    txt_country_code.setText(code_country);
                } else {
                    txt_country_code.setText("+1");
                }


                txt_country_code.setSelection(txt_country_code.getText().length());
                edt_mobile_number.setSelection(edt_mobile_number.getText().length());


                txt_submit_change_no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        if (txt_country_code.getText().toString().equals("")) {
                            Toast.makeText(VerificationActivity.this, "Please enter country code", Toast.LENGTH_SHORT).show();
                        } else if (edt_mobile_number.getText().toString().equals("") || edt_mobile_number.getText().toString().length() < 8 || edt_mobile_number.getText().toString().length() > 15) {
                            Toast.makeText(getApplicationContext(), "Please Enter Mobile Number Range Between 8-15", Toast.LENGTH_LONG).show();
                        } else {

                            flag = 1;
                            Intent i=new Intent(VerificationActivity.this,VerificationActivity.class);
                            i.putExtra("country_code",txt_country_code.getText().toString());
                            i.putExtra("mobile_num",edt_mobile_number.getText().toString());
                            startActivity(i);


                        }
                    }
                });
                dialog1.show();

            }

        });

     ;




    }


    CountDownTimer mCountDownTimer = new CountDownTimer(30 * 1000, 1000) {
        @Override
        public void onTick(final long millisUntilFinished) {
            //this will be called every second.

            s1=millisUntilFinished;

            resend_pin_txt.setVisibility(View.GONE);

            counter_time_txt.setText((millisUntilFinished / 1000) + "Sec");
            if (millisUntilFinished >= 30 * 1000) {

                resend_pin_txt.setVisibility(View.VISIBLE);
            }

        }




        @Override
        public void onFinish() {


            counter_relative.setVisibility(View.GONE);
            resend_pin_txt.setVisibility(View.VISIBLE);
        }
    };



    CountDownTimer mCountDownTimer2= new CountDownTimer(120 * 1000, 1000) {
        @Override
        public void onTick(final long millisUntilFinished) {
            //this will be called every second.

            s1=millisUntilFinished;




            call_me.setClickable(false);
            call_otp.setText("You can request otp via call in "+(millisUntilFinished / 1000) + " sec");

            //counter_time_txt.setText((millisUntilFinished / 1000) + "Sec");
            if (millisUntilFinished >= 120 * 1000) {

                Log.d("1111","111111");
                //resend_pin_txt.setVisibility(View.VISIBLE);
                call_me.setClickable(true);
                sms_otp.setVisibility(View.VISIBLE);
                call_me.setVisibility(View.VISIBLE);
                call_otp.setVisibility(View.GONE);
            }

        }


        @Override
        public void onFinish() {

            call_otp.setVisibility(View.GONE);
            call_me.setClickable(true);
            sms_otp.setVisibility(View.VISIBLE);
            call_me.setVisibility(View.VISIBLE);
        }
    };

    public String GetCountryZipCode() {
        String CountryID = "";
        String CountryZipCode = "";

        TelephonyManager manager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

        CountryID = manager.getSimCountryIso().toUpperCase();
        String[] rl = getResources().getStringArray(R.array.CountryCodes);
        for (int i = 0; i < rl.length; i++) {
            String[] g = rl[i].split(",");
            if (g[1].trim().equals(CountryID.trim())) {
                CountryZipCode = g[0];
                break;
            }
        }
        return CountryZipCode;
    }




}

