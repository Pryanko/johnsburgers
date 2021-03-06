package com.examle.libgo.johnsburgers.data.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.parceler.Parcel;

/**
 * @author libgo (08.12.2017)
 */
@Parcel
public class Timing {

    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("day")
    @Expose
    private String day;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

}
