package com.example.quyet.podomoro.adapters.viewHolders;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.quyet.podomoro.R;
import com.example.quyet.podomoro.activities.TaskActivity;
import com.example.quyet.podomoro.databases.models.Task;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.ContentValues.TAG;

/**
 * Created by quyet on 2/8/2017.
 */

public class TaskViewHolder extends RecyclerView.ViewHolder{
    @BindView(R.id.v_task_color)
    View v_task_color;
    @BindView(R.id.tv_task_name)
    TextView tv_task_name;
    @BindView(R.id.bt_item_task)
    ImageButton bt_item_task;

    public TaskViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        //set on click
        bt_item_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: click task");

            }
        });
    }
    public void bind(Task task){

        // 1 : bind color
//        v_task_color.setBackgroundColor(Color.parseColor(task.getColor()));
        GradientDrawable gradientDrawable = (GradientDrawable) v_task_color.getBackground();
        gradientDrawable.setColor(Color.parseColor(task.getColor()));
        // 2 : bind name
         tv_task_name.setText(task.getName());

    }



}
