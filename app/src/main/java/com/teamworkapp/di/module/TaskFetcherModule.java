package com.teamworkapp.di.module;

import android.app.Application;

import dagger.Module;

/**
 * @author Tosin Onikute.
 */

@Module
public class TaskFetcherModule {

    private Application application;

    public TaskFetcherModule(Application application){
        this.application = application;
    }

}
