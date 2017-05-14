package com.moviescheduler;

import android.content.SharedPreferences;
import android.content.Context;

import static android.content.Context.MODE_PRIVATE;


/**
 * Created by AmbroB on 5/14/2017.
 */

public class Token  {

    public static void saveToken(String token,Context context){

        SharedPreferences preferences = context.getSharedPreferences("myPrefs", MODE_PRIVATE);
        preferences.edit().putString("token", token).apply();
    }
    public static void deleteToken(Context context){
        SharedPreferences preferences = context.getSharedPreferences("myPrefs", MODE_PRIVATE);
        preferences.edit().remove("token").apply();
    }
    public static boolean hasToaken(Context context){
        SharedPreferences preferences = context.getSharedPreferences("myPrefs", MODE_PRIVATE);
        return preferences.contains("token");
    }
    public static String getToken(Context context){

        SharedPreferences preferences = context.getSharedPreferences("myPrefs", MODE_PRIVATE);
        return preferences.getString("token","");
    }
    public static void saveDetails(String details,Context context){
        SharedPreferences preferences = context.getSharedPreferences("myPrefs", MODE_PRIVATE);
        preferences.edit().putString("details", details).apply();
    }
    public static String getDetails(Context context){
        SharedPreferences preferences = context.getSharedPreferences("myPrefs", MODE_PRIVATE);
        return preferences.getString("details","");
    }



}
