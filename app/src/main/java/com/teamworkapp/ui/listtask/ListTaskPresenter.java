package com.teamworkapp.ui.listtask;

import android.app.Application;

import com.teamworkapp.data.model.task.Task;
import com.teamworkapp.data.model.task.TodoItem;
import com.teamworkapp.data.remote.TaskInteractor;
import com.teamworkapp.data.remote.TaskInterface;
import com.teamworkapp.ui.base.BasePresenter;
import com.teamworkapp.util.Logger;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * @author Tosin Onikute.
 */

public class ListTaskPresenter extends BasePresenter<ListTaskView>{

    private final Application application;
    private ListTaskView listTaskView;
    private Logger logger = Logger.getLogger(getClass());

    TaskInteractor taskInteractor;


    public ListTaskPresenter(Application application, TaskInteractor taskInteractor) {
        this.application = application;
        this.taskInteractor = taskInteractor;
    }

    @Override
    public void attachView(ListTaskView listTaskView){
        super.attachView(listTaskView);
    }

    @Override
    public void detachView(){
        super.detachView();
    }

    public void getTaskList(TaskInterface taskInterface, CompositeSubscription mCompositeSubscription){

        getMvpView().showLoading();

        mCompositeSubscription.add(taskInteractor.fetchAllTask(taskInterface)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Task>() {
                    @Override
                    public void call(Task posts) {

                        getMvpView().hideLoading();
                        List<TodoItem> arr = posts.getTodoItems();

                        ArrayList<TodoItem> taskItemList = new ArrayList<TodoItem>(arr);
                        getMvpView().setAdapter(taskItemList);

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        logger.debug(throwable.getLocalizedMessage());
                    }
                }));

    }




}
