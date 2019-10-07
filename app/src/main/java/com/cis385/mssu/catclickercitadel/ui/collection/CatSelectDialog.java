package com.cis385.mssu.catclickercitadel.ui.collection;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.cis385.mssu.catclickercitadel.R;

public class CatSelectDialog  {
    private Context context;

    public CatSelectDialog(Context current){
        this.context = current;
    }
    public void showDialog(Activity activity, String msg) {

        final String catName = msg;
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.cat_select_menu);

        Button okButton = (Button) dialog.findViewById(R.id.close_button);
        Button selectButton = (Button) dialog.findViewById(R.id.select_button);


        final ImageView selectedCat = dialog.findViewById(R.id.selectedCat);
        final int resID = context.getResources().getIdentifier(msg, "drawable", "com.cis385.mssu.catclickercitadel");
        selectedCat.setImageResource(resID);
        okButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });

        selectButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                SharedPreferences prefs = context.getSharedPreferences("currentCat", Context.MODE_PRIVATE);
                final   SharedPreferences.Editor editor = prefs.edit();
                editor.putString("currentCat",catName).commit();
                dialog.dismiss();
            }
        });

        dialog.show();

    }
    }

