package com.examle.libgo.johnsburgers.presentation.adapters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.examle.libgo.johnsburgers.R;
import com.examle.libgo.johnsburgers.data.pojos.MenuMealsAll;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author libgo (11.12.2017)
 */

public class MealsAllAdapter extends RecyclerView.Adapter<MealsAllAdapter.ViewHolder> {

    private List<MenuMealsAll> mealList;
    private Context context;
    private ItemAdapter itemAdapter = new ItemAdapter();

    public MealsAllAdapter(List<MenuMealsAll> list, Context mContext){
        this.mealList = list;
        this.context = mContext;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MealsAllAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.meals_all_card, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MenuMealsAll menuMeal = mealList.get(position);
        if(menuMeal.getDescribeBoolean()){
            holder.view.setVisibility(View.VISIBLE);
            holder.textViewDescribe.setVisibility(View.VISIBLE);
        }else{
            holder.view.setVisibility(View.GONE);
            holder.textViewDescribe.setVisibility(View.GONE);
        }
        holder.mealsItemImage.setImageURI(menuMeal.getScrImage());
        holder.textNameMeals.setText(menuMeal.getNameMeals());
        holder.textViewDescribe.setText(menuMeal.getDescribeText());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        holder.recyclerView.setLayoutManager(linearLayoutManager);
        holder.recyclerView.setFocusable(false);
        itemAdapter.addList(menuMeal.getMenuMeals());
        holder.recyclerView.setAdapter(itemAdapter);

    }



    @Override
    public int getItemCount() {
        if (mealList != null){
            return mealList.size();
        }
        return 0;
    }

     class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textViewAllMeals)
        TextView textNameMeals;
        @BindView(R.id.textDescribeAllMeals)
        TextView textViewDescribe;
        @BindView(R.id.meals_image)
        SimpleDraweeView mealsItemImage;
        @BindView(R.id.viewMeals)
        View view;
        @BindView(R.id.recyclerViewMeals)
        RecyclerView recyclerView;

         ViewHolder(View itemView) {
            super(itemView);
             ButterKnife.bind(this, itemView);
        }
    }
}
