package com.superheroes.app;

import android.app.Application;
import android.content.Context;

public class HeroesApplication extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        HeroesApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return HeroesApplication.context;
    }
}
