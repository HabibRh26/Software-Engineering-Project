package com.example.booksharing.model;

import java.io.Serializable;

public class BookPropertyListVwCls implements Serializable {
    private String id;
    private String bookName;
    private String bookCategory;
    private String bookQuantity;

    public BookPropertyListVwCls() {
    }

    public BookPropertyListVwCls(String id, String bookName, String bookCategory, String bookQuantity) {
        this.id = id;
        this.bookName = bookName;
        this.bookCategory = bookCategory;
        this.bookQuantity = bookQuantity;
    }

    public String getId() {
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

    public void setId(String id) {
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
