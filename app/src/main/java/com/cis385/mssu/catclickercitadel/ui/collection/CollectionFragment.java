package com.cis385.mssu.catclickercitadel.ui.collection;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.cis385.mssu.catclickercitadel.CatDictionary;
import com.cis385.mssu.catclickercitadel.R;

import java.util.Map;

public class CollectionFragment extends Fragment {

    private CollectionViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(CollectionViewModel.class);
        View root = inflater.inflate(R.layout.fragment_collection, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        dashboardViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {





        for (String temp : CatDictionary.catId) {
            checkUnlockables(temp);
        }




        Button unlockButton = getView().findViewById(R.id.unlockButton);


        unlockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            unlockAll();


            }
        });

    }





    private void unlockAll(){
        for (String catId : CatDictionary.catId) {
        SharedPreferences prefs = getActivity().getSharedPreferences(catId, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(catId, true).commit();
        }

    }
    private void checkUnlockables(String catId) {


        int imgId = getContext().getResources().getIdentifier(catId, "id", "com.cis385.mssu.catclickercitadel");
       try{ ImageView imageCat = (ImageView) getView().findViewById(imgId);



        SharedPreferences prefs = getActivity().getSharedPreferences(catId, Context.MODE_PRIVATE);

        Boolean unlocked = prefs.getBoolean(catId,false);

        if (unlocked == true) {
            int resId = getContext().getResources().getIdentifier(catId, "drawable", "com.cis385.mssu.catclickercitadel");
            imageCat.setImageResource(resId);

            }
       }
       catch (Exception e){

       }

        }





    }


