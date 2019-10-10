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

public class CatExchangeDialog {
    private Context context;
    int catCount;
    public CatExchangeDialog(Context current){
        this.context = current;
    }
    public void showDialog(final Activity activity) {


        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.cat_exchange_menu);

        Button okButton = (Button) dialog.findViewById(R.id.close_button);
        Button selectButton = (Button) dialog.findViewById(R.id.select_button);

        getCatCount(activity);
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



               if (catCount >= 10000) {
                   decrementCats(activity);
                   incrementYarn(activity);
               }

               dialog.dismiss();


            }
        });

        dialog.show();

    }

    private void decrementCats(Activity activity) {

       SharedPreferences prefs = activity.getSharedPreferences("catCounter", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = prefs.edit();

        editor.putInt("catCounter", catCount - 10000 ).commit();
    }

    private void incrementYarn(Activity activity) {

        SharedPreferences prefs = activity.getSharedPreferences("yarnCounter", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = prefs.edit();

        int currentYarnCount = prefs.getInt("yarnCounter", Context.MODE_PRIVATE);
        editor.putInt("yarnCounter", currentYarnCount + 3).commit();


    }

    private void getCatCount(Activity activity) {
        SharedPreferences prefs = activity.getSharedPreferences("catCounter", Context.MODE_PRIVATE);
        catCount = prefs.getInt("catCounter", 0);
    }

    private void changeCounterText(Dialog dialog) {

        TextView counterText = dialog.findViewById(R.id.currentYarnCount);

        counterText.setText("You currently have " + catCount + " cats.");

    }



}

