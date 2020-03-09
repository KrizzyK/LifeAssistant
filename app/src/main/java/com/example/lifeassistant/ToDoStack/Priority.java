package com.example.lifeassistant.ToDoStack;

import java.util.HashMap;
import java.util.Map;

// i used that code: https://codingexplained.com/coding/java/enum-to-integer-and-integer-to-enum

public enum Priority {
    LOW(0), MEDIUM(1), HIGH(2), URGENT(3);


    private final int value;
    private static Map map = new HashMap<>();

    private Priority(int value) {
        this.value = value;
    }

    static {
        for (Priority priority : Priority.values()) {
            map.put(priority.value, priority);
        }
    }
    public static Priority valueOf(int value) {
        return (Priority) map.get(value);
    }


    public int getValue() {
        return value;
    }
}
