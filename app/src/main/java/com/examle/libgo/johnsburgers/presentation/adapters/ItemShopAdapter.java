package com.examle.libgo.johnsburgers.presentation.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.examle.libgo.johnsburgers.R;
import com.examle.libgo.johnsburgers.data.pojos.ItemShop;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

import static com.examle.libgo.johnsburgers.tools.Const.EURO;
import static com.examle.libgo.johnsburgers.tools.Const.X;

/**
 * Created by libgo on 12.12.2017.
 */

public class ItemShopAdapter extends RecyclerView.Adapter<ItemShopAdapter.ViewHolder> {


    private List<ItemShop> itemShopList;


    public ItemShopAdapter(List<ItemShop> itemShopList) {
        this.itemShopList = itemShopList;
    }

    @Override
    public ItemShopAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ItemShopAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_shop_card, parent, false));
    }

    @Override
    public void onBindViewHolder(ItemShopAdapter.ViewHolder holder, int position) {
        ItemShop itemShop = itemShopList.get(position);
        holder.nameItemBasket.setText(itemShop.getItem_name());
        holder.counterBasket.setText(X + String.valueOf(itemShop.getCounter()));
        holder.costBasket.setText(EURO + String.valueOf(itemShop.getCost()));

    }

    @Override
    public int getItemCount() {
        if (itemShopList.size() != 0)
        {
            return itemShopList.size();
        }
        return 0;
    }


    public void removeItem(int position){
        Realm realm = Realm.getDefaultInstance();
        List<ItemShop> list = realm.where(ItemShop.class).findAll();
        realm.beginTransaction();
        ItemShop itemShop = list.get(position);
        itemShop.deleteFromRealm();
        realm.commitTransaction();
        notifyItemRemoved(position);

    }

     public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textViewNameItemBasket)
        TextView nameItemBasket;
        @BindView(R.id.textViewBasketCounter)
        TextView counterBasket;
        @BindView(R.id.textViewBasketAllCost)
        TextView costBasket;
        @BindView(R.id.background_view)
        RelativeLayout backgroundView;
        @BindView(R.id.foreground_view)
        public RelativeLayout foregroundView;

         public ViewHolder(View itemView) {
            super(itemView);
             ButterKnife.bind(this, itemView);
        }
    }
}
