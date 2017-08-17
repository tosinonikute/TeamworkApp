package com.teamworkapp.ui.multipletask;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.teamworkapp.BaseApplication;
import com.teamworkapp.R;
import com.teamworkapp.data.model.project.Project;
import com.teamworkapp.data.model.task.MultipleTask;
import com.teamworkapp.data.model.task.TodoItem;
import com.teamworkapp.data.model.tasklist.Tasklist;
import com.teamworkapp.data.remote.TaskInterface;
import com.teamworkapp.di.component.TaskComponent;
import com.teamworkapp.ui.base.BaseActivity;
import com.teamworkapp.ui.listtask.ListTaskActivity;
import com.teamworkapp.util.Logger;
import com.teamworkapp.util.NetworkUtil;

import java.util.ArrayList;
import java.util.Calendar;

import javax.inject.Inject;

import rx.subscriptions.CompositeSubscription;

public class MultipleTaskActivity extends BaseActivity implements AddMultipleTaskView {


    @Inject
    AddMultipleTaskPresenter presenter;

    @Inject
    TaskInterface taskInterface;

    private Logger logger = Logger.getLogger(getClass());
    private CompositeSubscription mCompositeSubscription;
    private LinearLayout mainLayout;
    LinearLayout parentLayout;

    private Snackbar snackbarOffline;
    private Snackbar msg;
    private String postId;
    private Button button;

    private Calendar calendar;
    private TextView startDate;
    private TextView dueDate;
    private int year, month, day;

    private TextView title, description, projectName, taskType, tags, seekPercentage, estimated, priority;
    private SwitchCompat notify, privates;

    private ArrayList<String> projectItems = new ArrayList<String>();
    private ArrayList<String> projectIds = new ArrayList<String>();

    private ArrayList<String> taskItems = new ArrayList<String>();
    private ArrayList<Tasklist> taskItemList = new ArrayList<Tasklist>();
    private boolean initiateSetProject;
    private String taskListId;
    private String projectId;
    private String userId;


    @Override
    protected void setupActivity(TaskComponent component, Bundle savedInstanceState) {
        setContentView(R.layout.activity_multiple_task);
        ((BaseApplication) getApplication()).getComponent().inject(this);
        presenter.attachView(this);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mCompositeSubscription = new CompositeSubscription();


        init();
        loadView();
        addNewField();

    }


    // Initialize the view
    public void init() {


        mainLayout = (LinearLayout) findViewById(R.id.add_layout);
        projectName = (TextView) findViewById(R.id.project_name);
        taskType = (TextView) findViewById(R.id.task_type);
        notify = (SwitchCompat) findViewById(R.id.notify_switch);
        privates = (SwitchCompat) findViewById(R.id.privates_switch);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        startDate = (TextView) findViewById(R.id.start_date);
        dueDate = (TextView) findViewById(R.id.due_date);


    }


    public void loadView(){
        if(NetworkUtil.isConnected(getApplicationContext())) {
            hideOfflineSnackBar();
            getProjectList();
            getUserId();

        } else {
            displayOfflineSnackbar();
        }
    }


    public void getProjectList(){
        presenter.getProjectList(taskInterface, mCompositeSubscription);
    }


    public void getUserId(){
        presenter.getUserId(taskInterface, mCompositeSubscription);
    }

    public void setUserId(String userId){
        this.userId = userId;
    }


    public void setProjectName(ArrayList<Project> project){
        for(int i=0; i<project.size(); i++){
            projectItems.add(project.get(i).getName().toString());
            projectIds.add(project.get(i).getId().toString());
        }
    }

    public void setTaskLists(ArrayList<Tasklist> taskListNames){
        taskItemList = taskListNames;
        for(int i=0; i<taskListNames.size(); i++){
            taskItems.add(taskListNames.get(i).getName().toString());
        }
    }


    public void setProject(View view){

        LayoutInflater inflater = LayoutInflater.from(MultipleTaskActivity.this);
        AlertDialog dialog = new AlertDialog.Builder(MultipleTaskActivity.this)
                .setTitle(getString(R.string.select_project))
                .setPositiveButton(getString(R.string.set_proj_name), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        presenter.getTaskList(taskInterface, mCompositeSubscription, projectId);
                    }
                })
                .setNegativeButton("Cancel", null)

                .setSingleChoiceItems(projectItems.toArray(new String[projectItems.size()]), -1, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        logger.debug(projectItems.get(item).toString());
                        projectName.setText(projectItems.get(item).toString());
                        projectId = projectIds.get(item);
                        initiateSetProject = true;
                    }
                })

                .create();
        dialog.show();

    }


    public void setTaskList(View view){

        if(projectName.getText().toString().equals(getResources().getString(R.string.project_name))){
            snackMsg(getString(R.string.please_select_project_first));
        }
        else if(taskItems.size() != 0) {
            LayoutInflater inflater = LayoutInflater.from(MultipleTaskActivity.this);
            AlertDialog dialog = new AlertDialog.Builder(MultipleTaskActivity.this)
                    .setTitle(getString(R.string.select_tasklist))
                    .setPositiveButton(getString(R.string.set_task_list), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            //
                        }
                    })
                    .setNegativeButton("Cancel", null)
                    .setSingleChoiceItems(taskItems.toArray(new String[taskItems.size()]), -1, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int item) {
                            logger.debug(projectItems.get(item).toString());
                            taskType.setText(taskItems.get(item).toString());
                            taskListId = taskItemList.get(item).getId();
                        }
                    })
                    .create();
            dialog.show();

        } else if(!initiateSetProject) {
            snackMsg(getString(R.string.please_select_project_new));

        } else {
            snackMsg(getString(R.string.loading_task_lists));
        }
    }


    @SuppressWarnings("deprecation")
    public void setStartDate(View view) {
        showDialog(999);
    }

    @SuppressWarnings("deprecation")
    public void setDueDate(View view) {
        showDialog(888);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    startDateListener, year, month, day);
        }
        if (id == 888) {
            return new DatePickerDialog(this,
                    dueDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener startDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showStartDate(arg1, arg2+1, arg3);
                }
            };


    private DatePickerDialog.OnDateSetListener dueDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDueDate(arg1, arg2+1, arg3);
                }
            };


    private void showStartDate(int year, int month, int day) {
        startDate.setText(new StringBuilder().append(convert(day)).append("/")
                .append(convert(month)).append("/").append(year));
    }


    private void showDueDate(int year, int month, int day) {
        dueDate.setText(new StringBuilder().append(convert(day)).append("/")
                .append(convert(month)).append("/").append(year));
    }

    public String convert(int dates){
        String newDate = String.valueOf(dates);
        if(String.valueOf(dates).length() == 1) newDate = "0" + String.valueOf(dates);
        return newDate;
    }




    public void addNewField(){

        // Programmatically Adding EditText to LinearLayout and Styling it

        parentLayout = (LinearLayout) findViewById(R.id.parent_layout);

        button = (Button) findViewById(R.id.add_more_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final LinearLayout childlayout = new LinearLayout(getApplicationContext());
                childlayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                childlayout.setOrientation(LinearLayout.HORIZONTAL);

                LinearLayout.LayoutParams pp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                final EditText editText = new EditText(MultipleTaskActivity.this);
                editText.setLayoutParams(pp);
                editText.setHint(getString(R.string.task_title_field));
                editText.getBackground().setColorFilter(getResources().getColor(R.color.colorSeparator), PorterDuff.Mode.SRC_ATOP);
                childlayout.addView(editText);
                pp.weight = 2;

                LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                ImageView cancelImage = new ImageView(MultipleTaskActivity.this);
                cancelImage.setLayoutParams(p);
                cancelImage.setImageResource(R.drawable.ic_cancel);
                cancelImage.setPadding(5, 22, 5, 0);
                p.weight = 1;


                childlayout.addView(cancelImage);
                parentLayout.addView(childlayout);

                cancelImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        parentLayout.removeView(childlayout);
                    }
                });

            }
        });
    }


    public void newTask(){

        if(validation()) {

            // Retrieve all task titles in EditText and concatenate them
            String taskTitles = getTitles();

            MultipleTask multipleTask = new MultipleTask();
            multipleTask.setContent(taskTitles);

            // set user id
            long creatorId = (long) Integer.parseInt(userId);
            multipleTask.setCreatorId(creatorId);

            // Set notify and Privates SwitchCompact values when checked or Not
            multipleTask.setNotify(notify.isChecked());
            multipleTask.setPrivate(privates.isChecked());


            TodoItem todoItem = new TodoItem();
            todoItem.setStartDate(formatDateBackward(startDate.getText().toString()));
            todoItem.setDueDate(formatDateBackward(dueDate.getText().toString()));

            multipleTask.setTodoItem(todoItem);

            String postId = taskListId;
            presenter.addTaskList(taskInterface, multipleTask, postId, getApplicationContext());

        }

    }


    public String getTitles(){

        StringBuilder sb = new StringBuilder();
        EditText initialText = (EditText) findViewById(R.id.edittext_task_title);
        sb.append(initialText.getText().toString() + "\n");

        int count = parentLayout.getChildCount();
        for(int i =0;i<count;i++) {
            View view = parentLayout.getChildAt(i);
            if(view instanceof LinearLayout) {

                View innerView = ((LinearLayout) view).getChildAt(0);
                if (innerView instanceof EditText) {

                    // Retrieve all task titles in EditText and concatenate them
                    EditText editText = (EditText) innerView;
                    sb.append(editText.getText().toString() + "\n");
                }
            }
        }

        return sb.toString();
    }



    public boolean validation(){

        EditText title = (EditText) findViewById(R.id.edittext_task_title);
        if(title.getText().toString().equals("")){
            snackMsg(getString(R.string.enter_title));

        } else if(projectName.getText().toString().equals(getString(R.string.project_name))){
            snackMsg(getString(R.string.enter_project));

        } else if(taskType.getText().toString().equals(getString(R.string.task_list))){
            snackMsg(getString(R.string.enter_task));

        } else if(startDate.getText().toString().equals(getString(R.string.start_date))){
            snackMsg(getString(R.string.enter_start_date));

        } else if(dueDate.getText().toString().equals(getString(R.string.due_date))){
            snackMsg(getString(R.string.enter_due_date));

        } else {
            return true;
        }
        return false;
    }


    public String formatDateForward(String unformatedStr){
        String formatted = "";
        if(unformatedStr.length() > 5) {
            formatted = unformatedStr.substring(6);
            formatted = formatted + "/" + unformatedStr.substring(4, 6);
            formatted = formatted + "/" + unformatedStr.substring(0, 4);
        }
        return formatted;
    }

    public String formatDateBackward(String unformatedStr){
        String formatted = "";
        if(unformatedStr.length() > 5) {
            unformatedStr = unformatedStr.replace("/", "");
            formatted = unformatedStr.substring(4);
            formatted = formatted + unformatedStr.substring(2, 4);
            formatted = formatted + unformatedStr.substring(0, 2);
        }
        return formatted;
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


    public void snackMsg(String message) {
        msg = Snackbar.make(mainLayout, message, Snackbar.LENGTH_SHORT);
        TextView snackbarText = (TextView) msg.getView().findViewById(android.support.design.R.id.snackbar_text);
        snackbarText.setTextColor(getApplicationContext().getResources().getColor(android.R.color.white));
        msg.show();
    }


    public void showLoading(){

    }

    public void hideLoading(){

    }

    public void openListActivity(){
        Intent intent = new Intent(getApplicationContext(), ListTaskActivity.class);
        startActivity(intent);
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
        inflater.inflate(R.menu.add_task_menu, menu);
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
            case R.id.action_save_task:
                newTask();
        }
        return super.onOptionsItemSelected(item);
    }



}
