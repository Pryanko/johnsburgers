package com.examle.libgo.johnsburgers.presentation.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.examle.libgo.johnsburgers.R;
import com.examle.libgo.johnsburgers.data.ServerResponse;
import com.examle.libgo.johnsburgers.data.pojos.Location;
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
import static com.examle.libgo.johnsburgers.tools.Const.KEY_LOCATION;
import static com.examle.libgo.johnsburgers.tools.Const.KEY_MODEL;
import static com.examle.libgo.johnsburgers.tools.Const.KEY_MODEL_LOCATION;
import static com.examle.libgo.johnsburgers.tools.Const.LOG_TAG;

/**
 * Created by libgo on 03.12.2017.
 */

public class InfoFragment extends MvpAppCompatFragment {

    private ServerResponse response;
    private List<News> news;
    private List<Timing> timings;
    private Location location;
    LinearLayoutManager linearLayoutManager;
    LinearLayoutManager layoutManagerLocation;

    //Bind View
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.locationRecyclerView)
    RecyclerView recyclerViewLocation;
    @BindView(R.id.locationViewTop)
    View view;
    @BindView(R.id.textAddress)
    TextView addressText;
    @BindView(R.id.textLocation)
    TextView locationText;
    @BindView(R.id.textLink)
    TextView linkText;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.info_foreground_view)
    RelativeLayout foregroundInfoView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setRetainInstance(true);




    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        ButterKnife.bind(this, view);
        if(savedInstanceState == null) {
            progressBar.setVisibility(View.VISIBLE);
            downloadData();
        }
        if(savedInstanceState != null){
            progressBar.setVisibility(View.VISIBLE);
            news = savedInstanceState.getParcelableArrayList(KEY_MODEL);
            timings = savedInstanceState.getParcelableArrayList(KEY_MODEL_LOCATION);
            location = savedInstanceState.getParcelable(KEY_LOCATION);
            Log.d("zSTATE", location.getAdressName());
            startAdapter("s");
        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void downloadData() {

        foregroundInfoView.setVisibility(View.INVISIBLE);
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
        Log.d(LOG_TAG, response.toString() );
        Log.d(LOG_TAG, response.getTimings().toString());
        startAdapter("startViewNews");

    }

    private void startAdapter(String s){
        progressBar.setVisibility(View.INVISIBLE);
        foregroundInfoView.setVisibility(View.VISIBLE);
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
        if(location == null){
            location = response.getLocation();
        }

        InfoAdapter infoAdapter = new InfoAdapter(news);
        LocationAdapter locationAdapter = new LocationAdapter(timings);

        recyclerView.setAdapter(infoAdapter);
        recyclerViewLocation.setAdapter(locationAdapter);
        Log.d(LOG_TAG + s, news.toString());
        addressText.setText(location.getAdressName());
        locationText.setText(location.getInfoLocation());

        linkText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri urlLink = Uri.parse(response.getLink());
                Intent openLink = new Intent(Intent.ACTION_VIEW, urlLink);
                startActivity(openLink);
            }
        });

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(KEY_MODEL, (ArrayList<? extends Parcelable>) news);
        outState.putParcelableArrayList(KEY_MODEL_LOCATION, (ArrayList<? extends Parcelable>) timings);
        outState.putParcelable(KEY_LOCATION, location);
        }
    }




