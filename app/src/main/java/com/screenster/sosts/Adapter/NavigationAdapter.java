package com.screenster.sosts.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.screenster.sosts.R;
import com.screenster.sosts.Util.SaveSharedPrefernces;


/**
 * Created by sys on 10/27/2016.
 */

public class NavigationAdapter extends BaseAdapter {

    private Activity activity;
    private String[] nNamesArrray;
    private TypedArray nImagesArrray;
    private static LayoutInflater inflater = null;
    SaveSharedPrefernces ssp;
    String chatCount="",notification_count="";
    //public ImageLoader imageLoader;

    public NavigationAdapter(Activity a, String[] Names_Array, TypedArray Images_Array) {
        activity = a;
        nNamesArrray = Names_Array;
        nImagesArrray = Images_Array;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ssp=new SaveSharedPrefernces();

        //imageLoader=new ImageLoader(activity.getApplicationContext());
    }

    public int getCount() {
        return nNamesArrray.length;
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (convertView == null)
            vi = inflater.inflate(R.layout.ui_nav_item, null);
        LinearLayout parent_layout=vi.findViewById(R.id.parent_layout);
        RelativeLayout layout_container=vi.findViewById(R.id.layout_container);
        TextView itemname = (TextView) vi.findViewById(R.id.item_name);
        //ImageView itemimage = (ImageView) vi.findViewById(R.id.item_image);
       // View m_viewdiv = (View) vi.findViewById(R.id.divider_view);



if (nNamesArrray[position].equals("Subscribe")){
    parent_layout.setVisibility(View.GONE);
    layout_container.setVisibility(View.GONE);
}
else {
    parent_layout.setVisibility(View.VISIBLE);
    layout_container.setVisibility(View.VISIBLE);
    itemname.setText(nNamesArrray[position]);
}





       // ViewGroup.LayoutParams params = m_viewdiv.getLayoutParams();
        if (position == 2 || position == 5) {
           // params.height = 3;
           /* m_viewdiv.setLayoutParams(params);
            m_viewdiv.setBackgroundColor(activity.getResources().getColor(R.color.white));*/
        } else if (position == 6) {
           // params.height = 1;
           /* m_viewdiv.setLayoutParams(params);
            m_viewdiv.setBackgroundColor(activity.getResources().getColor(R.color.white));*/
        } else {
           // params.height = 1;
           /* m_viewdiv.setBackgroundColor(activity.getResources().getColor(R.color.white));
            m_viewdiv.setLayoutParams(params);*/
        }

        /*if(GlobalMethods.getStringValue(activity, AppConstants.STORED_LANGUAGE_CODE).equalsIgnoreCase("ar"))
        {
            itemname.setGravity(Gravity.RIGHT);
        }else{
            itemname.setGravity(Gravity.LEFT);
        }*/

        return vi;
    }




}
