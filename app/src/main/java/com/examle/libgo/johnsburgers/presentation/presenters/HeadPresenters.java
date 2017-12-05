package com.examle.libgo.johnsburgers.presentation.presenters;

import com.examle.libgo.johnsburgers.data.ServerResponse;
import com.examle.libgo.johnsburgers.network.RequestApi;

/**
 * Created by libgo on 04.12.2017.
 */

public class HeadPresenters {

    private RequestApi requestApi;

    public HeadPresenters(RequestApi requestApi) {
        this.requestApi = requestApi;
    }

    public void onCreate(){
       // requestApi.startDownloadData();
    }
}
