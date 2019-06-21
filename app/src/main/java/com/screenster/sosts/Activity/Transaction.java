package com.screenster.sosts.Activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.screenster.sosts.Adapter.TransactionAdapter;
import com.screenster.sosts.R;
import com.screenster.sosts.Util.SaveSharedPrefernces;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class Transaction extends AppCompatActivity {

    TransactionAdapter adapter;
    LinearLayoutManager layoutManager;
    TextView header_txt;
    ImageView header_back;
    private SimpleDateFormat dateFormatter;
    ArrayList<Integer> transaction_list=new ArrayList<Integer>();
    String from_date="",to_date="";

    RelativeLayout layout_paypal,layout_bank;
    TextView start_date_txt,end_date_txt,send_to_bank_txt,total_earn_txt,balance_bank_txt;
    Calendar newCalendar,cal;
    private DatePickerDialog fromDatePickerDialog;

    SaveSharedPrefernces ssp;


    RecyclerView recList;
    int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transaction);

        ssp=new SaveSharedPrefernces();

        recList = (RecyclerView)findViewById(R.id.transaction_recycle);

        header_txt=findViewById(R.id.header_txt);
        header_txt.setText("Transactions");
        header_back=findViewById(R.id.header_back);
        start_date_txt=findViewById(R.id.start_date);
        end_date_txt=findViewById(R.id.end_date);
        send_to_bank_txt=findViewById(R.id.send_to_bank_txt);
        total_earn_txt=findViewById(R.id.total_earn_txt);
        balance_bank_txt=findViewById(R.id.balance_bank_txt);
        Intent i=getIntent();
        flag=i.getIntExtra("flag",0);


        send_to_bank_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Transaction.this,Deposite_Activivty.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("flag",21);
                startActivity(intent);

            }
        });



        if(ssp.getKEY_User_type(Transaction.this).equals("user_account"))
        {
            total_earn_txt.setText("Total Earning: $4.50");
            send_to_bank_txt.setText("Send to Bank");
            balance_bank_txt.setVisibility(View.GONE);
        }
        else
        {
            total_earn_txt.setText("Total Spent: $50.00");
            send_to_bank_txt.setText("Deposit");
            balance_bank_txt.setVisibility(View.VISIBLE);
        }

        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);


        header_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag==2)
                {
                    Intent intent=new Intent(Transaction.this,MainActivity.class);
                    intent.putExtra("flag",12);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                }
                else if(flag==22) {
                    Intent intent=new Intent(Transaction.this,MainActivity.class);
                    intent.putExtra("flag",5);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                }
                else
                {
                    finish();
                }
            }
        });


        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);
        transaction_list.add(1);
        transaction_list.add(0);
        transaction_list.add(5);
        transaction_list.add(3);
        transaction_list.add(4);
        transaction_list.add(0);
        adapter=new TransactionAdapter(this,transaction_list);
        recList.setAdapter(adapter);



        start_date_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //     Log.d("DATA","start_date_txt");
//                Toast.makeText(Transactions.this,"start_date_txt",Toast.LENGTH_SHORT).show();
                newCalendar = Calendar.getInstance();
                fromDatePickerDialog = new DatePickerDialog(Transaction.this, new DatePickerDialog.OnDateSetListener() {

                    public void onDateSet(DatePicker arg0, int year, int monthOfYear, int dayOfMonth) {
                        // Calendar newDate = Calendar.getInstance();

                        newCalendar.set(year, monthOfYear, dayOfMonth);

                        start_date_txt.setText(dateFormatter.format(newCalendar.getTime()).toString());


                        from_date =start_date_txt.getText().toString();
                        Log.d("from date",from_date);
                    }

                }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
                fromDatePickerDialog.getDatePicker().setMaxDate(newCalendar.getTimeInMillis());
                newCalendar.add(Calendar.YEAR, 0);

                fromDatePickerDialog.show();
            }
        });
        end_date_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cal = Calendar.getInstance();
                fromDatePickerDialog = new DatePickerDialog(Transaction.this, new DatePickerDialog.OnDateSetListener() {

                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        cal.set(year, monthOfYear, dayOfMonth);

                        to_date = dateFormatter.format(cal.getTime()).toString();
                        Log.d("todate",to_date);





                        if(start_date_txt.getText().toString().equals("")){

                            Toast.makeText(Transaction.this, "First select From date.", Toast.LENGTH_SHORT).show();
                        }


                        else if(from_date.compareTo(to_date)==1){

                            Toast.makeText(Transaction.this, "To Date Should be greater or equal to From Date.", Toast.LENGTH_SHORT).show();
                        }
                        else{

                            end_date_txt.setText(dateFormatter.format(cal.getTime()).toString());
                        }



                    }

                }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));

                fromDatePickerDialog.getDatePicker().setMaxDate(cal.getTimeInMillis());

                fromDatePickerDialog.show();
            }
        });


    }

}
