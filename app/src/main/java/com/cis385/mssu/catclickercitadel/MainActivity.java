package com.cis385.mssu.catclickercitadel;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.cis385.mssu.catclickercitadel.dialogs.CatExchangeDialog;
import com.cis385.mssu.catclickercitadel.dialogs.CatSelectDialog;
import com.cis385.mssu.catclickercitadel.ui.home.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {


private boolean cpsActive = false;

int cpsMultiplier = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);


    }


   public void yarnExchangeOpen(View v) {

        CatExchangeDialog yd = new CatExchangeDialog(getApplicationContext());
        yd.showDialog(this);


   }


    public void openDialog(View v) {
        String catId = v.getTag().toString();
        identifyCat(catId);
    }



    public void catsPerSecond(final SharedPreferences prefs){

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                incrementCat(prefs);
            }
        }, 0, 1000);
    }

public void triggerCps()
{

    cpsMultiplier = FortressDictionary.getCatsPerSecond(getApplicationContext());


    if (!cpsActive)
    {
        final SharedPreferences prefs = getSharedPreferences("catCounter", Context.MODE_PRIVATE);

        catsPerSecond(prefs);

        cpsActive = true;
    }

}




    private void incrementCat(SharedPreferences prefs) {

        Activity mActivity = MainActivity.this;

        mActivity.runOnUiThread(new Runnable() {
            public void run() {
                int catCount = CatContext.getIntRecord("catCounter", getApplicationContext());
                CatContext.setIntRecord("catCounter", getApplicationContext(), catCount + cpsMultiplier);
                catCount = catCount + cpsMultiplier;
                Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);


                if (fragment != null) {
                    Fragment currentFragment = fragment.getChildFragmentManager().getPrimaryNavigationFragment();

                    if (currentFragment instanceof HomeFragment) {
                        HomeFragment homeFragment = (HomeFragment) currentFragment;

                        homeFragment.updateCatCountGUI(catCount);
                    }
                }
            }
        });


    }



    public void identifyCat(String catId){

        SharedPreferences prefs = this.getSharedPreferences(catId, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = prefs.edit();

        // Check if unlocked
        if (catId.equals("cat") ||  (prefs.getBoolean(catId,false) == true)){


            CatSelectDialog menu = new CatSelectDialog(this);
            menu.showDialog(this, catId);
        }

    }



}
