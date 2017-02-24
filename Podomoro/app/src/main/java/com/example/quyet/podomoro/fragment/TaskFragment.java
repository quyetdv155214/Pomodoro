package com.example.quyet.podomoro.fragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.quyet.podomoro.R;
import com.example.quyet.podomoro.adapters.TaskAdapter;
import com.example.quyet.podomoro.databases.DBContext;
import com.example.quyet.podomoro.databases.TaskContext;
import com.example.quyet.podomoro.databases.models.Task;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaskFragment extends Fragment {
    private static final String TAG = "Task fragment";
    @BindView(R.id.rv_task)
    RecyclerView rvTask;
    private TaskAdapter taskAdapter;
    ProgressDialog myDialog;

    //    public static TaskFragment instance = new TaskFragment();
    public TaskFragment() {
        // Required empty public constructor
    }

    TaskFragmentListener taskFragmentListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        taskFragmentListener = (TaskFragmentListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_task, container, false);
        setupUI(view);
        addListener();
        ButterKnife.bind(this, view);
        return view;
    }

    private void setupUI(View view) {
        //
        ButterKnife.bind(this, view);
        //

        taskAdapter = new TaskAdapter();
        TaskContext.instance.getTaskFromServer();
        taskAdapter.notifyDataSetChanged();
        rvTask.setAdapter(taskAdapter);
        rvTask.setLayoutManager(new LinearLayoutManager(this.getContext()));

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.tasks);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this.getContext(), DividerItemDecoration.VERTICAL);
        rvTask.addItemDecoration(dividerItemDecoration);
        taskAdapter.setTaskLongClickListener(new TaskAdapter.TaskLongClickListener() {
            @Override
            public void onLongClick(final Task task) {

                final AlertDialog.Builder del = new AlertDialog.Builder(TaskFragment.this.getContext());
                del.setTitle(R.string.delete);
                del.setMessage(R.string.del_this_task);
                del.create();
                del.setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBContext.instance.deleteTask(task);
                        if (task.getLocal_id() != null)
                        TaskContext.instance.deleteTask(task);
                        else{
                            Toast.makeText(getContext(), "Can't delete this task\nThis task have null local id", Toast.LENGTH_SHORT).show();
                        }
                        taskAdapter.notifyDataSetChanged();
//                        Log.d(TAG, "onclick yes");
//                        dialog.cancel();
                    }
                });

                del.setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                del.show();

            }
        });

    }

    public void refresh() {
        taskAdapter.notifyDataSetChanged();
    }

    public void addListener() {
        taskAdapter.setTaskItemClickListener(new TaskAdapter.TaskItemClickListener() {
            @Override
            public void onItemClick(Task task) {
//                Log.d(TAG, String.format("onItemClick: %s", task));
                TaskDetailFragment taskDetailFragment = new TaskDetailFragment();
                taskDetailFragment.setTitle(getString(R.string.edit_task));
                //
//                Log.d(TAG, String.format("onItemClick: taskClicked %s ", task.toString()));
                taskDetailFragment.setTask(task);
                // replace fragment
                taskFragmentListener.onChangeFragment(taskDetailFragment, true);
            }
        });
        taskAdapter.setTaskTimerClickListener(new TaskAdapter.TaskTimerClickListener() {
            @Override
            public void onTimerClick(Task t) {
                TimerFragment timerFragment = new TimerFragment();
                timerFragment.setTitle(t.getName());
                taskFragmentListener.onChangeFragment(timerFragment, true);
            }
        });
    }

    @OnClick(R.id.fab)
    void onFabClick() {
        TaskDetailFragment taskDetailFragment = new TaskDetailFragment();
        // replace fragment
        taskFragmentListener.onChangeFragment(taskDetailFragment, true);
        // // TODO: 2/11/2017
        taskDetailFragment.setTitle("Add new task");
    }

}
