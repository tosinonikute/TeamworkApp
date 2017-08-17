package com.teamworkapp.ui.edittask;

import com.teamworkapp.data.model.project.Project;
import com.teamworkapp.data.model.tasklist.Tasklist;
import com.teamworkapp.ui.base.MvpView;

import java.util.ArrayList;

/**
 * @author Tosin Onikute.
 */

public interface EditTaskView extends MvpView {

    void init();

    void loadView();

    void setProjectName(ArrayList<Project> projectNames);

    void setTaskLists(ArrayList<Tasklist> taskListNames);

    void displayOfflineSnackbar();

    void hideOfflineSnackBar();

    void showLoading();

    void hideLoading();

}
