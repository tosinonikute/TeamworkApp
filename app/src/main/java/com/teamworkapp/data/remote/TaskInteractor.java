package com.teamworkapp.data.remote;

import android.content.Context;

import com.teamworkapp.data.model.account.AccountInfo;
import com.teamworkapp.data.model.project.Projects;
import com.teamworkapp.data.model.task.MultipleTask;
import com.teamworkapp.data.model.task.Task;
import com.teamworkapp.data.model.task.TaskUpdate;
import com.teamworkapp.data.model.tasklist.Tasklists;

import rx.Observable;

/**
 * @author Tosin Onikute.
 *
 * TaskInteractor is an interface that is implemented by the TaskInteractorImpl Data Manager
 *
 */

public interface TaskInteractor {

    Observable<AccountInfo> fetchAccountInfo(TaskInterface taskInterface);

    Observable<Task> fetchAllTask(TaskInterface taskInterface);

    void updateTask(TaskInterface taskInterface, TaskUpdate taskUpdate, String id, Context context);

    Observable<Projects> fetchAllProject(TaskInterface taskInterface);

    void addMultipleTask(TaskInterface taskInterface, MultipleTask multipleTask, String id, Context context);

    Observable<Tasklists> fetchTaskList(TaskInterface taskInterface, String projectId);

}
