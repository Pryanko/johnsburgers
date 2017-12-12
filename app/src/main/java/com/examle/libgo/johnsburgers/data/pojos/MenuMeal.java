package com.examle.libgo.johnsburgers.data.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



/**
 * Created by libgo on 11.12.2017.
 */

public class MenuMeal {

    @SerializedName("cost")
    @Expose
    private Integer cost;
    @SerializedName("name_meals_menu")
    @Expose
    private String nameMealsMenu;
    @SerializedName("describe_text_menu")
    @Expose
    private String describeTextMenu;

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public String getNameMealsMenu() {
        return nameMealsMenu;
    }

    public void setNameMealsMenu(String nameMealsMenu) {
        this.nameMealsMenu = nameMealsMenu;
    }

    public String getDescribeTextMenu() {
        return describeTextMenu;
    }

    public void setDescribeTextMenu(String describeTextMenu) {
        this.describeTextMenu = describeTextMenu;
    }

}
