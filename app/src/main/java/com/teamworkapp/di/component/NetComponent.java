package com.teamworkapp.di.component;

import android.content.SharedPreferences;


import com.teamworkapp.di.module.AppModule;
import com.teamworkapp.di.module.NetModule;

import javax.inject.Singleton;

import dagger.Component;
import retrofit.RestAdapter;


/**
 * @author Tosin Onikute.
 */

@Singleton
@Component(modules={AppModule.class, NetModule.class})
public interface NetComponent {

    // downstream components need these exposed
    RestAdapter restAdapter();
    SharedPreferences sharedPreferences();

}