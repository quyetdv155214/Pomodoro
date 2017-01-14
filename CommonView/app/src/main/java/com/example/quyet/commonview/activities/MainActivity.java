package com.example.quyet.commonview.activities;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quyet.commonview.R;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.toString();
    private ImageView imvFeel;
    private Spinner sp_fruit;
    private EditText et_simple;
    private Button btn_test;
    private CheckBox chb_fa;
    private RadioButton rd_male;
    private RadioButton rd_female;
    private RadioButton rd_undefined;
    private RatingBar rtb_simple;
    private SeekBar sb_simple;
    private TextView tv_seekbar_progress;
    private SearchView sv_simple;
    private ProgressBar pb_simple;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getReferences();
        setupUI();
        addListener();
        et_simple.clearFocus();
    }

    private void getReferences() {
        imvFeel = (ImageView) findViewById(R.id.imv_feel);
        sp_fruit = (Spinner) findViewById(R.id.sp_fruit);
        et_simple = (EditText) findViewById(R.id.et_simple);
        btn_test = (Button) findViewById(R.id.btn_test);
        chb_fa = (CheckBox) findViewById(R.id.chb_fa);
        rd_male = (RadioButton) findViewById(R.id.rd_male);
        rd_female = (RadioButton) findViewById(R.id.rd_female);
        rd_undefined = (RadioButton) findViewById(R.id.rd_undefined);
        rtb_simple = (RatingBar) findViewById(R.id.rtb_simple);
        sb_simple = (SeekBar) findViewById(R.id.sb_simple);
        tv_seekbar_progress = (TextView) findViewById(R.id.tv_seekbar_progress);
        sv_simple = (SearchView) findViewById(R.id.sv_simple);
        pb_simple = (ProgressBar) findViewById(R.id.pb_simple);
    }

    private void setupUI() {
        imvFeel.setImageResource(R.drawable.up);
        // insert data to spinner
        String fruit[] = new String[]
                {
                        "Apple",
                        "orange",
                        "kiwi"
                };

        // Adapter
        ArrayAdapter<String> fruitArrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                fruit
        );

        sp_fruit.setAdapter(fruitArrayAdapter);
        //setSelection index
        sp_fruit.post(new Runnable() {
            @Override
            public void run() {
                sp_fruit.setSelection(2);
            }
        });
    }

    private void addListener() {
        sp_fruit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(TAG, "Spinner Selected " + i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Log.d(TAG, "Nothing Selected ");
            }
        });
        et_simple.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
                Log.d(TAG, String.format(" beforeTextChanged : CharSequence %s ,  start %s ,  count %s  ,after %s ", charSequence, start, count, after));
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                Log.d(TAG, String.format(" onTextChanged : CharSequence %s ,  start %s ,  count %s  ,after %s ", charSequence, start, before, count));

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Log.d(TAG, String.format(" afterTextChanged : %s ", editable));

            }
        });
        chb_fa.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.d(TAG, String.format("check box fa :  %s", chb_fa.isChecked()));
            }
        });
        sb_simple.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Log.d(TAG, String.format("seekbar onProgress : %s %s", i , b));
                tv_seekbar_progress.setText(String.format("%s/%s",  i, sb_simple.getMax()));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.d(TAG, "onStart Tracking Touch");

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d(TAG, String.format("onStop Tracking Touch", seekBar.getProgress()));
                tv_seekbar_progress.setText(String.format("%s/%s",  sb_simple.getProgress(), sb_simple.getMax()));
            }
        });

        sv_simple.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d(TAG, String.format("onQueryTextChange %s ", newText));
                return false;
            }
        });
        btn_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //read checkbox status

                Log.d(TAG, String.format("%s , %s", chb_fa.getId(), chb_fa.isChecked()));
                //
                chb_fa.setChecked(!chb_fa.isChecked());

                Toast.makeText(MainActivity.this, String.format("you are %s ", rd_male.isChecked() ? "male" : rd_female.isChecked() ? "female" : "undefined"), Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, String.format("rating is %s ", rtb_simple.getRating()), Toast.LENGTH_SHORT).show();
                // seekbar read
                Log.d(TAG, String.format("seekbar : ", sb_simple.getProgress()));
                sb_simple.setProgress(sb_simple.getProgress() + 10);

                sv_simple.clearFocus();
                sv_simple.setQuery("", false);
                sv_simple.setIconified(true);
                pb_simple.setProgress(pb_simple.getProgress()  + 10);
            }
        });
    }

}
