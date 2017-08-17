package com.teamworkapp;

import android.app.Application;

import com.teamworkapp.di.component.DaggerNetComponent;
import com.teamworkapp.di.component.DaggerTaskComponent;
import com.teamworkapp.di.component.NetComponent;
import com.teamworkapp.di.component.TaskComponent;
import com.teamworkapp.di.module.AppModule;
import com.teamworkapp.di.module.NetModule;
import com.teamworkapp.di.module.RetrofitModule;
import com.teamworkapp.di.module.TaskFetcherModule;
import com.teamworkapp.di.module.TaskModule;


/**
 * @author Tosin Onikute.
 */

public class BaseApplication extends Application {

    public TaskComponent component;
    private NetComponent mNetComponent;

    @Override
    public void onCreate(){
        super.onCreate();

        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule())
                .build();

        component = DaggerTaskComponent
                .builder()
                .netComponent(mNetComponent)
                .retrofitModule(new RetrofitModule())
                .taskModule(new TaskModule(this))
                .taskFetcherModule(new TaskFetcherModule(this))
                .build();

    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }

    public TaskComponent getComponent() {
        return component;
    }

}
