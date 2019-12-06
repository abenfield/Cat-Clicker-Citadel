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

public class InsufficientFundsDialog {
    private Context context;
    int yarnCount;
    public InsufficientFundsDialog(Context current){
        this.context = current;
    }




    public void showDialog(final Activity activity) {


        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.insufficient_funds_menu);

        Button okButton = (Button) dialog.findViewById(R.id.close_button);




        okButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });



        dialog.show();

    }




    }






