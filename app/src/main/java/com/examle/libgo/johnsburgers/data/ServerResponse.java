package com.examle.libgo.johnsburgers.data;

import com.examle.libgo.johnsburgers.data.pojos.Location;
import com.examle.libgo.johnsburgers.data.pojos.News;
import com.examle.libgo.johnsburgers.data.pojos.Timings;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by libgo on 03.12.2017.
 */

public class ServerResponse {

    @SerializedName("status_code")
    @Expose
    private String statusCode;
    @SerializedName("response_date")
    @Expose
    private String responseDate;
    @SerializedName("news")
    @Expose
    private List<News> news = null;
    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("timings")
    @Expose
    private Timings timings;
    @SerializedName("link")
    @Expose
    private String link;

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

    public List<News> getNews() {
        return news;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Timings getTimings() {
        return timings;
    }

    public void setTimings(Timings timings) {
        this.timings = timings;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}