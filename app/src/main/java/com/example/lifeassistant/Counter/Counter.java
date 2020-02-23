package com.example.lifeassistant.Counter;

class Counter {
    private int count;
    private String name;
    Counter(String name, int count){
        this.name = name;
        this.count = count;
    }
    public void increment() {
        count++;
    }
    public void decrement() { count--; }
    public void setToZero() { count = 0; }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
