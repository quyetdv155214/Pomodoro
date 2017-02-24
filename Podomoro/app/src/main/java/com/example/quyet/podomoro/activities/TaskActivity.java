package com.example.quyet.podomoro.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.example.quyet.podomoro.R;
import com.example.quyet.podomoro.databases.TaskContext;
import com.example.quyet.podomoro.fragment.TaskFragment;
import com.example.quyet.podomoro.fragment.TaskFragmentListener;
import butterknife.ButterKnife;

public class TaskActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener , TaskFragmentListener
{
    static TaskFragment taskFragment;
    private static final String TAG = "task activity";
    ActionBarDrawerToggle toggle;
    ProgressDialog myDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        // Todo : Move
        //
        ButterKnife.bind(this);
        //
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //
        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    // set toggle icon arrow
                    toggle.setDrawerIndicatorEnabled(false);
                    toggle.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24px);
                    toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            onBackPressed();
                        }
                    });
                }else{
                    toggle.setDrawerIndicatorEnabled(true);
                    toggle.setToolbarNavigationClickListener(null);
                }
            }
        });
        setupUI();
        // change fragment
         taskFragment = new TaskFragment();
        onChangeFragment(taskFragment, false);
//        setupUI();
    }
    public static TaskFragment getTaskFragment(){
        return taskFragment;
    }

    public void setupUI(){
        myDialog = new ProgressDialog(this);
        myDialog.setMessage("Load Data...");
        myDialog.setCancelable(false);
        myDialog.onStart();
        boolean taskFromServer = TaskContext.instance.getTaskFromServer();
        if (taskFromServer == true){
            myDialog.dismiss();
        }


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.task, menu);
//        return true;
        return  false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            gotoSettingActivity();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void gotoSettingActivity() {
        try {
            Intent intent = new Intent(this, SettingActivity.class);
            this.startActivity(intent);
        } catch (Exception e) {


        }

    }

    private void gotoColorActivity() {
        Intent intent = new Intent(this, ColorActivity.class);
        this.startActivity(intent);
    }

    @Override
    public void onChangeFragment(Fragment frag, boolean addToBackStack) {
        if (addToBackStack)
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl_main, frag)
                    .addToBackStack(null)
                    .commit();
        else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl_main, frag)
                    .commit();
        }
    }
}
