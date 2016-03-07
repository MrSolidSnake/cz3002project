package com.example.mrsolidsnake.cz3002project;

/**
 * Created by User on 07/03/2016.
 */
public class Person {
    long id;
    String name;
    String picture;
    String[]answers;


    public Person(long id, String name, String picture, String[] answers) {
        this.id = id;
        this.name = name;
        this.picture = picture;
        this.answers = answers;
    }

    public Person(String name, String picture, String[] answers) {
        this.name = name;
        this.picture = picture;
        this.answers = answers;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String[] getAnswers() {
        return answers;
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;
    }

}
