package com.teamworkapp.ui.listtask;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.teamworkapp.BaseApplication;
import com.teamworkapp.R;
import com.teamworkapp.data.remote.TaskInterface;
import com.teamworkapp.di.component.TaskComponent;
import com.teamworkapp.ui.base.BaseActivity;
import com.teamworkapp.util.Logger;
import com.teamworkapp.util.ui.MaterialProgressBar;

import javax.inject.Inject;

public class ListTaskActivity extends BaseActivity implements ListTaskView {


    @Inject
    TaskInterface taskInterface;

    private Logger logger = Logger.getLogger(getClass());
    private LinearLayout mainLayout;

    private LinearLayoutManager layoutManager;
    private MaterialProgressBar progressBar;
    private Snackbar snackbarOffline;

    @Override
    protected void setupActivity(TaskComponent component, Bundle savedInstanceState) {
        setContentView(R.layout.activity_list_task);
        ((BaseApplication) getApplication()).getComponent().inject(this);


        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);


        init();
        loadView();

    }

    public void init(){
        progressBar = (MaterialProgressBar) findViewById(R.id.material_progress_bar);
        mainLayout = (LinearLayout) findViewById(R.id.main_layout);
        layoutManager = new LinearLayoutManager(getApplicationContext());
    }

    public void loadView(){

    }

    public void addNewTask(){

    }


    public void displayOfflineSnackbar() {
        snackbarOffline = Snackbar.make(mainLayout, R.string.no_connection_snackbar, Snackbar.LENGTH_INDEFINITE);
        TextView snackbarText = (TextView) snackbarOffline.getView().findViewById(android.support.design.R.id.snackbar_text);
        snackbarText.setTextColor(getApplicationContext().getResources().getColor(android.R.color.white));
        snackbarOffline.setAction(R.string.snackbar_action_retry, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadView();
            }
        });
        snackbarOffline.setActionTextColor(getResources().getColor(R.color.colorPrimary));
        snackbarOffline.show();
    }

    public void hideOfflineSnackBar() {
        if (snackbarOffline != null && snackbarOffline.isShown()) {
            snackbarOffline.dismiss();
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.list_task_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.action_new_task:
                addNewTask();
        }
        return super.onOptionsItemSelected(item);
    }
}
