package com.examle.libgo.johnsburgers.data.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by libgo on 03.12.2017.
 */

public class Timings {

    @SerializedName("monday")
    @Expose
    private String monday;
    @SerializedName("tuesday")
    @Expose
    private String tuesday;
    @SerializedName("wednesday")
    @Expose
    private String wednesday;
    @SerializedName("thursday")
    @Expose
    private String thursday;
    @SerializedName("friday")
    @Expose
    private String friday;
    @SerializedName("saturday")
    @Expose
    private String saturday;
    @SerializedName("Sunday")
    @Expose
    private String sunday;

    public String getMonday() {
        return monday;
    }

    public void setMonday(String monday) {
        this.monday = monday;
    }

    public String getTuesday() {
        return tuesday;
    }

    public void setTuesday(String tuesday) {
        this.tuesday = tuesday;
    }

    public String getWednesday() {
        return wednesday;
    }

    public void setWednesday(String wednesday) {
        this.wednesday = wednesday;
    }

    public String getThursday() {
        return thursday;
    }

    public void setThursday(String thursday) {
        this.thursday = thursday;
    }

    public String getFriday() {
        return friday;
    }

    public void setFriday(String friday) {
        this.friday = friday;
    }

    public String getSaturday() {
        return saturday;
    }

    public void setSaturday(String saturday) {
        this.saturday = saturday;
    }

    public String getSunday() {
        return sunday;
    }

    public void setSunday(String sunday) {
        this.sunday = sunday;
    }

}
