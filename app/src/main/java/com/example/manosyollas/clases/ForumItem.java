package com.example.manosyollas.clases;

public class ForumItem {
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
}
