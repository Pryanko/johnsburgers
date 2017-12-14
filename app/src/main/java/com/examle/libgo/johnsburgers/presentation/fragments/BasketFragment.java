package com.examle.libgo.johnsburgers.presentation.fragments;

import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.examle.libgo.johnsburgers.R;
import com.examle.libgo.johnsburgers.data.pojos.ItemShop;
import com.examle.libgo.johnsburgers.presentation.adapters.ItemShopAdapter;
import com.examle.libgo.johnsburgers.tools.RecyclerItemTouchHelper;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmChangeListener;

/**
 * Created by libgo on 03.12.2017.
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


    private LinearLayoutManager layoutManager;
    private ItemShopAdapter itemShopAdapter;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_basket, container, false);
        ButterKnife.bind(this, view);

        Realm realm = Realm.getDefaultInstance();
        buttonListener();
        itemShopAdapter = new ItemShopAdapter(realm.where(ItemShop.class).findAll());
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerViewBasket.setAdapter(itemShopAdapter);
        ItemTouchHelper.SimpleCallback itemTouchHelper = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelper).attachToRecyclerView(recyclerViewBasket);
        if(realm.where(ItemShop.class).findAll().size() == 0){
           // recyclerViewBasket.setVisibility(View.INVISIBLE);
            viewBasket.setVisibility(View.INVISIBLE);
            textViewOrder.setVisibility(View.INVISIBLE);
            buttonBasket.setVisibility(View.INVISIBLE);

        }
        else {
            changeView();
        }



        realm.addChangeListener(new RealmChangeListener<Realm>() {
            @Override
            public void onChange(Realm realm) {
                if(realm.where(ItemShop.class).findAll().size() != 0) {
                    changeView();
                    itemShopAdapter.notifyDataSetChanged();
                    recyclerViewBasket.smoothScrollToPosition(recyclerViewBasket.getAdapter().getItemCount() - 1);
                }
                else {
                    item_basket_image.setVisibility(View.VISIBLE);
                    textNullBasket.setVisibility(View.VISIBLE);
                    viewBasket.setVisibility(View.INVISIBLE);
                    textViewOrder.setVisibility(View.INVISIBLE);
                    buttonBasket.setVisibility(View.INVISIBLE);

                }


            }
        });

        return view;
    }


    protected void changeView(){
        item_basket_image.setVisibility(View.INVISIBLE);
        textNullBasket.setVisibility(View.INVISIBLE);
        //recyclerViewBasket.setVisibility(View.VISIBLE);
        viewBasket.setVisibility(View.VISIBLE);
        textViewOrder.setVisibility(View.VISIBLE);
        buttonBasket.setVisibility(View.VISIBLE);
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
        buttonBasket.setVisibility(View.INVISIBLE);
        getChildFragmentManager().beginTransaction().replace(R.id.order_container, new OrderFragment()).commit();

    }
}
