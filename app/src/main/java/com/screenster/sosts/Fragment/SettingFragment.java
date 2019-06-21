package com.screenster.sosts.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.screenster.sosts.Activity.ChangePassword;
import com.screenster.sosts.Activity.PersonalSetting;
import com.screenster.sosts.R;
import com.screenster.sosts.Util.SaveSharedPrefernces;

;

/**
 * Created by USER on 4/3/2018.
 */

public class SettingFragment extends Fragment {

RelativeLayout personal_info,change_password;
ImageView notification;
String msg="";
ImageView arrow;
SaveSharedPrefernces ssp;
String noti_status;
RelativeLayout rel_manage_user;

Boolean bool=false;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.setting, container, false);


        ssp=new SaveSharedPrefernces();







        personal_info=(RelativeLayout)rootView.findViewById(R.id.rel_personal_info);
        change_password=(RelativeLayout)rootView.findViewById(R.id.rel_change_password);

//        rel_manage_user.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i=new Intent(getActivity(),Block_User_View.class);
//                i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                startActivity(i);
//            }
//        });
//
//        if(ssp.getNotification_status(getActivity()).equals("ON")){
//
//            notification.setImageResource(R.drawable.notification_on);
//            bool=false;
//
//        }
//        else {
//            notification.setImageResource(R.drawable.notification_off);
//            bool=true;
//
//        }
//
//

        personal_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i=new Intent(getActivity(),PersonalSetting.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(i);



            }
        });
        change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(getActivity(),ChangePassword.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);


            }
        });


//        notification.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(bool){
//
//                    notification.setImageResource(R.drawable.notification_on);
//                    Toast.makeText(getActivity(), "Notification : ON", Toast.LENGTH_SHORT).show();
//                    noti_status="ON";
//                   Notification_status();
//                   arrow.setVisibility(View.GONE);
//
//                }
//                else {
//
//                    notification.setImageResource(R.drawable.notification_off);
//                    Toast.makeText(getActivity(), "Notification : OFF", Toast.LENGTH_SHORT).show();
//                    noti_status="OFF";
//                    Notification_status();
//                    arrow.setVisibility(View.VISIBLE);
//
//                }
//                bool=!bool;
//            }
//        });
//
//
//
//

        return rootView;






    }

//    public void Notification_status() {
//
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, Apis.api_url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//
//
////                        mHandler.sendEmptyMessage(HIDE_PROG_DIALOG);
////                        mHandler.sendEmptyMessage(LOAD_QUESTION_SUCCESS);
//                        JSONObject obj = null;
//                        try {
//                            obj = new JSONObject(response.toString());
//                            msg = obj.getString("msg");
//
//                            Log.d("Message", "<<>>" + msg);
//                            Log.d("response", response.toString());
//
//
//                            if(msg.equals("Status updated successfully")){
//
//                                if(obj.has("status")){
//
//                                    String stat=obj.getString("status");
//                                    ssp.setNotification_status(getActivity(),stat);
//
//                                }
//                            }
//
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        } catch (NullPointerException e) {
//                            e.printStackTrace();
//                        } catch (Exception e) {
//                            // TODO Auto-generated catch block
//                            e.printStackTrace();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
////                        mHandler.sendEmptyMessage(HIDE_PROG_DIALOG);
////                        mHandler.sendEmptyMessage(LOAD_QUESTION_SUCCESS);
//                        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
//                            Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
//                            Toast.makeText(getActivity(), getResources().getString(R.string.time_out_error), Toast.LENGTH_LONG).show();
//                        } else if (error instanceof AuthFailureError) {
//                            Toast.makeText(getActivity(), getResources().getString(R.string.auth_error), Toast.LENGTH_LONG).show();
//                            //TODO
//                        } else if (error instanceof ServerError) {
//                            Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
//                            Toast.makeText(getActivity(), getResources().getString(R.string.server_error), Toast.LENGTH_LONG).show();
//                            //TODO
//                        } else if (error instanceof NetworkError) {
//                            Toast.makeText(getActivity(), getResources().getString(R.string.networkError_Message), Toast.LENGTH_LONG).show();
//                            Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
//                            //TODO
//
//                        } else if (error instanceof ParseError) {
//
////							Toast.makeText(StringRequestActivity.this,error.toString(),Toast.LENGTH_LONG).show();
//                            //TODO
//                        }
//
//                    }
//                }) {
//
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("action", "Notificationsetting");
//                params.put("user_id", ssp.getuserid(getActivity()));
//               params.put("status",noti_status);
//
//
//                Log.d("params", params.toString());
//                return params;
//            }
//
//        };
//
//
//        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
//        requestQueue.add(stringRequest);
//        stringRequest.setRetryPolicy(new DefaultRetryPolicy(60 * 1000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//
//
//    }

//    public void onResume() {
//        super.onResume();
//
//        if(ssp.getNotification_status(getActivity()).equals("ON")){
//
//            notification.setImageResource(R.drawable.notification_on);
//            bool=false;
//            arrow.setVisibility(View.GONE);
//
//
//
//        }
//        else {
//            notification.setImageResource(R.drawable.notification_off);
//            bool=true;
//            arrow.setVisibility(View.VISIBLE);
//
//        }
//
//
//
//    }



}

