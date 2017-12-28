package com.examle.libgo.johnsburgers.rx;

import com.examle.libgo.johnsburgers.data.parcelers.DrinkResponse;
import com.examle.libgo.johnsburgers.data.parcelers.MealsResponse;
import com.examle.libgo.johnsburgers.data.parcelers.ServerResponse;
import com.examle.libgo.johnsburgers.network.ApiService;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author libgo (29.12.2017)
 */

public class RxNetwork {

    private static ApiService apiService = ApiService.retrofit.create(ApiService.class);

    public static Observable<ServerResponse> getInfoApi(){
        return apiService.getApi()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static  Observable<DrinkResponse> getDrinksApi(){
        return apiService.getMenuDrinks()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static  Observable<MealsResponse> getMealsApi(){
        return apiService.getMenuMeals()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
