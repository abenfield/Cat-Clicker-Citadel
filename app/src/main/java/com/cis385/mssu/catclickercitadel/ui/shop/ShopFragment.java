package com.cis385.mssu.catclickercitadel.ui.shop;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.cis385.mssu.catclickercitadel.CatContext;
import com.cis385.mssu.catclickercitadel.FortressDictionary;
import com.cis385.mssu.catclickercitadel.LootBoxActivity;
import com.cis385.mssu.catclickercitadel.R;
import com.cis385.mssu.catclickercitadel.dialogs.BuyLootBoxDialog;
import com.cis385.mssu.catclickercitadel.dialogs.CatExchangeDialog;
import com.cis385.mssu.catclickercitadel.dialogs.InsufficientFundsDialog;


public class ShopFragment extends Fragment {

    int lootBoxCount;
    int catCount;
    int yarnCount;
    public static RefreshBool refreshBool = new RefreshBool();
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


    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {

        final View finalView = view;

        for (String temp : FortressDictionary.fortressId) {
            checkUnlockables(temp);
        }


        updateLootBox(view);
        updateCatCount(view);
        refreshYarnCount(view);
        refreshBool.setListener(new RefreshBool.ChangeListener() {
            @Override
            public void onChange() {
                updateLootBox(finalView);
                updateCatCount(finalView);
            }
        });

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


        final Button hutButton =  view.findViewById(R.id.hut_button);
        hutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (yarnCount >= 10) {
                    yarnCount = yarnCount -5 ;
                    updateYarnCount(view);
                    CatContext.setBoolRecord("hut",getContext(),true);
                    hutButton.setText("Unlocked");
                }
            }});

        final Button barrackButton =  view.findViewById(R.id.barrack_button);
        barrackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (yarnCount >= 10) {
                    yarnCount = yarnCount -5 ;
                    updateYarnCount(view);
                    CatContext.setBoolRecord("barrack",getContext(),true);
                    barrackButton.setText("Unlocked");
                }
            }});


        openExchangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CatContext.getIntRecord("catCounter", getContext()) >= 10000){
                    CatExchangeDialog menu = new CatExchangeDialog(getContext());
                    menu.showDialog(getActivity());
                }
                      else {
                    InsufficientFundsDialog menu = new InsufficientFundsDialog(getContext());
                    menu.showDialog(getActivity());
                }}});

        buyLootBoxButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (CatContext.getIntRecord("yarnCounter", getContext()) >= 3) {
                    BuyLootBoxDialog menu = new BuyLootBoxDialog(getContext());
                    menu.showDialog(getActivity());
                }
                else {
                    InsufficientFundsDialog menu = new InsufficientFundsDialog(getContext());
                    menu.showDialog(getActivity());
                }

            }});






        }

    private void updateCatCount(View view) {
        TextView catCounterText = view.findViewById(R.id.exchangeCatCounter);
        catCount = CatContext.getIntRecord("catCounter",getContext());
        catCounterText.setText(catCount + " cats");
    }

    private void updateYarnCount(View view) {
       CatContext.setIntRecord("yarnCounter",getContext(),yarnCount);
        TextView yarnCountText = getActivity().findViewById(R.id.yarnCounter);
        yarnCountText.setText(String.valueOf(yarnCount));
    }
    private void refreshYarnCount(View view) {
        yarnCount = CatContext.getIntRecord("yarnCounter",getContext());
        TextView yarnCountText = getActivity().findViewById(R.id.yarnCounter);
        yarnCountText.setText(String.valueOf(yarnCount));
    }

    public void updateLootBox(View view) {

       lootBoxCount = CatContext.getIntRecord("lootBoxCounter", getContext());
        TextView lootBoxText = view.findViewById(R.id.lootBoxCount);

        lootBoxText.setText("You currently have " + lootBoxCount + " loot boxes");
        Button lootBoxButton = view.findViewById(R.id.lootButton);
        if (lootBoxCount == 0)
            lootBoxButton.setBackgroundColor(getResources().getColor(R.color.colorGray));
        else
            lootBoxButton.setBackgroundColor(getResources().getColor(R.color.lootBoxButton));


    }

    private void checkUnlockables(String fortressId) {


        int buttonId = getContext().getResources().getIdentifier(fortressId+ "_button", "id", "com.cis385.mssu.catclickercitadel");
        try  {
            Button button = getView().findViewById(buttonId);

            SharedPreferences prefs = getActivity().getSharedPreferences(fortressId, Context.MODE_PRIVATE);

            Boolean unlocked = prefs.getBoolean(fortressId,false);

            if (unlocked == true) {
                button.setText("Unlocked");
                button.setClickable(false);

            }
        }
        catch (Exception e){

        }

    }

}

