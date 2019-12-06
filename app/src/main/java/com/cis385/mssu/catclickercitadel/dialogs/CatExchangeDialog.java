package com.cis385.mssu.catclickercitadel.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.cis385.mssu.catclickercitadel.CatContext;
import com.cis385.mssu.catclickercitadel.R;
import com.cis385.mssu.catclickercitadel.ui.shop.ShopFragment;

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
                   updateYarnCounter(activity);
               }
               else
               {
                   InsufficientFundsDialog menu = new InsufficientFundsDialog(context);
                   menu.showDialog(activity);
               }
                ShopFragment.refreshBool.setBoo(true);

               dialog.dismiss();


            }
        });

        dialog.show();

    }

    private void decrementCats(Activity activity) {

        CatContext.setIntRecord("catCounter",context, catCount - 10000);
    }

    private void incrementYarn(Activity activity) {

       int yarnCount = CatContext.getIntRecord("yarnCounter",context);

        CatContext.setIntRecord("yarnCounter",context, yarnCount + 3);
    }

    private void getCatCount(Activity activity) {

        catCount =  CatContext.getIntRecord("catCounter",context);

    }

    private void changeCounterText(Dialog dialog) {

        TextView counterText = dialog.findViewById(R.id.currentYarnCount);

        counterText.setText("You currently have " + catCount + " cats.");

    }

    private void updateYarnCounter(Activity activity) {
        int yarnCount = CatContext.getIntRecord("yarnCounter",context);
        TextView yarnCountText = activity.findViewById(R.id.yarnCounter);
        yarnCountText.setText(String.valueOf(yarnCount));

    }

}

