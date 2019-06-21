package com.screenster.sosts.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.screenster.sosts.Activity.Assignment_Detail;
import com.screenster.sosts.R;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Seemtech on 8/14/2018.
 */

public class Assignment_Adapter extends RecyclerView.Adapter<Assignment_Adapter.ItemViewHolder> {
    public ArrayList mlist_store;
    public LayoutInflater inflater;
    Context context;
    String plan_id;
    ArrayList<String> assignment_list = new ArrayList<String>();
    Map<String, String> paramsCount;
    private ProgressDialog progress_dialog;
    private final int SHOW_PROG_DIALOG = 0, HIDE_PROG_DIALOG = 1, LOAD_QUESTION_SUCCESS = 2;
    private String progress_dialog_msg = "", tag_string_req = "string_req", msg, id;
    int productquanity = 0;
    public Assignment_Adapter(Context applicationContext, ArrayList<String> subscription_list) {

        this.assignment_list = assignment_list;
        this.context = applicationContext;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView profile_detail_img,delete_img;
        TextView title_name, profile_name, description_detail, awaiting_txt, time_status, status_txt, leads_email, members_months, retail_membership, free_txt, txt_price, txt_days, members_store;
        RelativeLayout active_job_relative2, sub_relative;
        LinearLayout assinment_linear;


        public ItemViewHolder(View itemView) {
            super(itemView);
            title_name = itemView.findViewById(R.id.title_name);
            profile_name = itemView.findViewById(R.id.profile_name);
            description_detail = itemView.findViewById(R.id.description_detail);
            awaiting_txt = itemView.findViewById(R.id.awaiting_txt);
            time_status = itemView.findViewById(R.id.time_status);
            profile_detail_img=itemView.findViewById(R.id.profile_detail_img);
            delete_img=itemView.findViewById(R.id.delete_img);
            assinment_linear=itemView.findViewById(R.id.assinment_linear);



            Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Light.ttf");
            Typeface custom_regular = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Regular.ttf");
            awaiting_txt.setTypeface(custom_font);
            time_status.setTypeface(custom_font);
            profile_name.setTypeface(custom_regular);
            title_name.setTypeface(custom_regular);






        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(final ItemViewHolder itemViewHolder, final int position) {

        itemViewHolder.assinment_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context, Assignment_Detail.class);
                context.startActivity(i);
            }
        });

        itemViewHolder.delete_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder alert;
                if (Build.VERSION.SDK_INT >= 11) {
                    alert = new AlertDialog.Builder(context, AlertDialog.THEME_HOLO_LIGHT);
                } else {
                    alert = new AlertDialog.Builder(context);
                }
                alert.setMessage("Do you want to delete?");

                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        context.fileList();
                    }
                });

                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                });



                try {
                    Dialog dialog = alert.create();
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });


    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_assignment, parent, false);
        ItemViewHolder viewHolder = new ItemViewHolder(v);
        return viewHolder;
    }


    @Override
    public int getItemCount() {
        return 10;
    }
}

