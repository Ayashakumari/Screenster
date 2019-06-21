package com.screenster.sosts.Adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.screenster.sosts.R;

import java.util.ArrayList;

/**
 * Created by USER on 8/20/2018.
 */

public class UserEntriesAdapter extends RecyclerView.Adapter<UserEntriesAdapter.ItemViewHolder> {

    public LayoutInflater inflater;
    Context context;

    ArrayList<String> UserEntries_list=new ArrayList<>();

    public UserEntriesAdapter(Context applicationContext,    ArrayList<String> UserEntries_list) {

        this.UserEntries_list = UserEntries_list;
        this.context = applicationContext;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView txt_status_user,badge_txt, profile_name, description_detail, awaiting_txt, time_status, status_txt, leads_email, members_months, retail_membership, free_txt, txt_price, txt_days, members_store;
        RelativeLayout relativeLayout;
        ImageView icon_download_cloud;

        public ItemViewHolder(View itemView) {
            super(itemView);

            txt_status_user=itemView.findViewById(R.id.txt_status_user);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relative_list);
            badge_txt=itemView.findViewById(R.id.badge_txt);
            icon_download_cloud=itemView.findViewById(R.id.icon_download_cloud);



        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(final ItemViewHolder itemViewHolder, final int position) {
        itemViewHolder.txt_status_user.setText(UserEntries_list.get(position));

        if(UserEntries_list.get(position).equals("Approved"))
        {
            itemViewHolder.txt_status_user.setTextColor(context.getResources().getColor(R.color.green));
            //itemViewHolder.badge_txt.setBackground(context.getDrawable(R.drawable.circle_green));
            itemViewHolder.badge_txt.setBackground(context.getResources().getDrawable(R.drawable.circle_green));
            itemViewHolder.icon_download_cloud.setVisibility(View.VISIBLE);
        }
        else if (UserEntries_list.get(position).equals("Reject")){
            itemViewHolder.txt_status_user.setTextColor(context.getResources().getColor(R.color.red));
            itemViewHolder.badge_txt.setBackground(context.getResources().getDrawable(R.drawable.red_circle));
            itemViewHolder.icon_download_cloud.setVisibility(View.GONE);
        }
        else if (UserEntries_list.get(position).equals("Awaiting for approval")){
            itemViewHolder.txt_status_user.setTextColor(context.getResources().getColor(R.color.darkbrown));
            itemViewHolder.badge_txt.setBackground(context.getResources().getDrawable(R.drawable.circle_golden_brown));
            itemViewHolder.icon_download_cloud.setVisibility(View.GONE);
        }

    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_entries, parent, false);
        ItemViewHolder viewHolder = new ItemViewHolder(v);
        return viewHolder;
    }


    @Override
    public int getItemCount() {
        return UserEntries_list.size();
    }
}

