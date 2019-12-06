package com.cis385.mssu.catclickercitadel.ui.home;

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

import com.cis385.mssu.catclickercitadel.CatContext;
import com.cis385.mssu.catclickercitadel.FortressDictionary;
import com.cis385.mssu.catclickercitadel.MainActivity;
import com.cis385.mssu.catclickercitadel.R;


public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private int yarnCount;
    private int clickCount;
    private int catCount;
    private int multiplier;

    boolean autoToggle = false;

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

        clickCount = CatContext.getIntRecord("clickCounter",getContext());
        catCount = CatContext.getIntRecord("catCounter",getContext());
        CatLeader currentCat = new CatLeader(getContext());

        multiplier = currentCat.getMultiplier();

        initGUI();


        ImageView catLeaderImage = getView().findViewById(R.id.catLeader);
        TextView catLeaderName = getView().findViewById(R.id.catLeaderName);


        catLeaderName.setText(currentCat.getCatName());
        catLeaderImage.setImageResource(currentCat.getRestId());


        updateYarnCounter();



        updateCatCountGUI(catCount);

        catLeaderImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bounceCat();
                boolean checkState = CatContext.getBoolRecord("meowEnabled",getContext());
               if (checkState)
                    mp.start();
                catCount  = (multiplier) + catCount ;
                clickCount  = clickCount + 1 ;

                CatContext.setIntRecord("catCounter",getContext(),catCount  + 1);
                CatContext.setIntRecord("clickCounter",getContext(),clickCount  + 1);



            }
        });



        ((MainActivity)getActivity()).triggerCps();

        checkUpgrades();

    }

    private void checkUpgrades() {
       ImageView castle = getView().findViewById(R.id.castle);

       boolean unlocked = CatContext.getBoolRecord("hut",getContext());

        if (unlocked == true)
        {
            castle.setImageResource(R.drawable.castle_hut);
        }
    }


    private void initGUI() {

         final TextView cpsText = getView().findViewById(R.id.cpsCounter);
         final TextView catCountText = getView().findViewById(R.id.catCounter);
        final TextView clickCountText = getView().findViewById(R.id.clickCounter);


        cpsText.setText(FortressDictionary.getCatsPerSecond(getContext()) + " Cats / Second");

        catCountText.setText(catCount  + " Cats");
        clickCountText.setText(clickCount + " Clicks");

        CatContext.setIntRecord("catCounter",getContext(),catCount );

    }





    public void updateCatCountGUI(int catCount) {
        final TextView catCountText = getView().findViewById(R.id.catCounter);
        final TextView clickCountText = getView().findViewById(R.id.clickCounter);
        final      TextView cpsCountText = getView().findViewById(R.id.cpsScore);

        catCountText.setText(catCount  + " Cats");
        clickCountText.setText(clickCount + " Clicks");

        cpsCountText.setText("+" + FortressDictionary.getCatsPerSecond(getContext()).toString());
        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            public void run() {
                cpsCountText.setText("");
            }

        }, 100);

        CatContext.setIntRecord("catCounter",getContext(),catCount );

    }

    private void updateYarnCounter() {
      yarnCount = CatContext.getIntRecord("yarnCounter",getContext());
        TextView yarnCountText = getActivity().findViewById(R.id.yarnCounter);
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

    }



