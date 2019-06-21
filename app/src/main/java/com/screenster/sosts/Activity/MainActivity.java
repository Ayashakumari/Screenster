package com.screenster.sosts.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.screenster.sosts.Adapter.NavigationAdapter;
import com.screenster.sosts.Fragment.AboutApp_Fragment;
import com.screenster.sosts.Fragment.BusinessNotificationFragment;
import com.screenster.sosts.Fragment.ContactUs;
import com.screenster.sosts.Fragment.ListViewFragment;
import com.screenster.sosts.Fragment.MapView;
import com.screenster.sosts.Fragment.My_Assignments_Fragment;
import com.screenster.sosts.Fragment.My_Compaigns;
import com.screenster.sosts.Fragment.NotificationFragment;
import com.screenster.sosts.Fragment.SettingFragment;
import com.screenster.sosts.Fragment.Stats;
import com.screenster.sosts.Fragment.TermsOfUseFragment;
import com.screenster.sosts.R;
import com.screenster.sosts.Util.GPSTracker;
import com.screenster.sosts.Util.SaveSharedPrefernces;
import com.screenster.sosts.Util.helper;

/**
 * Created by USER on 4/3/2018.
 */

public class MainActivity extends BaseActivity {
    ImageView tab_list_img, tab_map_img, ivlistview, ivfilter, prf_image;

    ConstraintLayout home, Assigments, notification, setting;
    static public LinearLayout toolbar_tab;
    TextView header_txt, user_name;
    Fragment fragment = null;

    private static boolean isAddFrag = false;
    int flag = 0;
    ImageView header_back, menu, logo, header_search;
    public static ImageView header_plus;

    public Toolbar mToolbar;
    public static FrameLayout frameLayout;

    public static Boolean bool = true;

    RelativeLayout rel_click;


    private ProgressDialog progress_dialog;
    private final int SHOW_PROG_DIALOG = 0, HIDE_PROG_DIALOG = 1, LOAD_QUESTION_SUCCESS = 2;
    private String progress_dialog_msg = "", msg, tag_string_req = "string_req";


    private String[] Customer_NavigationDrawerItemTitles;
    private TypedArray customerNavigationDrawerItemImages;


    public DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private ViewGroup header;
    private ViewGroup footer;
    NavigationAdapter adapter;
    Toolbar toolbar;
    RelativeLayout logout;
    RelativeLayout side_menu, containtLayout;
    SaveSharedPrefernces ssp;
    GPSTracker gpsTracker;
    public static RelativeLayout bottom_toolbar;
    public static RelativeLayout constraintLayout_post_toolbar;
    Intent flag_intent;
    TextView txt_chatcount, badge_txt;

    @Override
    protected void onCreate(Bundle savedInstancestate) {
        super.onCreate(savedInstancestate);
        setContentView(R.layout.activity_main);

        ssp = new SaveSharedPrefernces();


        flag_intent = getIntent();
        flag = flag_intent.getIntExtra("flag", 0);

        if (ssp.getKEY_User_type(MainActivity.this).equals("user_account")) {

            Customer_NavigationDrawerItemTitles = getResources().getStringArray(R.array.Customer_navigation_drawer_items_array);
        }
        else {
            Customer_NavigationDrawerItemTitles = getResources().getStringArray(R.array.Business_navigation_drawer_items_array);
        }
        containtLayout = (RelativeLayout) findViewById(R.id.containt_layout);

        constraintLayout_post_toolbar = (RelativeLayout) findViewById(R.id.constraintLayout_post_toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        side_menu = (RelativeLayout) findViewById(R.id.main_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        frameLayout = (FrameLayout) findViewById(R.id.content_frame);
        //  ivhome = (ImageView) findViewById(R.id.home_img);
        frameLayout = (FrameLayout) findViewById(R.id.content_frame);

        tab_list_img = findViewById(R.id.tab_list_img);
        tab_map_img = findViewById(R.id.tab_map_img);
        header_plus=findViewById(R.id.header_plus);




        bottom_toolbar = (RelativeLayout) findViewById(R.id.bottom_toolbar);
        //   ivAssigments = (ImageView) findViewById(R.id.Assigments_img);
        //  ivnotification = (ImageView) findViewById(R.id.notification_img);
        //  ivsetting = (ImageView) findViewById(R.id.setting_img);
        home = (ConstraintLayout) findViewById(R.id.toolbar_home);
        Assigments = (ConstraintLayout) findViewById(R.id.toolbar_Assigments);
        notification = (ConstraintLayout) findViewById(R.id.toolbar_notification);
        setting = (ConstraintLayout) findViewById(R.id.toolbar_setting);
        header_txt = (TextView) findViewById(R.id.header_txt);
        toolbar_tab = (LinearLayout) findViewById(R.id.linear_main);
        header_back = (ImageView) findViewById(R.id.header_back);
        menu = (ImageView) findViewById(R.id.header_menu);
        header_search = (ImageView) findViewById(R.id.header_search);
        header_back.setVisibility(View.GONE);
        menu.setVisibility(View.VISIBLE);
//        logo.setVisibility(View.VISIBLE);
        header_txt.setVisibility(View.GONE);

        adapter = new NavigationAdapter(MainActivity.this, Customer_NavigationDrawerItemTitles, customerNavigationDrawerItemImages);
        LayoutInflater inflater1 = getLayoutInflater();
        header = (ViewGroup) inflater1.inflate(R.layout.ui_header_itemcustomer, mDrawerList, false);
        header.setFocusable(true);

        footer = (ViewGroup) inflater1.inflate(R.layout.ui_footer_item, mDrawerList, false);
        mDrawerList.addHeaderView(header, null, false);
        mDrawerList.addFooterView(footer, null, false);
        mDrawerList.setAdapter(adapter);

        View view = (View) findViewById(R.id.drawer_layout);
        setupUI(view);

        tab_map_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.post_location = "";
                frameLayout.setVisibility(View.VISIBLE);
                header_search.setVisibility(View.VISIBLE);
                tab_list_img.setImageResource(R.drawable.tab_list_2x);
                tab_map_img.setImageResource(R.drawable.tab_map_active_2x);

                fragment = new MapView();
                if (fragment != null) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

                } else {
                    Log.e("MainActivity", "Error in creating fragment");
                }
            }
        });


        tab_list_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.post_location = "";

                header_search.setVisibility(View.VISIBLE);
                //map.setBackgroundColor(getResources().getColor(R.color.white));
                tab_list_img.setImageResource(R.drawable.tab_list_active_2x);
                tab_map_img.setImageResource(R.drawable.tab_map_2x);
                fragment = new ListViewFragment();
                if (fragment != null) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
                } else {
                    Log.e("MainActivity", "Error in creating fragment");
                }
            }
        });
        if (flag == 1) {
            header_back.setVisibility(View.GONE);
            menu.setVisibility(View.VISIBLE);
//                logo.setVisibility(View.VISIBLE);
            header_txt.setVisibility(View.VISIBLE);
            header_search.setVisibility(View.VISIBLE);
            home.setBackgroundResource(R.mipmap.nav_home_active);
            Assigments.setBackgroundResource(R.mipmap.nav_assignment);
            notification.setBackgroundResource(R.mipmap.nav_notification);
            setting.setBackgroundResource(R.mipmap.nav_setting);

            if (ssp.getKEY_User_type(MainActivity.this).equals("user_account")) {
                header_txt.setText("Open Opportunities");
                frameLayout.setVisibility(View.VISIBLE);
                // frameLayout.setVisibility(View.GONE);
                //fragment = null;
                fragment = new MapView();
                toolbar_tab.setVisibility(View.VISIBLE);
                tab_list_img.setImageResource(R.drawable.tab_list_2x);
                tab_map_img.setImageResource(R.drawable.tab_map_active_2x);
            } else {
                header_txt.setText("Stats");
                fragment = new Stats();
                toolbar_tab.setVisibility(View.GONE);
                header_search.setVisibility(View.GONE);
            }


            if (fragment != null) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();


            } else {
                Log.e("MainActivity", "Error in creating fragment");
            }

        }
        else if (flag == 12) {
            fragment = new My_Compaigns();
            Log.d("12", "ffffffff");
            header_back.setVisibility(View.GONE);
            menu.setVisibility(View.VISIBLE);
//                logo.setVisibility(View.GONE);
            header_txt.setVisibility(View.VISIBLE);
            header_search.setVisibility(View.GONE);
            home.setBackgroundResource(R.mipmap.nav_home);
            Assigments.setBackgroundResource(R.mipmap.nav_assignment_active);
            notification.setBackgroundResource(R.mipmap.nav_notification);
            setting.setBackgroundResource(R.mipmap.nav_setting);
            frameLayout.setVisibility(View.VISIBLE);
            header_plus.setVisibility(View.VISIBLE);
            header_txt.setText("My Campaigns");
            toolbar_tab.setVisibility(View.GONE);
            if (fragment != null) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
//


            } else {
                Log.e("MainActivity", "Error in creating fragment");
            }
        } else if (flag == 5) {
            Log.d("5", "hhhhhh");
            header_back.setVisibility(View.GONE);
            menu.setVisibility(View.VISIBLE);
//                logo.setVisibility(View.GONE);
            header_txt.setVisibility(View.VISIBLE);
            header_search.setVisibility(View.GONE);
            home.setBackgroundResource(R.mipmap.nav_home);
            Assigments.setBackgroundResource(R.mipmap.nav_assignment_active);
            notification.setBackgroundResource(R.mipmap.nav_notification);
            setting.setBackgroundResource(R.mipmap.nav_setting);
            frameLayout.setVisibility(View.VISIBLE);
            fragment = new My_Assignments_Fragment();
            header_txt.setText("My Assignment");
            toolbar_tab.setVisibility(View.GONE);
            header_plus.setVisibility(View.GONE);
            if (fragment != null) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
//


            } else {
                Log.e("MainActivity", "Error in creating fragment");
            }
        }
        else if(flag==13)
        {
            header_back.setVisibility(View.GONE);
            menu.setVisibility(View.VISIBLE);
//                logo.setVisibility(View.VISIBLE);
            header_txt.setVisibility(View.VISIBLE);
            header_search.setVisibility(View.VISIBLE);
            home.setBackgroundResource(R.mipmap.nav_home_active);
            Assigments.setBackgroundResource(R.mipmap.nav_assignment);
            notification.setBackgroundResource(R.mipmap.nav_notification);
            setting.setBackgroundResource(R.mipmap.nav_setting);
            header_txt.setText("Stats");
            fragment = new Stats();
            toolbar_tab.setVisibility(View.GONE);
            header_search.setVisibility(View.GONE);
            header_plus.setVisibility(View.GONE);
            if (fragment != null) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
//


            } else {
                Log.e("MainActivity", "Error in creating fragment");
            }
        }

        else {
            Log.d("1111111111","222222222222");
            selectItem(1);


        }

        mDrawerList.setItemsCanFocus(false);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        setupDrawerToggle();


        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.app_name, R.string.app_name) {
            public void onDrawerClosed(View view) {
                supportInvalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                supportInvalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
              mDrawerList.setSelection(0);
                super.onDrawerSlide(drawerView, slideOffset);
                containtLayout.setTranslationX(slideOffset * drawerView.getWidth());
                mDrawerLayout.bringChildToFront(drawerView);
                mDrawerLayout.requestLayout();
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);


        rel_click = (RelativeLayout) header.findViewById(R.id.rel_click);
        user_name = (TextView) header.findViewById(R.id.u_name);


        prf_image = (ImageView) header.findViewById(R.id.app_logo);


        logout = (RelativeLayout) footer.findViewById(R.id.rel_logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alert;
                if (Build.VERSION.SDK_INT >= 11) {
                    alert = new AlertDialog.Builder(MainActivity.this, AlertDialog.THEME_HOLO_LIGHT);
                } else {
                    alert = new AlertDialog.Builder(MainActivity.this);
                }
                alert.setTitle("Alert!");
                alert.setMessage("Do you want to logout?");


                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent i = new Intent(MainActivity.this, WelcomeActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(i);
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



        header_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bool) {
                    if (helper.freg_status==1) {
                        MapView.search_layout.setVisibility(View.VISIBLE);
                    }
                    else {
                        ListViewFragment.search_layout_view.setVisibility(View.VISIBLE);
                    }
                    toolbar_tab.setVisibility(View.GONE);
                } else {
                    toolbar_tab.setVisibility(View.VISIBLE);
                    if (helper.freg_status==1) {
                        MapView.search_layout.setVisibility(View.GONE);
                    }
                    else {
                        ListViewFragment.search_layout_view.setVisibility(View.GONE);
                    }
                }
                bool=!bool;

            }

        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                header_back.setVisibility(View.GONE);
                menu.setVisibility(View.VISIBLE);
//                logo.setVisibility(View.VISIBLE);
                header_txt.setVisibility(View.VISIBLE);
                header_search.setVisibility(View.VISIBLE);
                home.setBackgroundResource(R.mipmap.nav_home_active);
                Assigments.setBackgroundResource(R.mipmap.nav_assignment);
                notification.setBackgroundResource(R.mipmap.nav_notification);
                setting.setBackgroundResource(R.mipmap.nav_setting);

                if (ssp.getKEY_User_type(MainActivity.this).equals("user_account")) {
                    header_txt.setText("Open Opportunities");
                    frameLayout.setVisibility(View.VISIBLE);
                    header_plus.setVisibility(View.GONE);
                    // frameLayout.setVisibility(View.GONE);
                    //fragment = null;
                    fragment = new MapView();
                    toolbar_tab.setVisibility(View.VISIBLE);
                    tab_list_img.setImageResource(R.drawable.tab_list_2x);
                    tab_map_img.setImageResource(R.drawable.tab_map_active_2x);
                } else {
                    header_txt.setText("Stats");
                    fragment = new Stats();
                    toolbar_tab.setVisibility(View.GONE);
                    header_search.setVisibility(View.GONE);
                    header_plus.setVisibility(View.GONE);
                }


                if (fragment != null) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();


                } else {
                    Log.e("MainActivity", "Error in creating fragment");
                }
            }
        });


        Assigments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                header_back.setVisibility(View.GONE);
                menu.setVisibility(View.VISIBLE);
//                logo.setVisibility(View.GONE);
                header_txt.setVisibility(View.VISIBLE);
                header_search.setVisibility(View.GONE);
                home.setBackgroundResource(R.mipmap.nav_home);
                Assigments.setBackgroundResource(R.mipmap.nav_assignment_active);
                notification.setBackgroundResource(R.mipmap.nav_notification);
                setting.setBackgroundResource(R.mipmap.nav_setting);
                frameLayout.setVisibility(View.VISIBLE);
                if (ssp.getKEY_User_type(MainActivity.this).equals("user_account")) {
                    fragment = new My_Assignments_Fragment();
                    header_txt.setText("My Assignment");
                    toolbar_tab.setVisibility(View.GONE);
                    header_plus.setVisibility(View.GONE);
                } else {
                    fragment = new My_Compaigns();
                    header_txt.setText("My Campaigns");
                    toolbar_tab.setVisibility(View.GONE);
                    header_search.setVisibility(View.GONE);
                    header_plus.setVisibility(View.VISIBLE);
                }

                if (fragment != null) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();


                }


            }
        });


        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.post_location = "";
                header_back.setVisibility(View.GONE);
                menu.setVisibility(View.VISIBLE);
//                logo.setVisibility(View.GONE);
                header_txt.setVisibility(View.VISIBLE);
                header_search.setVisibility(View.GONE);
                home.setBackgroundResource(R.mipmap.nav_home);
                Assigments.setBackgroundResource(R.mipmap.nav_assignment);
                notification.setBackgroundResource(R.mipmap.nav_notification_active);
                setting.setBackgroundResource(R.mipmap.nav_setting);
                frameLayout.setVisibility(View.VISIBLE);
                if(ssp.getKEY_User_type(MainActivity.this).equals("user_account")) {
                    fragment = new NotificationFragment();
                    header_txt.setText("Notifications");
                    toolbar_tab.setVisibility(View.GONE);
                    header_plus.setVisibility(View.GONE);
                }
                else
                {
                    fragment = new BusinessNotificationFragment();
                    header_txt.setText("Notifications");
                    toolbar_tab.setVisibility(View.GONE);
                    header_plus.setVisibility(View.GONE);
                }
                if (fragment != null) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();


                } else {
                    Log.e("MainActivity", "Error in creating fragment");
                }

            }
        });

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.post_location = "";
                header_back.setVisibility(View.GONE);
                menu.setVisibility(View.VISIBLE);
//                logo.setVisibility(View.GONE);
                header_txt.setVisibility(View.VISIBLE);
                header_search.setVisibility(View.GONE);
                home.setBackgroundResource(R.mipmap.nav_home);
                Assigments.setBackgroundResource(R.mipmap.nav_assignment);
                notification.setBackgroundResource(R.mipmap.nav_notification);
                setting.setBackgroundResource(R.mipmap.nav_setting_active);
                frameLayout.setVisibility(View.VISIBLE);
                fragment = new SettingFragment();
                header_txt.setText("Account Setting");
                header_plus.setVisibility(View.GONE);
                toolbar_tab.setVisibility(View.GONE);

                if (fragment != null) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();


                }

            }
        });


    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
            if (position > 0) {
//                selectItem(position - 1);
            } else {
                selectItem(position);
            }
        }
    }

    private void selectItem(int position) {
        Fragment fragment = null;
        mDrawerList.setFocusable(false);
        header.requestFocus();
        helper.post_location = "";
        switch (position) {
            case 1:
                header_back.setVisibility(View.GONE);
                menu.setVisibility(View.VISIBLE);
//                logo.setVisibility(View.VISIBLE);
               // header_txt.setVisibility(View.VISIBLE);
                home.setBackgroundResource(R.mipmap.nav_home_active);
                Assigments.setBackgroundResource(R.mipmap.nav_assignment);
                notification.setBackgroundResource(R.mipmap.nav_notification);
                setting.setBackgroundResource(R.mipmap.nav_setting);
                bottom_toolbar.setVisibility(View.VISIBLE);
                frameLayout.setVisibility(View.VISIBLE);
                if (ssp.getKEY_User_type(MainActivity.this).equals("user_account")) {
                    header_txt.setText("Open Opportunities");
                    fragment = new MapView();
                    header_txt.setVisibility(View.VISIBLE);
                    toolbar_tab.setVisibility(View.VISIBLE);
                    header_search.setVisibility(View.VISIBLE);
                    header_plus.setVisibility(View.GONE);
                } else {
                    header_txt.setText("Stats");
                    fragment = new Stats();
                    header_txt.setVisibility(View.VISIBLE);
                    toolbar_tab.setVisibility(View.GONE);
                    header_search.setVisibility(View.GONE);
                    header_plus.setVisibility(View.GONE);
                }
                break;


            case 2:

                if (ssp.getKEY_User_type(MainActivity.this).equals("user_account")) {
                    Log.d("user_typ3", ssp.getKEY_User_type(MainActivity.this));
                    header_back.setVisibility(View.GONE);
                    menu.setVisibility(View.VISIBLE);
//                logo.setVisibility(View.GONE);
                    header_txt.setVisibility(View.VISIBLE);
                    header_search.setVisibility(View.GONE);
                    home.setBackgroundResource(R.mipmap.nav_home);
                    Assigments.setBackgroundResource(R.mipmap.nav_assignment_active);
                    notification.setBackgroundResource(R.mipmap.nav_notification);
                    setting.setBackgroundResource(R.mipmap.nav_setting);
                    frameLayout.setVisibility(View.VISIBLE);
                    toolbar_tab.setVisibility(View.GONE);
                    bottom_toolbar.setVisibility(View.VISIBLE);
                    header_plus.setVisibility(View.GONE);
                    fragment = new My_Assignments_Fragment();

                } else {
                    Log.d("user_typ4", ssp.getKEY_User_type(MainActivity.this));
                    header_back.setVisibility(View.GONE);
                    menu.setVisibility(View.VISIBLE);
//                logo.setVisibility(View.GONE);
                    header_txt.setVisibility(View.VISIBLE);
                    header_search.setVisibility(View.GONE);
                    home.setBackgroundResource(R.mipmap.nav_home);
                    Assigments.setBackgroundResource(R.mipmap.nav_assignment_active);
                    notification.setBackgroundResource(R.mipmap.nav_notification);
                    setting.setBackgroundResource(R.mipmap.nav_setting);
                    frameLayout.setVisibility(View.VISIBLE);
                    toolbar_tab.setVisibility(View.GONE);
                    bottom_toolbar.setVisibility(View.VISIBLE);
                    header_plus.setVisibility(View.VISIBLE);
                    fragment = new My_Compaigns();
                }
                break;

            case 3:
                header_back.setVisibility(View.GONE);
                menu.setVisibility(View.VISIBLE);
//                logo.setVisibility(View.VISIBLE);
                // header_txt.setVisibility(View.VISIBLE);
                home.setBackgroundResource(R.mipmap.nav_home);
                Assigments.setBackgroundResource(R.mipmap.nav_assignment);
                notification.setBackgroundResource(R.mipmap.nav_notification_active);
                setting.setBackgroundResource(R.mipmap.nav_setting);
                bottom_toolbar.setVisibility(View.VISIBLE);
                frameLayout.setVisibility(View.VISIBLE);
                if (ssp.getKEY_User_type(MainActivity.this).equals("user_account")) {
                    header_txt.setText("Notifications");
                    fragment = new NotificationFragment();
                    header_txt.setVisibility(View.VISIBLE);
                    toolbar_tab.setVisibility(View.GONE);
                    header_search.setVisibility(View.GONE);
                    header_plus.setVisibility(View.GONE);
                } else {
                    Intent i = new Intent(MainActivity.this, Deposite_Activivty.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(i);
                }
                break;





            case 4:
            header_back.setVisibility(View.GONE);
                menu.setVisibility(View.VISIBLE);
//                logo.setVisibility(View.GONE);
                header_txt.setVisibility(View.VISIBLE);
                header_txt.setText("Account Setting");
                header_search.setVisibility(View.GONE);
                home.setBackgroundResource(R.mipmap.nav_home);
                Assigments.setBackgroundResource(R.mipmap.nav_assignment);
                notification.setBackgroundResource(R.mipmap.nav_notification);
                setting.setBackgroundResource(R.mipmap.nav_setting_active);
                frameLayout.setVisibility(View.VISIBLE);
                toolbar_tab.setVisibility(View.GONE);
                bottom_toolbar.setVisibility(View.VISIBLE);
                header_plus.setVisibility(View.GONE);
                fragment = new SettingFragment();
                break;


            case 5:
                header_back.setVisibility(View.GONE);
                menu.setVisibility(View.VISIBLE);
//                logo.setVisibility(View.GONE);
                header_txt.setVisibility(View.VISIBLE);
                header_search.setVisibility(View.GONE);
                bottom_toolbar.setVisibility(View.GONE);
                toolbar_tab.setVisibility(View.GONE);
                fragment = new AboutApp_Fragment();
                header_plus.setVisibility(View.GONE);

                break;

            case 6:

                header_back.setVisibility(View.GONE);
                menu.setVisibility(View.VISIBLE);
//                logo.setVisibility(View.GONE);
                header_txt.setVisibility(View.VISIBLE);
                header_search.setVisibility(View.GONE);
                toolbar_tab.setVisibility(View.GONE);
                bottom_toolbar.setVisibility(View.GONE);
                header_plus.setVisibility(View.GONE);
                fragment = new TermsOfUseFragment();

                break;

            case 7:

                header_back.setVisibility(View.GONE);
                menu.setVisibility(View.VISIBLE);
//               logo.setVisibility(View.GONE);
                header_txt.setVisibility(View.VISIBLE);
                header_search.setVisibility(View.GONE);
                toolbar_tab.setVisibility(View.GONE);
                bottom_toolbar.setVisibility(View.GONE);
                header_plus.setVisibility(View.GONE);
                fragment = new ContactUs();


            default:
                break;


        }

        replaceFragment(fragment);
        mDrawerList.setItemChecked(position, true);
        mDrawerList.setSelection(position);


        header_txt.setText(Customer_NavigationDrawerItemTitles[position - 1]);


        mDrawerLayout.closeDrawer(side_menu);
    }


    public void replaceFragment(Fragment fmt) {
        if (fmt != null) {
            if (!fmt.isAdded()) {
                isAddFrag = true;
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, fmt)
                        .commit();
            }
        } else {
            Log.e("MainActivity", "Error in creating fragment");
        }

    }

    public void setupDrawerToggle() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.app_name, R.string.app_name);
        mDrawerToggle.setDrawerIndicatorEnabled(false);
        mDrawerToggle.syncState();
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
                    mDrawerLayout.closeDrawer(side_menu);
                } else {
                    mDrawerLayout.openDrawer(side_menu);
                }
            }
        });

    }


    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}

