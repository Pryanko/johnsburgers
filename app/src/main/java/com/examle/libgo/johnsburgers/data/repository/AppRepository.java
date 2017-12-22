package com.examle.libgo.johnsburgers.data.repository;

import com.examle.libgo.johnsburgers.data.parcelers.ServerResponse;
import com.examle.libgo.johnsburgers.network.ApiService;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author libgo (22.12.2017)
 */

public class AppRepository {

    private ApiService apiService = ApiService.retrofit.create(ApiService.class);

    public Observable<ServerResponse> getInfoApi(){
        return apiService.getApi()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    //ну и на каждый запрос создать по методу, позже разберемся с хранением данных в ропозитории
}
