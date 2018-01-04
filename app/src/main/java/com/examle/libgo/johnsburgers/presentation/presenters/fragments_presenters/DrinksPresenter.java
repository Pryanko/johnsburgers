package com.examle.libgo.johnsburgers.presentation.presenters.fragments_presenters;

import com.examle.libgo.johnsburgers.App;
import com.examle.libgo.johnsburgers.data.parcelers.DrinkResponse;
import com.examle.libgo.johnsburgers.data.pojos.MenuMeal;
import com.examle.libgo.johnsburgers.data.repository.AppRepository;
import com.examle.libgo.johnsburgers.presentation.fragments.child_fragments.DrinksFragment;
import com.examle.libgo.johnsburgers.rx.RxNetwork;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;


/**
 * @author libgo (29.12.2017)
 */

public class DrinksPresenter implements PresenterBase {

    private DrinksFragment drinksView;
    private AppRepository appRepository = App.getAppComponent().getAppRepository();

    public void setView(DrinksFragment fragment) {
        this.drinksView = fragment;
    }


    private void handleError(Throwable throwable) {
        //Обработкой займемся поздней)
    }

    @Override
    public void createView() {
        if (appRepository.getDownloadingD()) {
            drinksView.startViewShow();
        }else {
            downloadData();
        }
    }

    @Override
    public void errorView() {

    }

    @Override
    public void destroyView() {
        drinksView = null;
    }

    private void downloadData() {
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(RxNetwork.getDrinksApi()
                .doOnNext(drinkResponse -> appRepository.setDrinkResponse(drinkResponse))
                .subscribe(this::startView, this::handleError));
    }

    private void startView(DrinkResponse drinkResponse) {
        if (drinkResponse != null) {
            drinksView.startViewShow();
        }
    }

    public List<MenuMeal> getListMenu(){
        return appRepository.getDrinkResponse().getMenuDrinks();
    }
}
