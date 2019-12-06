package com.cis385.mssu.catclickercitadel;

/*

CatContext Class will be reserved for sending and retrieving data to and from SharedPreferences

 */


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;
import static java.lang.System.in;

public class CatContext {


    public static String getStringRecord (String recordName, Context context) {

            SharedPreferences preference = context.getSharedPreferences(recordName, MODE_PRIVATE);
             return preference.getString(recordName, "cat");

    }

    public static int getIntRecord (String recordName, Context context) {

        SharedPreferences preference = context.getSharedPreferences(recordName, MODE_PRIVATE);
        return preference.getInt(recordName, 0);

    }

    public static boolean getBoolRecord (String recordName, Context context) {

        SharedPreferences preference = context.getSharedPreferences(recordName, MODE_PRIVATE);
        return preference.getBoolean(recordName, false);


    }

    public static void setStringRecord (String recordName, Context context, String value) {

        SharedPreferences preference = context.getSharedPreferences(recordName, MODE_PRIVATE);
         SharedPreferences.Editor editor = preference.edit();
         editor.putString(recordName, value).apply();


    }

    public static void setIntRecord (String recordName, Context context, int value) {

        SharedPreferences preference = context.getSharedPreferences(recordName, MODE_PRIVATE);
        SharedPreferences.Editor editor = preference.edit();
        editor.putInt(recordName, value).apply();

    }

    public static void setBoolRecord (String recordName, Context context, boolean value) {

        SharedPreferences preference = context.getSharedPreferences(recordName, MODE_PRIVATE);
        SharedPreferences.Editor editor = preference.edit();
        editor.putBoolean(recordName, value).apply();

    }



}
