package com.teamworkapp.data.model.task;

import com.google.gson.annotations.SerializedName;

/**
 * @author Tosin Onikute.
 */

public class TaskUpdate {

    @SerializedName("todo-item")
    private EditTask todoItems = null;

    public TaskUpdate(EditTask todoItems){
        this.todoItems = todoItems;
    }

}
