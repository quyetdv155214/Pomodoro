package com.example.quyet.podomoro.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.quyet.podomoro.R;
import com.example.quyet.podomoro.adapters.ColorTableAdapter;
import com.example.quyet.podomoro.adapters.TaskAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ColorActivity extends AppCompatActivity {
//    @BindView(R.id.rv_color_Choose)
//    RecyclerView rv_color_Choose;
    RecyclerView rv_color_choose;
    ColorTableAdapter  colorTableAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);
//        ButterKnife.bind(this);
        setupUI();
    }

    private void setupUI() {
        rv_color_choose = (RecyclerView) findViewById(R.id.rv_color_choose);
        colorTableAdapter = new ColorTableAdapter();
        rv_color_choose.setAdapter(colorTableAdapter);
        rv_color_choose.setLayoutManager(new GridLayoutManager(this, 4));
    }

}
