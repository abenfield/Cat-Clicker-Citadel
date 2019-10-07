package com.cis385.mssu.catclickercitadel.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.ImageView;
import com.cis385.mssu.catclickercitadel.CatDictionary;
public class CatLeader {

    private String catId;
    private String catName;
    public int resId;
    private Context context;

    public int multiplier;

    final String currentCatKey = "currentCat";


    public CatLeader(Context context) {

        SharedPreferences currentCatPref = context.getSharedPreferences(currentCatKey, Context.MODE_PRIVATE);
        catId = currentCatPref.getString(currentCatKey, "cat");

        catName = CatDictionary.catLookup(catId, "name");

        multiplier = Integer.parseInt(CatDictionary.catLookup(catId,"multiplier"));


         resId = context.getResources().getIdentifier(catId, "drawable", "com.cis385.mssu.catclickercitadel");
    }


    public String getCatName(){

            return catName;
    }

    public int getMultiplier(){

        return multiplier;
    }


    public int getRestId(){

        return resId;
    }

    public void setResId(int id){

        resId = id;
    }






    public void updateCatLeader(String catID) {



    }
}