package com.example.booksharing.model;

import java.io.Serializable;

public class BookPropertyListVwCls implements Serializable {
    private int id;
    private String bookName;
    private String bookCategory;
    private String bookQuantity;

    public int getId() {
        return id;
    }

    public String getBookName() {
        return bookName;
    }

    public String getBookCategory() {
        return bookCategory;
    }

    public String getBookQuantity() {
        return bookQuantity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setBookCategory(String bookCategory) {
        this.bookCategory = bookCategory;
    }

    public void setBookQuantity(String bookQuantity) {
        this.bookQuantity = bookQuantity;
    }
}
