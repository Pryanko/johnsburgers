package com.examle.libgo.johnsburgers;

import android.app.Application;

import com.examle.libgo.johnsburgers.di.AppComponent;
import com.examle.libgo.johnsburgers.di.DaggerAppComponent;

/**
 * Created by libgo on 03.12.2017.
 */

public class App extends Application {

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.create();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
