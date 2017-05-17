package com.moviescheduler.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.moviescheduler.R;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Parautiu on 11.05.2017.
 */

public class ThirdFragment extends Fragment{

    View myView;
    Button goButton;
    Spinner spinner;

    private static final String TOKEN_NAME = "Authorization";
    private static final String TOKEN_VALUE = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJyenZzOTVAZ21haWwuY29tIiwiZXhwIjoxNDk2MDUxOTE2fQ.HZ4eVINa6Ng4cQPTp2HgttpRKnaZ2D9n4tndAkXd-VSZGvzr2BNiboM1FNMBtW8zc-Gw0InminMT9D1TmABLlw";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView=inflater.inflate(R.layout.third_layout,container,false);
        goButton=(Button)myView.findViewById(R.id.button);
    spinner=(Spinner)myView.findViewById(R.id.spinner) ;
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onGetEmptySeats();
            }
        });
        return myView;
    }

    private Integer[] parseToIntegerArray(String string) {
        String[] array = string.replace("[", "").replace("]", "").split(",");
        Integer[] ints = new Integer[array.length];

        for (int i = 0; i < array.length; i++) {
            ints[i] = Integer.parseInt(array[i]);

        }
        return ints;
    }

    void onGetEmptySeats() {
        String url = "https://back-end-school-project.herokuapp.com/api/get-empty-seats/idSchedule=" + 10;

        AsyncHttpClient client = new AsyncHttpClient();

        client.addHeader("Authorization", TOKEN_VALUE);

        client.get(getContext(), url, null, "application/json", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                String arrayAsString = new String(responseBody);

                ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(getContext(),android.R.layout.simple_spinner_item, parseToIntegerArray(arrayAsString));
                spinner.setAdapter(adapter);
                Toast.makeText(getContext(), arrayAsString , Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(getContext(), statusCode + "", Toast.LENGTH_LONG).show();
            }
        });


    }
}
