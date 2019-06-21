package com.screenster.sosts.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.screenster.sosts.R;


/**
 * Created by dev on 04-04-2018.
 */

public class List_Adapter extends RecyclerView.Adapter<List_Adapter.ContactViewHolder> {

    Context context;

    public List_Adapter(Context context) {
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    @Override
    public void onBindViewHolder(ContactViewHolder contactViewHolder, int i) {
//        contactViewHolder.cartView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, AcountSetting.class);
//                context.startActivity(intent);
//            }
//        });
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.listview_item, viewGroup, false);

        return new ContactViewHolder(itemView);
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout relativeLayout;

        public ContactViewHolder(View v) {
            super(v);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.list_item_layout);
        }

    }


}
