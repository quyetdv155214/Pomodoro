package com.example.quyet.podomoro.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quyet.podomoro.R;
import com.example.quyet.podomoro.activities.TaskActivity;
import com.example.quyet.podomoro.adapters.TaskColorAdapter;
import com.example.quyet.podomoro.databases.DBContext;
import com.example.quyet.podomoro.databases.models.Task;
import com.example.quyet.podomoro.decoration.TaskColorDecor;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaskDetailFragment extends Fragment implements FragmentListener{

    @BindView(R.id.rv_colors)
    RecyclerView rv_colors;

    @BindView(R.id.et_name)
    EditText et_name;
    @BindView(R.id.et_payment)
    EditText payment;
    TaskColorAdapter colorAdapter;
    private String title;
    private Task task;
    public TaskDetailFragment() {
        // Required empty public constructor
        setHasOptionsMenu(true);
    }

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
        return view;
    }

    private void setupUI(View view) {
        ButterKnife.bind(this,view);

        //set layout managet
        rv_colors.setLayoutManager(new GridLayoutManager(this.getContext(),4));
        // setAdapter
        colorAdapter= new TaskColorAdapter();
        rv_colors.setAdapter(colorAdapter);
        // add decoration
        rv_colors.addItemDecoration(new TaskColorDecor());
        //

        // set title
        if(getActivity() instanceof  TaskActivity){
           ((TaskActivity) getActivity()).getSupportActionBar().setTitle(title);
        }
        if (task != null){
            et_name.setText(task.getName());
            payment.setText(String.format("%.1f", task.getPayment_per_hour()));
            colorAdapter.setSelectedColor(task.getColor());
        }


    }
    private  void addListener(){
        payment.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (payment.getText() == null){
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
        if (item.getItemId() == R.id.menu_item)
        {
            if(true){
                Toast.makeText(this.getContext(), "Save", Toast.LENGTH_SHORT).show();
                //1 : get data from UI
                String taskName = et_name.getText().toString();
                double paymentPerHour = Float.parseFloat(payment.getText().toString());
                String color = colorAdapter.getSelectedColor();

                // 2 : Create new Task
                Task newTask = new Task(taskName, color, paymentPerHour);
                // 3 : add to database
                DBContext.instance.addTask(newTask);
            }

        }
        getActivity().onBackPressed();
        return false;
    }

    @Override
    public void replaceFragment(Fragment fragment, boolean addToBackStack) {
            new ManagerFragment(this.getActivity().getSupportFragmentManager(),R.id.fl_main)
                    .replaceFragment(fragment,addToBackStack);

    }


}
