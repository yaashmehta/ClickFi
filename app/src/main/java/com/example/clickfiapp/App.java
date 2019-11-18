package com.example.clickfiapp;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("L3Ma1Cvf7PE19Grzmgcdl5HtA7kCklmeqximpaNh")
                // if defined
                .clientKey("kSAQ0LnvnXMvYDP2LUr69RFJon8mVS0Y7pMc94Du")
                .server("https://parseapi.back4app.com/")
                .build()
        );


    }
}
