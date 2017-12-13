package com.examle.libgo.johnsburgers.data.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by libgo on 12.12.2017.
 */

public class ItemShop extends RealmObject {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("item_name")
    @Expose
    @PrimaryKey
    private String item_name;

    @SerializedName("counter")
    @Expose
    private Integer counter;

    @SerializedName("cost")
    @Expose
    private Integer cost;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public Integer getCounter() {
        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }
}
