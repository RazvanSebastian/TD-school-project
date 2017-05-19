package com.moviescheduler.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.moviescheduler.R;
import com.moviescheduler.service.InternetConnection;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;
import android.support.v4.app.*;
/**
 * Created by Parautiu on 11.05.2017.
 */

public class RegisterFragment extends Fragment{

    View myView;
    EditText emailRegister;
    EditText passwordRegister;
    Button signInButton;
    Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView=inflater.inflate(R.layout.register_layout,container,false);
        emailRegister=(EditText)myView.findViewById(R.id.editEmailRegister);
        passwordRegister=(EditText)myView.findViewById(R.id.editPasswordRegister);
        signInButton=(Button)myView.findViewById(R.id.registerButton);
        context=getContext();
        onClickRegister();

        return myView;
    }

    private void onClickRegister(){
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    onHttpPostRegistration();
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

            }
        });
    }


    private void onHttpPostRegistration() throws JSONException, UnsupportedEncodingException {



        if (emailRegister.getText().length() != 0 && passwordRegister.getText().length() != 0) {

            if(InternetConnection.isInternetAvailable(getContext())==false){
                Toast.makeText(myView.getContext(), "No internet connection!", Toast.LENGTH_LONG).show();
                return;
            }

            String url = "https://back-end-school-project.herokuapp.com/api/register";

            AsyncHttpClient client = new AsyncHttpClient();

            JSONObject jsonParams = new JSONObject();

            jsonParams.put("userEmail", emailRegister.getText());
            jsonParams.put("password", passwordRegister.getText());

            StringEntity entity = new StringEntity(jsonParams.toString());

            client.post(myView.getContext(), url, entity, "application/json", new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    Toast.makeText(myView.getContext(), "Success registration!", Toast.LENGTH_LONG).show();
                    try {
                        FragmentManager fm = getFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        FirstFragment loginFragment = new FirstFragment();
                        ft.replace(R.id.content_frame, loginFragment);
                        ft.commit();

                    }catch (Exception e){Toast.makeText(context,e.getMessage(),Toast.LENGTH_LONG).show();}
                }
                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                        String textError = new String(responseBody);
                        Toast.makeText(myView.getContext(), textError, Toast.LENGTH_LONG).show();

                }
            });

        }
    }
}
