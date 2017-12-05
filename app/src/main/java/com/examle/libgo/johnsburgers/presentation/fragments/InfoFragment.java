package com.examle.libgo.johnsburgers.presentation.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.examle.libgo.johnsburgers.App;
import com.examle.libgo.johnsburgers.R;
import com.examle.libgo.johnsburgers.data.ServerResponse;
import com.examle.libgo.johnsburgers.data.pojos.News;
import com.examle.libgo.johnsburgers.network.ApiService;
import com.examle.libgo.johnsburgers.presentation.adapters.InfoAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;





/**
 * Created by libgo on 03.12.2017.
 */

public class InfoFragment extends MvpAppCompatFragment {

    private CompositeDisposable compositeDisposable;
    private LinearLayoutManager linearLayoutManager;
    private ServerResponse response;
    private List<News> news;

    //Bind View
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        ButterKnife.bind(this, view);
        downloadData();
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(savedInstanceState != null) {
            startAdapter();
        }

    }

    private void downloadData() {
        compositeDisposable = new CompositeDisposable();
        ApiService apiService = ApiService.retrofit.create(ApiService.class);
        compositeDisposable.add(apiService.getApi()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::startViewNews, this::handleError));

    }

    private void handleError(Throwable throwable) {
    }

    private void startViewNews(ServerResponse serverResponse) {
        response = serverResponse;
        Log.d("Server Response", response.toString() );
        startAdapter();

    }

    private void startAdapter(){
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        news = response.getNews();
        InfoAdapter infoAdapter = new InfoAdapter(news);
        recyclerView.setAdapter(infoAdapter);
        Log.d("News", news.toString());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);


        }
    }

