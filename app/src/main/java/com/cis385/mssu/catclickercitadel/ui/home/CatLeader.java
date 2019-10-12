package com.cis385.mssu.catclickercitadel.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.ImageView;

import com.cis385.mssu.catclickercitadel.CatContext;
import com.cis385.mssu.catclickercitadel.CatDictionary;
public class CatLeader {

    private String catId;
    private String catName;
    public int resId;
    private Context context;

    public int multiplier ;




    public CatLeader(Context context) {

         catId = CatContext.getStringRecord("currentCat", context);

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

}