package com.examle.libgo.johnsburgers.data.pojos;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class BasketCost{

	@SerializedName("all_basket_cost")
	@Expose
	private String allBasketCost;

	public void setAllBasketCost(String allBasketCost){
		this.allBasketCost = allBasketCost;
	}

	public String getAllBasketCost(){
		return allBasketCost;
	}

	@Override
 	public String toString(){
		return 
			"BasketCost{" + 
			"all_basket_cost = '" + allBasketCost + '\'' + 
			"}";
		}
}