package com.teamworkapp.di.component;

import com.teamworkapp.di.module.RetrofitModule;
import com.teamworkapp.di.scope.UserScope;

import dagger.Component;

/**
 * @author Tosin Onikute.
 */

@UserScope
@Component(dependencies = NetComponent.class, modules = {RetrofitModule.class})
public interface TaskComponent {


}
