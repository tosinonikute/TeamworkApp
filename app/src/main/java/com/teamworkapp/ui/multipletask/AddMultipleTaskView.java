package com.teamworkapp.ui.multipletask;

import com.teamworkapp.data.model.project.Project;
import com.teamworkapp.data.model.tasklist.Tasklist;
import com.teamworkapp.ui.base.MvpView;

import java.util.ArrayList;

/**
 * @author Tosin Onikute.
 */

public interface AddMultipleTaskView extends MvpView {

    void showLoading();

    void hideLoading();

    void setProjectName(ArrayList<Project> projectNames);

    void setTaskLists(ArrayList<Tasklist> taskListNames);

    void openListActivity();

}
