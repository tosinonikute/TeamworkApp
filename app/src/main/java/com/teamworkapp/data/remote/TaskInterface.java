package com.teamworkapp.data.remote;

import com.teamworkapp.data.model.account.AccountInfo;
import com.teamworkapp.data.model.project.Projects;
import com.teamworkapp.data.model.task.MultipleTask;
import com.teamworkapp.data.model.task.Task;
import com.teamworkapp.data.model.task.TaskUpdate;
import com.teamworkapp.data.model.tasklist.Tasklists;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import rx.Observable;

/**
 * @author Tosin Onikute.
 */

public interface TaskInterface {

    @GET("/account.json")
    Observable<AccountInfo> getAccountInfo();

    @GET("/tasks.json")
    void getTask(Callback<Task> response);

    @GET("/tasks.json?sort=project")
    Observable<Task> getAllTask();

    @PUT("/tasks/{id}.json")
    void editTask(@Path("id") String id, @Body TaskUpdate body, Callback<Task> response);

    /* /tasklists/{task_list_id}/quickadd.json */
    @POST("/tasklists/{id}/quickadd.json")
    void addMultipleTask(@Path("id") String id, @Body MultipleTask body, Callback<Task> response);

    @GET("/projects.json?status=ACTIVE")
    Observable<Projects> getAllProject();

    /* /projects/{project_id}/tasklists.json */
    @GET("/projects/{id}/tasklists.json")
    Observable<Tasklists> getTasklist(@Path("id") String id);


}
