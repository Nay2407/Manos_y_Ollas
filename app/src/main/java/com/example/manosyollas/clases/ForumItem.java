package com.example.manosyollas.clases;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "forums")
public class ForumItem {
    @PrimaryKey
    @NonNull
    public String foroId;

    private String title;
    private String description;
    private int iconResId; // ID del recurso para el ícono


    public ForumItem(String title, String description, int iconResId) {
        this.title = title;
        this.description = description;
        this.iconResId = iconResId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getIconResId() {
        return iconResId;
    }
    public String getForoId() {
        return foroId;
    }
}
