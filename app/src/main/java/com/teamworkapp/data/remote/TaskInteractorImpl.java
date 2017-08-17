package com.teamworkapp.data.remote;

import android.app.Application;

import com.teamworkapp.data.model.task.Task;
import com.teamworkapp.util.Logger;

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




}
