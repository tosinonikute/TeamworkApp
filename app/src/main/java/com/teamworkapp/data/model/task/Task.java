package com.teamworkapp.data.model.task;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Tosin Onikute.
 */


public class Task implements java.io.Serializable {

    //@SerializedName("STATUS")
    //private String status;
    @SerializedName("todo-items")
    private List<TodoItem> todoItems = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /*public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    */

    public List<TodoItem> getTodoItems() {
        return todoItems;
    }

    public void setTodoItems(List<TodoItem> todoItems) {
        this.todoItems = todoItems;
    }


    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
