package com.example.manosyollas.clases;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ChatMessages")
public class MessageItem {
    @PrimaryKey
    @NonNull
    private String messageId;
    private int forumId;
    private String content;
    private String userId;
    private String timestamp;
    private int userProfileImage;
    private String userName;  // Nuevo campo para el nombre del usuario

    public MessageItem(String messageId, Integer forumId, String content, String userId, String timestamp, int userProfileImage, String userName) {
        this.messageId = messageId;
        this.forumId = forumId;
        this.content = content;
        this.userId = userId;
        this.timestamp = timestamp;
        this.userProfileImage = userProfileImage;
        this.userName = userName;
    }

    public String getMessageId() {
        return messageId;
    }

    public Integer getForumId() {
        return forumId;
    }

    public String getContent() {
        return content;
    }

    public String getUserId() {
        return userId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public int getUserProfileImage() {
        return userProfileImage;
    }

    public String getUserName() {
        return userName;
    }
}

