package com.examle.libgo.johnsburgers.data.pojos;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.parceler.Parcel;
/**
 * @author libgo (03.12.2017)
 */
@Parcel
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

    @SerializedName("color")
    @Expose
    private Integer color;


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

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }

}


