package com.screenster.sosts.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.screenster.sosts.R;

import java.util.ArrayList;

/**
 * Created by USER on 3/14/2018.
 */

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ItemViewHolder> {

    public LayoutInflater inflater;
    Context context;

    ArrayList<String> Notification_list=new ArrayList<>();

    public NotificationAdapter(Context applicationContext,    ArrayList<String> transaction_list) {

        this.Notification_list = transaction_list;
        this.context = applicationContext;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView txt_notification, profile_name, description_detail, awaiting_txt, time_status, status_txt, leads_email, members_months, retail_membership, free_txt, txt_price, txt_days, members_store;


        public ItemViewHolder(View itemView) {
            super(itemView);

            txt_notification=itemView.findViewById(R.id.txt_notification);


        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(final ItemViewHolder itemViewHolder, final int position) {

        //String st=Notification_list.get(position);

        Spannable wordtoSpan = new SpannableString("Your Entry 1/7 of Post REALnews has been Approved by Kenneth Moody. You have won $5.00 and has been credited to wallet.");

        wordtoSpan.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context,R.color.colorPrimary)), 23, 31, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        wordtoSpan.setSpan(new StyleSpan(Typeface.BOLD),23, 31, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        wordtoSpan.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context,R.color.gree)),41,49, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        wordtoSpan.setSpan(new StyleSpan(Typeface.BOLD),41, 49, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        wordtoSpan.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context,R.color.gree)), 68, 86, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        itemViewHolder.txt_notification.setText(wordtoSpan);
//        int number=Integer.parseInt(st);
//        if(number>0)
//        {
//            itemViewHolder.txt_status.setText("+  $"+transaction_list.get(position));
//            itemViewHolder.txt_status.setTextColor(context.getResources().getColor(R.color.green));
//        }
//        else {
//            itemViewHolder.txt_status.setText("-  $"+transaction_list.get(position));
//            itemViewHolder.txt_status.setTextColor(context.getResources().getColor(R.color.red));
//        }

    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification, parent, false);
      ItemViewHolder viewHolder = new ItemViewHolder(v);
        return viewHolder;
    }


    @Override
    public int getItemCount() {
        return 5;
    }
}

