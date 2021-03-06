package com.moviescheduler.activity;



import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.moviescheduler.R;
import com.moviescheduler.fragment.FirstFragment;
import com.moviescheduler.fragment.RegisterFragment;
import com.moviescheduler.fragment.SecondFragment;
import com.moviescheduler.fragment.ThirdFragment;


import com.moviescheduler.service.OnetimeAlarmReceiver;
import com.moviescheduler.service.Token;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;
import pojo.Spectacle;
import pojo.TicketScheduleSpectacle;

/**
 * http://stacktips.com/tutorials/android/repeat-alarm-example-in-android
 */

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Menu mMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        findViewById(R.id.background).setVisibility(View.VISIBLE);


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                startLoginActivity();
//
//
//                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//                        */
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
       drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        checkLogin(navigationView);

//        Date date=new Date(System.currentTimeMillis()+4000);
//        Date date1=new Date(System.currentTimeMillis()+6000);
//        setAlarm("Title1","Description1",date);
//        setAlarm("Title2","Description2",date1);

        //getAllTodaySpectacles();

    }
    public void checkLogin(NavigationView navigationView){
        boolean hasToken=Token.hasToaken(getApplicationContext());
        if(hasToken) {
            Menu menu = navigationView.getMenu();
            menu.findItem(R.id.nav_first_layout).setVisible(false);
           // menu.findItem(R.id.nav_third_layout).setVisible(true);
            menu.findItem(R.id.logout).setVisible(true);
        }
    }
    public void doLogout(){
        Token.deleteToken(this);
        this.recreate();
    }

    public void startLoginActivity() {
//        Intent intent = new Intent(this, LoginActivity.class);
//
//        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

       getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }



    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        findViewById(R.id.background).setVisibility(View.INVISIBLE);

        /**
         * @NOTE: For a new Fragment java class you must import android.support.v4.app.*; instead the basic package!!!
         */
        FragmentManager fragmentManager = getSupportFragmentManager();

        // fragmentManager.findFragmentById(R.id.nav_first_layout).setUserVisibleHint();
        if (id == R.id.nav_first_layout) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new FirstFragment()).commit();
        } else if (id == R.id.nav_second_layout) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new SecondFragment()).commit();
        }else if (id == R.id.logout){
            doLogout();
        }else if (id == R.id.nav_third_layout) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new ThirdFragment()).commit();
        }else
            if(id==R.id.register_layout){
                fragmentManager.beginTransaction().replace(R.id.content_frame,new RegisterFragment()).commit();


            }
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;

    }



}

