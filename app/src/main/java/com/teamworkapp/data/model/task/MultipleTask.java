package com.teamworkapp.data.model.task;

/**
 * @author Tosin Onikute.
 */

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public class MultipleTask {

    private String content;
    private Long tasklistId;
    @SerializedName("creator-id")
    private Long creatorId;
    private Boolean notify;
    private Boolean _private;
    private TodoItem todoItem;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getTasklistId() {
        return tasklistId;
    }

    public void setTasklistId(Long tasklistId) {
        this.tasklistId = tasklistId;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Boolean getNotify() {
        return notify;
    }

    public void setNotify(Boolean notify) {
        this.notify = notify;
    }

    public Boolean getPrivate() {
        return _private;
    }

    public void setPrivate(Boolean _private) {
        this._private = _private;
    }

    public TodoItem getTodoItem() {
        return todoItem;
    }

    public void setTodoItem(TodoItem todoItem) {
        this.todoItem = todoItem;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
