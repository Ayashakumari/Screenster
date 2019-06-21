package com.screenster.sosts.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
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

/**
 * Created by Seemtech on 8/20/2018.
 */

public class User_Response_Adapter extends RecyclerView.Adapter<User_Response_Adapter.ItemViewHolder> {
    public LayoutInflater inflater;
    Context context;
    String plan_id;

    ArrayList<String>user_response_list = new ArrayList<String>();
    Map<String, String> paramsCount;


    public User_Response_Adapter(Context applicationContext, ArrayList<String> user_response_list) {

        this.user_response_list = user_response_list;
        this.context = applicationContext;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {

        ImageView profile_detail_img,delete_img;
        TextView user_response_name, txt_description, description_detail,approved_txt,rejected_txt,week_days;


        public ItemViewHolder(View itemView) {
            super(itemView);
            user_response_name = itemView.findViewById(R.id.user_response_name);
            txt_description = itemView.findViewById(R.id.txt_description);
            description_detail = itemView.findViewById(R.id.description_detail);
            approved_txt=itemView.findViewById(R.id.approved_txt);
            rejected_txt=itemView.findViewById(R.id.rejected_txt);
            week_days=itemView.findViewById(R.id.week_days);



            profile_detail_img=itemView.findViewById(R.id.profile_detail_img);
            delete_img=itemView.findViewById(R.id.delete_img);



            Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Light.ttf");
            Typeface custom_regular = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Regular.ttf");


            txt_description.setTypeface(custom_regular);
            user_response_name.setTypeface(custom_regular);
            approved_txt.setTypeface(custom_regular);
            rejected_txt.setTypeface(custom_regular);
            week_days.setTypeface(custom_font);



        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(final ItemViewHolder itemViewHolder, final int position) {


    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_user_response, parent, false);
        ItemViewHolder viewHolder = new ItemViewHolder(v);
        return viewHolder;
    }


    @Override
    public int getItemCount() {
        return 10;
    }
}

