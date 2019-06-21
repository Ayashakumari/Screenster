package com.screenster.sosts.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.screenster.sosts.R;

import java.util.ArrayList;
import java.util.Map;

public class JobEntry_Adapter extends RecyclerView.Adapter<JobEntry_Adapter.ItemViewHolder> {
    public ArrayList mlist_store;
    public LayoutInflater inflater;
    Context context;
    String plan_id;

    ArrayList<String> entry_list = new ArrayList<String>();
    Map<String, String> paramsCount;


    public JobEntry_Adapter(Context applicationContext, ArrayList<String> entry_list) {

        this.entry_list = entry_list;
        this.context = applicationContext;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {

        ImageView profile_detail_img,delete_img;
        TextView title_name, profile_name, description_detail,badge_txt, awaiting_txt, time_status, status_txt, leads_email, members_months, retail_membership, free_txt, txt_price, txt_days, members_store;
        RelativeLayout active_job_relative2, sub_relative;


        public ItemViewHolder(View itemView) {
            super(itemView);

            title_name = itemView.findViewById(R.id.title_name);
            profile_name = itemView.findViewById(R.id.profile_name);
            description_detail = itemView.findViewById(R.id.description_detail);
            badge_txt=itemView.findViewById(R.id.badge_txt);
            //time_status = itemView.findViewById(R.id.time_status);

            profile_detail_img=itemView.findViewById(R.id.profile_detail_img);
            delete_img=itemView.findViewById(R.id.delete_img);



            Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Light.ttf");
            Typeface custom_regular = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Regular.ttf");


            profile_name.setTypeface(custom_regular);
            title_name.setTypeface(custom_regular);



        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(final ItemViewHolder itemViewHolder, final int position) {

        itemViewHolder.description_detail.setText(entry_list.get(position));

        if(entry_list.get(position).equals("Awaiting for Approval"))
        {
            itemViewHolder.description_detail.setTextColor(context.getResources().getColor(R.color.darkbrown));
            itemViewHolder.badge_txt.setBackground(context.getResources().getDrawable(R.drawable.circle_golden_brown));
            itemViewHolder.delete_img.setVisibility(View.VISIBLE);
        }
        else
        {
            itemViewHolder.description_detail.setTextColor(context.getResources().getColor(R.color.green));
            itemViewHolder.badge_txt.setBackground(context.getResources().getDrawable(R.drawable.circle_green));

            itemViewHolder.delete_img.setVisibility(View.GONE);
        }
        itemViewHolder.delete_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                AlertDialog.Builder alert;
                if (Build.VERSION.SDK_INT >= 11) {
                    alert = new AlertDialog.Builder(context, AlertDialog.THEME_HOLO_LIGHT);
                } else {
                    alert = new AlertDialog.Builder(context);
                }
                /*  if (chat_list.get(position).getLeave_status().equals("1")){
                    alert.setMessage("Do you want to delete this group?");
                }
                else {

                    if (chat_list.get(position).getIs_group_owner().equals("yes")) {
                        alert.setMessage("Do you want to leave & delete this group?");
                    } else {
                        alert.setMessage("Do you want to leave this group?");
                    }
                }*/

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

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_job_entry, parent, false);
       ItemViewHolder viewHolder = new ItemViewHolder(v);
        return viewHolder;
    }


    @Override
    public int getItemCount() {
        return entry_list.size();
    }
}
