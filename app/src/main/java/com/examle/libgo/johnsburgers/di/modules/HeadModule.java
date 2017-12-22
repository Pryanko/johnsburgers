package com.examle.libgo.johnsburgers.di.modules;

import com.examle.libgo.johnsburgers.presentation.presenters.HeadPresenters;
import com.examle.libgo.johnsburgers.tools.BottomBarBadgeHelper;
import com.examle.libgo.johnsburgers.tools.DataBaseSource;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
/**
 * @author libgo (03.12.2017)
 */
@Module
public class HeadModule {

    @Provides
    @Singleton
    BottomBarBadgeHelper bottomBarBadgeHelper(DataBaseSource dataBaseSource){
        return new BottomBarBadgeHelper(dataBaseSource);
    }

    @Provides
    @Singleton
    DataBaseSource dataBaseSource(){
        return new DataBaseSource();
    }

    @Provides
    @Singleton
    HeadPresenters headPresenters(BottomBarBadgeHelper bottomBarBadgeHelper) {
        return new HeadPresenters(bottomBarBadgeHelper);
    }
}
