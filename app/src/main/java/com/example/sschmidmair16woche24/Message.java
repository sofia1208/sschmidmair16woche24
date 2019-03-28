package com.example.sschmidmair16woche24;

import android.support.annotation.NonNull;

import java.util.Date;

public class Message implements Comparable<Message> {
    private String username;
    private Date date;
    private String message;
    private int id;
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void setId (int i)
    {
        id = i;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Message(String username,String message) {
        this.username = username;
        date = new Date();
        this.message = message;
        id++;
    }

    @Override
    public String toString() {
        return "Message{" +
                "username='" + username + '\'' +
                ", date='" + date + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    @Override
    public int compareTo(@NonNull Message message) {
        return message.getDate().compareTo(date);
    }
}
