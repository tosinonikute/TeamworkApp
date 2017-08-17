package com.teamworkapp.ui.edittask;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.teamworkapp.BaseApplication;
import com.teamworkapp.R;
import com.teamworkapp.data.model.project.Project;
import com.teamworkapp.data.model.task.EditTask;
import com.teamworkapp.data.model.task.TaskUpdate;
import com.teamworkapp.data.model.task.TodoItem;
import com.teamworkapp.data.model.tasklist.Tasklist;
import com.teamworkapp.data.remote.TaskInterface;
import com.teamworkapp.di.component.TaskComponent;
import com.teamworkapp.ui.base.BaseActivity;
import com.teamworkapp.util.Logger;
import com.teamworkapp.util.NetworkUtil;

import java.util.ArrayList;
import java.util.Calendar;

import javax.inject.Inject;

import rx.subscriptions.CompositeSubscription;


public class EditTaskActivity extends BaseActivity implements EditTaskView {

    @Inject
    EditTaskPresenter presenter;

    @Inject
    TaskInterface taskInterface;

    private Logger logger = Logger.getLogger(getClass());
    private CompositeSubscription mCompositeSubscription;
    private LinearLayout mainLayout;
    private Snackbar snackbarOffline;
    private Snackbar msg;

    private SeekBar seekBar;
    private TextView seekbarPercentage;

    private DatePicker datePicker;
    private Calendar calendar;
    private TextView startDate;
    private TextView dueDate;
    private int year, month, day;
    private ArrayList<TodoItem> mTodoItem;
    private int position;
    private String postId;

    private TextView title, description, projectName, taskType, tags, seekPercentage, estimated, priority;

    private SeekBar progressSeekbar;
    private ArrayList<String> items = new ArrayList<String>();

    private ArrayList<String> taskItems = new ArrayList<String>();
    private ArrayList<Tasklist> taskItemList = new ArrayList<Tasklist>();

    private String projectId;
    private String taskListId;

    @Override
    protected void setupActivity(TaskComponent component, Bundle savedInstanceState) {
        setContentView(R.layout.activity_edit_task);
        ((BaseApplication) getApplication()).getComponent().inject(this);
        presenter.attachView(this);


        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mCompositeSubscription = new CompositeSubscription();
        init();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mTodoItem = (ArrayList<TodoItem>) getIntent().getSerializableExtra("mTodoItem");
            position = extras.getInt("position");
            setValues();
        }

        loadView();

    }


    // Initialize the view
    public void init() {

        presenter.getProjectList(taskInterface, mCompositeSubscription);

        mainLayout = (LinearLayout) findViewById(R.id.edit_layout);
        title = (TextView) findViewById(R.id.title);
        description = (TextView) findViewById(R.id.description);
        projectName = (TextView) findViewById(R.id.project_name);
        taskType = (TextView) findViewById(R.id.task_type);
        tags = (TextView) findViewById(R.id.tags_count);
        seekPercentage = (TextView) findViewById(R.id.seekbar_percentage);
        estimated = (TextView) findViewById(R.id.estimated);
        priority = (TextView) findViewById(R.id.priority);


        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        //showDate(year, month+1, day);

        startDate = (TextView) findViewById(R.id.start_date);
        dueDate = (TextView) findViewById(R.id.due_date);

        SwitchCompat switchCompat = (SwitchCompat) findViewById(R.id.notify_switch);

        seekbarPercentage = (TextView) findViewById(R.id.seekbar_percentage);
        seekBar = (SeekBar) findViewById(R.id.progress_seekbar );
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekbarPercentage.setText(progress + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });




    }


    public void setValues(){

        String ctitle = "";
        String cDesc = "";
        String cProjectName = "";
        String cTaskType = "";
        int cTags = 0;
        String cSeekPercentage = "";
        String cStartDate = "";
        String cDueDate = "";
        int cEstimated = 0;
        String cPriority = "";

        String formattedStartDate = "";
        String formattedDueDate = "";

        if(mTodoItem.get(position).getId() != null) postId = String.valueOf(mTodoItem.get(position).getId());
        if(mTodoItem.get(position).getContent() != null) ctitle = mTodoItem.get(position).getContent();
        if(mTodoItem.get(position).getDescription() != null) cDesc = mTodoItem.get(position).getDescription();
        if(mTodoItem.get(position).getProjectName() != null) cProjectName = mTodoItem.get(position).getProjectName();
        if(mTodoItem.get(position).getTodoListName() != null) cTaskType = mTodoItem.get(position).getTodoListName();

        if(mTodoItem.get(position).getTags() != null) cTags = mTodoItem.get(position).getTags().size();

        if(mTodoItem.get(position).getProgress() != null) cSeekPercentage = mTodoItem.get(position).getProgress() + cSeekPercentage;


        if(mTodoItem.get(position).getStartDate() != null){
            formattedStartDate = formatDateForward(mTodoItem.get(position).getStartDate());
        }

        if(mTodoItem.get(position).getDueDate() != null){
            formattedDueDate = formatDateForward(mTodoItem.get(position).getDueDate());
        }

        if(mTodoItem.get(position).getEstimatedMinutes() != null) cEstimated = mTodoItem.get(position).getEstimatedMinutes();
        if(mTodoItem.get(position).getPriority() != null) cPriority = mTodoItem.get(position).getPriority();


        title.setText(ctitle);
        description.setText(cDesc);
        projectName.setText(cProjectName);
        taskType.setText(cTaskType);
        tags.setText(String.valueOf(cTags) + " Tags");

        seekPercentage.setText(cSeekPercentage + "%");
        seekBar.setProgress(Integer.parseInt(cSeekPercentage));

        startDate.setText(formattedStartDate);
        dueDate.setText(formattedDueDate);
        estimated.setText(String.valueOf(cEstimated));
        priority.setText(cPriority);
        projectId = mTodoItem.get(position).getProjectId().toString();

    }


    public void updateTask(){

        EditTask ss = new EditTask();
        ss.setContent(title.getText().toString());
        ss.setDescription(description.getText().toString());
        ss.setDueDate(formatDateBackward(dueDate.getText().toString()));
        ss.setPriority(priority.getText().toString());
        ss.setProgress(seekbarPercentage.getText().toString().replace("%", ""));
        ss.setResponsiblePartyId("999");
        ss.setStartDate(formatDateBackward(startDate.getText().toString()));
        ss.setTags(tags.getText().toString());

        TaskUpdate tu = new TaskUpdate(ss);
        presenter.updateTaskList(taskInterface, tu, postId, getApplicationContext());

    }


    public void loadView(){
        if(NetworkUtil.isConnected(getApplicationContext())) {
            hideOfflineSnackBar();
        } else {
            displayOfflineSnackbar();
        }
    }

    public void setProjectName(ArrayList<Project> projectNames){
        for(int i=0; i<projectNames.size(); i++){
            items.add(projectNames.get(i).getName().toString());
        }
    }


    public void setTaskLists(ArrayList<Tasklist> taskListNames){
        taskItemList = taskListNames;
        for(int i=0; i<taskListNames.size(); i++){
            taskItems.add(taskListNames.get(i).getName().toString());
        }
    }

    public void setProject(View view){

        LayoutInflater inflater = LayoutInflater.from(EditTaskActivity.this);
        final View yourCustomView = inflater.inflate(R.layout.setreminder, null);

        AlertDialog dialog = new AlertDialog.Builder(EditTaskActivity.this)
                .setTitle("Select a Projects")
                .setView(yourCustomView)
                .setPositiveButton("SET PROJECT NAME", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        presenter.getTaskList(taskInterface, mCompositeSubscription, projectId);
                    }
                })
                .setNegativeButton("Cancel", null)

                .setSingleChoiceItems(items.toArray(new String[items.size()]), -1, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        Log.d("CustomDialog", String.valueOf(item));
                        projectName.setText(items.get(item).toString());
                    }
                })

                .create();
        dialog.show();

    }


    public void setTaskList(View view){

        if(projectName.getText().toString().equals(getResources().getString(R.string.project_name))){
            snackMsg("Please select a project first");
        }
        else if(taskItems.size() != 0) {
            LayoutInflater inflater = LayoutInflater.from(EditTaskActivity.this);
            final View yourCustomView = inflater.inflate(R.layout.setreminder, null);

            AlertDialog dialog = new AlertDialog.Builder(EditTaskActivity.this)
                    .setTitle("Select a Projects")
                    .setView(yourCustomView)
                    .setPositiveButton("SET TASK LIST", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            //
                        }
                    })
                    .setNegativeButton("Cancel", null)
                    .setSingleChoiceItems(taskItems.toArray(new String[taskItems.size()]), -1, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int item) {
                            Log.d("CustomDialog", String.valueOf(item));
                            taskType.setText(taskItems.get(item).toString());
                            taskListId = taskItemList.get(item).getId();
                        }
                    })
                    .create();
            dialog.show();
        } else {
            snackMsg("Please wait, loading task lists");
        }
    }

    public void setTags(View view){

    }

    public void setColumn(View view){

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


    public void setEstimated(View view){

    }

    public void setPriority(View view){

    }

    public String formatDateForward(String unformatedStr){
        String formatted = "";
        formatted = unformatedStr.substring(6);
        formatted = formatted + "/" + unformatedStr.substring(4,6);
        formatted = formatted + "/" + unformatedStr.substring(0,4);
        return formatted;
    }

    public String formatDateBackward(String unformatedStr){
        String formatted = "";
        unformatedStr = unformatedStr.replace("/","");
        formatted = unformatedStr.substring(4);
        formatted = formatted + unformatedStr.substring(2,4);
        formatted = formatted + unformatedStr.substring(0,2);
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
        inflater.inflate(R.menu.edit_task_menu, menu);
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
            case R.id.action_edit_task:
                updateTask();
        }
        return super.onOptionsItemSelected(item);
    }

}