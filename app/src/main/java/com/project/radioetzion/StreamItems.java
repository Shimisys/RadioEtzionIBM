package com.project.radioetzion;

public class StreamItems {

    private int imgStream;
    private String txtStream;
    private int duration;
    private String createdTime;

    public StreamItems(int imgStream, String txtStream, int duration, String createdTime) {
        this.imgStream = imgStream;
        this.txtStream = txtStream;
        this.duration = duration;
        this.createdTime = createdTime;
    }

    public int getImgStream() {
        return imgStream;
    }

    public String getTxtStream() {
        return txtStream;
    }

    public int getDuration() {
        return duration;
    }

    public String getCreatedTime() {
        return createdTime;
    }
}
