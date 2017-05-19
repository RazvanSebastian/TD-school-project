package com.moviescheduler;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Intent;
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
import com.moviescheduler.activity.MainActivity;
import com.moviescheduler.service.InternetConnection;
import com.moviescheduler.service.OnetimeAlarmReceiver;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pojo.TicketScheduleSpectacle;

/**
 * Created by Parautiu on 19.05.2017.
 */

public class MovieSchedulerApp extends Application{

    private  static Calendar calendar;

    public MovieSchedulerApp() {
        super();
        calendar=Calendar.getInstance();
        calendar.set(Calendar.AM_PM,Calendar.AM);
        calendar.set(Calendar.HOUR_OF_DAY,8);
        calendar.set(Calendar.MINUTE,0);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        getAllTodaySpectacles();
    }

    private void setAlarm(TicketScheduleSpectacle ticketScheduleSpectacle){

        Intent intent1 = new Intent(this, OnetimeAlarmReceiver.class);

        intent1.putExtra("Name",ticketScheduleSpectacle.getSpectacleName());
        intent1.putExtra("Date",new Date(ticketScheduleSpectacle.getSpectacleDate()).toString());
        intent1.putExtra("Details",ticketScheduleSpectacle.toString());
        intent1.putExtra("ID",ticketScheduleSpectacle.getIdTicket());

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,ticketScheduleSpectacle.getIdTicket(),intent1, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager)this.getSystemService(this.ALARM_SERVICE);
        am.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
    }

    private void getAllTodaySpectacles() {

        if(InternetConnection.isInternetAvailable(getApplicationContext())==false){
            Toast.makeText(getApplicationContext(), "No internet connection!", Toast.LENGTH_LONG).show();
            return;
        }


        RequestQueue que = Volley.newRequestQueue(getApplicationContext());
        String url = "https://back-end-school-project.herokuapp.com/api/get-spectacles/today/user="+1;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Type listType = new TypeToken<ArrayList<TicketScheduleSpectacle>>() {}.getType();
                List<TicketScheduleSpectacle> list = new Gson().fromJson(response, listType);
                for(TicketScheduleSpectacle ticket:list){
                    setAlarm(ticket);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast toast = Toast.makeText(getApplicationContext(),error.getStackTrace()+"", Toast.LENGTH_LONG);
                toast.show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headerParams = new HashMap<String, String>();
                // headerParams.put(TOKEN_NAME, Token.getToken(getContext()));
                headerParams.put("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJyenZzOTVAZ21haWwuY29tIiwiZXhwIjoxNDk2MzMzODE1fQ.jlm43AgW2zWip3AQ98g_11fhsHCrTwZ_0gj1FOsIyZy_Qsuti8Fvx4z-yWALfpkujYrXV6LpRF-Dy2P0ZP59lA");
                return headerParams;
            }
        };
        que.add(stringRequest);
    }
}
