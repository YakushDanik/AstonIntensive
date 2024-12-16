package org.example.model;

public class BooksCategories {
    protected int bookID;
    protected int categoryID;

    public BooksCategories(int bookID, int categoryID){
        this.bookID = bookID;
        this.categoryID = categoryID;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }
}
