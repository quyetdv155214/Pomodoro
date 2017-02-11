package com.example.quyet.podomoro.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quyet.podomoro.R;
import com.example.quyet.podomoro.adapters.viewHolders.TaskViewHolder;
import com.example.quyet.podomoro.databases.DBContext;
import com.example.quyet.podomoro.databases.models.Task;

/**
 * Created by quyet on 2/8/2017.
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskViewHolder> {
    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 1 : create view
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(
                R.layout.item_task,
                parent,
                false
        );
        // 2 : create View holder
        TaskViewHolder taskViewHolder = new TaskViewHolder(itemView);
        return taskViewHolder;
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {
        //1 : get data based on positon
        Task task = DBContext.instance.allTask().get(position);
        //2 : bind data into view
        holder.bind(task);
    }
    // lay so luong
    @Override
    public int getItemCount() {
        return DBContext.instance.allTask().size();
    }
}
