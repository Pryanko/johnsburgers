package com.examle.libgo.johnsburgers.presentation.fragments;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.examle.libgo.johnsburgers.R;
import com.examle.libgo.johnsburgers.data.ServerResponse;
import com.examle.libgo.johnsburgers.data.pojos.News;
import com.examle.libgo.johnsburgers.data.pojos.Timing;
import com.examle.libgo.johnsburgers.network.ApiService;
import com.examle.libgo.johnsburgers.presentation.adapters.InfoAdapter;
import com.examle.libgo.johnsburgers.presentation.adapters.LocationAdapter;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import static com.examle.libgo.johnsburgers.tools.Const.KEY_MODEL;

/**
 * Created by libgo on 03.12.2017.
 */

public class InfoFragment extends MvpAppCompatFragment {

    private ServerResponse response;
    private List<News> news;
    private List<Timing> timings;
    LinearLayoutManager linearLayoutManager;
    LinearLayoutManager layoutManagerLocation;
    //Bind View
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.locationRecyclerView)
    RecyclerView recyclerViewLocation;
    @BindView(R.id.locationViewTop)
    View view;
    /*@BindView(R.id.progressBar)
    ProgressBar progressBar;*/


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        if(savedInstanceState == null) {
            downloadData();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        ButterKnife.bind(this, view);
        if(savedInstanceState != null){
            news = savedInstanceState.getParcelableArrayList(KEY_MODEL);
            startAdapter("onCreateView");
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void downloadData() {
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        ApiService apiService = ApiService.retrofit.create(ApiService.class);
        compositeDisposable.add(apiService.getApi()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::startViewNews, this::handleError));

    }

    private void handleError(Throwable throwable) {
        //Обработкой займемся поздней)
    }

    private void startViewNews(ServerResponse serverResponse) {
        response = serverResponse;
        Log.d("Server Response", response.toString() );
        Log.d("TIMINGS", response.getTimings().toString());
        startAdapter("startViewNews");

    }

    private void startAdapter(String s){
        view.setVisibility(View.VISIBLE);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        layoutManagerLocation = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerViewLocation.setLayoutManager(layoutManagerLocation);
        recyclerViewLocation.setNestedScrollingEnabled(false);
        if(news == null) {
            news = response.getNews();
        }
        if(timings == null){
            timings = response.getTimings();
        }

        InfoAdapter infoAdapter = new InfoAdapter(news);
        LocationAdapter locationAdapter = new LocationAdapter(timings);

        recyclerView.setAdapter(infoAdapter);
        recyclerViewLocation.setAdapter(locationAdapter);
        Log.d("News " + s, news.toString());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("scroll", recyclerView.getVerticalScrollbarPosition());
        outState.putParcelableArrayList(KEY_MODEL, (ArrayList<? extends Parcelable>) news);
        }
    }




