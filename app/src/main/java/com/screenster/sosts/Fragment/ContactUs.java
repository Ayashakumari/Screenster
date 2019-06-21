package com.screenster.sosts.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.screenster.sosts.R;
import com.screenster.sosts.Util.SaveSharedPrefernces;
import com.screenster.sosts.Util.helper;



//import com.android.volley.AuthFailureError;
//import com.android.volley.DefaultRetryPolicy;
//import com.android.volley.NetworkError;
//import com.android.volley.NoConnectionError;
//import com.android.volley.ParseError;
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.ServerError;
//import com.android.volley.TimeoutError;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
//import com.screenster.Until.SaveSharedPrefernces;
//import com.seemtech.blacktopstidapp.Activity.MainActivity;
//import com.seemtech.blacktopstidapp.Adapter.NotificationAdapter;
//import com.seemtech.blacktopstidapp.Entity.Notification_entity;
//import com.seemtech.blacktopstidapp.R;
//import com.seemtech.blacktopstidapp.Utill.Apis;
//import com.seemtech.blacktopstidapp.Utill.SaveSharedPrefernces;
//import com.seemtech.blacktopstidapp.Utill.helper;


/**
 * Created by USER on 4/3/2018.
 */

public class ContactUs extends Fragment {

    EditText name,email,phone,description;
    TextView write_count_txt;

    Button Submit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.contact_us, container, false);


    name = rootView.findViewById(R.id.contact_name);
    email = rootView.findViewById(R.id.contact_email);
    phone = rootView.findViewById(R.id.contact_mobile_number);
    description = rootView.findViewById(R.id.contact_description);
        write_count_txt = rootView.findViewById(R.id.write_count_txt);


        description.addTextChangedListener(new TextWatcher() {

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


        Submit = rootView.findViewById(R.id.contact_bt_submit);

        Submit.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (name.getText().toString().equals("")) {
                Toast.makeText(getActivity(), "Please Enter Name", Toast.LENGTH_SHORT).show();

            } else if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {

                Toast.makeText(getActivity(), "Please Enter valid Email", Toast.LENGTH_SHORT).show();
            } else if (phone.getText().toString().equals("")) {
                Toast.makeText(getActivity(), "Please Enter Mobile Number", Toast.LENGTH_SHORT).show();
            } else if (phone.getText().toString().length() < 8 ||phone.getText().toString().length() > 15) {

                Toast.makeText(getActivity(), "Please Enter Mobile number rage between 8-15", Toast.LENGTH_SHORT).show();
            } else if (description.getText().toString().equals("")) {


                Toast.makeText(getActivity(), "Please Enter description", Toast.LENGTH_SHORT).show();
            } else {

                Toast.makeText(getActivity(), "Your Query has been submitted successfully", Toast.LENGTH_SHORT).show();
            }
        }
    });





        return rootView;

}




}

