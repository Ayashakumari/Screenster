package com.screenster.sosts.Util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import static com.screenster.sosts.Util.helper.user_type;


public class SaveSharedPrefernces {

    SharedPreferences sharedPreferences;
    int latlng;
    public static final String PREFS_NAME = "BlackTopApp_Pref";
    public static final String KEY_PREFS = "key_pref";
    public static final String KEY_USERID = "user_id";
    public static final String KEY_FIRST_NAME = "fname";
    public static final String KEY_LAST_NAME = "ms";
    public static final String KEY_EMAIL_ADDRESS = "fm";
    public static final String KEY_CALLING_CODE = "kd";
    public static final String KEY_MOBILE_NO = "mfv";
    public static final String SAVE_MOBILE_NO = "nmbr";
    public static final String KEY_COMBINE_NO = "km";
    public static final String KEY_PASSWORD = "lpl";
    public static final String KEY_PROFILEURL = "n";
    public static final String KEY_ADDDRESS = "add";
    public static final String KEY_PROFILE_DESCRIPTION = "des";
    public static final String KEY_USERTYPE = "utype";
    public static final String KEY_LOGINSTATUS = "lgsta";
    public static final String KEY_ACCOUNT_SATAUS = "scsts";
    public static final String KEY_ACCOUNT_BALANCE = "acc";
    public static final String KEY_REVIEW_RATING = "acra";
    public static final String KEY_REVIEW_COUNT = "ciugyu";
    public static final String KEY_PLAN_NAME = "pkvcd";




    public static final String KEY_User_type = "acct_type";

    public static final String KEY_Already_account = "already_account";

    public static final String KEY_POST_LOCATION = "post_location";
    public static final String KEY_POST_LATITUDE  = "post_latitude";
    public static final String KEY_POST_LONGITUDE = "post_longitude";

    public String getKEY_User_type(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String user_type = sharedPreferences.getString(KEY_User_type, "");
        return user_type;
    }


    public void setKEY_User_type(Context context, String user_type) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        Editor editor = sharedPreferences.edit();
        editor.putString(KEY_User_type, user_type);
        editor.commit();
    }


    public String getKEY_Already_account(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String already_account = sharedPreferences.getString(KEY_Already_account, "");
        return already_account;
    }


    public void setKEY_Already_account(Context context, String already_account) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        Editor editor = sharedPreferences.edit();
        editor.putString(KEY_Already_account, already_account);
        editor.commit();
    }








}