package com.examle.libgo.johnsburgers.network;

import com.examle.libgo.johnsburgers.data.ServerResponse;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

import static com.examle.libgo.johnsburgers.tools.Const.API_URL;

/**
 * Created by libgo on 03.12.2017.
 */

public interface ApiService {

    @GET("cpISVQWqyG?indent=2")
    Observable<ServerResponse> getApi();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(API_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
