package com.examle.libgo.johnsburgers.rx;

import android.support.v7.widget.RecyclerView;

import com.examle.libgo.johnsburgers.data.parcelers.ServerResponse;
import com.examle.libgo.johnsburgers.data.pojos.News;
import com.examle.libgo.johnsburgers.presentation.adapters.InfoAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author libgo (03.12.2017)
 */

public class RxViews {

    public static void startInfo (RecyclerView recyclerView, ServerResponse serverResponse){


    }

    public static void startInfo(ServerResponse serverResponse, RecyclerView recyclerView) {
        List<News> newsList;
        newsList = serverResponse.getNews();
        InfoAdapter infoAdapter = new InfoAdapter(newsList);
        recyclerView.setAdapter(infoAdapter);
    }
}
