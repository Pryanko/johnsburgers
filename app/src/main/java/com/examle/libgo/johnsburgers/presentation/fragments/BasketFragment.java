package com.examle.libgo.johnsburgers.presentation.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.examle.libgo.johnsburgers.App;
import com.examle.libgo.johnsburgers.R;
import com.examle.libgo.johnsburgers.data.pojos.ItemShop;
import com.examle.libgo.johnsburgers.presentation.adapters.ItemShopAdapter;
import com.examle.libgo.johnsburgers.rx.RxViews;
import com.examle.libgo.johnsburgers.tools.DataBaseSource;
import com.examle.libgo.johnsburgers.tools.RecyclerItemTouchHelper;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;

/**
 * @author libgo (03.12.2017)
 */
public class BasketFragment extends MvpAppCompatFragment implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener, View.OnClickListener {

    @BindView(R.id.item_basket_image)
    SimpleDraweeView item_basket_image;
    @BindView(R.id.textViewNullBasket)
    TextView textNullBasket;
    @BindView(R.id.recyclerViewBasket)
    RecyclerView recyclerViewBasket;
    @BindView(R.id.basketView)
    View viewBasket;
    @BindView(R.id.textViewOrder)
    TextView textViewOrder;
    @BindView(R.id.buttonBasket)
    Button buttonBasket;
    @BindView(R.id.all_basket_cost)
    TextView allCostBasket;

    private LinearLayoutManager layoutManager;
    private ItemShopAdapter itemShopAdapter;
    private OrderFragment orderFragment = new OrderFragment();
    private DataBaseSource dataBaseSource = App.getAppComponent().getDataBaseSource();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_basket, container, false);
        ButterKnife.bind(this, view);



        buttonListener();

        itemShopAdapter = new ItemShopAdapter(dataBaseSource.getListItemShop());
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerViewBasket.setAdapter(itemShopAdapter);

        ItemTouchHelper.SimpleCallback itemTouchHelper = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelper).attachToRecyclerView(recyclerViewBasket);

        if(dataBaseSource.getFillTable()){
            viewBasket.setVisibility(View.INVISIBLE);
            textViewOrder.setVisibility(View.INVISIBLE);
            buttonBasket.setVisibility(View.INVISIBLE);
            allCostBasket.setVisibility(View.INVISIBLE);

        }
        else {
            onCostBasket(dataBaseSource.getListItemShop());
            changeView();
        }

        dataBaseSource.getRealm().addChangeListener(realm -> {
            if(dataBaseSource.getFillTable()) {
                item_basket_image.setVisibility(View.VISIBLE);
                textNullBasket.setVisibility(View.VISIBLE);
                viewBasket.setVisibility(View.INVISIBLE);
                textViewOrder.setVisibility(View.GONE);
                buttonBasket.setVisibility(View.GONE);
            }
            else {
                onCostBasket(dataBaseSource.getListItemShop());
                changeView();
                itemShopAdapter.notifyDataSetChanged();
                recyclerViewBasket.smoothScrollToPosition(recyclerViewBasket.getAdapter().getItemCount() - 1);
            }

        });
        return view;
    }

    protected void changeView(){
        item_basket_image.setVisibility(View.INVISIBLE);
        textNullBasket.setVisibility(View.INVISIBLE);
        viewBasket.setVisibility(View.VISIBLE);
        textViewOrder.setVisibility(View.VISIBLE);
        buttonBasket.setVisibility(View.VISIBLE);
        allCostBasket.setVisibility(View.VISIBLE);
        recyclerViewBasket.setLayoutManager(layoutManager);
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
         itemShopAdapter.removeItem(viewHolder.getAdapterPosition());
    }

    private void buttonListener() {
        buttonBasket.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        getFragmentManager().beginTransaction().replace(R.id.container_fragments, orderFragment).addToBackStack("hack").commit();

    }


    private void onCostBasket(List<ItemShop> itemShops){

          Observable<Integer> all_cost = RxViews.getAllCost(itemShops);
          all_cost.subscribe(integer -> allCostBasket.setText(String.valueOf(integer)), this::error);

    }

    private void error(Throwable throwable) {
    }


}
