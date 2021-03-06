package com.example.quyet.podomoro.fragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.quyet.podomoro.R;
import com.example.quyet.podomoro.activities.TaskActivity;
import com.example.quyet.podomoro.adapters.TaskAdapter;
import com.example.quyet.podomoro.adapters.TaskColorAdapter;
import com.example.quyet.podomoro.databases.DBContext;
import com.example.quyet.podomoro.databases.TaskManager;
import com.example.quyet.podomoro.databases.models.Task;
import com.example.quyet.podomoro.decoration.TaskColorDecor;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaskDetailFragment extends Fragment {

    private static final String TAG = TaskDetailFragment.class.toString();
    @BindView(R.id.rv_colors)
    RecyclerView rv_colors;
    @BindView(R.id.sw_isDone)
    Switch sw_isDone;
    @BindView(R.id.et_name)
    EditText et_name;
    @BindView(R.id.et_payment)
    EditText payment;
    @BindView(R.id.til_name)
    TextInputLayout tilName;
    @BindView(R.id.til_payment)
    TextInputLayout tilPayment;
    TaskColorAdapter colorAdapter;
    private String title;
    private Task task;


    public TaskDetailFragment() {
        // Required empty public constructor
        setHasOptionsMenu(true);
    }
    TaskFragmentListener taskFragmentListener;


    public void setTask(Task task) {
        this.task = task;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_task_detail, container, false);
        setupUI(view);
        addListener();
        return view;
    }


    private void setupUI(View view) {
        ButterKnife.bind(this, view);
        //set layout managet
        rv_colors.setLayoutManager(new GridLayoutManager(this.getContext(), 4));
        // setAdapter
        colorAdapter = new TaskColorAdapter();
        rv_colors.setAdapter(colorAdapter);
        // add decoration
        rv_colors.addItemDecoration(new TaskColorDecor());
        // set title
        if (getActivity() instanceof TaskActivity) {
            ((TaskActivity) getActivity()).getSupportActionBar().setTitle(title);
        }
        if (task != null) {
            et_name.setText(task.getName());
            payment.setText(String.format("%s", task.getPayment_per_hour()));
            colorAdapter.setSelectedColor(task.getColor());
            if (task.isDone()) {
                sw_isDone.setChecked(true);
            } else {
                sw_isDone.setChecked(false);
            }
        }


    }

    private void addListener() {
        payment.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (payment.getText() == null) {
                    payment.setText("0");
                }
            }
        });
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_edit_task, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_item) {
            View view = this.getActivity().getCurrentFocus();
            //
            if (view != null) {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
            //1 : get data from UI
            String taskName = et_name.getText().toString();
            ////validate input
            try {
                validateTaskName(taskName);
            } catch (Exception e) {
                tilName.setError(e.getMessage());
                return false;
            }
            try {
                validatePayment(payment.getText().toString());
            } catch (Exception e) {
                tilPayment.setError(e.getMessage());
                return false;
            }
            float paymentPerHour = Float.parseFloat(payment.getText().toString());
            String color = colorAdapter.getSelectedColor();
            boolean isDone = sw_isDone.isChecked();
            Log.d(TAG, String.format("onOptionsItemSelected: %s", isDone));
            Toast.makeText(this.getContext(), R.string.saved, Toast.LENGTH_SHORT).show();
            // 2 : Create new Task
             Task newTask = new Task(taskName, color, paymentPerHour, isDone, "", "");



            if (task == null) {
                // 3 : add to database
                DBContext.instance.addTask(newTask);
                TaskManager.instance.addNewTask(newTask);
                getActivity().onBackPressed();

            } else {
                final ProgressDialog myDialog = new ProgressDialog(this.getActivity());
                myDialog.setMessage("Waitting...");
                myDialog.setCancelable(false);
                myDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        getActivity().onBackPressed();
                    }
                });
                myDialog.show();

                Task temp = newTask;
                newTask.setLocal_id(task.getLocal_id());
                newTask.setId(task.getId());
                DBContext.instance.editTask(newTask);
                TaskManager.instance.editTask(newTask);
                TaskManager.instance.setEditTaskListener(new TaskManager.EditTaskListener() {
                    @Override
                    public void onEditTask(boolean ok) {
                        if(ok){
                            myDialog.dismiss();
                            getActivity().onBackPressed();
                        }


                    }
                });

            }
        }

//        taskFragmentListener.onChangeFragment(new TaskFragment(), false);


        return false;
    }

    public void validateTaskName(String taskName) throws Exception {
        if (taskName.isEmpty()) {
            throw new Exception("Enter task name");
        }
    }

    public void validatePayment(String payment) throws Exception {

        if (payment.isEmpty()) {
            throw new Exception("Enter payment per hour");
        }
        try {
            float paymentPerHour = Float.parseFloat(payment);

        } catch (Exception e) {
            throw new Exception("Wrong format");
        }
    }


}
