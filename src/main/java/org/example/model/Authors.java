package org.example.model;

public class Authors {
    protected int authorID;
    protected String name;

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
