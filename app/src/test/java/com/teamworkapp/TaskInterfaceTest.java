package com.teamworkapp;

import android.app.Application;

import com.teamworkapp.data.model.task.Task;
import com.teamworkapp.data.model.task.TodoItem;
import com.teamworkapp.data.remote.TaskInteractor;
import com.teamworkapp.data.remote.TaskInterface;
import com.teamworkapp.ui.listtask.ListTaskPresenter;
import com.teamworkapp.ui.listtask.ListTaskView;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.observers.TestSubscriber;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

/**
 * @author Tosin Onikute.
 */

public class TaskInterfaceTest {

    @Mock
    TaskInterface taskInterface;

    @Mock
    Application application;

    @Mock
    TaskInteractor taskInteractor;

    @Mock
    ListTaskView listTaskView;

    private ListTaskPresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new ListTaskPresenter(application, taskInteractor);
        presenter.attachView(listTaskView);
    }


    @Test
    public void testAPIResponse() throws Exception {
        // for this test, we use the provided Task API https://yat.teamwork.com/tasks.json?sort=project

        String tasktitle = "Task 1";
        String projectName = "Adamantium";
        String creatorFirstName = "Brian";
        String creatorLastName = "Halpin";

        // when
        when(taskInterface.getAllTask()).thenReturn(Observable.just(taskList()));

        // given
        given(taskInterface.getAllTask()).willReturn(Observable.just(taskList()));

        // When
        TestSubscriber<Task> subscriber = new TestSubscriber<>();
        taskInterface.getAllTask().subscribe(subscriber);

        // Then
        subscriber.awaitTerminalEvent();
        subscriber.assertNoErrors();

        List<Task> onNextEvents = subscriber.getOnNextEvents();
        List<TodoItem> countryInfo = onNextEvents.get(0).getTodoItems();


        // assert for task title
        Assert.assertEquals(tasktitle, countryInfo.get(0).getContent());

        // assert for project name
        Assert.assertEquals(projectName, countryInfo.get(0).getProjectName());

        // assert for creator first name
        Assert.assertEquals(creatorFirstName, countryInfo.get(0).getCreatorFirstname());

        // assert for creator last name
        Assert.assertEquals(creatorLastName, countryInfo.get(0).getCreatorLastname());

    }


    private Task taskList() {

        // Setting up the values for test

        Task tasks = new Task();

        TodoItem todoItem = new TodoItem();

        List<TodoItem> todoItemList = new ArrayList<>();

        todoItem.setContent("Task 1");
        todoItem.setProjectName("Adamantium");
        todoItem.setStartDate("20170815");
        todoItem.setDueDate("20171012");
        todoItem.setPriority("medium");
        todoItem.setCreatorFirstname("Brian");
        todoItem.setCreatorLastname("Halpin");
        todoItem.setTodoListId(1115157);

        todoItemList.add(todoItem);

        tasks.setTodoItems(todoItemList);

        return tasks;
    }



}
