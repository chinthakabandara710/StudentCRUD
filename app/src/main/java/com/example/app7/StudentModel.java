package com.example.app7;

public class StudentModel {

    private int id;
    private String name;
    private int age;
    private boolean isPass;

    public StudentModel(int id, String name, int age, boolean isPass) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.isPass = isPass;
    }

    public StudentModel() {
    }


    @Override
    public String toString() {
        return "StudentModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", isPass=" + isPass +
                '}';
    }


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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isPass() {
        return isPass;
    }

    public void setPass(boolean pass) {
        isPass = pass;
    }
}
