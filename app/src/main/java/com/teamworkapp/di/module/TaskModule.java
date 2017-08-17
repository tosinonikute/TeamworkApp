package com.teamworkapp.di.module;

import android.app.Application;

import com.teamworkapp.data.remote.TaskInteractor;
import com.teamworkapp.data.remote.TaskInteractorImpl;
import com.teamworkapp.ui.edittask.EditTaskPresenter;
import com.teamworkapp.ui.listtask.ListTaskPresenter;
import com.teamworkapp.ui.multipletask.AddMultipleTaskPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author Tosin Onikute.
 */

@Module
public class TaskModule {

    private Application application;

    public TaskModule(Application application){
        this.application = application;
    }

    @Provides
    public ListTaskPresenter getHelloPresenter(TaskInteractor taskInteractor){
        return new ListTaskPresenter(application, taskInteractor);
    }

    @Provides
    public AddMultipleTaskPresenter getAddTaskPresenter(TaskInteractor taskInteractor){
        return new AddMultipleTaskPresenter(application, taskInteractor);
    }

    @Provides
    public EditTaskPresenter getEditTaskPresenter(TaskInteractor taskInteractor){
        return new EditTaskPresenter(application, taskInteractor);
    }

    @Provides
    TaskInteractor provideHelloFetcher() {
        return new TaskInteractorImpl( application );
    }



}
