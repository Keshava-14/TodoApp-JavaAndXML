package com.example.mobileassignment1.Model;

public class ToDoModel {

    private String task;
    private int id, status;

    public String getTask(){
        return this.task;
    }

    public void setTask(String task){
        this.task = task;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getStatus(){
        return this.status;
    }

    public void setStatus(int status){
        this.status = status;
    }
}
