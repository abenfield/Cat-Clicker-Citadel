package com.cis385.mssu.catclickercitadel.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cis385.mssu.catclickercitadel.CatContext;
import com.cis385.mssu.catclickercitadel.CatDictionary;
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

        TextView catDescription = dialog.findViewById(R.id.catDescription);

        catDescription.setText(CatDictionary.catLookup(catName,"description"));

        TextView catText = dialog.findViewById(R.id.catName);

        catText.setText(CatDictionary.catLookup(catName,"name"));


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
                CatContext.setStringRecord("currentCat", context, catName );
                dialog.dismiss();
            }
        });

        dialog.show();

    }
    }

