package com.moviescheduler.service;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import com.moviescheduler.R;
import com.moviescheduler.activity.SpectacleNotified;

public class OnetimeAlarmReceiver extends BroadcastReceiver {

    private static int ID=0;

    @Override
    public void onReceive(Context context, Intent intent) {

        ID++;
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent notificationIntent= new Intent(context,SpectacleNotified.class);

        notificationIntent.putExtra("Details",intent.getStringExtra("Details"));

        PendingIntent resultPendingIntent = PendingIntent.getActivity(context, ID,
                notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder.setSmallIcon(R.drawable.ic_stat_name);
        mBuilder.setContentTitle("Spectacle "+intent.getStringExtra("Name"));
        mBuilder.setContentText("at "+'\n'+intent.getStringExtra("Date"));
        mBuilder.setVibrate(new long[]{1000,1000});
        mBuilder.setLights(Color.GREEN,3000,3000);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        mBuilder.setSound(alarmSound);

        mBuilder.setContentIntent(resultPendingIntent);

        // notificationID allows you to update the notification later on.
        mNotificationManager.notify(ID, mBuilder.build());

    }



}
