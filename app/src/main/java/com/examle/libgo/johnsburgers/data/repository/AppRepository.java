package com.examle.libgo.johnsburgers.data.repository;

import com.examle.libgo.johnsburgers.data.parcelers.ServerResponse;
import com.examle.libgo.johnsburgers.network.ApiService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author libgo (22.12.2017)
 */

public class AppRepository {

    private ApiService apiService = ApiService.retrofit.create(ApiService.class);
    private Boolean downloading = false;
    private ServerResponse serverResponse;


    public Observable<ServerResponse> getInfoApi(){
                 return apiService.getApi()
                 .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public ServerResponse getServerResponse(){
        return serverResponse;
    }

    public  Boolean getDownloading(){
        return downloading;
    }

    private void okResponse(ServerResponse serverResponse){
        this.serverResponse = serverResponse;
        downloading = true;
    }

    private void handleError(Throwable throwable) {
    }

    //ну и на каждый запрос создать по методу, позже разберемся с хранением данных в ропозитории
}
