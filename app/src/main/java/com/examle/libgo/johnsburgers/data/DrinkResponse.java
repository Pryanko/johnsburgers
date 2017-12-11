package com.examle.libgo.johnsburgers.data;

import com.examle.libgo.johnsburgers.data.pojos.MenuMeal;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by libgo on 11.12.2017.
 */

public class DrinkResponse {

    @SerializedName("status_code")
    @Expose
    private String statusCode;
    @SerializedName("response_date")
    @Expose
    private String responseDate;
    @SerializedName("menu_drinks")
    @Expose
    private List<MenuMeal> menuDrinks = null;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(String responseDate) {
        this.responseDate = responseDate;
    }

    public List<MenuMeal> getMenuDrinks() {
        return menuDrinks;
    }

    public void setMenuDrinks(List<MenuMeal> menuDrinks) {
        this.menuDrinks = menuDrinks;
    }
}
