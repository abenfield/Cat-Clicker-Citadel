package com.cis385.mssu.catclickercitadel.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.cis385.mssu.catclickercitadel.MainActivity;
import com.cis385.mssu.catclickercitadel.R;

import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;
import java.util.prefs.Preferences;

import static android.content.Context.MODE_PRIVATE;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private int score;
    private int multiplier;
    private int autoclick;
    boolean autoToggle = false;
    private String key = "GodBlessThisHome";
    MediaPlayer mp;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, @Nullable Bundle savedInstanceState) {


        mp = MediaPlayer.create(getContext(), R.raw.meow);
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);


        // I am root
        return root;


    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {


        SharedPreferences prefs = getActivity().getSharedPreferences(key, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = prefs.edit();


        score = prefs.getInt(key, 0);

        ImageView imageView = (ImageView) getView().findViewById(R.id.catLeader);
        final TextView textView = (TextView) getView().findViewById(R.id.counter);
        Button autoClick = getView().findViewById((R.id.button));

        textView.setText(score + " Cats");


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bounceCat();
                mp.start();
                score++;
                textView.setText(score + " Cats");
                editor.putInt(key, score).commit();

            }
        });


        autoClick.setOnClickListener(new View.OnClickListener() {
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

    }


    public void bounceCat() {
        final ImageView imageView = (ImageView) getView().findViewById(R.id.catLeader);
        final android.view.ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        layoutParams.width += 30;
        layoutParams.height += 30;
        layoutParams.width += 40;
        layoutParams.height += 40;
        imageView.setLayoutParams(layoutParams);
        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            public void run() {
                layoutParams.width -= 30;
                layoutParams.height -= 30;
                layoutParams.width -= 40;
                layoutParams.height -= 40;
                imageView.setLayoutParams(layoutParams);
            }

        }, 100);

}


    public void autoClicker(){
        SharedPreferences prefs = getActivity().getSharedPreferences(key, Context.MODE_PRIVATE);

        final TextView textView = (TextView) getView().findViewById(R.id.counter);
        final SharedPreferences.Editor editor = prefs.edit();

        editor.putInt(key, score).commit();



        while (true) {

            score++;
            textView.setText(score + " Cats");
            editor.putInt(key, score).commit();
        }

    }



    }



