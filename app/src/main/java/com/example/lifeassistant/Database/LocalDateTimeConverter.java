package com.example.lifeassistant.Database;

import androidx.room.TypeConverter;

import org.threeten.bp.LocalDateTime;


public class LocalDateTimeConverter {
    @TypeConverter
    public LocalDateTime fromTimestamp(String date) {
        return date == null ? null : LocalDateTime.parse(date);
    }

    @TypeConverter
    public String dateToTimestamp(LocalDateTime date) {
        if (date == null) {
            return null;
        } else {
            return date.toString();
        }
    }

}

