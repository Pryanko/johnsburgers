package com.examle.libgo.johnsburgers.tools.mappers;

import com.examle.libgo.johnsburgers.data.pojos.ItemShop;

import java.util.List;

/**
 * @author libgo (27.12.2017)
 */

public class MapCostBasket {

    public static String mapCost (List<ItemShop> list){
         int i = 0;
         for (ItemShop itemShop : list){
             i = i + itemShop.getAll_cost();
         }
        return String.valueOf(i);
    }

}
