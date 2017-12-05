package com.examle.libgo.johnsburgers.di;

import com.examle.libgo.johnsburgers.data.ServerResponse;
import com.examle.libgo.johnsburgers.di.modules.HeadModule;
import com.examle.libgo.johnsburgers.presentation.presenters.HeadPresenters;

import javax.inject.Singleton;

import dagger.Component;
/**
 * Created by libgo on 03.12.2017.
 */
@Singleton
@Component (modules = {HeadModule.class})
public interface AppComponent {

    HeadPresenters getHeadPresenters();

    ServerResponse getServerResponse();

}
