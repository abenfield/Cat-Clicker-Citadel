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

    public static List<String> catId = Arrays.asList(
            "cat",
            "robot_cat",
            "frankenstein_cat",
            "weed_cat",
            "pirate_cat",
            "real_cat",
            "ghost_cat",
            "black_cat",
            "witch_cat",
            "wizard_cat",
            "santa_cat",
             "hanukkah_cat");


    public static String catLookup(String catId, String searchKey){

        HashMap<String, HashMap<String, String>> catDictionary = new HashMap<String, HashMap<String,String>>();

        HashMap<String, String> name = new HashMap<String, String>();
        HashMap<String, String> multiplier = new HashMap<String, String>();
        HashMap<String, String> description = new HashMap<String, String>();

        catDictionary.put("name", name);
        catDictionary.put("multiplier", multiplier);
        catDictionary.put("description", description);

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
        name.put("santa_cat","Santa Cat");
        name.put("hanukkah_cat","Hanukkah Cat");

        multiplier.put("cat","1");
        multiplier.put("real_cat","5");
        multiplier.put("robot_cat","4");
        multiplier.put("frankenstein_cat","3");
        multiplier.put("weed_cat","420");
        multiplier.put("pirate_cat","6");
        multiplier.put("black_cat","5");
        multiplier.put("ghost_cat","2");
        multiplier.put("witch_cat","3");
        multiplier.put("wizard_cat","10");
        multiplier.put("santa_cat","50");
        multiplier.put("hanukkah_cat","50");

        description.put("cat","A cute yet somewhat ordinary kitty.\n 1 cat for every click.");
        description.put("real_cat","This Kitty has transcended it's own reality.\n 5 cats for every click. ");
        description.put("robot_cat","Beep Boop Meow! Cold Posterior, Warm Heart. \n 4 cats for every click.");
        description.put("frankenstein_cat","Frankenstein's Kitty says, 'Frankenstein is the scientist, not the monster. Meow!'\n 3 cats for every click.");
        description.put("weed_cat","meeeeeeeeeeowwwww\n 420 cats for every click.");
        description.put("pirate_cat","Argg! This Kitty has sailed the seven seas in search of the perfect place to drop it's loot.. Too bad it's on your kitchen table.\n 6 cats for every click.");
        description.put("black_cat","Contrary to popular belief, this kitty is actually really lucky, but suffers from a crippling gambling addiction. Meow!\n 5 cats for every click.");
        description.put("ghost_cat","Spoookyyyy Kitty! It's like you can't see him at all.\n 2 cats for every click.");
        description.put("witch_cat","This cat has avoided the witch trials successfully. Did you know that was only 327 years ago? That's like three old people ago. Meow!\n 3 cats for every click.");
        description.put("wizard_cat","This kitty is very wise but is also very mysterious..\n 10 cats for every click.");
        description.put("santa_cat","Ho Ho Ho Ho! Merry Christmas!\n 50 cats for every click.");
        description.put("hanukkah_cat","Hanukkah Sameach! May all the joys of Hanukkah fill your heart throughout the New Year!\n 50 cats for every click.");





        return((HashMap<String, String>)catDictionary.get(searchKey)).get(catId);


    }




}
