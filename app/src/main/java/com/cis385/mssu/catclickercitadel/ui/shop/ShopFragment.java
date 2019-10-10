package com.cis385.mssu.catclickercitadel.ui.shop;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.cis385.mssu.catclickercitadel.CatDictionary;
import com.cis385.mssu.catclickercitadel.LootBoxActivity;
import com.cis385.mssu.catclickercitadel.R;
import com.cis385.mssu.catclickercitadel.ui.collection.CatSelectDialog;
import com.cis385.mssu.catclickercitadel.ui.collection.CollectionFragment;

import java.util.Collection;
import java.util.Random;


public class ShopFragment extends Fragment {

    int lootBoxCount;
    String lootBoxCounterKey = "lootBoxCounter";

    private ShopViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(ShopViewModel.class);
        View root = inflater.inflate(R.layout.fragment_shop, container, false);
        final TextView textView = root.findViewById(R.id.text_notifications);
        notificationsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });


        return root;
    }


    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        final View finalView = view;
        updateLootBox(view);


        Button lootBoxButton = view.findViewById(R.id.lootButton);
        Button openExchangeButton = view.findViewById(R.id.openExchangeButton);
        Button buyLootBoxButton = view.findViewById(R.id.buyLootBoxButton);

        lootBoxButton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
                if (lootBoxCount >  0) {
                    Intent myIntent = new Intent(getActivity(), LootBoxActivity.class);
                    startActivity(myIntent);
                }

          }});

        openExchangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CatExchangeDialog menu = new CatExchangeDialog(getContext());
                menu.showDialog(getActivity());
            }});
        buyLootBoxButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BuyLootBoxDialog menu = new BuyLootBoxDialog(getContext());
                menu.showDialog(getActivity());
            }});






        }

    private void updateLootBox(View view) {

        SharedPreferences prefs = getActivity().getSharedPreferences(lootBoxCounterKey, Context.MODE_PRIVATE);
        lootBoxCount = prefs.getInt(lootBoxCounterKey, 0);
        TextView lootBoxText = view.findViewById(R.id.lootBoxCount);

        lootBoxText.setText("You currently have " + lootBoxCount + " loot boxes");

        if (lootBoxCount == 0) {
            Button lootBoxButton = view.findViewById(R.id.lootButton);
            lootBoxButton.setBackgroundColor(getResources().getColor(R.color.colorGray));
        }


    }
}

