package com.examle.libgo.johnsburgers.presentation.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.examle.libgo.johnsburgers.App;
import com.examle.libgo.johnsburgers.R;
import com.examle.libgo.johnsburgers.presentation.adapters.InfoAdapter;
import com.examle.libgo.johnsburgers.presentation.adapters.LocationAdapter;
import com.examle.libgo.johnsburgers.presentation.presenters.fragments_presenters.InfoPresenter;
import com.examle.libgo.johnsburgers.presentation.presenters.fragments_presenters.ViewFragmentsBase;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author libgo (03.12.2017)
 */

public class InfoFragment extends MvpAppCompatFragment implements ViewFragmentsBase {

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

    private InfoPresenter infoPresenter = App.getAppComponent().getInfoPresenter();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    InfoAdapter infoAdapter = new InfoAdapter();
    LocationAdapter locationAdapter = new LocationAdapter();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        ButterKnife.bind(this, view);
        //
        foregroundInfoView.setVisibility(View.INVISIBLE);
        //run
        infoPresenter.setView(this);
        infoPresenter.createView();
        return view;
    }

    @Override
    public void onPlayShow() {

        //visibility
        view.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        foregroundInfoView.setVisibility(View.VISIBLE);

        //filling adapters
        infoAdapter.setDataList(infoPresenter.getServerResponse().getNews());
        locationAdapter.setTimes(infoPresenter.getServerResponse().getTimings());

        //recycler options
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(infoAdapter);
        recyclerViewLocation.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewLocation.setNestedScrollingEnabled(false);
        recyclerViewLocation.setFocusable(false);
        recyclerViewLocation.setAdapter(locationAdapter);

        //information fill
        addressText.setText(infoPresenter.getServerResponse().getLocation().getAdressName());
        locationText.setText(infoPresenter.getServerResponse().getLocation().getInfoLocation());

        linkText.setOnClickListener(view -> {
            Uri urlLink = Uri.parse(infoPresenter.getServerResponse().getLink());
            Intent openLink = new Intent(Intent.ACTION_VIEW, urlLink);
            startActivity(openLink);
        });

    }

    @Override
    public void onError() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        infoPresenter.destroyView();
    }
}




