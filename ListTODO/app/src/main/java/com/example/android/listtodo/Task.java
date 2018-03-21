package com.example.android.listtodo;

/**
 * Created by Lenovo on 3/19/2018.
 */

public class Task {

    public Task(){

    }
    private String Name,Timestamp;

    public Task(String name, String timestamp) {
        Name = name;
        Timestamp = timestamp;
    }

    public String getName() {
        return Name;
    }

    public String getDate() {
        return Timestamp;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setDate(String timestamp) {
        Timestamp = timestamp;
    }
}
