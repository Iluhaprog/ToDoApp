package com.mycompany.todoapp.entitys;

public class Doing {

    private int id;
    private String text;
    private String date;
    private boolean isComleted;
    private int userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isComleted() {
        return isComleted;
    }

    public void setComleted(boolean isComleted) {
        this.isComleted = isComleted;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}