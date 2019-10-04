package com.cis385.mssu.catclickercitadel.ui.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.cis385.mssu.catclickercitadel.R;

public class SettingsFragment extends Fragment {

    private SettingsViewModel settingsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        settingsViewModel =
                ViewModelProviders.of(this).get(SettingsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_settings, container, false);
        final TextView textView = root.findViewById(R.id.text_notifications);
        settingsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }


    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        SharedPreferences prefs = getActivity().getSharedPreferences("meowEnabled", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = prefs.edit();



        ToggleButton meowToggle = (ToggleButton) getView().findViewById(R.id.meowToggle);

        boolean checkState = prefs.getBoolean("meowEnabled",true);
        meowToggle.setChecked(checkState);

        meowToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                editor.putBoolean("meowEnabled", true).commit();
                }
                else
                {
                    editor.putBoolean("meowEnabled", false).commit();

                }
            }
        });
    }


    }