package com.cis385.mssu.catclickercitadel;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class FortressDictionary {

    public static List<String> fortressId = Arrays.asList(
            "hut",
            "barracks",
            "fortress",
            "castle"
            );


    public static String catLookup(String fortressId, String searchKey){

        HashMap<String, HashMap<String, String>> catDictionary = new HashMap<String, HashMap<String,String>>();

        HashMap<String, String> name = new HashMap<String, String>();
        HashMap<String, String> cps = new HashMap<String, String>();

        catDictionary.put("name", name);
        catDictionary.put("cps", cps);


        name.put("cat","Common Cat");
        name.put("real_cat","Hyperrealism Cat");
        name.put("robot_cat","Robot Cat");
        name.put("frankenstein_cat","Frankekitten");
        name.put("pirate_cat","Pirate Cat");
        name.put("weed_cat","Cat Nipped Kitty");
        name.put("black_cat","Black Cat");
        name.put("ghost_cat","Ghost Cat");
        name.put("wizard_cat","Wizard Cat");
        name.put("witch_cat","Witch Cat");


        cps.put("hut","1");
        cps.put("barracks","1");
        cps.put("fortress","1");
        cps.put("castle","1");




       return((HashMap<String, String>)catDictionary.get(searchKey)).get(fortressId);


    }




}
