package com.examle.libgo.johnsburgers.data.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.List;

/**
 * @author libgo (11.12.2017)
 */
@Parcel
public class MenuMealsAll {
    @SerializedName("menu_meals")
    @Expose
    private List<MenuMeal> menuMeals = null;
    @SerializedName("scr_image")
    @Expose
    private String scrImage;
    @SerializedName("describe_boolean")
    @Expose
    private Boolean describeBoolean;
    @SerializedName("describe_text")
    @Expose
    private String describeText;
    @SerializedName("name_meals")
    @Expose
    private String nameMeals;

    public List<MenuMeal> getMenuMeals() {
        return menuMeals;
    }

    public void setMenuMeals(List<MenuMeal> menuMeals) {
        this.menuMeals = menuMeals;
    }

    public String getScrImage() {
        return scrImage;
    }

    public void setScrImage(String scrImage) {
        this.scrImage = scrImage;
    }

    public Boolean getDescribeBoolean() {
        return describeBoolean;
    }

    public void setDescribeBoolean(Boolean describeBoolean) {
        this.describeBoolean = describeBoolean;
    }

    public String getDescribeText() {
        return describeText;
    }

    public void setDescribeText(String describeText) {
        this.describeText = describeText;
    }

    public String getNameMeals() {
        return nameMeals;
    }

    public void setNameMeals(String nameMeals) {
        this.nameMeals = nameMeals;
    }

}

