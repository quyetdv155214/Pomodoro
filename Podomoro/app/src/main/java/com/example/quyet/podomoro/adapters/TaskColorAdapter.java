package com.example.quyet.podomoro.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quyet.podomoro.R;
import com.example.quyet.podomoro.adapters.viewHolders.ColorTableViewHolder;
import com.example.quyet.podomoro.databases.DBContext;
import com.example.quyet.podomoro.databases.models.Color;
import com.example.quyet.podomoro.databases.models.Task;

import static android.content.ContentValues.TAG;

/**
 * Created by quyet on 2/11/2017.
 */

public class TaskColorAdapter extends RecyclerView.Adapter<ColorTableViewHolder>{
    private int selectedPosition;
    @Override
    public ColorTableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //lay layout inflater
        LayoutInflater layoutInflater  = LayoutInflater.from(parent.getContext());
        // cang view
        View itemView  = layoutInflater.inflate(
                R.layout.item_color,
                parent,
                false
        );

        ColorTableViewHolder colorTableViewHolder = new ColorTableViewHolder(itemView);
        return colorTableViewHolder;
    }

    @Override
    public void onBindViewHolder(ColorTableViewHolder holder, final int position) {
        Color color = DBContext.instance.allColor().get(position);
        holder.bind(color);
        if(selectedPosition == position){
            holder.setCheck(true);
        }else{
            holder.setCheck(false);
        }
        // bat su kien onclick item
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPosition = position;
                Log.d(TAG, "onClick : item position "+ selectedPosition);
                notifyDataSetChanged();
            }
        });



    }

    @Override
    public int getItemCount() {

        return DBContext.instance.allColor().size();
    }
    public void setSelectedColor(String color)
    {
        selectedPosition = 0;
        for (int colorIndex =0; colorIndex <Color.COLORS.length; colorIndex++)
        {
            if (Color.COLORS[colorIndex].equalsIgnoreCase(color)){
                selectedPosition = colorIndex;
                break;
            }
        }
        notifyDataSetChanged();
    }
    public String getSelectedColor(){
        return Color.COLORS[selectedPosition];
    }
}
