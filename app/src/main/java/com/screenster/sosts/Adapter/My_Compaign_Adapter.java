package com.screenster.sosts.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.screenster.sosts.Activity.Detail_Response;
import com.screenster.sosts.R;
import java.util.ArrayList;
/**
 * Created by Seemtech on 8/20/2018.
 */
public class My_Compaign_Adapter extends RecyclerView.Adapter<My_Compaign_Adapter.ItemViewHolder> {

    public LayoutInflater inflater;
    Context context;

    ArrayList<Integer> my_compaign_list=new ArrayList<Integer>();

    public My_Compaign_Adapter(Context applicationContext,    ArrayList<Integer> my_compaign_list) {

        this.my_compaign_list = my_compaign_list;
        this.context = applicationContext;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView approved_status,epire_post_txt,description_detail,real_new_txt,submit_txt,expire_date_txt;
        ImageView delete_img,edit_img;
        LinearLayout assinment_linear;

        public ItemViewHolder(View itemView) {
            super(itemView);

                approved_status=itemView.findViewById(R.id.expire_date_txt);
            epire_post_txt=itemView.findViewById(R.id.epire_post_txt);
            description_detail=itemView.findViewById(R.id.description_detail);
            real_new_txt=itemView.findViewById(R.id.real_new_txt);
            submit_txt=itemView.findViewById(R.id.submit_txt);
            expire_date_txt=itemView.findViewById(R.id.expire_date_txt);
            delete_img=itemView.findViewById(R.id.delete_img);
            edit_img=itemView.findViewById(R.id.delete_img);
            assinment_linear=itemView.findViewById(R.id.assinment_linear2);

            Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Light.ttf");
            Typeface custom_regular = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Regular.ttf");

            real_new_txt.setTypeface(custom_regular);
            description_detail.setTypeface(custom_font);
            submit_txt.setTypeface(custom_font);
            expire_date_txt.setTypeface(custom_font);
            approved_status.setTypeface(custom_regular);
            epire_post_txt.setTypeface(custom_regular);


        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(final ItemViewHolder itemViewHolder, final int position) {

        itemViewHolder.assinment_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context, Detail_Response.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                context.startActivity(i);
            }
        });



    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_my_compaigns, parent, false);
        ItemViewHolder viewHolder = new ItemViewHolder(v);
        return viewHolder;
    }


    @Override
    public int getItemCount() {
        return 10;
    }
}

