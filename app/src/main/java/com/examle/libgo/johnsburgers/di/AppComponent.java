package com.examle.libgo.johnsburgers.di;

import com.examle.libgo.johnsburgers.di.modules.HeadModule;
import com.examle.libgo.johnsburgers.tools.BottomBarBadgeHelper;
import com.examle.libgo.johnsburgers.tools.DataBaseSource;

import javax.inject.Singleton;

import dagger.Component;
/**
 * @author libgo (03.12.2017)
 */
@Singleton
@Component (modules = {HeadModule.class})
public interface AppComponent {

    BottomBarBadgeHelper getBottomBarBadgeHelper();

    DataBaseSource getDataBaseSource();

}
