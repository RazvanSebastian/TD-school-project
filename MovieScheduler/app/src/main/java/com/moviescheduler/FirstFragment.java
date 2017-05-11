package com.moviescheduler;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import pojo.ModelTest;


/**
 * Created by Parautiu on 11.05.2017.
 */

public class FirstFragment extends Fragment {

    View myView;
    Button buttonTest;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.first_layout, container, false);
        //initialize the button
        this.buttonTest = (Button) myView.findViewById(R.id.buttonGetTest);
        //call the method
        this.onClickGetTest();
        return myView;
    }

    private void onClickGetTest() {
        this.buttonTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    sendGet();
                }
                catch(Exception e){}

            }
        });
    }

    // HTTP GET request
    private void sendGet() throws Exception {
        final TextView textView = (TextView) getActivity().findViewById(R.id.textView2);
        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        String url = "https://java-spring-boot.herokuapp.com/get";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                ModelTest model=gson.fromJson(response,ModelTest.class);
                textView.setText("Response as JSON :" + response +'\n'
                        +"Response parsed from JSON : "+model.getId()+" "+model.getName());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText("Did no work!");
            }
        }
        );
        queue.add(stringRequest);

    }
}




