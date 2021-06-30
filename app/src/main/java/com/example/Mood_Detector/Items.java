package com.example.Mood_Detector;

public class Items {
    private String title;
    private String content;
    private int imageView;

    public Items(String title, String content, int imageView) {
        this.title = title;
        this.content = content;
        this.imageView = imageView;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getImageView() {
        return imageView;
    }
}
