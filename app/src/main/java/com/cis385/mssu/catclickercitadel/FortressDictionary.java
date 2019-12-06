package com.cis385.mssu.catclickercitadel;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.prefs.Preferences;

public class FortressDictionary {




    public static List<String> fortressId = Arrays.asList(
            "hut",
            "barracks",
            "monastery",
            "tower"
            );


    public static Integer getCatsPerSecond(Context context){

        int totalCps = 0;

        HashMap<String, Integer> cps = new HashMap<String, Integer>();

        cps.put("hut",1);
        cps.put("barracks",5);
        cps.put("monastery",10);
        cps.put("tower",50);


        for (String temp : FortressDictionary.fortressId) {
            SharedPreferences prefs = context.getSharedPreferences(temp,Context.MODE_PRIVATE);
            Boolean unlocked = prefs.getBoolean(temp,false);
            if (unlocked)
                totalCps += cps.get(temp);
        }

       return totalCps;


    }




}
