package com.cis385.mssu.catclickercitadel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CatDictionary {

    public static List<String> catId = Arrays.asList("cat", "robot_cat", "frankenstein_cat", "weed_cat", "pirate_cat","real_cat");


    public static String catLookup(String catId, String searchKey){

        HashMap<String, HashMap<String, String>> catDictionary = new HashMap<String, HashMap<String,String>>();

        HashMap<String, String> name = new HashMap<String, String>();
        HashMap<String, String> multiplier = new HashMap<String, String>();

        catDictionary.put("name", name);
        catDictionary.put("multiplier", multiplier);


        name.put("cat","Common Cat");
        name.put("real_cat","Hyperrealism Cat");
        name.put("robot_cat","Robot Cat");
        name.put("frankenstein_cat","Frankekitten");
        name.put("pirate_cat","Pirate Cat");
        name.put("weed_cat","Cat Nipped Kitty");


        multiplier.put("cat","1");
        multiplier.put("real_cat","5");
        multiplier.put("robot_cat","5");
        multiplier.put("frankenstein_cat","3");
        multiplier.put("weed_cat","420");
        multiplier.put("pirate_cat","3");





       return((HashMap<String, String>)catDictionary.get(searchKey)).get(catId);


    }




}
