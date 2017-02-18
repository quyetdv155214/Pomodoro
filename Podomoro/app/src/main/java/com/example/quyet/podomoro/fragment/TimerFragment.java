package com.example.quyet.podomoro.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quyet.podomoro.R;
import com.example.quyet.podomoro.activities.TaskActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimerFragment extends Fragment {

private String title;

    public void setTitle(String title) {
        this.title = title;
    }

    public TimerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

         View view =inflater.inflate(R.layout.fragment_timer, container, false);
        if(getActivity() instanceof TaskActivity){
            ((TaskActivity) getActivity()).getSupportActionBar().setTitle(title);
        }
        return  view;
    }

}
