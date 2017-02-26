package com.example.quyet.podomoro.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quyet.podomoro.R;
import com.example.quyet.podomoro.databases.DBContext;
import com.example.quyet.podomoro.databases.TaskManager;
import com.example.quyet.podomoro.databases.models.Task;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoadingFragment extends Fragment {


    private static final String TAG = "Loading fragment";

    public LoadingFragment() {
        // Required empty public constructor
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        taskFragmentListener = (TaskFragmentListener) context;
    }

    TaskFragmentListener taskFragmentListener;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_loading, container, false);
        loadData();
        return view;
    }

    public  void loadData(){
        TaskManager.instance.getTaskFromServer();
        TaskManager.instance.setGetTasksListener(new TaskManager.GetTasksListener() {
            @Override
            public void onGetAllTask(boolean ok) {
                Log.d(TAG, "onGetAllTask: ");
//                List<Task> tasks = DBContext.instance.allTask();
//                for (Task t :
//                        tasks) {
//                    Log.d(TAG, String.format("onGetAllTask: %s", t.toString()));
//                }
                TaskFragment taskFragment = new TaskFragment();
                taskFragmentListener.onChangeFragment(taskFragment, false);
            }
        });
    }

}
