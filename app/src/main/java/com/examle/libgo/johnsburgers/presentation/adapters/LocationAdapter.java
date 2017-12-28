package com.examle.libgo.johnsburgers.presentation.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.examle.libgo.johnsburgers.R;
import com.examle.libgo.johnsburgers.data.pojos.Timing;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author libgo (08.12.2017)
 */

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.ViewHolder> {

    private List<Timing> times = new ArrayList<>();

    public void setTimes(List<Timing> times) {
        if(this.times.size() == 0) {
            this.times = times;
        }
        notifyDataSetChanged();
    }

    @Override
    public LocationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LocationAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.days_card, parent, false));
    }

    @Override
    public void onBindViewHolder(LocationAdapter.ViewHolder holder, int position) {
        Timing timingPosition = times.get(position);
        holder.textViewDay.setText(timingPosition.getDay());
        holder.textViewClock.setText(timingPosition.getTime());

    }

    @Override
    public int getItemCount() {
        if(times != null){
            return times.size();
        }
        return 0;
    }

     class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textViewDay)
         TextView textViewDay;
        @BindView(R.id.textViewClock)
        TextView textViewClock;

         ViewHolder(View itemView) {
             super(itemView);
            ButterKnife.bind(this, itemView);
         }
     }
}
