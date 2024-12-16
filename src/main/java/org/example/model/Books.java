package org.example.model;

import java.awt.print.Book;

public class Books {
    protected int bookID;
    protected String title;
    protected int authorID;

    public Books(int bookID, String title, int authorID){
        this.bookID = bookID;
        this.title = title;
        this.authorID = authorID;
    }

    public int getAuthorID() {
        return authorID;
    }

    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
