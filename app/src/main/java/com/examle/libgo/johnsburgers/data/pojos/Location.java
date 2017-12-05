package com.examle.libgo.johnsburgers.data.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by libgo on 03.12.2017.
 */

public class Location {

    @SerializedName("adress_name")
    @Expose
    private String adressName;
    @SerializedName("info_location")
    @Expose
    private String infoLocation;

    public String getAdressName() {
        return adressName;
    }

    public void setAdressName(String adressName) {
        this.adressName = adressName;
    }

    public String getInfoLocation() {
        return infoLocation;
    }

    public void setInfoLocation(String infoLocation) {
        this.infoLocation = infoLocation;
    }
}
