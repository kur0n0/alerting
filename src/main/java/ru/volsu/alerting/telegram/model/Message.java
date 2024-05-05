package ru.volsu.alerting.telegram.model;

import jakarta.persistence.*;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private int messageId;

    private String title;

    @Column(name = "message_text")
    private String text;

    public Message() {
    }

    public Message(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

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

    public String toTelegramMessage() {
        return String.format("Тема: %s\n\nСообщение: %s", title, text);
    }
}
