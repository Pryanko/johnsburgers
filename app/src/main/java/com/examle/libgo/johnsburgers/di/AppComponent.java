package com.examle.libgo.johnsburgers.di;

import android.content.Context;

import com.examle.libgo.johnsburgers.data.repository.AppRepository;
import com.examle.libgo.johnsburgers.di.modules.RepositoryModule;
import com.examle.libgo.johnsburgers.di.modules.HeadModule;
import com.examle.libgo.johnsburgers.presentation.presenters.HeadPresenters;
import com.examle.libgo.johnsburgers.presentation.presenters.fragments_presenters.DrinksPresenter;
import com.examle.libgo.johnsburgers.presentation.presenters.fragments_presenters.InfoPresenter;
import com.examle.libgo.johnsburgers.tools.BottomBarBadgeHelper;
import com.examle.libgo.johnsburgers.tools.DataBaseSource;

import javax.inject.Singleton;

import dagger.Component;
/**
 * @author libgo (03.12.2017)
 */
@Singleton
@Component (modules = {HeadModule.class, RepositoryModule.class})
public interface AppComponent {

    //HeadModule
    BottomBarBadgeHelper getBottomBarBadgeHelper();

    DataBaseSource getDataBaseSource();

    HeadPresenters getHeadPresenter();

    InfoPresenter getInfoPresenter();

    DrinksPresenter getDrinkPresenter();
    //*


    //RepositoryModule
    AppRepository getAppRepository();

    //*
}
