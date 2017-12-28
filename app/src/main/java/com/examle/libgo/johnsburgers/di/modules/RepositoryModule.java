package com.examle.libgo.johnsburgers.di.modules;

import com.examle.libgo.johnsburgers.data.repository.AppRepository;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

/**
 * @author libgo (03.12.2017)
 */

@Module
public class RepositoryModule {

    @Provides
    @Singleton
    AppRepository appRepository(){
        return new AppRepository();
    }


}
