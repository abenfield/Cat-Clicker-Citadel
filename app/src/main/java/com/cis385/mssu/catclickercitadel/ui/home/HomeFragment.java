package com.cis385.mssu.catclickercitadel.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
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
import androidx.lifecycle.ViewModelProviders;

import com.cis385.mssu.catclickercitadel.R;

import org.w3c.dom.Text;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private int yarnCount;
    private String yarnCounterKey = "yarnCounter";
    private int score;
    private int multiplier = 1;
    private int autoclick;
    boolean autoToggle = false;
    private String catCounterKey = "catCounter";
    private String currentCatKey = "currentCat";
    MediaPlayer mp;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, @Nullable Bundle savedInstanceState) {




        mp = MediaPlayer.create(getContext(), R.raw.meow);
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        return root;


    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        ImageView catLeaderImage = getView().findViewById(R.id.catLeader);
        TextView catLeaderName = getView().findViewById(R.id.catLeaderName);


        CatLeader currentCat = new CatLeader(getContext());
        catLeaderName.setText(currentCat.getCatName());
        catLeaderImage.setImageResource(currentCat.getRestId());
        multiplier = currentCat.multiplier;

        setYarnCounter();




        SharedPreferences prefs = getActivity().getSharedPreferences(catCounterKey, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = prefs.edit();


        score = prefs.getInt(catCounterKey, 0);
        final TextView textView = getView().findViewById(R.id.catCounter);

        textView.setText(score + " Cats");

        catLeaderImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bounceCat();
                SharedPreferences prefs = getActivity().getSharedPreferences("meowEnabled", Context.MODE_PRIVATE);
                boolean checkState = prefs.getBoolean("meowEnabled", true);
               if (checkState)
                    mp.start();
                score = (multiplier) + score;
               textView.setText(score + " Cats");
                editor.putInt(catCounterKey, score).commit();

            }
        });


     /*   autoClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Timer t = new Timer();
                TimerTask tt = new TimerTask() {
                    @Override
                    public void run() {
                        autoClicker();
                    }

                    ;
                };

                if (autoToggle == true) {

                }
                if (autoToggle == false) {
                    autoToggle = true;
                    t.schedule(tt, 1000);

                }


            }
        });
*/
    }

    private void setYarnCounter() {
        SharedPreferences prefs = getActivity().getSharedPreferences(yarnCounterKey, Context.MODE_PRIVATE);
        yarnCount = prefs.getInt(yarnCounterKey, 0);
        TextView yarnCountText = getView().findViewById(R.id.yarn_counter);
        yarnCountText.setText(String.valueOf(yarnCount));

    }


    public void bounceCat() {
        final TextView scoreText = getView().findViewById(R.id.score);
        final ImageView imageView = (ImageView) getView().findViewById(R.id.catLeader);
        final android.view.ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        String scoreDisplay = "+" + multiplier;
        scoreText.setText(scoreDisplay);
        layoutParams.width += 30;
        layoutParams.height += 30;
        layoutParams.width += 40;
        layoutParams.height += 40;
        imageView.setLayoutParams(layoutParams);
        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            public void run() {
                scoreText.setText("");
                layoutParams.width -= 30;
                layoutParams.height -= 30;
                layoutParams.width -= 40;
                layoutParams.height -= 40;
                imageView.setLayoutParams(layoutParams);
            }

        }, 100);

}


    public void autoClicker(){
        SharedPreferences prefs = getActivity().getSharedPreferences(catCounterKey, Context.MODE_PRIVATE);

        final TextView textView = (TextView) getView().findViewById(R.id.catCounter);
        final SharedPreferences.Editor editor = prefs.edit();

        editor.putInt(catCounterKey, score).commit();



        while (true) {

            score++;
            textView.setText(score + " Cats");
            editor.putInt(catCounterKey, score).commit();
        }

    }



    }



