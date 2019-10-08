package com.cis385.mssu.catclickercitadel.ui.shop;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.cis385.mssu.catclickercitadel.R;
import com.cis385.mssu.catclickercitadel.ui.collection.CollectionFragment;

import java.util.Collection;
import java.util.Random;

public class ShopFragment extends Fragment {
     int tapCount = 0;

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


    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){

       final ImageView lootBox = view.findViewById(R.id.lootBox);
        final ImageView prize = view.findViewById(R.id.prize);
        final View finalView = view;
        lootBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tapCount++;
                if (tapCount == 0)
                    lootBox.setImageResource(R.drawable.box_close);
                if (tapCount == 1)
                lootBox.setImageResource(R.drawable.box_half);
                if (tapCount == 2 )
                    lootBox.setImageResource(R.drawable.box_almost);
                if (tapCount == 3) {
                    lootBox.setImageResource(R.drawable.box_opened);

                    prize.setVisibility(View.VISIBLE);


                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            spinWheel(prize);

                        }
                    }, 800);


                    Handler handler2 = new Handler();
                    handler2.postDelayed(new Runnable() {
                        public void run() {
                            spinWheel(prize);

                        }
                    }, 1200);

                    Handler handler3 = new Handler();
                    handler3.postDelayed(new Runnable() {
                        public void run() {
                            spinWheel(prize);

                        }
                    }, 1800);

                    Handler handler4 = new Handler();
                    handler4.postDelayed(new Runnable() {
                        public void run() {
                            spinWheel(prize);

                        }
                    }, 2400);

                    Handler handler5 = new Handler();
                    handler5.postDelayed(new Runnable() {
                        public void run() {
                            spinWheel(prize);

                        }
                    }, 3000);

                    Handler handler6 = new Handler();
                    handler6.postDelayed(new Runnable() {
                        public void run() {
                            spinWheel(prize);

                        }
                    }, 3600);

                    Handler handler7 = new Handler();
                    handler7.postDelayed(new Runnable() {
                        public void run() {
                            spinWheel(prize);

                        }
                    }, 4200);




                    Handler handler8 = new Handler();
                    handler8.postDelayed(new Runnable() {
                        public void run() {
                            spinWheel(prize, finalView);


                        }
                    }, 4800);


                    Handler handler9 = new Handler();
                    handler9.postDelayed(new Runnable() {
                        public void run() {
                            FragmentManager fragmentManager = getFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            CollectionFragment NAME = new CollectionFragment();
                            fragmentTransaction.replace(R.id.nav_host_fragment, NAME);
                            fragmentTransaction.commit();


                        }
                    }, 7000);





                }
                    }



        });
    }



    private void spinWheel(ImageView prize) {
     ImageView prizeImage = prize;




             int randomNumber = new Random().nextInt(CatDictionary.catId.size());
            String catId = CatDictionary.catId.get(randomNumber);
            final int resId = getContext().getResources().getIdentifier(catId, "drawable", "com.cis385.mssu.catclickercitadel");
         prizeImage.setImageResource(resId);





    }
    private void spinWheel(ImageView prize, View view) {
        ImageView prizeImage = prize;
        TextView winnerMsg = view.findViewById(R.id.unlockText);



        int randomNumber = new Random().nextInt(CatDictionary.catId.size());
        String catId = CatDictionary.catId.get(randomNumber);
        final int resId = getContext().getResources().getIdentifier(catId, "drawable", "com.cis385.mssu.catclickercitadel");
        prizeImage.setImageResource(resId);
        winnerMsg.setVisibility(View.VISIBLE);
        winnerMsg.setText("You unlocked " + CatDictionary.catLookup(catId,"name"));
        unlock(catId);



    }
    public void unlock(String catId){
        SharedPreferences prefs = getActivity().getSharedPreferences(catId, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(catId, true).commit();

    }


}