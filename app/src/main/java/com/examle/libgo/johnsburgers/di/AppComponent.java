package com.examle.libgo.johnsburgers.di;

import com.examle.libgo.johnsburgers.data.repository.AppRepository;
import com.examle.libgo.johnsburgers.di.modules.ApiModule;
import com.examle.libgo.johnsburgers.di.modules.HeadModule;
import com.examle.libgo.johnsburgers.presentation.presenters.HeadPresenters;
import com.examle.libgo.johnsburgers.tools.BottomBarBadgeHelper;
import com.examle.libgo.johnsburgers.tools.DataBaseSource;

import javax.inject.Singleton;

import dagger.Component;
/**
 * @author libgo (03.12.2017)
 */
@Singleton
@Component (modules = {HeadModule.class, ApiModule.class})
public interface AppComponent {

    //HeadModule
    BottomBarBadgeHelper getBottomBarBadgeHelper();

    DataBaseSource getDataBaseSource();

    HeadPresenters getHeadPresenters();
    //*


    //ApiModule
    AppRepository getAppRepository();

    //*
}
