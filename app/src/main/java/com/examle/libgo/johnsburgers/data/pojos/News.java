package com.examle.libgo.johnsburgers.data.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by libgo on 03.12.2017.
 */

public class News {

    @SerializedName("name_news")
    @Expose
    private String nameNews;
    @SerializedName("text_news")
    @Expose
    private String textNews;
    @SerializedName("scr_news")
    @Expose
    private String scrNews;

    public String getNameNews() {
        return nameNews;
    }

    public void setNameNews(String nameNews) {
        this.nameNews = nameNews;
    }

    public String getTextNews() {
        return textNews;
    }

    public void setTextNews(String textNews) {
        this.textNews = textNews;
    }

    public String getScrNews() {
        return scrNews;
    }

    public void setScrNews(String scrNews) {
        this.scrNews = scrNews;
    }

}


