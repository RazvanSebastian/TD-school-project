package com.moviescheduler;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;
import pojo.UserDetails;


/**
 * Created by Parautiu on 11.05.2017.
 */

public class FirstFragment extends Fragment {

    private static final String TOKEN_NAME="Authorization";
    private static final String USER_DETAILS="User-Details";

    View myView;
    Button buttonLogin;
    EditText emailText;
    EditText passwordText;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.first_layout, container, false);
        //initialize elements
        this.buttonLogin = (Button) myView.findViewById(R.id.buttonLogin);
        this.emailText = (EditText) myView.findViewById(R.id.editTextEmail);
        this.passwordText = (EditText) myView.findViewById(R.id.editTextPassword);
        //call the method
        this.onClickLogin();
        return myView;
    }

    private void onClickLogin() {
        this.buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    sendLoginPost();
                } catch (Exception e) {
                }

            }
        });
    }

    // HTTP LOGIN POST request
    private void sendLoginPost() throws Exception {

        if (emailText.getText().length()!=0&& emailText.getText().length()!=0) {

            String url = "https://back-end-school-project.herokuapp.com/login";

            AsyncHttpClient client = new AsyncHttpClient();

            JSONObject jsonParams = new JSONObject();
            jsonParams.put("userEmail", emailText.getText());
            jsonParams.put("password", passwordText.getText());

            StringEntity entity = new StringEntity(jsonParams.toString());

            client.post(myView.getContext(), url, entity, "application/json", new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    String authToken=null;
                    UserDetails userDetails=null;
                    for (Header h : headers) {
                        if(h.getName().equals(TOKEN_NAME))
                            authToken=h.getValue();
                        if(h.getName().equals(USER_DETAILS))
                            userDetails=new Gson().fromJson(h.getValue(), UserDetails.class);
                    }
                    Toast.makeText(myView.getContext(), authToken+" "+userDetails.toString(), Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    Toast.makeText(myView.getContext(), "Bad credentials!", Toast.LENGTH_LONG).show();
                }
            });
        }
        else
            Toast.makeText(myView.getContext(),"Empty inputs!", Toast.LENGTH_LONG).show();

    }

}




