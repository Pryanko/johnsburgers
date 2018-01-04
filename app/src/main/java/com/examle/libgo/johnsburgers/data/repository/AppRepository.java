package com.examle.libgo.johnsburgers.data.repository;

import com.examle.libgo.johnsburgers.data.parcelers.DrinkResponse;
import com.examle.libgo.johnsburgers.data.parcelers.MealsResponse;
import com.examle.libgo.johnsburgers.data.parcelers.ServerResponse;

/**
 * @author libgo (22.12.2017)
 */

public class AppRepository {

    private Boolean downloading = false;
    private Boolean downloadingD = false;
    private Boolean downloadingM = false;
    private ServerResponse serverResponse;
    private DrinkResponse drinkResponse;
    private MealsResponse mealsResponse;

    public void okResponse(ServerResponse serverResponse){
        this.serverResponse = serverResponse;
        downloading = true;
    }

    public ServerResponse getServerResponse(){
        return serverResponse;
    }

    public  Boolean getDownloading(){
        return downloading;
    }

    public DrinkResponse getDrinkResponse() {
        return drinkResponse;
    }

    public void setDrinkResponse(DrinkResponse drinkResponse) {
        this.drinkResponse = drinkResponse;
        downloadingD = true;
    }

    public MealsResponse getMealsResponse() {
        return mealsResponse;
    }

    public void setMealsResponse(MealsResponse mealsResponse) {
        this.mealsResponse = mealsResponse;
        downloadingM = true;
    }

    public Boolean getDownloadingD() {
        return downloadingD;
    }

    public Boolean getDownloadingM() {
        return downloadingM;
    }

}
