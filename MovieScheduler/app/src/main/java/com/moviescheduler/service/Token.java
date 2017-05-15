package com.moviescheduler.service;

import android.content.SharedPreferences;
import android.content.Context;

import static android.content.Context.MODE_PRIVATE;


/**
 * Created by AmbroB on 5/14/2017.
 */

public class Token  {

    static final String TOKEN_NAME="Authorization";
    static final String USER_DETAILS="User-Details";

    public static void saveToken(String token,Context context){

        SharedPreferences preferences = context.getSharedPreferences("myPrefs", MODE_PRIVATE);
        preferences.edit().putString(TOKEN_NAME, token).apply();
    }
    public static void deleteToken(Context context){
        SharedPreferences preferences = context.getSharedPreferences("myPrefs", MODE_PRIVATE);
        preferences.edit().remove(TOKEN_NAME).apply();
    }
    public static boolean hasToaken(Context context){
        SharedPreferences preferences = context.getSharedPreferences("myPrefs", MODE_PRIVATE);
        return preferences.contains(TOKEN_NAME);
    }
    public static String getToken(Context context){

        SharedPreferences preferences = context.getSharedPreferences("myPrefs", MODE_PRIVATE);
        return preferences.getString(TOKEN_NAME,"");
    }
    public static void saveDetails(String details,Context context){
        SharedPreferences preferences = context.getSharedPreferences("myPrefs", MODE_PRIVATE);
        preferences.edit().putString(USER_DETAILS, details).apply();
    }
    public static String getDetails(Context context){
        SharedPreferences preferences = context.getSharedPreferences("myPrefs", MODE_PRIVATE);
        return preferences.getString(USER_DETAILS,"");
    }



}
