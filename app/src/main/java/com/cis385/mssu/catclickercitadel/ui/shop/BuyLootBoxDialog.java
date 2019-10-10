package com.cis385.mssu.catclickercitadel.ui.shop;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.cis385.mssu.catclickercitadel.R;

public class BuyLootBoxDialog {
    private Context context;
    int yarnCount;
    String yarnCounterKey = "yarnCounter";
    String lootBoxCounterKey = "lootBoxCounter";
    public BuyLootBoxDialog(Context current){
        this.context = current;
    }
    public void showDialog(final Activity activity) {


        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.buy_lootbox_menu);

        Button okButton = (Button) dialog.findViewById(R.id.close_button);
        Button selectButton = (Button) dialog.findViewById(R.id.select_button);

        getYarnCount(activity);
        changeCounterText(dialog);



        okButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });

        selectButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {



               if (yarnCount >= 3) {
                   decrementYarn(activity);
                   incrementLootbox(activity);
               }

               dialog.dismiss();


            }
        });

        dialog.show();

    }

    private void decrementYarn(Activity activity) {

       SharedPreferences prefs = activity.getSharedPreferences(yarnCounterKey, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = prefs.edit();

        editor.putInt(yarnCounterKey, yarnCount - 3 ).commit();
    }

    private void incrementLootbox(Activity activity) {

        SharedPreferences prefs = activity.getSharedPreferences(lootBoxCounterKey, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = prefs.edit();

        int currentLootBoxCount = prefs.getInt(lootBoxCounterKey, Context.MODE_PRIVATE);
        editor.putInt(lootBoxCounterKey, currentLootBoxCount + 1).commit();


    }

    private void getYarnCount(Activity activity) {
        SharedPreferences prefs = activity.getSharedPreferences(yarnCounterKey, Context.MODE_PRIVATE);
        yarnCount = prefs.getInt(yarnCounterKey, 0);
    }

    private void changeCounterText(Dialog dialog) {

        TextView counterText = dialog.findViewById(R.id.currentYarnCount);

        counterText.setText("You currently have " + yarnCount + " yarn.");

    }



}

