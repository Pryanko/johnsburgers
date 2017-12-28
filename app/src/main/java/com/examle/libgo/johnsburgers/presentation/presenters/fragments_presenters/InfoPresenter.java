package com.examle.libgo.johnsburgers.presentation.presenters.fragments_presenters;

import com.examle.libgo.johnsburgers.App;
import com.examle.libgo.johnsburgers.data.parcelers.ServerResponse;
import com.examle.libgo.johnsburgers.data.repository.AppRepository;
import com.examle.libgo.johnsburgers.presentation.fragments.InfoFragment;
import com.examle.libgo.johnsburgers.rx.RxNetwork;
import io.reactivex.disposables.CompositeDisposable;

/**
 * @author libgo (29.12.2017)
 */

public class InfoPresenter implements PresenterBase {

    private AppRepository appRepository = App.getAppComponent().getAppRepository();
    private InfoFragment infoView;

    public void setView(InfoFragment fragment) {
        this.infoView = fragment;
    }

    @Override
    public void createView() {
          downloadData();
    }

    private void downloadData() {
        /**
         * Вообще инфу дергать с сети должен репозиторий. Данный проект  учебно - практический. в течении
         * обучения - я перепробывал кучу вариантов и подходов.
         */

        if(appRepository.getDownloading()){
            infoView.onPlayShow();
        }else {
            CompositeDisposable compositeDisposable = new CompositeDisposable();
            compositeDisposable.add(RxNetwork.getInfoApi()
                    .doOnNext(serverResponse -> appRepository.okResponse(serverResponse))
                    .subscribe(this::okServerResponse, this::handleError)
            );
        }

    }

    private void okServerResponse(ServerResponse serverResponse) {
        //appRepository.okResponse(serverResponse);  Не знаю насколько это правильно - но пишу данные в репозиторий в doOnNext выше. Наверное не особо правильно :)
        infoView.onPlayShow();
    }

    public ServerResponse getServerResponse(){
        return appRepository.getServerResponse();
    }

    private void handleError(Throwable throwable) {
        //Обработкой займемся поздней)
    }

    @Override
    public void errorView() {

    }

    @Override
    public void destroyView() {
        infoView = null;
    }


}
