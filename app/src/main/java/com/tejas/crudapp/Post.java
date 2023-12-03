package com.tejas.crudapp;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Posts_table")
public class Post {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;

    private String description;
    private String author;



    public Post(String title, String description,String author) {
        this.title = title;
        this.description = description;
        this.author = author;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }
}