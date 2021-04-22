package com.example.myui.mynotes.notes.models;

import java.io.Serializable;

public class Note implements Serializable {
    private long id;
    private String text;

    public Note(String text) {
        this.text = text;
    }

    public Note(long id, String text) {
        this.id = id;
        this.text = text;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
