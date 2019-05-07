package com.example.booksharing.model;

import java.io.Serializable;

public class PointTableListVwCls implements Serializable {
    private String id;
    private String mail;
    private int point;


    public PointTableListVwCls() {
    }

    public PointTableListVwCls(String id, String mail, int point) {
        this.id = id;
        this.mail = mail;
        this.point = point;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
