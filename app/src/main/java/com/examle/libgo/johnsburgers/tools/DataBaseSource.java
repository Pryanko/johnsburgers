package com.examle.libgo.johnsburgers.tools;

import com.examle.libgo.johnsburgers.data.pojos.ItemShop;
import java.util.List;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import static com.examle.libgo.johnsburgers.tools.Const.REALM_PRIMARY_KEY;
/**
 * Created by libgo on 17.12.2017.
 */
public class DataBaseSource {

    private RealmConfiguration realmConfiguration;

    public DataBaseSource(){
        realmConfiguration = Realm.getDefaultConfiguration();
    }

    private ItemShop getSingleItem (String s){
        Realm realm = Realm.getInstance(realmConfiguration);
        ItemShop itemShop = realm.where(ItemShop.class).equalTo(REALM_PRIMARY_KEY, s).findFirst();
        realm.close();
        return itemShop;
    }

    private Integer sizeTable(){
        Realm realm = Realm.getInstance(realmConfiguration);
        Integer sizeTable = realm.where(ItemShop.class).findAll().size();
        realm.close();
        return sizeTable;
    }

    public List<ItemShop> getListItemShop(){
        Realm realm = Realm.getInstance(realmConfiguration);
        return realm.where(ItemShop.class).findAll();
    }

    public Boolean getValidItem(String s){
        return getSingleItem(s) == null;
    }

    public Boolean getFillTable(){
        return sizeTable() == 0;
    }

    public Realm getRealm(){
        return Realm.getInstance(realmConfiguration);
    }

    Integer getSizeTable(){
        return sizeTable();
    }

    public void deleteItem(Integer position){
        Realm realm = Realm.getInstance(realmConfiguration);
        List<ItemShop> list = realm.where(ItemShop.class).findAll();
        realm.beginTransaction();
        ItemShop itemShop = list.get(position);
        itemShop.deleteFromRealm();
        realm.commitTransaction();
        realm.close();
    }

    public void addItem(String s, Integer i){
        Realm realm = Realm.getInstance(realmConfiguration);
        realm.beginTransaction();
        ItemShop itemShop = realm.createObject(ItemShop.class, s);
        itemShop.setCounter(1);
        itemShop.setCost(i);
        realm.commitTransaction();
        realm.close();
    }

    public void changeCounterCost(String s){
        Realm realm = Realm.getInstance(realmConfiguration);
        ItemShop itemShop = realm.where(ItemShop.class).equalTo(REALM_PRIMARY_KEY, s).findFirst();
        realm.beginTransaction();
        assert itemShop != null;
        itemShop.setCounter(itemShop.getCounter() + 1);
        itemShop.setCost(itemShop.getCost() * itemShop.getCounter());
        realm.commitTransaction();
        realm.close();
    }
}
