package com.examle.libgo.johnsburgers.presentation.fragments.child_fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.examle.libgo.johnsburgers.R;
import com.examle.libgo.johnsburgers.data.parcelers.DrinkResponse;
import com.examle.libgo.johnsburgers.data.pojos.MenuMeal;
import com.examle.libgo.johnsburgers.network.ApiService;
import com.examle.libgo.johnsburgers.presentation.adapters.ItemAdapter;
import org.parceler.Parcels;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import static com.examle.libgo.johnsburgers.tools.Const.KEY_MODEL_DRINKS;
/**
 * @author libgo (08.12.2017)
 */

public class DrinksFragment extends MvpAppCompatFragment {
    @BindView(R.id.recyclerViewItemDrinks)
    RecyclerView recyclerView;
    private DrinkResponse drinkResponse;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_drinks, container, false);
        ButterKnife.bind(this, view);

        if(savedInstanceState == null){
            downloadData();
        }
        if(savedInstanceState != null){
            drinkResponse = Parcels.unwrap(savedInstanceState.getParcelable(KEY_MODEL_DRINKS));
            startViewDrinks(drinkResponse);
        }
        return view;
    }

    private void downloadData() {
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        ApiService apiService = ApiService.retrofit.create(ApiService.class);
        compositeDisposable.add(apiService.getMenuDrinks()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::startViewDrinks, this::handleError));
    }

    private void handleError(Throwable throwable) {
        //Обработкой займемся поздней)
    }

    private void startViewDrinks(DrinkResponse drinkResponse) {
        this.drinkResponse = drinkResponse;
        List<MenuMeal> list = drinkResponse.getMenuDrinks();
        ItemAdapter itemAdapter = new ItemAdapter(list);
        LinearLayoutManager lm = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(lm);
        recyclerView.setAdapter(itemAdapter);
        recyclerView.setNestedScrollingEnabled(false);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(KEY_MODEL_DRINKS, Parcels.wrap(drinkResponse));
    }
}


