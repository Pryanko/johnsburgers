package com.examle.libgo.johnsburgers.di.modules;


import android.app.Fragment;

import android.content.Context;
import android.os.Bundle;

import com.examle.libgo.johnsburgers.data.ServerResponse;
import com.examle.libgo.johnsburgers.network.RequestApi;
import com.examle.libgo.johnsburgers.presentation.adapters.SwipeAdapter;
import com.examle.libgo.johnsburgers.presentation.presenters.HeadPresenters;
import com.examle.libgo.johnsburgers.tools.BottomBarBadgeHelper;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by libgo on 03.12.2017.
 */
@Module
public class HeadModule {

    @Provides
    @Singleton
    BottomBarBadgeHelper bottomBarBadgeHelper(){
        return new BottomBarBadgeHelper();
    }

    @Provides
    @Singleton
    ServerResponse serverResponse(){
    return new ServerResponse();
}


    @Provides
    @Singleton
    RequestApi requestApi(ServerResponse serverResponse){
        return new RequestApi(serverResponse);
    }


    @Provides
    @Singleton
    HeadPresenters headPresenters(RequestApi requestApi){
        return new HeadPresenters(requestApi);
    }


}
