package com.teamworkapp.di.component;

import com.teamworkapp.di.module.RetrofitModule;
import com.teamworkapp.di.scope.UserScope;
import com.teamworkapp.ui.listtask.ListTaskActivity;

import dagger.Component;

/**
 * @author Tosin Onikute.
 */

@UserScope
@Component(dependencies = NetComponent.class, modules = {RetrofitModule.class})
public interface TaskComponent {

    void inject(ListTaskActivity listTaskActivity);
}
