package com.example.manosyollas.clases;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ChatMessages")
public class ChatMessage {


    @PrimaryKey(autoGenerate = true)
    private int id;
    private String content;
    private int forumId; // ID del foro al que pertenece el mensaje

    public ChatMessage(String content, int forumId, String userName, String timestamp, String userProfileImage) {
        this.content = content;
        this.forumId = forumId;
        this.userName = userName;
        this.timestamp = timestamp;
        this.userProfileImage = userProfileImage;
    }

    private String userName; // Nombre del usuario
    private String timestamp; // Para gestionar la fecha y hora del mensaje
    private String userProfileImage; // Recurso de la imagen del perfil (id del drawable)

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getForumId() {
        return forumId;
    }

    public void setForumId(int forumId) {
        this.forumId = forumId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserProfileImage() {
        return userProfileImage; // Devuelve el id del recurso
    }

    public void setUserProfileImage(String userProfileImage) {
        this.userProfileImage = userProfileImage;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
