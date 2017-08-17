package com.teamworkapp.data.remote;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.teamworkapp.data.model.project.Projects;
import com.teamworkapp.data.model.task.MultipleTask;
import com.teamworkapp.data.model.task.Task;
import com.teamworkapp.data.model.task.TaskUpdate;
import com.teamworkapp.data.model.tasklist.Tasklists;
import com.teamworkapp.util.Logger;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import rx.Observable;
import rx.functions.Func1;


/**
 * @author Tosin Onikute.
 *
 * This is a Data Manager implementer class which contains methods, exposed for all the tasklist related data handling operations
 * to decouple your class, thus making it cleaner and testable
 *
 */

public class TaskInteractorImpl implements TaskInteractor {

    private final Application application;
    private Logger logger = Logger.getLogger(getClass());

    public TaskInteractorImpl(Application application) {
        this.application = application;
    }


    public Observable<Task> fetchAllTask(TaskInterface taskInterface){

        return taskInterface.getAllTask()
                .flatMap(new Func1<Task, Observable<Task>>() {
                    @Override
                    public Observable<Task> call(Task task) {
                        return Observable.just(task);
                    }
                })
                .onErrorReturn(new Func1<Throwable, Task>() {
                    @Override
                    public Task call(Throwable thr) {
                        return null;
                    }
                });

    }


    public void updateTask(TaskInterface taskInterface, TaskUpdate taskUpdate, String id, final Context context){

        taskInterface.editTask(id, taskUpdate, new Callback<Task>() {
            @Override
            public void success(Task info, Response response) {

                if(response.getStatus() == 200){
                    toastMsg("Task Updated Successfully", context);
                } else {
                    toastMsg(response.getReason(), context);
                }

            }

            @Override
            public void failure(RetrofitError error) {
                logger.debug(error.getLocalizedMessage());
                toastMsg(error.getLocalizedMessage().toString(), context);
            }
        });
    }


    public Observable<Projects> fetchAllProject(TaskInterface taskInterface){

        return taskInterface.getAllProject()
                .flatMap(new Func1<Projects, Observable<Projects>>() {
                    @Override
                    public Observable<Projects> call(Projects projects) {
                        return Observable.just(projects);
                    }
                })
                .onErrorReturn(new Func1<Throwable, Projects>() {
                    @Override
                    public Projects call(Throwable thr) {
                        return null;
                    }
                });
    }


    public void addMultipleTask(TaskInterface taskInterface, MultipleTask multipleTask, String id, final Context context){

        taskInterface.addMultipleTask(id, multipleTask, new Callback<Task>() {
            @Override
            public void success(Task info, Response response) {

                if(response.getStatus() == 200 ){
                    toastMsg("Tasks Created Successfully", context);
                } else {
                    toastMsg(response.getReason(), context);
                }

            }

            @Override
            public void failure(RetrofitError error) {
                logger.debug(error.getLocalizedMessage());
                toastMsg(error.getLocalizedMessage().toString(), context);
            }
        });
    }


    public Observable<Tasklists> fetchTaskList(TaskInterface taskInterface, String projectId){

        return taskInterface.getTasklist(projectId)
                .flatMap(new Func1<Tasklists, Observable<Tasklists>>() {
                    @Override
                    public Observable<Tasklists> call(Tasklists task) {
                        return Observable.just(task);
                    }
                })
                .onErrorReturn(new Func1<Throwable, Tasklists>() {
                    @Override
                    public Tasklists call(Throwable thr) {
                        return null;
                    }
                });

    }

    public void toastMsg(String str, Context context){
        Toast.makeText(context, str, Toast.LENGTH_LONG).show();
    }


}
