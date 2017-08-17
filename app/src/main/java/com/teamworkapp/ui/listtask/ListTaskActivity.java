package com.teamworkapp.ui.listtask;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.teamworkapp.BaseApplication;
import com.teamworkapp.R;
import com.teamworkapp.data.model.task.TodoItem;
import com.teamworkapp.data.remote.TaskInterface;
import com.teamworkapp.di.component.TaskComponent;
import com.teamworkapp.ui.base.BaseActivity;
import com.teamworkapp.ui.multipletask.MultipleTaskActivity;
import com.teamworkapp.util.Logger;
import com.teamworkapp.util.NetworkUtil;
import com.teamworkapp.util.ui.MaterialProgressBar;

import java.util.ArrayList;

import javax.inject.Inject;

import rx.subscriptions.CompositeSubscription;

public class ListTaskActivity extends BaseActivity implements ListTaskView {

    @Inject
    ListTaskPresenter presenter;

    @Inject
    TaskInterface taskInterface;

    private Logger logger = Logger.getLogger(getClass());
    private CompositeSubscription mCompositeSubscription;
    private LinearLayout mainLayout;
    private TaskListAdapter adapter;
    private RecyclerView recyclerView;
    private MaterialProgressBar progressBar;
    private LinearLayoutManager layoutManager;
    private Snackbar snackbarOffline;


    @Override
    protected void setupActivity(TaskComponent component, Bundle savedInstanceState) {
        setContentView(R.layout.activity_list_task);
        ((BaseApplication) getApplication()).getComponent().inject(this);
        presenter.attachView(this);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        mCompositeSubscription = new CompositeSubscription();
        init();
        loadView();


    }

    // Initialize the view
    public void init() {
        progressBar = (MaterialProgressBar) findViewById(R.id.material_progress_bar);
        mainLayout = (LinearLayout) findViewById(R.id.main_layout);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView = (RecyclerView) findViewById(R.id.task_list_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
    }

    public void loadView(){
        if(NetworkUtil.isConnected(getApplicationContext())) {
            taskList();
            hideOfflineSnackBar();
        } else {
            displayOfflineSnackbar();
        }
    }

    public void taskList(){
        presenter.getTaskList(taskInterface, mCompositeSubscription);
    }

    public void setAdapter(ArrayList<TodoItem> todoItemsList){
        if(todoItemsList.size() > 0) {
            adapter = new TaskListAdapter(getApplicationContext(), todoItemsList);
            recyclerView.setAdapter(adapter); // set adapter on recyclerview
        }
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
    public void showLoading(){
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading(){
        progressBar.setVisibility(View.GONE);
    }

    public void addNewTask(){
        if(NetworkUtil.isConnected(getApplicationContext())) {
            Intent intent = new Intent(this, MultipleTaskActivity.class);
            startActivity(intent);
        } else {
            displayOfflineSnackbar();
        }
    }

    @Override
    protected void onDestroy() {
        if (mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
        super.onDestroy();
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
