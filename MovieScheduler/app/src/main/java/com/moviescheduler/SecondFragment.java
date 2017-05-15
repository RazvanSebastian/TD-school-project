package com.moviescheduler;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

import pojo.Spectacle;

/**
 * Created by Parautiu on 11.05.2017.
 */

public class SecondFragment extends Fragment{

    View myView;
    Button getSpectacleButton;

    private static final String TOKEN_NAME="Authorization";
    private static final String TOKEN_VALUE="Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJyenZzOTVAZ21haWwuY29tIiwiZXhwIjoxNDk2MDUxOTE2fQ.HZ4eVINa6Ng4cQPTp2HgttpRKnaZ2D9n4tndAkXd-VSZGvzr2BNiboM1FNMBtW8zc-Gw0InminMT9D1TmABLlw";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView=inflater.inflate(R.layout.second_layout,container,false);
        getSpectacleButton=(Button)myView.findViewById(R.id.getSpectacle);
        onGetSpectacle();
        return myView;
    }

    void onGetSpectacle(){
        getSpectacleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue que= Volley.newRequestQueue(getActivity().getApplicationContext());
                String url="https://back-end-school-project.herokuapp.com/api/get-all-spectacles";
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        TextView listView=(TextView)myView.findViewById(R.id.textView2);
//                        Toast toast = Toast.makeText(myView.getContext(),response,Toast.LENGTH_LONG);
//                        toast.show();
                        Type listType = new TypeToken<ArrayList<Spectacle>>(){}.getType();
                        List<Spectacle> spectaclesReceives= new Gson().fromJson(response, listType);
                        String listString="";
                        for(Spectacle s:spectaclesReceives)
                            listString+=s.toString()+'\n';
                        listView.setText(listString);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast toast = Toast.makeText(myView.getContext(),error.toString(),Toast.LENGTH_LONG);
                        toast.show();
                    }
                })
                {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String,String> headerParams = new HashMap<String, String>();
                        headerParams.put(TOKEN_NAME,TOKEN_VALUE);
                        return  headerParams;
                    }
                };
                que.add(stringRequest);
            }
        });
    }
}
