package com.teamworkapp.data.model.task;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Tosin Onikute.
 */

public class TodoItem implements java.io.Serializable {

    private Integer id;
    private Boolean canComplete;
    @SerializedName("comments-count")
    private Integer commentsCount;
    private String description;
    @SerializedName("has-reminders")
    private Boolean hasReminders;
    @SerializedName("has-unread-comments")
    private Boolean hasUnreadComments;
    private Integer _private;
    private String content;
    private Integer order;
    @SerializedName("project-id")
    private Integer projectId;
    @SerializedName("project-name")
    private String projectName;
    @SerializedName("todo-list-id")
    private Integer todoListId;
    @SerializedName("todo-list-name")
    private String todoListName;
    @SerializedName("tasklist-private")
    private Boolean tasklistPrivate;
    @SerializedName("tasklist-isTemplate")
    private Boolean tasklistIsTemplate;
    private String status;
    @SerializedName("company-name")
    private String companyName;
    @SerializedName("company-id")
    private Integer companyId;
    @SerializedName("creator-id")
    private Integer creatorId;
    @SerializedName("creator-firstname")
    private String creatorFirstname;
    @SerializedName("creator-lastname")
    private String creatorLastname;
    private Boolean completed;
    @SerializedName("start-date")
    private String startDate;
    @SerializedName("due-date-base")
    private String dueDateBase;
    @SerializedName("due-date")
    private String dueDate;
    @SerializedName("created-on")
    private String createdOn;
    @SerializedName("last-changed-on")
    private String lastChangedOn;
    private Integer position;
    @SerializedName("estimated-minutes")
    private Integer estimatedMinutes;
    private String priority;
    private Integer progress;
    @SerializedName("harvest-enabled")
    private Boolean harvestEnabled;
    private String parentTaskId;
    private String lockdownId;
    @SerializedName("tasklist-lockdownId")
    private String tasklistLockdownId;

    private Integer hasDependencies;
    private Integer hasPredecessors;
    private Boolean hasTickets;
    private List<Tag> tags = null;
    private String timeIsLogged;
    @SerializedName("attachments-count")
    private Integer attachmentsCount;
    @SerializedName("responsible-party-ids")
    private String responsiblePartyIds;
    @SerializedName("responsible-party-id")
    private String responsiblePartyId;
    @SerializedName("responsible-party-names")
    private String responsiblePartyNames;
    @SerializedName("responsible-party-type")
    private String responsiblePartyType;
    @SerializedName("responsible-party-firstname")
    private String responsiblePartyFirstname;
    @SerializedName("responsible-party-lastname")
    private String responsiblePartyLastname;
    @SerializedName("responsible-party-summary")
    private String responsiblePartySummary;
    private List<Object> predecessors = null;
    private Recurring recurring;
    private Boolean canEdit;
    private Boolean viewEstimatedTime;
    @SerializedName("creator-avatar-url")
    private String creatorAvatarUrl;
    private Boolean canLogTime;
    private String commentFollowerSummary;
    private String changeFollowerSummary;
    private String commentFollowerIds;
    private String changeFollowerIds;
    private Boolean userFollowingComments;
    private Boolean userFollowingChanges;
    private Integer dLM;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getCanComplete() {
        return canComplete;
    }

    public void setCanComplete(Boolean canComplete) {
        this.canComplete = canComplete;
    }

    public Integer getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(Integer commentsCount) {
        this.commentsCount = commentsCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getHasReminders() {
        return hasReminders;
    }

    public void setHasReminders(Boolean hasReminders) {
        this.hasReminders = hasReminders;
    }

    public Boolean getHasUnreadComments() {
        return hasUnreadComments;
    }

    public void setHasUnreadComments(Boolean hasUnreadComments) {
        this.hasUnreadComments = hasUnreadComments;
    }

    public Integer getPrivate() {
        return _private;
    }

    public void setPrivate(Integer _private) {
        this._private = _private;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getTodoListId() {
        return todoListId;
    }

    public void setTodoListId(Integer todoListId) {
        this.todoListId = todoListId;
    }

    public String getTodoListName() {
        return todoListName;
    }

    public void setTodoListName(String todoListName) {
        this.todoListName = todoListName;
    }

    public Boolean getTasklistPrivate() {
        return tasklistPrivate;
    }

    public void setTasklistPrivate(Boolean tasklistPrivate) {
        this.tasklistPrivate = tasklistPrivate;
    }

    public Boolean getTasklistIsTemplate() {
        return tasklistIsTemplate;
    }

    public void setTasklistIsTemplate(Boolean tasklistIsTemplate) {
        this.tasklistIsTemplate = tasklistIsTemplate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatorFirstname() {
        return creatorFirstname;
    }

    public void setCreatorFirstname(String creatorFirstname) {
        this.creatorFirstname = creatorFirstname;
    }

    public String getCreatorLastname() {
        return creatorLastname;
    }

    public void setCreatorLastname(String creatorLastname) {
        this.creatorLastname = creatorLastname;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getDueDateBase() {
        return dueDateBase;
    }

    public void setDueDateBase(String dueDateBase) {
        this.dueDateBase = dueDateBase;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getLastChangedOn() {
        return lastChangedOn;
    }

    public void setLastChangedOn(String lastChangedOn) {
        this.lastChangedOn = lastChangedOn;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getEstimatedMinutes() {
        return estimatedMinutes;
    }

    public void setEstimatedMinutes(Integer estimatedMinutes) {
        this.estimatedMinutes = estimatedMinutes;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public Boolean getHarvestEnabled() {
        return harvestEnabled;
    }

    public void setHarvestEnabled(Boolean harvestEnabled) {
        this.harvestEnabled = harvestEnabled;
    }

    public String getParentTaskId() {
        return parentTaskId;
    }

    public void setParentTaskId(String parentTaskId) {
        this.parentTaskId = parentTaskId;
    }

    public String getLockdownId() {
        return lockdownId;
    }

    public void setLockdownId(String lockdownId) {
        this.lockdownId = lockdownId;
    }

    public String getTasklistLockdownId() {
        return tasklistLockdownId;
    }

    public void setTasklistLockdownId(String tasklistLockdownId) {
        this.tasklistLockdownId = tasklistLockdownId;
    }

    public Integer getHasDependencies() {
        return hasDependencies;
    }

    public void setHasDependencies(Integer hasDependencies) {
        this.hasDependencies = hasDependencies;
    }

    public Integer getHasPredecessors() {
        return hasPredecessors;
    }

    public void setHasPredecessors(Integer hasPredecessors) {
        this.hasPredecessors = hasPredecessors;
    }

    public Boolean getHasTickets() {
        return hasTickets;
    }

    public void setHasTickets(Boolean hasTickets) {
        this.hasTickets = hasTickets;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public String getTimeIsLogged() {
        return timeIsLogged;
    }

    public void setTimeIsLogged(String timeIsLogged) {
        this.timeIsLogged = timeIsLogged;
    }

    public Integer getAttachmentsCount() {
        return attachmentsCount;
    }

    public void setAttachmentsCount(Integer attachmentsCount) {
        this.attachmentsCount = attachmentsCount;
    }

    public String getResponsiblePartyIds() {
        return responsiblePartyIds;
    }

    public void setResponsiblePartyIds(String responsiblePartyIds) {
        this.responsiblePartyIds = responsiblePartyIds;
    }

    public String getResponsiblePartyId() {
        return responsiblePartyId;
    }

    public void setResponsiblePartyId(String responsiblePartyId) {
        this.responsiblePartyId = responsiblePartyId;
    }

    public String getResponsiblePartyNames() {
        return responsiblePartyNames;
    }

    public void setResponsiblePartyNames(String responsiblePartyNames) {
        this.responsiblePartyNames = responsiblePartyNames;
    }

    public String getResponsiblePartyType() {
        return responsiblePartyType;
    }

    public void setResponsiblePartyType(String responsiblePartyType) {
        this.responsiblePartyType = responsiblePartyType;
    }

    public String getResponsiblePartyFirstname() {
        return responsiblePartyFirstname;
    }

    public void setResponsiblePartyFirstname(String responsiblePartyFirstname) {
        this.responsiblePartyFirstname = responsiblePartyFirstname;
    }

    public String getResponsiblePartyLastname() {
        return responsiblePartyLastname;
    }

    public void setResponsiblePartyLastname(String responsiblePartyLastname) {
        this.responsiblePartyLastname = responsiblePartyLastname;
    }

    public String getResponsiblePartySummary() {
        return responsiblePartySummary;
    }

    public void setResponsiblePartySummary(String responsiblePartySummary) {
        this.responsiblePartySummary = responsiblePartySummary;
    }

    public List<Object> getPredecessors() {
        return predecessors;
    }

    public void setPredecessors(List<Object> predecessors) {
        this.predecessors = predecessors;
    }

    public Recurring getRecurring() {
        return recurring;
    }

    public void setRecurring(Recurring recurring) {
        this.recurring = recurring;
    }

    public Boolean getCanEdit() {
        return canEdit;
    }

    public void setCanEdit(Boolean canEdit) {
        this.canEdit = canEdit;
    }

    public Boolean getViewEstimatedTime() {
        return viewEstimatedTime;
    }

    public void setViewEstimatedTime(Boolean viewEstimatedTime) {
        this.viewEstimatedTime = viewEstimatedTime;
    }

    public String getCreatorAvatarUrl() {
        return creatorAvatarUrl;
    }

    public void setCreatorAvatarUrl(String creatorAvatarUrl) {
        this.creatorAvatarUrl = creatorAvatarUrl;
    }

    public Boolean getCanLogTime() {
        return canLogTime;
    }

    public void setCanLogTime(Boolean canLogTime) {
        this.canLogTime = canLogTime;
    }

    public String getCommentFollowerSummary() {
        return commentFollowerSummary;
    }

    public void setCommentFollowerSummary(String commentFollowerSummary) {
        this.commentFollowerSummary = commentFollowerSummary;
    }

    public String getChangeFollowerSummary() {
        return changeFollowerSummary;
    }

    public void setChangeFollowerSummary(String changeFollowerSummary) {
        this.changeFollowerSummary = changeFollowerSummary;
    }

    public String getCommentFollowerIds() {
        return commentFollowerIds;
    }

    public void setCommentFollowerIds(String commentFollowerIds) {
        this.commentFollowerIds = commentFollowerIds;
    }

    public String getChangeFollowerIds() {
        return changeFollowerIds;
    }

    public void setChangeFollowerIds(String changeFollowerIds) {
        this.changeFollowerIds = changeFollowerIds;
    }

    public Boolean getUserFollowingComments() {
        return userFollowingComments;
    }

    public void setUserFollowingComments(Boolean userFollowingComments) {
        this.userFollowingComments = userFollowingComments;
    }

    public Boolean getUserFollowingChanges() {
        return userFollowingChanges;
    }

    public void setUserFollowingChanges(Boolean userFollowingChanges) {
        this.userFollowingChanges = userFollowingChanges;
    }

    public Integer getDLM() {
        return dLM;
    }

    public void setDLM(Integer dLM) {
        this.dLM = dLM;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
