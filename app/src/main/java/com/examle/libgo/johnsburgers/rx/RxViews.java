package com.examle.libgo.johnsburgers.rx;


import android.util.Log;

import com.examle.libgo.johnsburgers.data.pojos.ItemShop;
import com.examle.libgo.johnsburgers.tools.mappers.MapCostBasket;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * @author libgo (03.12.2017)
 */

public class RxViews {

    public static Observable<Integer> getAllCost(List<ItemShop> shopList){
        return Observable.just(shopList)
                .map(MapCostBasket::mapCost)
                .doOnNext(integer -> Log.d("I test ", String.valueOf(integer)))
                .observeOn(AndroidSchedulers.mainThread());
    }

}
