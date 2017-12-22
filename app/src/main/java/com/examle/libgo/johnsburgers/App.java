package com.examle.libgo.johnsburgers;

import android.app.Application;
import com.examle.libgo.johnsburgers.di.AppComponent;
import com.examle.libgo.johnsburgers.di.DaggerAppComponent;
import com.facebook.drawee.backends.pipeline.Fresco;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * @author libgo (03.12.2017)
 */

public class App extends Application {

    private static AppComponent appComponent;


    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.create();

        Fresco.initialize(this);

        Realm.init(getApplicationContext());
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("item_menu")
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }

}
