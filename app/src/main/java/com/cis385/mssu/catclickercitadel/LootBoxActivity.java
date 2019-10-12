package com.cis385.mssu.catclickercitadel;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cis385.mssu.catclickercitadel.R;
import com.cis385.mssu.catclickercitadel.ui.collection.CollectionFragment;
import com.cis385.mssu.catclickercitadel.ui.shop.ShopFragment;

import java.util.Random;

public class LootBoxActivity extends AppCompatActivity {

    int tapCount = 0;
    String lootBoxCounterKey = "lootBoxCounter";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loot_box_activity);
        getSupportActionBar().hide();


        startLootBox();
    }

    public void startLootBox(){

        final ImageView lootBox = findViewById(R.id.lootBox);
        final ImageView prize = findViewById(R.id.prize);
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.spin);
        lootBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tapCount++;
                if (tapCount == 0)
                    lootBox.setImageResource(R.drawable.box_close);
                if (tapCount == 1)
                    lootBox.setImageResource(R.drawable.box_half);
                if (tapCount == 2 )
                    lootBox.setImageResource(R.drawable.box_almost);
                if (tapCount == 3) {
                    lootBox.setImageResource(R.drawable.box_opened);
                    mp.start();
                    prize.setVisibility(View.VISIBLE);


                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            spinWheel(prize);

                        }
                    }, 800);


                    Handler handler2 = new Handler();
                    handler2.postDelayed(new Runnable() {
                        public void run() {
                            spinWheel(prize);

                        }
                    }, 1200);

                    Handler handler3 = new Handler();
                    handler3.postDelayed(new Runnable() {
                        public void run() {
                            spinWheel(prize);

                        }
                    }, 1800);

                    Handler handler4 = new Handler();
                    handler4.postDelayed(new Runnable() {
                        public void run() {
                            spinWheel(prize);

                        }
                    }, 2400);

                    Handler handler5 = new Handler();
                    handler5.postDelayed(new Runnable() {
                        public void run() {
                            spinWheel(prize);

                        }
                    }, 3000);

                    Handler handler6 = new Handler();
                    handler6.postDelayed(new Runnable() {
                        public void run() {
                            spinWheel(prize);

                        }
                    }, 3600);

                    Handler handler7 = new Handler();
                    handler7.postDelayed(new Runnable() {
                        public void run() {
                            spinWheel(prize);

                        }
                    }, 4200);




                    Handler handler8 = new Handler();
                    handler8.postDelayed(new Runnable() {
                        public void run() {
                            spinWheel(prize, "winner");


                        }
                    }, 4800);


                    Handler handler9 = new Handler();
                    handler9.postDelayed(new Runnable() {
                        public void run() {
            Intent myIntent = new Intent(LootBoxActivity.this, MainActivity.class);

                            startActivity(myIntent);


                        }
                    }, 7000);





                }

            }



        });
    }



    private void spinWheel(ImageView prize) {
        ImageView prizeImage = prize;




        int randomNumber = new Random().nextInt(CatDictionary.catId.size());
        String catId = CatDictionary.catId.get(randomNumber);
        final int resId = getResources().getIdentifier(catId, "drawable", "com.cis385.mssu.catclickercitadel");
        prizeImage.setImageResource(resId);





    }
    private void spinWheel(ImageView prize, String winner) {
        ImageView prizeImage = prize;
        TextView winnerMsg = findViewById(R.id.unlockText);



        int randomNumber = new Random().nextInt(CatDictionary.catId.size());
        String catId = CatDictionary.catId.get(randomNumber);
        final int resId = getResources().getIdentifier(catId, "drawable", "com.cis385.mssu.catclickercitadel");
        prizeImage.setImageResource(resId);
        winnerMsg.setVisibility(View.VISIBLE);
        winnerMsg.setText("You unlocked " + CatDictionary.catLookup(catId,"name"));
        decrementLootBox();
        unlock(catId);



    }

    private void decrementLootBox() {

       int lootBoxCount = CatContext.getIntRecord("lootBoxCounter",getApplicationContext());

        CatContext.setIntRecord("lootBoxCounter",getApplicationContext(), lootBoxCount - 1);

    }

    public void unlock(String catId){
        CatContext.setBoolRecord(catId,getApplicationContext(), true);


    }



}
