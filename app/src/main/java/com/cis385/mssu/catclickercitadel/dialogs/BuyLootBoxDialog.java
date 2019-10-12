package com.cis385.mssu.catclickercitadel.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.cis385.mssu.catclickercitadel.CatContext;
import com.cis385.mssu.catclickercitadel.MainActivity;
import com.cis385.mssu.catclickercitadel.R;
import com.cis385.mssu.catclickercitadel.ui.shop.ShopFragment;

public class BuyLootBoxDialog  {
    private Context context;
    int yarnCount;
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
                   incrementLootBox(activity);
                    updateYarnCounter(activity);
               }


               dialog.dismiss();


            }
        });

        dialog.show();

    }

    private void decrementYarn(Activity activity) {

        CatContext.setIntRecord("yarnCounter",context, yarnCount - 3);

    }

    private void incrementLootBox(Activity activity) {

        int lootBoxCount = CatContext.getIntRecord("lootBoxCounter",context);
        ;
        CatContext.setIntRecord("lootBoxCounter",context, lootBoxCount + 1);



    }

    private void getYarnCount(Activity activity) {
       yarnCount = CatContext.getIntRecord("yarnCounter",context);

    }

    private void changeCounterText(Dialog dialog) {

        TextView counterText = dialog.findViewById(R.id.currentYarnCount);

        counterText.setText("You currently have " + yarnCount + " yarn.");

    }

    private void updateYarnCounter(Activity activity) {
        int yarnCount = CatContext.getIntRecord("yarnCounter",context);
        TextView yarnCountText = activity.findViewById(R.id.yarnCounter);
        yarnCountText.setText(String.valueOf(yarnCount));

    }



}

