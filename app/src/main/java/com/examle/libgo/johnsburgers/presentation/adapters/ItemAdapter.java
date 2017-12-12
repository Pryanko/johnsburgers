package com.examle.libgo.johnsburgers.presentation.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.examle.libgo.johnsburgers.App;
import com.examle.libgo.johnsburgers.R;
import com.examle.libgo.johnsburgers.data.pojos.ItemShop;
import com.examle.libgo.johnsburgers.data.pojos.MenuMeal;
import com.examle.libgo.johnsburgers.tools.BottomBarBadgeHelper;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

import static com.examle.libgo.johnsburgers.tools.Const.EURO;

/**
 * Created by libgo on 11.12.2017.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private List<MenuMeal> mealList;
    private BottomBarBadgeHelper bottomBarBadgeHelper = App.getAppComponent().getBottomBarBadgeHelper();

    public ItemAdapter(List<MenuMeal> list){
        this.mealList = list;
    }

    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_card, parent, false));
    }

    @Override
    public void onBindViewHolder(ItemAdapter.ViewHolder holder, int position) {
        Realm realm = Realm.getDefaultInstance();

        MenuMeal menuMeal = mealList.get(position);
        holder.textViewNameItem.setText(menuMeal.getNameMealsMenu());
        holder.textViewDescribeItem.setText(menuMeal.getDescribeTextMenu());
        holder.textViewCost.setText(EURO + String.valueOf(menuMeal.getCost()));
        holder.buttonItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Integer i = realm.where(ItemShop.class).findAll().size();
                 realm.beginTransaction();
                 ItemShop itemShop = realm.createObject(ItemShop.class, i);
                 itemShop.setCost(menuMeal.getCost());
                 itemShop.setItem_name(menuMeal.getNameMealsMenu());
                 itemShop.setCounter(1);
                 realm.commitTransaction();
                Log.d("RealmMM", realm.where(ItemShop.class).findAll().toString());
                 bottomBarBadgeHelper.setBottomBadge();
            }
        });

    }

    @Override
    public int getItemCount() {
        if(mealList != null){
         return mealList.size();
        }
        return 0;
    }

     class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textViewNameItem)
        TextView textViewNameItem;
        @BindView(R.id.textViewDescribeItem)
        TextView textViewDescribeItem;
        @BindView(R.id.textViewCost)
        TextView textViewCost;
        @BindView(R.id.buttonItem)
        Button buttonItem;

         ViewHolder(View itemView) {
            super(itemView);
             ButterKnife.bind(this, itemView);
        }
    }
}
