package com.example.lifeassistant.ToDoStack;

import org.threeten.bp.Duration;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime; ////
// https://github.com/JakeWharton/ThreeTenABP
// https://stackoverflow.com/questions/28745205/cannot-resolve-symbol-java-time-localdate-error-in-android-studio


public class Task {
    private Priority taskPriority;
    private String taskInfo;
    LocalDateTime deadlineDate;
    LocalDateTime createdDate;

    public Task(Priority taskPriority, String taskInfo, LocalDateTime deadlineDate) {
        this.taskPriority = taskPriority;
        this.taskInfo = taskInfo;
        this.deadlineDate = deadlineDate;
        this.createdDate = LocalDateTime.now();
    }

    public Task(Priority taskPriority, String taskInfo, LocalDate deadlineDate) {
        this.taskPriority = taskPriority;
        this.taskInfo = taskInfo;
        this.deadlineDate = deadlineDate.atStartOfDay();
        this.createdDate = LocalDateTime.now();
    }

    private long getSecondsLeft() {
        LocalDateTime now = LocalDateTime.now();
        return (Duration.between(now, deadlineDate).getSeconds() % 60);
    }

    private long getDaysLeft() {
        LocalDateTime now = LocalDateTime.now();
        return Duration.between(now, deadlineDate).toDays();
    }

    private long getHoursLeft() {
        LocalDateTime now = LocalDateTime.now();
        return (Duration.between(now, deadlineDate).toHours() % 24);
    }

    private long getMinutesLeft() {
        LocalDateTime now = LocalDateTime.now();
        return (Duration.between(now, deadlineDate).toMinutes() % 60);
    }
    public String getTimeLeft() {
        return String.valueOf(getDaysLeft())+" days, "+
                String.valueOf(getHoursLeft())+"h, "+
                String.valueOf(getMinutesLeft())+"m, "+
                String.valueOf(getSecondsLeft())+"s.";
    }


    public Priority getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(Priority taskPriority) {
        this.taskPriority = taskPriority;
    }

    public String getTaskInfo() {
        return taskInfo;
    }

    public void setTaskInfo(String taskInfo) {
        this.taskInfo = taskInfo;
    }

    public LocalDateTime getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(LocalDateTime deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}

