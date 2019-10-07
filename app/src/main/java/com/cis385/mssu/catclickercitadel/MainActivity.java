package com.cis385.mssu.catclickercitadel;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cis385.mssu.catclickercitadel.ui.collection.CatSelectDialog;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {


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

    public void openDialog(View v) {
        String catId = v.getTag().toString();
        identifyCat(catId);



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
