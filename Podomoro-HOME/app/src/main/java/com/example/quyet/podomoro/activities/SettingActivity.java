package com.example.quyet.podomoro.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.quyet.podomoro.R;
import com.example.quyet.podomoro.settings.Setting;
import com.example.quyet.podomoro.settings.Sharepref;

import static com.example.quyet.podomoro.settings.Setting.DEFAULT_BREAKTIME;
import static com.example.quyet.podomoro.settings.Setting.DEFAULT_COUT_TO_LONG_BREAK;
import static com.example.quyet.podomoro.settings.Setting.DEFAULT_LONG_BREAKTIME;
import static com.example.quyet.podomoro.settings.Setting.DEFAUL_WORKTIME;

public class SettingActivity extends AppCompatActivity {

    private TextView tv_workTime;
    private TextView tv_breakTime;
    private TextView tv_longBreak;
    private SeekBar sb_workTime;
    private SeekBar sb_breakTime;
    private SeekBar sb_longBreak;
    private Sharepref sharepref = Sharepref.getInstance();
    private Spinner spinner;
    private Button bt_reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getReferences();
        addListener();
        setupUI();
    }

    private void getReferences() {
        tv_breakTime = (TextView) findViewById(R.id.tv_breakTime);
        tv_workTime = (TextView) findViewById(R.id.tv_workTime);
        tv_longBreak = (TextView) findViewById(R.id.tv_longBreak);
        sb_breakTime = (SeekBar) findViewById(R.id.sb_breakTime);
        sb_longBreak = (SeekBar) findViewById(R.id.sb_longBreak);
        sb_workTime = (SeekBar) findViewById(R.id.sb_workTime);
        spinner = (Spinner) findViewById(R.id.sp_count);
        bt_reset = (Button) findViewById(R.id.bt_reset);
    }

    private void setupUI() {
        Setting setting = sharepref.getSetting();
        if (setting == null) {
            setting = new Setting(DEFAUL_WORKTIME, DEFAULT_BREAKTIME,
                    DEFAULT_LONG_BREAKTIME, DEFAULT_COUT_TO_LONG_BREAK);
            sharepref.putSetting(setting);
            sb_workTime.setProgress(DEFAUL_WORKTIME);
            sb_breakTime.setProgress(DEFAULT_BREAKTIME);
            sb_longBreak.setProgress(DEFAULT_LONG_BREAKTIME);
            spinner.post(new Runnable() {
                @Override
                public void run() {
                    spinner.setSelection(DEFAULT_COUT_TO_LONG_BREAK-1);
                }
            });
        } else {
            sb_workTime.setProgress(setting.getWorkTime());
            sb_breakTime.setProgress(setting.getBreakTime());
            sb_longBreak.setProgress(setting.getLongBreakTime());
            final int count = setting.getLongBreakAffter();
            spinner.post(new Runnable() {
                @Override
                public void run() {
                    spinner.setSelection(count-1);
                }
            });

        }

        Integer count[] = new Integer[]
                {
                      1,2,3,4,5,6,7,8,9,10
                };

        // Adapter
        ArrayAdapter<Integer> fruitArrayAdapter = new ArrayAdapter<Integer>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                count
        );

        spinner.setAdapter(fruitArrayAdapter);
        //setSelection index


    }


    private void addListener() {
        bt_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharepref.putSetting(null);
                setupUI();
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Setting setting = sharepref.getSetting();
                setting.setLongBreakAffter(i+1);
                sharepref.putSetting(setting);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        sb_workTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tv_workTime.setText(String.format("%s min", sb_workTime.getProgress()));
                Setting setting = sharepref.getSetting();
                setting.setWorkTime(sb_workTime.getProgress());
                sharepref.putSetting(setting);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        sb_breakTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tv_breakTime.setText(String.format("%s min", sb_breakTime.getProgress()));
                Setting setting = sharepref.getSetting();
                setting.setBreakTime(sb_breakTime.getProgress());
                sharepref.putSetting(setting);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        sb_longBreak.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tv_longBreak.setText(String.format("%s min", sb_longBreak.getProgress()));
                Setting setting = sharepref.getSetting();
                setting.setLongBreakTime(sb_longBreak.getProgress());
                sharepref.putSetting(setting);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


}
