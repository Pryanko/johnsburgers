package com.examle.libgo.johnsburgers.network;

import com.examle.libgo.johnsburgers.data.parcelers.DrinkResponse;
import com.examle.libgo.johnsburgers.data.parcelers.ServerResponse;
import com.examle.libgo.johnsburgers.data.parcelers.MealsResponse;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

import static com.examle.libgo.johnsburgers.tools.Const.API_URL;

/**
 * @author libgo (03.12.2017)
 */

public interface ApiService {

    @GET("ceYlLNevkO?indent=2")
    Observable<ServerResponse> getApi();

    @GET("cvrhEatlaq?indent=2")
    Observable<MealsResponse> getMenuMeals();

    @GET("bUdsdpCeMi?indent=2")
    Observable<DrinkResponse> getMenuDrinks();


    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(API_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
