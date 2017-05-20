package com.moviescheduler.activity;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
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
import com.moviescheduler.R;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pojo.SpectacleSchedule;

public class NewUserSpectacleActivity extends AppCompatActivity {

    Long idSpectacle;
    String spectacleDescription;
    Spinner dateSpinner;
    Spinner seatsSpinner;
    Button bookButton;
    ArrayAdapter<String> listAdapter;
    ArrayAdapter<Integer> arrayAdapter;
    private static final String TOKEN_NAME = "Authorization";
    private static final String TOKEN_VALUE = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJyenZzOTVAZ21haWwuY29tIiwiZXhwIjoxNDk2MDUxOTE2fQ.HZ4eVINa6Ng4cQPTp2HgttpRKnaZ2D9n4tndAkXd-VSZGvzr2BNiboM1FNMBtW8zc-Gw0InminMT9D1TmABLlw";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user_spectacle);

        /**
        @NOTE1 !!!: using spectacle id you will receive the schedule of the spectacle
        @NOTE2 !!!: check SecondFragment to see how I have send request to server and change url to https://back-end-school-project.herokuapp.com/api/get-spectacle-scheduler/idSpectacle=3
         Using a list view (check fragment 2) add to list every schedule to be able to select a schedule and also an empty seat... I will create a request to receive every empty seat for the schedule selected!

         */if(getIntent().hasExtra("SpectacleId") && getIntent().hasExtra("SpectacleDescription")) {
        idSpectacle = getIntent().getLongExtra("SpectacleId", 0);
            spectacleDescription = getIntent().getStringExtra("SpectacleDescription");
            Toast.makeText(getApplicationContext(),idSpectacle+" "+spectacleDescription,Toast.LENGTH_LONG).show();}

       initButton();
        initDesctiption(spectacleDescription);
        onGetSpectacleSchedule(idSpectacle);

    }

    void initDesctiption(String descriptionText){
        TextView descriptionTextView= (TextView) findViewById(R.id.descriptionText);
        descriptionTextView.setText(descriptionText);
    }
    void initButton(){
        bookButton=(Button) findViewById(R.id.bookNowButton);
        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    void initDateSpinner(final List<SpectacleSchedule> spectacleSchedules){
        List<String> list=new ArrayList<String>();
        if(spectacleSchedules.isEmpty()){
            list.add("-No show schduled-");

        }
        else {
            for (SpectacleSchedule spectacle : spectacleSchedules) {
                list.add(getDateAsString(spectacle));
            }
        }


        dateSpinner=(Spinner) findViewById(R.id.dateSpinner);
        listAdapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,list);
        listAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dateSpinner.setAdapter(listAdapter);

        dateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                onGetSpectacleSeats(spectacleSchedules.get(position).getIdSpectacleSchedule());
                setPrice(spectacleSchedules.get(position).getPrice());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //TODO:DateSpinner onItemSelected -> start the fragment


    }
    void initSetsSpinner(Integer[] allEmptySeats){
        if(allEmptySeats.length==0){
            allEmptySeats=new Integer[1];
            allEmptySeats[0]=-1;
            bookButton.setClickable(false);
        }
        else{
            bookButton.setClickable(true);
        }

        seatsSpinner=(Spinner) findViewById(R.id.seatSpinner);
        arrayAdapter=new ArrayAdapter<Integer>(this,android.R.layout.simple_selectable_list_item,allEmptySeats);
       arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        seatsSpinner.setAdapter(arrayAdapter);
    }

    String getDateAsString(SpectacleSchedule spectacleSchedule){
        Long l=Long.parseLong(spectacleSchedule.getSpectacleDate());
        Date date=new Date(l);
        return date.toString();

    }
    void onGetSpectacleSchedule(long id) {

        RequestQueue que = Volley.newRequestQueue(getApplicationContext());
        String url = url="https://back-end-school-project.herokuapp.com/api/get-spectacle-scheduler/idSpectacle=";
        url=url+id;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                    Type listType = new TypeToken<ArrayList<SpectacleSchedule>>() {
                    }.getType();
                /**
                @NOTE 1 - i get an NullPointerException at listType
                 @NOTE 2 - Problem with json parsing Date SOLVED!

                  */
                    List<SpectacleSchedule> spectaclesReceives = new Gson().fromJson(response, listType);
                    initDateSpinner(spectaclesReceives);


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
                headerParams.put(TOKEN_NAME, TOKEN_VALUE);
                return headerParams;
            }
        };
        que.add(stringRequest);
    }
    void onGetSpectacleSeats(long id){
        RequestQueue que = Volley.newRequestQueue(getApplicationContext());
        String url = url="https://back-end-school-project.herokuapp.com/api/get-empty-seats/idSchedule=";
        url=url+id;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Type listType = new TypeToken<Integer[]>() {
                }.getType();
                /**
                 @NOTE 1 - i get an NullPointerException at listType
                 @NOTE 2 - Problem with json parsing Date SOLVED!

                 */
                Integer[] allEmptySeats = new Gson().fromJson(response, listType);
                initSetsSpinner(allEmptySeats);


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
                headerParams.put(TOKEN_NAME, TOKEN_VALUE);
                return headerParams;
            }
        };
        que.add(stringRequest);
    }
    void setPrice(int price){
        TextView priceValue=(TextView) findViewById(R.id.priceValue);
        priceValue.setText(price+"");
    }

}
