package com.example.quyet.podomoro.adapters.viewHolders;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.example.quyet.podomoro.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by quyet on 2/11/2017.
 */

public class ColorTableViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    @BindView(R.id.iv_check)
    ImageView iv_check;
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

    }
    public  void setCheck(boolean check){
        if (check){
            iv_check.setVisibility(View.VISIBLE);
        }else{
            iv_check.setVisibility(View.INVISIBLE);
        }
    }
}
