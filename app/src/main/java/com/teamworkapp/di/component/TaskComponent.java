package com.teamworkapp.di.component;

import com.teamworkapp.di.module.RetrofitModule;
import com.teamworkapp.di.module.TaskFetcherModule;
import com.teamworkapp.di.module.TaskModule;
import com.teamworkapp.di.scope.UserScope;
import com.teamworkapp.ui.edittask.EditTaskActivity;
import com.teamworkapp.ui.listtask.ListTaskActivity;
import com.teamworkapp.ui.multipletask.MultipleTaskActivity;

import dagger.Component;

/**
 * @author Tosin Onikute.
 */

@UserScope
@Component(dependencies = NetComponent.class, modules = {RetrofitModule.class})
public interface TaskComponent {


}
