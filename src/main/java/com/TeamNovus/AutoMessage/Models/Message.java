package com.TeamNovus.AutoMessage.Models;

public class Message {

    private String message;

    public Message(String raw) {
        this.setMessage(raw);
    }

    public String getMessage() {
        return message;
    }

    public Message setMessage(String raw) {
        this.message = raw;
        return this;
    }
}
