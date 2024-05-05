package ru.volsu.alerting.telegram.dto;

import ru.volsu.alerting.telegram.model.Message;

public class MessageDTO {

    private String title;

    private String text;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Message toEntity() {
        return new Message(title, text);
    }
}
