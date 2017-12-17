package com.examle.libgo.johnsburgers.data.parcelers;

import com.examle.libgo.johnsburgers.data.pojos.MenuMealsAll;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.List;

/**
 * @author libgo (11.12.2017)
 */
@Parcel
public class MealsResponse {

    @SerializedName("status_code")
    @Expose
    private String statusCode;
    @SerializedName("menu_meals_all")
    @Expose
    private List<MenuMealsAll> menuMealsAll = null;
    @SerializedName("response_date")
    @Expose
    private String responseDate;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public List<MenuMealsAll> getMenuMealsAll() {
        return menuMealsAll;
    }

    public void setMenuMealsAll(List<MenuMealsAll> menuMealsAll) {
        this.menuMealsAll = menuMealsAll;
    }

    public String getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(String responseDate) {
        this.responseDate = responseDate;
    }

}
