package com.screenster.sosts.Activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;

import android.app.DatePickerDialog;


import android.app.Dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.screenster.sosts.R;
import com.screenster.sosts.Util.GPSTracker;
import com.screenster.sosts.Util.SaveSharedPrefernces;
import com.screenster.sosts.Util.helper;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static com.screenster.sosts.R.*;
import static com.screenster.sosts.R.id.compaign_location;


public class PostcompaignActivity extends AppCompatActivity {
    EditText compaign_name, entity_required, price_entry;
    EditText notes_user, compaign_detail;
    TextView locations, compaign_radius, compaign_date, compaign_expiry;
    TextView write_count_txt;
    double latis, longis;
    private   EditText write_some_txt;
    ImageView header_back;
    TextView header_text;
    TextView buttonpost;
    Calendar newCalendar,cal;
    Intent flag_intent;

    String lat,lang,location;
    String features;

    Geocoder geocoder;
    List<Address> address_list;

    private DatePickerDialog fromDatePickerDialog;

    String from_date="",to_date="";

    private SimpleDateFormat dateFormatter;

    GPSTracker gpsTracker;

    Context context;
    int flag = 0;
//    String date_time, date_t;
//    int mYear, mMonth, mDay, mHour, mMinute;

    SaveSharedPrefernces ssp;

    int e1=0,e2=0;
    TextView budget;



    String[] Distance_list = {"10 mile", "20 mile", "30 mile", "40 mile", "50 mile", "60 mile", "70 mile", "80 mile", "90 mile", "100 mile"};


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_postcompaign);
        ssp=new SaveSharedPrefernces();
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        compaign_name = findViewById(id.compaign_name);
        entity_required = findViewById(id.compaign_entry);
        price_entry = findViewById(id.compaign_price);

        locations = findViewById(id.compaign_location);
        write_some_txt=findViewById(R.id.write_some_txt);

        budget = findViewById(id.budget);



        compaign_radius = findViewById(id.compaign_radius);

        compaign_date = findViewById(id.compaign_date);

        compaign_expiry = findViewById(id.compaign_expiry);

        notes_user = findViewById(id.compaign_notesuser);
       // compaign_detail = findViewById(id.notes);

        write_count_txt = findViewById(id.write_count_txt);

        buttonpost = findViewById(id.btn_post);


        header_back = findViewById(id.header_back);
        header_text = findViewById(id.header_txt);
        header_text.setText("Post Compaign");

        flag_intent = getIntent();
        flag = flag_intent.getIntExtra("flag", 0);


        header_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag==31)
                {
                    Intent i=new Intent(PostcompaignActivity.this,MainActivity.class);
                    i.putExtra("flag",12);
                    i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(i);
                }
                else {
                    Intent i = new Intent(PostcompaignActivity.this, MainActivity.class);
                    i.putExtra("flag", 13);
                    i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(i);
                }
            }
        });


        write_some_txt.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String value = charSequence.toString();
                int data;
                if (value.length() < 1) {
                    write_count_txt.setText("0/250");
                } else {
                    data = 250 - value.length();
                    write_count_txt.setText(value.length() + "/" + 250);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        compaign_radius.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(PostcompaignActivity.this);
                builder.setTitle("Select distance");
                builder.setItems(Distance_list, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int position) {

                        compaign_radius.setText(Distance_list[position].toString());

                        Log.d("<><>><>", "???????" + compaign_radius.getText().toString().replaceAll("mile", ""));



                    }


                });
                Dialog dialog = builder.create();
                dialog.show();

            }

        });


        compaign_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                newCalendar = Calendar.getInstance();
                fromDatePickerDialog = new DatePickerDialog(PostcompaignActivity.this, new DatePickerDialog.OnDateSetListener() {

                    public void onDateSet(DatePicker arg0, int year, int monthOfYear, int dayOfMonth) {

                        newCalendar.set(year, monthOfYear, dayOfMonth);

                        compaign_date.setText(dateFormatter.format(newCalendar.getTime()).toString());


                        from_date =compaign_date.getText().toString();
                        Log.d("from date",from_date);
                    }

                }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
                fromDatePickerDialog.getDatePicker().setMaxDate(newCalendar.getTimeInMillis());
                newCalendar.add(Calendar.YEAR, 0);

                fromDatePickerDialog.show();
            }
        });

        locations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(PostcompaignActivity.this, com.screenster.sosts.Activity.Map.class);
                i.putExtra("register","1");
                i.putExtra("location",location);
                i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);



            }
        });
        gpsTracker = new GPSTracker(PostcompaignActivity.this);
        lat = String.valueOf(gpsTracker.getLatitude());

        Log.d("Latitude","" +lat);
        lang = String.valueOf(gpsTracker.getLongitude());
        Log.d("longitude", "" + lang);
        latis = Double.parseDouble(lat);
        longis = Double.parseDouble(lang);

        if (helper.latitude.equals("")) {
            helper.latitude = lat;
        }
        if (helper.longitude.equals("")) {

            helper.longitude = lang;
        }
        geocoder = new Geocoder(PostcompaignActivity.this, Locale.getDefault());

        try {
            address_list = geocoder.getFromLocation(latis, longis, 1);

            if (address_list.isEmpty()) {
                Toast.makeText(PostcompaignActivity.this, "hhh", Toast.LENGTH_SHORT).show();

            } else {
                if (address_list.size() > 0) {

                    features = address_list.get(0).getAddressLine(0);
                    Log.d("features", features);


                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }







        buttonpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (compaign_name.getText().toString().equals("")) {
                    Toast.makeText(PostcompaignActivity.this, "Please enter  name", Toast.LENGTH_SHORT).show();
                }

                   else if(entity_required.getText().toString().equals(""))
                {
                Toast.makeText(PostcompaignActivity.this, "Please enter business name", Toast.LENGTH_SHORT).show();
                }

                else if(price_entry.getText().toString().equals(""))
                {
                    Toast.makeText(PostcompaignActivity.this, "Please enter business location", Toast.LENGTH_SHORT).show();
                }
                else  if(locations.getText().toString().equals(""))
                {
                    Toast.makeText(PostcompaignActivity.this, "Please enter service provider distance", Toast.LENGTH_SHORT).show();
                }

                else if (compaign_radius.getText().toString().equals("")){
                    Toast.makeText(PostcompaignActivity.this,"please enter radius",Toast.LENGTH_LONG).show();
                }
                else if (compaign_expiry.getText().toString().equals("")){
                    Toast.makeText(PostcompaignActivity.this,"Please enter date",Toast.LENGTH_LONG).show();
                }
                else if (compaign_expiry.getText().toString().equals("")){
                    Toast.makeText(PostcompaignActivity.this,"Please enter accept and expiry date",Toast.LENGTH_LONG).show();
                }
                else if (notes_user.getText().toString().equals("")){
                    Toast.makeText(PostcompaignActivity.this,"Please enter user notes ",Toast.LENGTH_LONG).show();

                }
                else if (write_some_txt.getText().toString().equals("")){

                    Toast.makeText(PostcompaignActivity.this,"Please enter Compaign Detail ",Toast.LENGTH_LONG).show();

                }
                else {
                    Intent post = new Intent(PostcompaignActivity.this,MainActivity.class);
                    post.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    post.putExtra("flag",12);
                    startActivity(post);

                }



            }
        });

        entity_required.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                if (s.length()!=0) {
                    e1 = Integer.parseInt(entity_required.getText().toString());
                }


                if(e1!=0&&e2!=0) {
                    if(TextUtils.isEmpty(entity_required.getText().toString())||TextUtils.isEmpty(price_entry.getText().toString()))
                    {
                        budget.setText("Total Budget $0.00");
                    }
                    else {
                        budget.setText(String.valueOf("Total Budget $" + e1 * e2) + ".00");
                    }

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        price_entry.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.length()!=0) {
                    e2 = Integer.parseInt(price_entry.getText().toString());
                }
                if(e1!=0&&e2!=0) {
                    if(TextUtils.isEmpty(entity_required.getText().toString())||TextUtils.isEmpty(price_entry.getText().toString()))
                    {
                        budget.setText("Total Budget $0.00");
                    }
                    else {
                        budget.setText(String.valueOf("Total Budget $" + e1 * e2) + ".00");
                    }

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });




    }

    @Override
    protected void onResume() {

        if (!helper.location.equals("")) {

            locations.setText(helper.location);

        } else {
            if (!TextUtils.isEmpty(features)) {
                locations.setText(features);
            } else {
                locations.setText("Select  Location");
            }
        }

//        buttonpost.setText(helper.category_location);

        super.onResume();
    }
}


