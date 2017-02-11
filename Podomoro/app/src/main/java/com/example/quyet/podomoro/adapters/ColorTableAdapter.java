package com.example.quyet.podomoro.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quyet.podomoro.R;
import com.example.quyet.podomoro.adapters.viewHolders.ColorTableViewHolder;
import com.example.quyet.podomoro.databases.DBContext;
import com.example.quyet.podomoro.databases.models.Color;

/**
 * Created by quyet on 2/11/2017.
 */

public class ColorTableAdapter extends RecyclerView.Adapter<ColorTableViewHolder>{

    @Override
    public ColorTableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater  = LayoutInflater.from(parent.getContext());
        View itemView  = layoutInflater.inflate(
                R.layout.item_color,
                parent,
                false
        );

        ColorTableViewHolder colorTableViewHolder = new ColorTableViewHolder(itemView);
        return colorTableViewHolder;
    }

    @Override
    public void onBindViewHolder(ColorTableViewHolder holder, int position) {
        Color color = DBContext.instance.allColor().get(position);
        holder.bind(color);
    }

    @Override
    public int getItemCount() {

        return DBContext.instance.allColor().size();
    }
}
