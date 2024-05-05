package ru.volsu.alerting.telegram.model;

import jakarta.persistence.*;
import ru.volsu.alerting.user.model.UserGroup;

@Entity
public class MessageHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_history_id")
    private int messageHistoryId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "message_id")
    private Message message;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_group_id")
    private UserGroup userGroup;

    public int getMessageHistoryId() {
        return messageHistoryId;
    }

    public void setMessageHistoryId(int messageHistoryId) {
        this.messageHistoryId = messageHistoryId;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }
}
