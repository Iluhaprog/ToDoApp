package com.mycompany.todoapp.entitys;

public class User {

    private int id;
    private String name;
    private String pass;
    private String role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String toString() {
        return "id: " + this.id + ";\nname: " + this.name + ";\nPassword: " + this.pass + ";\nRole: " + this.role;
    }

    public boolean isEmpty(){
        if (this.name == null && this.pass == null && this.role == null) {
            return true;
        }
        return false;
    }
}