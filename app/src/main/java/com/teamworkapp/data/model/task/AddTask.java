package com.teamworkapp.data.model.task;

import com.google.gson.annotations.SerializedName;

/**
 * @author Tosin Onikute.
 */

public class AddTask {

    public String content;
    public boolean notify;
    public String description;
    @SerializedName("due-date")
    public String dueDate;
    @SerializedName("start-date")
    public String startDate;
    @SerializedName("private")
    public String privates;
    @SerializedName("grant-access-to")
    public String grantAccessTo;
    public String priority;
    public String progress;
    @SerializedName("estimated-minutes")
    public String estimatedMinutes;
    @SerializedName("responsible-party-id")
    public String responsiblePartyId;

    public String tasklistId;
    public String pendingFileAttachments;
    public String tags;
    public String columnId;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }


    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public void setEstimatedMinutes(String estimatedMinutes) {
        this.estimatedMinutes = estimatedMinutes;
    }

    public String getEstimatedMinutes() {
        return estimatedMinutes;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }


    public String getResponsiblePartyId() {
        return responsiblePartyId;
    }

    public void setResponsiblePartyId(String responsiblePartyId) {
        this.responsiblePartyId = responsiblePartyId;
    }


}
