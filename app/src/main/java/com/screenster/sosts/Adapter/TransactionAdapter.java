package com.screenster.sosts.Adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.screenster.sosts.R;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by USER on 8/16/2018.
 */

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ItemViewHolder> {

    public LayoutInflater inflater;
    Context context;

    ArrayList<Integer> transaction_list=new ArrayList<Integer>();

    public TransactionAdapter(Context applicationContext,    ArrayList<Integer> transaction_list) {

        this.transaction_list = transaction_list;
        this.context = applicationContext;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView txt_status, profile_name, description_detail, awaiting_txt, time_status, status_txt, leads_email, members_months, retail_membership, free_txt, txt_price, txt_days, members_store;


        public ItemViewHolder(View itemView) {
            super(itemView);

            txt_status=itemView.findViewById(R.id.txt_status);









        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(final ItemViewHolder itemViewHolder, final int position) {

      int s=transaction_list.get(position);
        //int number=Integer.parseInt(s);
        if(transaction_list.get(position)>0)
        {
            itemViewHolder.txt_status.setText("+  $"+transaction_list.get(position)+".00");
            itemViewHolder.txt_status.setTextColor(context.getResources().getColor(R.color.green));
        }
        else {
            itemViewHolder.txt_status.setText("-  $"+transaction_list.get(position)+".00");
            itemViewHolder.txt_status.setTextColor(context.getResources().getColor(R.color.red));
        }

    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_transaction, parent, false);
       ItemViewHolder viewHolder = new ItemViewHolder(v);
        return viewHolder;
    }


    @Override
    public int getItemCount() {
        return transaction_list.size();
    }
}

