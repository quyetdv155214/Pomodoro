package com.example.quyet.podomoro.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.quyet.podomoro.R;
import com.example.quyet.podomoro.adapters.TaskAdapter;
import com.example.quyet.podomoro.databases.models.Task;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaskFragment extends Fragment implements FragmentListener {


    private static final String TAG = "Task fragment";
    @BindView(R.id.rv_task)
    RecyclerView rvTask;
    private TaskAdapter taskAdapter;

    public static TaskFragment instance = new TaskFragment();
    public TaskFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_task, container, false);
        setupUI(view);
        ButterKnife.bind(this,view);
        return  view;


    }


    private void setupUI(View view) {
        //

        ButterKnife.bind(this,view);
        taskAdapter = new TaskAdapter();
        rvTask.setAdapter(taskAdapter);
        rvTask.setLayoutManager(new LinearLayoutManager(this.getContext()));

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Task");
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this.getContext(), DividerItemDecoration.VERTICAL);
        rvTask.addItemDecoration(dividerItemDecoration);

        taskAdapter.setTaskItemClickListener(new TaskAdapter.TaskItemClickListener() {
            @Override
            public void onItemClick(Task task) {
                Log.d(TAG, String.format("onItemClick: %s", task));
                TaskDetailFragment taskDetailFragment = new TaskDetailFragment();
                replaceFragment(taskDetailFragment, true);
                // // TODO: 2/11/2017
                taskDetailFragment.setTitle("Edit");
                taskDetailFragment.setTask(task);
            }
        });
    }
    @OnClick(R.id.fab)
    void onFabClick(){
        TaskDetailFragment taskDetailFragment = new TaskDetailFragment();
        replaceFragment(taskDetailFragment, true);
        // // TODO: 2/11/2017
        taskDetailFragment.setTitle("add new Task");
    }
    @Override
    public void replaceFragment(Fragment fragment, boolean addToBackStack) {
        ManagerFragment mf  = new ManagerFragment(this.getActivity().getSupportFragmentManager(), R.id.fl_main);
        mf.replaceFragment(fragment, true);
    }
}
