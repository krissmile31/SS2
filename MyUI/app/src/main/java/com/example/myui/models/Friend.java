package com.example.myui.models;

import java.io.Serializable;

public class Friend implements Serializable {
    private String name;
    private String nickname;
    private String insta;
    private String fb;
    private String email;
    private String phoneNo;

    public Friend(String name, String nickname, String insta, String fb, String email, String phoneNo) {
        this.name = name;
        this.nickname = nickname;
        this.insta = insta;
        this.fb = fb;
        this.email = email;
        this.phoneNo = phoneNo;
    }

    public String getInsta() {
        return insta;
    }

    public void setInsta(String insta) {
        this.insta = insta;
    }

    public String getFb() {
        return fb;
    }

    public void setFb(String fb) {
        this.fb = fb;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}