package com.moviescheduler.fragment;

import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ListView;
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
import com.moviescheduler.R;
import com.moviescheduler.activity.NewUserSpectacleActivity;
import com.moviescheduler.service.InternetConnection;
import com.moviescheduler.service.Token;

import pojo.Spectacle;
import popup.PopUp;

/**
 * Created by Parautiu on 11.05.2017.
 */

public class SecondFragment extends Fragment{

    View myView;
    ListView spectacleListView;
    ArrayAdapter<Spectacle> listAdapter;

    Context context;

    private static final String TOKEN_NAME = "Authorization";
    private static final String TOKEN_VALUE = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJyenZzOTVAZ21haWwuY29tIiwiZXhwIjoxNDk2MDUxOTE2fQ.HZ4eVINa6Ng4cQPTp2HgttpRKnaZ2D9n4tndAkXd-VSZGvzr2BNiboM1FNMBtW8zc-Gw0InminMT9D1TmABLlw";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.second_layout, container, false);

        onGetSpectacle();
        context=getContext();
        /**
         * @NOTE: Must instantiate again... Don't know why...
         */
        spectacleListView = (ListView) myView.findViewById(R.id.spectacleListView);
        while (spectacleListView == null) {
        }
        spectacleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(Token.hasToaken(getContext()))
               openNewPopOnSelectSpectacle("Add new spectacle", "Would you like to select a schedule for this spectacle?", listAdapter.getItem(position));
                else{
                    Toast.makeText(context,"Please Login",Toast.LENGTH_LONG).show();
                }
            }
        });

        return myView;
    }

    private void initList(List<Spectacle> spectaclesReceives){
        spectacleListView=(ListView)myView.findViewById(R.id.spectacleListView);
        listAdapter=new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,spectaclesReceives);
        spectacleListView.setAdapter(listAdapter);
    }

    void onGetSpectacle() {

        if(InternetConnection.isInternetAvailable(getContext())==false){
            Toast.makeText(myView.getContext(), "No internet connection!", Toast.LENGTH_LONG).show();
            return;
        }

        RequestQueue que = Volley.newRequestQueue(getActivity().getApplicationContext());
        String url = "https://back-end-school-project.herokuapp.com/api/get-all-spectacles";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Type listType = new TypeToken<ArrayList<Spectacle>>() {}.getType();
                List<Spectacle> spectaclesReceives = new Gson().fromJson(response, listType);
                initList(spectaclesReceives);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast toast = Toast.makeText(myView.getContext(),error.getStackTrace()+"", Toast.LENGTH_LONG);
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


    public void openNewPopOnSelectSpectacle(String title, String message, final Spectacle spectacleToPass) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);
        // set title
        alertDialogBuilder.setTitle(title);

        // set dialog message
        alertDialogBuilder
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        Intent intent = new Intent(getActivity().getApplicationContext(),NewUserSpectacleActivity.class);
                       intent.putExtra("SpectacleDescription",spectacleToPass.toString());
                        intent.putExtra("SpectacleId",spectacleToPass.getIdSpectacle());
                        try {
                            context.startActivity(intent);
                        }catch (Exception e){Toast.makeText(context,e.getMessage()+"",Toast.LENGTH_LONG).show();}
                    }
                })
                .setNegativeButton("No",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();

    }
}

