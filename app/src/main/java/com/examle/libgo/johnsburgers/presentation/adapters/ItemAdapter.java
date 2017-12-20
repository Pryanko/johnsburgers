package com.examle.libgo.johnsburgers.presentation.adapters;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.examle.libgo.johnsburgers.App;
import com.examle.libgo.johnsburgers.R;
import com.examle.libgo.johnsburgers.data.pojos.MenuMeal;
import com.examle.libgo.johnsburgers.layout.ExpandCardLayout;
import com.examle.libgo.johnsburgers.tools.BottomBarBadgeHelper;
import com.examle.libgo.johnsburgers.tools.DataBaseSource;
import java.util.HashSet;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import static com.examle.libgo.johnsburgers.tools.constants.ConstApp.EURO;
/**
 * @author libgo (11.12.2017)
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private List<MenuMeal> mealList;
    private HashSet<Integer> expandedPositionSet;
    private BottomBarBadgeHelper bottomBarBadgeHelper = App.getAppComponent().getBottomBarBadgeHelper();
    private DataBaseSource dataBaseSource = App.getAppComponent().getDataBaseSource();


    public ItemAdapter(List<MenuMeal> list){
        expandedPositionSet = new HashSet<>();
        this.mealList = list;

    }

    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_card, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ItemAdapter.ViewHolder holder, int position) {
        holder.updateItem(position);
        MenuMeal menuMeal = mealList.get(position);
        holder.textViewNameItem.setText(menuMeal.getNameMealsMenu());
        holder.textViewDescribeItem.setText(menuMeal.getDescribeTextMenu());
        holder.textViewCost.setText(EURO + String.valueOf(menuMeal.getCost()));
        holder.buttonItem.setOnClickListener(view -> {

            if(dataBaseSource.getValidItem(menuMeal.getNameMealsMenu())){
                dataBaseSource.addItem(menuMeal.getNameMealsMenu(), menuMeal.getCost());
            }
            else{
                dataBaseSource.changeCounterCost(menuMeal.getNameMealsMenu());
            }
            bottomBarBadgeHelper.changeBottomBadge();
    });
    }

    private void registerExpand(int position) {
        if (expandedPositionSet.contains(position)) {
            removeExpand(position);
        } else {
            addExpand(position);

        }
    }

    private void removeExpand(int position) {
        expandedPositionSet.remove(position);
    }


    private void addExpand(int position){
        expandedPositionSet.add(position);
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
        @BindView(R.id.expand_layout)
        ExpandCardLayout expandCardLayout;

         private ViewHolder(View itemView) {
            super(itemView);
             ButterKnife.bind(this, itemView);
        }

         private void updateItem(int position) {
             expandCardLayout.setOnExpandListener(expanded -> registerExpand(position));
             expandCardLayout.setExpand(expandedPositionSet.contains(position));
         }
     }
}
