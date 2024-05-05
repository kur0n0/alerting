package ru.volsu.alerting.telegram.dto;

public class SendMessageRequest {

    private String userGroupName;

    private MessageDTO message;

    public String getUserGroupName() {
        return userGroupName;
    }

    public void setUserGroupName(String userGroupName) {
        this.userGroupName = userGroupName;
    }

    public MessageDTO getMessage() {
        return message;
    }

    public void setMessage(MessageDTO message) {
        this.message = message;
    }
}
