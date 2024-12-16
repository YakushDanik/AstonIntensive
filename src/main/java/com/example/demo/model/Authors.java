package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "authors")
public class Authors {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    protected int authorID;

    protected String name;

    public Authors(){}
    public Authors(int authorID, String name){
        this.authorID = authorID;
        this.name = name;
    }

    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getAuthorID() {
        return authorID;
    }
}