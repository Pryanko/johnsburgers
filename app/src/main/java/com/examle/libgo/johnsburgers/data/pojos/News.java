package com.examle.libgo.johnsburgers.data.pojos;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by libgo on 03.12.2017.
 */

public class News implements Parcelable{
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
    private Boolean color;

    protected News(Parcel in) {
        nameNews = in.readString();
        textNews = in.readString();
        scrNews = in.readString();
    }

    public static final Creator<News> CREATOR = new Creator<News>() {
        @Override
        public News createFromParcel(Parcel in) {
            return new News(in);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };

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

    public Boolean getColor() {
        return color;
    }

    public void setColor(Boolean color) {
        this.color = color;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nameNews);
        parcel.writeString(textNews);
        parcel.writeString(scrNews);

    }
}


