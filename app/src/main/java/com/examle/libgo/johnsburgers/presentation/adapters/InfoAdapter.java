package com.examle.libgo.johnsburgers.presentation.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.examle.libgo.johnsburgers.R;
import com.examle.libgo.johnsburgers.data.pojos.News;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by libgo on 03.12.2017.
 */

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.ViewHolder> {

    private List<News> dataList;

    public InfoAdapter (List<News> newsList){
        this.dataList = newsList;
    }

    @Override
    public InfoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_card, parent, false));
    }

    @Override
    public void onBindViewHolder(InfoAdapter.ViewHolder holder, int position) {
        News news = dataList.get(position);

        holder.itemView.setImageURI(news.getScrNews());
        if(news.getColor()){
            holder.view.setVisibility(View.VISIBLE);
        }
        holder.nameNews.setText(news.getTextNews());
        holder.textNameNews.setText(news.getNameNews());
    }

    @Override
    public int getItemCount() {
        if(dataList != null) {
            return dataList.size();
        }
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_image)
        SimpleDraweeView itemView;
        @BindView(R.id.textNameNews)
        TextView textNameNews;
        @BindView(R.id.textNews)
        TextView nameNews;
        @BindView(R.id.colorView)
        View view;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
