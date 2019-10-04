package com.cis385.mssu.catclickercitadel.ui.home;

import android.widget.ImageView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cis385.mssu.catclickercitadel.R;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {

        mText = new MutableLiveData<>();

    }

    public LiveData<String> getText() {
        return mText;
    }


}