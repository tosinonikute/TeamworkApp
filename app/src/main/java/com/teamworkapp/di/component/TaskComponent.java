package com.teamworkapp.di.component;

import com.teamworkapp.di.module.RetrofitModule;
import com.teamworkapp.di.module.TaskFetcherModule;
import com.teamworkapp.di.module.TaskModule;
import com.teamworkapp.di.scope.UserScope;
import com.teamworkapp.ui.edittask.EditTaskActivity;
import com.teamworkapp.ui.listtask.ListTaskActivity;

import dagger.Component;

/**
 * @author Tosin Onikute.
 */

@UserScope
@Component(dependencies = NetComponent.class, modules = {RetrofitModule.class, TaskModule.class, TaskFetcherModule.class})
public interface TaskComponent {

    void inject(ListTaskActivity listTaskActivity);
    void inject(EditTaskActivity editTaskActivity);

}
