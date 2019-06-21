package com.screenster.sosts.Adapter;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.screenster.sosts.R;


import java.util.ArrayList;
import java.util.List;


/**
 * Created by admin on 21-08-2017.
 */
public class PopupAdapter implements GoogleMap.InfoWindowAdapter {
    LayoutInflater inflater = null;
    Context context;

    Geocoder geocoder;
    List<Address> addresses;
    Double latis,longis;
    String longitude,latitude;

    public static final int RequestPermissionCode = 1;

    String no_people,date,time,city,address,comment,address1="",state="",device_id,msg="";
    String split_name,split_last,account_balance;
    ArrayList<String> map_list;

String txt="";
String post_id,other_user_id;

    public PopupAdapter(LayoutInflater inflater, Context context) {
        this.inflater = inflater;
        this.context = context;

    }

    @Override
    public View getInfoWindow(Marker marker) {
        final View popup = inflater.inflate(R.layout.detail_on_map, null);
        TextView tv = (TextView) popup.findViewById(R.id.list_location);
        TextView title_name=(TextView) popup.findViewById(R.id.title_name);
        ImageView frwd_arrow=(ImageView) popup.findViewById(R.id.frwd_arrow);
        RelativeLayout relative_list=(RelativeLayout)popup.findViewById(R.id.relative_list);
        ImageView profile_detail_img=popup.findViewById(R.id.profile_detail_img);
        TextView profile_name=popup.findViewById(R.id.profile_name);
        TextView category_txt=popup.findViewById(R.id.category_txt);
        txt=marker.getTitle();


         LinearLayout container=popup.findViewById(R.id.container_popup);
       /* relative_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, SignPostDetails.class);


                intent.putExtra("signpost_id",post_id);
                intent.putExtra("signpost_postedby",other_user_id);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                context.startActivity(intent);
                Log.d("hbcwbchbc","ewhbdwhbwubcw");

            }
        });
*/

        return (popup);
    }


    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }
}