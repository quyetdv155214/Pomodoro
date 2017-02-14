package com.example.quyet.podomoro.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
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
import com.example.quyet.podomoro.adapters.ColorTableAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaskDetailFragment extends Fragment implements FragmentListener{

    @BindView(R.id.rv_colors)
    RecyclerView rv_colors;
    @BindView(R.id.et_payment)
    EditText payment;
    public TaskDetailFragment() {
        // Required empty public constructor
        setHasOptionsMenu(true);
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
        ColorTableAdapter colorAdapter = new ColorTableAdapter();
        rv_colors.setAdapter(colorAdapter);
        rv_colors.setLayoutManager(new GridLayoutManager(this.getContext(),4));
        payment.setText("0.0");
        payment.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (payment.getText() == null){
                    payment.setText("0");
                }
            }
        });
        AppCompatActivity activity =  (AppCompatActivity) getActivity();
        activity.getSupportActionBar().setTitle("Create new task");
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
            inflater.inflate(R.menu.menu_edit_task, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this.getContext(), "Save", Toast.LENGTH_SHORT).show();
        TaskFragment taskFragment = new TaskFragment();
        // // TODO: 2/14/2017 pop back stack  
//        replaceFragment(taskFragment, false);

        return true;
    }

    @Override
    public void replaceFragment(Fragment fragment, boolean addToBackStack) {
            new ManagerFragment(this.getActivity().getSupportFragmentManager(),R.id.fl_main)
                    .replaceFragment(fragment,addToBackStack);

    }
}
