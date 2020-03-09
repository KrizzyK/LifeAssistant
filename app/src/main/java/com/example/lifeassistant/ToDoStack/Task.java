package com.example.lifeassistant.ToDoStack;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.lifeassistant.Database.DateConverter;
import com.example.lifeassistant.Database.PriorityConverter;

import org.threeten.bp.Duration;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.LocalTime;

import java.io.Serializable;
// https://github.com/JakeWharton/ThreeTenABP
// https://stackoverflow.com/questions/28745205/cannot-resolve-symbol-java-time-localdate-error-in-android-studio

@Entity
public class Task implements Serializable {
    @TypeConverters(PriorityConverter.class)
    @ColumnInfo(name = "taskPriority")
    private Priority taskPriority;

    @ColumnInfo(name = "taskInfo")
    private String taskInfo;

    @TypeConverters(DateConverter.class)
    @ColumnInfo(name = "deadlineDate")
    LocalDateTime deadlineDate;

    @TypeConverters(DateConverter.class)
    @PrimaryKey
    @NonNull
    LocalDateTime createdDate;


    public Task(Priority taskPriority, String taskInfo, LocalDateTime deadlineDate) {
        this.taskPriority = taskPriority;
        this.taskInfo = taskInfo;
        this.deadlineDate = deadlineDate;
        this.createdDate = LocalDateTime.now();
    }
    @Ignore
    public Task() {
        this.taskPriority = Priority.MEDIUM;
        this.taskInfo = "";
        this.deadlineDate = LocalDateTime.now(); //
        this.createdDate = LocalDateTime.now();
    }

    @Ignore
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

    @Override
    public String toString() {
        return "Task{" +
                "taskPriority=" + taskPriority +
                ", taskInfo='" + taskInfo + '\'' +
                ", deadlineDate=" + deadlineDate +
                ", createdDate=" + createdDate +
                '}';
    }

    public LocalDate getDeadlineDateOnly(){
        return LocalDate.of(deadlineDate.getYear(), deadlineDate.getMonth(), deadlineDate.getDayOfMonth());
    }
    public LocalTime getDeadlineTimeOnly() {
        return  LocalTime.of(deadlineDate.getHour(), deadlineDate.getMinute());
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

