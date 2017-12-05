package com.examle.libgo.johnsburgers.network;

import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.examle.libgo.johnsburgers.App;
import com.examle.libgo.johnsburgers.data.ServerResponse;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by libgo on 04.12.2017.
 */

public class RequestApi {

    private ServerResponse response;
    private CompositeDisposable compositeDisposable;

    public RequestApi(ServerResponse serverResponse) {

    }


}
