package com.example.quyet.podomoro.adapters.viewHolders;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;

import com.example.quyet.podomoro.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by quyet on 2/11/2017.
 */

public class ColorTableViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    @BindView(R.id.cb_color)
    CheckBox cb_color;
    @BindView(R.id.v_color)
    View v_color;

    public ColorTableViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
    public void bind(com.example.quyet.podomoro.databases.models.Color color){
        GradientDrawable gradientDrawable = (GradientDrawable) v_color.getBackground();
        gradientDrawable.setColor(Color.parseColor(color.getColor()));
    }
    @Override
    public void onClick(View view) {
        if (cb_color.isChecked()){
            cb_color.setChecked(false);
        }else{
            cb_color.setChecked(true);
        }
    }
}
