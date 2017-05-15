package com.moviescheduler.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.moviescheduler.R;

public class NewUserSpectacleActivity extends AppCompatActivity {

    Long idSpectacle;
    String spectacleDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user_spectacle);

        /**
        @NOTE1 !!!: using spectacle id you will receive the schedule of the spectacle
        @NOTE2 !!!: check SecondFragment to see how I have send request to server and change url to https://back-end-school-project.herokuapp.com/api/get-spectacle-scheduler/idSpectacle=3
         Using a list view (check fragment 2) add to list every schedule to be able to select a schedule and also an empty seat... I will create a request to receive every empty seat for the schedule selected!

         */
        idSpectacle=getIntent().getLongExtra("SpectacleId",0);
       spectacleDescription=getIntent().getStringExtra("SpectacleDescription");


    }
}
