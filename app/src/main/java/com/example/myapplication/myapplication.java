package com.example.myapplication;

import android.app.Application;

import com.firebase.client.Firebase;

public class myapplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Firebase.setAndroidContext(this);
    }
}
