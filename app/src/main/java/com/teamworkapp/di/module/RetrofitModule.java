package com.teamworkapp.di.module;


import com.teamworkapp.data.remote.TaskInterface;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;


/**
 * @author Tosin Onikute.
 */

@Module
public class RetrofitModule {

    @Provides
    public TaskInterface providesTaskInterface(RestAdapter restAdapter) {
        return restAdapter.create(TaskInterface.class);
    }
}