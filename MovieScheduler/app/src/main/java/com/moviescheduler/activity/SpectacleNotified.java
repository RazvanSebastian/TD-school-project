package com.moviescheduler.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.moviescheduler.R;

public class SpectacleNotified extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spectacle_notified);

        Intent intent = getIntent();
        if(intent.hasExtra("Details")){
            TextView t=(TextView)findViewById(R.id.textView9);
            t.setText(intent.getStringExtra("Details"));
        }
    }
}
